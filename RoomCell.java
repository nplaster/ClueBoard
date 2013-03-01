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
	
	public RoomCell(int row, int column, char initial) {
		super(row,column);
		roomInitial = initial;
	}
	
	public RoomCell(int row, int column, char initial, DoorDirection doorDirection) {
		super(row,column);
		roomInitial = initial;
		this.doorDirection = doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	
	//Override draw when we add GUI

}
