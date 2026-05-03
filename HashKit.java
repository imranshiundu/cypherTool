package cyphertool;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashKit {
    private HashKit() {}

    public static String sha256(String input) {
        return digest("SHA-256", input);
    }

    public static String sha512(String input) {
        return digest("SHA-512", input);
    }

    public static String hmacSha256(String input, String secret) {
        if (secret == null || secret.isEmpty()) return "HMAC secret cannot be empty.";
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(key);
            return toHex(mac.doFinal(input.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            return "Could not calculate HMAC-SHA256: " + ex.getMessage();
        }
    }

    private static String digest(String algorithm, String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            return toHex(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException ex) {
            return "Hash algorithm not available: " + algorithm;
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder out = new StringBuilder();
        for (byte b : bytes) out.append(String.format("%02x", b));
        return out.toString();
    }
}
