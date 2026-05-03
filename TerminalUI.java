package cyphertool;

public final class TerminalUI {
    private TerminalUI() {}

    public static void banner() {
        line();
        System.out.println("   ______            __              ______            __");
        System.out.println("  / ____/_  ______  / /_  ___  _____/_  __/___  ____  / /");
        System.out.println(" / /   / / / / __ \/ __ \/ _ \/ ___/ / / / __ \/ __ \/ / ");
        System.out.println("/ /___/ /_/ / /_/ / / / /  __/ /    / / / /_/ / /_/ / /  ");
        System.out.println("\____/\__, / .___/_/ /_/\___/_/    /_/  \____/\____/_/   ");
        System.out.println("     /____/_/                                               ");
        System.out.println("            OFFLINE CRYPTO PLAYGROUND + UTILITY KIT");
        line();
        System.out.println(" Hide messages for fun. Protect files for real. Learn the difference.");
        System.out.println(" No accounts. No tracking. No cloud required. Local-first by design.");
        line();
    }

    public static void card(String number, String title, String tag, String description) {
        System.out.printf("  [%s] %-18s %-24s %s%n", number, title, tag, description);
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("-- " + title + " --");
    }

    public static void line() {
        System.out.println("============================================================");
    }

    public static void thinLine() {
        System.out.println("------------------------------------------------------------");
    }
}
