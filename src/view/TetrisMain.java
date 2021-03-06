package view;

import java.awt.EventQueue;




/**
 * Runs the Tetris GUI. 
 * 
 * @author Lindsee Purnell
 * @version tetris1.0
 */
public final class TetrisMain {
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * 
     * 
     * @param theArgs Command line arguments.
     */ 
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new TetrisGUI().start();
            }
        }); 
    }
}
 