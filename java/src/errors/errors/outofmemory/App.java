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
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();

		while (true) {
			arrayList.add("cos");
		}
	}
}