package Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

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

	//Check number of rooms in legend is correct
	@Test
	public void testLegend() {
		int lines = 0;
		try {
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File("Legend.txt")));
		lnr.skip(Long.MAX_VALUE);
		lines = lnr.getLineNumber();
		}
		catch (IOException e) {
			System.err.println("Legend.txt does not exist");
		}
		Assert.assertEquals(11, lines);
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

}
