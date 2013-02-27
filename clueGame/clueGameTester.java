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
		for ( BoardCell cell : board.getCells() ) {
			if ( cell.isDoorway()) numDoors++;
		}
		assertEquals(17, numDoors);
		assertEquals(false, board.getRoomCellAt(10,8).isDoorway());
		assertEquals(false, board.getRoomCellAt(12,12).isDoorway());
		assertEquals(false, board.getRoomCellAt(23,17).isDoorway());
		assertEquals(false, board.getRoomCellAt(13,22).isDoorway());
		assertEquals(false, board.getRoomCellAt(1,1).isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, board.getRoomCellAt(3, 6).getDoorDirection());
		assertEquals(RoomCell.DoorDirection.UP, board.getRoomCellAt(18, 14).getDoorDirection());
		assertEquals(RoomCell.DoorDirection.LEFT, board.getRoomCellAt(12, 16).getDoorDirection());
	}
	
	@Test
	public void testRooms() {
		assertEquals(true, board.getRoomCellAt(1,1).isRoom());
		assertEquals(true, board.getRoomCellAt(13,21).isRoom());
		assertEquals(true, board.getRoomCellAt(23,3).isRoom());
		assertEquals(false, board.getRoomCellAt(17,16).isRoom());
		assertEquals(false, board.getRoomCellAt(3,8).isRoom());
		assertEquals(9, board.getRooms().size());
		assertEquals("Study", board.getRooms().get('S'));
		assertEquals("Lounge", board.getRooms().get('O'));
		assertEquals("Ballroom", board.getRooms().get('B'));
		assertEquals('K', board.getRoomCellAt(23,20).getInitial());
		assertEquals('L', board.getRoomCellAt(21,5).getInitial());
		assertEquals('P', board.getRoomCellAt(16,5).getInitial());
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(28, board.calcIndex(1, 3));
		assertEquals(496, board.calcIndex(20, 15));
		assertEquals(154, board.calcIndex(6, 9));
	}
	
	@Test (expected = BadConfigException.class)
	public void testBadConfigException() {
		try {
			board.loadConfigFiles();
		} catch (BadConfigException e) {
			System.out.println("Configuration file is not working.");
		}
	}
}
