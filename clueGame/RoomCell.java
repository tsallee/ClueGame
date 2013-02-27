package clueGame;

public class RoomCell extends BoardCell {
	
	private char initial;
			
	public RoomCell() {
		// TODO Auto-generated constructor stub
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
	
	/*
	public void draw() {
		
	}
	*/

}
