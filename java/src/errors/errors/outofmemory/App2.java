package errors.outofmemory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class App2 {

	public static void main(String[] args) {

		System.out.println("Run its gonna blow!");
		tooManyThreads();
	}

	private static void tooManyThreads() {

		final AtomicInteger i = new AtomicInteger(0);
		while (true) {

			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println(" thread" + i.incrementAndGet());
						Thread.sleep(10000000);
					} catch (InterruptedException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}).start();
		}

	}
}