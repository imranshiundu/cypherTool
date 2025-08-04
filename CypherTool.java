package cyphertool;

import java.util.Scanner;

public class CypherTool {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startProgram() {
        System.out.println("Welcome to Our Simple Cypher Tool. It was made by Imran Shiundu, Valarie Kigen & Dennis Kiarie. We hope you enjoy using it!");
        System.out.println("Type 'exit' whenever you want to quit.\n");

        while (true) {
            String operation = chooseOperation();
            if (operation.equals("exit")) return;

            String cipherType = chooseCipher();
            if (cipherType.equals("exit")) return;

            String message = getMessage();
            if (message.equals("exit")) return;

            processMessage(operation, cipherType, message);
        }
    }

    private static String chooseOperation() {
        while (true) {
            System.out.println("Choose operation:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.print("Your choice (1 or 2): ");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                return "exit";
            }

            switch (input) {
                case "1": return "encrypt";
                case "2": return "decrypt";
                default: System.out.println("Invalid choice. Please enter 1 or 2.\n");
            }
        }
    }

    private static String chooseCipher() {
        while (true) {
            System.out.println("\nChoose cipher:");
            System.out.println("1. ROT13");
            System.out.println("2. Atbash");
            System.out.println("3. Caesar (shift by 3)");
            System.out.print("Your choice (1, 2 or 3): ");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                return "exit";
            }

            switch (input) {
                case "1": return "rot13";
                case "2": return "atbash";
                case "3": return "caesar";
                default: System.out.println("Invalid choice. Please enter 1, 2 or 3.");
            }
        }
    }

    private static String getMessage() {
        while (true) {
            System.out.println("\nEnter your message:");
            System.out.print("> ");
            String message = scanner.nextLine().trim();

            if (message.equalsIgnoreCase("exit")) {
                return "exit";
            }

            if (!message.isEmpty()) {
                return message;
            }

            System.out.println("Message cannot be empty. Please try again.");
        }
    }

    private static void processMessage(String operation, String cipherType, String message) {
        String result;
        String action = operation.equals("encrypt") ? "Encrypted" : "Decrypted";

        switch (cipherType) {
            case "rot13":
                result = operation.equals("encrypt") ? encryptRot13(message) : decryptRot13(message);
                System.out.println("\n" + action + " message (ROT13):");
                break;
            case "atbash":
                result = operation.equals("encrypt") ? encryptAtbash(message) : decryptAtbash(message);
                System.out.println("\n" + action + " message (Atbash):");
                break;
            case "caesar":
                result = operation.equals("encrypt") ? encryptCaesar(message) : decryptCaesar(message);
                System.out.println("\n" + action + " message (Caesar):");
                break;
            default:
                result = "Error: Unknown cipher";
        }

        System.out.println(result + "\n");
        System.out.println("----------------------------------------\n");
    }


    public static String encryptRot13(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char shifted = (char) (((c - 'a' + 13) % 26) + 'a');
                result.append(shifted);
            } else if (c >= 'A' && c <= 'Z') {
                char shifted = (char) (((c - 'A' + 13) % 26) + 'A');
                result.append(shifted);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decryptRot13(String s) {
        return encryptRot13(s);
    }


    public static String encryptAtbash(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char flipped = (char) ('z' - (c - 'a'));
                result.append(flipped);
            } else if (c >= 'A' && c <= 'Z') {
                char flipped = (char) ('Z' - (c - 'A'));
                result.append(flipped);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decryptAtbash(String s) {
        return encryptAtbash(s);
    }


    public static String encryptCaesar(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char shifted = (char) (((c - 'a' + 3) % 26) + 'a');
                result.append(shifted);
            } else if (c >= 'A' && c <= 'Z') {
                char shifted = (char) (((c - 'A' + 3) % 26) + 'A');
                result.append(shifted);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decryptCaesar(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char shifted = (char) (((c - 'a' + 23) % 26) + 'a');
                result.append(shifted);
            } else if (c >= 'A' && c <= 'Z') {
                char shifted = (char) (((c - 'A' + 23) % 26) + 'A');
                result.append(shifted);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}