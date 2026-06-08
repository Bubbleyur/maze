package assetMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameData {
    // assets
    public static ArrayList<Huruf> huruf = new ArrayList<>();
    public static ArrayList<Point> daftarKotak = new ArrayList<>();
    public static ArrayList<Point> daftarAir = new ArrayList<>();
    public static ArrayList<Item> daftarKunci = new ArrayList<>();

    // space
    public static final int TILE_SIZE = 32;

    public static int[][] map;

    public static boolean[][] visited;

    // constants
    public static final int WALL = 0;
    public static final int FLOOR = 1;
    public static final int PLAYER = 2;
    public static final int EXIT = 3;
    public static final int BOX = 4;
    public static final int AIR = 5;

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

    // Item
    public static boolean kunciMuncul = false;
    public static boolean pintuAirPanas = false;


    public static boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < COLS && y < ROWS;
    }

    public static boolean canMove(int x, int y) {
        if(!inBounds(x, y)) return false;

        if(map[y][x] == WALL || map[y][x] == BOX || (map[y][x] == AIR && !pintuAirPanas) || visited[y][x]){
            return false;
        }

        if(x == 3 && y == 15){
            if(!pintuAirPanas){
                return false;
            }
        }
        return true;
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
                g.drawImage(Assets.floor,drawX,drawY,TILE_SIZE,TILE_SIZE,null);
                
                // If it's a wall, draw the wall on top
                if(map[y][x] == WALL) {
                    int type = getWallType(y, x);
                    // Ensure type doesn't exceed array bounds
                    if (type >= Assets.wallTiles.length) {
                        type = Assets.wallTiles.length - 1;
                    }
                    g.drawImage(Assets.wallTiles[type],drawX,drawY,TILE_SIZE,TILE_SIZE,null);
                }
                else if(map[y][x] == BOX){
                    if(Assets.gameObject.containsKey("KOTAK")){
                        g.drawImage(Assets.gameObject.get("KOTAK"), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
                    }
                }
                else if(map[y][x] == EXIT){
                    if(Assets.gameObject.containsKey("PINTU")){
                        g.drawImage(Assets.gameObject.get("PINTU"), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
                    }
                }
                else if(map[y][x] == AIR){
                    if(Assets.gameObject.containsKey("AIR_PANAS")){
                        g.drawImage(Assets.gameObject.get("AIR_PANAS"), drawX, drawY, TILE_SIZE, TILE_SIZE,null);
                    }
                }

                if(x == 3 && y == 15 && !pintuAirPanas){
                    if(Assets.gameObject.containsKey("PINTU")){
                        g.drawImage(Assets.gameObject.get("PINTU"), drawX, drawY, TILE_SIZE, TILE_SIZE,null);
                    }
                }
            }
        }
    }
    
    public static void kotak(){
        daftarKotak.clear();
        daftarKotak.add(new Point(22,1));
        daftarKotak.add(new Point(14,13));

        for (Point p : daftarKotak) {
            if(inBounds(p.x, p.y)){
                map[p.y][p.x] = BOX;
            }
        }
    }

    public static void Air(){
        daftarAir.clear();
        daftarAir.add(new Point(2, 15));

        for (Point p : daftarAir) {
            if(inBounds(p.x, p.y)){
                map[p.y][p.x] = AIR;
            }
        }
    }

    public static void tempatKunci(){
        daftarKunci.clear();
        daftarKunci.add(new Item("KUNCI", 1, 15, true));
        daftarKunci.add(new Item("KUNCI", 5, 5, false));
        daftarKunci.add(new Item("KUNCI", 17, 3, false));
    }

    public static void statusKunci(){
        boolean kDiambil = false;
        boolean uDiambil = false;
        boolean nDiambil = false;
        boolean cDiambil = false;
        boolean iDiambil = false;

        for (Huruf h : huruf) {
            if(h.sudahDiambil){
                if(h.kata.equalsIgnoreCase("k")) kDiambil = true;
                if(h.kata.equalsIgnoreCase("u")) uDiambil = true;
                if(h.kata.equalsIgnoreCase("n")) nDiambil = true;
                if(h.kata.equalsIgnoreCase("c")) cDiambil = true;
                if(h.kata.equalsIgnoreCase("i")) iDiambil = true;
            }
        }
        if(kDiambil && uDiambil && nDiambil && cDiambil && iDiambil){
            kunciMuncul = true;
        }
    }

    public static void drawItems(Graphics g) {
        int offsetX = 4;
        int offsetY = 4;

        for (Item k : daftarKunci) {
            // Kunci hanya digambar jika semua huruf sudah didapatkan DAN kunci belum diambil player
            if (kunciMuncul && !k.sudahDiambil) {
                if (Assets.gameObject.containsKey(k.jenis)) {
                    g.drawImage(Assets.gameObject.get(k.jenis), (k.x * TILE_SIZE) + offsetX, (k.y * TILE_SIZE) + offsetY, 24, 24, null);
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
                    g.drawImage(imgObjek, drawX, drawY,24,24, null);
                }
                else{
                    for(int j=0; j<kataKunci.length(); j++){
                        char c = kataKunci.charAt(j);
                        if(Assets.gameFont.containsKey(c)){
                            BufferedImage imgHuruf = Assets.gameFont.get(c);
                            g.drawImage(imgHuruf, drawX + (j*spacing), drawY, 18, 18, null);
                        }
                    }
                }
            }
        }
    }

    public static void tempatHuruf(){
        huruf.clear();
        huruf.add(new Huruf("K", 3, 3));
        huruf.add(new Huruf("U", 10, 3));
        huruf.add(new Huruf("N", 4, 5));
        huruf.add(new Huruf("C", 7, 7));
        huruf.add(new Huruf("I", 15, 12));
        huruf.add(new Huruf("K", 22, 13));
        huruf.add(new Huruf("O", 9, 9));
        huruf.add(new Huruf("T", 19, 13));
        huruf.add(new Huruf("A", 20, 1));
        huruf.add(new Huruf("K", 3, 13));
        huruf.add(new Huruf("I", 21, 3));
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
        g.drawImage(Assets.animasiPlayer[indexAnimasiPlayer],playerX * TILE_SIZE,playerY * TILE_SIZE,TILE_SIZE,TILE_SIZE,null);
    }

    public static void loadingMap(int[][] map1) {
        map = map1;
        ROWS = map.length;
        COLS = map[0].length;
        kotak();
        Air();
        tempatKunci();
        tempatHuruf();
    }
}
