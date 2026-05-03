# Secure Mode

Secure Mode is the serious side of CypherTool.

It is different from the playground ciphers. ROT13, Atbash, Caesar, Morse, A1Z26, and similar methods are useful for learning and puzzles. Secure Mode is for private text encryption using modern authenticated cryptography.

## Current secure method

CypherTool currently implements password-based AES-GCM text encryption.

Profile:

```text
Algorithm: AES/GCM/NoPadding
Key size: 256-bit derived key
KDF: PBKDF2WithHmacSHA256
Iterations: 210,000
Salt: 16 random bytes per encryption
IV/nonce: 12 random bytes per encryption
Authentication tag: 128-bit
Package format: version + salt + iv + ciphertext/auth tag
Output format: Base64
```

## What AES-GCM gives you

AES-GCM gives confidentiality and integrity.

Confidentiality means the plaintext is hidden from someone who does not have the password.

Integrity means the encrypted package cannot be silently changed. If someone edits the encrypted data, decryption should fail.

This is why AES-GCM is better than simply encrypting data without authentication.

## What the password does

The user enters a password. CypherTool does not use the password directly as an AES key.

Instead, it uses PBKDF2WithHmacSHA256 with a random salt and 210,000 iterations to derive a 256-bit AES key.

The salt is stored inside the encrypted package. The salt does not need to be secret. Its job is to make repeated password attacks harder and ensure the same password does not always derive the same key package.

## What the IV does

AES-GCM needs a unique IV/nonce for each encryption.

CypherTool generates 12 random IV bytes for every encryption and stores them in the package.

Never reuse the same AES-GCM key and IV pair for different messages.

## Package format

The encrypted output is Base64 text containing:

```text
1 byte version
16 bytes salt
12 bytes IV
ciphertext + GCM authentication tag
```

The output can be copied and saved as text.

## Security limits

CypherTool cannot recover lost passwords.

A weak password can still be guessed by an attacker. AES-GCM is strong, but the whole system is only as strong as the user's password and device security.

CypherTool does not yet provide:

- encrypted file mode
- encrypted note vault
- secure clipboard clearing
- signed releases
- formal external security audit
- hardware-backed keys

## Practical use

Good uses:

- encrypting private notes locally
- learning how authenticated encryption packages work
- testing wrong-password and tampered-data behavior
- comparing serious encryption against toy ciphers

Avoid using it for:

- storing national-security secrets
- production banking infrastructure
- other people's private data without permission
- high-risk enterprise systems without professional audit

## Planned next secure features

- AES-GCM file encryption
- ChaCha20-Poly1305 text encryption
- ChaCha20-Poly1305 file encryption
- encrypted notes folder
- tamper test mode
- file checksum verification
- signed release verification
