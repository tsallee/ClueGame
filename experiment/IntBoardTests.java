package experiment;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;

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
		HashSet targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}

}
