package connect4;

/**
 * Class Board for Connect Four game.
 * Contains methods to set up board and functionality in C4.
 * @author Sally
 *
 */
public class Board {
  
  /** 6x7 grid that holds Chip objects */
  private Chip[][] grid;
  /** Keeps track of turn (either or red or Blue) */
  private boolean isRedTurn;
  /** Winner: can be Red, Blue, or None if game in winning state or tie; otherwise, null */
  private String winner;
  
  /** 
   * Constructor for Connect 4 Board class 
   * */ 
  public Board() {
    grid = new Chip[6][7];
    isRedTurn = true;
    winner = null;
  }
  
  /** 
   * Allows player to add a chip. If chip placed in a valid space on the board, returns true. Otherwise, returns false. 
   * @param integer that represents the column to add chip
   * @return true if chip is placed in a legal spot
   * @author Sally
   * */
  public boolean addChip(int i) {
    // did user select a valid int for the column?
    boolean isValid = ((i >= 0) && (i <= 6));
    // stringing turn for chip constructor
    String turn;
    if (isRedTurn) {turn = "Red";}
    else {turn = "Blue";}
    // if they did, check for an empty space
    // if they're all full, return false
    if (isValid) {
      // for debugging: System.out.println(("isValid is true"));
      for (int row_index = 5; row_index >= 0; row_index--) {
        if (grid[row_index][i] == null) {
          // for debugging: System.out.println("grid["+row_index+"]["+i+"] is null");
          grid[row_index][i] = new Chip(turn);
          if (isRedTurn) {isRedTurn = false;}
          else {isRedTurn = true;}
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  /** 
   * Checks if board contains a winning state. 
   * @return true if chips align in a winning state of Connect 4
   * @author Sally
   * */
  public boolean isWinningState() {
    boolean vertwin = (isVerticalWin("Red") || isVerticalWin("Blue"));
    boolean horiwin = (isHorizontalWin("Red") || isHorizontalWin("Blue"));
    boolean diagwin = (isDiagonalWin("Red") || isDiagonalWin("Blue"));
    if (vertwin || horiwin || diagwin) {
      return true;
    }
    return false;
  }
  
  public boolean isTie() {
    boolean isanyonewinning = isWinningState();
    if (!isanyonewinning) {
      for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[r].length; c++) {
          if (grid[r][c] == null) {
            return false;
          }
        }
       }
      return true;
    }
    return false;
  }

  public boolean isVerticalWin(String c) {
    for (int col = 0; col < 7; col++) {
      for (int row = 0; row < 3; row++) {
          if (grid[row][col] != null && grid[row][col].getChipcolor().equals(c) &&
              grid[row+1][col] != null && grid[row + 1][col].getChipcolor().equals(c) &&
              grid[row+2][col] != null && grid[row + 2][col].getChipcolor().equals(c) &&
              grid[row+3][col] != null && grid[row + 3][col].getChipcolor().equals(c)) {
            winner = c;   
            return true;
             }
       }
     }
     return false;
  }
  
  public boolean isHorizontalWin(String c) {
    for (int r=0; r<6; r++) {
      for (int c1=0; c1<4; c1++) {
          if (grid[r][c1] != null && grid[r][c1].getChipcolor().equals(c) &&
             grid[r][c1+1] != null && grid[r][c1 + 1].getChipcolor().equals(c)&&
             grid [r][c1+2] != null && grid[r][c1 + 2].getChipcolor().equals(c) &&
             grid[r][c1+3] != null && grid[r][c1 + 3].getChipcolor().equals(c)) {
            winner = c;
            return true;
            }
      }
    }
    return false;
  }
  
  public boolean isDiagonalWin(String c) {
    if (isDiagonalWinLeftToRight(c)) {
      return true;
    }
    if (isDiagonalWinRightToLeft(c)) {
      return true;
    }
    return false;
  }
  
  private boolean isDiagonalWinLeftToRight(String c) {
    for (int row = 3; row < 6; row++) {
      for (int col = 0; col < 4; col++) {
          if (grid[row][col] != null && grid[row][col].getChipcolor().equals(c) &&
              grid[row-1][col+1] != null && grid[row-1][col+1].getChipcolor().equals(c) &&
              grid[row-2][col+2] != null && grid[row-2][col+2].getChipcolor().equals(c) &&
              grid[row-3][col+3] != null && grid[row-3][col+3].getChipcolor().equals(c)) {
            winner = c;
            return true;
          }
      }
    }
    return false;
  }
  
  private boolean isDiagonalWinRightToLeft(String c) {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 4; col++) {
          if (grid[row][col] != null && grid[row][col].getChipcolor().equals(c) &&
              grid[row+1][col+1] != null && grid[row+1][col+1].getChipcolor().equals(c) &&
              grid[row+2][col+2] != null && grid[row+2][col+2].getChipcolor().equals(c) &&
              grid[row+3][col+3] != null && grid[row+3][col+3].getChipcolor().equals(c)) {
            winner = c;
            return true;
          }
      }
    }
    return false;
  }
   
  public boolean getisRedTurn() {
    return isRedTurn;
  }

  public void setisRedTurn(boolean b) {
    isRedTurn = b;
  }
  public Object[][] getGrid() {
    return grid;
  }

  public void setGrid(Chip[][] fullgrid) {
    grid = fullgrid;
  }

  public String getWinner() {
    return winner;
  }

}
