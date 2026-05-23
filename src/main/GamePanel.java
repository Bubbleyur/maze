package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.Timer;

import assetMap.Assets;
import assetMap.GameData;
import assetMap.Huruf;
import assetMap.Pathfind;

public class GamePanel extends JPanel {
    boolean gameSelesai = false;

    public GamePanel() {
        Assets.load();
        this.setPreferredSize(new Dimension(768, 544));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.drawMap(g);
        GameData.drawPlayer(g);
        GameData.drawHuruf(g);
        // Tampilan game selesai
        if (gameSelesai) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Congrats", 250, 250);
        }
    }

    public void mulaiPencarian() {
        boolean ketemu = Pathfind.jalanBenar(GameData.playerX, GameData.playerY);

        if (ketemu) {
            Timer time = new Timer(200, e -> { // 0,5 detik
                if (!Pathfind.rute.isEmpty()) {
                    Point nextMove = Pathfind.rute.remove(0);
                    GameData.playerX = nextMove.x;
                    GameData.playerY = nextMove.y;
                    for (Huruf h : GameData.huruf) {
                        if (GameData.playerX == h.posisiX && GameData.playerY == h.posisiY) {
                            h.sudahDiambil = true;
                        }
                    }
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                    gameSelesai = true;
                    repaint();
                }
            });
            time.start();
        } else {
            System.out.println("Jalan buntu");
        }
    }
}
