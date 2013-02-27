package clueGame;

public abstract class BoardCell {
	
	int row;
	int column;
	
	public BoardCell() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isWalkway() {
		return false;
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
