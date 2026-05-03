# CypherTool roadmap

This roadmap turns CypherTool into a bigger, fun, useful cryptography playground.

## Version 0.1 - Current baseline

Status: done

- Java console app
- ROT13
- Atbash
- Caesar shift by 3
- Basic encrypt/decrypt menu

## Version 0.2 - Playground expansion

Goal: make the console version feel bigger immediately.

Methods to add:

- ROT47
- custom Caesar shift
- Caesar brute-force viewer
- Base64 encode/decode
- Morse code
- A1Z26
- Reverse text
- Hex encode/decode
- Binary encode/decode

UX upgrades:

- clearer menu
- security tier shown beside every method
- method explanation before running
- copy-friendly output
- invalid-input recovery

## Version 0.3 - Classical cipher lab

Goal: make it a real learning tool.

Methods to add:

- Vigenere
- Beaufort
- Affine
- Rail Fence
- Columnar Transposition
- Playfair
- Bacon's cipher

Features:

- show how the cipher works
- show key requirements
- show known weaknesses
- add examples

## Version 0.4 - Utility toolkit

Goal: make it useful for developers and everyday users.

Tools to add:

- URL encode/decode
- HTML entity encode/decode
- ASCII code viewer
- SHA-256
- SHA-512
- HMAC-SHA256
- random password generator
- passphrase generator
- password strength estimator

## Version 0.5 - Secure encryption mode

Goal: add serious private text and file encryption.

Features:

- AES-GCM text encryption
- AES-GCM text decryption
- AES-GCM file encryption
- AES-GCM file decryption
- secure random salt and IV generation
- password-based key derivation
- authentication failure warning
- password-loss warning

Important: this phase must be tested carefully. Do not merge unsafe crypto code casually.

## Version 0.6 - Challenge mode

Goal: make CypherTool fun.

Features:

- generate puzzles
- decode challenges
- hints
- scoring
- beginner, medium, hard levels
- daily puzzle
- cipher identification challenge

## Version 0.7 - Better interface

Options:

- richer terminal UI
- JavaFX desktop app
- simple web version
- packaged release files

## Version 1.0 - Stable release

A stable release should include:

- 20+ methods
- tests for every reversible method
- documented security tiers
- no `.class` files committed
- clean project structure
- beginner-friendly docs
- safe error messages
- secure mode tested with wrong-password and tampered-data cases
