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
   - **Confidentiality:** only the sender and the intended receiver can understand the message
   - **Message Integrity**: the message between the sender and the receiver should not be altered in transit, either maliciously or by accident
   - **End-point Authentication**: both the sender and the receiver can verify the identity of the other party involved in the communication
   - Non-repudiation: the assurance that someone cannot deny the validity of something. For example, if I receive a message from you, you cannot say this is not from you.

3. Cryptographic Components

   - plaintext: original message
   - cyphertext: encrypted message
   - Keys:
     - Shared secrete key (**symmetric cryptography**): K<sub>A</sub> == K<sub>B</sub> and are secret
     - Public key  (**asymmetric cryptography**): parties have a public key (everyone knows) and a secret key (only Alice and Bob knows)
     - Symmetric crypto is usually faster than asymmetric crypto, so in practice we often use a combination of both //TODO what's the advantages of public key crypto?

   <img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230210093741719.png" alt="image-20230210093741719" style="zoom:50%;" />

## Cryptography Building Blocks

1. Requirements for builidng blocks:
   - deterministic: a plaintext can generate only one cyphertext
   - Reversible: we can decrypt cyphertext to the plaintext
   - Efficient to compute
2. Common building blocks:
   - **substitution**: replace each byte with a different byte
     - **Caeser Cypher** crypto system: each alpha letter is rotated by an offset, e.g., a -> d, b -> e, ...
       - for example, "DSZQUP SVMFT" -> "CRYPTO RULES", for which the key is the offset 1
       - symmetric algorithm:
         - Encrypt: CC(plaintext, key)
         - Decrypt: CC(cyphertext, -key)
   - **permutation**: shifting things around
     - pig latin: move the consonant clusters to the end and add "ay", e.g., "CRYPTO RULES" -> "YPTOCRAY ULESRAY"
   - **XOR**: bitwise exclusive or. XOR and plus are similar, but XOR is slightly nicer because plus need to deal with carrying / borrowing bits
     - important applications of XOR:
       - a ^ b ^ b = a
       - x ^ 0 = x
3. Downsides of the above building blocks:
   - Substitution: easy to decode. Since substitution is used for bytes, there are only 2<sup>8</sup> possible keys. We can use brute force or statistical analysis to find the key.
   - Permutations: [ref](https://en.wikibooks.org/wiki/Cryptography/Permutation_cipher)
     - the plaintext may have to be padded (e.g., -ay in pig latin) (if the padding is identifiable then part of the key is revealed)
     - information related to the length of the key is revealed by the length of the ciphertext.

## Types of Attack

A cryptosystem is secure if attacks can only use brute force to do key guessing.

1. **Cyphertext Only**: the attacker has access to only the intercepted cyphertext.
   - For example, the statistical analysis can help in a cypiertext-only attack for Ceaser Cypher
2. **Known Plaintext**: the attacker knows some of the cyphertext & plaintext pairs
3. **Chosen Plaintext**: the attacker can choose plaintexts and view their corresponding cyphertext. For example, the attacker might try encrpting messages `000000` and `000001`. If the output cyphertexts are very similar, the attacker can know something about the key from the difference.
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

# 5 Public key (Asymmetric) Crypto

The **symmetric cryptography** requires a shared secrete key between the sender and the receiver. But sharing the key itself requires secure communication. To solve this problem, the **public key cryptography** makes it possible to transmit encrypted messages without distributing any secrete keys. And this system can also be used for authentication and digital signature.

## Main Idea

If Alice wants to send a message to Bob:

1. Alice encrypts the message with Bob's public key (K<sub>B</sub><sup>+</sup>): K<sub>B</sub><sup>+</sup>(m)
2. Bob decrypts the message with his private key (K<sub>B</sub><sup>-</sup>): K<sub>B</sub><sup>-</sup>(K<sub>B</sub><sup>+</sup>(m)) = m

<img src="./assets/image-20230225175800441.png" alt="image-20230225175800441" style="zoom:80%;" />

Since anyone can use Bob's public key to send him a encrypted message, a digital signature is needed to bind a sender to a message.

## Diffie-Hellman

|       | Secrete Number | Public Key                                     | Private Key                                                  |
| ----- | -------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| Alice | S<sub>A</sub>  | T<sub>A</sub> = g<sup>S<sub>A</sub></sup> % N  | T<sub>B</sub><sup>S<sub>A</sub></sup> =  g<sup>S<sub>A</sub>S<sub>B </sub></sup> % N |
| Bob   | S<sub>B</sub>  | T<sub>B</sub> = g<sup>S<sub>B </sub></sup> % N | T<sub>A</sub><sup>S<sub>B</sub></sup> =  g<sup>S<sub>A</sub>S<sub>B </sub></sup> % N |

Note, g and N are public. A common choice of g is 2.

To decode the private key, we need to solve g<sup>x</sup> % N = T<sub>A</sub>.  With the mod N, this will be really really hard! This is called **discrete logarithm problem** and it's impractical to solve for big numbers. (That's why DH generates long keys)

