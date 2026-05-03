package cyphertool;

import java.security.SecureRandom;
import java.util.Locale;

public final class PasswordKit {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.?/";
    private static final String[] WORDS = {
            "river", "market", "orbit", "stone", "signal", "forest", "silver", "anchor",
            "velvet", "rocket", "planet", "garden", "mirror", "ember", "cloud", "bridge",
            "falcon", "summit", "ocean", "copper", "lantern", "safari", "violet", "harbor"
    };

    private PasswordKit() {}

    public static String generatePassword(int length, boolean symbols) {
        if (length < 8) length = 8;
        String alphabet = LOWER + UPPER + DIGITS + (symbols ? SYMBOLS : "");
        StringBuilder out = new StringBuilder();

        out.append(randomChar(LOWER));
        out.append(randomChar(UPPER));
        out.append(randomChar(DIGITS));
        if (symbols) out.append(randomChar(SYMBOLS));

        while (out.length() < length) out.append(randomChar(alphabet));
        return shuffle(out.toString());
    }

    public static String generatePassphrase(int words) {
        if (words < 4) words = 4;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < words; i++) {
            if (i > 0) out.append('-');
            out.append(WORDS[RANDOM.nextInt(WORDS.length)]);
        }
        out.append('-').append(100 + RANDOM.nextInt(900));
        return out.toString();
    }

    public static String strengthReport(String password) {
        if (password == null || password.isEmpty()) return "Password is empty.";
        int score = 0;
        StringBuilder advice = new StringBuilder();

        if (password.length() >= 12) score += 25; else advice.append("- Use at least 12 characters.\n");
        if (password.length() >= 16) score += 15;
        if (password.matches(".*[a-z].*")) score += 10; else advice.append("- Add lowercase letters.\n");
        if (password.matches(".*[A-Z].*")) score += 10; else advice.append("- Add uppercase letters.\n");
        if (password.matches(".*[0-9].*")) score += 10; else advice.append("- Add numbers.\n");
        if (password.matches(".*[^A-Za-z0-9].*")) score += 15; else advice.append("- Add symbols.\n");
        if (!hasObviousPattern(password)) score += 15; else advice.append("- Avoid obvious patterns like 1234, password, qwerty, or repeated characters.\n");

        String label;
        if (score >= 85) label = "Strong";
        else if (score >= 65) label = "Good";
        else if (score >= 40) label = "Weak";
        else label = "Very weak";

        return "Score: " + Math.min(score, 100) + "/100\nRating: " + label + "\n" +
                (advice.length() == 0 ? "Advice: Looks good for general use. For important accounts, prefer a long unique password or passphrase." : "Advice:\n" + advice);
    }

    private static boolean hasObviousPattern(String password) {
        String p = password.toLowerCase(Locale.ROOT);
        return p.contains("password") || p.contains("qwerty") || p.contains("1234") ||
                p.contains("0000") || p.matches(".*(.)\\1{3,}.*");
    }

    private static char randomChar(String chars) {
        return chars.charAt(RANDOM.nextInt(chars.length()));
    }

    private static String shuffle(String input) {
        char[] chars = input.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
