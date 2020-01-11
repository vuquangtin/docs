package errors.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class HeapOOM {
	static class OOMObject {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<OOMObject> list = new ArrayList<OOMObject>();
		long i = 0;
		while (true) {
			System.out.println(++i);
			list.add(new OOMObject());
		}
	}

}

/*
**
*NOTE: "Java heap space"
 java.lang.OutOfMemoryError: Java heap space
 Dumping heap to java_pid19454.hprof ...
 Heap dump file created [27534792 bytes in 0.081 secs]
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at HeapOOM.main(HeapOOM.java:21)
*/