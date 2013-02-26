package Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Board.Board;
import Board.BadConfigFormatException;
import Board.BoardCell;
import Board.RoomCell;
import Board.WalkwayCell;


public class ClueBoardTest {
	Board board;
	RoomCell cell;
	
	@Before
	public void beforeTest() {
		board = new Board();
		board.loadConfigFiles();	
	}

	
	//Check mapping and number of rooms
	@Test
	public void testMapping() {
		Map<Character, String> rooms = board.getRooms();
		Assert.assertEquals(11, rooms.size());
		Assert.assertEquals("Closet", rooms.get("X"));
		Assert.assertEquals("Walkway", rooms.get("W"));
		Assert.assertEquals("Conservatory", rooms.get("C"));
		Assert.assertEquals("Kitchen", rooms.get("K")); 
		Assert.assertEquals("Ballroom", rooms.get("B"));
		Assert.assertEquals("Billiard Room", rooms.get("R"));
		Assert.assertEquals("Library", rooms.get("L"));
		Assert.assertEquals("Study", rooms.get("S"));
		Assert.assertEquals("Dining Room", rooms.get("D"));
		Assert.assertEquals("Lounge", rooms.get("O"));
		Assert.assertEquals("Hall", rooms.get("H"));
	}
	
	//Check room initials
	@Test
	public void testRoomInitial() {
		//Test Kitchen
		cell = (RoomCell) board.GetRoomCellAt(0, 0);
		Assert.assertEquals("K", cell.getRoomInitial());
		//Test Conservatory
		cell = (RoomCell) board.GetRoomCellAt(2, 8);
		Assert.assertEquals("C", cell.getRoomInitial());
		//Test Hall
		cell = (RoomCell) board.GetRoomCellAt(3, 14);
		Assert.assertEquals("H", cell.getRoomInitial());
		//Test Library
		cell = (RoomCell) board.GetRoomCellAt(6, 22);
		Assert.assertEquals("L", cell.getRoomInitial());
		//Test Ballroom
		cell = (RoomCell) board.GetRoomCellAt(11, 0);
		Assert.assertEquals("B", cell.getRoomInitial());
		//Test Lounge
		cell = (RoomCell) board.GetRoomCellAt(15, 22);
		Assert.assertEquals("O", cell.getRoomInitial());
		//Test Billiard Room
		cell = (RoomCell) board.GetRoomCellAt(22, 0);
		Assert.assertEquals("R", cell.getRoomInitial());
		//Test Dining Room
		cell = (RoomCell) board.GetRoomCellAt(21, 10);
		Assert.assertEquals("D", cell.getRoomInitial());
		//Test Study
		cell = (RoomCell) board.GetRoomCellAt(22, 22);
		Assert.assertEquals("S", cell.getRoomInitial());
	}
	
	//Check number of rooms in board is correct
	@Test
	public void testNumRooms() {
		Assert.assertEquals(23, board.getNumRows());
		Assert.assertEquals(23, board.getNumColumns());
	}
	
	//Check non doors
	@Test
	public void testNonDoors() {
		//test upper left corner
		cell = (RoomCell) board.GetRoomCellAt(0, 0);
		Assert.assertEquals(false, cell.isDoorway());
		//test bottom right corner
		cell = (RoomCell) board.GetRoomCellAt(22, 22);
		Assert.assertEquals(false, cell.isDoorway());
		//test top edge
		cell = (RoomCell) board.GetRoomCellAt(0, 10);
		Assert.assertEquals(false, cell.isDoorway());
		//test left edge
		cell = (RoomCell) board.GetRoomCellAt(18, 0);
		Assert.assertEquals(false, cell.isDoorway());
		//test right edge
		cell = (RoomCell) board.GetRoomCellAt(7, 22);
		Assert.assertEquals(false, cell.isDoorway());
		//test bottom edge
		cell = (RoomCell) board.GetRoomCellAt(22, 10);
		Assert.assertEquals(false, cell.isDoorway());
		//test center cells
		cell = (RoomCell) board.GetRoomCellAt(12, 12);
		Assert.assertEquals(false, cell.isDoorway());
		cell = (RoomCell) board.GetRoomCellAt(10, 3);
		Assert.assertEquals(false, cell.isDoorway());
		cell = (RoomCell) board.GetRoomCellAt(3, 10);
		Assert.assertEquals(false, cell.isDoorway());
	}
	
	//Test door direction
	@Test
	public void testDoorDirection() {
		//Kitchen door
		cell = (RoomCell) board.GetRoomCellAt(3, 4);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
		//Conservatory door
		cell = (RoomCell) board.GetRoomCellAt(1, 10);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		//Hall door
		cell = (RoomCell) board.GetRoomCellAt(4, 13);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
		//Library doors
		cell = (RoomCell) board.GetRoomCellAt(6, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(7, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		//Ballroom doors
		cell = (RoomCell) board.GetRoomCellAt(10, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(11, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(9, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		//Lounge door
		cell = (RoomCell) board.GetRoomCellAt(13, 16);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		//Billiard room door
		cell = (RoomCell) board.GetRoomCellAt(17, 3);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("UP", cell.getDoorDirection());
		//Dining room doors
		cell = (RoomCell) board.GetRoomCellAt(17, 8);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(18, 8);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		//Study door
		cell = (RoomCell) board.GetRoomCellAt(18, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
	}
	
	//Test calcIndex
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(0,board.calcIndex(0, 0));
		Assert.assertEquals(528,board.calcIndex(22, 22));
		Assert.assertEquals(396,board.calcIndex(17, 5));
		Assert.assertEquals(261,board.calcIndex(11, 19));
		Assert.assertEquals(461,board.calcIndex(20, 1));
	}
	
	//Test exception tossing
	@Test (expected = BadConfigFormatException.class)
	public void testException() throws BadConfigFormatException {
		board.loadConfigFiles();
	}

}
