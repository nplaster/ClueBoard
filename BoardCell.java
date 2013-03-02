package clueGame;
//Naomi and Brandon
public class BoardCell {
	private int row;
	private int column;
	
	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public boolean isWalkway(){
		//return true if the cell is a wlkaway
		return false;
	}
	
	public boolean isRoom(){
		//return true if the cell is a room
		return false;
	}
	
	public boolean isDoorway(){
		//return true if the cell is a doorway
		return false;
	}
	
	//later add abstract method named draw
	
}
