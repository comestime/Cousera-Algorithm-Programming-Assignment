/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null) return result;
        if (nums.length == 0) {
        	result.add(new ArrayList<Integer>());
        	return result;
        }
        
        // map to track the number of occurrence of each element
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	if (map.containsKey(nums[i])) {
        		map.put(nums[i], map.get(nums[i]) + 1);
        	} else {
        		map.put(nums[i], 1);
        	}
        }
        List<Integer> distinctNum = new ArrayList<Integer>(map.keySet());
        List<Integer> temp = new ArrayList<Integer>();
        dfs(map, distinctNum, 0, temp, result);
        return result;
    }
    
    private void dfs(Map<Integer, Integer> map, List<Integer> distinctNum, int index, List<Integer> temp, List<List<Integer>> result) {
    	if (index == distinctNum.size()) {
    		result.add(new ArrayList<Integer>(temp));
    		return;
    	}
    	
    	int cur = distinctNum.get(index);
    	for (int i = 0; i <= map.get(cur); i++) {
    		if (i != 0) temp.add(cur);
    		dfs(map, distinctNum, index + 1, temp, result);
    	}
    	
    	// int cur has been added at most map.get(cur) times
		for (int i = 1; i <= map.get(cur); i++) {
			temp.remove(temp.size() - 1);
		}
    }
    
    public static void main(String[] args) {
    	SubsetWithDup solution = new SubsetWithDup();
    	int[] test = new int[] {1, 2, 2};
    	System.out.println(solution.subsetsWithDup(test));
    }
}
