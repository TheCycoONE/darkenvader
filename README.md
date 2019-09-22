Darkenvader is a game I wrote in highschool in 2003. The initial commit
is that version, with the addition of this README.md, the LICENSE.md, and
git files.

Darkenvader was written for Java 1.3.

# Running

Darkenvader can be compiled with the Java JDK 12 from the root of this repo with:
`javac ./Darkenvader/Darkenvader.java`

Darkenvader can then be run with:
`java Darkenvader.Darkenvader`

I have tested Darkenvader on JDK 8 and Open JDK 12. It was originally written for
JDK 1.3, so it should compile and run there as well.

# Known issues
* The game makes use of what are now deprecated APIs.
* Movement is VERY inefficient. At the time I didn't know how to show a portion
  of an image without reloading the whole thing.
* High score file is UTF-16 with no BOM, and written to wherever you run the
  game from.
