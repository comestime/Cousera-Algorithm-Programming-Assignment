/*
Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

Assumptions

The given array is not null or empty
K >= 2
Examples

A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
A = {2, 1}, K = 2, return []
 */

package voting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityNumIII {
	// Time = O(n * k); Space = O(k)
	public List<Integer> majority(int[] array, int k) {
		if (array == null) return null;
		
		// key: array element; value: number of occurrences
		Map<Integer, Integer> map = new HashMap<>();
		
		// step 1:
		List<Integer> temp = new ArrayList<>();
		for (int num : array) {
			Integer occ = map.get(num);
			if (occ != null) {
				map.put(num, occ + 1);
			} else {
				if (map.size() < k - 1) {
					map.put(num, 1);
				} else {
					for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
						if (entry.getValue() == 1) {
							temp.add(entry.getKey());
							// can't do concurrent removal during iteration
							// map.remove(entry.getKey());
						} else {
							map.put(entry.getKey(), entry.getValue() - 1);
						}
					}
					for (Integer key : temp) {
						map.remove(key);
					}
					temp.clear();
				}
			}
		}
		
		// step 2: iterate over the array, and count all frequencies of candidate from 1 to k - 1
		// to find which candidate has a counter that is larger than n * (1 / k)
		for (Integer key : map.keySet()) {
			map.put(key, 0);		// reset counters
		}
		
		for (int num : array) {
			Integer occ = map.get(num);
			if (occ == null) {
				continue;
			} else {
				map.put(num, occ + 1);
			}
		}
		
		List<Integer> result = new ArrayList<>();
		for (Integer key : map.keySet()) {
			Integer occ = map.get(key);
			if (occ > array.length / k) {
				result.add(key);
			}
		}
		Collections.sort(result);
		return result;
	}
}
