/*
Given an array with duplicated elements, find all possible combinations with size of k

hint: use a similar approach to solve subset problem with duplicated elements

For example,
If array = [1, 1, 2, 3] and k = 2, the solution is:

[
  [1,1],
  [1,2],
  [1,3],
  [2,3]
]
*/

package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationWDup {
	   public List<List<Integer>> combine(int[] array, int k) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        List<Integer> temp = new ArrayList<Integer>();
	        if (array == null) return result;
	        if (array.length == 0) {
	        	result.add(temp);
	        	return result;
	        }
	        // step 1, use a hash map to deduplication
	        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	        for (int i = 0; i < array.length; i++) {
	        	if (map.containsKey(array[i])) {
	        		map.put(array[i], map.get(array[i]) + 1);
	        	} else {
	        		map.put(array[i], 1);
	        	}
	        }
	        List<Integer> distinctNums = new ArrayList<Integer>(map.keySet());
	        
	        // step 2, perform combination
	        helper(map, k, distinctNums, 0, 0, temp, result);
	        return result;
	    }
	    
	    private void helper(Map<Integer, Integer> map, int k, List<Integer> distinctNums, int index, 
	    					int selected, List<Integer> temp, List<List<Integer>> result) {
	    	// if already select k elements, find a result and return
	        if (k == selected) {
	            result.add(new ArrayList<Integer>(temp));
	            return;
	        }
	        // if in the last level but no result found, simply return
	        if (index == distinctNums.size()) {
	            return;
	        }
	        
	        // for each distinct element with an occurrence of "cur" times
	        // it can present in the result from 0 to k - selected times
	        int cur = map.get(distinctNums.get(index));
	        for (int i = 0; i <= Math.min(cur, k - selected); i++) {
	        	if (i != 0) temp.add(distinctNums.get(index));
	        	// System.out.println(index + " : " + temp);
	        	helper(map, k, distinctNums, index + 1, selected + i, temp, result);
	        }
	        
        	// temp is a container, don't forget to remove the added elements
        	for (int i = 1; i <= Math.min(cur, k - selected); i++) {
        		temp.remove(temp.size() - 1);
        	}
	    }
	    
	    public static void main(String[] args) {
	    	CombinationWDup solution = new CombinationWDup();
	    	int[] test = new int[] {1, 1, 2, 3};
	    	System.out.println(solution.combine(test, 2));
	    }
}
