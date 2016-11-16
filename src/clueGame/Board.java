package clueGame;

import java.util.*;

import javax.swing.JPanel;

import clueControlGUI.MouseClickerPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.lang.reflect.Field;

public class Board extends JPanel {
	private int startCell;
	private int pathLength;
	
	public Boolean nextTurn = true;

	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Map<Character, String> legendMap = new HashMap<Character, String>();
	Set<BoardCell> visited;
	Set<BoardCell> targets;
	private final int MAX_BOARD_SIZE = 100;
	String layoutFile;
	String legendFile;
	// The new files
	String playerFile;
	String weaponFile;
	String[][] board = new String[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	private BoardCell[][] grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	int NUM_ROWS;
	int NUM_COLS;

	// This will be our deck of cards, the other three are the sub decks of deck.
	private ArrayList<Card> deck = new ArrayList<>();
	public ArrayList<Card> roomCards = new ArrayList<>();
	public ArrayList<Card> personCards = new ArrayList<>();
	public ArrayList<Card> weaponCards = new ArrayList<>();
	public ArrayList<Card> seenCards = new ArrayList<>();
	// Player objects
	public ArrayList<Player> players = new ArrayList<>();
	public HumanPlayer person;
	public ArrayList<ComputerPlayer> comp = new ArrayList<ComputerPlayer>();
	//This is the solution to the game
	private Solution solution;

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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (BoardCell[] i: grid) {
			for (BoardCell j: i) {
				j.draw(g);
			}
		}
		
		for (Player i: players) {
			i.draw(g);
		}
	}

