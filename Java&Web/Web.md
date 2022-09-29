# 1 Web Pages

## Unix

### Unix Permissions

d<span style="color:red">rwx</span><span style="color:blue">r-x</span><span style="color:green">---</span>

- d - directory
- <span style="color:red">owner's permissions</span>
- <span style="color:blue">group's permissions</span> (try running groups in cmd)
- <span style="color:green">everyone else</span>
- `% chmod go+rX images # change permissions for group and other to read and execute`
  - capital X???
- `ls -ld`

### Home Directory

commands: 

- `echo $HOME`
- `cd $HOME / cd ~ / cd`: enter home directory
- `echo $PATH`: what is $PATH? list of dire the computer will look through to find commands you run
- `echo $SHELL`
- `env`: environment variables in your system 

## Web Client & Server

- web client: web browser - display info
- web server: a program that allows a web browser to talk to it and sends it the requested files
  - popular web servers:
    - Apach
    - Apach Tomcat
    - Nginx
    - LiteSpeed

## HTML

1. HTML is a static language, not programming language

2. Tags & Attributes: 

   - `<tag attr1="value1" attr2="value2"> stuff </tag>`
     - Attributes can be from CSS
   - <tag/>: tags don't contain "stuff"

3. HTML web page pieces //TODO

   ```html
   <html>
     	head
   		body
   </html>
   ```

### Common Tags

- `<a href="https://google.com"> This is a link for google </a>`: a hyperlink
  - <a href="https://google.com"> This is a link for google </a>

- `<img src="name.png"/>`: no closing tag

- `<ul>`: unordered list

- `<ol>`: ordered list
  - `<li>`

- Grouping:

  - `<div>`

  - `<span>`

- `<script>`: for Javascript

### Colors

stored as RGB, 1 byte for each value. e.g., 0xFF0033 is red

the smallest trunk the computer deals with - 1 byte

normal size  - 8 bytes

We use int to store color, extra 1 byte is used for opacity

## CSS

1. Link CSS file to your HTML file: (rel is for relation)

   ```html
   <head>
     <link rel="stylesheet" type="text/css" href="styles.css">  //  TODO
   </head>
   ```

2. Selector:

3. Class

4. id

## Emacs

open emacs editor: `emacs -nw`

1. create a new file: C-x C-f
2. save a file: C-x C-s
3. split the screen: C-x 2
4. switch buffers: C-x b
5. move between split windows: C-x o
6. suspend Emacs: C-z
7. fg: return Emacs
8. cancel a command: C-g
9. save file: C-x C-s
10. mark set: C-space （必须首先调成英文模式）
11. copy: M-w
12. cut: C-w
13. paste: C-y
14. move the cursor:
    - By line: C-a to start, C-e to end

TODO: create a html file and css file in Emacs

what's the address for html file in a remote server?

## Javascript

link script to html

## Testing

# 2 Basic Network Architecture

## Client / Server Model

Most networking applications are using client / server model, e.g., web browser, zoom, facebook.

![Lightbox](./assets/801.png)

* Networked applications without cline/servers are often called "**peer to peer**" applications, e.g., BitTorrent.

## Data Transfer Layers - OSI layer model

OSI (Open Systems Interconnection) Layer Model. From the bottom to the top, the layers are:

1. **Physical layer**: the physical medium that transmits raw bit stream between devices, e.g., radio wave, electric signals
2. **Link layer**: breaks up packets into frames and sends them from source to destination. e.g., ethernet, wifi
3. **Network layer**: how information is sent across the internet. The network layer use IP to route packets to a destination node, e.g., iPv4, iPv6
4. :star:**Transport layer**: how data gets from a **program** to another program
   - we use **port** to decide which program the data belongs to
   - TCP & UDP - standards that enable application program and computing devices to exchange data over network
     - **UDP** defines source port and destination port, but cannot check if the data is received. 
     - **TCP** gurantees your will receive all data because it will resend lost package

5. :star::star:**Application layer**: how your application parses the data it sends/receives. (eg. **HTTP**)

## Protocols

Protocols define how two programs communicates. e.g., HTTP, ssh, smtp

- **HTTP**: Hypertext Transfer Protocol -- a request-response protocol that describes the order and syntax for presenting information.
  - text-based, stateless (no session information), for web pages
- **HTTPS**: HTTP  with encryption and verification
- UDP: compare to TCP, UDP is not reliable, because it can't gurantee all data will be received. (live video may use UDP, because users can't move back)
- **DNS** (Domain name system): translate human readable computer names into unique IP address
- TLS (Transport Layer Security): TLS is used in HTPPS to secure data. i.e., TLS pacekt is secured.

### Protocol layers

- lowest level - internet protocol: specifies how small “packets” of data (with no inherent semantic information) are passed between machines.
- Next - TCP
- highest level - application protocol

### TCP/IP

A packet based on TCP/IP protocol has following information:

- IP header: 
  - Version/type/length
  - ID/Flags
  - Time/protocol/Checksum
    - checksum: A checksum is a value that represents the number of bits in a transmission message and is used by IT professionals to detect high-level errors within data transmissions.
- TCP header
  - Source
  - Destination
- Payload (application data)

#### IP Address

1. IP: a unique machine ID

   - IPv4: 32 bits, ~4.3 billion addresses
     - e.g., 10.17.165.139: 4 sections, each section's value is 0~255 (8 bits)

   - IPv6: 128 bits. Every atom in the universe can has its IP.

2. reading your IP address: run `Ifconfig` in the terminal, your IPv4 address is under `en0` and next to `inet` and IPv6 address is near to `inet6`

#### TCP

1. TCP specifies which program on the machine gets the data - **port** number

2. Many common applications use fixed port numbers:

   - HTTP - 80


   - HTPPS - 443


   - ssh - 21
     - School of Computing change the port to 5522 to avoid hits


   - Doom - 666


## Wireshark

- loopback: look for the traffic of the program on your computer
- Wifi: en0: look for the traffic of programs over internet

## Lab

### Ping

`ping` command relies on the Internet Control Message Protocol (ICMP). Its basic use is to confirm the connectivity between two hosts.

```bash
ping 10.17.165.139 # send requests to this IP address
ping -c 10 10.17.165.139 # send 10 echo requests
```

`ping` can show **round-trip time** (response time + reply time) for each transmitted packet

```bash
sonia@Yues-MacBook-Pro Java&Web % ping -c 5 google.com
PING google.com (142.250.189.14): 56 data bytes
64 bytes from 142.250.189.14: icmp_seq=0 ttl=57 time=46.433 ms
64 bytes from 142.250.189.14: icmp_seq=1 ttl=57 time=51.359 ms
64 bytes from 142.250.189.14: icmp_seq=2 ttl=57 time=46.906 ms
64 bytes from 142.250.189.14: icmp_seq=3 ttl=57 time=60.286 ms
64 bytes from 142.250.189.14: icmp_seq=4 ttl=57 time=53.585 ms

--- google.com ping statistics ---
5 packets transmitted, 5 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 46.433/51.714/60.286/5.062 ms
```



# 3 Basic Networking

