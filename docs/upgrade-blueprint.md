# CypherTool upgrade blueprint

CypherTool should become more than a class exercise. The target is a playful cryptography playground and a genuinely useful privacy utility.

The product must clearly separate three worlds:

1. **Fun ciphers** - puzzles, learning, games, challenges, and hidden messages.
2. **Encoders and hashes** - useful data conversion and fingerprinting tools.
3. **Real encryption** - modern authenticated encryption for actual private notes and files.

No screen should pretend that toy ciphers are secure. The tool should teach people while still giving them practical features.

## Product personality

CypherTool should feel like a small hacker-lab, puzzle box, and safety tool in one.

Tone:

- clear
- playful
- not childish
- beginner-friendly
- honest about security
- useful enough for daily use

Possible tagline:

> Hide messages for fun. Protect files for real. Learn the difference.

## The 20+ method target

### A. Classical and playful ciphers

These are for fun, puzzles, teaching, and challenges.

1. ROT13
2. ROT47
3. Atbash
4. Caesar with custom shift
5. Caesar brute-force viewer
6. Vigenere
7. Beaufort cipher
8. Affine cipher
9. Rail Fence cipher
10. Columnar Transposition
11. Playfair cipher
12. Bacon's cipher
13. Morse code
14. A1Z26 letter-number cipher
15. Binary text encoding
16. Hex text encoding
17. ASCII code view
18. Emoji substitution mode
19. Reverse text
20. Word shuffle / hidden-message puzzle mode

### B. Encoding and checksum tools

These are not encryption, but they are useful in development and data handling.

21. Base64 encode/decode
22. URL encode/decode
23. HTML entity encode/decode
24. Hex encode/decode
25. Binary encode/decode
26. SHA-256 hash
27. SHA-512 hash
28. HMAC-SHA256
29. Password strength estimate
30. Random password/passphrase generator

### C. Serious encryption tools

These are the practical tools. They must be implemented carefully and explained honestly.

31. AES-GCM text encryption
32. AES-GCM file encryption
33. ChaCha20-Poly1305 text encryption
34. ChaCha20-Poly1305 file encryption
35. PBKDF2 password-based key derivation
36. Argon2id support if the project later allows external libraries
37. Secure random key generator
38. Encrypted note vault
39. Encrypted file package format
40. Recovery warning and password-loss warning system

## Security tiers

Every method should display a visible tier.

| Tier | Label | Meaning |
|---|---|---|
| Tier 0 | Puzzle only | Fun transformation, not private |
| Tier 1 | Encoding only | Useful format conversion, not private |
| Tier 2 | Fingerprint | Hash/checksum; one-way, not encryption |
| Tier 3 | Real encryption | Authenticated encryption for private content |

## Modes

### 1. Playground mode

For learners and casual users.

Features:

- choose a cipher
- type a message
- see transformed output instantly
- explain what changed
- show whether the method is reversible
- show security tier
- copy output

### 2. Challenge mode

For fun and learning.

Features:

- generate a secret message
- ask user to decode it
- hints after failed attempts
- score system
- beginner, medium, hard levels
- daily puzzle

### 3. Utility mode

For real usefulness.

Features:

- Base64, URL, HTML, Hex, Binary conversions
- hash generator
- password generator
- password strength feedback
- copy-to-clipboard friendly output

### 4. Secure mode

For actual private text and file protection.

Features:

- AES-GCM encrypt/decrypt text
- AES-GCM encrypt/decrypt files
- ChaCha20-Poly1305 option where supported
- random salt and IV per encryption
- password-derived keys
- authentication failure warning
- clear warning that lost passwords cannot be recovered

## Recommended architecture

The current project keeps all logic in `CypherTool.java`. The larger version should split the code.

```text
src/cyphertool/
├── Main.java
├── app/
│   ├── CypherApp.java
│   ├── MenuRenderer.java
│   └── InputReader.java
├── core/
│   ├── CipherMethod.java
│   ├── CipherResult.java
│   ├── SecurityTier.java
│   └── MethodRegistry.java
├── classical/
│   ├── Rot13Cipher.java
│   ├── Rot47Cipher.java
│   ├── AtbashCipher.java
│   ├── CaesarCipher.java
│   ├── VigenereCipher.java
│   ├── PlayfairCipher.java
│   └── RailFenceCipher.java
├── encoding/
│   ├── Base64Tool.java
│   ├── UrlCodec.java
│   ├── HexCodec.java
│   └── BinaryCodec.java
├── crypto/
│   ├── AesGcmTool.java
│   ├── ChaCha20Poly1305Tool.java
│   ├── KeyDerivation.java
│   └── SecureRandomTools.java
└── utility/
    ├── PasswordGenerator.java
    ├── PasswordStrength.java
    └── TextTools.java
```

## Implementation phases

### Phase 1 - Clean console playground

- refactor menu code
- add method registry
- add security tier labels
- keep existing ROT13, Atbash, Caesar working
- add ROT47, custom Caesar, Base64, Morse, Hex, Binary, Reverse text

### Phase 2 - More classical ciphers

- Vigenere
- Affine
- Rail Fence
- Columnar Transposition
- Playfair
- Bacon's cipher
- Caesar brute-force viewer

### Phase 3 - Utility tools

- SHA-256
- SHA-512
- HMAC-SHA256
- password generator
- password strength estimate
- URL encode/decode
- HTML entity encode/decode

### Phase 4 - Serious encryption

- AES-GCM text encryption
- AES-GCM file encryption
- secure package format
- password-based key derivation
- clear error messages for wrong password or modified data

### Phase 5 - Playful polish

- challenge mode
- levels
- hints
- score
- daily puzzle
- themed messages
- copy-friendly output

### Phase 6 - Optional UI

- JavaFX desktop app, or
- simple web version, or
- terminal UI with colored menus

## The useful features people will actually return for

- encrypt a private note
- encrypt a small file
- generate a strong password
- hash text for checksums
- encode/decode Base64 and URL strings
- teach beginners how ciphers work
- play puzzle challenges
- brute-force Caesar messages for learning

## Rules for the project

1. Never label classical ciphers as secure.
2. Never store user passwords.
3. Never reuse IVs/nonces in real encryption.
4. Never invent a custom modern encryption algorithm.
5. Use standard Java cryptography APIs for serious encryption.
6. Keep fun features separated from secure features.
7. Make every method explain itself before output.
8. Keep the tool offline-first.
9. Keep the interface simple enough for beginners.
10. Make the code modular enough for contributors.
