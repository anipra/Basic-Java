
/**
 * @author Anipra
 *
 */
public class Move {
	String str = null;
	int row = 0;
	int col = 0 ;
	int tried[][]; 
	int size;
	
	private final static int TRIED = 1;

	public Move(String value, int row, int col) {
		this.str = value;
		this.row = row;
		this.col = col;
		this.size = BoggleSearch.size;
		this.tried = new int[size][size];
		initialize(size);
	}
	
	/**
	 * 
	 * @param s string value stored in the storage
	 * @param row row value stored in the storage
	 * @param col column value stored in the storage
	 * @param use array which contains the tried positions
	 */
	public Move(String s,int row, int col, int[][] use) {
		this.str = s;
		this.row = row;
		this.col = col;
		this.size = BoggleSearch.size;
		this.tried = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++){
				if(use[i][j] == TRIED)
					tried[i][j] = TRIED;
				else
					tried[i][j] = 0;
			}
		}
		this.tried[row][col] = TRIED;
		
		
	}
	
	
	/**
	 * this method initializes the tried array
	 * @param size of the grid to initialize the tried array
	 */
	public void initialize(int size) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++){
				tried[i][j] = 0;
			}
		}
	}
}
