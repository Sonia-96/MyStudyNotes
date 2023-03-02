# Midterm Review

Write a new protocol for the network stack, which one is the easiest and hardest? (sample question for midterm)

- application layer easiest: only talk to the program
- hardest: network layer. need to make many routers to agree with the protocol

## The Basics

* What are the layers of the network stack and what are their purposes?  What are example protocols in each layer?
* What are the types of network delays and their causes?

## Transport Layer

* What features are implemented at the transport layer?
* How can we achieve reliable data transfer (a bit of a misnomer) over an unreliable network?
* What features does TCP provide beyond reliable data transfer?

## Network Layer

* How do routers know where to forward packets?
* What is an autonomous system?
* What algorithms might we use to route within or between ASs
* How are IP addresses assigned to make life easier on routers?
* What is NAT, why do we need it, and how does it work?

## Link Layer

* What's the role of the link layer?
* What is ARP and why do we need it?
* How can we avoid collisions when communicating over a broadcast medium?
* What is a switch, and how are they different from routers?

## Crypto

* What are the properties we want for secure communication?
* What is a block cypher?  What are their inputs/outputs?  What are the primitives used to build one?  What are examples of block cyphers?
* What is a stream cypher?  How do they work?  What are common examples of stream cyphers?
* What is a cryptographic hash function?  How are they different from hash functions used for hash tables?
* What is Diffie Hellman key exchange, and how does it work?
* What is RSA?  What can you do with an RSA public and/or private key?
* **How are DH and RSA related/different?**
* What are block cypher modes of operation and why do we need them?
* What is a digital **certificate**?  What are they used for?
* What are some simple authentication protocols and how can they be compromised?