# Catching java.lang.OutOfMemoryError?

There are a number of scenarios where you may wish to catch an OutOfMemoryError and in my experience (on Windows and Solaris JVMs), only very infrequently is OutOfMemoryError the death-knell to a JVM.

There is only one good reason to catch an OutOfMemoryError and that is to close down gracefully, cleanly releasing resources and logging the reason for the failure best you can (if it is still possible to do so).

In general, the OutOfMemoryError occurs due to a block memory allocation that cannot be satisfied with the remaining resources of the heap.

When the Error is thrown the heap contains the same amount of allocated objects as before the unsuccessful allocation and now is the time to drop references to run-time objects to free even more memory that may be required for cleanup. In these cases, it may even be possible to continue but that would definitely be a bad idea as you can never be 100% certain that the JVM is in a reparable state.

Demonstration that OutOfMemoryError does not mean that the JVM is out of memory in the catch block:

```java
private static final int MEGABYTE = (1024*1024);
public static void runOutOfMemory() {
    MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
    for (int i=1; i <= 100; i++) {
        try {
            byte[] bytes = new byte[MEGABYTE*500];
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
            long maxMemory = heapUsage.getMax() / MEGABYTE;
            long usedMemory = heapUsage.getUsed() / MEGABYTE;
            System.out.println(i+ " : Memory Use :" + usedMemory + "M/" + maxMemory + "M");
        }
    }
}
```

Output of this code:

```java
1 : Memory Use :0M/247M
..
..
..
98 : Memory Use :0M/247M
99 : Memory Use :0M/247M
100 : Memory Use :0M/247M

```
If running something critical, I usually catch the Error, log it to syserr, then log it using my logging framework of choice, then proceed to release resources and close down in a clean fashion. What's the worst that can happen? The JVM is dying (or already dead) anyway and by catching the Error there is at least a chance of cleanup.

The caveat is that you have to target the catching of these types of errors only in places where cleanup is possible. Don't blanket catch(Throwable t) {} everywhere or nonsense like that.

@see <a href='https://stackoverflow.com/questions/2679330/catching-java-lang-outofmemoryerror'>https://stackoverflow.com/questions/2679330/catching-java-lang-outofmemoryerror</a>