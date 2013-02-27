package clueGame;

public class RoomCell extends BoardCell {

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
	
	/*
	public void draw() {
		
	}
	*/

}
