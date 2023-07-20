/**
 * The little person you control
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Coins extends JComponent implements Runnable, BoxObject
{
    private int time=0;
    private boolean inUse = true;
    //assuming hitbox size of 12 by 12
    private final int XLOW = (int)((360*1.2-360)/2+6);
    private final int XHIGH = (int)(360*1.2-(360*1.2-360)/2-6);
    private final int YLOW = (int)(640*1.2-(640*1.2-640)/2-6);
    private double x;
    private double y;
    private int[] xs = new int[4],ys = new int[4];
    private int waitingUntil = 0;
    private int waitTime = 0;
    private Color color;
    /**
     * wait time just staggers the different objects
     */
    public Coins(int x){
        color = Color.yellow;
        this.x = x;
        if(this.x<XLOW)
            this.x=XLOW;
        else if(this.x>XHIGH)
            this.x=XHIGH;
        y=YLOW;
    }
    
    public void reUse(int x){
        //color is still yellow
        this.x = x;
        if(this.x<XLOW)
            this.x=XLOW;
        else if(this.x>XHIGH)
            this.x=XHIGH;
        //y is still yLOW
        inUse = true;
    }
    
    @Override
    public void paintComponent(Graphics g) //required
    {
        Graphics2D g2 = (Graphics2D) g;
        // invoke the draw method 
        // ...
        draw(g2);
    }
    
    public void collected(){
        inUse = false;
    }
    
    public boolean isInUse(){
        return inUse;
    }
    
    public boolean isInside(BoxObject o){
        int[] xs2 = o.getxs();
        int[] ys2 = o.getys();
        if(xs[0]>=xs2[0]&&xs[0]<=xs2[1]||xs[1]<=xs2[1]&&xs[1]>=xs2[0])
            if(ys[0]>=ys2[0]&&ys[0]<=ys2[2]||ys[2]<=ys2[2]&&ys[2]>=ys2[0])
                return true;
        
        return false;
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
        xs[0]=xTemp-6;xs[1]=xTemp+6;xs[2]=xTemp+6;xs[3]=xTemp-6;
        ys[0]=yTemp-6;ys[1]=yTemp-6;ys[2]=yTemp+6;ys[3]=yTemp+6;
        page.fillPolygon(xs,ys,4);
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
