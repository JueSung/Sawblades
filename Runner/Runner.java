/**
 * The screen
 */

import java.awt.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Runner extends JComponent //implements Runnable
{
    private final int APPLET_WIDTH = 400;    //Size constants
    private final int APPLET_HEIGHT = 150;
    private final int HEIGHT_MIN = 100;
    private final int VARIANCE = 45;
    private int time = 0;
    Random generator = new Random();

    private Car c1;

    //-----------------------------------------------------------------
    //  Creates several stick figures with varying characteristics.
    //-----------------------------------------------------------------
    public Runner () //init in applet is like constructor; runs only once
    {                   //Different than start.
        //int h1,h2,h3,h4;  // heights of stick figures

        //h1 = HEIGHT_MIN + generator.nextInt(VARIANCE);
        //h2 = HEIGHT_MIN + generator.nextInt(VARIANCE);
        //h3 = HEIGHT_MIN + generator.nextInt(VARIANCE);
        //h4 = HEIGHT_MIN + generator.nextInt(VARIANCE);

        //0,0 is upper left
        //+x moves objects to right
        //+y moves objects down
        //coordinates start top left.
        //figure1 = new StickFigure (100, 150, Color.red, h1);
        //x, y, color, height
        //figure2 = new StickFigure(150, 150, Color.green, h2);
        //figure3 = new StickFigure(200, 150, Color.cyan, h3);
        //figure4 = new StickFigure(250, 150, Color.yellow, h4);

        //myImage = new Window(45,10,new Color(200,200,200));
        
        Color white = Color.white;
        System.out.println(white.getRed() + " " + white.getGreen() + " " + white.getBlue());
        
        ground = new Floor(0,300,500,125, new Color(86,125,70));
        road = new Floor(0,310,500,30,Color.black,Color.yellow);
        //dashes = new Floor(0,323,500,4,Color.yellow);
        sky = new Sky(new Color (225,240,255),new Color(55,120,255),new Color(55,120,255),Color.black,0,0,500,425);
        sun = new Sun(50,50,true,Color.yellow);
        moon = new Sun(50,50,false,Color.white);
        b1 = new Building(5,130,110,170);
        b2 = new Building(130,220,140,80);
        b3 = new Building(310,85,80,215);
        b4 = new Building(400,145,95,155);
        c1 = new Car(300,true,Color.blue,100);
        c2 = new Car(120,false,Color.yellow,30);
        c3 = new Car(0, true, Color.red, 65);
        c4 = new Car(-300, false, Color.green, 23);
        c5 = new Car(1203, true, Color.orange, 79);
        c6 = new Car(500, false, Color.gray, 40);
        cloud1 = new Cloud(200,80,100, true);
        cloud2 = new Cloud(430, 40, 75, false);
        cloud3 = new Cloud(0, 100, 87, true);
        filter = new Filter(500,125, Color.black);
        ufo = new UFO(Color.green,Color.gray,Color.yellow,Color.white);
        
        
        Thread t1 = new Thread(sun);
        t1.start();
        Thread themoon = new Thread(moon);
        themoon.start();
        Thread t2 = new Thread(sky);
        t2.start();
        Thread tb1 = new Thread(b1);
        tb1.start();
        Thread tb2 = new Thread(b2);
        tb2.start();
        Thread tb3 = new Thread(b3);
        tb3.start();
        Thread tb4 = new Thread(b4);
        tb4.start();
        Thread tfilter = new Thread(filter);
        tfilter.start();
        Thread tc1 = new Thread(c1);
        tc1.start();
        Thread tc2 = new Thread(c2);
        tc2.start();
        Thread tc3 = new Thread(c3);
        tc3.start();
        Thread tc4 = new Thread(c4);
        tc4.start();
        Thread tc5 = new Thread(c5);
        tc5.start();
        Thread tc6 = new Thread(c6);
        tc6.start();
        Thread tcloud1 = new Thread(cloud1);
        tcloud1.start();
        Thread tcloud2 = new Thread(cloud2);
        tcloud2.start();
        Thread tcloud3 = new Thread(cloud3);
        tcloud3.start();
        Thread tufo = new Thread(ufo);
        tufo.start();
        
        
        
        /*
        Thread t1 = new Thread(ground);
        t1.start();
        Thread t2 = new Thread(figure2);
        t2.start();
        Thread t3 = new Thread(figure3);
        t3.start();
        Thread t4 = new Thread(myImage);
        t4.start();
        */

        //setBackground (Color.black); //sets the color of background
        //setSize (APPLET_WIDTH, APPLET_HEIGHT); //Sets up applet window

        //figure2.draw(page);


    }

    //-----------------------------------------------------------------
    //  Paints the stick figures on the applet.
    //-----------------------------------------------------------------
    public void paintComponent(Graphics g)
    {
        time +=1;
        System.out.println(time);
        //1000 is full day + night
        if(time >= 1000){
            time = 0;
        }
        Graphics2D page = (Graphics2D) g;
        //page = this.page;
        //draw buildings on top of background so that it is in front of it.
        sky.setTime(time);
        sun.setTime(time);
        moon.setTime(time);
        b1.setTime(time);
        b2.setTime(time);
        b3.setTime(time);
        b4.setTime(time);
        filter.setTime(time);
        c1.setTime(time);
        c2.setTime(time);
        c3.setTime(time);
        c4.setTime(time);
        c5.setTime(time);
        c6.setTime(time);
        
        
        sky.draw(page);
        ground.draw(page);
        road.draw(page);
        
        filter.draw(page);
        sun.draw(page);
        moon.draw(page);
        b1.draw(page);
        b2.draw(page);
        b3.draw(page);
        b4.draw(page);
        c1.draw(page);
        c2.draw(page);
        c3.draw(page);
        c4.draw(page);
        c5.draw(page);
        c6.draw(page);
        ufo.draw(page);
        cloud1.draw(page);
        cloud2.draw(page);
        cloud3.draw(page);
        
        
        //myImage.draw(page);
        //figure1.draw (page);
        //figure2.draw (page);
        //figure3.draw (page);
        //figure4.draw (page);
        //ground.draw(page);

        //Thread t1 = new Thread(ground);
        //t1.start();

        /*
        int newHeight = HEIGHT_MIN + generator.nextInt(VARIANCE);
        figure1.setHeight(newHeight);
        newHeight = HEIGHT_MIN + generator.nextInt(VARIANCE);
        figure2.setHeight(newHeight);
        newHeight = HEIGHT_MIN + generator.nextInt(VARIANCE);
        figure3.setHeight(newHeight);
        newHeight = HEIGHT_MIN + generator.nextInt(VARIANCE);
        figure4.setHeight(newHeight);
        */
        //try{
        //pause program for quarter-sec (in miliseconds)
        //Thread.sleep(250);
        //}
        //catch(InterruptedException e){}

        //figure1.draw(page);

        //repaint(); //IMPORTANT LINE
    }

    public void nextFrame()
    {
        //figure1.setHeight(HEIGHT_MIN + generator.nextInt(VARIANCE));
        //figure2.setHeight(HEIGHT_MIN + generator.nextInt(VARIANCE));
        //figure3.setHeight(HEIGHT_MIN + generator.nextInt(VARIANCE));
        //figure4.setHeight(HEIGHT_MIN + generator.nextInt(VARIANCE));
        //running ++;
        //ground.setX(running);
        //Thread t1 = new Thread(ground);
        //t1.start();

        repaint();

    }

    public void run()
    {
    }
}
