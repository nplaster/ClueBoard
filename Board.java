package Board;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

//Naomi and Brandon
public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private HashSet<Integer> targets;
	private Map<Integer, LinkedList<Integer>> adjMtx;
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
		return (23*row) + column;
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
	
	public void calcTargets(int location, int steps) {
		
	}
	
	public void calcAdjacencies(){
		
	}
	
	public HashSet getTargets(){
		return targets;	
	}
	
	public LinkedList<Integer> getAdjList(int location){
		return adjMtx.get(location);
	}
	
	public void startTargets(int location, int steps){
		
	}
	
}
