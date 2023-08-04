package SimplePong;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH=1000;
    public static final int HEIGHT=WIDTH * 9/16;
    public boolean running=false;
    private Thread gameThread;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    public Game() {
        canvasSetup();
        new Window("Ping Pong",this);


    }
    private void canvasSetup() {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    public void run() {

    }
    public void start(){
        gameThread=new Thread(this);
        gameThread.start();
        running=true;

    }
    public void stop(){
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    public static void main(String [] args){
        new Game();
    }

}
