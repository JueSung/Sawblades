
import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;
public class Score extends JComponent implements Runnable
{
    private int time;
    private int score;
    public Score(){
        score=0;
    }
    
    public void increment(){
        score++;
    }
    
    public void draw(Graphics2D page){
        Color color = new Color(0,0,0,100);
        page.setColor(color);
        int x;

        String scoreString = Integer.toString(score);
        page.setFont(new Font("Arial", Font.PLAIN, 80));
        if(score<10)
            x=194;
        else if(score<100)
            x=172;
        else//assuming a score doesnt become over 1000
            x=128;
        page.drawString(scoreString, x, 155);
        //size 80 dimensions
        //single digit -- x->44;y->58
        //double digit -- x-> 88; y->58
        //triple digit -- x.>176 i assume; y->58
        
        //page.setColor(Color.black);
        //x216 is middle
        //int[] plsDraw = {216,260,260,216};
        //int[] plsDraw2 = {97,97,155,155};
        //page.drawPolygon(plsDraw,plsDraw2,4);
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
        while(true){
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
