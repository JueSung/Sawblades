import javax.swing.JFrame;
import java.awt.*;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author gcschmit
 * @version 18 July 2014
 */
public class ScreenViewer
{
    // the cityscape will be animated for 60 seconds
    static final int ANIMATION_TIME_IN_SECONDS = 1500;
   
    /**
     * main method for the program which creates and configures the frame for the program
     * 
     * @param args  not used
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create and configure the frame (window) for the program
        JFrame frame = new JFrame();
        
        frame.setSize(500 /* x */, 425 /* y */); //changes window size
        frame.setTitle("Cityscape");
        frame.setBackground(Color.red);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        int h1 = 100 + (int)(Math.random()*45);
        int h2 = 100 + (int)(Math.random()*45);
        
        // a frame contains a single component; create the Cityscape component and add it to the frame
        ScreenComponent component = new ScreenComponent();
        
        
        LineUp row = new LineUp();
        //frame.add(component);
        frame.add(row);
        
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // animate the cityscape
        for( int seconds = 0; seconds < ANIMATION_TIME_IN_SECONDS; seconds++ )
        {
            row.nextFrame();
            //may need to adjust to make city look better
            Thread.sleep(17); //mill seconds 1000 = 1 second
        }
        
    }

}
