package cyphertool;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public final class CipherKit {
    private static final Map<Character, String> MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_BACK = new HashMap<>();

    static {
        addMorse('A', ".-"); addMorse('B', "-..."); addMorse('C', "-.-."); addMorse('D', "-..");
        addMorse('E', "."); addMorse('F', "..-."); addMorse('G', "--."); addMorse('H', "....");
        addMorse('I', ".."); addMorse('J', ".---"); addMorse('K', "-.-"); addMorse('L', ".-..");
        addMorse('M', "--"); addMorse('N', "-."); addMorse('O', "---"); addMorse('P', ".--.");
        addMorse('Q', "--.-"); addMorse('R', ".-."); addMorse('S', "..."); addMorse('T', "-");
        addMorse('U', "..-"); addMorse('V', "...-"); addMorse('W', ".--"); addMorse('X', "-..-");
        addMorse('Y', "-.--"); addMorse('Z', "--..");
        addMorse('0', "-----"); addMorse('1', ".----"); addMorse('2', "..---"); addMorse('3', "...--");
        addMorse('4', "....-"); addMorse('5', "....."); addMorse('6', "-...."); addMorse('7', "--...");
        addMorse('8', "---.."); addMorse('9', "----.");
    }

    private CipherKit() {}

    private static void addMorse(char letter, String code) {
        MORSE.put(letter, code);
        MORSE_BACK.put(code, letter);
    }

    public static String rot13(String input) {
        return caesar(input, 13);
    }

    public static String rot47(String input) {
        StringBuilder out = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= 33 && c <= 126) out.append((char) (33 + ((c - 33 + 47) % 94)));
            else out.append(c);
        }
        return out.toString();
    }

    public static String atbash(String input) {
        StringBuilder out = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') out.append((char) ('z' - (c - 'a')));
            else if (c >= 'A' && c <= 'Z') out.append((char) ('Z' - (c - 'A')));
            else out.append(c);
        }
        return out.toString();
    }

    public static String caesar(String input, int shift) {
        int normalized = ((shift % 26) + 26) % 26;
        StringBuilder out = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') out.append((char) (((c - 'a' + normalized) % 26) + 'a'));
            else if (c >= 'A' && c <= 'Z') out.append((char) (((c - 'A' + normalized) % 26) + 'A'));
            else out.append(c);
        }
        return out.toString();
    }

    public static String caesarBruteForce(String input) {
        StringBuilder out = new StringBuilder();
        for (int shift = 1; shift < 26; shift++) {
            out.append("Shift ").append(shift).append(": ").append(caesar(input, -shift)).append(System.lineSeparator());
        }
        return out.toString();
    }

    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String base64Decode(String input) {
        try {
            return new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException ex) {
            return "Invalid Base64 input.";
        }
    }

    public static String hexEncode(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        StringBuilder out = new StringBuilder();
        for (byte b : bytes) out.append(String.format("%02x", b));
        return out.toString();
    }

    public static String hexDecode(String input) {
        String clean = input.replaceAll("\\s+", "");
        if (clean.length() % 2 != 0 || !clean.matches("[0-9a-fA-F]*")) return "Invalid hex input.";
        byte[] bytes = new byte[clean.length() / 2];
        for (int i = 0; i < clean.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(clean.substring(i, i + 2), 16);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String binaryEncode(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        StringBuilder out = new StringBuilder();
        for (byte b : bytes) out.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0')).append(' ');
        return out.toString().trim();
    }

    public static String binaryDecode(String input) {
        String clean = input.trim();
        if (clean.isEmpty()) return "Invalid binary input.";
        String[] parts = clean.split("\\s+");
        byte[] bytes = new byte[parts.length];
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].matches("[01]{8}")) return "Invalid binary input. Use 8-bit groups separated by spaces.";
            bytes[i] = (byte) Integer.parseInt(parts[i], 2);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String a1z26Encode(String input) {
        StringBuilder out = new StringBuilder();
        for (char c : input.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') out.append(c - 'A' + 1).append(' ');
            else if (c == ' ') out.append("/ ");
            else out.append(c).append(' ');
        }
        return out.toString().trim();
    }

    public static String morseEncode(String input) {
        StringBuilder out = new StringBuilder();
        for (char c : input.toUpperCase().toCharArray()) {
            if (c == ' ') out.append("/ ");
            else if (MORSE.containsKey(c)) out.append(MORSE.get(c)).append(' ');
            else out.append(c).append(' ');
        }
        return out.toString().trim();
    }

    public static String morseDecode(String input) {
        StringBuilder out = new StringBuilder();
        for (String token : input.trim().split("\\s+")) {
            if (token.equals("/")) out.append(' ');
            else if (MORSE_BACK.containsKey(token)) out.append(MORSE_BACK.get(token));
            else out.append('?');
        }
        return out.toString();
    }
}
