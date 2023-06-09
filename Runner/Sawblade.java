/**
 * The little person you control
 */

import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Sawblade extends JComponent implements Runnable
{
    private int time=0;
    public Sawblade(){
        
    }
    
    @Override
    public void paintComponent(Graphics g) //required
    {
        Graphics2D g2 = (Graphics2D) g;
        // invoke the draw method 
        // ...
        draw(g2);
    }
    
    public void setTime(int time){
        this.time = time;
    }
    
    public void draw(Graphics2D page){
        
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
