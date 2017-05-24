package pa4_8_puzzle;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private MinPQ<SearchNode> solutionPQ;
	private MinPQ<SearchNode> testPQ;		// to test if problem solvable
	private boolean solvable;
	private SearchNode solutionNode;
	
	// SearchNode class
	private class SearchNode {
		private final Board board;
		private final int numOfMoves;
		private final SearchNode prev;
		
		private SearchNode(Board board, int numOfMoves, SearchNode prev) {
			this.board = board;
			this.numOfMoves = numOfMoves;
			this.prev = prev;
		}
		
		private int getManhattanPriority() {
			return numOfMoves + board.manhattan();
		}
	}
	
	private class ManhattanPriorComparator implements Comparator<SearchNode> {
		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			if ((o1.board.manhattan() + o1.numOfMoves) < (o2.board.manhattan() + o2.numOfMoves))
				return -1;
			else if ((o1.board.manhattan() + o1.numOfMoves) > (o2.board.manhattan() + o2.numOfMoves))
				return 1;
			else return 0;
		}
	}
	
	/*
	 To detect unsolvable situations, run the A* algorithm on two puzzle instances—one with the initial 
	 board and one with the initial board modified by swapping a pair of blocks—in lockstep 
	 (alternating back and forth between exploring search nodes in each of the two game trees). 
	 Exactly one of the two will lead to the goal board.
	 */
    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
    	if (initial == null) throw new java.lang.NullPointerException();
    	solutionPQ = new MinPQ<SearchNode>(new ManhattanPriorComparator());
    	testPQ = new MinPQ<SearchNode>(new ManhattanPriorComparator());
    	solvable = false;
    	solutionNode = null;
    	
    	solutionPQ.insert(new SearchNode(initial, 0, null));
    	testPQ.insert(new SearchNode(initial.twin(), 0, null));
    	
    	while (true) {
    		SearchNode solution = solutionPQ.delMin();
    		SearchNode test = testPQ.delMin();
    		// System.out.println(solution.getManhattanPriority());
    		
    		if (solution.board.isGoal()) {
    			solutionNode = solution;
    			solvable = true;
    			break;
    		}
    		if (test.board.isGoal()) {
    			solvable = false;
    			break;
    		}
    		
    		for (Board board:solution.board.neighbors()) {
    			// critical optimization
    			if (solution.prev != null && board.equals(solution.prev.board)) continue;
    			solutionPQ.insert(new SearchNode(board, moves(solution) + 1, solution));
    		}
    		for (Board board:test.board.neighbors()) {
    			// critical optimization
    			if (test.prev != null && board.equals(test.prev.board)) continue;
    			testPQ.insert(new SearchNode(board, moves(test) + 1, test));
    		}
    	}
    }
    
    public boolean isSolvable() {           // is the initial board solvable?
    	return solvable;
    }
    
    // Backward counting to initial state for moves calculation
    private int moves(SearchNode node) {
    	int i = 0;
    	while (node.prev != null) {
    		i++;
    		node = node.prev;
    	}
    	return i;
    }
    
    public int moves()  {                   // min number of moves to solve initial board; -1 if unsolvable
    	if (!isSolvable()) return -1;
    	return solutionNode.numOfMoves;
    }
    
    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
    	if (!isSolvable()) return null;
    	Stack<Board> solutionStack = new Stack<Board>();
    	
    	SearchNode pointer = solutionNode;
    	while (pointer != null) {
    		solutionStack.push(pointer.board);
    		pointer = pointer.prev;
    	}
    	
    	return solutionStack;
    }
    
    public static void main(String[] args) { // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        // System.out.println(n);
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
        		// System.out.println(blocks[i][j]);
            }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
