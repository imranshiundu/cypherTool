# Implementation tasks

This file breaks the upgrade into buildable tasks.

## Task 1 - Refactor the app shell

Create a cleaner structure:

```text
src/cyphertool/Main.java
src/cyphertool/app/CypherApp.java
src/cyphertool/app/MenuRenderer.java
src/cyphertool/app/InputReader.java
src/cyphertool/core/CipherMethod.java
src/cyphertool/core/SecurityTier.java
src/cyphertool/core/MethodRegistry.java
```

Acceptance criteria:

- app still runs from the terminal
- old ROT13, Atbash, and Caesar behavior still works
- menu is easier to extend
- each method has name, category, tier, description, encrypt/decrypt support

## Task 2 - Add first expansion methods

Add:

- ROT47
- custom Caesar shift
- Caesar brute-force viewer
- Base64 encode/decode
- Reverse text
- A1Z26
- Morse code

Acceptance criteria:

- each reversible method has encrypt and decrypt
- each method has a short explanation
- invalid input does not crash the app

## Task 3 - Add data conversion utilities

Add:

- Hex encode/decode
- Binary encode/decode
- ASCII code viewer
- URL encode/decode
- HTML entity encode/decode

Acceptance criteria:

- UTF-8 text handled correctly where applicable
- invalid input produces useful messages

## Task 4 - Add hash and password tools

Add:

- SHA-256
- SHA-512
- HMAC-SHA256
- random password generator
- passphrase generator
- password strength estimator

Acceptance criteria:

- hash tools are clearly marked as one-way
- HMAC requires a secret key
- generated passwords allow length/options

## Task 5 - Add advanced classical ciphers

Add:

- Vigenere
- Beaufort
- Affine
- Rail Fence
- Columnar Transposition
- Playfair
- Bacon's cipher

Acceptance criteria:

- each method includes key validation
- each method includes a weakness explanation
- tests prove encrypt/decrypt round trips where possible

## Task 6 - Add secure encryption mode

Add:

- AES-GCM text encryption
- AES-GCM text decryption
- AES-GCM file encryption
- AES-GCM file decryption
- secure package format

Acceptance criteria:

- random salt per encryption
- random IV per encryption
- password-derived key
- authentication tag verification
- wrong password fails safely
- modified ciphertext fails safely
- plaintext is not written to logs

## Task 7 - Add challenge mode

Add:

- generated puzzles
- hints
- score
- difficulty levels
- solution reveal
- cipher identification challenge

Acceptance criteria:

- at least 20 playable challenges
- no challenge requires internet
- beginner challenges explain the answer after completion

## Task 8 - Add tests

Test categories:

- round-trip tests for reversible ciphers
- known-output tests for classical ciphers
- invalid-input tests
- wrong-password secure mode tests
- tampered-ciphertext secure mode tests

## Task 9 - Remove compiled files

The repo currently contains `.class` files. They should be removed after the source structure is stable because compiled build artifacts do not belong in Git.

Acceptance criteria:

- `.class` files removed
- `.gitignore` prevents them from returning
- README run instructions still work
