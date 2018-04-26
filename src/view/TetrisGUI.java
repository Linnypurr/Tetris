package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.Board;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

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
    private static final int SCORE_PANEL_SIZE = 300; 
    
    /** Magic number 300. */
    private static final int INSTRUCTION_X_SIZE = 300; 
    
    /** Magic number 600. */
    private static final int INSTRUCTION_Y_SIZE = 600;
    
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
    private static final Color DARK_PURPLE_BACKGROUND = new Color(28, 0, 38); 
    
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
    
    /** Instance of music panel. */ 
    private MusicPanel myMusicPanel; 
    
    /** For getting user input. */
    private String myUsersName = ""; 
    
    /** HashMap for organizing high scores. */ 
    private TreeMap<Integer, String> myUsersMap; 
    
    /** Stack for organization. */ 
    private Stack<Integer> myStack = new Stack<Integer>(); 
     
    
    /** 
     * Method to start the creation of the GUI. 
     */
    public void start() {
        myPickedSize = panelDialog(); 
        myBoard = new Board();
        myBoardPanel = new BoardPanel(myBoard, myPickedSize); 
        myScorePanel = new ScorePanel(); 
        myUpNextPanel = new UpNextPanel(); 
        myMusicPanel = new MusicPanel(); 
        myUsersMap = new TreeMap<>(); 
        myBoard.addObserver(this);
        myBoard.addObserver(myBoardPanel);
        myBoard.addObserver(myScorePanel); 
        myBoard.addObserver(myUpNextPanel);
        myBoardPanel.setFocusable(true);
        myMainFrame.setPreferredSize(new Dimension(myWidth, myHeight));
        myMainFrame.setResizable(false);
        myMainFrame.getContentPane().setBackground(DARK_PURPLE_BACKGROUND);
        myMainFrame.setLocationRelativeTo(null);
        myMainFrame.setVisible(true);
        
        myMainFrame.setLayout(new BorderLayout());
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainFrame.add(myBoardPanel, BorderLayout.CENTER);
        myMainFrame.add(setEastPanel(), BorderLayout.EAST); 
        myBoard.newGame();
        myBoardPanel.requestFocusInWindow();
        myMainFrame.pack();
        
    }
    
    //final JPanel theSPanel, 
//    final JPanel theUPanel, final JPanel theMPanel
    
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
     * @return JPanel for mainframe. 
     */
    private JPanel setEastPanel() {
        final JPanel eastPanel = new JPanel(); 
        final BoxLayout boxLayout = new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS); 
        eastPanel.setLayout(boxLayout);
        eastPanel.setBackground(DARK_PURPLE_BACKGROUND);
        
        eastPanel.add(myUpNextPanel);       
        eastPanel.add(myScorePanel); 
        eastPanel.add(myMusicPanel);
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
        instructPanel.setPreferredSize(new Dimension(INSTRUCTION_X_SIZE, INSTRUCTION_Y_SIZE));
        final TitledBorder titleBorder = BorderFactory.createTitledBorder(myLineBorder, 
             " How to play: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER); 
        titleBorder.setTitleColor(WHITE);
        titleBorder.setTitleFont(myFont);
        instructPanel.setBorder(
                  BorderFactory.createCompoundBorder(myEmptyBorder, titleBorder));
        final BoxLayout boxLayout = new BoxLayout(instructPanel, BoxLayout.PAGE_AXIS); 
        instructPanel.setLayout(boxLayout);
        instructPanel.setBackground(DARK_PURPLE_BACKGROUND); 
        
        //JTextArea
        final JTextArea text = new JTextArea(); 
        text.setEditable(false);
        text.setText("Move right: " + RIGHT + " or D\nRotate: " + UP + " or W"
            + "\nMove left: " + LEFT + " or A\n" + "Speed down: " + DOWN +  " or S"
            + "\nDrop: Space \nPause: P \n\nOne line = 100 points\nTetris = 800 points");
        text.setForeground(WHITE);
        text.setBackground(DARK_PURPLE_BACKGROUND);
        text.setFont(myFont); 
        instructPanel.add(text); 

        return instructPanel;  
    }
   
    /** Sees if the game is over. */ 
    @Override
    public void update(final Observable theObs, final Object theArg) {
        if (theObs instanceof Board) {
            
            if (theArg instanceof Boolean) {
                final Object[] options = {"Yes", "No"};
                final int choice = JOptionPane.showOptionDialog(null, 
                            "Game Over! Play again?", "Game Over", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION,
                            null, options, options[0]); 
                if (choice == 0) {
                    myMusicPanel.myMusic.pauseMusic();
                    myMainFrame.dispose();  
                    new TetrisGUI().start();     
                } else {
                    myMusicPanel.myMusic.pauseMusic();
                    myUsersName = JOptionPane.showInputDialog("Please enter your name: ");
                    try {
                        final FileWriter writeScores = new FileWriter("Highscore.txt", true);
                        writeScores.write(myUsersName + " " + myScorePanel.getScore() + "\n");
                        writeScores.close(); 
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                    listHighScores();  
                }
                
              
            }
        }
        
    }
    
    /**
     * Private Method to show off high scores. 
     *
     */
    private void printHighScores() {
        final JDialog dialogBox = new JDialog();
        dialogBox.setLayout(new BoxLayout(dialogBox.getContentPane(), BoxLayout.PAGE_AXIS));
        dialogBox.setBackground(DARK_PURPLE_BACKGROUND);
        dialogBox.setVisible(true);
        dialogBox.setTitle("High Scores: ");
        dialogBox.setFont(myFont);
        dialogBox.setSize(SCORE_PANEL_SIZE, SCORE_PANEL_SIZE);
        
        final JLabel highScoreLabel = new JLabel("High Scores: \n");
        dialogBox.add(highScoreLabel);
        int count = 1;
        
        for (Integer i : myStack) {
            final JLabel userLabel = new JLabel(); 
            userLabel.setHorizontalTextPosition(JLabel.CENTER);
            userLabel.setText(count + " " 
                + myUsersMap.get(i) + " " + i);
            count++;
            userLabel.setFont(myFont);
            userLabel.setBackground(DARK_PURPLE_BACKGROUND);
            dialogBox.add(userLabel); 
        }

        
    }
    
    /**
     * Organize and list high scores correctly. 
     */
    private void listHighScores() {
        final Stack<Integer> firstStack = new Stack<Integer>();
        try {
            final FileReader readScores = new FileReader("Highscore.txt");
            final BufferedReader bufferedReader = new BufferedReader(readScores); 
            String user;
            String[] stringArray = null; 
            while ((user = bufferedReader.readLine()) != null) {
                stringArray = user.split(" ", -2);
                final Integer intScore = Integer.parseInt(stringArray[1]);
                myUsersMap.put(intScore, stringArray[0]);
            }
           
            readScores.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
        
        for (Integer i : myUsersMap.keySet()) {
            if (i > 0) {
                firstStack.push(i); 
            }
        }
        
        while (!firstStack.isEmpty()) {
            myStack.push(firstStack.pop()); 
        }
        printHighScores(); 
        
    }
}
