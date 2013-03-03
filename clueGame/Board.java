package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private Map<Integer, LinkedList<Integer>> adjacencyMap;
	private int numRows;
	private int numColumns;
	String legendFileName;
	String boardFileName;
	static final int BOARD_SIZE = 576;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		legendFileName = "LegendConfig.txt";
		boardFileName = "ClueBoard.csv";
	}
	
	public Board(String board, String legend) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		legendFileName = legend;
		boardFileName = board;
	}
	
	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e ) {
			System.out.println("File not found.");
		} catch (BadConfigFormatException e) {
			System.out.println("Bad format Exception");
		}
	}
	
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader reader = new FileReader(legendFileName);
		Scanner in = new Scanner(reader);
		while(in.hasNext()) {
			String input = in.nextLine();
			String[] sep = input.split(", ");
			if (sep.length > 2) throw new BadConfigFormatException();
			if (sep[0].length() >1 ) throw new BadConfigFormatException();
			rooms.put(input.charAt(0), sep[1]);
		}
	}
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		numRows = 0;
		boolean firstLine = true;
		FileReader reader = new FileReader(boardFileName);
		Scanner in = new Scanner(reader);
		while(in.hasNext()) {
			String input = in.nextLine();
			String[] sep = input.split(",");
			if(firstLine == true) {
				numColumns = sep.length;
				firstLine = false;
			}
			if(sep.length != numColumns) throw new BadConfigFormatException();
			
			for(int i = 0; i < sep.length; ++i) {
				if(!rooms.containsKey(sep[i].charAt(0)) && sep[i] == "W") {
					cells.add(new BoardCell(i, numRows, true));
				}else if(!rooms.containsKey(sep[i].charAt(0)) && sep[i] == "X") {
					cells.add(new BoardCell(i, numRows, false));
				}else if(rooms.containsKey(sep[i].charAt(0)) 
						&& (sep[i].length() == 1 || sep[i].length() == 2)) {
					cells.add(new RoomCell(sep[i]));
				} else throw new BadConfigFormatException();
			}
			numRows++;
		}
	}
	
	//Creates the adjacency lists.
	public void calcAdjacencies() {
		LinkedList<Integer> adjList;
		int left, right, above, below, row, column;
		RoomCell roomCell = new RoomCell();
		
		for ( int cellIndex = 0; cellIndex < BOARD_SIZE; ++cellIndex ) {
			row = getRow(cellIndex);
			column = getColumn(cellIndex);
			left = cellIndex - 1;
			right = cellIndex + 1;
			above = cellIndex - numColumns;
			below = cellIndex + numColumns;
			adjList = new LinkedList<Integer>();
			BoardCell cell = getCellAt(cellIndex);
			
			if ( !isEdge(cellIndex) ) {
				System.out.println("calculating inner board adjacencies..." + cellIndex);
				//Middle of board walkway cells
				if ( cell.isWalkway() ) {
					if ( getCellAt(left).isWalkway() ) {
						System.out.println("adding cell (" + row + ", " + column + ")" );
						adjList.add(left);
					}
					if ( getCellAt(right).isWalkway() ) {
						System.out.println("adding cell (" + row + ", " + column + ")" );
						adjList.add(right);
					}
					if ( getCellAt(above).isWalkway() ) {
						System.out.println("adding cell (" + row + ", " + column + ")" );
						adjList.add(above);
					}
					if ( getCellAt(below).isWalkway() ) {
						System.out.println("adding cell (" + row + ", " + column + ")" );
						adjList.add(below);
					}
					if ( getCellAt(left).isDoorway() ) {
						roomCell = getRoomCellAt(row, column + 1);
						if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.RIGHT)
							adjList.add(left);
					}
					if ( getCellAt(right).isDoorway() ) {
						roomCell = getRoomCellAt(row, column - 1);
						if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.LEFT)
							adjList.add(right);
					}
					if ( getCellAt(above).isDoorway() ) {
						roomCell = getRoomCellAt(row - 1, column);
						if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.DOWN)
							adjList.add(above);
					}
					if ( getCellAt(below).isDoorway() ) {
						roomCell = getRoomCellAt(row + 1, column);
						if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.UP)
							adjList.add(below);
					}
				//Middle of board rooms / closet
				} else {
					roomCell = getRoomCellAt(row, column);
					if ( roomCell.isDoorway() ) {
						if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.DOWN)
							adjList.add(below);
						else if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.UP)
							adjList.add(above);
						else if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.RIGHT)
							adjList.add(right);
						else if ( roomCell.getDoorDirection() == RoomCell.DoorDirection.LEFT)
							adjList.add(left);
					}
				}
			}			
			adjacencyMap.put(cellIndex, adjList);
		}
		
	}
	
	//Calculates the available targets given an row/col and number of steps.
	public Set<BoardCell> calcTargets(int row, int col, int steps) {
		return new HashSet<BoardCell>();
	}
	
	public int calcIndex(int row, int column) {
		return(row * numColumns + column);
	}
	
	public boolean isEdge(int cellIndex) {
		if ( isTopEdge(cellIndex) || isBottomEdge(cellIndex) ||
				 isRightEdge(cellIndex) || isLeftEdge(cellIndex) )
			return true;
		else
			return false;
	}
	
	public boolean isTopEdge(int cellIndex) {
		if ( getRow(cellIndex) == 0 ) return true;
		return false;
	}

	public boolean isRightEdge(int cellIndex) {
		if ( getColumn(cellIndex) == 23 ) return true;
		return false;
	}
	
	public boolean isLeftEdge(int cellIndex) {
		if ( getColumn(cellIndex) == 0 ) return true;
		return false;
	}
	public boolean isBottomEdge(int cellIndex) {
		if ( getRow(cellIndex) == 23 ) return true;
		return false;
	}
	
	public int getRow(int cellIndex) {
		return (cellIndex + 1) / numRows;
	}
	
	public int getColumn(int cellIndex) {
		return cellIndex % numRows;
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
		return (RoomCell) cells.get(calcIndex(row, column));
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
	
	//Returns a linked list with the adjacencies for a given index.
	public LinkedList<Integer> getAdjList(int index) {
		return new LinkedList<Integer>();
	}
		
	//Returns a set of all the targets that are available. Called after calcTargets.
	public Set<BoardCell> getTargets() {
		return new HashSet<BoardCell>();
	}

	//Returns the cell at the given index.
	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}
	
	

}
