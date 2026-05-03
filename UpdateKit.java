package cyphertool;

public final class UpdateKit {
    public static final String TELEGRAM_CHANNEL = "https://t.me/cyphertool_updates";
    public static final String GITHUB_REPO = "https://github.com/imranshiundu/cypherTool";

    private UpdateKit() {}

    public static String updateMessage() {
        return "CypherTool update channels:\n" +
                "- GitHub releases/source: " + GITHUB_REPO + "\n" +
                "- Telegram announcements: " + TELEGRAM_CHANNEL + "\n\n" +
                "Security rule: CypherTool should never auto-run code from Telegram messages. " +
                "Telegram is for announcements and release notices. Install updates from the official GitHub repository only.";
    }
}
