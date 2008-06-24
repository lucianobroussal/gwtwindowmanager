The GwtWindowManager project is compiled with eclipse.

Compilation
----------

To have gwtwindowmanager compiling sucessfully
define in eclipse project properties the GWT_HOME classpath entry variable.
define in eclipse project properties the GWT_EXT classpath entry variable.
GWT_HOME is the root dir of your GWT binaries installation.

Building binary jar
-------------------
Rename the build-template.properties by build.properties and set up the paths variables.
Open the ant build.xml file with the Ant view and run the package task.

Javadoc
-------
Edit the build.xml file and set the GWT_HOME ant property with the root dir path of your GWT binaries installation.
Open the ant build.xml file with the Ant view and hit the javadoc task.


------------------------------------------------------------------------
Copyright (c) 2006-2007 Luciano Broussal (http://www.gwtwindowmanager.org)
   