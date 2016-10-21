package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class SetupTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 26;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
