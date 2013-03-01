package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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
	
	public int calcIndex(int row, int column) {
		return(row * numColumns + column);
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
		return cells.get(index);
	}
	
	

}
