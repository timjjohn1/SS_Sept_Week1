This is my weekly project for week1.
RMDBS.java is my main driver.

What I learned:
The biggest take away from this project is the methods of seperating the three layers(Presentation, Processing, and Data).
In order to do that in this project, I was able to put the presentation layer in the main driver(which will create an instance of the service layer).
I was then able to have the service layer(Service.java) be the service class which creates an instance of each data access object.
Finally with each data access object(AuthorDAO.java, BookDAO.java, Publisher.java), they will create the pojo's when they are read from a file.

Another take away from this project is that the static keyword should only be used when very much necessary.
This ensures that those class method will be used only when you have made an instance of a class and when you need them.

Finally, a simple fix that my project had was function calls that were left open when I called another member function from with in it.
I fixed this by replacing the function calls with returns in order for them to clear off of the stack and it will not lead to a stack overflow.

-Tim Johnson
