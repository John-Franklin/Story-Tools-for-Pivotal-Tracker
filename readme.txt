GamePane starts the application. 

Multiple instances of the the program run together; the server thread will appear to throw an exception on later server threads created, but that's just printStackTrace at work. If the files are changed from localhost it should work on multiple machines but would require some change to the GamePane class to separate the creation of the server from it.

The packets are sent through UDP and socket programming. The format for the packets are decided through Java object serialization, which allows the Player class to expand without needing necessarily to create new formats. However, this is very space wasting and will fall apart if too much is added to the player class, requiring individual formatting.

The PlayerMP class is necessary to ensure that instances of Players don't end up being sent with arrays of other players. It's useful for separating what should be sent over packet and what shouldn't.

Logging out is currently unimplemented, but the carnage packets will be the packets that do it if it ever is implemented.

Encryption of any sort remains unimplemented, even for login.