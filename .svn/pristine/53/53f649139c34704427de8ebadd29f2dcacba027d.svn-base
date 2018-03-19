package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.Board;

/**
 * ScorePanel class is a panel on the Main Gui
 * that listens to the amount of lines cleared and 
 * calculates the current score. 
 * 
 * @author Lindsee Purnell
 * @version Tetris1.0
 */
public class ScorePanel extends JPanel implements Observer {
    
    /** Generated ID. */
    private static final long serialVersionUID = 6535364451262812255L;

    /** Magic number 4. */
    private static final int FOUR = 4; 
    
    /** Magic number 2. */
    private static final int TWO = 2; 
    
    /** Magic number 100. */
    private static final int ONE_HUNDRED = 100;
    
    /** Magic number 200. */
    private static final int TWO_HUNDRED = 200;
    
    /** The default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(300, 400);
    
    /** Background Color of GUI and Panels. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
    
    /** Border Color. */
    private static final Color WHITE = new Color(255, 255, 255); 
    
    /** Font size. */
    private static final int FONT_SIZE = 12; 
    
    /** Default font used. */ 
    private final Font myFont = new Font("Monospace", Font.BOLD, FONT_SIZE);
    
    /** Empty border for padding. */
    private final Border myEmptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
     
    /** Line Border. */ 
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
    
    /** Title Border. */ 
    private final TitledBorder myTitleBorder = BorderFactory.createTitledBorder(myLineBorder, 
          " Score: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER); 
    /** Integer variable to hold score. */
    private int myScore; 
    
    /**
     * Constructor of score panel holds and explains score. 
     */
    public ScorePanel() {
        super(); 
        setPreferredSize(DEFAULT_SIZE); 
        setBackground(DARK_PURPLE); 
        setVisible(true);  
        myTitleBorder.setTitleColor(WHITE);
        myTitleBorder.setTitleFont(myFont);
        setBorder(BorderFactory.createCompoundBorder(myEmptyBorder, myTitleBorder));
        myScore = 0; 
          
    }
    
    /** Paints the current score and score algorithm. */ 
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics; 
        //final String stringTest = "One line = 100 points\nTetris = 800 points"; 
        g2d.setFont(myFont);
        g2d.setColor(WHITE);
        g2d.drawString("Current Score: " +  myScore, getWidth() / FOUR, getHeight() / TWO);
        
    }
    
    /** Holds the Integer array and passes it to calculate. */ 
    @Override
    public void update(final Observable theObs, final Object theArg) {
        final ArrayList<Integer> integers = new ArrayList<>();
        if (theObs instanceof Board) {
            if (theArg instanceof Integer[]) {
                final Integer[] arrayInts = (Integer[]) theArg; 
                for (int i = 0; i < arrayInts.length; i++) {
                    integers.add(i); 
                }
                calculateScore(integers);
                repaint(); 
            }
        }
    }
    
    /**
     * Private method to calculate the score based on lines cleared.
     * 
     * @param theIntegers arraylist of lines cleared. 
     */
    private void calculateScore(final ArrayList<Integer> theIntegers) { 
        if (theIntegers.size() > 0) {
            myScore = (theIntegers.size() * ONE_HUNDRED) + myScore; 
            if (theIntegers.size() == FOUR) { 
                myScore = (theIntegers.size() * TWO_HUNDRED) + myScore; 
            }
        }
    }

}
