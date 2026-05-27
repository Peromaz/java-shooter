package Main;

import Physics.Vector2D;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable {
    public static final int FPS = 60;
    //Screen Settings
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static double delta = 0;
    public static double deltaTime = 0;

    public static Vector2D relativeMousePosition = new Vector2D(0,0);
    Thread gameThread;
    KeyHandler inputHandler = new KeyHandler();
    Player player = new Player();

//    private Insets windowInsets = new Insets(0,0,0,0);

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true); // should make rendering faster?
        this.addKeyListener(inputHandler);
        this.setFocusable(true);

        this.addMouseMotionListener(new MouseAdapter() {
            private void updateMouse(MouseEvent e){
                relativeMousePosition.x = e.getX(); //- windowInsets.left;
                relativeMousePosition.y = e.getY(); //- windowInsets.top;
            }
            @Override
            public void mouseMoved(MouseEvent e){
                updateMouse(e);
            }
            @Override
            public void mouseDragged(MouseEvent e){
                updateMouse(e);
            }
        });
    }

//    public void setWindowInsets(Insets insets) {
//        this.windowInsets = insets;
//    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS; //amount of time between each frame ~ about .016 seconds
        delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        //game loop
        while(gameThread != null){
            currentTime = System.nanoTime();
            long elapsed = currentTime - lastTime;
            delta += elapsed / drawInterval;
            timer += elapsed;
            lastTime = currentTime;

            if (delta >= 1){
                deltaTime = 1.0/FPS;
                //update game logic
                update();
                //render
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                System.out.println(player.direction);
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
