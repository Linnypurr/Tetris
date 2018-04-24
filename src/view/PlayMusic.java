package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PlayMusic {
    
    /** Input Stream for the .wav file.*/
    private InputStream myStream;
    
    /** Audio Stream. */
    private AudioStream myAudioStream;
    
    /** audioPlayer. */
    private AudioPlayer myAudioPlayer = AudioPlayer.player; 
    
    /** 
     * Constructor for play music class.
     */
    public PlayMusic() {
        try { 
            myStream = new FileInputStream(new File
            ("Soft_and_Furious_-_02_-_Game_On.wav"));
            myAudioStream = new AudioStream(myStream);
        } catch (final FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (final IOException e) {
            System.out.println("Input output exception error");
            e.printStackTrace();
        }  
    }
    
    /** Starts music. */ 
    public void startMusic() {
        myAudioPlayer.start(myAudioStream);
    }
    
    /** Pauses music. */
    public void pauseMusic() {
        if (myAudioStream != null) {
//            try {
//                //myAudioPlayer.wait();
//            } catch (final InterruptedException e) {
//                e.printStackTrace();
//            } 
        }
    }
}
