package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.Board;
import model.MovableTetrisPiece;

/**
 * UpNextPanel class creates the JPanel that shows
 * what piece will be played next. 
 * 
 * @author Lindsee Purnell
 * @version Tetris1.0
 */
public class UpNextPanel extends JPanel implements Observer {
    
    /** Default ID. */
    private static final long serialVersionUID = 7698631454821585053L;

    /**Background color. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
     
    /**Border color. */
    private static final Color WHITE = new Color(255, 255, 255); 
    
    /** The default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(300, 500);
    
    /** Font size. */
    private static final int FONT_SIZE = 12; 
    
    /** Default font used. */ 
    private final Font myFont = new Font("Monospace", Font.BOLD, FONT_SIZE);
    
    /** Empty border for padding. */
    private final Border myEmptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    /** Lined Border. */
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
        
    /** Variable for movable tetris piece. */ 
    private MovableTetrisPiece myTetrisPiece; 
    
    /** String of the tetris piece. */ 
    private String myTetrisString; 
     
    /** HashMap to help design Tetris pieces. */
    private Map<Character, Color> myTetrisMap; 
    
    /** Constructor for the panel. */ 
    public UpNextPanel() {
        super();
        setBackground(DARK_PURPLE);
        setPreferredSize(DEFAULT_SIZE);
        final TitledBorder titleBorder = BorderFactory.createTitledBorder(myLineBorder, 
                " Up Next: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER);
        titleBorder.setTitleFont(myFont);
        titleBorder.setTitleColor(WHITE);
        setBorder(BorderFactory.createCompoundBorder(myEmptyBorder, titleBorder));
        getTetrisPieces(); 
    }
    
    /**
     * Method that populates and returns hashmap of designed tetris
     * pieces. 
     */
    private void getTetrisPieces() {
        
        myTetrisMap = new HashMap<>(); 
        final Color brightOrange = new Color(219, 65, 0); 
        final Color lightBlue = new Color(96, 255, 233); 
        final Color magenta = new Color(246, 92, 235); 
        final Color lightGreen = new Color(65, 219, 0);
        final Color brightYellow = new Color(255, 255, 119);
        final Color brightRed = new Color(255, 0, 0);
        final Color darkBlue = new Color(31, 0, 246); 
        
        myTetrisMap.put('I', lightBlue);
        myTetrisMap.put('J', darkBlue);
        myTetrisMap.put('L', brightOrange);
        myTetrisMap.put('O', brightYellow);
        myTetrisMap.put('S', lightGreen);
        myTetrisMap.put('T', magenta); 
        myTetrisMap.put('Z', brightRed);
        

          
        
    }
    /** takes the tetris piece turns to a toString output. */ 
    @Override
    public void update(final Observable theObs, final Object theArg) {
        if (theObs instanceof Board) {
            if (theArg instanceof MovableTetrisPiece) {
                myTetrisPiece = (MovableTetrisPiece) theArg; 
                myTetrisString = myTetrisPiece.toString();
                repaint(); 
            }
        }
        
    }
    
    /** Shows the next piece. */ 
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;  
        final int thick = 3;
        g2d.setStroke(new BasicStroke(thick));

        final int blockSize = getWidth() / 15; 
        int x = getX();
        int y = getY() + 1;
        
        final char[] charboard = myTetrisString.toCharArray(); 
        final char[] rightboard = Arrays.copyOfRange(charboard, 4, charboard.length);
        
        for (char c : rightboard) {
           
            if (c == ' ') {
                final Rectangle rect1 = new Rectangle(x * blockSize, y * blockSize, 
                                            blockSize, blockSize);
                g2d.setColor(WHITE); 
                g2d.draw(rect1);
            } else if (myTetrisMap.containsKey(c)) {
                final Rectangle rect = new Rectangle(x * blockSize, y * blockSize, 
                                                     blockSize, blockSize);
                g2d.setColor(WHITE); 
                g2d.draw(rect);
                g2d.setColor(myTetrisMap.get(c));
                g2d.fill(rect);
            }
            
            if (c == '\n') {
                x = getX();
                y++;
            }
            x++; 
        }
        
    }
}
