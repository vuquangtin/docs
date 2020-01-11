package errors.outofmemory;

/**
 * VM Args: -Xss2M
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class JavaVMStackOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}

	private void dontStop() {
		while (true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					dontStop();
				}
			});

			thread.start();
		}
	}
}