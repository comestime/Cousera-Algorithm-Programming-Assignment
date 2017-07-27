/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

package dfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationWODup {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        helper(n, k, 0, 0, temp, result);
        return result;
    }
    
    private void helper(int n, int k, int index, int selected, List<Integer> temp, List<List<Integer>> result) {
        if (k == selected) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        if (index == n) {
            return;
        }
        
        // case 1: select nothing in this level
        helper(n, k, index + 1, selected, temp, result);
        
        // case2: select (index + 1) in this level
        temp.add(index + 1);
        helper(n, k, index + 1, selected + 1, temp, result);
        temp.remove(temp.size() - 1);
    }
    
    public static void main(String[] args) {
    	CombinationWODup solution = new CombinationWODup();
    	System.out.println(solution.combine(4, 2));
    }
}
