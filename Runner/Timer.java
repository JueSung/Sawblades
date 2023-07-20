
/**
 * Displays the time 60 second count down
 */
import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Timer extends JComponent implements Runnable
{
    private int time;
    private int displayedTime;
    public Timer(){
        displayedTime =60;
    }
    //a method for every number
    
    public void increment(){
        displayedTime++;
    }
    
    public void decrement(){
        displayedTime--;
    }
    
    public void draw(Graphics2D page){
        Color color = Color.black;
        page.setColor(color);
        //page.drawPolygon(xs,ys,4);
    }
    
    public void setTime(int time){
        this.time = time;
    }
    
    @Override
    public void paintComponent(Graphics g) //required
    {
        Graphics2D g2 = (Graphics2D) g;
        // invoke the draw method 
        // ...
        draw(g2);
    }
    
    public void run(){
        int pastTime=0;
        while(true){
            if(pastTime+48<=time){
                decrement();
                pastTime=time;
                System.out.println(displayedTime);
            }
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
