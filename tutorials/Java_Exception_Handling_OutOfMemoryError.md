# Java Exception Handling – OutOfMemoryError

Making our way through our detailed Java Exception Handling series, today we’ll be going over the OutOfMemoryError, which is thrown when the Java Virtual Machine (JVM) is unable to allocate an object due to lack of memory.

In this article we’ll explore the OutOfMemoryError in more detail, starting with where it sits in the larger Java Exception Hierarchy. We’ll then take a look at some fully-functional Java code samples that will illustrate how memory allocation works, and how improper memory management might lead to OutOfMemoryErrors in your own code, so let’s get started!

## The Technical Rundown

All Java errors implement the java.lang.Throwable interface, or are extended from another inherited class therein. The full exception hierarchy of this error is:

---->java.lang.Object
-------->java.lang.Throwable
------------->java.lang.Error
------------------>java.lang.VirtualMachineError
----------------------->OutOfMemoryError

## Full Code Sample

Below is the full code sample we’ll be using in this article. It can be copied and pasted if you’d like to play with the code yourself and see how everything works.

```java
package errors.outofmemory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * 
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class Main {
	private static int sleepPeriod = 0x40;
	private static int minStrSize = 0x100;
	private static int maxStrSize = 0x400;
	private static int minStrsQty = 0x400;
	private static int maxStrsQty = 0x800;
	private static int replaceProbability = 0x07;
	private static ArrayList<String> arrayList = new ArrayList<>();
	private static Random rand = new Random();
	private static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws InterruptedException {
		waitToStart();

		while (true) {
			sleep(sleepPeriod);

			for (int i = minStrsQty + rand.nextInt(maxStrsQty - minStrsQty + 1); i > 0; --i) {
				if (arrayList.size() > 0
						&& rand.nextInt(replaceProbability) == 0) {
					arrayList.set(rand.nextInt(arrayList.size()), newString());
				} else {
					arrayList.add(newString());
				}
			}
		}
	}

	private static String newString() {
		stringBuilder.setLength(0);

		for (int i = minStrSize + rand.nextInt(maxStrSize - minStrSize + 1); i > 0; --i) {
			stringBuilder.append((rand.nextInt(2) == 0 ? 'a' : 'A')
					+ (char) rand.nextInt(26));
		}

		return stringBuilder.toString();
	}

	private static void waitToStart() {
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}

```

```java
package errors.outofmemory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * 
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class Main {
	private static int sleepPeriod = 0x40;
	private static int minStrSize = 0x100;
	private static int maxStrSize = 0x400;
	private static int minStrsQty = 0x400;
	private static int maxStrsQty = 0x800;
	private static int replaceProbability = 0x07;
	private static ArrayList<String> arrayList = new ArrayList<>();
	private static Random rand = new Random();
	private static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws InterruptedException {
		waitToStart();

		while (true) {
			sleep(sleepPeriod);

			for (int i = minStrsQty + rand.nextInt(maxStrsQty - minStrsQty + 1); i > 0; --i) {
				if (arrayList.size() > 0
						&& rand.nextInt(replaceProbability) == 0) {
					arrayList.set(rand.nextInt(arrayList.size()), newString());
				} else {
					arrayList.add(newString());
				}
			}
		}
	}

	private static String newString() {
		stringBuilder.setLength(0);

		for (int i = minStrSize + rand.nextInt(maxStrSize - minStrSize + 1); i > 0; --i) {
			stringBuilder.append((rand.nextInt(2) == 0 ? 'a' : 'A')
					+ (char) rand.nextInt(26));
		}

		return stringBuilder.toString();
	}

	private static void waitToStart() {
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}
```


## When Should You Use It?

To understand why an OutOfMemoryError might occur, we must first briefly examine Java’s memory management scheme. When the Java Virtual Machine (JVM) first launches, it sets aside a chunk of heap memory (commonly referred to simply as the heap). A heap is an area of memory that the JVM can use to store newly allocated objects. Any object within the heap that is referenced by another object is considered active, which forces that object to remain in the heap for the duration if its lifespan (i.e. while it remains referenced). Once an object is no longer referenced it is considered garbage, and the garbage collector will reclaim the memory that the object had previously required.

The size of the heap depends on two factors, which can be controlled by commmand-line options when launching Java. -Xms is used to set the initial heap size, which is the initial amount of memory the application allocates to the heap. The -Xmx flag is used to set the maximum heap size, which, as the name suggests, specifies just how many bytes the heap is allocated in total. In short, the amount of heap memory given to the application will impact how many objects, and of what size, can be allocated at once before running into issues.

In addition to heap memory, the JVM also manages another set of memory called non-heap memory. As the name suggests, this is memory that isn’t part of the heap used to store objects, but, instead, is used to store class structures pools, field data, method data, and the executing code of said methods and constructors. This non-heap memory pool can be adjusted, but is initially created when the JVM starts.

Now that we know a bit more about how memory limitations in Java are set and attributed, let’s take a look at some sample code. We’ll start with the allocateMemory(long bytes) method, which holds the majority of the logic that we’ll be using in this example:

