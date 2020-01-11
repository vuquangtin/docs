package errors.outofmemory.directmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class DirectOutOfMemory {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		List<Long> list = new ArrayList<Long>();
		int i = 0;
		while (i <= 30) {
			list.add(unsafe.allocateMemory(_1MB));
			i++;
			System.out.println(i);
		}
	}
}