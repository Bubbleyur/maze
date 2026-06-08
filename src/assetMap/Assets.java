package assetMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Assets {

    public static BufferedImage floor;
    public static BufferedImage[] wallTiles = new BufferedImage[4];
    public static BufferedImage[] animasiPlayer = new BufferedImage[2];

    public static HashMap<Character, BufferedImage> gameFont = new HashMap<>();
    public static HashMap<String, BufferedImage> gameObject = new HashMap<>();

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

            gameObject.put("KUNCI", ImageIO.read(new File("references/PNG/Tiles/Tiles/tile_0201.png")));

            gameObject.put("PINTU", ImageIO.read(new File("references/PNG/Tiles/Tiles/tile_0209.png")));

            gameObject.put("KOTAK", ImageIO.read(new File("references/PNG/Tiles/Tiles/tile_0202.png")));

            gameObject.put("AIR_PANAS", ImageIO.read(new File("references/PNG/Tiles/Tiles/tile_0129.png")));

            String huruf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int indexFile = 108;
            for(int i=0; i<huruf.length(); i++){
                char alfabet = huruf.charAt(i);
                String namaFile = String.format("tile_%04d.png", indexFile + i);
                gameFont.put(alfabet, ImageIO.read(new File("references/PNG/Interface/Huruf/" + namaFile)));
            }

            System.out.println("Assets Loaded successfully!");

        }
        catch(Exception e) {
            System.err.println("Error loading assets kontol: " + e.getMessage());
            e.printStackTrace();
        }
    }
}