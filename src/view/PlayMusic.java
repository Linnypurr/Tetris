package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Class to handle music in game. 
 * 
 * @author linny
 * @version 2.0 
 */
public class PlayMusic {
    
    /** Input Stream for the .wav file.*/
    private File myFile;
    
    /** Audio Stream. */
    private AudioInputStream myAudioStream;
    
    /** Clip for audio. */
    private Clip myClip;
     
    
    /** 
     * Constructor for play music class.
     */
    public PlayMusic() {
        try { 
            myFile = new File("Soft_and_Furious_-_02_-_Game_On.wav");
            myAudioStream = AudioSystem.getAudioInputStream(myFile);
            myClip = AudioSystem.getClip();
            myClip.open(myAudioStream);
        } catch (final FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (final IOException e) {
            System.out.println("Input output exception error");
            e.printStackTrace();
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        } catch (final UnsupportedAudioFileException e) {
            e.printStackTrace();
        }  
    }
    
    /** Starts music. */ 
    public void startMusic() {
        myClip.setFramePosition(0);
        myClip.start();
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /** Pauses music. */
    public void pauseMusic() {
        myClip.stop();
    }
}
