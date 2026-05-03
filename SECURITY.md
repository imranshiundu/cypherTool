# Security notes

CypherTool contains educational ciphers. Treat the classical methods as learning tools, not secure protection.

## Safe expectations

ROT13, Atbash, Caesar, ROT47, Vigenere, and Base64 should not be used for real secrets. They are included because they help learners understand how text transformations work.

A modern encryption method such as AES-GCM is the correct direction for real encryption practice, but even modern encryption depends on strong passwords, safe implementation, and careful handling of decrypted data.

## Password guidance

Use long passwords or passphrases. A strong passphrase is usually easier to remember and harder to guess than a short complex-looking password.

Example pattern:

```text
river-market-iron-sky-2026
```

Avoid passwords based on your name, birthday, phone number, school, favorite team, or common words alone.

## Reporting problems

Open a GitHub issue with:

- what failed
- Java version
- operating system
- exact steps to reproduce
- expected result
- actual result

Do not post real passwords, private keys, or sensitive plaintext in issues.
