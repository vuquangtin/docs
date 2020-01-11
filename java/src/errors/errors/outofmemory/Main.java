package errors.outofmemory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * 
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class Main {
	private static int sleepPeriod = 0x40;
	private static int minStrSize = 0x100;
	private static int maxStrSize = 0x400;
	private static int minStrsQty = 0x400;
	private static int maxStrsQty = 0x800;
	private static int replaceProbability = 0x07;
	private static ArrayList<String> arrayList = new ArrayList<>();
	private static Random rand = new Random();
	private static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws InterruptedException {
		waitToStart();

		while (true) {
			sleep(sleepPeriod);

			for (int i = minStrsQty + rand.nextInt(maxStrsQty - minStrsQty + 1); i > 0; --i) {
				if (arrayList.size() > 0
						&& rand.nextInt(replaceProbability) == 0) {
					arrayList.set(rand.nextInt(arrayList.size()), newString());
				} else {
					arrayList.add(newString());
				}
			}
		}
	}

	private static String newString() {
		stringBuilder.setLength(0);

		for (int i = minStrSize + rand.nextInt(maxStrSize - minStrSize + 1); i > 0; --i) {
			stringBuilder.append((rand.nextInt(2) == 0 ? 'a' : 'A')
					+ (char) rand.nextInt(26));
		}

		return stringBuilder.toString();
	}

	private static void waitToStart() {
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}
