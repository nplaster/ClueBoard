import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Naomi and Brandon
public class Board {
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character, String> rooms = new HashMap<Character, String>();
	private int numRows;
	private int numColumns;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadConfigFiles(){
		//add logic and more helper methods
	}

	public void calcIndex(int row, int column){
		//add logic
	}
	
	public BoardCell GetRoomCellAt(int row, int column){
		//add logic
		return null;
	}
	
	//need to add getters for the board fields
	
}
