package Board;
//Naomi and Brandon
public class BoardCell {
	private int row;
	private int column;
	
	public BoardCell() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean isWalkway(){
		//return true if the cell is a wlkaway
		return false;
	}
	
	public Boolean isRoom(){
		//return true if the cell is a room
		return false;
	}
	
	public Boolean isDoorway(){
		//return true if the cell is a doorway
		return false;
	}
	
	//later add abstract method named draw
	
}
