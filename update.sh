#!/usr/bin/env bash
set -euo pipefail

printf '\n== CypherTool Safe Updater ==\n'

if [ ! -d .git ]; then
  echo "Run this from a CypherTool git checkout."
  exit 1
fi

git fetch origin main
LOCAL=$(git rev-parse HEAD)
REMOTE=$(git rev-parse origin/main)

if [ "$LOCAL" = "$REMOTE" ]; then
  echo "CypherTool is already up to date."
else
  echo "Updates available from GitHub. Applying fast-forward update..."
  git pull --ff-only origin main
fi

mkdir -p out
javac -d out Main.java CypherTool.java CipherKit.java HashKit.java PasswordKit.java SecureCryptoKit.java TerminalUI.java UpdateKit.java
echo "Update complete. Run ./run.sh or java -cp out cyphertool.Main"
