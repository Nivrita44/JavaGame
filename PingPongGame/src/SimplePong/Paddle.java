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

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);

        int sx;
        String scoreText = Integer.toString(score);
        Font font = new Font("Roboto",Font.PLAIN,50);
        int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
        int padding = 25;

        if(left)
            sx = Game.WIDTH/2 - padding -strWidth;
        else
            sx=Game.WIDTH/2 + padding;

        g.setFont(font);
        g.drawString(scoreText,sx,50);
    }

    public void update(Ball ball) {
        y = Game.ensureRange(y += vel,0,Game.HEIGHT-height);
        int ballX = ball.getX();
        int ballY = ball.getY();


        if(left){
            if(ballX <= width && ballY + Ball.SIZE >= y && ballY <= y+height)
            ball.changeXDir();
        }
        else
        {
            if(ballX + Ball.SIZE >=Game.WIDTH - width && ballY + Ball.SIZE >= y && ballY <= y + height)
                ball.changeXDir();
        }
    }
}
