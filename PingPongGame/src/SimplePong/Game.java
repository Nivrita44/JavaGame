package SimplePong;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 5162710183389028792L;

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


    private void initialize(){
        ball=new Ball();
        paddle1=new Paddle(Color.green,true);
        paddle2=new Paddle(Color.red,false);



    }
    private void canvasSetup() {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000 / amountOfTicks;
        double delta=0;
        long timer=System.currentTimeMillis();
        int frames=0;
        while(running)
        {
            long now =System.nanoTime();
            delta +=(now - lastTime) /ns;
            lastTime =now;
            while(delta >= 1)
            {
                update();
                delta--;
            }
            if(running) draw();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer +=1000;
                System.out.println("FPS : " +frames);
                frames = 0;
            }
        }
        stop();

    }

    private void update() {
    }

    private void draw() {

        BufferStrategy buffer = this.getBufferStrategy();
        if(buffer == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g =buffer.getDrawGraphics();
        drawBackground(g);

       ball.draw(g);


        g.dispose();
        buffer.show();

    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.white);
        Graphics2D g2d= (Graphics2D) g;
        Stroke dashed =new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{10},0);
        g2d.setStroke(dashed);
        g2d.drawLine(WIDTH/2, 0,WIDTH /2,HEIGHT);


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
    public static int sign(double v) {
        if( v <= 0)
        {
            return -1;
        }
        return 1;

    }
    public static void main(String [] args){
        new Game();
    }

}
