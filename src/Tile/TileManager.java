package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.screenCol][gamePanel.screenRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFile) {
        try{
            InputStream is = getClass().getResourceAsStream(mapFile); //import text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //reads text file

            int col = 0, row = 0;

            while(col < gamePanel.screenCol && row < gamePanel.screenRow) {
                String line = br.readLine(); //reads 1 line of txt
                while(col < gamePanel.screenCol) {
                    String numbers[] = line.split(" "); //splits the line into indiv numbers
                    int num = Integer.parseInt(numbers[col]); //saves as an int instead of string

                    mapTileNum[col][row] = num;
                    col++;
                } if(col == gamePanel.screenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        int col = 0, row = 0, x = 0, y = 0;

        while(row < gamePanel.screenRow) { //col < gamePanel.screenCol && row < gamePanel.screenRow
            int tileNum = mapTileNum[col][row];

            graphics2D.drawImage(tile[tileNum].image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
            col++;
            x+= gamePanel.tileSize;
            if(col == gamePanel.screenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
