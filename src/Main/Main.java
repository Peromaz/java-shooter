package Main;

import javax.swing.*;
import java.awt.*;
/*
TODO List:
    - player looks at mouse position
    - Add AABB collision
    - Add Shooting
    - Add UI elements

    Add eventually:
    - Add multiplayer
    - inventory Keys 1-9
    - Title screen, Multiple Jframes?
    - Add sound fx

 */
public class Main {
    private final static String GAME_TITLE = "1v1 Shooter";
    private static int CANVAS_WIDTH = 1920;
    private static int CANVAS_HEIGHT = 1080;

    public static void main(String[] args){
        System.setProperty("sun.java2d.opengl", "true");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("1v1 Adventure");

        GamePanel gamePanel = new GamePanel();
        window.setContentPane(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Insets insets = window.getInsets();
        window.setSize(
                GamePanel.SCREEN_WIDTH + insets.left + insets.right,
                GamePanel.SCREEN_HEIGHT + insets.top + insets.bottom
        );
        System.out.println("Insets: " + insets);
        gamePanel.setWindowInsets(insets);
        gamePanel.startGameThread();

    }
}
