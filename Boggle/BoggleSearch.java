import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Anipra
 *
 */

public class BoggleSearch {
	
	static int size;
	String file;
	public static String[][] grid;
	String regex = "[A-Z]+";
	
	public BoggleSearch () {
		
	}
	
	/**
	 * 
	 * @param s size of the grid
	 * @param s1 the file passed to initialize the grid
	 */
	
	BoggleSearch(String s, String s1) throws Exception {
		try {
			size = Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			System.out.println("Should be a number!");
			System.exit(0);
		}
		file = s1; 	
		String line = null;
		StringTokenizer st;
		String token;
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		grid = new String[size][size];
		for(int i = 0; i < size; i++) {
			if(scanner.hasNextLine())
				line = scanner.nextLine();
			st = new StringTokenizer(line);
			for(int j = 0; j < size; j++) {
				if(st.hasMoreTokens()){
					token = st.nextToken();	
					if(token.matches(regex))
						grid[i][j] = token;
					else 
						throw new InvalidFileFormatException("Invalid Format");
				}
			}
		}

		scanner.close();
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if(args[2].equals("-c")) {
			BoggleSearch boggle = new BoggleSearch(args[0], args[3]);
			Boggle b = new 	Boggle(size);
			Storage s = new Storage();
			s.stackOrQueue(args[1]);
			b.find(grid);
			b.printWords();
		}
		else {
			System.out.println("GUI option is not available. Please pass third argument as -c....");
		}
	}
}
