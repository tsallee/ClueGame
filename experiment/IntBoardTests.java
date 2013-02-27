package experiment;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.HashSet;
import java.util.LinkedList;

public class IntBoardTests {
	
	private IntBoard board;
	
	@Before
	public void setup() {
		board = new IntBoard();
		board.calcAdjacencies();
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(7, board.calcIndex(1, 3));
		assertEquals(9, board.calcIndex(2, 1));
		assertEquals(14, board.calcIndex(3, 2));
	}
	
	@Test
	public void testAdjacency0() {
		LinkedList<Integer> testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency15() {
		LinkedList<Integer> testList = board.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency5() {
		LinkedList<Integer> testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
	}
			
	@Test
	public void testAdjacency6() {
		LinkedList<Integer> testList = board.getAdjList(6);
		Assert.assertTrue(testList.contains(7));
		Assert.assertTrue(testList.contains(2));
		Assert.assertTrue(testList.contains(5));
		Assert.assertTrue(testList.contains(10));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency7() {
		LinkedList<Integer> testList = board.getAdjList(7);
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency8() {
		LinkedList<Integer> testList = board.getAdjList(8);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(12));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testTargets0_3() {
		board.startTargets(0, 3);
		HashSet<Integer> targets = board.getTargets();
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
		HashSet<Integer> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(2));
		assertTrue(targets.contains(7));
	}
	
	@Test
	public void testTargets5_2() {
		board.startTargets(5, 2);
		HashSet<Integer> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(0));
		assertTrue(targets.contains(8));
		assertTrue(targets.contains(2));
		assertTrue(targets.contains(10));
		assertTrue(targets.contains(13));
		assertTrue(targets.contains(7));
	}

	@Test
	public void testTargets15_5() {
		board.startTargets(15, 5);
		HashSet<Integer> targets = board.getTargets();
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
	
	@Test
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

	@Test
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
