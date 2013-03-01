package clueGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	String legendFileName;
	String boardFileName;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
	}
	
	public Board(String legend, String board) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		legendFileName = legend;
		boardFileName = board;
	}
	
	public void loadConfigFiles() {
		loadRoomConfig();
		loadBoardConfig();
	}
	
	public void loadRoomConfig() throws BadConfigFormatException {
		
	}
	
	public void loadBoardConfig() throws BadConfigFormatException {
		
	}
	
	public int calcIndex(int row, int column) {
		//return (row * 4 + column);
		return 0;
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
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

	//Adjacency tests:
	
	//Creates the adjacency lists.
	public void calcAdjacencies() {
		
	}
	
	//Returns a linked list with the adjacencies for a given index.
	public LinkedList<Integer> getAdjList(int index) {
		return new LinkedList<Integer>();
	}
	
	//Calculates the available targets given an row/col and number of steps.
	public Set<BoardCell> calcTargets(int row, int col, int steps) {
		return new HashSet<BoardCell>();
	}
	
	//Returns a set of all the targets that are available. Called after calcTargets.
	public Set<BoardCell> getTargets() {
		return new HashSet<BoardCell>();
	}

	//Returns the cell at the given index.
	public BoardCell getCellAt(int index) {
		return null;
	}
	
	

}
