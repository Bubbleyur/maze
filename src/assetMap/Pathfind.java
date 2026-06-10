package assetMap;
import java.awt.Point;
import java.util.ArrayList;

public class Pathfind {
    public static ArrayList<Point> rute = new ArrayList<>();
    static ArrayList<Point> ruteSementara = new ArrayList<>();

    public static boolean jalanBenar(int startX, int startY){
        rute.clear();
        int korSekarangX = startX;
        int korSekarangY = startY;
        // Reset di awal
        GameData.pintuAirPanas = false;
        GameData.airDingin = false;
        GameData.kotakLengkap = false;

        // backtracking pencarian huruf
        for (Huruf h : GameData.huruf) {
            if(h.kata.equalsIgnoreCase("Kunci") || h.kata.equalsIgnoreCase("Pintu") || h.kata.equalsIgnoreCase("Kotak")){
                continue;
            }
            GameData.resetVisited();
            ruteSementara.clear();
            solve(korSekarangX, korSekarangY, h.posisiX, h.posisiY);
            // buat gak ada duplikat di arraynya
            if(!rute.isEmpty() && !ruteSementara.isEmpty()){
                ruteSementara.remove(0);
            }
            rute.addAll(ruteSementara);
            korSekarangX = h.posisiX;
            korSekarangY = h.posisiY;
        }
        GameData.airDingin = true;
        GameData.kotakLengkap = true;

        // Backtracking kunci pintu air panas
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, 3, 9)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        korSekarangX = 3;
        korSekarangY = 9;
        GameData.pintuAirPanas = true;

        // Backtracking Kunci
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, 1, 15)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        korSekarangX = 1;
        korSekarangY = 15;

        // Backtracking Exit
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, 22, 16)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        //Reset
        GameData.pintuAirPanas = false;
        GameData.airDingin = false;
        GameData.kotakLengkap = false;
        return true;
    }

    public static boolean solve(int awalX, int awalY, int targetX, int targetY){
        // Basecase
        if(awalX == targetX && awalY == targetY){
            ruteSementara.add(0, new Point(awalX, awalY)); // cara cepat dari rute.add(0, int[]{x,y});
            return true;
        }
        // penanda kalo kordinat x,y sudah pernah dilewati
        GameData.visited[awalY][awalX] = true;

        // pengecekan atas,bawah,kanan,samping
        for(int i=0; i<4; i++){
            int nextX = awalX + GameData.dx[i];
            int nextY = awalY + GameData.dy[i];
            // pengecekan apakah itu batas frame atau wall
            if(GameData.canMove(nextX, nextY)){
                if(solve(nextX, nextY, targetX, targetY)){ // rekursi
                    ruteSementara.add(0 ,new Point(awalX,awalY));
                    return true;
                }
            }
        }
        // mundur satu langkah
        return false;
    }
}
