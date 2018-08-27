package connect4;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JMenuItem;

public class Menucontroller implements ActionListener {
  
  View view;
//  Gamecontroller game;
  
  // ------------
  // Following 2 methods open a webpage on Java
  // Taken from Vulcan's post on StackExchange
  // https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
  // 1)
  public static boolean openWebpage(URI uri) {

    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return false;
  }
  // 2)
  public static boolean openWebpage(URL url) {
    try {
        return openWebpage(url.toURI());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
    return false;
  }
  // --------------
  
  public Menucontroller(View v) {
    view = v;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source instanceof JMenuItem) {
        JMenuItem button = (JMenuItem) source;
        String whatbutton = button.getText();
        if (whatbutton.equals("Against another person")) {
          view.startGame();
        }
        else if (whatbutton.equals("Against computer")) {
          view.notsupported();
        }
        else if (whatbutton.equals("Exit")) {
          view.exitConfirmation();
        }
        else if (whatbutton.equals("The game")) {
          try {
            Menucontroller.openWebpage(new URL("https://en.wikipedia.org/wiki/Connect_Four"));
          } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            view.notsupported();
          }
        }
        else if (whatbutton.equals("The developer")) {
          try {
            Menucontroller.openWebpage(new URL("https://github.com/sallylungs"));
          } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            view.notsupported();
          }
        }
    }
    if (source instanceof JButton) {
      /* view.grid = new JPanel();
      view.grid.setLayout(new GridLayout(6,7));
      view.gridbuttons = (new HashMap<String, JButton>());
      for (int r = 0; r < 6; r++) {
        for (int c = 0; c < 7; c++) {
          JButton b = new JButton();
          //b.setIcon(defaultIcon);
          //b.setText(""+r+""+c);
          b.setName("b"+r+""+c);
          view.gridbuttons.put(b.getName(), b);
          b.setEnabled(false);
          b.setLayout(new BorderLayout());
          b.setBackground(Color.WHITE);
          b.setForeground(Color.BLACK);
          b.setPreferredSize(new Dimension(100,100));
          b.setOpaque(true);
          b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY)); 
          view.grid.add(b);
        }}
      */
      view.closeGame();
      view = new View();
      view.startGame();
    }
  }
}

