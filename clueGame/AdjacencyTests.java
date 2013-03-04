package clueGame;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AdjacencyTests {
	
	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
		board.loadConfigFiles();
		board.calcAdjacencies();
	}
	
	@Test
	public void testAdjacencySurroundedByWalkways() {
		//Locations with only walkways as adjacent locations
		LinkedList<Integer> adjList = board.getAdjList(board.calcIndex(17, 16));
		assertEquals(4, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(17, 15)));
		assertTrue(adjList.contains(board.calcIndex(16, 16)));
		assertTrue(adjList.contains(board.calcIndex(18, 16)));
		assertTrue(adjList.contains(board.calcIndex(17, 17)));
	}
	
	@Test
	public void testEdgeAdjacencies() {
		//Locations at the edge
		LinkedList<Integer> adjList = board.getAdjList(board.calcIndex(8, 0));
		assertEquals(2, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(8, 7)));
		assertTrue(adjList.contains(board.calcIndex(11, 3)));
		
		adjList = board.getAdjList(board.calcIndex(0, 8));
		assertEquals(2, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(0, 7)));
		assertTrue(adjList.contains(board.calcIndex(1, 8)));
		
		adjList = board.getAdjList(board.calcIndex(8, 23));
		assertEquals(2, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(7, 23)));
		assertTrue(adjList.contains(board.calcIndex(8, 22)));
		
		adjList = board.getAdjList(board.calcIndex(23, 16));
		assertEquals(3, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(23, 15)));
		assertTrue(adjList.contains(board.calcIndex(23, 17)));
		assertTrue(adjList.contains(board.calcIndex(22, 16)));
	}
	
	@Test
	public void testNextToRoomNotDoorway() {
		LinkedList<Integer> adjList = board.getAdjList(board.calcIndex(22, 6));
		assertEquals(3, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(21, 6)));
		assertTrue(adjList.contains(board.calcIndex(23, 6)));
		assertTrue(adjList.contains(board.calcIndex(22, 7)));
		
		adjList = board.getAdjList(board.calcIndex(1, 15));
		assertEquals(3, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(1, 16)));
		assertTrue(adjList.contains(board.calcIndex(0, 15)));
		assertTrue(adjList.contains(board.calcIndex(2, 15)));
	}
	
	@Test
	public void testNextToDoor() {
		LinkedList<Integer> adjList = board.getAdjList(board.calcIndex(20, 16));
		assertEquals(4, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(19, 16)));
		assertTrue(adjList.contains(board.calcIndex(20, 17)));
		assertTrue(adjList.contains(board.calcIndex(21, 16)));
		assertTrue(adjList.contains(board.calcIndex(21, 16)));
		assertTrue(board.getRoomCellAt(20, 15).isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, board.getRoomCellAt(20, 15).getDoorDirection());
		
		adjList = board.getAdjList(board.calcIndex(4, 6));
		assertEquals(4, adjList.size());
		assertTrue(adjList.contains(board.calcIndex(3, 6)));
		assertTrue(adjList.contains(board.calcIndex(4, 7)));
		assertTrue(adjList.contains(board.calcIndex(5, 6)));
		assertTrue(adjList.contains(board.calcIndex(4, 5)));
		assertTrue(board.getRoomCellAt(3, 6).isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, board.getRoomCellAt(3, 6).getDoorDirection());
	}
	
	@Test
	public void testDoorwayAdjacency() {
		LinkedList<Integer> adjList = board.getAdjList(board.calcIndex(19, 4));
		assertEquals(1, adjList.size());
		assertTrue(board.getRoomCellAt(19, 4).isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, board.getRoomCellAt(19, 4).getDoorDirection());
		assertTrue(adjList.contains(board.calcIndex(19, 5)));

		adjList = board.getAdjList(board.calcIndex(18, 19));
		assertEquals(1, adjList.size());
		assertTrue(board.getRoomCellAt(18, 19).isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, board.getRoomCellAt(18, 19).getDoorDirection());
		assertTrue(adjList.contains(board.calcIndex(17, 19)));
	}
	
	@Test
	public void testWalkwayTargets() {
		
		board.calcTargets(7, 19, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 18))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 17))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 18))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 20))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 21))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 20))));
		
		board.calcTargets(4, 0, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 1))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 0))));
		
		board.calcTargets(7, 23, 5);
		targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 23))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 21))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 19))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 22))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 20))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 18))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 21))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 23))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 19))));
		
		
		board.calcTargets(23, 14, 2);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(23, 16))));
		
	}
	
	@Test
	public void testRoomEnterTargets() {
		
		board.calcTargets(11, 3, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 3))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 2))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 4))));
		assertTrue(board.getRoomCellAt(10, 3).isDoorway());
		
		board.calcTargets(16, 10, 3);
		targets = board.getTargets();
		
		System.out.println(targets.size());
		
		LinkedList<Integer> bla = board.getAdjList(board.calcIndex(11, 8));
		for ( int i : bla )
			System.out.println(board.getCellAt(i));
			
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 13))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 9))));
		assertTrue(board.getRoomCellAt(18, 9).isDoorway());
		
	}
	
	@Test
	public void testRoomLeaveTargets() {
		
		board.calcTargets(21, 3, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 5))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 6))));
		
		board.calcTargets(5, 17, 4);
		targets = board.getTargets();
		
		System.out.println(targets.size());
		for ( BoardCell test : targets )
			System.out.println(test);
		
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 20))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 19))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 18))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 16))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 15))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 15))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 16))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 17))));
		assertTrue(board.getRoomCellAt(9, 17).isDoorway());
		
	}

}
