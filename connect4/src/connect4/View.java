package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class View extends JFrame {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Menucontroller menucontroller;
  private Exitcontroller exitcontroller;
  private Gamecontroller gamecontroller;
  
  private Image bluechip;
  private Image redchip;
  
  JPanel grid;
  HashMap<String, JButton> gridbuttons;
  
  private JPanel turnpanel;
  private JLabel turnlabel;
  
  private JMenu menu;
  private JMenuItem option1, option2, subopt1, subopt2;
  
  private JFrame frame;
  private JFrame exitconf;
  
  private JDialog popup;

  private JButton newgame;


  public static void main(String [] args) {
    new View();
  }
  
  public View() {
    menucontroller = new Menucontroller(this);
    exitcontroller = new Exitcontroller(this);

    frame = new JFrame();
    JPanel everythingpanel = new JPanel();
    everythingpanel.setLayout(new BorderLayout());
    
    // Menu
    JPanel menupanel = new JPanel();
    JMenuBar bar = new JMenuBar();
    menu = new JMenu("Menu");
    option1 = new JMenu("New game");
    subopt1 = new JMenuItem("Against another person");
    subopt1.addActionListener(menucontroller); // LISTENER
    subopt2 = new JMenuItem("Against computer");
    subopt2.addActionListener(menucontroller); // LISTENER
    popup = new JDialog(popup, "Error");
    
    //Setup popup frame
    JPanel explanation = new JPanel();
    explanation.setLayout(new BoxLayout(explanation, BoxLayout.Y_AXIS));
    explanation.setAlignmentX(CENTER_ALIGNMENT); 
    JLabel indevelopment = new JLabel("This feature is in development.");
    //indevelopment.setLayout(new BoxLayout(explanation, BoxLayout.Y_AXIS));
    indevelopment.setAlignmentX(CENTER_ALIGNMENT);
    JLabel notyetavail = new JLabel("Please be patient!");
    notyetavail.setAlignmentX(CENTER_ALIGNMENT);
    explanation.add(indevelopment);
    explanation.add(notyetavail);
    Image sorry;
    try {
      sorry = ImageIO.read(getClass().getResource("sorry.png"));
      sorry = sorry.getScaledInstance(85, 85, java.awt.Image.SCALE_SMOOTH ) ; 
      ImageIcon sorryicon = new ImageIcon(sorry);
      JLabel sorrylabel = new JLabel(sorryicon);
      sorrylabel.setAlignmentX(CENTER_ALIGNMENT);
      explanation.add(sorrylabel);
    } catch (Exception ex) {
      System.out.println(ex);
    }
    popup.add(explanation);

    option1.add(subopt1);
    option1.add(subopt2);
    option2 = new JMenuItem("Exit");
    option2.addActionListener(menucontroller); //LISTENER
    menu.add(option1);
    menu.add(option2);
    bar.add(menu);
    
    JMenu about = new JMenu("About");
    JMenuItem thegame, thedeveloper;
    thegame = new JMenuItem("The game");
    thegame.addActionListener(menucontroller);
    thedeveloper = new JMenuItem("The developer");
    thedeveloper.addActionListener(menucontroller); 
    about.add(thegame);
    about.add(thedeveloper);
    bar.add(about);
    
    menupanel.add(bar);    
    menupanel.setLayout(new FlowLayout(FlowLayout.LEFT));
  
    // Grid
    grid = new JPanel();
    grid.setLayout(new GridLayout(6,7));
    gridbuttons = (new HashMap<String, JButton>());
    for (int r = 0; r < 6; r++) {
      for (int c = 0; c < 7; c++) {
        JButton b = new JButton();
        //b.setIcon(defaultIcon);
        //b.setText(""+r+""+c);
        b.setName("b"+r+""+c);
        gridbuttons.put(b.getName(), b);
        b.setEnabled(false);
        b.setLayout(new BorderLayout());
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setPreferredSize(new Dimension(100,100));
        b.setOpaque(true);
        b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY)); 
        grid.add(b);
      }}
    
    // Set up chip images
    try {
      redchip = ImageIO.read(getClass().getResource("13122-large-red-circle.png"));
      redchip = redchip.getScaledInstance(85, 85, java.awt.Image.SCALE_SMOOTH ) ; 
      bluechip = ImageIO.read(getClass().getResource("957-large-blue-circle.png"));
      bluechip = bluechip.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH ) ; 
    } catch (Exception ex) {
      System.out.println(ex);
    }
    
    // Start prompt
    turnpanel = new JPanel();
    turnlabel = new JLabel("Please press menu to start a new game");
    turnpanel.add(turnlabel);
    
    //Win or tie situation
    newgame = new JButton("New game?");
    turnpanel.add(newgame);
    newgame.setVisible(false);
    
    everythingpanel.add(menupanel, BorderLayout.NORTH);
    everythingpanel.add(turnpanel, BorderLayout.CENTER);
    everythingpanel.add(grid, BorderLayout.SOUTH);
    
    frame.add(everythingpanel);
    frame.setSize(700, 700);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.repaint();    
    }

  void startGame() {
    gamecontroller = new Gamecontroller(this, new Board());
    Collection<JButton> buttons = gridbuttons.values();
    for (JButton button: buttons) {
      button.setEnabled(true);
      button.addActionListener(gamecontroller);
    }
    this.updateTurn("Red");
    frame.repaint();
  }
  
  void closeGame() {
    frame.setVisible(false); //you can't see me!
    frame.dispose();
    dispose();
    //this.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }
  
  void addChip(int col, String color) {
    JButton b;
    ImageIcon image;
    for (int i = 5; i > -1; i--) {
      String name = "b"+i+""+col;
      JButton test = gridbuttons.get(name);
      if (test.getIcon() == null) {
        b = gridbuttons.get(name);
        if (color.equals("Red")) {
          image = new ImageIcon(redchip);
        }
        else {
          image = new ImageIcon(bluechip);
        }
        b.setIcon(image);
        break;
      }
      }
    }
    
  void updateTurn(String color) {
    turnlabel.setText("Turn: "+color);
  }
  
  void win(String color) {
    turnlabel.setText(color+" wins!");
    newgame.setVisible(true);
    newgame.addActionListener(menucontroller);
    //Collection<JButton> buttons = gridbuttons.values();
    //for (JButton button: buttons) {
    //  button.addActionListener(null);
    //}
  }
  
  void tie() {
    turnlabel.setText("It's a tie!");
    newgame.setVisible(true);
    Collection<JButton> buttons = gridbuttons.values();
    for (JButton button: buttons) {
      button.addActionListener(null);
    }
  }
  
  void notsupported() {
    popup.setVisible(true);
    popup.setLocationRelativeTo(null);
    popup.setSize(210,150);
  }
  
  void exitConfirmation() {
    exitconf = new JFrame();
    JPanel everythingpanel = new JPanel();
    JPanel exitpanel = new JPanel();
    JLabel exit = new JLabel("Exit game?");
    JPanel buttonpanel = new JPanel();
    JButton ebutton = new JButton("Yes");
    JButton nvm = new JButton("Cancel");
    ebutton.addActionListener(exitcontroller);
    nvm.addActionListener(exitcontroller);
    buttonpanel.add(ebutton);
    buttonpanel.add(nvm);
    exitpanel.add(exit);
    everythingpanel.add(exitpanel);
    everythingpanel.add(buttonpanel);
    exitconf.add(everythingpanel);
    exitconf.setSize(200, 100);
    exitconf.setLocationRelativeTo(null);
    exitconf.setVisible(true);
    exitconf.repaint();

  }
  
   void exit() {
    exitconf.dispose();
    frame.dispose();
  }
  
   void cancelexit() {
    exitconf.dispose();
  }
}
