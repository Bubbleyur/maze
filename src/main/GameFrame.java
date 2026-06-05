package main;
import javax.swing.JFrame;

import assetMap.GameData;

public class GameFrame extends JFrame {
    public GameFrame(){
        // Setup properti jendela utama (Frame)
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.pack();
        this.setTitle("Game Pathfinding"); // Menambah judul pada window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Membuat window muncul persis di tengah layar
        // Memasukkan GamePanel ke dalam Frame
        this.setVisible(true);
        // Set posisi awal player
        GameData.playerX = 1;
        GameData.playerY = 1;
        // Mulai jalankan pencarian di panel
        panel.mulaiPencarian();
    }
}
