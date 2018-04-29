package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Class for creating a music panel 
 * in the game. 
 * 
 * @author linny
 * @version 2.0 bby
 */
public class MusicPanel extends JPanel {
    
    /** serial ID. */
    private static final long serialVersionUID = -7769629329232391671L;

    /**Background color. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
    
    /** Font size. */
    private static final int FONT_SIZE = 12;
   
    /** Magic number 200. */
    private static final int MUSIC_JPANEL_DIMENSION = 200; 
    
     /**Border color. */
    private static final Color WHITE = new Color(255, 255, 255);
    
    /**Instance of PlayMusic. */
    private PlayMusic myMusic = new PlayMusic();
    
    /** Line Border. */ 
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
    
    /** Empty border for padding. */
    private final Border myEmptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    /** Title Border. */ 
    private final TitledBorder myTitleBorder = BorderFactory.createTitledBorder(myLineBorder, 
          " Music: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER); 
    
    /** Default font used. */ 
    private final Font myFont = new Font("Monospace", Font.BOLD, FONT_SIZE);
     
    /** Enable or disable buttons. */ 
    private boolean myToggleButton; 
    
    /**
     * Constructor for music JPanel. 
     */
    public MusicPanel() {
        super(); 
        myToggleButton = true;
        setBackground(DARK_PURPLE);
        setPreferredSize(new Dimension(
            MUSIC_JPANEL_DIMENSION, MUSIC_JPANEL_DIMENSION)); 
        myTitleBorder.setTitleColor(WHITE);
        myTitleBorder.setTitleFont(myFont); 
        setBorder(
            BorderFactory.createCompoundBorder(myEmptyBorder, myTitleBorder));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(playButton());
        add(pauseButton()); 
    }
    
    /**
     * So I can stop the music outside of the class. 
     * 
     * @return myMusic. 
     */
    public PlayMusic getMyMusic() {
        return myMusic; 
    }
    
    /**
     * Private method to play music.
     * 
     * @return Button for users to play. 
     */
    private JButton playButton() {
        final JButton playButton = new JButton("Play music");
        playButton.setFocusable(false); 
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) { 
                myMusic.startMusic(); 
            }
        });
        playButton.setEnabled(myToggleButton);
        return playButton;
    }
    
    /** 
     * Private method to pause music.  
     * 
     * @return Button for users to pause. 
     */
    private JButton pauseButton() {
        final JButton pauseButton = new JButton("Pause music");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent theEvent) {
                myMusic.pauseMusic(); 
            }
        });
        pauseButton.setEnabled(myToggleButton);
        return pauseButton; 
    }
}
