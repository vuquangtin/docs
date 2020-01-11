package errors.outofmemory.perm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class PermOutOfMemory {
	public void permLeak() {
		List list = new ArrayList();
		int i = 0;
		while (true) {
			list.add(String.valueOf("xxx" + i++).intern());
		}
	}

	public static void main(String[] args) {
		PermOutOfMemory poom = new PermOutOfMemory();
		poom.permLeak();
	}
}