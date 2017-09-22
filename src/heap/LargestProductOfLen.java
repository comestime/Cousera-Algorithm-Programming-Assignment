/*
Given a dictionary containing many words,
find the largest product of two words’ lengths, 
such that the two words do not share any common characters.
Words are sorted according to length

Assumptions

The words only contains characters of 'a' to 'z'
The dictionary is not null and does not contains null string, and has at least two strings
If there is no such pair of words, just return 0
Examples

dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
 */

package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class LargestProductOfLen {
	public int largestProduct(String[] dict) {
		// return largest1(dict);
		return largest2(dict);
	}
	
	// method 1: for-for loop
	private int largest1(String[] dict) {
		int globalMax = 0;
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < dict.length - 1; i++) {
			for (int j = i + 1; j < dict.length; j++) {
				set.clear();
				for (int k = 0; k < dict[i].length(); k++) {
					set.add(dict[i].charAt(k));
				}
				for (int k = 0; k < dict[j].length(); k++) {
					if (set.contains(dict[j].charAt(k))) {
						break;
					} else if (k == dict[j].length() - 1) {
						globalMax = Math.max(globalMax, dict[i].length() * dict[j].length());
					}
				}
			}
		}
		
		return globalMax;
	}
	
	// method 2: best first search
	private int largest2(String[] dict) {
		// sort the string array first
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (o1.length() == o2.length()) {
					return 0;
				}
				return o1.length() < o2.length() ? 1 : -1;
			}		
		});
		
		int globalMax = 0;
		Queue<Cell> queue = new PriorityQueue<>();
		// set for deduplication during expansion
		Set<Cell> set = new HashSet<>();
		// set for check if common chars exist
		Set<Character> set1 = new HashSet<>();
		Cell cur = new Cell(0, 1, dict[0].length() * dict[1].length());
		queue.add(cur);
		set.add(cur);
		
		while (!queue.isEmpty()) {
			cur = queue.poll();
			
			// perform the expansion first
			if (cur.idx1 + 1 < cur.idx2 && cur.idx1 < dict.length) {
				Cell temp = new Cell(cur.idx1 + 1, cur.idx2, dict[cur.idx1 + 1].length() * dict[cur.idx2].length());
				set.add(temp);
				queue.add(temp);
			}
			if (cur.idx2 + 1 < dict.length) {
				Cell temp = new Cell(cur.idx1, cur.idx2 + 1, dict[cur.idx1].length() * dict[cur.idx2 + 1].length());
				set.add(temp);
				queue.add(temp);
			}
			
			// check if common chars exist for current string pair
			set1.clear();
			for (int i = 0; i < dict[cur.idx1].length(); i++) {
				set1.add(dict[cur.idx1].charAt(i));
			}
			for (int i = 0; i < dict[cur.idx2].length(); i++) {
				if (set1.contains(dict[cur.idx2].charAt(i))) {
					break;
				} else if (i == dict[cur.idx2].length() - 1) {
					globalMax = cur.product;
					return globalMax;
				}
			}
		}
		
		return globalMax;
	}
	
	// use a inner-class to represent the string pair
	private class Cell implements Comparable<Cell>{
		private int idx1;
		private int idx2;
		private int product;
		
		private Cell(int idx1, int idx2, int product) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.product = product;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + idx1;
			result = prime * result + idx2;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (idx1 != other.idx1)
				return false;
			if (idx2 != other.idx2)
				return false;
			return true;
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (product == o.product) return 0;
			// used in max heap
			return product > o.product ? -1 : 1;
		}


		private LargestProductOfLen getOuterType() {
			return LargestProductOfLen.this;
		}
		
	}
}
