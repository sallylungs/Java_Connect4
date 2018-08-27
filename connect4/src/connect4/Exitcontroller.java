package connect4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Exitcontroller implements ActionListener {

  View view;
  
  public Exitcontroller(View v) {
    view = v;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source instanceof JButton) {
      JButton choice = (JButton) source;
      String whatbutton = choice.getText();
      System.out.println(whatbutton);
      if (whatbutton.equals("Yes")) {
        view.exit();
      }
      else if (whatbutton.equals("Cancel")) {
        view.cancelexit();
      }
    }
  }
}
