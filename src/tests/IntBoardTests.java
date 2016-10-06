package tests;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.*;

import clueGame.Board;
import clueGame.BoardCell;

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
	
	
	Board board;
	
	@Before
	public void before() {
		this.board = new Board(4,4);
	}
	
	@Test
	public void testAdjacency00()
	{
		BoardCell cell = board.getCellAt(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency13()
	{
		BoardCell cell = board.getCellAt(1,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 3)));
		assertTrue(testList.contains(board.getCellAt(0, 3)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency30()
	{
		BoardCell cell = board.getCellAt(3,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(3, 1)));
		assertTrue(testList.contains(board.getCellAt(2, 0)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency33()
	{
		BoardCell cell = board.getCellAt(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(2, 3)));
		assertTrue(testList.contains(board.getCellAt(3, 2)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency11()
	{
		BoardCell cell = board.getCellAt(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertTrue(testList.contains(board.getCellAt(1, 2)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency22()
	{
		BoardCell cell = board.getCellAt(2,2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(2, 3)));
		assertTrue(testList.contains(board.getCellAt(3, 2)));
		assertTrue(testList.contains(board.getCellAt(1, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = board.getCellAt(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 0)));
		assertTrue(targets.contains(board.getCellAt(2, 1)));
		assertTrue(targets.contains(board.getCellAt(0, 1)));
		assertTrue(targets.contains(board.getCellAt(1, 2)));
		assertTrue(targets.contains(board.getCellAt(0, 3)));
		assertTrue(targets.contains(board.getCellAt(1, 0)));
	}
	
	@Test
	public void testTargets03_2()
	{
		BoardCell cell = board.getCellAt(0, 3);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 1)));
		assertTrue(targets.contains(board.getCellAt(1, 2)));
		assertTrue(targets.contains(board.getCellAt(2, 3)));
	}
	
	@Test
	public void testTargets10_1()
	{
		BoardCell cell = board.getCellAt(1, 0);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 1)));
		assertTrue(targets.contains(board.getCellAt(0, 0)));
		assertTrue(targets.contains(board.getCellAt(2, 0)));

	}
	
	@Test
	public void testTargets30_3()
	{
		BoardCell cell = board.getCellAt(3, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 0)));
		assertTrue(targets.contains(board.getCellAt(1, 1)));
		assertTrue(targets.contains(board.getCellAt(2, 2)));
		assertTrue(targets.contains(board.getCellAt(3, 3)));
		assertTrue(targets.contains(board.getCellAt(2, 0)));
		assertTrue(targets.contains(board.getCellAt(3, 1)));

	}

	@Test
	public void testTargets22_2()
	{
		BoardCell cell = board.getCellAt(2, 2);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 2)));
		assertTrue(targets.contains(board.getCellAt(1, 1)));
		assertTrue(targets.contains(board.getCellAt(1, 3)));
		assertTrue(targets.contains(board.getCellAt(2, 0)));
		assertTrue(targets.contains(board.getCellAt(3, 1)));
		assertTrue(targets.contains(board.getCellAt(3, 3)));

	}
	
	@Test
	public void testTargets20_6()
	{
		BoardCell cell = board.getCellAt(2, 0);
		board.calcTargets(cell, 4);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 0)));
		assertTrue(targets.contains(board.getCellAt(0, 2)));
		assertTrue(targets.contains(board.getCellAt(1, 1)));
		assertTrue(targets.contains(board.getCellAt(1, 3)));
		assertTrue(targets.contains(board.getCellAt(2, 2)));
		assertTrue(targets.contains(board.getCellAt(3, 1)));
		assertTrue(targets.contains(board.getCellAt(3, 3)));

	}
}
