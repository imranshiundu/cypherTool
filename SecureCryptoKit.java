package cyphertool;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public final class SecureCryptoKit {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int VERSION_AES_GCM = 1;
    private static final int SALT_BYTES = 16;
    private static final int IV_BYTES = 12;
    private static final int TAG_BITS = 128;
    private static final int KEY_BITS = 256;
    private static final int PBKDF2_ITERATIONS = 210_000;

    private SecureCryptoKit() {}

    public static String encryptTextAesGcm(String plaintext, String password) {
        if (plaintext == null || plaintext.isEmpty()) return "Plaintext cannot be empty.";
        if (password == null || password.isEmpty()) return "Password cannot be empty.";

        try {
            byte[] salt = randomBytes(SALT_BYTES);
            byte[] iv = randomBytes(IV_BYTES);
            SecretKeySpec key = deriveAesKey(password.toCharArray(), salt);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_BITS, iv));
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            ByteBuffer packageBuffer = ByteBuffer.allocate(1 + SALT_BYTES + IV_BYTES + ciphertext.length);
            packageBuffer.put((byte) VERSION_AES_GCM);
            packageBuffer.put(salt);
            packageBuffer.put(iv);
            packageBuffer.put(ciphertext);

            return Base64.getEncoder().encodeToString(packageBuffer.array());
        } catch (Exception ex) {
            return "Encryption failed: " + ex.getMessage();
        }
    }

    public static String decryptTextAesGcm(String encryptedPackage, String password) {
        if (encryptedPackage == null || encryptedPackage.isEmpty()) return "Encrypted package cannot be empty.";
        if (password == null || password.isEmpty()) return "Password cannot be empty.";

        try {
            byte[] packed = Base64.getDecoder().decode(encryptedPackage.trim());
            int minimum = 1 + SALT_BYTES + IV_BYTES + 1;
            if (packed.length < minimum || packed[0] != VERSION_AES_GCM) {
                return "Unsupported or damaged encrypted package.";
            }

            ByteBuffer buffer = ByteBuffer.wrap(packed);
            buffer.get();

            byte[] salt = new byte[SALT_BYTES];
            byte[] iv = new byte[IV_BYTES];
            byte[] ciphertext = new byte[buffer.remaining() - SALT_BYTES - IV_BYTES];
            buffer.get(salt);
            buffer.get(iv);
            buffer.get(ciphertext);

            SecretKeySpec key = deriveAesKey(password.toCharArray(), salt);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_BITS, iv));
            byte[] plaintext = cipher.doFinal(ciphertext);
            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException ex) {
            return "Invalid Base64 encrypted package.";
        } catch (Exception ex) {
            return "Decryption failed. Wrong password or modified data.";
        }
    }

    public static String generateAesKeyBase64() {
        byte[] key = randomBytes(32);
        return Base64.getEncoder().encodeToString(key);
    }

    public static String securityProfile() {
        return "AES-GCM secure profile:\n" +
                "- Algorithm: AES/GCM/NoPadding\n" +
                "- Key size: 256-bit derived key\n" +
                "- Password KDF: PBKDF2WithHmacSHA256\n" +
                "- PBKDF2 iterations: " + PBKDF2_ITERATIONS + "\n" +
                "- Salt: 16 random bytes per encryption\n" +
                "- IV/nonce: 12 random bytes per encryption\n" +
                "- Auth tag: 128-bit tamper detection\n" +
                "- Package format: version + salt + iv + ciphertext/auth tag, Base64 encoded\n\n" +
                "This is the correct direction for serious local text encryption. Security still depends on a strong unique password.";
    }

    private static SecretKeySpec deriveAesKey(char[] password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, KEY_BITS);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static byte[] randomBytes(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return bytes;
    }
}
