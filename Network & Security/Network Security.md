# 1 Session Keys, TLS

## Session Keys

1. session key: a key used for a session
2. why use session keys?
   - The longer a key is used, the more cyphertext + plaintext pairs an evasdropper can collect and use them to try to an offline attack
   - If one key is leaked, all conversations using that key will be decrypted! (prefect forward secrecy)
   - If we need to communicate with an untrusted software, we shouldn't give them a long term key
3. **Perfect Forward Secrecy** (PFS): even if the secret key is leaked, the attacker cannot decrypt any previous encrypted messages
4. long-lived key is stored on disk
5. Choices of session key:
   - shared-secret key crypto:
     - f(R, Kab): bad. 
       - not secret: because Alice sends this message to Bob
       - can guess the long-term key: if the attacker try to decrypt this message, the long-term key Kab might be leaked
     - f(R + 1, Kab): 
       - secret: because Alice doesn't send this message to Bob
       - Can guess the long-term key
       - if the next round's nonce is R + 1, and the message Alice sends to Bob is f(R+1, Kab). The session key is leaked.
     - f(R, Kab + 1): this one is better, but if anyone gets Kab, the session keys are leaked
     - none of above are PFS because Kab is long lived
   - public key crypto:
     - A -> B: Enc(session_key, B<sub>pub</sub>). If Bob's private key is hacked, the session key will be leaked. If Alice's private key is hacked, it's OK.
     - A -> B: sign(Enc(session_key, B<sub>pub</sub>), A<sub>priv</sub>)
     - A -> B: Enc(R1, Bpub). B-> A: Enc(R2, Apub). session key = math(R1, R2)
       - the math function here is usually Diffie-Hellmen. R1, R2 are public keys, and the session key is the secret key. Even if Apriv and Bpriv are revealed, an attacker only knows R1 and R2; they won't know the session key. So we get PFS.

## TLS (Transport Layer Security)

TLS for secure communication in transport layer

### TLS Handshake

1. Client to Server: Client Hello, list of cypher suites,  random **nonce**

2. Server to Client: Server Hello, server certificate (prove its identity to the client), chosen cypher suite, random **nonce**

- (client and server compute **pre master secret**, which is basically a session key based on two nonces that were sent. Usually we use Diffie-Hellman to compute the pre-master secrete.)

3. Client to Server: Encrypt(PreMasterSecret, ServerPublicKey)

- (each side generates 4 session keys)

- (each side computes 2 keys, one for encryption and one for message authentication - MAC key)

4. Server to Client: MAC(all handshake messages + "SRVR", S<sub>MAC</sub>) (the algorithm here depends on the chosen cypher suite) (Smac is server's MAC key)

5. Client to Server: MAC(all handshake messages + "CLNT", C<sub>MAC</sub>)

The last 2 steps are to prevent Trudy from modifying the first 2 messages to trick server/client into using some broken cypher suites like RC4.

### Cypher Suites

TLS let the server and the client to choose cyphers to use for the different parts of the communication. A cypher suite specifies:

- key exchange algorithm
- Bulk encryption algorithm
- MAC algorithm 

An example is `TLS_ECDHE_RSA_AES_128_GCM`:

- TLS: use TLS
- ECDHE: use Elliptic Curve Diffie Hellman Ephemeral, the key exchange algorithm. Here, ephemeral means we get PFS.
- RSA: the server's certificate is an RSA certificate
- AES_128_GCM: the bulk encryption algorithm and the MAC algorithm. `AES_GCM` does authenticated encryption.

### TLS Records

A **TLS record** is a TCP segment, but the data field is **encrypted** and contains extra header info for TLS. The following parts are included in a TLS record:

- IP healder
- TCP Header
- TLS Header: type, version, length
- data, paddings
- MAC: MAC of the record header, data, and SSL sequence number (which is not actually sent)
- (data, paddings + MAC are encrypted)

downsides:

- IP header and TCP header are in plaintexts, evasdropper can se src/dest IP/port

## Assignments TLSLite

1. HKDF
   - use CBC mode
   - Prk - pseudorandom key
2. data types
   - message: fix-sized byte array
3. hint: start from wring everything in main, then seperate them into classes

# 2 IPSec & Tor

## IPSec

// TODO: read textbook chapter 8.7

IPSec is the same as the TLS but at network layer

cool use: create **virtual private network (VPN)**

<img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230315101210567.png" alt="image-20230315101210567" style="zoom:50%;" />

src and dest IPs are encrypted

### Security Association (SA)

SA is oneway, so we need 2 SAs for bidirectional communication.

An SA has the following information:

- ID - security parameter index

## Tor

Even if you use VPN, doesn't mean you are safe because the VPN know all the messages.

Example: wrap a letter in layerd envelopes through nodes in Tor Network, so nody can know both the src and dest IP (to be specific, you don't know who is Bob talking with)

<img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230315102136264.png" alt="image-20230315102136264" style="zoom:50%;" />

![image-20230315102151538](/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230315102151538.png)

# 3 Wireless Security

Transport Layer Security - TLS

Network Layer Security - IPSec

Link Layer Security - Wifi Security

## WEP

1. WEP - Wired Equivalent Privacy

   - 64 bit for encryption: 40 bits for shared secret (key) + 24 bits for IV

   - encrypted with stream cypher:

     - Rule # 1: don't repeat the keystream

   - issues:
     - only IV changes for messages, so there are 2 ** 24 keystreams to choose from. Therefore, after 12000 messages, there's 99% chances of duplicated keystream
     - once used RC4 as the stream cypher, which is really bad in this case. Because first ~100 bytes of RC4 keystreams leak info about the key

WEP is bad: short keys, stream cypher, short IVs

## WPA (Wifi Protected Access)

1. 4-way handshakes

better encryption algorithm: WPA2 -  have AES support

flexible authentication options: including public key authentication
