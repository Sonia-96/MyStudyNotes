# Midterm Review

sample question for midterm: Write a new protocol for the network stack, which one is the easiest and hardest? 

- application layer easiest: only talk to the program
- hardest: network layer. need to make many routers to agree with the protocol

## The Basics

1. What are the layers of the network stack and what are their purposes?  What are example protocols in each layer?
   - 

2. What are the types of network delays and their causes?
   - 

## Transport Layer

1. **What features are implemented at the transport layer?**

   transport layer is responsible for process to process communication.

   - use port number to specify the process
   - use TCP/UCP sockts to communicate between processes

   > Segmentation of data into smaller units (segments) for transmission over the network.
   >
   > End-to-end delivery of data between applications running on different devices.
   >
   > Flow control to ensure that data is transmitted at a rate that the receiver can handle.
   >
   > Error recovery and retransmission of lost or corrupted packets.
   >
   > Multiplexing and demultiplexing of data streams from multiple applications onto a single network connection.

2. How can we achieve reliable data transfer (a bit of a misnomer) over an unreliable network?
   - to avoid corruption:
     - checksum: error detection
     - ACK + sequence number: make sure the receiver has received the packet
     - retransmission
   - to handle lost packet: retransmit after timeout
   - Pipeling: using a scheme mixing Go-Bac-N and Selective Repeat, making data transmission more efficient

3. What features does TCP provide beyond reliable data transfer?
   - flow control: preotect the receiver. The data in-flight should be no more than the receiver window. 
   - congestion control: uses three states (slow start, congestin avoidance, fast recovery) to control the cwnd, the general rule for which is that lost packets (3 duplicate ACKs, timeout) will decrease the cwnd and acknowledged packet will increase the cwnd 

## Network Layer

1. How do routers know where to forward packets?

   Routers use forwarding table to determine to where to forward packets. The forwareding table contains the inforamtion like IP prefixes and output link port. When a packet arrives at a router, the router looks up the forwarding table and forward the packets to the next hop. 

2. What is an **autonomous system**?

   An autonomous system is a group of routers that are under the same administrative control. The routers under the same AS uses the same routing algorithm. 

3. **What algorithms might we use to route within or between ASs**
   - within AS: OSFP protocol --  Dijsktra algorithm
   - between AS: BGP protocol 

4. How are IP addresses assigned to make life easier on routers?
   - Small-scale network: DHCP
   - Big-scale network: NAT

5. What is NAT, why do we need it, and how does it work?

   NAT is network address translation, which is a way to share one public address with many derives and use port number to track each device. (NAT translation table)

## Link Layer

1. What's the role of the link layer?

   responsible for one-hop communication

2. What is ARP and why do we need it?

   address resolution protocol. Answer queries "what is the MAC address for the device with this IP?" (map IP -> MAC)

3. How can we avoid collisions when communicating over a broadcast medium?

   CSMA protocol: devices listen to see if there's other device transmitting data.They will wait until they stop before they start transmitting. If two devices try to transmit at the same time, a collision occurs, the devices will backoff and try again aftere a random delay.

4. What is a switch, and how are they different from routers?

   A switch is a transparent device that forwards packets based on their destiniation MAC address. A **switch** is like a router but operates in link layer. 

## Crypto

1. What are the properties we want for secure communication?
   - confidentiality: only the sender and the intended receiver can understand the message
   - non-repudiation: the assurance that someone cannot deny the validity of something. For example, if I receive a message from you, you can not say it's not from you.
   - message integrity: the messge between the sender and the receiver should not be altered
   - authentication: both the sender and the receiver can verify the identity of other party included in the communication

2. What is a block cypher?  What are their inputs/outputs?  What are the primitives used to build one?  What are examples of block cyphers?
   - A block cypher is a type of shared secret key cryptographic algorithm. In a block cypher, a message to be encrypted is processed in blocks of fixed length. Say, the block size is 64 bits, the message will be broken into 64-bit blocks and encrypted with a secret key. 
   - intputs: blocks of plaintext and secret key. Outputs: cyphertext blocks
   - primitives: 
     - key schedule algorithm
     - substitution table
     - permutation table
   - examples:
     - DES: feistel network
     - 3DES
     - AES

3. What is a stream cypher?  How do they work?  What are common examples of stream cyphers?
   - A stream cypher is a type of shared secret key cryptographic algorithm. A stream cypher generates a pseudorandom keystream and xor it with the plaintext byte by byte. Unlike block cypher, which operates on fixed-length blocks of data, stream cypher can encrypt data continuously. 
   - how do they work:
     - use key schedule algorithm to generate a secret key
     - generate a pseudorandom keystream
     - Encrypt:  keystream xor plaintext
     - Decrypt: cyphertext xor keystream
   - examples:
     - RC4
     - chacha20

4. What is a cryptographic hash function?  How are they different from hash functions used for hash tables?
   - a cryptographic hash function is a hash algorithm used to verify the data integrity and for authentication
   - differences:
     - cryptographic hash function should be much more difficult to find collisions (collision-resistant)
     - crypto hash should be slow to compute for passwords so they are more esistant for brute force attacks

5. What is Diffie Hellman key exchange, and how does it work?

6. What is RSA?  What can you do with an RSA public and/or private key?
   - Encrypt with public key
   - sign with private key

7. **How are DH and RSA related/different?**

   - Similarities:

     - both are public crypto algorithm

     - both uses the equation xab % N to compute the secret key

   - differences:

     - DH is used for secret key exchange, RSA is widely used for ssecure data transmission and digital signature
     - DH's numbers are big prime numbers, RSA's numbers are carefully chosen based on number theory
     - the security of DH is based on the discret logarithm problem, while RSA is based on prime factoration problem (it's infeasible to quickly factor a large number into two primes)

8. What are block cypher modes of operation and why do we need them?
   - ECB
   - Cypher Block Chaning
   - Output Feedback Mode
   - Counter Mode
   - Galoid Counter Mode = Counter Mode + GMAC (authentication)

9. What is a digital **certificate**?  What are they used for?
   - a digitial certificate is issued by Certificate Authority to verify a public key belongs to a certain entity.

10. **What are some simple authentication protocols and how can they be compromised?**