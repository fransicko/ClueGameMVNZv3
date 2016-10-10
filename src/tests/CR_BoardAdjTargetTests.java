package tests;

/*
 * This program tests that adjacencies and targets are calculated correctly.
 */

import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class CR_BoardAdjTargetTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		board.setConfigFiles("CR_ClueLayout.csv", "CR_ClueLegend.txt");		
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(24, 25);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(1, 11);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(6, 25);
		assertEquals(0, testList.size())
		;
		// Test one that is in middle of room
		testList = board.getAdjList(4, 2);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(2, 17);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(6, 12);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(4, 5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 6)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(8, 24);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 23)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(3, 21);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 21)));
		//TEST DOORWAY UP
		testList = board.getAdjList(9, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 3)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(4, 6);
		assertTrue(testList.contains(board.getCellAt(4, 5)));
		assertTrue(testList.contains(board.getCellAt(3, 6)));
		assertTrue(testList.contains(board.getCellAt(5, 6)));
		assertTrue(testList.contains(board.getCellAt(4, 7)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(11, 14);
		assertTrue(testList.contains(board.getCellAt(10, 14)));
		assertTrue(testList.contains(board.getCellAt(11, 13)));
		assertTrue(testList.contains(board.getCellAt(11, 15)));
		assertTrue(testList.contains(board.getCellAt(12, 14)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(8, 23);
		assertTrue(testList.contains(board.getCellAt(7, 23)));
		assertTrue(testList.contains(board.getCellAt(9, 23)));
		assertTrue(testList.contains(board.getCellAt(8, 22)));
		assertTrue(testList.contains(board.getCellAt(8, 24)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(22, 25);
		assertTrue(testList.contains(board.getCellAt(23, 25)));
		assertTrue(testList.contains(board.getCellAt(21, 25)));
		assertTrue(testList.contains(board.getCellAt(22, 24)));
		assertEquals(3, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		//Next to cellar.  Two walkway piece. Down and Right
		Set<BoardCell> testList = board.getAdjList(0, 14);
		assertTrue(testList.contains(board.getCellAt(1, 14)));
		assertTrue(testList.contains(board.getCellAt(0, 15)));
		assertEquals(2, testList.size());
		
		// Tests two walk ways on the edge of the board
		testList = board.getAdjList(6, 0);
		assertTrue(testList.contains(board.getCellAt(6, 1)));
		assertTrue(testList.contains(board.getCellAt(7, 0)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(18, 20);
		assertTrue(testList.contains(board.getCellAt(17, 20)));
		assertTrue(testList.contains(board.getCellAt(19, 20)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(9,8);
		assertTrue(testList.contains(board.getCellAt(9, 7)));
		assertTrue(testList.contains(board.getCellAt(9, 9)));
		assertTrue(testList.contains(board.getCellAt(8, 8)));
		assertTrue(testList.contains(board.getCellAt(8, 10)));
		assertEquals(4, testList.size());

		//Tests the bottom of the board by a closet
		testList = board.getAdjList(24, 7);
		assertTrue(testList.contains(board.getCellAt(23, 7)));
		assertTrue(testList.contains(board.getCellAt(24, 6)));
		assertEquals(2, testList.size());

	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(22, 20, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 20)));
		assertTrue(targets.contains(board.getCellAt(22, 21)));	
		
		board.calcTargets(8, 0, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 0)));
		assertTrue(targets.contains(board.getCellAt(9, 0)));			
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(22, 20, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 20)));
		assertTrue(targets.contains(board.getCellAt(20, 21)));
		assertTrue(targets.contains(board.getCellAt(22, 22)));
		
		board.calcTargets(8, 0, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 0)));
		assertTrue(targets.contains(board.getCellAt(6, 0)));	
		assertTrue(targets.contains(board.getCellAt(7, 1)));			
	}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(22, 20, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(18, 20)));
		assertTrue(targets.contains(board.getCellAt(22, 24)));
		assertTrue(targets.contains(board.getCellAt(21, 23)));
		assertTrue(targets.contains(board.getCellAt(20, 20)));
		assertTrue(targets.contains(board.getCellAt(21, 21)));
		assertTrue(targets.contains(board.getCellAt(20, 22)));
		
		// Includes a path that doesn't have enough length
		board.calcTargets(8, 0, 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 0)));
		assertTrue(targets.contains(board.getCellAt(6, 2)));	
		assertTrue(targets.contains(board.getCellAt(7, 3)));	
		assertTrue(targets.contains(board.getCellAt(6, 0))); 
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(8, 0, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 0)));
		assertTrue(targets.contains(board.getCellAt(9, 3)));	
		assertTrue(targets.contains(board.getCellAt(7, 5)));	
		assertTrue(targets.contains(board.getCellAt(6, 4)));	
		assertTrue(targets.contains(board.getCellAt(6, 2)));
		assertTrue(targets.contains(board.getCellAt(7, 3)));
	}	
	
	
	
	-+
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(23, 3, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(7, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(21, 3)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(1, 23)));
		assertTrue(targets.contains(board.getCellAt(5, 23)));
		// one up/down, one left/right
		assertTrue(targets.contains(board.getCellAt(2, 22)));
		assertTrue(targets.contains(board.getCellAt(2, 24)));
		assertTrue(targets.contains(board.getCellAt(4, 22)));
		assertTrue(targets.contains(board.getCellAt(4, 24)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(21, 25, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(7, targets.size());
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(20, 25)));
		assertTrue(targets.contains(board.getCellAt(22, 25)));
		// 3 spaces left (Can't go right)
		assertTrue(targets.contains(board.getCellAt(21, 22)));
		// Lower up and lower down
		assertTrue(targets.contains(board.getCellAt(20, 23)));
		assertTrue(targets.contains(board.getCellAt(22, 23)));
		// directly left
		assertTrue(targets.contains(board.getCellAt(21, 24)));
		// into the room
		assertTrue(targets.contains(board.getCellAt(23, 25)));		
		
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(10, 15, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(11, 15)));
		// Take two steps
		board.calcTargets(10, 15, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(12, 15)));
		assertTrue(targets.contains(board.getCellAt(11, 14)));
		assertTrue(targets.contains(board.getCellAt(11, 16)));
	}

}
