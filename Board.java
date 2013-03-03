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
	private HashSet<BoardCell> targets;
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
		targets = new HashSet<BoardCell>();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		legend = "ClueLegend.txt";
		board = "ClueLayout.csv";
		visited = new boolean[numRooms];
		Arrays.fill(visited,false);
	}
	
	public Board(String board, String legend ) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		this.board = board;
		this.legend = legend;
		visited = new boolean[numRooms];
		Arrays.fill(visited,false);
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
		while(input.hasNextLine()) {
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
	
	//calcTargets with location
	public void calcTargets(int location, int steps) {
		LinkedList<BoardCell> adjs = new LinkedList<BoardCell>();
		for(Integer i : adjMtx.get(location)) {
			if(!visited[i])
				adjs.add(cells.get(i));
		}
		for(BoardCell adjCell : adjs) {
			visited[calcIndex(adjCell.getRow(), adjCell.getColumn())] = true;
			if(steps == 1) 
				targets.add(adjCell);
			else
				calcTargets(calcIndex(adjCell.getRow(), adjCell.getColumn()), steps - 1);
			visited[calcIndex(adjCell.getRow(), adjCell.getColumn())] = false;
		}
	}
	
	//calcTargets with coordinates
	public void calcTargets(int row, int column, int steps) {
		int location = calcIndex(row,column);
		LinkedList<BoardCell> adjs = new LinkedList<BoardCell>();
		for(Integer i : adjMtx.get(location)) {
			if(!visited[i])
				adjs.add(cells.get(location));
		}
		for(BoardCell adjCell : adjs) {
			visited[calcIndex(adjCell.getRow(), adjCell.getColumn())] = true;
			if(steps == 1) 
				targets.add(adjCell);
			else
				calcTargets(calcIndex(adjCell.getRow(), adjCell.getColumn()), steps - 1);
			visited[calcIndex(adjCell.getRow(), adjCell.getColumn())] = false;
		}
	}
	
	public void calcAdjacencies(){
		LinkedList<Integer> adjs;
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				adjs = new LinkedList<Integer>();
				//check down
				if(row < 22)
					checkAdjacency(row + 1, column, adjs);
				//check up
				if(row > 0)
					checkAdjacency(row - 1, column, adjs);
				//check left
				if(column > 0)
					checkAdjacency(row, column - 1, adjs);
				//check right
				if(column < 22)
					checkAdjacency(row, column + 1, adjs);
				adjMtx.put(calcIndex(row,column),adjs);
			}
		}
	}
	
	//Checks if the adjacent cell is a walkway or a door you can enter
	public void checkAdjacency(int row, int column, LinkedList<Integer> adjs) {
		int location = calcIndex(row,column);
		if(cells.get(location).isWalkway())
			adjs.add(location);
		else if (cells.get(location).isDoorway()) {
			RoomCell test = (RoomCell) cells.get(location);
			RoomCell.DoorDirection direction = test.getDoorDirection();
			switch (direction) {
			case DOWN :
				if(test.getRow() + 1 == row) {
					adjs.add(location);
				}
				break;
			case UP :
				if(test.getRow() - 1 == row)
					adjs.add(location);
				break;
			case RIGHT :
				if(test.getColumn() + 1 == column)
					adjs.add(location);
				break;
			case LEFT :
				if(test.getColumn() -1 == column)
					adjs.add(location);
				break;
			}
		}
	}
	
	public HashSet<BoardCell> getTargets(){
		return targets;	
	}
	
	public LinkedList<Integer> getAdjList(int location){
		return adjMtx.get(location);
	}
	
	public void startTargets(int location, int steps){
		//empty targets and set visited to false just in case
		targets = new HashSet<BoardCell>();
		Arrays.fill(visited, false);
		//set start location to true
		visited[location] = true;
		calcTargets(location,steps);
	}
	
}
