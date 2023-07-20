
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
    final private int YPIVOT = 160;
    final private int XLEFT = 120;
    final private int XRIGHT = 220;
    private ArrayList<int[]> numsX = new ArrayList<int[]>();
    private ArrayList<int[]> numsY = new ArrayList<int[]>();
    public Timer(){
        displayedTime =60;
        //initialize arraylists
    }
    
    /**
     * only x is different, center y will be the same either way
     * x is either 146 or 226
     * YPIVOT is always 160
     */
    public void resetTo(int x){
        //zero
        //one
        numsX.get(0)[0]=x+40;numsX.get(0)[1]=x+50;numsX.get(0)[2]=x+50;numsX.get(0)[3]=x+40;
        numsY.get(0)[0]= YPIVOT+8;numsY.get(0)[1]=YPIVOT+8;
        numsY.get(0)[2]=YPIVOT+88;numsY.get(0)[3]=YPIVOT+88;
        
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
        int tensDigit = displayedTime/10;//gets tens digit bc trunkate
        int onesDigit = displayedTime%10;
        //page.drawPolygon(xs,ys,4);
        
        //draing boxes
        //x -> 6 10s use 4
        //y -> 12 8s use 10
        int[] testOneX = {146/*pivot*/+40,146+50,146+50,146+40};
        int[] testOneY = {160+8,160+8,160+8*11,160+8*11};
        page.fillPolygon(testOneX,testOneY,4);
        int[] rectangleX1 = {146,206,206,146};
        int[] rectangleY1 = {160,160,256,256};
        int[] rectangleX2 = {226,286,286,226};
        int[] rectangleY2 = {160,160,256,256};
        page.drawPolygon(rectangleX1,rectangleY1,4);
        page.drawPolygon(rectangleX2,rectangleY2,4);
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
