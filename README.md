# CypherTool

CypherTool is growing from a simple Java cipher exercise into a playful cryptography playground and a useful offline privacy toolkit.

It should help people do three things:

1. **Play** with secret messages, puzzles, and classic ciphers.
2. **Learn** how encryption, encoding, hashing, and ciphers differ.
3. **Use** practical tools such as encoders, hashes, password generators, and modern authenticated encryption.

> Hide messages for fun. Protect files for real. Learn the difference.

## Current version

The current Java console app now has five modes:

- **Playground** - fun ciphers and hidden-message tools.
- **Utility** - practical encoders, decoders, hashes, and HMAC.
- **Challenge** - a small puzzle mode.
- **Passwords** - local password generator, passphrase generator, and strength checker.
- **Security guide** - plain-English guidance on what is safe and what is only play.

Implemented methods and tools:

| Method / Tool | Category | Secure for real secrets? | Status |
|---|---:|---:|---|
| ROT13 | Classical substitution | No | Implemented |
| ROT47 | Classical rotation | No | Implemented |
| Atbash | Classical substitution | No | Implemented |
| Caesar custom shift | Classical substitution | No | Implemented |
| Caesar brute-force viewer | Learning tool | No | Implemented |
| Morse encode/decode | Encoding/play | No | Implemented |
| A1Z26 | Puzzle cipher | No | Implemented |
| Reverse text | Puzzle tool | No | Implemented |
| Base64 encode/decode | Encoding | No | Implemented |
| Hex encode/decode | Encoding | No | Implemented |
| Binary encode/decode | Encoding | No | Implemented |
| SHA-256 | Hash/fingerprint | One-way, not encryption | Implemented |
| SHA-512 | Hash/fingerprint | One-way, not encryption | Implemented |
| HMAC-SHA256 | Message authentication | Useful with shared secret | Implemented |
| Random password generator | Password utility | Useful | Implemented |
| Passphrase generator | Password utility | Useful | Implemented |
| Password strength checker | Password utility | Estimate only | Implemented |

The source is now split between:

- `Main.java` - entry point
- `CypherTool.java` - console app flow
- `CipherKit.java` - reusable cipher and encoding methods
- `HashKit.java` - SHA-256, SHA-512, and HMAC-SHA256
- `PasswordKit.java` - password generation, passphrase generation, and strength reports

## Why people would use this

CypherTool should not only be a classroom exercise. The useful side is:

- quickly encode/decode Base64, Hex, and Binary text offline
- generate hashes for text fingerprints and checksums
- create HMAC signatures for simple integrity checks
- generate strong passwords without visiting a website
- generate memorable passphrases
- check weak passwords locally without sending them anywhere
- learn why toy ciphers are weak by actually using and breaking them

## Upgrade target

The larger version should support at least 20 methods across fun ciphers, encoders, hashes, password tools, and serious encryption.

### Classical and playful ciphers

These are for learning and puzzles, not real secrecy.

- ROT13
- ROT47
- Atbash
- Caesar with custom shift
- Caesar brute-force viewer
- Vigenere
- Beaufort
- Affine
- Rail Fence
- Columnar Transposition
- Playfair
- Bacon's cipher
- Morse code
- A1Z26 letter-number cipher
- Reverse text
- Emoji substitution mode

### Encoding and utility tools

These are useful, but not encryption.

- Base64 encode/decode
- URL encode/decode
- HTML entity encode/decode
- Hex encode/decode
- Binary encode/decode
- ASCII code view
- SHA-256 hash
- SHA-512 hash
- HMAC-SHA256
- Password/passphrase generator
- Password strength estimator

### Serious encryption tools

These are for real private content when implemented correctly.

- AES-GCM text encryption
- AES-GCM file encryption
- ChaCha20-Poly1305 text encryption
- ChaCha20-Poly1305 file encryption
- Password-based key derivation
- Secure random key generator
- Encrypted note vault
- Encrypted file package format

## Security tiers

Every method should show a security tier so users understand what they are using.

| Tier | Label | Meaning |
|---|---|---|
| Tier 0 | Puzzle only | Fun transformation, not private |
| Tier 1 | Encoding only | Useful format conversion, not private |
| Tier 2 | Fingerprint | Hash/checksum; one-way, not encryption |
| Tier 3 | Real encryption | Authenticated encryption for private content |

## Why this matters

Classical ciphers are useful for learning, but weak for secrecy.

ROT13 has no secret key. Atbash has no secret key. Caesar has only 25 useful shifts. Base64 is not encryption at all. Anyone can decode it.

Modern encryption is different. Real encryption should use reviewed algorithms, random values, proper key derivation, and tamper detection. That is why AES-GCM and ChaCha20-Poly1305 belong in the serious side of CypherTool, while ROT13 and Playfair belong in the playground side.

## Product modes

### Playground mode

A fun place to transform messages and learn how each cipher works.

### Challenge mode

A puzzle mode where users decode messages, get hints, and build scores.

### Utility mode

A practical mode for Base64, Hex, Binary, hashes, HMAC, future URL encoding, and text conversions.

### Password mode

A local mode for generating strong passwords, memorable passphrases, and checking password strength.

### Secure mode

A serious mode for private notes and file encryption, with clear warnings and safe defaults. This is planned next.

## Requirements

- Java 11 or newer
- Terminal or command prompt

## Run

From the repository root:

```bash
javac -d out Main.java CypherTool.java CipherKit.java HashKit.java PasswordKit.java
java -cp out cyphertool.Main
```

Type `exit` at the prompts to close the program.

## Documentation

- [`docs/ciphers.md`](docs/ciphers.md) explains the cipher methods in plain English.
- [`docs/upgrade-blueprint.md`](docs/upgrade-blueprint.md) describes the larger product direction, 20+ method target, architecture, and implementation phases.
- [`SECURITY.md`](SECURITY.md) explains safe-use expectations.

## Responsible use

Use CypherTool for learning, demonstrations, puzzles, development utilities, and protecting your own data. Do not use it to hide harmful activity or handle other people's private information without permission.

## Project rule

CypherTool must be fun, but never dishonest. If a method is a toy cipher, the tool should say so. If a method is for real encryption, the implementation must be careful, standard, and tested.