```java
/**
* Attempts to allocate memory of the given number of bytes.
*
* @param bytes Number of bytes to allocate.
*/
private static void allocateMemory(long bytes) {
    try {
        Logging.log(String.format("Attempting to allocate %s bytes of memory.", formatNumber(bytes)));
        // Check if bytes exceeds maximum bytes in array.
        if (bytes > MAX_ARRAY_SIZE) {
            // Determine number of memory chunks contained in bytes.
            long chunks = getArrayChunkCount(bytes);
            // Get remainder after chunking.
            int remainder = (int) (bytes - MAX_ARRAY_SIZE * chunks);
            // Two-dimensional array containing an array of MAX_ARRAY_SIZE per chunk.
            byte\[]\[] chunkedByteArray = new byte[(int) chunks]\[MAX_ARRAY_SIZE];
            // Remainder array.
            byte[] remainingByteArray = new byte[remainder];
        } else {
            // Array of bytes bytes.
            byte[] byteArray = new byte[(int) bytes];
        }
        // Output memory usage info.
        printMemoryUsage();
        Logging.log(String.format("SUCCESSFULLY allocated %s bytes of memory.", formatNumber(bytes)));
    } catch (OutOfMemoryError error) {
        Logging.log(String.format("FAILED to allocate %s bytes of memory.", formatNumber(bytes)));
        // Output expected OutOfMemoryErrors.
        Logging.log(error);
    } catch (Exception | Error exception) {
        Logging.log(String.format("FAILED to allocate %s bytes of memory.", formatNumber(bytes)));
        // Output unexpected Exceptions/Errors.
        Logging.log(exception, false);
    }
}
```

The allocateMemory(long bytes) method attempts to allocate memory equivalent to the number of passed bytes. We do so by initializing byte[] arrays of equal size to the number of bytes. However, since Java limits the size of arrays to slightly under the maximum size of an Integer, if the passed bytes parameter exceeds this maximum, we need to create a series of arrays to properly allocate all memory. The MAX_ARRAY_SIZE constant defines the largest array size we can use:

```java
// Maximum allowed array size in current JVM.
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 2;
```

With that, we determine how many “chunks” (maximized arrays) we can fit within the bytes parameter by passing it to the getArrayChunkCount(long bytes) method:

```java
/**
* Gets the number of maximum-sized arrays that can
* be created within the passed number of bytes.
*
* @param bytes Total number of bytes.
* @return Number of maximum-sized arrays.
*/
private static long getArrayChunkCount(long bytes) {
    return (bytes / MAX_ARRAY_SIZE);
}
```
Once determined, we create a two-dimensional array with the first dimension size equal to the number of chunks, and the second dimension equal to MAX_ARRAY_SIZE. Finally, we determine the remainder after chunking and allocate that to its own single-dimension array.

If allocation was successful (i.e. no errors occurred), we output a success message to the log. Otherwise, we output a failed message and the expected OutOfMemoryError.

During processing, we also call the printMemoryUsage() method, which uses the ManagementFactory class to retrieve heap and non-heap memory usage data:

```java
/**
* Prints current memory usage stats.
*/
private static void printMemoryUsage() {
    try {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemory = memoryBean.getNonHeapMemoryUsage();
        String format = "%-15s%-15s%-15s%-15s";

        Logging.lineSeparator("HEAP MEMORY");
        Logging.log(String.format(format, "COMMITTED", "INIT", "USED", "MAX"));
        Logging.log(String.format(format,
                formatNumber(heapMemory.getCommitted()),
                formatNumber(heapMemory.getInit()),
                formatNumber(heapMemory.getUsed()),
                formatNumber(heapMemory.getMax()))
        );

        Logging.lineSeparator("NON-HEAP MEMORY");
        Logging.log(String.format(format, "COMMITTED", "INIT", "USED", "MAX"));
        Logging.log(String.format(format,
                formatNumber(nonHeapMemory.getCommitted()),
                formatNumber(nonHeapMemory.getInit()),
                formatNumber(nonHeapMemory.getUsed()),
                formatNumber(nonHeapMemory.getMax()))
        );
    } catch (OutOfMemoryError error) {
        // Output expected OutOfMemoryErrors.
        Logging.log(error);
    } catch (Exception | Error exception) {
        // Output unexpected Exceptions/Errors.
        Logging.log(exception, false);
    }
}

/**
    * Formats passed number as comma-delimited String.
    *
    * @param number Number to format.
    * @return Comma-delimited String.
    */
private static String formatNumber(long number) {
    return NumberFormat.getInstance().format(number);
}
```
With the help of the formatNumber(long number) method, we’re able to cleanly output the current memory usage during each memory allocation attempt.

Alright, with everything setup we can test this out by intentionally allocating various amounts of memory. Our Main.main(String[] args) method does so by passing increasing numeric values to allocateMemory(long bytes):

```java
public static void main(String[] args) {
    // Minute value.
    long bytes = 24601;
    Logging.lineSeparator(formatNumber(bytes), '=');
    allocateMemory(bytes);

    // ...
}
```
This produces the following output, showing the attempt to allocate, the current memory usage stats, and the success message:

