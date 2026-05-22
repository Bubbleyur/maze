import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(){
        // Setup properti jendela utama (Frame)
            this.setTitle("Game Pathfinding"); // Menambah judul pada window
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(780, 550);
            this.setLocationRelativeTo(null); // Membuat window muncul persis di tengah layar
            // Memasukkan GamePanel ke dalam Frame
            GamePanel panel = new GamePanel();
            this.add(panel);
            this.setVisible(true);
            // Set posisi awal player
            GameData.playerX = 1;
            GameData.playerY = 1;
            // Mulai jalankan pencarian di panel
            panel.mulaiPencarian();
    }
}
