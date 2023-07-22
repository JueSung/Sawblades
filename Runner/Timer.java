
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
 
    public void increment(){
        displayedTime++;
    }
    
    public void decrement(){
        if(displayedTime!=0)
            displayedTime--;
    }
    
    public void draw(Graphics2D page){
        Color color = Color.black;
        page.setColor(color);

        String timeString = Integer.toString(displayedTime);
        //page.drawPolygon(xs,ys,4);
        
        //draing boxes
        //x -> 6 10s use 4
        //y -> 12 8s use 10
        page.setFont(new Font("Arial", Font.PLAIN, 40));
        page.drawString(timeString, 36, 64);

        //------------------
        /*
        int[] rectangleX1 = {146,206,206,146};
        int[] rectangleY1 = {160,160,256,256};
        int[] rectangleX2 = {226,286,286,226};
        int[] rectangleY2 = {160,160,256,256};
        page.drawPolygon(rectangleX1,rectangleY1,4);
        page.drawPolygon(rectangleX2,rectangleY2,4);
        */
        
    }
    
    public int getDisplayedTime(){
        return displayedTime;
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
            }
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
