# 1 Intro

## Cryptography

- Cryptography: secure communication in the presence of adversaries

- Cryptoanalysis: attempts to break cryptographic systems

1. Adversaries:

     - Eavesdropper
     
     -  man in the middle ([MITM](https://www.imperva.com/learn/application-security/man-in-the-middle-attack-mitm/)): "broadly speaking, a MITM attack is the equivalent of a mailman opening your bank statement, writting down your account details, then resealing the envolope and delivering it to your door"
     
         ![man in the middle mitm attack](./assets/man-in-the-middle-mitm-attack.png)
     
     - assume adversaries have NSA(National Security Agency)-sized data centers

2. Secure communication requirements:
   - Confidentiality: only the intended recipient can read the message
   - Data integrity: the message received contains the information the sender intended 
   - Authentication: can verify the sender
   - non-repudiation: the assurance that someone cannot deny the validity of something. For example, if I receive a message from you, you cannot say this is not from you.

3. Cryptographic Components

   - plaintext: original message
   - cyphertext: encrypted message
   - Keys:
     - Shared secrete key (**symmetric cryptography**): K<sub>A</sub> == K<sub>B</sub> and are secret
     - Public key  (**asymmetric cryptography**): parties have a public key (everyone knows) and a secret key (only Alice and Bob knows)
     - Symmetric crypto is usually faster than asymmetric crypto, so in practice we often use a combination of both

   <img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230210093741719.png" alt="image-20230210093741719" style="zoom:50%;" />

## Cryptography Building Blocks

1. Requirements for builidng blocks:
   - deterministic: a plaintext can generate only one cyphertext
   - Reversible: we can decrypt cyphertext to the plaintext
   - Efficient to compute
2. Common building blocks:
   - substitution: replace each byte with a different byte
     - Caeser Cypher crypto system: each alpha letter are rotated by an offset, e.g., a -> d, b -> e, ...
       - for example, "DSZQUP SVMFT" -> "CRYPTO RULES", for which the key is the offset 1
       - symmetric algorithm:
         - Encrypt: CC(plaintext, key)
         - Decrypt: CC(cyphertext, -key)
   - permutation: shifting things around
     - pig latin: move the consonant clusters to the end and add "ay", e.g., "CRYPTO RULES" -> "YPTOCRAY ULESRAY"
   - XOR: bitwise exclusive or. XOR and plus are similar, but XOR is slightly nicer because plus need to deal with carrying / borrowing bits
     - important applications of XOR:
       - a ^ b ^ b = a
       - x ^ 0 = x
3. Downsides of the above building blocks:
   - Substitution: easy to decode. Since substitution is used for bytes, there are only 2<sup>8</sup> possible keys. We can use brute force or statistical analysis to find the key.
   - Permutations: [ref](https://en.wikibooks.org/wiki/Cryptography/Permutation_cipher)
     - the plaintext may have to be padded (if the padding is identifiable then part of the key is revealed)
     - information relating to the length of the key is revealed by the length of the ciphertext.
   - combine substitution + permutation can get better crypto system

## Types of Attack

A cryptosystem is secure if attacks can only use brute force to do key guessing.

1. Cyphertext Only: the attacker has access to only the intercepted cyphertext. The statistical analysis can help in a cypiertext-only attack.
2. Known Plaintext: the attacker knows some of the cyphertext & plaintext pairs and decode the key
3. Chosen Plaintext: the attacker can choose plaintexts and view their corresponding cyphertext. For example, the attacker might try encrpting messages `000000` and `000001`. If the output ctyphertexts are very similar, the attacker can know something about the key from the difference.
4. Cyphertext should:
   - appear random: avoid cyphertext-only and known-plaintext attacks
   - small changes to input should result in large changes to cyphertext ("Avalanche effect"): avoid chosen plaintext 
   - possible combining 3 different building blocks in various orders

# 2 Block Cypher - Symmetric 

A block cipher is an encryption algorithm that encrypts a **fixed size** of n-bits of data - known as a **block** - at one time. The usual sizes of each block are 64 bits, 128 bits, and 256 bits. So for example, a 64-bit block cipher will take in 64 bits of plaintext and encrypt it into 64 bits of ciphertext. 

- symmetric
- encrypts block-sized (fixed size) plaintext to a certain-byte cyphertext
- security depends on key size / block size. assuming n bit key, then there are 2<sup>n</sup> possibilities, attackers need to guess 2<sup>n-1</sup> times statistically. The bigger the block size is, the more possibilities there are.
- usually the cyphertext and plaintext are the same size
  - if cyphertext < plaintext, they it is not reversible
  - in theory cyphytext can be bigger than plaintext, but we don't see such algorithm

## DES (Data Encryption Standard)

Old, broken

64-bit block

Key size: 56 bits (too small for the modern computingd)

downsides: 

- desgined for hardware implementation, not software: bit permutation is easy in hardware but hard in software 
- 56 bits are too small for a key

## 3DES

do DES three times: E(D(E(m, k1), k2), k3)

- a = E(plaintext, K1)
- b = D(a, k2)
- c = E(b, k3)

## AES (Advanced Encryption Standard)

new, good

1. features:

   - 128 block size (variabale)

   - Key size: 128, 192, or 256. bigger key size -> more rounds

   - efficient in software

2. Operations: (all are reversible)

   - sub bytes: b[i] = table[b[i]]

   - Shift rows

   - mix columns (?)

     ```
     for each column:
     	for each row:
     		look up column for byte[row][col]
     		rotate that result
     	rotate all the oclumn entries together
     ```

   - Add rounkey key: xor with the round key

3. how to deal with long messages? stream cypher
   - Encryption: plaintext xor key
   - Decryption: cyphertext xor key
   - We need to get a good pseduo random number generator. repeated key string can cause compromised data

# 3 Stream Cypher

1. key stream: pseudo random

## CSPRNG

// review this part of video!!!

## RC4

Homework: implement RC4 algorithm!

RC - Ron's Code. this way is broken. don't use it!!

expected period = 10 ** 100

## ChaCha20

modern stream cypher

1. Confidential: canno't know the plaintext without knowing the key
2. Integrity: if the output is changed, can we detect it?

stream cypher vs. block cypher:

- if one bit is changed in block cypher, the plaintext decrypted will be huge different. So block cypher is closer to integerity than stream cypher. (???)
- stream cypher & bloc cypher are both confidential

## Bit Flipping Attacks

# 4 Message Authentication

1. Crypto Hash Function properties:
   - Collision resistant: 
     - attackers cannot find 2 messages that hash to the same value
     - attackers know the hash value but still cannot know anything about the key
   - irreversible
   - output should appear random
   - avalanche effect: any change to a message will cause a big change in hash
2. Hash functions:
   - MD5: broken
   - SHA-1: 128 bits
   - SHA-2: similar to SHA-1. 256 bits
   - SHA-3 are totally different from SHA-2. So if there's an attack on SHA-2, hope it won't work on SHA-3
   - Black: the best hash function so far
3. Message Integrity Checking: H(message + key) --> HMAC algorithm

## HMAC

H(K | H(K|M))

