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
 * 
 * @author linny
 *
 */
public class MusicPanel extends JPanel {
    /**Background color. */
    private static final Color DARK_PURPLE = new Color(28, 0, 38); 
    
    /** Font size. */
    private static final int FONT_SIZE = 12;
   
    /** Magic number 200. */
    private static final int MUSIC_JPANEL_DIMENSION = 200; 
    
     /**Border color. */
    private static final Color WHITE = new Color(255, 255, 255);
    
    /** Line Border. */ 
    private final Border myLineBorder = BorderFactory.createLineBorder(WHITE, 3);
    
    /** Empty border for padding. */
    private final Border myEmptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    /** Default font used. */ 
    private final Font myFont = new Font("Monospace", Font.BOLD, FONT_SIZE);
    
    final PlayMusic music = new PlayMusic();
    
    public MusicPanel() {
        super(); 
        setBackground(DARK_PURPLE);
        setPreferredSize(new Dimension(
            MUSIC_JPANEL_DIMENSION, MUSIC_JPANEL_DIMENSION)); 
        final TitledBorder titleBorder = BorderFactory.createTitledBorder(myLineBorder, 
            " Music: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER); 
        titleBorder.setTitleColor(WHITE);
        titleBorder.setTitleFont(myFont); 
        setBorder(
            BorderFactory.createCompoundBorder(myEmptyBorder, titleBorder));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(playButton());
        add(pauseButton()); 
    }
    
    private JButton playButton() {
        final JButton playButton = new JButton("Play music");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) { 
                music.startMusic();
            }
        });
        return playButton;
    }
    
    private JButton pauseButton() {
        final JButton pauseButton = new JButton("Pause music");
        pauseButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent theEvent) {
                music.pauseMusic();
            }
        });
        return pauseButton; 
    }
}
