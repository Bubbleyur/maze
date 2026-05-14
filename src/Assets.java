import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Assets {
    BufferedImage playerSprite;
    BufferedImage wallSprite;
    BufferedImage floorSprite;
    public static Image floor;
    public static Image wall;
    public static Image player;

    public static void load() {
        try {
            // Ganti "floor.png" dsb dengan nama file gambar Anda di folder proyek
            floor = ImageIO.read(new File("assets/pathtile/tile_0024.png"));
            wall = ImageIO.read(new File("assets/pathtile/tile_0018.png"));
            player = ImageIO.read(new File("assets/pathtile/tile_0000.png"));
        } catch (IOException e) {
            System.out.println("Gagal memuat gambar! Pastikan file ada di folder res/");
            e.printStackTrace();
        }
    }
}
