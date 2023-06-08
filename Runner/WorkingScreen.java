
/**
 * Just shows the screen
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class WorkingScreen extends JComponent implements Runnable
{
    private Color rim, background;
    public WorkingScreen(Color rim, Color background){
        this.rim = rim;
        this.background = background;
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
        int[] x = {(int)((360*1.2-360)/2),(int)(360*1.2-((360*1.2-360)/2)),
        (int)(360*1.2-((360*1.2-360)/2)),(int)((360*1.2-360)/2)};
        
        int[] y = {(int)((640*1.2-640)/2),(int)((640*1.2-640)/2),
        (int)(640*1.2-((640*1.2-640)/2)),(int)(640*1.2-((640*1.2-640)/2))};
        
        page.setColor(background);
        page.fillPolygon(x,y,4);
        page.setColor(rim);
        page.drawPolygon(x,y,4);
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
