package assetMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameData {
    // assets
    public static ArrayList<Huruf> huruf = new ArrayList<>();

    // space
    public static final int TILE_SIZE = 32;

    public static int[][] map;

    public static boolean[][] visited;

    // constants
    public static final int WALL = 0;
    public static final int FLOOR = 1;
    public static final int PLAYER = 2;
    public static final int EXIT = 3;

    // moveable
    public static int playerX;
    public static int playerY;

    public static int targetX;
    public static int targetY;

    public static int ROWS;
    public static int COLS;

    // direction
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};

    // Animasi
    public static int indexAnimasiPlayer = 0;

    public static boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < COLS && y < ROWS;
    }

    public static boolean canMove(int x, int y) {
        return inBounds(x, y)
            && map[y][x] != 0
            && !visited[y][x];
    }

    public static void resetVisited() {
        visited = new boolean[ROWS][COLS];
    }

    public static void printMap() {
        for(int y = 0; y < ROWS; y++) {
            for(int x = 0; x < COLS; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static void drawMap(Graphics g) {

        for(int y = 0; y < map.length; y++) {

            for(int x = 0; x < map[y].length; x++) {

                int drawX = x * TILE_SIZE;
                int drawY = y * TILE_SIZE;

                // Always draw the path tile first
                g.drawImage(
                    Assets.floor,
                    drawX,
                    drawY,
                    TILE_SIZE,
                    TILE_SIZE,
                    null
                );

                // If it's a wall, draw the wall on top
                if(map[y][x] == WALL) {

                    int type = getWallType(y, x);

                    // Ensure type doesn't exceed array bounds
                    if (type >= Assets.wallTiles.length) {
                        type = Assets.wallTiles.length - 1;
                    }

                    g.drawImage(
                        Assets.wallTiles[type],
                        drawX,
                        drawY,
                        TILE_SIZE,
                        TILE_SIZE,
                        null
                    );
                }
            }
        }
    }

    public static void drawHuruf(Graphics g){
        int offsetX = 8;
        int offsetY = 8;
        int spacing  = 12;
        for (Huruf i : huruf) {
            if(!i.sudahDiambil){
                String kataKunci = i.kata.toUpperCase();
                int drawX = (i.posisiX * TILE_SIZE) + offsetX;
                int drawY = (i.posisiY * TILE_SIZE) + offsetY;

                if(Assets.gameObject.containsKey(kataKunci)){
                    BufferedImage imgObjek = Assets.gameObject.get(kataKunci);
                    g.drawImage(imgObjek, drawX, drawY, 16,16, null);
                }
                else{
                    for(int j=0; j<kataKunci.length(); j++){
                        char c = kataKunci.charAt(j);
                        if(Assets.gameFont.containsKey(c)){
                            BufferedImage imgHuruf = Assets.gameFont.get(c);
                            g.drawImage(imgHuruf, drawX + (j*spacing), drawY, 16, 16, null);
                        }
                    }
                }
            }
        }
    }

    public static void tempatHuruf(){
        huruf.clear();
        huruf.add(new Huruf("O", 3, 3));
        huruf.add(new Huruf("R", 10, 3));
        huruf.add(new Huruf("A", 4, 5));
        huruf.add(new Huruf("N", 7, 7));
        huruf.add(new Huruf("G", 15, 12));
        huruf.add(new Huruf("T", 22, 13));
        huruf.add(new Huruf("A", 9, 9));
        huruf.add(new Huruf("M", 19, 13));
        huruf.add(new Huruf("Kunci", 3, 15));
    }

    static boolean isWall(int r, int c) {
        if (r < 0 || c < 0 || r >= map.length || c >= map[0].length)
            return false;

        return map[r][c] == WALL;
    }

    static int getWallType(int row, int col) {
        boolean up = isWall(row - 1, col);
        boolean right = isWall(row, col + 1);
        boolean down = isWall(row + 1, col);
        boolean left = isWall(row, col - 1);

        // Priority 1: Horizontal links (middle first, then ends)
        if (left && right) return 1;
        if (right) return 0;
        if (left) return 2;

        // Priority 2: Vertical links
        if (up || down) return 3;

        // Default: Vertical pole for isolated walls
        return 3;
    }

    public static void drawPlayer(Graphics g) {

        g.drawImage(
            Assets.animasiPlayer[indexAnimasiPlayer],
            playerX * TILE_SIZE,
            playerY * TILE_SIZE,
            TILE_SIZE,
            TILE_SIZE,
            null
        );
    }


    public static void loadingMap(int[][] map1) {
        map = map1;
        ROWS = map.length;
        COLS = map[0].length;
        tempatHuruf();
    }
}
