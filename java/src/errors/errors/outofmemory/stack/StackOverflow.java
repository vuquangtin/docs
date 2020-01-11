package errors.outofmemory.stack;

/**
 * 
 * VM Args: -Xss256k
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class StackOverflow {
	private int length;

	public void stackLeak() {
		length++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		StackOverflow sof = new StackOverflow();
		try {
			sof.stackLeak();
		} catch (Throwable e) {
			System.out.println("Stack depth : " + sof.length);
			throw e;
		}
	}
}