package pa4_8_puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board {
	private int dimension;
	private int[][] blocks;
	private int xIdx;		// x index of blank block
	private int yIdx;		// y index of blank block
	
	
	public Board(int[][] blocks) {          // construct a board from an n-by-n array of blocks
											// (where blocks[i][j] = block in row i, column j)
		dimension = blocks.length;
		this.blocks = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++) {
				if (blocks[i][j] == 0) {
					xIdx = i;
					yIdx = j;
				}
				this.blocks[i][j] = blocks[i][j];
			}
	}
	
    public int dimension() {                // board dimension
    	return dimension;
    }
    
    public int hamming() {                  // number of blocks out of place
    	int n = 0;
    	for (int i = 0; i < dimension; i++)
    		for (int j = 0; j < dimension; j++) {
    			if (blocks[i][j] == 0) continue;
    			if (blocks[i][j] != (i * dimension + j + 1)) n++;
    		}
    	return n;
    }
    
    public int manhattan() {                // sum of Manhattan distances between blocks and goal
    	int n = 0;
    	for (int i = 0; i < dimension; i++)
    		for (int j = 0; j < dimension; j++) {
    			if (blocks[i][j] == 0) continue;
    			// the blocks[i][j] should located in (row, col)
    			int row = (blocks[i][j] - 1) / dimension;
    			int col = (blocks[i][j] - 1) % dimension;
    			n += (Math.abs(row - i) + Math.abs(col - j));
    		}
    	return n;
    }
    
    public boolean isGoal() {               // is this board the goal board?
    	for (int i = 0; i < dimension; i++) 
    		for (int j = 0; j < dimension; j++) {
    			if (blocks[i][j]  == 0) continue;
    			if (blocks[i][j] != (i * dimension + j + 1)) return false;
    		}
    	return true;
    }
    
    private void swap(int[][] a, int x1, int y1, int x2, int y2) {
    	int temp = a[x1][y1];
    	a[x1][y1] = a[x2][y2];
    	a[x2][y2] = temp;
    }
    
    private int[][] copyBlocks() {
    	int[][] newBlocks = new int[dimension][dimension];
    	for (int i = 0; i < dimension; i++)
    		for (int j = 0; j < dimension; j++)
    			newBlocks[i][j] = blocks[i][j];
    	return newBlocks;
    }
    
    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
    	int[][] twinBlocks = copyBlocks();
    	// make sure never swap the blank block
    	if (xIdx == 0) swap(twinBlocks, dimension - 1, dimension - 1, dimension - 1, dimension - 2);
    	else if (xIdx == (dimension - 1)) swap(twinBlocks, 0, dimension - 1, 0, dimension - 2);
    	else if (yIdx == 0) swap(twinBlocks, 0, dimension - 1, 1, dimension - 1);
    	else if (yIdx == (dimension - 1)) swap(twinBlocks, 0, 0, 1, 0);
    	else swap(twinBlocks, 0, 0, 0, 1);
    	return new Board(twinBlocks);
    }
    
    public boolean equals(Object y) {       // does this board equal y?
    	if (y == this) return true;
    	if (y == null) return false;
    	if (y.getClass() != this.getClass()) return false;
    	
    	Board that = (Board) y;
    	if (this.dimension != that.dimension) return false;
    	for (int i = 0; i < dimension; i++)
    		for (int j = 0; j < dimension; j++) {
    			if (this.blocks[i][j] != that.blocks[i][j]) return false;
    		}
    	return true;
    }
    
    public Iterable<Board> neighbors() {    // all neighboring boards
    	Queue<Board> queue = new Queue<Board>();
    	if (xIdx > 0) {
    		int[][] newBlocks = copyBlocks();
    		swap(newBlocks, xIdx, yIdx, xIdx - 1, yIdx);
    		queue.enqueue(new Board(newBlocks));
    	}
    	if (xIdx < dimension - 1) {
    		int[][] newBlocks = copyBlocks();
    		swap(newBlocks, xIdx, yIdx, xIdx + 1, yIdx);
    		queue.enqueue(new Board(newBlocks));
    	}
    	if (yIdx > 0) {
    		int[][] newBlocks = copyBlocks();
    		swap(newBlocks, xIdx, yIdx, xIdx, yIdx - 1);
    		queue.enqueue(new Board(newBlocks));
    	}
    	if (yIdx < dimension - 1) {
    		int[][] newBlocks = copyBlocks();
    		swap(newBlocks, xIdx, yIdx, xIdx, yIdx + 1);
    		queue.enqueue(new Board(newBlocks));
    	}
    	return queue;
    }
    
    public String toString() {              // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
    	// unit tests (not graded)
    }
}
