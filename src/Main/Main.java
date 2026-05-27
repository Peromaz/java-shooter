package Main;

import javax.swing.*;
import java.awt.*;
/*
TODO List:
    - player looks at mouse position (WIP)
    - Add Shooting
    - Add AABB collision
    - Add UI elements=
    Add eventually:
    - Add multiplayer
    - inventory Keys 1-9
    - Title screen, Multiple Jframes?
    - Add sound fx
s
 */
public class Main {
    private final static String GAME_TITLE = "1v1 Shooter";

    public static void main(String[] args){
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.opengl.fbobject", "false");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("1v1 Adventure");

        GamePanel gamePanel = new GamePanel();
        window.setContentPane(gamePanel);
        window.setUndecorated(true);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

//        Insets insets = window.getInsets();
//        window.setSize(
//                GamePanel.SCREEN_WIDTH + insets.left + insets.right,
//                GamePanel.SCREEN_HEIGHT + insets.top + insets.bottom
//        );
//        System.out.println("Insets: " + insets);
//        gamePanel.setWindowInsets(insets);
        gamePanel.startGameThread();
    }
}
