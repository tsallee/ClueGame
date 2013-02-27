package clueGame;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import org.junit.Test;

public class clueGameTester {
	
	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void testRowsCols() {
		assertEquals(24, board.getNumColumns());
		assertEquals(24, board.getNumRows());
	}

	@Test
	public void testDoors() {
		int numDoors = 0;
		for(BoardCell cell : board.getCells()) {
			if(cell.isDoorway()) numDoors++;
		}
		assertEquals(17, numDoors);
		assertEquals(false, board.getRoomCellAt(10,8).isDoorway());
		assertEquals(false, board.getRoomCellAt(12,12).isDoorway());
		assertEquals(false, board.getRoomCellAt(23,17).isDoorway());
		assertEquals(false, board.getRoomCellAt(13,22).isDoorway());
		assertEquals(false, board.getRoomCellAt(1,1).isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, board.getRoomCellAt(4, 6).getDoorDirection());
		assertEquals(RoomCell.DoorDirection.UP, board.getRoomCellAt(19, 14).getDoorDirection());
		assertEquals(RoomCell.DoorDirection.LEFT, board.getRoomCellAt(13, 16).getDoorDirection());
	}
	
	@Test
	public void testRooms() {
		
	}
	
	@Test
	public void testCalcIndex() {
		
	}
	
	@Test
	public void testBadConfigException() {
		
	}
}
