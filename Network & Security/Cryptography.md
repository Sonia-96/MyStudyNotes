# Intro

1. Things we care about for secure communication:
   - Confidentiality: no one but intended recipient can read the message
   - message integrity: 
   - Authentication: know who we are talking to 
   - non-repudiation

2. Adversaries:

   - Eavesdropper
   - man in the middle ([MITM](https://www.imperva.com/learn/application-security/man-in-the-middle-attack-mitm/))
   - assume adversaries have NSA data centers (???)

3. Terminologies

   - plaintext
   - cyphertext
   - Keys:
     - Shared secrete key (symmetric key): the same key is used for both encryption and decryption
       - Encrypt: CC(plaintext, key)
       - Decrypt: CC(cyphertext, -key)
     - Public key cryptography (assymetric cryptography): parties have a public key and secret key // TODO

   <img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230210093741719.png" alt="image-20230210093741719" style="zoom:50%;" />

4. Cryptosystems
   - Substitution building block: replace parts of the message with something else
     - Caeser Cypher: each letter are rotated by an offset
   - permutation building block: shifting things around
     - pig latin
   - XOR
     - XOR and plus are similar, but plus need to deal with carrying / borrowing bits
     - a ^ b ^ b = a
     - x ^ 0 = x
5. Requirements for builidng blocks:
   - deterministic: a plaintext can generate only one cyphertext
   - Reversible: we can decrypt cyphertext to the plaintext
   - Efficient to compute
6. downsides:
   - Substitution: often use bytes. Then there are only 256 entries in the table, which is easy to decode
   - Permutations: 
   - combine substitution + permutation can get better crypto system // TODO review this part
7. A cryptosystem is secure if attacks can only use brute force to do key guessing
8. types of attack
   - cyphertext only: 
     - statistical analysis can help this
   - Known plaintext: get cyphertext & plaintext pairs and decode the key
   - chosen plaintext: if the output ctyphertext is very similar, attacker might get some information from it
9. Cyphertext should:
   - Appear random
   - small changes to input should result in large changes to cyphertext: avoid chosen plaintext 
   - "Avalanche effect"??? // TODO review

# 1 Block Cypher - Symmetric 

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

# 2 Stream Cypher

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

- if one bit is changed in block cypher, the plaintext decrypted will be huge different. So block cypher is clower to integerity than stream cypher. (???)
- stream cypher & bloc cypher are both confidential

# 3. Message Authentication

