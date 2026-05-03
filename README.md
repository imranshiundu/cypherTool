# CypherTool

CypherTool is a Java console toolkit for learning classical ciphers and understanding the difference between classical ciphers, encoding, and modern encryption.

It started as a simple ROT13, Atbash, and Caesar program. The upgrade goal is to make it clearer, safer, better documented, and easier for students or contributors to understand.

## Current methods

| Method | Category | Secure for real secrets? | What it teaches |
|---|---:|---:|---|
| ROT13 | Classical substitution | No | Letter rotation and reversible transformations |
| Atbash | Classical substitution | No | Alphabet mirroring |
| Caesar shift by 3 | Classical substitution | No | Shift ciphers and brute-force weakness |

## Planned/upgrade methods

| Method | Category | Secure for real secrets? | Notes |
|---|---:|---:|---|
| ROT47 | Classical rotation | No | Rotates printable ASCII characters |
| Custom Caesar | Classical substitution | No | Allows user-defined shift values |
| Vigenere | Classical polyalphabetic | No | Uses a keyword; historically stronger than Caesar but weak today |
| Base64 | Encoding | No | Encoding only; not encryption |
| AES-GCM | Modern authenticated encryption | Yes, when implemented correctly | Should use random IVs, salts, password-based key derivation, and authentication |

## Why the classical methods are weak

Classical ciphers are useful for learning, not for protecting private data.

Think of hiding a key under the third flower pot every day. That is a system, but once someone learns the pattern, the door is open. ROT13, Atbash, and Caesar work like that. They transform text, but the pattern is small and predictable.

ROT13 has no secret key. Atbash has no secret key. Caesar has only 25 useful shifts, so a computer or even a patient person can try every possible answer quickly.

Base64 is not encryption either. It is like putting a note inside a transparent envelope. It changes the form of the message so systems can carry it, but it does not hide the meaning.

Modern encryption is different. A safe design uses strong algorithms, random values, key derivation, and tamper detection. That is why AES-GCM is the right direction for the real encryption side of this project.

## Requirements

- Java 11 or newer
- Terminal or command prompt

No external libraries are required for the current version.

## Run

From the repository root:

```bash
javac -d out Main.java CypherTool.java
java -cp out cyphertool.Main
```

Type `exit` at the prompts to close the program.

## Existing project structure

```text
.
├── CypherTool.java   # Main cipher logic and console flow
├── Main.java         # Entry point
├── README.md         # Project documentation
└── docs/
    └── ciphers.md    # Deeper explanation of supported and planned ciphers
```

## Responsible use

Use this tool for learning, demonstrations, and protecting your own data only. Do not use it to hide harmful activity or handle other people's secrets without permission.
