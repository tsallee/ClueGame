package clueGame;

public class RoomCell extends BoardCell {
	
	private char initial;
			
	public RoomCell() {
		// TODO Auto-generated constructor stub
	}
	
	public RoomCell(String input) {
		if(input.length() == 1) {
			initial = input.charAt(0);
			doorDirection = DoorDirection.NONE;
		} else {
			initial = input.charAt(0);
			char dir = input.charAt(1);
			if(dir == 'U') doorDirection = DoorDirection.UP;
			else if(dir == 'D') doorDirection = DoorDirection.DOWN;
			else if(dir == 'L') doorDirection = DoorDirection.LEFT;
			else if(dir == 'R') doorDirection = DoorDirection.RIGHT;
		}
	}
	
	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE;
	}
	
	private DoorDirection doorDirection;
	
	@Override
	public boolean isRoom() {
		return true;
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
	
	/*
	public void draw() {
		
	}
	*/

}
