# Intro

1. Things we care about for secure communication:
   - Confidentiality: no one but intended recipient can read the message
   - message integrity: 
   - Authentication: know who we are talking to 
   - non-repudiation

2. Adversaries:

   - Eavesdropper
   - man in the middle ([MITM](https://www.imperva.com/learn/application-security/man-in-the-middle-attack-mitm/))
   - assume adversaries have NSA data centers

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
   - chosen plaintext: if the output ctyphertext is very close, attacker might get some information from it
9. Cyphertext should:
   - Appear random
   - small changes to input should result in large changes to cyphertext: avoid chosen plaintext 
   - "Avalanche effect"??? // TODO review

## 1 Block Cypher

A block cipher is an encryption algorithm that encrypts a **fixed size** of n-bits of data - known as a **block** - at one time. The usual sizes of each block are 64 bits, 128 bits, and 256 bits. So for example, a 64-bit block cipher will take in 64 bits of plaintext and encrypt it into 64 bits of ciphertext. 

- symmetric
- encrypts block-sized (fixed size) plaintext to a certain-byte cyphertext
- security depends on key size / block size. assuming n bit key, then there are 2<sup>n</sup> possibilities, attackers need to guess 2<sup>n-1</sup> times statistically. The bigger the block size is, the more possibilities there are.
- 

## DES (Data Encryption Standard)

Old, broken

## AES (Advanced Encryption Standard)

new, good
