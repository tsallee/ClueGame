package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	
	private ArrayList<BoardCell> cells;
	private Map<Character, LinkedList<Integer>> roomAdjacencies;
	private Map<Character, String> rooms;
	private Map<Integer, LinkedList<Integer>> adjacencyMap;
	private HashSet<BoardCell> targets;
	private boolean[] visited;
	private int numRows;
	private int numColumns;
	String legendFileName;
	String boardFileName;
	private int boardSize;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		adjacencyMap = new HashMap<Integer, LinkedList<Integer>>();
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
			boardSize = numRows*numColumns;
		} catch (FileNotFoundException e ) {
			System.out.println("File not found.");
		} catch (BadConfigFormatException e) {
			System.out.println("Bad format Exception");
		}
	}
	
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		roomAdjacencies = new HashMap<Character, LinkedList<Integer>>();
		FileReader reader = new FileReader(legendFileName);
		Scanner in = new Scanner(reader);
		while(in.hasNext()) {
			String input = in.nextLine();
			String[] sep = input.split(", ");
			if (sep.length > 2) throw new BadConfigFormatException();
			if (sep[0].length() >1 ) throw new BadConfigFormatException();
			rooms.put(input.charAt(0), sep[1]);
			roomAdjacencies.put(input.charAt(0), new LinkedList<Integer>());
		}
	}
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		numRows = 0;
		boolean firstLine = true;
		LinkedList<Integer> adjList;
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
				if(rooms.containsKey(sep[i].charAt(0)) && sep[i] == "W") {
					cells.add(new BoardCell(numRows, i, true));
				} else if(rooms.containsKey(sep[i].charAt(0)) && sep[i] == "X") {
					cells.add(new BoardCell(numRows, i, false));
				} else if(rooms.containsKey(sep[i].charAt(0)) && (sep[i].length() < 3)) {
					cells.add(new RoomCell(numRows, i, sep[i]));
					if(sep[i].length() > 1) {
						char second = sep[i].charAt(1);
						//if(second == 'N') System.out.println(numRows + "\n" + i + "\n" + second);
						if ( second == 'U') {
							adjList = roomAdjacencies.get(sep[i].charAt(0));
							adjList.add(calcIndex(numRows - 1, i));
							roomAdjacencies.put(sep[i].charAt(0), adjList);
						} else if ( second == 'L') {
							adjList = roomAdjacencies.get(sep[i].charAt(0));
							adjList.add(calcIndex(numRows, i - 1));
							roomAdjacencies.put(sep[i].charAt(0), adjList);
						} else if ( second == 'R') {
							adjList = roomAdjacencies.get(sep[i].charAt(0));
							adjList.add(calcIndex(numRows, i + 1));
							roomAdjacencies.put(sep[i].charAt(0), adjList);
						} else if ( second == 'D') {
							adjList = roomAdjacencies.get(sep[i].charAt(0));
							adjList.add(calcIndex(numRows + 1, i));
							roomAdjacencies.put(sep[i].charAt(0), adjList);
						} else {
							//System.out.println("N");
						}
					}
				} else {
					//System.out.println("except");
					throw new BadConfigFormatException();
				}
			}
			numRows++;
		}		
	}
	
	//Creates the adjacency lists.
	public void calcAdjacencies() {
		LinkedList<Integer> adjList;
		int left, right, above, below;
		RoomCell roomCell = new RoomCell();

		for( int cellIndex = 0; cellIndex < boardSize; ++cellIndex) {
			left = cellIndex - 1;
			right = cellIndex + 1;
			above = cellIndex - numColumns;
			below = cellIndex + numColumns;
			adjList = new LinkedList<Integer>();
			
			if( getCellAt(cellIndex).isRoom() ) {
				roomCell = getRoomCellAt(cellIndex);
				adjList = roomAdjacencies.get(roomCell.getInitial());
				
			} else {			
				if(!isRightEdge(cellIndex) ) {
					if ( !getCellAt(right).isRoom() || getCellAt(right).isDoorway())
							adjList.add(right);
				}
				if(!isLeftEdge(cellIndex)) {
					if ( !getCellAt(left).isRoom() || getCellAt(left).isDoorway())
							adjList.add(left);
				}
				if(!isTopEdge(cellIndex)) {
					if ( !getCellAt(above).isRoom() || getCellAt(above).isDoorway()) 
						adjList.add(above);
				}
				if(!isBottomEdge(cellIndex)) {
					if ( !getCellAt(below).isRoom() || getCellAt(below).isDoorway()) 
						adjList.add(below);
				}
			}
			adjacencyMap.put(cellIndex, adjList);
		}
	}
	
	//Calculates the available targets given an row/col and number of steps.
	public void calcTargets(int row, int column, int steps) {
		int location = calcIndex(row, column);
		visited = new boolean[boardSize];
		targets = new HashSet<BoardCell>();
		visited[location] = true;
		helpTargets(location, steps);
		if(targets.contains(getCellAt(location))) targets.remove(getCellAt(location));
	}
	
	public void helpTargets(int location, int steps) {
		LinkedList<Integer> adjList = getAdjList(location);
		
		for(int cell : adjList) {
			visited[cell] = true;
			if(getCellAt(cell).isDoorway()) {
				targets.add(getCellAt(cell));
				return;
			} else if(steps == 1) targets.add(getCellAt(cell));
			else helpTargets(cell, steps - 1);
			visited[cell] = false;
		}
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
		if ( cellIndex < numColumns ) return true;
		return false;
	}

	public boolean isRightEdge(int cellIndex) {
		if ( cellIndex % numColumns == (numColumns - 1) ) return true;
		return false;
	}
	
	public boolean isLeftEdge(int cellIndex) {
		if ( cellIndex % numColumns == 0 ) return true;
		return false;
	}
	public boolean isBottomEdge(int cellIndex) {
		if ( cellIndex > boardSize - numColumns ) return true;
		return false;
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
		return (RoomCell) cells.get(calcIndex(row, column));
	}
	
	public RoomCell getRoomCellAt(int index) {
		return (RoomCell) cells.get(index);
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
		return adjacencyMap.get(index);
	}
		
	//Returns a set of all the targets that are available. Called after calcTargets.
	public Set<BoardCell> getTargets() {
		return targets;
	}

	//Returns the cell at the given index.
	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}
}
