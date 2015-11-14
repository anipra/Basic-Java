import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Anipra
 * 
 */

public class Boggle {

	HashSet<String> set = new HashSet<String>();
	Storage s = new Storage();
	BoggleDictionary bdict; 
	int Size;
	int count = 0;
	private final static int TRIED = 1;


	public Boggle(int s) {
		this.Size = s;
		try {
			bdict = new BoggleDictionary();
		} catch (Exception ioe) {
			System.err.println("error reading dictionary");
			System.exit(1);
		}
	}

	
	/**
	 * runs through the entire grid and calls search() method 
	 * which is the search algorithm 
	 * @param a grid
	 */
	public void find(String[][] a) {
		for (int x = 0; x < Size; x++) {
			for (int y = 0; y < Size; y++) {
				search(x, y, a);
			}
		}
	}

	
	/**
	 * 
	 * @param i row value for the first element
	 * @param j column value for first element
	 * @param a grid
	 */
	
	public void search(int i, int j, String[][] a) {
		String value = a[i][j];
		Move m = new Move(value, i, j);
		m.tried[i][j] = TRIED;
		s.store(m);
		
		while (s.isEmpty() == false) {

			Move curr = s.retrieve();
			int x = curr.row;
			int y = curr.col;
			String word = curr.str;
			
			if (word.length() >= 3) {
				if (bdict.contains(word)) {
					set.add(word);
				}
			}

			if (word.length() < (Size * Size) || word.length() < 17) {

				position(x - 1, y - 1, a, curr);// diagonally upper left
				position(x + 1, y - 1, a, curr);// diagonally lower left
				position(x, y - 1, a, curr); // left
				position(x - 1, y + 1, a, curr);// diagonally upper right
				position(x - 1, y, a, curr); // up
				position(x + 1, y + 1, a, curr);// diagonally lower right
				position(x + 1, y, a, curr); // down
				position(x, y + 1, a, curr); // right
			}
		}
	}

	
	/**
	 * this method stores the new position in the storage
	 * @param row row value of new position
	 * @param col column value of new position
	 * @param grid grid of letters
	 * @param curr move object containing previous row, column, string and tried array
	 */
	
	public void position(int row, int col, String[][] grid, Move curr) {
		
		if (validPosition(row, col, curr)) {
			String value = grid[row][col];
			String word = curr.str;
			word = word.concat(value);
			Move m = new Move(word, row, col,curr.tried);
			s.store(m);
		}
	}
	
	
	/**
	 * 
	 * @param row new row value
	 * @param col new column value
	 * @param curr move object containing previous row, column, string and tried array
	 * @return false if not valid position
	 */
	
	public boolean validPosition(int row, int col, Move curr) {
		boolean result = true;
	
		// check if cell is in the bounds of the matrix
		if (row >= 0 && row < curr.tried.length && col >= 0
				&& col < curr.tried[row].length) {
			// check if cell is not previously tried
			if (curr.tried[row][col] == TRIED) {
				result = false;
			}
		} else
			result = false;

		return result;
	}
	
	/**
	 * Prints the Hashset which contains the stored words
	 */

	public void printWords() {
		System.out.println("HashSet: ");

		// Use an Iterator to print each element of the TreeSet.
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next() + ", ");
		System.out.println();
	}

}
