package errors.outofmemory.stack;

/**
 * VM Args: -Xss128M -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class StackOutOfMemory {
	public void dontStop() {
		System.out.println(Thread.currentThread().getName() + "start!");
		while (true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		StackOutOfMemory soom = new StackOutOfMemory();
		soom.stackLeakByThread();
	}
}