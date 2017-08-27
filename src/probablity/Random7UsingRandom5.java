/*
 * Given a random generator random5(),
 * the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random7().
 */

package probablity;

public class Random7UsingRandom5 {
	public static int random5() {
		return (int)(Math.random() * 5);
	}
	
	public int random7() {
		while (true) {
			int rand = 5 * random5() + random5();
			if (rand < 21) {
				return rand % 7;
			}
		}
	}
}
