/**
 * The screen
 */

import java.awt.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Runner extends JComponent //implements Runnable
{
    private final int APPLET_WIDTH = 400;    //Size constants
    private final int APPLET_HEIGHT = 150;
    private final int HEIGHT_MIN = 100;
    private final int VARIANCE = 45;
    private int time = 0;
    Random generator = new Random();

    //private Car c1;
    private WorkingScreen screen;
    private Avatar you;

    public Runner () //init in applet is like constructor; runs only once
    {
        //c1 = new Car(300,true,Color.blue,100);
        screen = new WorkingScreen(Color.red,new Color(255,225,225));
        you = new Avatar();

        //Thread tc1 = new Thread(c1);
        //tc1.start();
        Thread tWC = new Thread(screen);
        tWC.start();
        Thread tY = new Thread(you);
        tY.start();
    }

    //-----------------------------------------------------------------
    //  Paints the stick figures on the applet.
    //-----------------------------------------------------------------
    public void paintComponent(Graphics g)
    {
        IsKeyPressed.main();
        time +=1;
        System.out.println(time);
        //1000 is full day + night
        if(time >= 1000){
            time = 0;
        }
        Graphics2D page = (Graphics2D) g;
        //page = this.page;
        
        //c1.setTime(time);
        
        
        //c1.draw(page);
        screen.draw(page);
        you.draw(page);

        //repaint(); //IMPORTANT LINE
    }

    public void nextFrame()
    {
        repaint();
    }

    public void run()
    {
    }
}
