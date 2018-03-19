package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.Board;

//import view.BoardPanel.MoveKeyListener;

/**
 * Main GUI for tetris sets up frame and other Panels.
 * 
 * @author Lindsee Purnell
 * @version tetris1.0
 */
public class TetrisGUI implements Observer {
    
    /** Magic number 50. */
    private static final int FIFTY = 50; 
    
    /** Small board. */
    private static final int FIFTEEN = 15; 
    
    /** Medium board. */
    private static final int TWENTY = 20; 
    
    /** Large board. */
    private static final int TWENTY_TWO = 22; 
    
    /** Magic number 300. */
    private static final int THREE_HUNDRED = 300; 
    
    /** Magic number 600. */
    private static final int SIX_HUNDRED = 600;
    
    /** Font size. */
    private static final int FONT_SIZE = 12; 
    
    /** Up arrow unicode. */
    private static final char UP = '\u2B61';
  
    /** Down arrow unicode. */
    private static final char DOWN = '\u2B63';
    
    /** Right arrow unicode. */
    private static final char RIGHT = '\u2B62';
    
    /** Left arrow unicode. */
    private static final char LEFT = '\u2B60';
    
    /**Background color. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
    
    /**Border color. */
    private static final Color WHITE = new Color(255, 255, 255); 
    
    /** Empty border for padding. */
    private final Border myEmptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    /** Line Border. */ 
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
    
    /** Default font used. */ 
    private final Font myFont = new Font("Monospace", Font.BOLD, FONT_SIZE);
    
    /**JFrame width. */
    private final int myWidth = 600; 
    
    /** JFrame height. */
    private final int myHeight = 600; 
    
    /** JFrame with title. */
    private final JFrame myMainFrame = new JFrame("Tetris");  
    
    /** Boolean of game. */
    private Boolean myGameOver; 
    
    /** integer of users preferred size. */
    private  int myPickedSize; 
    
    /** Instance of the board. */
    private Board myBoard; 
    
    /**Instance of the boardpanel. */
    private BoardPanel myBoardPanel;
    
    /** Instance of the scorePanel. */
    private ScorePanel myScorePanel;
    
    /** Instance of the preview panel. */
    private UpNextPanel myUpNextPanel; 
    
    /** 
     * Method to start the creation of the GUI. 
     */
    public void start() {
        myPickedSize = panelDialog();
        myGameOver = false; 
        myBoard = new Board();
        myBoardPanel = new BoardPanel(myBoard, myPickedSize); 
        myScorePanel = new ScorePanel(); 
        myUpNextPanel = new UpNextPanel(); 
        myBoard.addObserver(this);
        myBoard.addObserver(myBoardPanel);
        myBoard.addObserver(myScorePanel); 
        myBoard.addObserver(myUpNextPanel);
        myBoardPanel.setFocusable(true);
        myMainFrame.setPreferredSize(new Dimension(myWidth, myHeight));
        myMainFrame.setResizable(false);
        myMainFrame.getContentPane().setBackground(DARK_PURPLE);
        myMainFrame.setLocationRelativeTo(null);
        myMainFrame.setVisible(true);
        
        myMainFrame.setLayout(new BorderLayout());
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainFrame.add(myBoardPanel, BorderLayout.CENTER);
        myMainFrame.add(setEastPanel(myScorePanel, myUpNextPanel), BorderLayout.EAST); 
        myBoard.newGame();
        myBoardPanel.requestFocusInWindow();
        myMainFrame.pack();
        
    }
    
    
    /**
     * Method to have user decide on board size. 
     * 
     * @return integer for width and height of board. 
     */
    private int panelDialog() {
        final Object[] options = {"Small", "Medium", "Large"}; 
        final int size; 
        final int choice = JOptionPane.showOptionDialog(null, 
                           "Please choose a board size: ", "Tetris board option", 
                           JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION,
                            null, options, options[1]);
        if (choice == 0) {
            size = FIFTEEN; 
        } else if (choice == 1) {
            size = TWENTY; 
        } else {
            size = TWENTY_TWO; 
        }
        
        return size; 
    } 
    
    /**
     * Method to create Panel in the East of the GUI. 
     * 
     * @param theSPanel for scoring. 
     * @param theUPanel for piece preview. 
     * @return JPanel for mainframe. 
     */
    private JPanel setEastPanel(final JPanel theSPanel, final JPanel theUPanel) {
        final JPanel eastPanel = new JPanel(); 
        final BoxLayout boxLayout = new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS); 
        eastPanel.setLayout(boxLayout);
        eastPanel.setBackground(DARK_PURPLE);
        
        eastPanel.add(theUPanel); 
        
        eastPanel.add(Box.createVerticalStrut(FIFTY)); 
        
        eastPanel.add(theSPanel); 
        
        eastPanel.add(Box.createVerticalStrut(FIFTY));

        eastPanel.add(setInstructions());
        
        return eastPanel; 
        
    }
    
    /**
     * JPanel to create Instruction Panel on
     * how to play the game. 
     * 
     * @return instructions Panel
     */
    private JPanel setInstructions() {
        final JPanel instructPanel = new JPanel(); 
        instructPanel.setPreferredSize(new Dimension(THREE_HUNDRED, SIX_HUNDRED));
        final TitledBorder titleBorder = BorderFactory.createTitledBorder(myLineBorder, 
             " How to play: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER); 
        titleBorder.setTitleColor(WHITE);
        titleBorder.setTitleFont(myFont);
        instructPanel.setBorder(
                  BorderFactory.createCompoundBorder(myEmptyBorder, titleBorder));
        final BoxLayout boxLayout = new BoxLayout(instructPanel, BoxLayout.PAGE_AXIS); 
        instructPanel.setLayout(boxLayout);
        instructPanel.setBackground(DARK_PURPLE); 
        
        //JTextArea
        final JTextArea text = new JTextArea(); 
        text.setEditable(false);
        text.setText("Move right: " + RIGHT + " or D\nRotate: " + UP + " or W"
            + "\nMove left: " + LEFT + " or A\n" + "Speed down: " + DOWN +  " or S"
            + "\nDrop: Space \nPause: P \n\nOne line = 100 points\nTetris = 800 points");
        text.setForeground(WHITE);
        text.setBackground(DARK_PURPLE);
        text.setFont(myFont); 
        instructPanel.add(text); 

        return instructPanel; 
    }
   
    /** Sees if the game is over. */ 
    @Override
    public void update(final Observable theObs, final Object theArg) {
        if (theObs instanceof Board) {
            
            if (theArg instanceof Boolean) {
                myGameOver = (Boolean) theArg;
                final Object[] options = {"Yes", "No"};
                final int choice = JOptionPane.showOptionDialog(null, 
                            "Game Over! Play again?", "Game Over", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION,
                            null, options, options[0]); 
                if (choice == 0) {
                    myMainFrame.dispose();  
                    new TetrisGUI().start();     
                }
                
              
            }
        }
        
    }
        

}
