import java.util.ArrayList;
import java.util.Collections;

public class Pathfind {

    public static boolean findPath(int x, int y) {
        // 1. Cek target
        if (x == GameData.targetX && y == GameData.targetY) {
            System.out.println("Jalan ditemukan!");
            return true;
        }

        if (GameData.canMove(x, y)) {
            GameData.visited[y][x] = true;

            // Simpan nilai asli dan tandai sedang dieksplorasi (untuk visualisasi)
            int originalValue = GameData.map[y][x];
            GameData.map[y][x] = 4; // Angka 4 untuk warna "sedang dicari"

            // Jeda agar terlihat step-by-step
            try { Thread.sleep(50); } catch (Exception e) {}

            // --- BAGIAN RANDOM ---
            ArrayList<Integer> directions = new ArrayList<>();
            for (int i = 0; i < 4; i++) directions.add(i);
            Collections.shuffle(directions); // Mengacak urutan arah (Atas, Bawah, Kiri, Kanan)
            // ---------------------

            // Coba 4 arah dengan urutan yang sudah diacak
            for (int i : directions) {
                int nextX = x + GameData.dx[i];
                int nextY = y + GameData.dy[i];

                if (findPath(nextX, nextY)) {
                    return true;
                }
            }

            // BACKTRACK: Jika buntu, kembalikan warna map ke semula
            GameData.map[y][x] = originalValue;
            try { Thread.sleep(30); } catch (Exception e) {}
        }
        return false;
    }
}