
/**
 * Write a description of class Car here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.*;    
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.Arrays;

public class Car extends JComponent implements Runnable
{
    private int time = 0, xstart, ystart, speed, operator;
    private Color color;
    private int[] x = new int[6], y = new int[6];
    private boolean direction; //left is true, right is false
    
    public Car(int xstart, boolean direction, Color color, int speed){
        this.direction = direction;
        this.speed = speed;
        this.color = color;
        this.xstart = xstart;
        if(direction){
            ystart = 317;
            operator = 1;
        }
        else{
            ystart = 337;
            operator = -1;
        }
        x[0] = xstart;
        x[1] = xstart+25*operator;
        x[2] = xstart+25*operator;
        x[3] = xstart+10*operator;
        x[4] = xstart+5*operator;
        x[5] = xstart;
        y[0] = ystart;
        y[1] = ystart;
        y[2] = ystart-15;
        y[3] = ystart-15;
        y[4] = ystart-10;
        y[5] = ystart-10;
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
        //headlight
        if(time>500){
            page.setColor(new Color(249,255,206,200));
            if(direction){
                int[] headlightX = {x[0]+4,x[0]-10,x[0]-30};
                int[] headlightY = {y[0]-10,y[0]+4,y[0]+4};
                page.fillPolygon(headlightX,headlightY,3);
            }
            else{
                int[] headlightX = {x[0]-4,x[0]+10,x[0]+30};
                int[] headlightY = {y[0]-10,y[0]+4,y[0]+4};
                page.fillPolygon(headlightX,headlightY,3);
            }
        }
        //car body
        page.setColor(color);
        page.fillPolygon(x,y,x.length);
        //wheels
        page.setColor(Color.gray);
        if(direction){
            page.fillOval(x[0]+4,y[0]-4,8,8);
            page.fillOval(x[0]+15,y[0]-4,8,8);
        }
        else{
            page.fillOval(x[0]-10,y[0]-4,8,8);
            page.fillOval(x[0]-21,y[0]-4,8,8);
        }
    }
    
    public void run(){
        double interval = (double)speed/100 * (double)1/50*500;
        while(true){
            int ufoX = (int)MouseInfo.getPointerInfo().getLocation().getX()-25;
            int ufoY = (int)MouseInfo.getPointerInfo().getLocation().getY()-75;
            
            for(int i=0;i< x.length;i++){
                x[i] -= (int)(interval * operator);
            }
            if(Math.abs(ufoX - (double)(x[0]+x[1])/2) <= 40 && Math.abs(ufoY+70-(double)(y[0]+y[2])/2) <= 50){
                for(int i=0; i < y.length; i++){
                    y[i] -= 3;
                }
            }
            else{
                if(y[0] != ystart){
                    for(int i=0; i <y.length; i++){
                        y[i]+=3;
                    }
                }
            }
            
            //recycles car
            if(x[0] >=1600 || x[0] <= -1100){
                for(int i = 0;i<x.length;i++){
                    x[i]+=operator*1600;
                }
            }
            try{
                Thread.sleep(30);
            }
            catch(Exception e){}
        }
    }
}
