package errors.outofmemory;

/**
 * 
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class OutOfMemorySimulator {
	public static void main(String[] args) {
		new OutOfMemorySimulator().generateOutOfMemory();
	}

	public void generateOutOfMemory() {
		int increment = 20;
		System.out.println("\n=================> OutOfMemoryTest Start\n");
		for (int it = 1; it < 20; it++) {
			System.out.println("Iteration " + it + " Free Memory: "
					+ Runtime.getRuntime().freeMemory());
			int loop = 2;
			int[] outOfMemoryArray = new int[increment];
			// feel outOfMemoryArray array in loop..
			do {
				outOfMemoryArray[loop] = 0;
				loop--;
			} while (loop > 0);
			increment = increment * 5;
			System.out.println("\nRequired Memory for next loop: " + increment);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
