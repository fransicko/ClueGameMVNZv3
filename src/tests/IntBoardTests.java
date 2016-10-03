package tests;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.*;


import experiment.*;

//@Before method to set up your IntBoard.
//Methods to test the creation of adjacency lists, including:
//top left corner (i.e., location [0][0])
//bottom right corner (i.e., location [3][3])
//a right edge (e.g., location [1][3])
//a left edge (e.g., location [3][0])
//second column middle of grid (e.g., location [1][1])
//second from last column, middle of grid(e.g., location [2][2]).
//Note: the "second" type tests are for catching off-by-one errors.
//Create at least six methods to test target creation (see examples below)
//This class must work with a 4x4 grid (i.e., not the full game board)
//should be in package tests


public class IntBoardTests {
	IntBoard board;
	
	
	@Before
	public void before() {
		this.board = new IntBoard(4,4);
	}
	
	@Test
	public void testAdjacency00()
	{
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency13()
	{
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency30()
	{
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency33()
	{
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency11()
	{
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency22()
	{
		BoardCell cell = board.getCell(2,2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testTargets3_0()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testTargets1_3()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}

}