	public void calcAdjacencies() {

		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				Set<BoardCell> adjSet = new HashSet<BoardCell>();

				// This checks to see if the one above it is not out of bounds,
				// if adj tiles are walkway or doors.. and it tests if the tile
				// that is being tested is a walk way or a door.
				if (i > 0 && grid[i - 1][j].getWholeValue().equals("W")
						|| i > 0 && grid[i - 1][j].getWholeValue().length() == 2
						&& grid[i - 1][j].getWholeValue().charAt(1) == 'D') {
					if (grid[i][j].getWholeValue().equals("W")) {
						adjSet.add(getCellAt(i - 1, j));
					}
					if (grid[i][j].getWholeValue().length() == 2) {
						if ((grid[i][j].getSecondInitial() == 'U')) {
							adjSet.add(getCellAt(i - 1, j));
						}
					}
				}
				// This checks to see if the one Below it is not out of bounds,
				// if adj tiles are walkway or doors.. and it tests if the tile
				// that is being tested is a walk way or a door.
				if (i < grid.length - 1 && grid[i + 1][j].getWholeValue().equals("W")
						|| i < grid.length - 1 && grid[i + 1][j].getWholeValue().length() == 2
						&& grid[i + 1][j].getWholeValue().charAt(1) == 'U') {
					if (grid[i][j].getWholeValue().equals("W")) {
						adjSet.add(getCellAt(i + 1, j));
					}
					if (grid[i][j].getWholeValue().length() == 2) {
						if ((grid[i][j].getSecondInitial() == 'D')) {
							adjSet.add(getCellAt(i + 1, j));
						}
					}
				}
				// This checks to see if the one left it is not out of bounds,
				// if adj tiles are walkway or doors.. and it tests if the tile
				// that is being tested is a walk way or a door.
				if (j > 0 && grid[i][j - 1].getWholeValue().equals("W")
						|| j > 0 && grid[i][j - 1].getWholeValue().length() == 2
						&& grid[i][j - 1].getWholeValue().charAt(1) == 'R') {
					if (grid[i][j].getWholeValue().equals("W")) {
						adjSet.add(getCellAt(i, j - 1));
					}
					if (grid[i][j].getWholeValue().length() == 2) {
						if ((grid[i][j].getSecondInitial() == 'L')) {
							adjSet.add(getCellAt(i, j - 1));
						}
					}
				}
				// This checks to see if the one right it is not out of bounds,
				// if adj tiles are walkway or doors.. and it tests if the tile
				// that is being tested is a walk way or a door.
				if (j < grid[i].length - 1 && grid[i][j + 1].getWholeValue().equals("W")
						|| j < grid[i].length - 1 && grid[i][j + 1].getWholeValue().length() == 2
						&& grid[i][j + 1].getWholeValue().charAt(1) == 'L') {
					if (grid[i][j].getWholeValue().equals("W")) {
						adjSet.add(getCellAt(i, j + 1));
					}
					if (grid[i][j].getWholeValue().length() == 2) {
						if ((grid[i][j].getSecondInitial() == 'R')) {
							adjSet.add(getCellAt(i, j + 1));
						}
					}
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

	//This is the path Algorithm 
	private void findAllTargets(BoardCell cell, int pathLength) {
		for (BoardCell c : this.adjMatrix.get(cell)) {
			if (this.visited.contains(c)) {
				continue;
			}
			if (pathLength == 1 || c.getWholeValue().length() == 2) {
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
			loadPlayerFiles();
			loadWeaponFiles();
			makeDeck();
			dealHands();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadConfigFile() {
		try {
			loadBoardConfig();
			loadRoomConfig();
			loadPlayerFiles();
			loadWeaponFiles();
			makeDeck();
			dealHands();
			mkSoln();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This will make a solution, this can be changed later but for testing right now we will do this
	public void mkSoln() {
		Random ran = new Random();
		int room = Math.abs(ran.nextInt())%roomCards.size();
		int ppl = 0;
		do {
			ppl = Math.abs(ran.nextInt())%personCards.size();
		} while (personCards.get(ppl).getName().equals(person.getName()));
		int weapon = Math.abs(ran.nextInt())%weaponCards.size();
		
		solution = new Solution(personCards.get(ppl).getName(), roomCards.get(room).getName(), weaponCards.get(weapon).getName());
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

					//We will add the rooms to our deck of cards
					if (key != 'X' && key != 'W') {
						roomCards.add(new Card(legend[i+1], CardType.ROOM));
					}
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

	// This is were me and the have added code
	// This is were we will load the files to fill our players
	//This is to convert a string to a color
	public java.awt.Color convert(String strColor) {
		java.awt.Color color;
		try {
			//We can use reflection to convert the string to a color
			Field field = java.awt.Color.class.getField(strColor);//Class.forName("java.awt.Color").getField(strColor.trim());
			color = (java.awt.Color)field.get(null);
		} catch(Exception e) {
			color = null;//Not defined
		}
		return color;
	}

	public void loadPlayerFiles() throws FileNotFoundException {
		FileReader readerThree = new FileReader(playerFile);
		Scanner in = new Scanner(readerThree);
		String DELIMITER = ", ";

		// This integer is so that we can just read everything from the players file
		int i = 0;
		while (in.hasNext()) {
			String line = in.nextLine();
			// We only read lines that aren't commented out
			if (line.charAt(0) != '/') {
				String[] plFile = line.split(DELIMITER);
				if (i == 0) {
					person = new HumanPlayer(plFile[0], Integer.parseInt(plFile[1]), Integer.parseInt(plFile[2]), convert(plFile[3]));
					players.add(person);
					++i;
				}
				else {
					comp.add(new ComputerPlayer(plFile[0], Integer.parseInt(plFile[1]), Integer.parseInt(plFile[2]), convert(plFile[3])));
					players.add(comp.get(i-1));
					++i;
				}
				personCards.add(new Card(plFile[0], CardType.PERSON));
			}
		}
	}

	public void loadWeaponFiles() throws FileNotFoundException {
		FileReader reader = new FileReader(weaponFile);
		Scanner in = new Scanner(reader);
		String weapon = "";
		while (in.hasNextLine()) {
			weapon = in.nextLine();
			weaponCards.add(new Card(weapon,CardType.WEAPON));
		}
	}
	
	public void dealHands() {
		Random ran = new Random();
		ArrayList<Card> newDeck = new ArrayList<>(deck);
		
		while (!newDeck.isEmpty()) {
			int card = Math.abs(ran.nextInt())%newDeck.size();
			person.giveCard(newDeck.get(card));
			newDeck.remove(card);
			
			for (ComputerPlayer i: comp) {
				if (!newDeck.isEmpty()) {
					card = Math.abs(ran.nextInt())%newDeck.size();
					i.giveCard(newDeck.get(card));
					newDeck.remove(card);
				}
				
			}
		}
	}
	
	// This will make the deck
	public void makeDeck() {
		for (Card i: roomCards) {
			deck.add(i);
		}
		for (Card i: personCards) {
			deck.add(i);
		}
		for (Card i: weaponCards) {
			deck.add(i);
		}
	}


	//This will select the answer, 
	//not sure how it works yet but will soon
	public void selectAnswer() {

	}

	//This will handle suggestions, the suggester is the person who made the suggestion
	public Card handleSuggestion(Solution suggestion, Player suggester) {
		Card correctSugs = null;
		
		// Gets the location of our suggester
		int index = 0;
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).getName().equals(suggester.getName())) {
				index = i;
				break;
			}
		}
		
		// We will check to see if we are at the end of the list of players
		int newIndex = 0;
		if (index != players.size() - 1) newIndex = index+1;
		
		for (int i = newIndex; i < players.size(); ++i) {
			Card sugs = players.get(i).disproveSuggestion(suggestion);
			
			if (i == index) return correctSugs;
			if (sugs != null) return sugs;
			
			// Reset the index so that way we start at the beginning
			if (i == players.size() - 1) i = -1;
		}
		
		return correctSugs;
		
	}

	// this will check the accusation to see if it is correct
	public boolean checkAccusation(Solution accusation) {
		return solution.person.equals(accusation.person) && solution.weapon.equals(accusation.weapon) && solution.room.equals(accusation.room);
	}

	// AutoGenerated might need to be changed
	public Map<Character, String> getLegend() {
		return legendMap;
	}
	
	//returns string room name
	public String getLegendAt(char n) {
		return legendMap.get(n);
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

	// This is for the player and weapon files, just so past tests pass
	public void setConfigFiles2(String string3, String string4) {
		playerFile = string3;
		weaponFile = string4;
	}

	public Set<BoardCell> getAdjList(int one, int two) {

		return adjMatrix.get(grid[one][two]);
	}

	// I changed the return type to Int. It was a Void.
	public void calcTargets(int i, int j, int k) {
		// Called CalcTargets
		calcTargets(grid[i][j], k);
	}

	// These getters are for testing
	public HumanPlayer getPerson() {
		return person;
	}
	
	public void setPerson(HumanPlayer updatedLocationPlayer) {
		person = updatedLocationPlayer;
	}

	// Get the person at the index
	public ComputerPlayer getCompAt(int index) {
		return comp.get(index);
	}
	
	public ArrayList<ComputerPlayer> getComp() {
		return comp;
	}
	
	public int deckSize(){
		return deck.size();
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public Solution getSoln() {
		return solution;
	}
	
	public void setTurn(Boolean t) {
		nextTurn = t;
	}

	public String getInitial(int x, int y) {
		return grid[x][y].getWholeValue();
	}
}
