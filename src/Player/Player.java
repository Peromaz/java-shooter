package Player;

import Main.KeyHandler;
import Physics.Vector2D;

import java.awt.*;

public class Player {
    private Vector2D position;
    private Vector2D velocity;
    public Vector2D direction;
    private int PLAYER_SIZE = 50;
    private int PLAYER_SPEED = 1;
    private double health;

    private Graphics2D g2;
    public Player(){
        position = new Vector2D(0,0);
        velocity = new Vector2D(0,0);
        direction = new Vector2D(0,0);
        health = 100;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fillOval( (int) position.x, (int) position.y,PLAYER_SIZE,PLAYER_SIZE);
    }
    
    public void applyPhysics(){
        velocity = new Vector2D(direction.x * PLAYER_SPEED,direction.y * PLAYER_SPEED );
        position.x += velocity.x;
        position.y += velocity.y;
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
