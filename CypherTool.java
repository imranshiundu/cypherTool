package cyphertool;

import java.util.Scanner;

public class CypherTool {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startProgram() {
        printWelcome();

        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("exit") || choice.equals("0")) {
                System.out.println("CypherTool closed. Keep your real secrets safe.");
                return;
            }

            switch (choice) {
                case "1": runSecureMode(); break;
                case "2": runPlaygroundMode(); break;
                case "3": runUtilityMode(); break;
                case "4": runPasswordMode(); break;
                case "5": runChallengeMode(); break;
                case "6": printSecurityGuide(); break;
                case "7": printUpdates(); break;
                default: System.out.println("Invalid choice. Pick 1-7 or 0.\n");
            }
        }
    }

    private static void printWelcome() {
        TerminalUI.banner();
        System.out.println("Type 'exit' anytime to quit.\n");
    }

    private static void printMainMenu() {
        TerminalUI.section("Main Menu");
        TerminalUI.card("1", "Secure Mode", "REAL ENCRYPTION", "AES-GCM private text encryption and decryption");
        TerminalUI.card("2", "Playground", "PUZZLE + LEARNING", "Classic ciphers, hidden messages, brute-force demos");
        TerminalUI.card("3", "Utility", "DEV + DATA TOOLS", "Base64, Hex, Binary, SHA hashes, HMAC");
        TerminalUI.card("4", "Passwords", "LOCAL SAFETY", "Generate passwords, passphrases, check strength");
        TerminalUI.card("5", "Challenge", "GAME MODE", "Decode puzzles and learn by breaking ciphers");
        TerminalUI.card("6", "Security Guide", "TRUTH LAYER", "Know what is safe and what is just play");
        TerminalUI.card("7", "Updates", "RELEASE INFO", "GitHub source and Telegram announcement channel");
        TerminalUI.card("0", "Exit", "", "Close CypherTool");
        TerminalUI.thinLine();
        System.out.print("> ");
    }

    private static void runSecureMode() {
        while (true) {
            TerminalUI.section("Secure Mode - Authenticated Encryption");
            TerminalUI.card("1", "AES-GCM Encrypt", "Tier 3", "Encrypt private text with password-derived 256-bit key");
            TerminalUI.card("2", "AES-GCM Decrypt", "Tier 3", "Decrypt CypherTool AES-GCM package");
            TerminalUI.card("3", "Generate Key", "Secure random", "Generate a 256-bit random key as Base64");
            TerminalUI.card("4", "Crypto Profile", "Audit info", "Show AES-GCM/KDF/security parameters");
            TerminalUI.card("0", "Back", "", "Return to main menu");
            TerminalUI.thinLine();
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) return;
            if (choice.equals("0")) return;

            String result;
            switch (choice) {
                case "1":
                    explain("AES-GCM Encrypt", "This uses authenticated encryption. If the encrypted package is changed, decryption should fail.");
                    String plaintext = readLine("Enter private text to encrypt:\n> ");
                    if (plaintext == null) return;
                    String encPassword = readLine("Enter strong password. If you lose it, CypherTool cannot recover the text:\n> ");
                    if (encPassword == null) return;
                    result = SecureCryptoKit.encryptTextAesGcm(plaintext, encPassword);
                    break;
                case "2":
                    explain("AES-GCM Decrypt", "Paste a CypherTool AES-GCM package and the correct password.");
                    String encrypted = readLine("Paste encrypted package:\n> ");
                    if (encrypted == null) return;
                    String decPassword = readLine("Enter password:\n> ");
                    if (decPassword == null) return;
                    result = SecureCryptoKit.decryptTextAesGcm(encrypted, decPassword);
                    break;
                case "3":
                    explain("Secure random key", "This generates 32 random bytes encoded as Base64. Store it safely if you use it.");
                    result = SecureCryptoKit.generateAesKeyBase64();
                    break;
                case "4":
                    result = SecureCryptoKit.securityProfile();
                    break;
                default:
                    System.out.println("Invalid secure-mode tool.");
                    continue;
            }
            printResult(result);
        }
    }

    private static void runPlaygroundMode() {
        while (true) {
            TerminalUI.section("Playground - Fun Ciphers");
            TerminalUI.card("1", "ROT13", "Puzzle only", "Letter rotation by 13");
            TerminalUI.card("2", "ROT47", "Puzzle only", "Printable character rotation");
            TerminalUI.card("3", "Atbash", "Puzzle only", "Alphabet mirror");
            TerminalUI.card("4", "Caesar", "Puzzle only", "Custom shift");
            TerminalUI.card("5", "Caesar Break", "Learning", "Show all possible Caesar reversals");
            TerminalUI.card("6", "Morse Encode", "Signal play", "Text to dot-dash");
            TerminalUI.card("7", "Morse Decode", "Signal play", "Dot-dash back to text");
            TerminalUI.card("8", "A1Z26", "Puzzle only", "A=1, B=2, C=3");
            TerminalUI.card("9", "Reverse", "Puzzle only", "Flip text order");
            TerminalUI.card("0", "Back", "", "Return to main menu");
            TerminalUI.thinLine();
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) return;
            if (choice.equals("0")) return;

            String message = readMessage();
            if (message == null) return;

            String result;
            switch (choice) {
                case "1": explain("ROT13", "Every letter moves 13 places. It is fun, but has no key."); result = CipherKit.rot13(message); break;
                case "2": explain("ROT47", "Rotates printable characters. Good for puzzles, not privacy."); result = CipherKit.rot47(message); break;
                case "3": explain("Atbash", "Mirrors the alphabet: A becomes Z, B becomes Y."); result = CipherKit.atbash(message); break;
                case "4":
                    int shift = readInt("Shift amount: ");
                    explain("Caesar", "Moves letters by your chosen shift. Easy to brute force.");
                    result = CipherKit.caesar(message, shift);
                    break;
                case "5": explain("Caesar brute force", "Shows all 25 possible Caesar reversals so learners can see why Caesar is weak."); result = CipherKit.caesarBruteForce(message); break;
                case "6": explain("Morse", "Turns letters and numbers into dot-dash signals."); result = CipherKit.morseEncode(message); break;
                case "7": explain("Morse", "Turns dot-dash groups back into text. Use spaces between letters and / between words."); result = CipherKit.morseDecode(message); break;
                case "8": explain("A1Z26", "A becomes 1, B becomes 2, Z becomes 26."); result = CipherKit.a1z26Encode(message); break;
                case "9": explain("Reverse", "Flips the message order. Simple, visible, and only for fun."); result = CipherKit.reverse(message); break;
                default: System.out.println("Invalid playground method."); continue;
            }
            printResult(result);
        }
    }

    private static void runUtilityMode() {
        while (true) {
            TerminalUI.section("Utility - Useful Offline Tools");
            TerminalUI.card("1", "Base64 Encode", "Encoding", "Text to Base64");
            TerminalUI.card("2", "Base64 Decode", "Encoding", "Base64 to text");
            TerminalUI.card("3", "Hex Encode", "Encoding", "Text to hexadecimal");
            TerminalUI.card("4", "Hex Decode", "Encoding", "Hexadecimal to text");
            TerminalUI.card("5", "Binary Encode", "Encoding", "Text to 8-bit binary");
            TerminalUI.card("6", "Binary Decode", "Encoding", "8-bit binary to text");
            TerminalUI.card("7", "SHA-256", "Fingerprint", "One-way hash");
            TerminalUI.card("8", "SHA-512", "Fingerprint", "Longer one-way hash");
            TerminalUI.card("9", "HMAC-SHA256", "Integrity", "Message check with a shared secret");
            TerminalUI.card("0", "Back", "", "Return to main menu");
            TerminalUI.thinLine();
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) return;
            if (choice.equals("0")) return;

            String message = readMessage();
            if (message == null) return;

            String result;
            switch (choice) {
                case "1": explain("Base64", "Useful when data must travel as text. It is not encryption."); result = CipherKit.base64Encode(message); break;
                case "2": explain("Base64", "Anyone can decode Base64. Never treat it as private."); result = CipherKit.base64Decode(message); break;
                case "3": explain("Hex", "Shows UTF-8 bytes as hexadecimal text."); result = CipherKit.hexEncode(message); break;
                case "4": explain("Hex", "Turns hexadecimal bytes back into text."); result = CipherKit.hexDecode(message); break;
                case "5": explain("Binary", "Shows UTF-8 bytes as 8-bit binary groups."); result = CipherKit.binaryEncode(message); break;
                case "6": explain("Binary", "Turns 8-bit binary groups back into text."); result = CipherKit.binaryDecode(message); break;
                case "7": explain("SHA-256", "Creates a one-way fingerprint. It cannot be decrypted."); result = HashKit.sha256(message); break;
                case "8": explain("SHA-512", "Creates a longer one-way fingerprint. It cannot be decrypted."); result = HashKit.sha512(message); break;
                case "9":
                    explain("HMAC-SHA256", "Uses a secret key to prove a message came from someone with that key and was not changed.");
                    String secret = readLine("Enter HMAC secret: ");
                    result = secret == null ? "Cancelled." : HashKit.hmacSha256(message, secret);
                    break;
                default: System.out.println("Invalid utility tool."); continue;
            }
            printResult(result);
        }
    }

    private static void runPasswordMode() {
        while (true) {
            TerminalUI.section("Passwords - Local Safety Tools");
            TerminalUI.card("1", "Password", "Generate", "Random password using SecureRandom");
            TerminalUI.card("2", "Passphrase", "Generate", "Memorable word-based password");
            TerminalUI.card("3", "Strength", "Check", "Local password strength report");
            TerminalUI.card("0", "Back", "", "Return to main menu");
            TerminalUI.thinLine();
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) return;
            if (choice.equals("0")) return;

            String result;
            switch (choice) {
                case "1":
                    int length = readInt("Length, minimum 8: ");
                    String symbolsAnswer = readLine("Include symbols? yes/no: ");
                    result = PasswordKit.generatePassword(length, symbolsAnswer != null && (symbolsAnswer.equalsIgnoreCase("yes") || symbolsAnswer.equalsIgnoreCase("y")));
                    explain("Password generator", "Creates a random password using Java SecureRandom.");
                    break;
                case "2":
                    int words = readInt("Number of words, minimum 4: ");
                    result = PasswordKit.generatePassphrase(words);
                    explain("Passphrase generator", "Creates a memorable password using random words and a number.");
                    break;
                case "3":
                    String password = readLine("Enter password to check: ");
                    result = password == null ? "Cancelled." : PasswordKit.strengthReport(password);
                    explain("Password strength", "This is a local estimate, not a guarantee. Unique and long still matters most.");
                    break;
                default: System.out.println("Invalid password tool."); continue;
            }
            printResult(result);
        }
    }

    private static void runChallengeMode() {
        TerminalUI.section("Challenge Mode");
        System.out.println("Decode this ROT13 message.");
        String answer = "CypherTool can be useful and fun";
        String puzzle = CipherKit.rot13(answer);
        System.out.println("Puzzle: " + puzzle);
        System.out.print("Your answer: ");
        String attempt = scanner.nextLine();

        if (attempt.equalsIgnoreCase(answer)) {
            System.out.println("Correct. You broke the puzzle.\n");
        } else {
            System.out.println("Not yet. Hint: ROT13 is its own reverse.");
            System.out.println("Answer: " + answer + "\n");
        }
    }

    private static void printSecurityGuide() {
        TerminalUI.section("Security Guide");
        System.out.println("Puzzle ciphers: ROT13, ROT47, Atbash, Caesar, Morse, A1Z26, Reverse.");
        System.out.println("Encoding tools: Base64, Hex, Binary. Useful, but not private.");
        System.out.println("Hash tools: SHA-256 and SHA-512 create fingerprints. They are one-way, not encryption.");
        System.out.println("HMAC: proves message integrity when both sides share a secret key.");
        System.out.println("Password tools: useful now for generating stronger local passwords and passphrases.");
        System.out.println("Secure Mode: AES-GCM gives authenticated encryption for private text when used with a strong password.");
        System.out.println("Future hardening: file encryption, ChaCha20-Poly1305, signed releases, and formal tests.");
        TerminalUI.thinLine();
        System.out.println();
    }

    private static void printUpdates() {
        TerminalUI.section("Updates");
        System.out.println(UpdateKit.updateMessage());
        TerminalUI.thinLine();
        System.out.println();
    }

    private static String readMessage() {
        return readLine("\nEnter text:\n> ");
    }

    private static String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String message = scanner.nextLine();
            if (message.trim().equalsIgnoreCase("exit")) return null;
            if (!message.isEmpty()) return message;
            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static void explain(String title, String explanation) {
        System.out.println("\n" + title + ": " + explanation);
    }

    private static void printResult(String result) {
        System.out.println("\nResult:");
        System.out.println(result);
        System.out.println();
        TerminalUI.thinLine();
        System.out.println();
    }

    public static String encryptRot13(String s) { return CipherKit.rot13(s); }
    public static String decryptRot13(String s) { return CipherKit.rot13(s); }
    public static String encryptAtbash(String s) { return CipherKit.atbash(s); }
    public static String decryptAtbash(String s) { return CipherKit.atbash(s); }
    public static String encryptCaesar(String s) { return CipherKit.caesar(s, 3); }
    public static String decryptCaesar(String s) { return CipherKit.caesar(s, -3); }
}
