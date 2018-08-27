package connect4;

import static org.junit.Assert.*;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

public class testBoard {

  @Test
  public void test_addChip() {
    Board gameboard = new Board();
    gameboard.setisRedTurn(true);
    // Cases in which addChip should return false
    // 1) param i < 0 or i > 6 (aka chip deposit off the grid)
    boolean test1 = gameboard.addChip(-1);
    assertFalse("addChip returns false if param is -1", test1);
    assertTrue("Team is still red", gameboard.getisRedTurn());
    boolean test2 = gameboard.addChip(100);
    assertFalse("addChip returns false if param is 100", test2);
    // 2) columns already full
    // Setting up a full board where no team is winning
    Chip[][] fullgrid = new Chip[6][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < fullgrid.length; r++) {
      if (r % 2 == 0) {
        fullgrid[r] = evens;
      }
      else {
        fullgrid[r] = odds;
      }
    }
    gameboard.setGrid(fullgrid);
    for (int i = 0; i < fullgrid.length; i++) {
      assertFalse("addChip returns false if column is full", gameboard.addChip(i));
    }
    // 3) Any other cases should be fine
    // In this case, boolean must return true
    // And board.grid should reflect that new chip
    // Moreover, turn must change after each addChip call
    gameboard = new Board();
    gameboard.setisRedTurn(true);
    boolean test3 = gameboard.addChip(0);
    assertTrue("addChip returns true if valid int selected", test3);
    assertFalse("calling addChip has changed the turn", gameboard.getisRedTurn());
    assertEquals("board.grid has updated to reflect new chip after addchip call", ((Chip) gameboard.getGrid()[5][0]).getChipcolor(), "Red");
  }
  
  @Test
  public void test_isVerticalWin() {
    // Should return true for 2 cases:
    // Red win and Blue win
    // - Setting up a board in which Red has a vertical win
    Board gameboard = new Board();
    Chip[][] verticalwinforteamred = new Chip[6][7];
    int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
    for (int i = 5; i > 1; i--) {
      verticalwinforteamred[i][randomNum] = new Chip("Red");
    }
    gameboard.setGrid(verticalwinforteamred);
    boolean testcase1 = gameboard.isVerticalWin("Red");
    boolean testcase1_cont = gameboard.isVerticalWin("Blue");
    assertTrue("isVerticalWin('Red') returns true?",testcase1);
    assertFalse("isVerticalWin('Blue') returns false?", testcase1_cont);
    // - Setting up a board in which Blue has a vertical win
    Chip[][] verticalwinforteamBlue = new Chip[6][7];
    randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
    for (int i = 5; i > 1; i--) {
      verticalwinforteamBlue[i][randomNum] = new Chip("Blue");
    }
    gameboard.setGrid(verticalwinforteamBlue);
    boolean testcase2 = gameboard.isVerticalWin("Blue");
    boolean testcase2_cont = gameboard.isVerticalWin("Red");
    assertTrue("isVerticalWin('Blue') returns true?", testcase2);
    assertFalse("isVerticalWin('Red') returns false?", testcase2_cont);
    // Should return false for any other case
    // - Setting up a board in which no vertical win
    Chip[][] tieboard = new Chip[6][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < tieboard.length; r++) {
      if (r % 2 == 0) {
        tieboard[r] = evens;
      }
      else {
        tieboard[r] = odds;
      }
    }
    gameboard.setGrid(tieboard);
    boolean testcase3 = gameboard.isVerticalWin("Red");
    boolean testcase3_cont = gameboard.isVerticalWin("Blue");
    assertFalse("Board in which no vertical win returns false for red win?", testcase3);
    assertFalse("Board in which no vertical win returns false for Blue win?", testcase3_cont);
    // - Empty board case
    Chip[][] emptyboard = new Chip[6][7];
    gameboard.setGrid(emptyboard);
    boolean testcase4 = gameboard.isVerticalWin("Red");
    boolean testcase4_cont = gameboard.isVerticalWin("Blue");
    assertFalse("Empty board returns false for red vertical win?", testcase4);
    assertFalse("Empty board returns false for Blue vertical win?", testcase4_cont);
  }
  
