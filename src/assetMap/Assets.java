package assetMap;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Assets {

    public static BufferedImage floor;
    public static BufferedImage[] wallTiles = new BufferedImage[4];
    public static BufferedImage[] animasiPlayer = new BufferedImage[2];

    public static void load() {
        System.out.println("Loading assets from: " + new File("assets").getAbsolutePath());
        try {
            for(int i = 0; i < 4; i++) {
                wallTiles[i] = ImageIO.read(
                    new File("assets/object/wall/" + i + ".png")
                );
            }

            floor = ImageIO.read(new File("assets/pathtile/flooroutside.png"));

            animasiPlayer[0] = ImageIO.read(new File("assets/entity/tile_0008.png"));
            animasiPlayer[1] = ImageIO.read(new File("assets/entity/tile_0009.png"));

            System.out.println("Assets Loaded successfully!");

        }
        catch(Exception e) {
            System.err.println("Error loading assets kontol: " + e.getMessage());
            e.printStackTrace();
        }
    }
}