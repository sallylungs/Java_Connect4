package connect4;

public class Chip {
  private String chipcolor;
  
  /**
   * Constructor for Space class.
   * @param
   */
  public Chip(String c) {
    setChipcolor(c);
  }

  public String getChipcolor() {
    return chipcolor;
  }

  public void setChipcolor(String chipcolor) {
    this.chipcolor = chipcolor;
  }
  
}
