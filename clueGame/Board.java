package clueGame;

import java.util.ArrayList;
import java.util.Map;

public class Board {
	
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadConfigFiles() {
		
	}
	
	public int calcIndex(int row, int column) {
		//return (row * 4 + column);
		return 0;
	}
	
	public BoardCell getRoomCellAt(int row, int column) {
		return new RoomCell();
	}
	
	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	

}
