/**
 * The little person you control
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Sawblade extends JComponent implements Runnable, BoxObject
{
    private int time=0;
    private boolean marked = false;
    private double angle=3*Math.PI/2.0; //radians
    //assuming hitbox size of 30 by 30
    private final int XLOW = (int)((360*1.2-360)/2+15);
    private final int XHIGH = (int)(360*1.2-(360*1.2-360)/2-15);
    private final int YLOW = (int)(640*1.2-(640*1.2-640)/2-15);
    private final int YHIGH = 15;
    private double x = XLOW+XHIGH/2;
    private double y;
    private int[] xs = new int[4],ys = new int[4];
    private int waitingUntil = 0;
    private int waitTime = 0;
    private Color color;
    /**
     * wait time just staggers the different objects
     */
    public Sawblade(int waitTime, int waitingUntil){
        this.waitTime = waitTime;
        time = waitingUntil-waitTime;
        reset();
    }
    
    /**
     * reset
     * resets sawblade to be above with new angle and timing
       */
    public void reset(){
        y=YHIGH;
        x = (int)(Math.random()*(XHIGH-XLOW)+XLOW);
        angle = Math.random()*(Math.PI/2.0)+5*Math.PI/4.0; //radians
        waitingUntil = time+waitTime;
        marked = false;
        color = Color.RED;
    }
    
    @Override
    public void paintComponent(Graphics g) //required
    {
        Graphics2D g2 = (Graphics2D) g;
        // invoke the draw method 
        // ...
        draw(g2);
    }
    
    public boolean isInside(BoxObject o){
        int[] xs2 = o.getxs();
        int[] ys2 = o.getys();
        if(xs[0]>=xs2[0]&&xs[0]<=xs2[1]||xs[1]<=xs2[1]&&xs[1]>=xs2[0])
            if(ys[0]>=ys2[0]&&ys[0]<=ys2[2]||ys[2]<=ys2[2]&&ys[2]>=ys2[0])
                return true;
        
        return false;
    }
    
    public void mark(){
        color = Color.GREEN;
        marked = true;
    }
    
    public boolean isMarked(){
        return marked;
    }
    
    public int getX(){
        return (int)x;
    }
    
    public int getY(){
        return (int)y;
    }
    
    public int[] getxs(){
        return xs;
    }
    
    public int[] getys(){
        return ys;
    }
    
    public void setTime(int time){
        this.time = time;
    }
    
    public void draw(Graphics2D page){
        page.setColor(color);
        int xTemp = (int)x;
        int yTemp = (int)y;
        xs[0]=xTemp-15;xs[1]=xTemp+15;xs[2]=xTemp+15;xs[3]=xTemp-15;
        ys[0]=yTemp-15;ys[1]=yTemp-15;ys[2]=yTemp+15;ys[3]=yTemp+15;
        page.drawPolygon(xs,ys,4);
    }
    
    public void run(){
        while(true){

            if(time>=waitingUntil){
                x=8*Math.cos(angle)+x;
                y = -8*Math.sin(angle)+y;
            }
            
            if(x<XLOW){
                x=XLOW;
                angle = 3*Math.PI/2.0+(3*Math.PI/2.0-angle);
            }
            if(x>XHIGH){
                x=XHIGH;
                angle = 3*Math.PI/2.0-(angle-3*Math.PI/2.0);
            }
            if(y>YLOW){
                y=YLOW;
                angle = 2*Math.PI-angle;
            }
            if(y<YHIGH){
                reset();
            }
            
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
