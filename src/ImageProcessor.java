import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageProcessor {
    private BufferedImage img = null;
    ImageProcessor(String filepath){
        try {
            img = ImageIO.read(new File(filepath));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