Diffie-Hellman allows the parties to compute the private key without sending the secrete numbers over the network. After each party gets the private key, they switch to symmetric cryptography (like AES) to communicate. 

## RSA

RSA is similar to Diffie-Hellman, with the equation that m<sup>ed</sup> % N = m, for which e, d, N are carefully chosen.

1. Generate public and private RSA keys:
   - Pick 2 big prime numbers `p` and `q`. The larger the values, the more difficult it is to break the RSA, but the longer it takes to perform the encoding and decoding.
   - Compute `n` = p * q, `z` = (p - 1) * (q - 1)
   - Choose a number `e`: e < n && e and z has no common factors (e and z are relatively prime)
   - Choose a number `d`: e * d % z = 1
   - <e, n> is public key, <d, n> is private key
2. If Alice wants to send a message (`m` in bit pattern, which is smaller than `n`) to Bob:
   - Alice encrypts the message: C = m<sup>e</sup> % n
   - Bob decrypts the cyphertext: C<sup>d</sup> % n = m<sup>de</sup> % n = m,

3. Signatures with RSA
   - you sign the message with your private key: s = m<sup>d</sup> % n
   - people verify the message with your public key: m = s<sup>e</sup> =  m<sup>de</sup> % n. They will get the original message if the signature is valid! 

The security of RSA lies in the fact that there's no algorithms for quickly factoring a number, in this case `n`, into primes `p` and `q`. This is called **prime factoration** problem. If one knew `p` and `q`, and given the public key `e`, one could easily compute the secrete key `d`.

## Fast Exponentiation

The public key cryptography depends on our ability to quickly compute x<sup>n</sup> % m, with n and m as big prime numbers. 

Say, the exponent is 32 = 0b 100011 = 2^5 + 2 ^ 1 + 2 ^ 0. Then x<sup>32</sup> = x<sup>2^5</sup> * x<sup> 2 ^ 1</sup> * x <sup>2 ^ 0</sup> % m. We only need 3 modulo multiplications to get the result.

```java
// compute x ^ n % m
public static int fastExponentiation(int x, int n, int m) {
  int res = 1;
  while (n > 0) {
    if ((n & 1) == 1) {
      res = res * x % m;
    }
    n >>= 1;
    x = x * x % m;
  }
  return res;
}
```



## Elliptic Curve

Elliptic curve is a way to generate private and public keys. It can generate much shorter keys and are slightly more difficult to be decoded than the descrete logarithm problem. 

https://www.youtube.com/watch?v=NF1pwjL9-DE

In Diffie-Hellman, the private key is g<sup>x</sup> % N, while in Elliptic Curve Cryptography, the private key is e * g. The curve has 2 properties: given g an e * g (g is a point, e is an integer), it's super hard to figure out e. (Why??? it seems like just a simple math.)
