Darkenvader is a game I wrote in highschool in 2003 for Java 1.3.

The 2003 version is tagged in git as `v1.0`

Since then I have fixed a number of bugs, and added a modern build system.

# Running
I have tested Darkenvader using versions 8 and 12 of OpenJDK and Oracle's JDK.

## Linux / MacOS
1. `cd` into the root directory
2. Run `./gradlew run`

## Windows
1. `cd` into the root directory
2. Run `gradlew.bat run`

# Known issues
* Movement is VERY inefficient. At the time I didn't know how to show a portion
  of an image without reloading the whole thing.
* High score file is UTF-16 with no BOM.
