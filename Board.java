package Board;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Naomi and Brandon
public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
	}
	
	public void loadConfigFiles(){
		//add logic and more helper methods
	}

	public int calcIndex(int row, int column){
		//add logic
		return 1000;
	}
	
	public BoardCell GetRoomCellAt(int row, int column){
		//add logic
		return null;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	
}
