import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args)throws IOException {
        BufferedImage img = null;
        File f = null;

        try{
            f = new File("C:\\Users\\a3pseute\\Pictures\\quart.png");
            img = ImageIO.read(f);

            int width = img.getWidth();
            int height = img.getHeight();

            int p_rgb = img.getRGB(64, 64);
            ColorBright myColor = new ColorBright(p_rgb);
            System.out.println(myColor.getBrightness());
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
