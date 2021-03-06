package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import model.Board;

/**
 * BoardPanel produces the output for the tetris game 
 * shows the playable pieces and the grid of the panel. 
 * 
 * @author Lindsee Purnell
 * @version Tetris1.0
 */
public class BoardPanel extends JPanel implements Observer {
   
    /** serial ID. */
    private static final long serialVersionUID = -7701057436252781405L; 

    /** Magic number 1000. */
    private static final int ONE_THOUSAND = 1000;
    
    /** Background Color of GUI and Panels. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
    
    /** Border Color. */ 
    private static final Color WHITE = new Color(255, 255, 255); 
    
    /** Instance of Board class. */ 
    private final Board myBoard; 
    
    /** Lined border. */
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
    
    /** Timer for movement of pieces and speed of game. */
    private Timer myTimer; 
    
    /** Width of panel. */
    private final int myWidth;
    
    /** Height of panel. */
    private final int myHeight; 
    
    /**Size of tetris individual blocks. */
    private int myBlockSize; 
    
    /** HashMap to help design Tetris pieces. */
    private Map<Character, Color> myTetrisPieces; 
    
    /** KeyAdapter of movement. */
    private KeyAdapter myMoveKeys = new MoveKeyListener(); 
    
    /** KeyAdapter of pause. */
    private KeyAdapter myPauseKey = new PauseKeyListener(); 
    
    
    /** String for boards toString. */
    private String myCurrentBoardString; 
    
    /** 
     * Constructor for BoardPanel calls
     * parent JPanel. 
     * 
     * @param theBoard instance of Board
     * @param theSize size of Board
     */
    public BoardPanel(final Board theBoard, final int theSize) {
        super(); 
        myBoard = theBoard;
        
        myWidth = myBoard.getWidth() * theSize; 
        
        myHeight = myBoard.getHeight() * theSize; 
        
        setPreferredSize(new Dimension(myWidth, myHeight)); 
        setBackground(DARK_PURPLE); 
        setBorder(myLineBorder); 
        setFocusable(true); 
      
        myBlockSize = myWidth / myBoard.getWidth();
        
        initialize(); 
        
    }
    
    /** Helper method to initialize other methods. */
    private void initialize() {
        myTimer = new Timer(ONE_THOUSAND, new TimeListener()); 
        myTimer.start();
        myBoard.newGame();
        getTetrisPieces(); 
        addKeyListener(myMoveKeys); 
    }
    
    /** Private method for if the game is paused. */
    private void pause() {
        myTimer.stop();
        removeKeyListener(myMoveKeys); 
        addKeyListener(myPauseKey); 
    }
    
    /** Private method to unpause the game. */ 
    private void unpause() {
        removeKeyListener(myPauseKey);
        addKeyListener(myMoveKeys); 
        myTimer.restart();
    }
    
    /**
     * Method that populates and returns hashmap of designed tetris
     * pieces. 
     * @return myTetrisPieces hashmap. 
     */
    private Map<Character, Color> getTetrisPieces() {
        
        myTetrisPieces = new HashMap<>(); 
        final Color brightOrange = new Color(255, 195, 0); 
        final Color lightBlue = new Color(96, 255, 233); 
        final Color magenta = new Color(246, 92, 235); 
        final Color lightGreen = new Color(65, 219, 0);
        final Color brightYellow = new Color(255, 255, 119);
        final Color brightRed = new Color(255, 0, 0);
        final Color darkBlue = new Color(31, 0, 246); 
        
        myTetrisPieces.put('I', lightBlue);
        myTetrisPieces.put('J', darkBlue);
        myTetrisPieces.put('L', brightOrange);
        myTetrisPieces.put('O', brightYellow);
        myTetrisPieces.put('S', lightGreen);
        myTetrisPieces.put('T', magenta);
        myTetrisPieces.put('Z', brightRed);
        
        return myTetrisPieces; 
    }
    
    /**
     * If toString is received sets the too string,
     * if boolean is received passes to gameOver. 
     */
    @Override
    public void update(final Observable theObs, final Object theArg) { 
        if (theObs instanceof Board) {
            if (theArg instanceof String) {
                myCurrentBoardString = theArg.toString(); 

                repaint();
            } 
        }
        
    }
    
    /** Designs border and tetris pieces. */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics; 
        final int thick = 3;
        g2d.setStroke(new BasicStroke(thick));
        
        int x = getX();
        int y = getY(); 
        
        final char[] charboard = myCurrentBoardString.toCharArray(); 
        final char[] rightboard = Arrays.copyOfRange(charboard, 60, charboard.length);
        
        for (final char c : rightboard) {
            
            if (c == ' ') {
                final Rectangle mainRect = new Rectangle(x * myBlockSize, y * myBlockSize, 
                                                         myBlockSize, myBlockSize);
                g2d.setColor(Color.WHITE);
                g2d.draw(mainRect);
                
            }
          
            if (myTetrisPieces.containsKey(c)) {
                final Rectangle rect = new Rectangle(x * myBlockSize, y * myBlockSize, 
                                                  myBlockSize, myBlockSize);
                g2d.setColor(WHITE);
                g2d.draw(rect);
                g2d.setColor(myTetrisPieces.get(c));
                g2d.fill(rect);
            } 
                
            if (c == '\n') {
                x = getX();
                y++;
                
            }
            x++; 
        }  
    }
    
   /**
    * Private inner class to move the pieces from 
    * key presses. 
    * 
    * @author Lindsee Purnell
    * @version Tetris1.0
    */
    private class MoveKeyListener extends KeyAdapter {
        /**
         * Accepts up, down, left, right, space and P keys.
         */
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_UP
                            || theEvent.getKeyCode() == KeyEvent.VK_W) {
                myBoard.rotate();
                
            } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT
                            || theEvent.getKeyCode() == KeyEvent.VK_D) {
                myBoard.right();
                
            } else if (theEvent.getKeyCode() == KeyEvent.VK_LEFT
                            || theEvent.getKeyCode() == KeyEvent.VK_A) {
                
                myBoard.left();
                
            } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN
                            || theEvent.getKeyCode() == KeyEvent.VK_S) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                pause(); 
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            }
        }
    } // End MoveKeyListener
    
    /**
     * Private key listener just for pauses. 
     * 
     * @author Lindsee Purnell
     * @version Tetris1.0 
     */
    private class PauseKeyListener extends KeyAdapter {
        /**
         * Just need P key for pause. 
         */
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                unpause(); 
            }  
        }
    } // End PauseKeyListener
    
    /**
     * Inner class for timer action.
     * 
     * @author Lindsee Purnell
     * @version Tetris1.0 
     */
    private class TimeListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.down(); 
        }
        
    } // End TimeListener
}
