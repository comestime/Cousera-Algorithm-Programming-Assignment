/*
Given a random generator random5(), 
the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random1000()
 */

package probablity;

public class Random1000UsingRandom5 {
	public int random1000() {
		while (true) {
			int rand = 0;
			for (int i = 0; i < 5; i++) {
				rand = rand * 5 + Random7UsingRandom5.random5();
			}
			if (rand < 3000) {
				return rand % 1000;
			}
		}
	}
}
