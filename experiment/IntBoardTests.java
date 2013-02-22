package experiment;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.*;

public class IntBoardTests {
	
	private IntBoard board;
	
	@Before
	public void setup() {
		board = new IntBoard();
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(7, board.calcIndex(1, 3));
		assertEquals(9, board.calcIndex(2, 1));
		assertEquals(14, board.calcIndex(3, 2));
	}
	
	@Test
	public void testAdjacency0()
	{
		LinkedList testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		board.startTargets(0, 3);
		HashSet targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(12));
		assertTrue(targets.contains(9));
		assertTrue(targets.contains(1));
		assertTrue(targets.contains(6));
		assertTrue(targets.contains(3));
		assertTrue(targets.contains(4));
	}
	
	@Test
	public void testTargets3_1() {
		board.startTargets(3, 1);
		HashSet targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(2));
		assertTrue(targets.contains(7));
	}
	
	@Test
	public void testTargets5_2() {
		board.startTargets(5, 2);
		HashSet targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(0));
		assertTrue(targets.contains(8));
		assertTrue(targets.contains(2));
		assertTrue(targets.contains(10));
	}

	@Test
	public void testTargets15_5() {
		board.startTargets(15, 5);
		HashSet targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(4));
		assertTrue(targets.contains(1));
		assertTrue(targets.contains(12));
		assertTrue(targets.contains(9));
		assertTrue(targets.contains(6));
		assertTrue(targets.contains(3));
		assertTrue(targets.contains(14));
		assertTrue(targets.contains(11));
	}
	
	public void testTargets7_4() {
		board.startTargets(7, 4);
		HashSet targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(0));
		assertTrue(targets.contains(8));
		assertTrue(targets.contains(13));
		assertTrue(targets.contains(5));
		assertTrue(targets.contains(2));
		assertTrue(targets.contains(10));
		assertTrue(targets.contains(15));
	}

	public void testTargets9_6() {
		board.startTargets(9, 6);
		HashSet targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(4));
		assertTrue(targets.contains(12));
		assertTrue(targets.contains(6));
		assertTrue(targets.contains(14));
		assertTrue(targets.contains(1));
		assertTrue(targets.contains(11));
		assertTrue(targets.contains(3));
	}

}
