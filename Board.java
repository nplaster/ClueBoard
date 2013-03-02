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
	private int numRooms;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
	}
	
	public void loadConfigFiles() {
		try {
			this.loadRoomConfig("Legend.txt");
			this.loadBoardConfig("Clue Board.csv");
		}
		catch (BadConfigFormatException e) {
			System.out.println(e);
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Load Legend file
	public void loadRoomConfig(String fileName) throws BadConfigFormatException, FileNotFoundException {
		FileReader legend = new FileReader(fileName);
		Scanner input = new Scanner(legend);
		for(int i = 0; i < ROOMS; ++i) {
			if(input.hasNextLine()) {
				String line = input.nextLine();
				String[] parts = line.split(",");
				if(parts[0].length() == 0 || parts[1].length() == 0) {
					throw new BadConfigFormatException("Bad configuration in Legend file");

				}
				else {
					char initial = parts[0].charAt(0);
					String room = parts[1];
					room = room.substring(1, room.length());
					rooms.put(initial, room);				}
			}
			else {
				throw new BadConfigFormatException("Too few rooms in Legend file");
			}
		}
	}
	
	public void loadBoardConfig(String fileName) throws BadConfigFormatException, FileNotFoundException {
		BoardCell newCell;
		FileReader board = new FileReader(fileName);
		Scanner input = new Scanner(board);
		for(int i = 0; i < ROWS; ++i) {
			if(input.hasNextLine()) {
				String line = input.nextLine();
				String[] parts = line.split(",");
				if(parts.length != COLS) {
					throw new BadConfigFormatException("Too few columns in board file");
				}
				else {
					for(int j = 0; j < COLS; ++j) {
						if(parts[j].charAt(0) == 'X' || parts[j].charAt(0) == 'W' || parts[j].charAt(0) == 'C' || parts[j].charAt(0) == 'K' || parts[j].charAt(0) == 'B' || parts[j].charAt(0) == 'R'
								 || parts[j].charAt(0) == 'L' || parts[j].charAt(0) == 'S' || parts[j].charAt(0) == 'D' || parts[j].charAt(0) == 'O' || parts[j].charAt(0) == 'H') {
							if(parts[j].length() == 1 && parts[j] == "W") {
								newCell = new WalkwayCell(i,j);
								cells.add(newCell);
							}
							else if(parts[j].length() == 1) {
								newCell = new RoomCell(i,j,parts[j].charAt(0));
								cells.add(newCell);
							}
							else {
								newCell = new RoomCell(i,j,parts[j].charAt(0),parts[j].charAt(1));
								cells.add(newCell);
							}
						}
						else 
							throw new BadConfigFormatException("Invalid room initial");
					}
				}
			}
			else {
				throw new BadConfigFormatException("Too few rows in board file");
			}
		}
		numRooms = cells.size();
	}

	public int calcIndex(int row, int column){
		return (23*row) + column;
	}
	
	public BoardCell getRoomCellAt(int row, int column){
		return cells.get(calcIndex(row,column));
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRooms() {
		return numRooms;
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
