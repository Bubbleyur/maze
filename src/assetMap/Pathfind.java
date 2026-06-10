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
        GameData.exitLengkap = false;

        // backtracking pencarian huruf
        for (Huruf h : GameData.huruf) {
            if(h.kata.equalsIgnoreCase("Kunci") || h.kata.equalsIgnoreCase("Pintu") || h.kata.equalsIgnoreCase("Kotak")){
                continue;
            }
            GameData.resetVisited(); // Menghapus jejak pencarian sebelumnya
            ruteSementara.clear(); // Mengkosongkan rute sementara
            solve(korSekarangX, korSekarangY, h.posisiX, h.posisiY);
            // hapus elemen 0 buat gak duplikat kordinat di arraynya
            if(!rute.isEmpty() && !ruteSementara.isEmpty()){
                ruteSementara.remove(0);
            }
            rute.addAll(ruteSementara); // simpan rute sementara ke rute utama
            korSekarangX = h.posisiX;
            korSekarangY = h.posisiY;
        }
        GameData.airDingin = true;
        GameData.kotakLengkap = true;
        GameData.exitLengkap = true;

        // Backtracking kunci pintu air panas
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, GameData.POSISI_KUNCI_AIR_PANAS.x, GameData.POSISI_KUNCI_AIR_PANAS.y)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        korSekarangX = GameData.POSISI_KUNCI_AIR_PANAS.x;
        korSekarangY = GameData.POSISI_KUNCI_AIR_PANAS.y;
        GameData.pintuAirPanas = true;

        // Backtracking Kunci
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, GameData.POSISI_KUNCI_ASLI.x, GameData.POSISI_KUNCI_ASLI.y)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        korSekarangX = GameData.POSISI_KUNCI_ASLI.x;
        korSekarangY = GameData.POSISI_KUNCI_ASLI.y;

        // Backtracking Exit
        GameData.resetVisited();
        ruteSementara.clear();
        if(!solve(korSekarangX, korSekarangY, GameData.POSISI_EXIT.x, GameData.POSISI_EXIT.y)) return false;
        // buat gak ada duplikat di arraynya
        if(!rute.isEmpty() && !ruteSementara.isEmpty()){
            ruteSementara.remove(0);
        }
        rute.addAll(ruteSementara);
        //Reset
        GameData.pintuAirPanas = false;
        GameData.airDingin = false;
        GameData.kotakLengkap = false;
        GameData.exitLengkap = false;
        return true;
    }

    // Bactracking
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
