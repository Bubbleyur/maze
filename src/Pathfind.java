import java.awt.Point;
import java.util.ArrayList;

public class Pathfind {
    static ArrayList<Point> rute = new ArrayList<>();
    public static boolean jalanBenar(int startX, int startY){
        GameData.resetVisited();
        rute.clear();
        return solve(startX, startY);
    }

    public static boolean solve(int x, int y){
        // BASECASE
        if(GameData.map[y][x] == GameData.EXIT){ // EXIT = 3
            System.out.println("Ketemu jalan keluar");
            rute.add(0,new Point(x,y)); // cara cepat dari rute.add(0, int[]{x,y});
            return true;
        }
        // penanda kalo kordinat x,y sudah pernah dilewati
        GameData.visited[y][x] = true;

        // pengecekan atas,bawah,kanan,samping
        for(int i=0; i<4; i++){
            int nextX = x + GameData.dx[i];
            int nxtY = y + GameData.dy[i];
            // pengecekan apakah itu batas frame atau wall
            if(GameData.canMove(nextX, nxtY)){
                if(solve(nextX, nxtY)){ // rekursi
                    rute.add(0 ,new Point(x,y));
                    return true;
                }
            }
        }

        // mereset penanda
        GameData.visited[y][x] = false;

        // mundur satu langkah
        return false;
    }
}
