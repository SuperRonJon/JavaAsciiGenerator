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

    double[][] getDarknessArray(){
        double[][] pixels = new double[img.getHeight()][img.getWidth()];
        for(int i = 0; i < img.getHeight() - 1; i++){
            for(int j = 0; j < img.getWidth() - 1; j++){
                int pixel_rgb = img.getRGB(j, i);
                ColorBright myColor = new ColorBright(pixel_rgb);
                pixels[i][j] = myColor.getBrightness();
            }
        }
        return pixels;
    }
}
