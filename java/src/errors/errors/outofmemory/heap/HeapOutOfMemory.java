package errors.outofmemory.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:+HeapOnOutOfMemoryError
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class HeapOutOfMemory {
	public void heapLeak() {
		List list = new ArrayList();
		while (true) {
			list.add(new int[100000]);
		}
	}

	public void xxx() {

	}

	public static void main(String[] args) {
		HeapOutOfMemory hoom = new HeapOutOfMemory();
		hoom.heapLeak();
	}
}