package SimplePong;
import java.awt.*;

public class Paddle {
    private int x,y;
    private int vel=0;
    private int speed = 10;
    private int width = 22, height=85;
    private int score =0;
    private Color color;
    private boolean left;

    public Paddle(Color c, boolean left){
         color = c;
         this.left =left;

         if(left)
             x=0;
         else
             x=Game.WIDTH -width;
         y=Game.HEIGHT/2 - height /2;

}
    public void addPoint(){
        score++;
    }
}
