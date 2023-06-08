/**
 * The little person you control
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Avatar extends JComponent implements Runnable
{
    private int time;
    private final int YLOW = (int)(640*1.2-(640*1.2-640)/2-15);
    private int x = (int)(360*1.2/2), y=YLOW;
    private final int XLOW = (int)((360*1.2-360)/2+15);
    private final int XHIGH = (int)(360*1.2-(360*1.2-360)/2-15);
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
    
    public void draw(Graphics2D page){
        System.out.println(x);
        //hitbox
        int[] xHitBox = {x-15,x+15,x+15,x-15};
        int[] yHitBox = {y-15,y-15,y+15,y+15};
        Color color = Color.black;
        page.setColor(color);
        page.drawPolygon(xHitBox,yHitBox,4);
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
