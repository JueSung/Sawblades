/**
 * The little person you control
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Avatar extends JComponent implements Runnable, BoxObject
{
    private int time;
    private final int YLOW = (int)(640*1.2-(640*1.2-640)/2-15);
    private int x = (int)(360*1.2/2), y=YLOW;
    private final int XLOW = (int)((360*1.2-360)/2+15);
    private final int XHIGH = (int)(360*1.2-(360*1.2-360)/2-15);
    private int[] xs = new int[4], ys = new int[4];
    public Avatar(){
        
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
    
    public boolean isAbove(BoxObject o){
        if(Math.abs(x-o.getX())<=5&&y<o.getY()){
            return true;
        }
        return false;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int[] getxs(){
        return xs;
    }
    
    public int[] getys(){
        return ys;
    }
    
    public boolean isOnGround(){
        return y==YLOW;
    }
    
    public void draw(Graphics2D page){
        //hitbox
        xs[0]=x-15;xs[1]=x+15;xs[2]=x+15;xs[3]=x-15;
        ys[0]=y-15;ys[1]=y-15;ys[2]=y+15;ys[3]=y+15;
        Color color = Color.black;
        page.setColor(color);
        page.drawPolygon(xs,ys,4);
    }
    
    public void run(){
        boolean spaceFlag=false,spaceFlag2=false;
        boolean spaceHasReleased=true;//differentiates connected space holds
        int spaceDuration = -10;
        int yOriginal2=0;//initialzied to arbitrary number
        int spaceDuration2 = -10;
        while(true){
            if(IsKeyPressed.isLEFTPressed()){
                x-=8;
            }
            if(IsKeyPressed.isRIGHTPressed()){
                x+=8;
            }
            if(IsKeyPressed.isSPACEPressed()){
                if(spaceFlag&&spaceFlag2==false&&spaceHasReleased){
                    spaceDuration2=-10;
                    spaceFlag2=true;
                    yOriginal2=y;
                }
                if(spaceFlag==false){
                    spaceDuration = -10;
                    spaceFlag = true;
                }
                spaceHasReleased = false;
            }
            if(!IsKeyPressed.isSPACEPressed()){
                spaceHasReleased=true;
            }
            if(spaceFlag&&!spaceFlag2){
                y=(int)(1.5*Math.pow(spaceDuration,2)+YLOW-150);
                spaceDuration++;
                if(spaceDuration>10)
                    spaceFlag = false;
            }
            if(spaceFlag2){
                y=(int)(1.5*Math.pow(spaceDuration2,2)+yOriginal2-150);
                spaceDuration2++;
                if(y>YLOW){
                    spaceFlag = false;
                    spaceFlag2 = false;
                }
            }
            if(y>YLOW)
                y=YLOW;
            if(x<XLOW)
                x=XLOW;
            if(x>XHIGH)
                x=XHIGH;
            
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
