code snipets will be helpful

1. equals & ==

   ```java
   Integer i1 = new Integer( 5 );
   Integer i2 = new Integer( 5 );
   
   System.out.println( "==:     " + ( i1 == i2 ) ); // false
   System.out.println( "equals: " + i1.equals( i2 ) ); // true
   ```


# Web Server

**Q4.** We've all used browsers to visit pages on the web. Briefly and clearly explain how typing a website address into the browser lets us see a webpage.

Hint: your answer will probably involve the terms "client", "server", "sockets", "html", and "http requests".

**Solution:**

When we type a website address into the browser, the following chain of events occurs:

a. The browser (client) initiates an http request (get request) to the server powering the website. The actual request is often done via sockets: the server listens on some port for a socket, gets one from the client, then grabs information from that socket using a stream of some sort.

b. The server then serves up whatever file the browser is asking for. This is either an index.html page (if / was requested) or often some other html page.

c. The browser receives the html over the socket it gave the server, and renders the webpage from the html it receives.

d. If the html uses other resources (css, files, etc), the browser requests those from the server too.