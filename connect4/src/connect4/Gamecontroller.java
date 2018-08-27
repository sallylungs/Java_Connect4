package connect4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Gamecontroller implements ActionListener {

  View view;
  Board board;
  
  public Gamecontroller(View v, Board b) {
    view = v;
    board = b;
  }

  public String getTurn() {
    if (board.getisRedTurn()) {
      return "Red";
    }
    else {
      return "Blue";
    }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!board.isWinningState() || !board.isTie()) {
        System.out.println("Let them press a button");
        Object source = e.getSource();
        if (source instanceof JButton) {
          JButton choice = (JButton) source;
          String nameofbutton = choice.getName();
          char columnchar = nameofbutton.charAt(2);
          
          int columnNum = Character.getNumericValue(columnchar);
          System.out.println(this.getTurn());
          view.addChip(columnNum, this.getTurn());
          board.addChip(columnNum);
          view.updateTurn(this.getTurn());
        }
      }
    boolean isThereAWinnerNow = board.isWinningState();
    boolean howAboutATie = board.isTie();
    if (isThereAWinnerNow) {
      view.win(board.getWinner());
    }
    else if (howAboutATie) {
      view.tie();
    }
  }
}
  
  
