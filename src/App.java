
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JPanel{

    public App() {

        Assets.load();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.drawMap(g);
    }

    static int[][] map = {
        {1,1,1,1,1},
        {1,0,0,0,1},
        {1,1,1,0,1},
        {0,0,1,1,1},
        {1,1,1,0,1}
    };

    public static void main(String[] args) {
    // 1. Peta yang lebih luas (misal 15x15)
    int[][] largeMap = {
        {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,0,0,0,1,0,0,0,0,1},
        {1,0,1,1,1,0,1,1,1,1,0,1,1,1,1},
        {1,0,0,0,1,0,1,0,0,0,0,1,0,0,0},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,1,3}, // 3 adalah EXIT
        // ... tambahkan baris lagi sesukanya
    };

    GameData game = new GameData(largeMap);
    GameData.targetX = 14; 
    GameData.targetY = 4;

    JFrame frame = new JFrame("Visualisasi Backtracking");
    App panel = new App();
    frame.add(panel);
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    // Jalankan Pathfinding di Thread terpisah agar visual terlihat step-by-step
    new Thread(() -> {
        Pathfind.findPath(0, 0);
    }).start();

    // Loop sederhana untuk me-refresh layar (Repaint)
    new Thread(() -> {
        while(true) {
            panel.repaint();
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }).start();
}
}
