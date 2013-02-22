package experiment;

import static org.junit.Assert.*;
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
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}

}
