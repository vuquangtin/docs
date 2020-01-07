# How to control Java heap size (memory) allocation (xmx, xms)

Java memory FAQ: How do I control the amount of memory my Java program uses (i.e., Java RAM usage)?

## The short answer

The short answer is that you use these Java command-line parameters to help control the RAM use of application:

* Use -Xmx to specify the maximum heap size
* Use -Xms to specify the initial Java heap size
* Use -Xss to set the Java thread stack size

Use this syntax to specify the amount of memory the JVM should use:

```txt
-Xms64m or -Xms64M
-Xmx1g or -Xmx1G
```

## The longer answer

As a bit of background, I’m running a Java application on a Raspberry Pi device where memory is limited. Unfortunately, every time I try to run the program I get this Java heap size error message:

>“Error occurred during initialization of VM. Could not reserve enough space for object heap. Could not create the Java virtual machine.”

I knew my program doesn’t need a lot of memory — it was just hitting a database and generating some files and reports — so I got around this memory limit problem by specifying the maximum Java heap size my program was allowed to allocate. In my case I didn’t think about it too hard and just chose a heap size limit of 64 MB RAM, and after I set this RAM limit my program ran fine.

Setting the maximum Java heap size (Xmx)

You set the maximum Java heap size of your program using the -Xmx option to the Java interpreter. To specifically limit your heap size to 64 MB the option should be specified like this:

```
-Xmx64m
```

Using that memory limit setting, the Java command I use in my shell script to start my Java program looks like this:

```
java -Xmx64m -classpath ".:${THE_CLASSPATH}" ${PROGRAM_NAME}
```

where THE_CLASSPATH and PROGRAM_NAME are variables set earlier in my script. (The important part here is the -Xmx64m portion of the command.)

## More Java memory-related command line arguments

You can find more options for controlling Java application memory use by looking at the output of the java -X command. Here's what the output of those commands looks like from my JVM:

```
$ java -X

-Xmixed           mixed mode execution (default)
-Xint             interpreted mode execution only
-Xbootclasspath:  set search path for bootstrap classes and resources 
	-Xbootclasspath/a:                   append to end of bootstrap class path 
	-Xbootclasspath/p:                   prepend in front of bootstrap class path 
	-Xnoclassgc       disable class garbage collection 
	-Xloggc:    log GC status to a file with time stamps
-Xbatch           disable background compilation
-Xms              set initial Java heap size
-Xmx              set maximum Java heap size
-Xss              set java thread stack size
-Xprof            output cpu profiling data
-Xfuture          enable strictest checks, anticipating future default
-Xrs              reduce use of OS signals by Java/VM (see documentation)
-Xdock:name=      override default application name displayed in dock 
	-Xdock:icon=                   override default icon displayed in dock 
	-Xcheck:jni       perform additional checks for JNI functions 
	-Xshare:off	      do not attempt to use shared class data 
	-Xshare:auto      use shared class data if possible (default) 
	-Xshare:on	      require using shared class data, otherwise fail.  
	The -X options are non-standard and subject to change without notice. 
```

From that list, the command-line arguments specifically related to Java application memory use are:

```
-Xnoclassgc   disable class garbage collection
-Xms          set initial Java heap size
-Xmx          set maximum Java heap size
-Xss          set java thread stack size
```

## Java heap size descriptions (xms, xmx, xmn)

Digging around, I just found this additional Java xms, xmx, and xmn information on Apple's web site:

```
-Xms size in bytes
Sets the initial size of the Java heap. 
The default size is 2097152 (2MB). 
The values must be a multiple of, and greater than, 1024 bytes (1KB).
(The -server flag increases the default size to 32M.)

-Xmn size in bytes
Sets the initial Java heap size for the Eden generation. 
The default value is 640K. 
(The -server flag increases the default size to 2M.)

-Xmx size in bytes
Sets the maximum size to which the Java heap can grow. 
The default size is 64M. 
(The -server flag increases the default size to 128M.) 
The maximum heap limit is about 2 GB (2048MB).
```

## Java memory arguments (xms, xmx, xmn) formatting

When setting the Java heap size, you should specify your memory argument using one of the letters “m” or “M” for MB, or “g” or “G” for GB. Your setting won’t work if you specify “MB” or “GB.” Valid arguments look like this:

* -Xms64m or -Xms64M
* -Xmx1g or -Xmx1G
* Can also use 2048MB to specify 2GB

Also, make sure you just use whole numbers when specifying your arguments. Using -Xmx512m is a valid option, but -Xmx0.5g will cause an error.

