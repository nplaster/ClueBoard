package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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

	// Check mapping and number of rooms
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

	// Check room initials
	@Test
	public void testRoomInitial() {
		// Test Kitchen
		cell = (RoomCell) board.GetRoomCellAt(0, 0);
		Assert.assertEquals("K", cell.getRoomInitial());
		// Test Conservatory
		cell = (RoomCell) board.GetRoomCellAt(2, 8);
		Assert.assertEquals("C", cell.getRoomInitial());
		// Test Hall
		cell = (RoomCell) board.GetRoomCellAt(3, 14);
		Assert.assertEquals("H", cell.getRoomInitial());
		// Test Library
		cell = (RoomCell) board.GetRoomCellAt(6, 22);
		Assert.assertEquals("L", cell.getRoomInitial());
		// Test Ballroom
		cell = (RoomCell) board.GetRoomCellAt(11, 0);
		Assert.assertEquals("B", cell.getRoomInitial());
		// Test Lounge
		cell = (RoomCell) board.GetRoomCellAt(15, 22);
		Assert.assertEquals("O", cell.getRoomInitial());
		// Test Billiard Room
		cell = (RoomCell) board.GetRoomCellAt(22, 0);
		Assert.assertEquals("R", cell.getRoomInitial());
		// Test Dining Room
		cell = (RoomCell) board.GetRoomCellAt(21, 10);
		Assert.assertEquals("D", cell.getRoomInitial());
		// Test Study
		cell = (RoomCell) board.GetRoomCellAt(22, 22);
		Assert.assertEquals("S", cell.getRoomInitial());
	}

	// Check number of rooms in board is correct
	@Test
	public void testNumRooms() {
		Assert.assertEquals(23, board.getNumRows());
		Assert.assertEquals(23, board.getNumColumns());
	}

	// Check non doors
	@Test
	public void testNonDoors() {
		// test upper left corner
		cell = (RoomCell) board.GetRoomCellAt(0, 0);
		Assert.assertEquals(false, cell.isDoorway());
		// test bottom right corner
		cell = (RoomCell) board.GetRoomCellAt(22, 22);
		Assert.assertEquals(false, cell.isDoorway());
		// test top edge
		cell = (RoomCell) board.GetRoomCellAt(0, 10);
		Assert.assertEquals(false, cell.isDoorway());
		// test left edge
		cell = (RoomCell) board.GetRoomCellAt(18, 0);
		Assert.assertEquals(false, cell.isDoorway());
		// test right edge
		cell = (RoomCell) board.GetRoomCellAt(7, 22);
		Assert.assertEquals(false, cell.isDoorway());
		// test bottom edge
		cell = (RoomCell) board.GetRoomCellAt(22, 10);
		Assert.assertEquals(false, cell.isDoorway());
		// test center cells
		cell = (RoomCell) board.GetRoomCellAt(12, 12);
		Assert.assertEquals(false, cell.isDoorway());
		cell = (RoomCell) board.GetRoomCellAt(10, 3);
		Assert.assertEquals(false, cell.isDoorway());
		cell = (RoomCell) board.GetRoomCellAt(3, 10);
		Assert.assertEquals(false, cell.isDoorway());
	}

	// Test door direction
	@Test
	public void testDoorDirection() {
		// Kitchen door
		cell = (RoomCell) board.GetRoomCellAt(3, 4);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
		// Conservatory door
		cell = (RoomCell) board.GetRoomCellAt(1, 10);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		// Hall door
		cell = (RoomCell) board.GetRoomCellAt(4, 13);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
		// Library doors
		cell = (RoomCell) board.GetRoomCellAt(6, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(7, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		// Ballroom doors
		cell = (RoomCell) board.GetRoomCellAt(10, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(11, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(9, 5);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("RIGHT", cell.getDoorDirection());
		// Lounge door
		cell = (RoomCell) board.GetRoomCellAt(13, 16);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		// Billiard room door
		cell = (RoomCell) board.GetRoomCellAt(17, 3);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("UP", cell.getDoorDirection());
		// Dining room doors
		cell = (RoomCell) board.GetRoomCellAt(17, 8);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		cell = (RoomCell) board.GetRoomCellAt(18, 8);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("LEFT", cell.getDoorDirection());
		// Study door
		cell = (RoomCell) board.GetRoomCellAt(18, 19);
		Assert.assertEquals(true, cell.isDoorway());
		Assert.assertEquals("DOWN", cell.getDoorDirection());
	}

	// Test calcIndex
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(0, board.calcIndex(0, 0));
		Assert.assertEquals(528, board.calcIndex(22, 22));
		Assert.assertEquals(396, board.calcIndex(17, 5));
		Assert.assertEquals(261, board.calcIndex(11, 19));
		Assert.assertEquals(461, board.calcIndex(20, 1));
	}

	// Test exception tossing
	@Test(expected = BadConfigFormatException.class)
	public void testException() throws BadConfigFormatException {
		board.loadConfigFiles();
	}
	
	//Test walkways and edge cases
	@Test
	public void testWalkwayAdjacent(){
		//Location 145
		LinkedList<Integer> testList = board.getAdjList(145);
		Assert.assertTrue(testList.contains(144));
		Assert.assertTrue(testList.contains(146));
		Assert.assertTrue(testList.contains(122));
		Assert.assertTrue(testList.contains(168));
		Assert.assertEquals(4, testList.size());
		
		//Location 92
		testList = board.getAdjList(92);
		Assert.assertTrue(testList.contains(115));
		Assert.assertEquals(1, testList.size());
		
		//Location 129
		testList = board.getAdjList(129);
		Assert.assertTrue(testList.contains(128));
		Assert.assertTrue(testList.contains(152));
		Assert.assertEquals(2, testList.size());
		
		//Location 41
		testList = board.getAdjList(41);
		Assert.assertTrue(testList.contains(40));
		Assert.assertTrue(testList.contains(64));
		Assert.assertTrue(testList.contains(18));
		Assert.assertEquals(3, testList.size());
		
		//Location 413
		testList = board.getAdjList(413);
		Assert.assertTrue(testList.contains(412));
		Assert.assertTrue(testList.contains(390));
		Assert.assertEquals(2, testList.size());
		
		//Location 521
		testList = board.getAdjList(521);
		Assert.assertTrue(testList.contains(520));
		Assert.assertTrue(testList.contains(498));
		Assert.assertEquals(2, testList.size());
	}
	
	//Test Locations beside a room not a doorway
	@Test
	public void testNotDoorwayLocation(){
		//Location 328
		LinkedList<Integer> testList = board.getAdjList(328);
		Assert.assertTrue(testList.contains(329));
		Assert.assertTrue(testList.contains(305));
		Assert.assertTrue(testList.contains(351));
		Assert.assertEquals(3, testList.size());
		
		//Location 222
		testList = board.getAdjList(222);
		Assert.assertTrue(testList.contains(221));
		Assert.assertTrue(testList.contains(199));
		Assert.assertTrue(testList.contains(245));
		Assert.assertEquals(3, testList.size());
	}
	
	//Test Locations Adjacent to a doorway
	@Test
	public void testDoorwayAdjacent(){
		//Location 96
		LinkedList<Integer> testList = board.getAdjList(96);
		Assert.assertTrue(testList.contains(97));
		Assert.assertTrue(testList.contains(73));
		Assert.assertTrue(testList.contains(119));
		Assert.assertEquals(3, testList.size());
		
		//Location 156
		testList = board.getAdjList(156);
		Assert.assertTrue(testList.contains(155));
		Assert.assertTrue(testList.contains(157));
		Assert.assertTrue(testList.contains(133));
		Assert.assertTrue(testList.contains(179));
		Assert.assertEquals(4, testList.size());
		
		//Location 213
		testList = board.getAdjList(213);
		Assert.assertTrue(testList.contains(212));
		Assert.assertTrue(testList.contains(214));
		Assert.assertTrue(testList.contains(190));
		Assert.assertTrue(testList.contains(236));
		Assert.assertEquals(4, testList.size());
		
		//Location 410
		testList = board.getAdjList(410);
		Assert.assertTrue(testList.contains(411));
		Assert.assertTrue(testList.contains(409));
		Assert.assertTrue(testList.contains(387));
		Assert.assertTrue(testList.contains(433));
		Assert.assertEquals(4, testList.size());
		
	}
	
	//Test Doorways
	public void testDoorways(){
		//Location 105
		LinkedList<Integer> testList = board.getAdjList(105);
		Assert.assertTrue(testList.contains(128));
		Assert.assertEquals(1, testList.size());
		
		//Location 180
		testList = board.getAdjList(180);
		Assert.assertTrue(testList.contains(179));
		Assert.assertEquals(1, testList.size());
		
		//Location 235
		testList = board.getAdjList(235);
		Assert.assertTrue(testList.contains(236));
		Assert.assertEquals(1, testList.size());
		
		//Location 394
		testList = board.getAdjList(394);
		Assert.assertTrue(testList.contains(371));
		Assert.assertEquals(1, testList.size());
	}
	
	//TestTargets Walkways
	@Test
	public void testTargetWalkways(){
		//Location Row 16 Column 1
		board.startTargets(board.calcIndex(16, 1), 1);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
		//Location Row 14 Column 13
		board.startTargets(board.calcIndex(14, 13), 5);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
		//Location Row 22 Column 14
		board.startTargets(board.calcIndex(22, 14), 6);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
		//Location Row 19 Column 10
		board.startTargets(board.calcIndex(19, 10), 2);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
	}

	
	//TestTargets Leaving rooms
	@Test
	public void testTargetsExit(){
		//Location Row 3 Column 4
		board.startTargets(board.calcIndex(3, 4), 1);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
		//Location Row 18 Column 8
		board.startTargets(board.calcIndex(18, 8), 4);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
	}
	
	//TestTargets Entering rooms
	@Test
	public void testTargetsEnter(){
		//Location Row 1 Column 12
		board.startTargets(board.calcIndex(1, 12), 3);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
		//Location Row 11 Column 6
		board.startTargets(board.calcIndex(11, 6), 1);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		
	}

}
