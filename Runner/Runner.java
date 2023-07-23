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

    //private Car c1;
    private WorkingScreen screen;
    private Avatar you;
    private Sawblade s1,s2,s3,s4,s5;
    private ArrayList<Sawblade> sawblades = new ArrayList();
    private ArrayList<Coins> coins = new ArrayList();
    private Timer timer;
    private Score score;
    
    private boolean gameOver = false;

    public Runner () //init in applet is like constructor; runs only once
    {
        //c1 = new Car(300,true,Color.blue,100);
        screen = new WorkingScreen(Color.red,new Color(255,225,225));
        timer = new Timer();
        you = new Avatar();
        score = new Score();
        s1 = new Sawblade(50,0);
        s2 = new Sawblade(50,75);
        s3 = new Sawblade(35,75);
        s4 = new Sawblade(70,250);
        s5 = new Sawblade(70,250);
        sawblades.add(s1);sawblades.add(s2);sawblades.add(s3);
        sawblades.add(s4);sawblades.add(s5);

        //Thread tc1 = new Thread(c1);
        //tc1.start();
        Thread tWC = new Thread(screen);
        tWC.start();
        Thread tt = new Thread(timer);
        tt.start();
        Thread tY = new Thread(you);
        tY.start();
        Thread tSC = new Thread(score);
        tSC.start();
        Thread tS1 = new Thread(s1);
        tS1.start();
        Thread tS2 = new Thread(s2);
        tS2.start();
        Thread tS3 = new Thread(s3);
        tS3.start();
        Thread tS4 = new Thread(s4);
        tS4.start();
        Thread tS5 = new Thread(s5);
        tS5.start();
    }

    //-----------------------------------------------------------------
    //  Paints the stick figures on the applet.
    //-----------------------------------------------------------------
    public void paintComponent(Graphics g)
    {
        IsKeyPressed.main();
        time +=1;
        //System.out.println(time);
        Graphics2D page = (Graphics2D) g;
        //page = this.page;
        int[] fullScreenX = {0,500,500,0};
        int[] fullScreenY = {0,0,800,800};
        page.setColor(Color.RED);
        if(gameOver)
            page.fillPolygon(fullScreenX,fullScreenY,4);
        
        //c1.setTime(time);
        timer.setTime(time);
        s1.setTime(time);
        s2.setTime(time);
        s3.setTime(time);
        s4.setTime(time);
        s5.setTime(time);
        //System.out.println(time);
        
        
        //c1.draw(page);
        screen.draw(page);
        timer.draw(page);
        you.draw(page);
        score.draw(page);
        s1.draw(page);
        s2.draw(page);
        s3.draw(page);
        s4.draw(page);
        s5.draw(page);
        for(int i=0; i<coins.size();i++){
            if(coins.get(i).isInUse())
                coins.get(i).draw(page);
        }
        
        //-------------------------------
        for(int i = 0;i<sawblades.size();i++){
            if(you.isInside(sawblades.get(i)))
                gameOver = true;
            if(you.isAbove(sawblades.get(i)))
                sawblades.get(i).mark();
            if(you.isOnGround())
                if(sawblades.get(i).isMarked()){
                    int coinLocation = sawblades.get(i).getX();
                    getCoinIndex(coinLocation);
                    sawblades.get(i).reset();
                }
        }
        for(int i =0; i<coins.size();i++){
            //would be more efficient if used two arraylists instead of
            //checking the boolean every time for inuse
            if(you.isInside(coins.get(i))&&coins.get(i).isInUse()){
                coins.get(i).collected();
                timer.increment();
                score.increment();
            }
        }

        //repaint(); //IMPORTANT LINE
    }

    public int getCoinIndex(int x){
        int index=-1;
        for(int i=0; i<coins.size()&& index == -1;i++){
            if(!coins.get(i).isInUse()){
                index = i;
                coins.get(i).reUse(x);
                return index;
            }
        }
        Coins coin = new Coins(x);
        Thread thread = new Thread(coin);
        thread.start();
        coins.add(coin);
        return coins.size()-1;
    }
    
    public void nextFrame()
    {
        repaint();
    }

    public void run()
    {
    }
}
