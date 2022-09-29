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

![Lightbox](./assets/801.png)

## Data Transfer Layers

1. generic client process:
   - find server
   - find path to server
   - Handle "dropper packets"
   - Manage bandwidth use
2. **OSI Layer Model**
   - :star::star:Application: 
   - :star:Transport: TCP/IP(most common), UDP
     - TCP gurantees your will receive all data (will resend lost package), UDP don't. live video may use UDP, because you can't move back
   - Network: iPv4, iPv6
   - Link: ethernet, wifi
   - Physical: radio
   
   sockets in TCP???

### Sending Data

Protocol: how two programs communicates. e.g., HTTP, ssh, smtp

- Http & https difference?

- IP header
- TCP header
  - Source
  - Checksum: https://www.techtarget.com/searchsecurity/definition/checksum
- Payload: application data

#### Protocols

Cookies

DNS: 

#### IP Address

IP: a unique machine ID

1. IPv4: 32 bits, ~4.3 billion addresses
2. IPv6: 64 bits,



reading your IP address:

- Ifconfig, find inet in en0
  - 10.17.165.139: country code, area code, 

#### TCP

specifies which program on the machine gets the data (program id) - port number

common port numbers:

- HTTP - 80

- https - 443

- ssh - 21 

- Doom - 666 ???



404 - data not found

200 - OK

### Wireshark

- en0
- Lookback

# 3 Basic Networking

