package mygame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private BufferedImage spriteSheet;

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void loadSpriteSheet() {
        File spriteFile = new File("./sprites/punyworld-overworld-tileset.png");
        System.out.println("Absolute path: " + spriteFile.getAbsolutePath());
        if (spriteFile.exists()) {
            try {
                spriteSheet = ImageIO.read(spriteFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist: " + spriteFile.getAbsolutePath());
        }
    }
}
