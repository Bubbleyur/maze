package main;
import java.awt.*;
import javax.swing.*;
import assetMap.*;

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
        GameData.drawItems(g);
        GameData.drawHuruf(g);
        GameData.drawPlayer(g);
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
            Timer time = new Timer(200, e -> { // 0,2 detik
                if (!Pathfind.rute.isEmpty()) {
                    Point nextMove = Pathfind.rute.remove(0);
                    GameData.playerX = nextMove.x;
                    GameData.playerY = nextMove.y;
                    GameData.indexAnimasiPlayer = (GameData.indexAnimasiPlayer + 1) % 2;
                    for (Huruf h : GameData.huruf) {
                        if (GameData.playerX == h.posisiX && GameData.playerY == h.posisiY) {
                            if(!h.sudahDiambil){
                                h.sudahDiambil = true;
                                System.out.println("Mengambil huruf: " + h.kata);
                                GameData.statusKunci();
                                GameData.statusAir();
                            }
                        }
                    }
                    for (Item k : GameData.daftarKunci) {
                        if(GameData.playerX == k.x && GameData.playerY == k.y && GameData.kunciMuncul){
                            if(!k.sudahDiambil){
                                k.sudahDiambil = true;
                                if(k.x == 5 && k.y == 5){
                                    GameData.pintuAirPanas = true;
                                    System.out.println("Pintu Kunci Air Panas!");
                                    System.out.println("Berhasil diambil");
                                    System.out.println("Pintu Terbuka");
                                }
                                else if(k.asli){
                                    GameData.pintuAirPanas = true;
                                    System.out.println("Kunci Asli!");
                                    System.out.println("Berhasil diambil");
                                }
                                else{
                                    System.out.println("Kunci Palsu");
                                    System.out.println("Berhasil diambil");
                                }
                            }
                        }
                    }
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                    gameSelesai = true;
                    GameData.indexAnimasiPlayer = 0;
                    repaint();
                }
            });
            time.start();
        } else {
            System.out.println("Jalan buntu");
        }
    }
}
