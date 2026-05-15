
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class App extends JPanel{
    boolean gameSelesai = false;
    public App() {

        Assets.load();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.drawMap(g);
        GameData.drawPlayer(g);
        // tampilan game selesai
        if(gameSelesai){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Congrats", 250, 250);
        }

    }

    static int[][] map = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,0},
        {0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1,0},
        {0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0},
        {0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0},
        {0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0},
        {0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1,0},
        {0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0},
        {0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0},
        {0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,1,0,1,0},
        {0,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,0},
        {0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,0},
        {0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,3,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    public static void main(String[] args) throws Exception {
        GameData game = new GameData(map);

        JFrame frame = new JFrame();

        App panel = new App();

        // components

        frame.add(panel);

        frame.setSize(780, 550);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        GameData.playerX = 1;
        GameData.playerY = 1;

        panel.mulaiPencarian();

    }

    public void mulaiPencarian(){
        boolean ketemu = Pathfind.jalanBenar(GameData.playerX, GameData.playerY);

        if(ketemu){
            Timer time = new Timer(500, e ->{ //0,5 detik
                if(!Pathfind.rute.isEmpty()){
                    Point nextMove = Pathfind.rute.remove(0); // cara kerjanya simpan nilai dari indeks 0 ke nextMove, hapus indeks 0 biar else nya jalan
                    GameData.playerX = nextMove.x; // nyimpan indeks 0 (X)
                    GameData.playerY = nextMove.y; // nyimpan indeks 0 (Y)
                    repaint();
                }
                else{
                    ((Timer) e.getSource()).stop();
                    gameSelesai = true;
                    repaint();
                }
            });
            time.start();
        }
        else{
            System.out.println("Jalan buntu");
        }
    }
}
