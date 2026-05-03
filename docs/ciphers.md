# Cipher guide

This guide explains the methods in CypherTool in plain language.

## Encryption vs encoding vs obfuscation

Encryption protects information using a secret key. Without the right key, the message should remain unreadable.

Encoding changes data into another format so systems can store or transmit it safely. Base64 is encoding, not encryption.

Obfuscation makes something less obvious, but does not provide serious security. ROT13, Atbash, and Caesar are best treated as learning examples.

## ROT13

ROT13 shifts every letter by 13 places.

- A becomes N
- B becomes O
- N becomes A

Because the English alphabet has 26 letters, applying ROT13 twice returns the original message.

Weakness: there is no secret key. Anyone can reverse it instantly.

## Atbash

Atbash mirrors the alphabet.

- A becomes Z
- B becomes Y
- C becomes X

Weakness: it preserves too many patterns. Common letters and word shapes can still be studied.

## Caesar cipher

Caesar shifts letters by a chosen number.

Example with shift 3:

- A becomes D
- B becomes E
- Z becomes C

Weakness: there are only 25 meaningful shifts. A person or computer can try all of them quickly.

## ROT47

ROT47 rotates printable ASCII characters by 47 positions. It affects letters, numbers, and many symbols.

Weakness: it is still a fixed transformation. Anyone who knows ROT47 can reverse it.

## Vigenere cipher

Vigenere uses a keyword to choose different Caesar shifts across the message.

Example keyword: `LEMON`

Each keyword letter becomes a shift:

- L = 11
- E = 4
- M = 12
- O = 14
- N = 13

Weakness: when the keyword repeats, patterns can leak. Modern computers can break weak Vigenere messages, especially long ones.

## Base64

Base64 turns bytes into text using a safe alphabet. It is useful when binary data must pass through text-only systems.

Weakness: anyone can decode it. It has no password and no secrecy.

## AES-GCM direction

AES-GCM is the modern direction for the serious encryption side of CypherTool.

A proper AES-GCM implementation should use:

- a strong key or a strong password-derived key
- random salt when deriving keys from passwords
- random IV/nonce for each encryption
- authentication tag verification during decryption
- clear error handling when ciphertext is modified or the wrong password is used

The salt and IV do not need to be secret. They need to be unique and random.

## What to use when

Use classical ciphers when teaching, playing, or demonstrating how substitution works.

Use Base64 when you need text-safe encoding.

Use AES-GCM or another reviewed modern authenticated encryption scheme when you need actual encryption.

For production systems, use reviewed libraries, tested protocols, and proper key management instead of inventing your own cryptography.
