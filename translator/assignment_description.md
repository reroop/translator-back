**_Assignment description:_**

The assignment is to build a small web-application for an Estonian-English dictionary. 
The basic functionality from user's perspective is the following:

-User can add Estonian/English words and their equivalents.

-One word can have several equivalents.

-User can search dictionary entries

-Finds exact matches

-Finds fuzzy matches (replacing 1-2 letters in the search keyword should
 still yield relevant results, Levenshtein's algorithm might help here)

Everything else regarding the functionality is up to you. If you want to add or modify some 
functionality then feel free to do so and you might earn extra points.

Technical details:

-Backend should ideally be implemented in Java using some kind of framework, 
preferably Spring Boot.

-For the database solution a relational database should be used that is preferably embedded 
into the application such as H2, HSQLDB, Sqlite or 
if you host your application yourself MariaDB/MySQL, Postgres, Redis are fine too.

-Frontend should be implemented in Javascript/Typescript using any framework or way that suits you, 
we are using React with Typescript and this is what you will be working with here so keep that in mind.