  @Test
  public void test_isHorizontalWin() {
    // Should return true for 2 cases:
    // Red win and Blue win
    // - Setting up a board in which Red has a horizontal win
    Board gameboard = new Board();
    Chip[][] horizontalwinforteamred = new Chip[6][7];
    int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
    for (int i = 6; i > 2; i--) {
      horizontalwinforteamred[randomNum][i] = new Chip("Red");
    }
    gameboard.setGrid(horizontalwinforteamred);
    boolean testcase1 = gameboard.isHorizontalWin("Red");
    boolean testcase1_cont = gameboard.isHorizontalWin("Blue");
    assertTrue("isHorizontalWin('Red') returns true?",testcase1);
    assertFalse("isHorizontalWin('Blue') returns false?", testcase1_cont);
    // - Setting up a board in which Blue has a vertical win
    Chip[][] horizontalwinforteamBlue = new Chip[6][7];
    randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
    for (int i = 6; i > 2; i--) {
      horizontalwinforteamBlue[randomNum][i] = new Chip("Blue");
    }
    gameboard.setGrid(horizontalwinforteamBlue);
    boolean testcase2 = gameboard.isHorizontalWin("Blue");
    boolean testcase2_cont = gameboard.isHorizontalWin("Red");
    assertTrue("isHorizontalWin('Blue') returns true?", testcase2);
    assertFalse("isHorizontalWin('Red') returns false?", testcase2_cont);
    // Should return false for any other case
    // - Setting up a board in which no horizontal win
    Chip[][] tieboard = new Chip[6][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < tieboard.length; r++) {
      if (r % 2 == 0) {
        tieboard[r] = evens;
      }
      else {
        tieboard[r] = odds;
      }
    }
    gameboard.setGrid(tieboard);
    boolean testcase3 = gameboard.isHorizontalWin("Red");
    boolean testcase3_cont = gameboard.isHorizontalWin("Blue");
    assertFalse("Board in which no horizontal win returns false for red win?", testcase3);
    assertFalse("Board in which no horizontal win returns false for Blue win?", testcase3_cont);
    // - Empty board case
    Chip[][] emptyboard = new Chip[6][7];
    gameboard.setGrid(emptyboard);
    boolean testcase4 = gameboard.isHorizontalWin("Red");
    boolean testcase4_cont = gameboard.isHorizontalWin("Blue");
    assertFalse("Empty board returns false for red horizontal win?", testcase4);
    assertFalse("Empty board returns false for Blue hortizontal win?", testcase4_cont);
  }
  
  @Test
  public void test_isDiagonalWin() {
    // Should return true for 2 cases:
    // Red win and Blue win
    // - Setting up a board in which Red has a diagonal win
    Board gameboard = new Board();
    Chip[][] diagonalwinforteamred = new Chip[6][7];
    int possiblerowforstart = ThreadLocalRandom.current().nextInt(0, 2 + 1);
    int possiblecolforstart = ThreadLocalRandom.current().nextInt(0, 3 + 1);
    for (int i = 0; i < 4; i++) {
      diagonalwinforteamred[possiblerowforstart+i][possiblecolforstart+i] = new Chip("Red");
    }
    gameboard.setGrid(diagonalwinforteamred);
    boolean testcase1 = gameboard.isDiagonalWin("Red");
    boolean testcase1_cont = gameboard.isDiagonalWin("Blue");
    assertTrue("isDiagonalWin('Red') returns true?",testcase1);
    assertFalse("isDiagonalWin('Blue') returns false?", testcase1_cont);
    // - Setting up a board in which Blue has a diagonal win
    Chip[][] diagonalwinforteamBlue = new Chip[7][7];
    possiblerowforstart = ThreadLocalRandom.current().nextInt(3, 5 + 1);
    possiblecolforstart = ThreadLocalRandom.current().nextInt(0, 3 + 1);
    for (int i = 0; i < 4; i++) {
      diagonalwinforteamBlue[possiblerowforstart-i][possiblecolforstart+i] = new Chip("Blue");
    }
    gameboard.setGrid(diagonalwinforteamBlue);
    boolean testcase2 = gameboard.isDiagonalWin("Blue");
    boolean testcase2_cont = gameboard.isDiagonalWin("Red");
    assertTrue("isDiagonalWin('Blue') returns true?", testcase2);
    assertFalse("isDiagonalWin('Red') returns false?", testcase2_cont);
    // Should return false for any other case
    // - Setting up a board in which no vertical win
    Chip[][] tieboard = new Chip[7][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < tieboard.length; r++) {
      if (r % 2 == 0) {
        tieboard[r] = evens;
      }
      else {
        tieboard[r] = odds;
      }
    }
    gameboard.setGrid(tieboard);
    boolean testcase3 = gameboard.isDiagonalWin("Red");
    boolean testcase3_cont = gameboard.isDiagonalWin("Blue");
    assertFalse("Board in which no diagonal win returns false for red win?", testcase3);
    assertFalse("Board in which no diagonal win returns false for Blue win?", testcase3_cont);
    // - Empty board case
    Chip[][] emptyboard = new Chip[7][7];
    gameboard.setGrid(emptyboard);
    boolean testcase4 = gameboard.isDiagonalWin("Red");
    boolean testcase4_cont = gameboard.isDiagonalWin("Blue");
    assertFalse("Empty board returns false for red diagonal win?", testcase4);
    assertFalse("Empty board returns false for Blue diagonal win?", testcase4_cont);
  }
  
