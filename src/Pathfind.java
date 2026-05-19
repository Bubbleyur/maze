import java.awt.Point;
import java.util.ArrayList;

public class Pathfind {
    static ArrayList<Point> rute = new ArrayList<>();
    static ArrayList<Point> ruteSementara = new ArrayList<>();
    public static boolean jalanBenar(int startX, int startY){
        rute.clear();
        int korSekarangX = startX;
        int korSekarangY = startY;
        for (Huruf h : GameData.huruf) {
            GameData.resetVisited();
            ruteSementara.clear();
            solve(korSekarangX, korSekarangY, h.posisiX, h.posisiY);
            rute.addAll(ruteSementara);
            korSekarangX = h.posisiX;
            korSekarangY = h.posisiY;
        }
        GameData.resetVisited();
        ruteSementara.clear();
        solve(korSekarangX, korSekarangY, 22, 14);
        rute.addAll(ruteSementara);
        // return solve(startX, startY);
        return true;
    }

    public static boolean solve(int awalX, int awalY, int targetX, int targetY){

        if(awalX == targetX && awalY == targetY){
            ruteSementara.add(0, new Point(awalX, awalY));
            return true;
        }
        // BASECASE
        // if(GameData.map[awalY][awalX] == GameData.EXIT){ // EXIT = 3
        //     System.out.println("Ketemu jalan keluar");
        //     rute.add(0,new Point(awalX,awalY)); // cara cepat dari rute.add(0, int[]{x,y});
        //     return true;
        // }
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

        // mereset penanda
        GameData.visited[awalY][awalX] = false;

        // mundur satu langkah
        return false;
    }
}
