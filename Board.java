package Board;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

//Naomi and Brandon
public class Board {
	public static final int ROWS = 23;
	public static final int COLS = 23;
	public static final int ROOMS = 11;
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
	
	public void loadConfigFiles() {
		try {
			this.loadLegend("Legend.txt");
			this.loadBoard("Board.csv");
		}
		catch (BadConfigFormatException e) {
			System.out.println(e);
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Load Legend file
	public void loadLegend(String fileName) throws BadConfigFormatException, FileNotFoundException {
		FileReader legend = new FileReader(fileName);
		Scanner input = new Scanner(legend);
		for(int i = 0; i < ROOMS; ++i) {
			if(input.hasNextLine()) {
				String line = input.nextLine();
				String[] parts = line.split(",");
				if(parts[0].length() == 0 || parts[1].length() == 0) {
					throw new BadConfigFormatException("Bad configuration in Legend.txt");

				}
				else {
					char initial = parts[0].charAt(0);
					String room = parts[1];
					room = room.substring(1, room.length());
					rooms.put(initial, room);				}
			}
			else {
				throw new BadConfigFormatException("Too few rooms in Legend.txt");
			}
		}
	}
	
	public void loadBoard(String fileName) {
		
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
