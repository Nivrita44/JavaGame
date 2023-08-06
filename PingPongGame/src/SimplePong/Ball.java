package SimplePong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public static final int SIZE =16;
    private int x,y;
    private int xVel,yVel;
    private int speed = 5;


    public Ball(){
        reset();

    }
    private void reset(){
        x=Game.WIDTH /2 - SIZE/2 ;
        y=Game.HEIGHT /2 -SIZE/2;

        xVel=Game.sign(Math.random() * 2.0 - 1);
        yVel=Game.sign(Math.random() * 2.0 - 1);
        xVel=1;
        yVel=1;

    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public void changeYDir(){
        yVel *= -1;
    }
    public void changeXDir(){
        xVel *= -1;
    }

    public void draw(Graphics a) {
        a.setColor(Color.white);
        a.fillRect(x,y,SIZE,SIZE);

    }

    public void update(Paddle paddle1, Paddle paddle2) {
        x += xVel * speed;
        y += yVel * speed;

        if(y + SIZE >= Game.HEIGHT || y<=0)
            changeYDir();
        if(x + SIZE >= Game.WIDTH) {
            paddle1.addPoint();
            reset();
        }
        if(x <= 0){
            paddle2.addPoint();
            reset();
        }

    }
}

