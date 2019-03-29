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
        double[][] pixels = new double[img.getWidth()][img.getHeight()];
        for(int i = 0; i < img.getWidth() - 1; i++){
            for(int j = 0; j < img.getHeight() - 1; j++){
                int pixel_rgb = img.getRGB(i, j);
                ColorBright myColor = new ColorBright(pixel_rgb);
                pixels[i][j] = myColor.getBrightness();
            }
        }

        return pixels;
    }
}
