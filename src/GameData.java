
import java.awt.*;

public class GameData {
    // assets


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

                if(map[y][x] == 1) {

                    g.drawImage(
                        Assets.floorSprite,
                        drawX,
                        drawY,
                        TILE_SIZE,
                        TILE_SIZE,
                        null
                    );
                }
                else {

                    g.drawImage(
                        Assets.wallSprite,
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

    public static void drawPlayer(Graphics g) {

        g.drawImage(
            Assets.playerSprite,
            playerX * TILE_SIZE,
            playerY * TILE_SIZE,
            TILE_SIZE,
            TILE_SIZE,
            null
        );
    }

    public static boolean movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (!canMove(newX, newY)) {
            return false;
        }

        // hapus player dari posisi lama
        map[playerY][playerX] = FLOOR;

        // update posisi
        playerX = newX;
        playerY = newY;

        // simpan player ke map baru
        map[playerY][playerX] = PLAYER;

        return true;
    }

    public GameData(int[][] map) {
    this.map = map;

    ROWS = map.length;
    COLS = map[0].length;
    resetVisited();


    // cari posisi player
    for (int y = 0; y < ROWS; y++) {
        for (int x = 0; x < COLS; x++) {
            if (map[y][x] == PLAYER) {
                playerX = x;
                playerY = y;
            }

            if (map[y][x] == EXIT) {
                targetX = x;
                targetY = y;
            }
        }
    }
}
    
}
