package clueGame;

public class BoardCell {
	int row;
	int column;
	boolean walkway;
	
	public BoardCell() {}
	
	public BoardCell(int rowInd, int colInd, boolean isWalkway) {
		super();
		row = rowInd;
		column = colInd;
		walkway = isWalkway;
	}

	public boolean isWalkway() {
		return walkway;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}
	
	/*
	public void draw() {
		
	}
	*/
	
}
