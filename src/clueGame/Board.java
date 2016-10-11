package clueGame;

import java.util.*;
import java.io.*;

public class Board {
	private int startCell;
	private int pathLength;

	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Map<Character, String> legendMap = new HashMap<Character, String>();
	Set<BoardCell> visited;
	Set<BoardCell> targets;
	private final int MAX_BOARD_SIZE = 100;
	String layoutFile;
	String legendFile;
	String[][] board = new String[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	private BoardCell[][] grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	int NUM_ROWS;
	int NUM_COLS;
	String[] fullLegend = null;

	// variable used for singleton pattern
	private static Board theInstance = new Board();

	// ctor is private to ensure only one can be created
	private Board() {
	}

	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public Board(int height, int width) {
		super();
		this.grid = new BoardCell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = new BoardCell(i, j, null);

			}
		}
		calcAdjacencies();
	}

	public void calcAdjacencies() {

		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				Set<BoardCell> adjSet = new HashSet<BoardCell>();
				if (i > 0) {
					adjSet.add(getCellAt(i - 1, j));
				}
				if (i < grid.length - 1) {
					adjSet.add(getCellAt(i + 1, j));
				}
				if (j > 0) {
					adjSet.add(getCellAt(i, j - 1));
				}
				if (j < grid[i].length - 1) {
					adjSet.add(getCellAt(i, j + 1));
				}
				adjMatrix.put(grid[i][j], adjSet);
			}
		}

	}

	public void calcTargets(BoardCell cell, int pathLength) {
		this.targets = new HashSet<BoardCell>();
		this.visited = new HashSet<BoardCell>();
		visited.add(cell);
		findAllTargets(cell, pathLength);
	}

	private void findAllTargets(BoardCell cell, int pathLength) {
		for (BoardCell c : this.adjMatrix.get(cell)) {
			if (this.visited.contains(c)) {
				continue;
			}
			if (pathLength == 1) {
				this.targets.add(c);
			} else {
				this.visited.add(c);
				findAllTargets(c, pathLength - 1);
				this.visited.remove(c);
			}
		}
	}

	//// AutoGenerated might need to be changed
	public void initialize() {
		try {
			loadBoardConfig();
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadRoomConfig() throws BadConfigFormatException {
		BufferedReader fileReader = null;

		// This File reader, reads the file and puts it into a map
		final String DELIMITERTWO = ", ";
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(legendFile));

			int location = 0;
			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {

				// Get all tokens available in line
				String[] legend = line.split(DELIMITERTWO);
				location++;
				// This grabs all the letters for the keys
				for (int i = 0; i < legend.length; i = i + 3) {
					Character key = legend[i].charAt(0);
					legendMap.put(key, legend[i + 1]);
				}
			}

			checkLegend();
		} catch (Exception e) {
			throw new BadConfigFormatException("Some Exception", e);
		}
		{
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void checkLegend() throws BadConfigFormatException {

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {

				char letter = grid[i][j].getInitial();

				if (!legendMap.containsKey(letter)) {
					throw new BadConfigFormatException("Does not contain the Legend key: " + letter);
				}
			}
		}

	}

	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		BufferedReader fileReader = null;
		// Delimiter used in CSV file
		final String DELIMITER = ",";

		try {
			FileReader readerTwo = new FileReader(layoutFile);
			Scanner in = new Scanner(readerTwo);
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(layoutFile));

			// This while loop detects the number of rows.
			int value = 0;
			String word = "";
			while (in.hasNextLine()) {
				word = in.nextLine();
				value++;
			}

			// This grabs the number of columns. and removes Commas so it
			// doesn't count any extras
			for (int i = 0; i < word.length(); i++) {
				word = word.replace(",", "");

			}

			NUM_COLS = word.length();
			NUM_ROWS = value;

			this.grid = new BoardCell[NUM_ROWS][NUM_COLS];

			int i = 0;
			int count = 0;
			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] csvFile = line.split(DELIMITER);

				for (int j = 0; j < NUM_COLS; j++) {
					this.grid[i][j] = new BoardCell(i, j, csvFile[j]);
				}
				i++;
				count++;
			}
			calcAdjacencies();
			in.close();
		} catch (Exception e) {
			throw new BadConfigFormatException("error", e);
		}

		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new BadConfigFormatException("Some IOException", e);
		}

	}

	// AutoGenerated might need to be changed
	public Map<Character, String> getLegend() {
		return legendMap;
	}

	// AutoGenerated might need to be changed
	public int getNumRows() {
		int rows = NUM_ROWS;
		return rows;
	}

	// AutoGenerated might need to be changed
	public int getNumColumns() {

		int cols = NUM_COLS;

		return cols;
	}

	public Set<BoardCell> getTargets() {
		return this.targets;
	}

	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);
	}

	public BoardCell getCellAt(int height, int width) {
		return grid[height][width];
	}

	//// AutoGenerated might need to be changed
	public void setConfigFiles(String string, String string2) {
		layoutFile = string;
		legendFile = string2;
	}

	public Set<BoardCell> getAdjList(int one, int two) {
		// System.out.println(grid[one][two].getInitial());

			
			System.out.println("Points("+ one + ", "+two + ") is being tested");
			HashSet<BoardCell> temp = (HashSet<BoardCell>) adjMatrix.get(grid[one][two]);
			
			
			System.out.println("Size of Temp before tests: " + temp.size());
			
			Iterator<BoardCell> iter = temp.iterator();

			if (iter.hasNext()) {
				BoardCell value = iter.next();
				System.out.println("Tested: " + value.getWholeValue());
				if (value.getInitial() != 'W') {
					temp.remove(value);
					System.out.println("Removed " + value.getWholeValue());
				}
			}
			
			if (!temp.contains("W")){
				temp.clear();
			}
			


			System.out.println("Size of Temp is: " + temp.size());
			
			Iterator<BoardCell> iterTwo = temp.iterator();
			if (iterTwo.hasNext()) {
				BoardCell valueTwo = iterTwo.next();
				System.out.println("What is left in the Set: " + valueTwo.getWholeValue());
			}

		return temp;
	}

	// I changed the return type to Int. It was a Void.
	public int calcTargets(int i, int j, int k) {
		return 0;
	}

}