```java
================ 24,601 ================
Attempting to allocate 24,601 bytes of memory.

------------- HEAP MEMORY --------------
COMMITTED      INIT           USED           MAX            
257,425,408    268,435,456    4,026,592      3,803,185,152  
----------- NON-HEAP MEMORY ------------
COMMITTED      INIT           USED           MAX            
8,060,928      2,555,904      5,295,344      -1

SUCCESSFULLY allocated 24,601 bytes of memory.
```

Even with a small allocation amount of 24,601, we can see some interesting data from the memory usage stats. Particularly, notice that the heap memory used is only about 4 MB, which is even less than the non-heap memory used to store the application code.

Let’s bump it up a bit to 10,000,000:

```java
// Ten million.
bytes = 10000000;
Logging.lineSeparator(formatNumber(bytes), '=');
allocateMemory(bytes);

============== 10,000,000 ==============
Attempting to allocate 10,000,000 bytes of memory.

------------- HEAP MEMORY --------------
COMMITTED      INIT           USED           MAX            
257,425,408    268,435,456    14,026,608     3,803,185,152  
----------- NON-HEAP MEMORY ------------
COMMITTED      INIT           USED           MAX            
8,060,928      2,555,904      5,311,528      -1          

SUCCESSFULLY allocated 10,000,000 bytes of memory.
```
Nothing else has really changed except for the heap memory used, so it can accommodate the 10 MB we’ve allocated.

Here we’ll try the maximum Integer size, along with a long value that is an order of magnitude larger than that:

```java
// Max integer value.
bytes = 2147483647;
Logging.lineSeparator(formatNumber(bytes), '=');
allocateMemory(bytes);

// Medium value.
bytes = 2500000000L;
Logging.lineSeparator(formatNumber(bytes), '=');
allocateMemory(bytes);

============ 2,147,483,647 =============
Attempting to allocate 2,147,483,647 bytes of memory.

------------- HEAP MEMORY --------------
COMMITTED      INIT           USED           MAX            
2,405,433,344  268,435,456    2,161,510,272  3,803,185,152  
----------- NON-HEAP MEMORY ------------
COMMITTED      INIT           USED           MAX            
8,060,928      2,555,904      5,330,352      -1          

SUCCESSFULLY allocated 2,147,483,647 bytes of memory.

============ 2,500,000,000 =============
Attempting to allocate 2,500,000,000 bytes of memory.

------------- HEAP MEMORY --------------
COMMITTED      INIT           USED           MAX            
2,986,868,736  268,435,456    2,500,485,304  3,803,185,152  
----------- NON-HEAP MEMORY ------------
COMMITTED      INIT           USED           MAX            
8,060,928      2,555,904      5,341,936      -1          

SUCCESSFULLY allocated 2,500,000,000 bytes of memory.
```

Now we’re starting to really see things ramp up by using over 2 GB of memory. Even the heap memory committed quantity has to be increased, which is essentially the combined amount of working heap memory, plus current memory stored in garbage collection. Thus, the committed amount will (almost) always exceed the actual used heap memory value.

Finally, let’s try somewhere around 3 GB and an excessively large 10 PB to see what happens:

```java
// Large value.
bytes = 3000000000L;
Logging.lineSeparator(formatNumber(bytes), '=');
allocateMemory(bytes);

// Extreme value.
bytes = 9999999999999999L;
Logging.lineSeparator(formatNumber(bytes), '=');
allocateMemory(bytes);

============ 3,000,000,000 =============
Attempting to allocate 3,000,000,000 bytes of memory.
FAILED to allocate 3,000,000,000 bytes of memory.
[EXPECTED] java.lang.OutOfMemoryError: Java heap space

======== 9,999,999,999,999,999 =========
Attempting to allocate 9,999,999,999,999,999 bytes of memory.
FAILED to allocate 9,999,999,999,999,999 bytes of memory.
[EXPECTED] java.lang.OutOfMemoryError: Java heap space
```
The 3 GB allocation finally puts us over the top and throws an OutOfMemoryError, indicating that the Java heap space has been exceeded. This indicates that our total memory usage has exceeded the heap memory max value of ~3.8 GB. Unsurprisingly then, the attempt to allocate 10 petabytes also slightly exceeds my current JVM limitations by just a bit.

The Airbrake-Java library provides real-time error monitoring and automatic exception reporting for all your Java-based projects. Tight integration with Airbrake’s state of the art web dashboard ensures that Airbrake-Java gives you round-the-clock status updates on your application’s health and error rates. Airbrake-Java easily integrates with all the latest Java frameworks and platforms like Spring, Maven, log4j, Struts, Kotlin, Grails, Groovy, and many more. Plus, Airbrake-Java allows you to easily customize exception parameters and gives you full, configurable filter capabilities so you only gather the errors that matter most.

Check out all the amazing features Airbrake-Java has to offer and see for yourself why so many of the world’s best engineering teams are using Airbrake to revolutionize their exception handling practices!


@see 
https://airbrake.io/blog/java-exception-handling/outofmemoryerror