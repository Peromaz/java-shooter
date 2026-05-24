package Main;

import Player.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int FPS = 60;
    //Screen Settings
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;


    Thread gameThread;
    KeyHandler inputHandler = new KeyHandler();
    Player player = new Player();



    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true); // should make rendering faster?
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInteval = (double) 1000000000 /FPS; //amount of time between each frame ~ about .016 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        //game loop
        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInteval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                //update game logic
                update();
                //render
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                System.out.println(player.direction);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update(inputHandler);
    }
    public void paintComponent(Graphics g){ //built in Jpanel method
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //////////
        player.draw(g2);
        ///////
        g2.dispose(); //releases system resources
    }
}
