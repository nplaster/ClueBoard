package clueGame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
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
	private int numRows;
	private int numColumns;
	//Defaults to Dr. Rader's config files
	private String legend;
	private String board;
	private boolean[] visited;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		legend = "ClueLegend.txt";
		board = "ClueLayout.csv";
	}
	
	public Board(String board, String legend ) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		this.board = board;
		this.legend = legend;
	}
	
	public void loadConfigFiles() {
		try {
			this.loadRoomConfig();
			this.loadBoardConfig();
		}
		catch (BadConfigFormatException e) {
			System.out.println(e);
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Load Legend file
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader legendr = new FileReader(legend);
		Scanner input = new Scanner(legendr);
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
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		BoardCell newCell;
		FileReader boardr = new FileReader(board);
		Scanner input = new Scanner(boardr);
		for(int i = 0; i < ROWS; ++i) {
			if(input.hasNextLine()) {
				numRows++;
				String line = input.nextLine();
				String[] parts = line.split(",");
				if(parts.length != COLS) {
					throw new BadConfigFormatException("Too few columns in board file");
				}
				else {
					for(int j = 0; j < COLS; ++j) {
						if(i==0)
							numColumns++;
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

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int calcIndex(int row, int column){
		return (23*row) + column;
	}
	
	public RoomCell getRoomCellAt(int row, int column){
		if(cells.get(calcIndex(row,column)).isRoom())
			return (RoomCell) cells.get(calcIndex(row,column));
		else
			return null;
	}
	
	public BoardCell getCellAt(int location) {
		return cells.get(location);
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
		LinkedList<Integer> adjs = new LinkedList<Integer>();
		for(Integer i : adjMtx.get(location)) {
			if(!visited[i])
				adjs.add(i);
		}
		for(Integer adjCell : adjs) {
			visited[adjCell] = true;
			if(steps == 1) 
				targets.add(adjCell);
			else
				calcTargets(adjCell, steps - 1);
			visited[adjCell] = false;
		}
	}
	
	public void calcAdjacencies(){
		LinkedList<Integer> adjs;
		for(int row = 0; row <= 3; row++) {
			for(int column = 0; column <= 3; column++) {
				adjs = new LinkedList<Integer>();
				if(row == 3) 
					adjs.add(calcIndex(row-1,column));
				else if(row == 0)
					adjs.add(calcIndex(row+1,column));
				else {
					adjs.add(calcIndex(row+1,column));
					adjs.add(calcIndex(row-1,column));
				}

				if(column == 3)
					adjs.add(calcIndex(row,column-1));
				else if(column == 0)
					adjs.add(calcIndex(row,column+1));
				else {
					adjs.add(calcIndex(row,column+1));
					adjs.add(calcIndex(row,column-1));
				}
				adjMtx.put(calcIndex(row,column),adjs);
			}
		}
	}
	
	public HashSet getTargets(){
		return targets;	
	}
	
	public LinkedList<Integer> getAdjList(int location){
		return adjMtx.get(location);
	}
	
	public void startTargets(int location, int steps){
		//empty targets and set visited to false just in case
		targets = new HashSet<Integer>();
		Arrays.fill(visited, false);
		//set start location to true
		visited[location] = true;
		calcTargets(location,steps);
	}
	
}
