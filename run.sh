#!/usr/bin/env bash
set -euo pipefail

mkdir -p out
javac -d out Main.java CypherTool.java CipherKit.java HashKit.java PasswordKit.java SecureCryptoKit.java TerminalUI.java UpdateKit.java
java -cp out cyphertool.Main
