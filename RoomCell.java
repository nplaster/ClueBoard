package Board;
//Naomi and Brandon
public class RoomCell extends BoardCell {
	public enum DoorDirection { UP, DOWN, LEFT, RIGHT, NONE };
	private DoorDirection doorDirection;
	private char roomInitial;
	
	@Override
	public boolean isRoom(){
		return true;
	}
	
	public RoomCell() {
		// TODO Auto-generated constructor stub
	}

	public char getRoomInitial() {
		return roomInitial;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	
	//Override draw when we add GUI

}