  @Test
  public void test_isWinningState() {
    // Two cases: either winning state or not
    // - Winning state returns true for vertical, horizontal, and/or diagonal wins
    // Vertical
    Board gameboard = new Board();
    Chip[][] verticalwinforteamred = new Chip[6][7];
    int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
    for (int i = 5; i > 1; i--) {
      verticalwinforteamred[i][randomNum] = new Chip("Red");
    }
    gameboard.setGrid(verticalwinforteamred);
    boolean test1 = gameboard.isWinningState();
    assertTrue("isWinningState detects vertical win?", test1);
    // Horizontal
    Chip[][] horizontalwinforteamBlue = new Chip[6][7];
    randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
    for (int i = 6; i > 2; i--) {
      horizontalwinforteamBlue[randomNum][i] = new Chip("Blue");
    }
    gameboard.setGrid(horizontalwinforteamBlue);
    boolean test2 = gameboard.isWinningState();
    assertTrue("isWinningState detects horizontal win?", test2);
    // Diagonal
    Chip[][] diagonalwinforteamred = new Chip[6][7];
    int possiblerowforstart = ThreadLocalRandom.current().nextInt(0, 2 + 1);
    int possiblecolforstart = ThreadLocalRandom.current().nextInt(0, 3 + 1);
    for (int i = 0; i < 4; i++) {
      diagonalwinforteamred[possiblerowforstart+i][possiblecolforstart+i] = new Chip("Red");
    }
    gameboard.setGrid(diagonalwinforteamred);
    boolean test3 = gameboard.isWinningState();
    assertTrue("isWinningState detects diagonal win?", test3);
    // - Returns false otherwise
    // Tie
    Chip[][] tieboard = new Chip[6][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < tieboard.length; r++) {
      if (r % 2 == 0) {
        tieboard[r] = evens;
      }
      else {
        tieboard[r] = odds;
      }
    }
    gameboard.setGrid(tieboard);
    boolean test4 = gameboard.isWinningState();
    assertFalse("isWinningState detects no win for tie board?", test4);
    Chip[][] emptyboard = new Chip[6][7];
    gameboard.setGrid(emptyboard);
    boolean test5 = gameboard.isWinningState();
    assertFalse("IsWiningState detects no win for empty board?", test5);
  }
  
  @Test
  public void test_isTie() {
    // Only returns true if there is a tie
    // Aka board is full and no one is winning
    // - Board full and no one winning
    Board gameboard = new Board();
    Chip[][] tieboard = new Chip[7][7];
    Chip[] evens = new Chip[7];
    for (int i = 0; i < evens.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        evens[i] = new Chip("Red");
      }
      else if (i % 2 == 0) {
        evens[i] = new Chip("Red");
      }
      else {
        evens[i] = new Chip("Blue");
      }
    }
    Chip[] odds = new Chip[7];
    for (int i = 0 ; i < odds.length; i++) {
      if (i ==  2 || i == 3 || i == 4) {
        odds[i] = new Chip("Blue");
      }
      else if (i % 2 == 0) {
        odds[i] = new Chip("Blue");
      }
      else {
        odds[i] = new Chip("Red");
      }
    }
    for (int r = 0; r < tieboard.length; r++) {
      if (r % 2 == 0) {
        tieboard[r] = evens;
      }
      else {
        tieboard[r] = odds;
      }
    }
    gameboard.setGrid(tieboard);
    boolean test1 = gameboard.isTie();
    assertTrue("isTie detects tie and returns true? (when board full and no one winning)", test1);
    // - Board full but someone is winning
    for (int r = 0; r < tieboard.length; r++) {
      for (int c = 0; c < tieboard[r].length; c++) {
        tieboard[r][c] = new Chip("Red");
      }
    }
    boolean test2 = gameboard.isTie();
    assertFalse("isTie detects fake tie? (when board full and someone winning)", test2);
    // - Board not full 
    Chip[][] emptyboard = new Chip[7][7];
    gameboard.setGrid(emptyboard);
    boolean test3 = gameboard.isTie();
    assertFalse("isTie returns false correctly? (when board not full)", test3);
  }
  
  @Test
  public void test_Board() {
    Board gameboard = new Board();
    assertEquals(gameboard.getGrid().length, 6);
    for (int r = 0; r < gameboard.getGrid().length; r++) {
      assertEquals(gameboard.getGrid()[r].length, 7);
      for (int c = 0; c < gameboard.getGrid()[r].length; c++) {
        assertNull("all chip slots initialized to null?", gameboard.getGrid()[r][c]);
      }
    }
    assertTrue("isRedTurn set to true?", gameboard.getisRedTurn());
    assertNull("winner set to null?", gameboard.getWinner());
  }
}
