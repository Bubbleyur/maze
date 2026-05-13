
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
        {0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
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
    }
}
