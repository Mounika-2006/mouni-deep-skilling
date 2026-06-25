/*
 This file contains example sources and commands to create and run two Java modules:
 - com.utils  (exports com.utils)
 - com.greetings (requires com.utils)

 File layout (create these files under a project directory, e.g. modules-demo):

 modules-demo/
 ├─ com.utils/
 │  ├─ src/com.utils/module-info.java
 │  └─ src/com.utils/com/utils/Utils.java
 └─ com.greetings/
	├─ src/com.greetings/module-info.java
	└─ src/com.greetings/com/greetings/Main.java

module-info.java for com.utils (modules-demo/com.utils/src/com.utils/module-info.java):

-----
module com.utils {
	exports com.utils;
}
-----

Utils.java (modules-demo/com.utils/src/com.utils/com/utils/Utils.java):

-----
package com.utils;

public class Utils {
	public static String greet(String name) {
		return "Hello, " + name + "!";
	}
}
-----

module-info.java for com.greetings (modules-demo/com.greetings/src/com.greetings/module-info.java):

-----
module com.greetings {
	requires com.utils;
}
-----

Main.java (modules-demo/com.greetings/src/com.greetings/com/greetings/Main.java):

-----
package com.greetings;

import com.utils.Utils;

public class Main {
	public static void main(String[] args) {
		System.out.println(Utils.greet("World"));
	}
}
-----

Compile and run using module path (from modules-demo directory):

javac -d out/com.utils com.utils/src/com.utils/module-info.java com.utils/src/com.utils/com/utils/Utils.java
javac -p out -d out/com.greetings com.greetings/src/com.greetings/module-info.java com.greetings/src/com.greetings/com/greetings/Main.java

java -p out -m com.greetings/com.greetings.Main

This single file is only documentation/example. Create the files and folders as shown and run the commands above.
*/
public class CreateandUseJavaModules {
    // placeholder class - see file header for module examples and commands
}
