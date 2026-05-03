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
                case "1": runPlaygroundMode(); break;
                case "2": runUtilityMode(); break;
                case "3": runChallengeMode(); break;
                case "4": runPasswordMode(); break;
                case "5": printSecurityGuide(); break;
                default: System.out.println("Invalid choice. Pick 1, 2, 3, 4, 5 or 0.\n");
            }
        }
    }

    private static void printWelcome() {
        System.out.println("========================================");
        System.out.println("CypherTool");
        System.out.println("Hide messages for fun. Protect files for real. Learn the difference.");
        System.out.println("========================================");
        System.out.println("Type 'exit' anytime to quit.\n");
    }

    private static void printMainMenu() {
        System.out.println("Choose mode:");
        System.out.println("1. Playground - fun ciphers and hidden messages");
        System.out.println("2. Utility - encoders, decoders, hashes and HMAC");
        System.out.println("3. Challenge - decode a puzzle");
        System.out.println("4. Passwords - generate and check password strength");
        System.out.println("5. Security guide - what is safe and what is only play");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private static void runPlaygroundMode() {
        while (true) {
            System.out.println("\nPlayground methods:");
            System.out.println("1. ROT13                 [Puzzle only]");
            System.out.println("2. ROT47                 [Puzzle only]");
            System.out.println("3. Atbash                [Puzzle only]");
            System.out.println("4. Caesar custom shift   [Puzzle only]");
            System.out.println("5. Caesar brute force    [Learning tool]");
            System.out.println("6. Morse encode          [Encoding/play]");
            System.out.println("7. Morse decode          [Encoding/play]");
            System.out.println("8. A1Z26 encode          [Puzzle only]");
            System.out.println("9. Reverse text          [Puzzle only]");
            System.out.println("0. Back");
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
            System.out.println("\nUtility tools:");
            System.out.println("1. Base64 encode         [Encoding only]");
            System.out.println("2. Base64 decode         [Encoding only]");
            System.out.println("3. Hex encode            [Encoding only]");
            System.out.println("4. Hex decode            [Encoding only]");
            System.out.println("5. Binary encode         [Encoding only]");
            System.out.println("6. Binary decode         [Encoding only]");
            System.out.println("7. SHA-256 hash          [One-way fingerprint]");
            System.out.println("8. SHA-512 hash          [One-way fingerprint]");
            System.out.println("9. HMAC-SHA256           [Message authentication]");
            System.out.println("0. Back");
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
                    result = HashKit.hmacSha256(message, secret);
                    break;
                default: System.out.println("Invalid utility tool."); continue;
            }
            printResult(result);
        }
    }

    private static void runPasswordMode() {
        while (true) {
            System.out.println("\nPassword tools:");
            System.out.println("1. Generate password");
            System.out.println("2. Generate passphrase");
            System.out.println("3. Check password strength");
            System.out.println("0. Back");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) return;
            if (choice.equals("0")) return;

            String result;
            switch (choice) {
                case "1":
                    int length = readInt("Length, minimum 8: ");
                    String symbolsAnswer = readLine("Include symbols? yes/no: ");
                    result = PasswordKit.generatePassword(length, symbolsAnswer.equalsIgnoreCase("yes") || symbolsAnswer.equalsIgnoreCase("y"));
                    explain("Password generator", "Creates a random password using Java SecureRandom.");
                    break;
                case "2":
                    int words = readInt("Number of words, minimum 4: ");
                    result = PasswordKit.generatePassphrase(words);
                    explain("Passphrase generator", "Creates a memorable password using random words and a number.");
                    break;
                case "3":
                    String password = readLine("Enter password to check: ");
                    result = PasswordKit.strengthReport(password);
                    explain("Password strength", "This is a local estimate, not a guarantee. Unique and long still matters most.");
                    break;
                default: System.out.println("Invalid password tool."); continue;
            }
            printResult(result);
        }
    }

    private static void runChallengeMode() {
        System.out.println("\nChallenge: Decode this ROT13 message.");
        String answer = "CYpherTool can be useful and fun";
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
        System.out.println("\nSecurity guide:");
        System.out.println("- Puzzle ciphers: ROT13, ROT47, Atbash, Caesar, Morse, A1Z26, Reverse.");
        System.out.println("- Encoding tools: Base64, Hex, Binary. Useful, but not private.");
        System.out.println("- Hash tools: SHA-256 and SHA-512 create fingerprints. They are one-way, not encryption.");
        System.out.println("- HMAC: proves message integrity when both sides share a secret key.");
        System.out.println("- Password tools: useful now for generating stronger local passwords and passphrases.");
        System.out.println("- Real encryption: coming next. It should use authenticated encryption like AES-GCM.\n");
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
        System.out.println("\n----------------------------------------\n");
    }

    public static String encryptRot13(String s) { return CipherKit.rot13(s); }
    public static String decryptRot13(String s) { return CipherKit.rot13(s); }
    public static String encryptAtbash(String s) { return CipherKit.atbash(s); }
    public static String decryptAtbash(String s) { return CipherKit.atbash(s); }
    public static String encryptCaesar(String s) { return CipherKit.caesar(s, 3); }
    public static String decryptCaesar(String s) { return CipherKit.caesar(s, -3); }
}
