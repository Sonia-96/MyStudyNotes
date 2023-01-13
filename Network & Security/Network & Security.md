# 1 Intro

Course overview:
- Networking
- Cryptography
- Computer Security

## The Internet

the Internet: connect hosts and transmit data

- hosts: endpoint devices that want to exchange data. Clients and servers are both hosts.
- access network: a small network with many hosts. e.g., UConnect, Google Fiber, Xfinity
- internet backbone: networks with many high-capacity fiber optic links that can carry huge amounts of traffic, e.g., AT&T, T-mobile

## Switching

- circuit switching: reserve a line between people who are calling. This is wasteful if you don't speak for the whole duration of the call. 

  - Computer communication is bursty, which is not suitable for circuit switching.

- **packet switching:** Data is split into packets. Instead of reserving a path from host to host, at each **hop** along the path, the packet is forward to the next hop.

  - Packet: header + payload
  - voice phone calls use packet switching!

  ![img](./assets/Hop-count-trans.png)

## Network Layer

From top to bottom, the network layers and corresponding protocols:

1. **Application Layer**: how applications parse the data
   - HTTP protocol: web page. a type of client-server protocol
   - SATP protocol: email
   - DNS: turn human-readable domain names to numeric numbers
2. **Transport Layer**: process to process communication
   - TCP
   - UDP - User Datagraph Protocol
3. **Network Layer**: host to host communication (IP) (multiple-hop communication)
   - IP protocol
4. Link Layer: one-hop communication, e.g. Wifi, Ethernet
   - breaks up packets into frames and sends them from source to destination
5. Physical Layer: mechanism for one hop communication: electrocal/optical/eletromagnetic wave stuff

"Programmers work in the application layer, have to make choices about the transport layer, and have to understand what goes wrong in the network layer."

# 2 Delays

## Four Common Delays

1. **Propogation Delay**: the time physical signal takes to travel between hops

   - depends on the distance and the speed of the connection (radio, fiber optics > electrical signal)

   - propogation_delay = scale * distance / speed_of_light
     - the type of the connection will influence the scale

2. **Processing Delay**: the time devices spend on examining packets (often just read the header)

   - processing delay is usually in the order of nanoseconds, which is constant and negligible

3. **Transmission Delay**: the time to convert packets from bits to physical signals. This depends on devices.

   - the devices are rated by their transmission delay, e.g., 1 Gbps ethernet device, 54 Mbps wireless device
   - transmission_delay = data_length / device_transmission_rate

4. **Queueing Delay**: the time that packets are stored in the queue. The queue is fix-sized. If the queue is full, the packets outside of the queue will be dropped. 

   - Queueing delay depends on how busy the network is. So queueing delay is very unpredictable and variable.

   - traffic_intensity (TI) = incoming_data_rate * avg_size / transmission_rate
   - we should keep TI < 1

## How to measure delays

- It's hard to measure the time from host1 to host2, because we will get delays to get the timestamp. but we can measure the time on the same host. -- Round Trip Time (RTT)
  - ICMP protocol, `ping` pong
  - RTT = 2 processing delays (~0) + 2 * (4 delays for each hop)
- if we test RTT for several times, the variability should be due to queueing delay (bur probably they go different paths)
- we can estimate propagation delay based on distance
- transmission delay: vary packet sizes, the difference in time should be due to transmission delay

## traceroute

each packet has a "time to lie (TTL)" packet which specifys the number of hops it is allowed to travel. Many devices send a response when TTL hist 0.

- `traceroute`: sends packet with increasing TTL values and measure the RTT of any packet that gets a response

## Throughput

- Throughput: the amount of data (bits/s) that can be transmitted etween hosts
- bottleneck node: the minimum throughput device on a path

for example, if i send data from my laptop through my wifi to google server, the bottleneck node is highly propably my own wifi.The internet backbone usually has a big capacity so is rarely a bottleneck.

a bottleneck node is usually the access network.

# 3 Application Layer

homework: write a DNS server

Q: TCP - stream?

1. Application layer is the highest layer, but it need to interace with lower layers:

   - get IP address from network layer

   - get port number from transport layer

2. Transport Layer tradeoffs. We usually consider the following needs:

   - reliable data transfer: all sent data need to be received in order, but potentially slow
   - latency: data need to be received in a certain time window
   - data rate

   TCP is reliable, UDP is fast. Streaming applications are more likely to use UDP.

3. Application Topology:

   - client/server model:
     - HTTP, web socket
   - peer to peer model: programs behaves as both clients and clients at various times. e.g., BitTorrent
     - Disadvantages: 
       - can be very slow if multiple comptuters access to one computer
       - limited security (viruses, file security)
     - Advantages:

4. HTTP protocol

   - use TCP
   - Text based: is easier to read, write, and debug but has bigger size
   - single request: Request/response/done
   - Content Length header: the size of the body in bytes
   - special characters: \r\n, ' ' (empty space), : 

5. protocols for email:

   - SMTP -Simple Mail Transfer Protocol: sending email
     - laptop -> utah.edu SMTP mailservers ->  (use SMTP to push emails to) gmail // TODO draw a graph similar to ben's in class

   - IMAP/POP receiving email: receiving email
   - all are TCP based

6. BitTorrent

   - Trackers: HTTP servers, tracking who are sharing the data
   - distrbuted hash table
   - originally TCP, then switch to UDP (uTP - UDP Torrent Protocol)

7. DNS: domain name system

   - Enables network layer

   - tanslate a domain name to an IP: e.g., cs.utah.edu -> 155.22.17. 21 (iPv4)

   - binary protocol

   - use UDP because that IP address is so small that we usually only use one packet for it

     