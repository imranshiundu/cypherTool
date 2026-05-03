#!/usr/bin/env bash
set -euo pipefail

APP_NAME="cyphertool"
INSTALL_DIR="${HOME}/.local/share/cyphertool"
BIN_DIR="${HOME}/.local/bin"
REPO_URL="https://github.com/imranshiundu/cypherTool.git"

printf '\n== CypherTool Installer ==\n'

if ! command -v javac >/dev/null 2>&1; then
  echo "Java compiler not found. Install JDK 11 or newer first."
  echo "Ubuntu/Debian: sudo apt install openjdk-17-jdk"
  exit 1
fi

mkdir -p "$INSTALL_DIR" "$BIN_DIR"

if [ -d "$INSTALL_DIR/.git" ]; then
  echo "Updating existing installation..."
  git -C "$INSTALL_DIR" pull --ff-only
else
  echo "Installing CypherTool into $INSTALL_DIR ..."
  git clone "$REPO_URL" "$INSTALL_DIR"
fi

cd "$INSTALL_DIR"
mkdir -p out
javac -d out Main.java CypherTool.java CipherKit.java HashKit.java PasswordKit.java SecureCryptoKit.java TerminalUI.java UpdateKit.java

cat > "$BIN_DIR/$APP_NAME" <<'RUNNER'
#!/usr/bin/env bash
set -euo pipefail
APP_DIR="${HOME}/.local/share/cyphertool"
cd "$APP_DIR"
java -cp out cyphertool.Main
RUNNER

chmod +x "$BIN_DIR/$APP_NAME"

echo "Installed. Run CypherTool with: $APP_NAME"

case ":$PATH:" in
  *":$BIN_DIR:"*) ;;
  *) echo "Note: $BIN_DIR is not in your PATH. Add this to your shell profile:"; echo "export PATH=\"$BIN_DIR:\$PATH\"" ;;
esac
