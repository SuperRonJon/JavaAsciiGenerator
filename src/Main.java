import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        ImageProcessor ip = new ImageProcessor("C:\\Users\\a3pseute\\Pictures\\quart.png");
        double[][] darknessArray = ip.getDarknessArray();
        System.out.println("test");
    }
}
