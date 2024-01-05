package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int origTileSize = 16; // 16x16
    final int scale = 3;

    final public int tileSize = origTileSize*scale; //48x48px isa ka tile
    final public int screenCol = 16; //16 tiles horizontally
    final public int screenRow = 12; //12 tiles vertically
    final public int screenWidth = tileSize*screenCol; //768 pixels
    final public int screenHeight = tileSize*screenRow; //576 pixels
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    int FPS = 60;

    //ENTITIES
    Player player = new Player(this,keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.decode("#2E1F3D"));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); //passes GamePanel class to constructor
        gameThread.start();
    }

    /*@Override
    public void run() {
        double drawInterval = 1000000000/FPS; // using nanosecond here. 0.1666~ second or 1/60 of a second.
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint(); //how u call paintComponent method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; //double(nano) man ang gigamit. divide by 100k para mahimong milli

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    */

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //FPS
        long timer = 0;
        long drawCount = 0;

        while(gameThread!=null) {
            currentTime = System.nanoTime();
//            System.out.println("last: " + lastTime);
//            System.out.println("current: " + currentTime);

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

//            System.out.println("delta: " + delta);

            if(delta >= 1) { //1 second???
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        tileManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();
    }

}
