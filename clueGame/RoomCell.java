package clueGame;

public class RoomCell extends BoardCell {
	
	private char initial;
	private int row, column;
	boolean walkway = false;
			
	public RoomCell() {
		// TODO Auto-generated constructor stub
	}
	
	public RoomCell(int rowIn, int colIn, String input) {
		if(input.length() == 1) {
			initial = input.charAt(0);
			row = rowIn;
			column = colIn;
			doorDirection = DoorDirection.NONE;
		} else {
			initial = input.charAt(0);
			row = rowIn;
			column = colIn;
			char dir = input.charAt(1);
			if(dir == 'U') doorDirection = DoorDirection.UP;
			else if(dir == 'D') doorDirection = DoorDirection.DOWN;
			else if(dir == 'L') doorDirection = DoorDirection.LEFT;
			else if(dir == 'R') doorDirection = DoorDirection.RIGHT;
			else doorDirection = DoorDirection.NONE;
		}
	}
	
	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE;
	}
	
	private DoorDirection doorDirection;
	
	
	public boolean isRoom() {
		if(initial != 'X' && initial != 'W') return true;
		else return false;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return initial;
	}
	
	@Override
	public boolean isDoorway() {
		if(doorDirection != DoorDirection.NONE) return true;
		else return false;
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
	
	/*
	public void draw() {
		
	}
	*/

}
