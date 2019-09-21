Darkenvader is a game I wrote in highschool in 2003. The initial commit
is that version, with the addition of this README.md, the LICENSE.md, and
git files.

Darkenvader was written for Java 1.3, using the AWT toolset.

# Running

Darkenvader can be compiled with the 1.8.x JDK from the root of this repo with:
`javac ./Darkenvader/Darkenvader.java`

Darkenvader can then be run with:
`java Darkenvader.Darkenvader`

I have not tested with any newer JDK version. It was originally written for
JDK 1.3, so it should compile and run there as well.

# Known issues
* The game makes use of what are now deprecated APIs.
* Movement is VERY inefficient. At the time I didn't know how to show a portion
  of an image without reloading the whole thing.
* On my computer the bottom of all the windows is being cut off. Something must
  have changed in the last 16 years about how height is calculated.
* High score file is UTF-16 with no BOM, and written to wherever you run the
  game from.
