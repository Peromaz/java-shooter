package Player;

import Main.GamePanel;
import Main.KeyHandler;
import Physics.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Player {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D direction;

    private int PLAYER_SIZE = 50;
    private int PLAYER_SPEED = 300; //Pixels per second
    //private float health;

    private Graphics2D g2;
    public Player(){
        position = new Vector2D(0,0);
        velocity = new Vector2D(0,0);
        direction = new Vector2D(0,0);
        //health = 100;
    }

    public Vector2D getDirection(){
        return direction;
    }

    public Ellipse2D.Float centeredEllipse(float cx, float cy, float width, float height){
        return new Ellipse2D.Float(cx - width / 2, cy - height / 2, width, height);
    }

    public Rectangle2D.Float centeredRect(float cx, float cy, float width, float height) {
        return new Rectangle2D.Float(cx - width / 2, cy - height / 2, width, height);
    }

    private double getAngleFromObjectToMouse(float posX, float posY){
        float dx = GamePanel.relativeMousePosition.x - posX;
        float dy = GamePanel.relativeMousePosition.y - posY;
        return Math.atan2(dy, dx);
    }
    public void draw(Graphics2D g2){
        //player shapes
        Ellipse2D.Float playerBody = centeredEllipse(position.x, position.y, PLAYER_SIZE, PLAYER_SIZE);

        Rectangle2D.Float playerGun = centeredRect(position.x + 50, position.y, 25, 5);

        double angleInDegrees = getAngleFromObjectToMouse(position.x, position.y);
//        System.out.printf("player=(%.1f,%.1f) mouse=(%.1f,%.1f) angle=%.2f%n",
//                position.x, position.y,
//                GamePanel.relativeMousePosition.x,
//                GamePanel.relativeMousePosition.y,
//                Math.toDegrees(angleInDegrees));
        AffineTransform transform = AffineTransform.getRotateInstance(angleInDegrees,position.x, position.y );
        AffineTransform old = g2.getTransform();

        g2.transform(transform);
        g2.setColor(Color.RED);
        g2.fill(playerGun);
        g2.setColor(Color.BLACK);
        g2.draw(playerGun);
        g2.setTransform(old);

        g2.setColor(Color.GREEN);
        g2.drawLine(
                (int) position.x, (int) position.y,
                (int) GamePanel.relativeMousePosition.x,
                (int) GamePanel.relativeMousePosition.y
        );

        g2.setColor(Color.WHITE);
        g2.fill(playerBody);
        g2.setColor(Color.BLACK);
        g2.draw(playerBody);


    }
    
    public void applyPhysics(){
        velocity = new Vector2D(direction.x * PLAYER_SPEED,direction.y * PLAYER_SPEED );
        position.x += velocity.x * (float) GamePanel.deltaTime;
        position.y += velocity.y * (float) GamePanel.deltaTime;
    }

    public void handleInput(KeyHandler input){
        //up-down
        if (input.upPressed){
            direction.y = -1;
        }else if(input.downPressed){
            direction.y = 1;
        }else{
            direction.y = 0;
        }
        //left-right
        if (input.leftPressed){
            direction.x = -1;
        }else if (input.rightPressed){
            direction.x = 1;
        }else{
            direction.x = 0;
        }

        direction.normalize();
    }

    /**
     * updates the physics and handles input of a player
     * basically allows you to do things
     * @param input
     */
    public void update(KeyHandler input){
        handleInput(input);
        applyPhysics();
    }

}
