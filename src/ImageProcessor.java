import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageProcessor {
    private BufferedImage img = null;

    ImageProcessor(String filepath) {
        try {
            img = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    double[][] getDarknessArray() {
        double[][] pixels = new double[img.getHeight()][img.getWidth()];
        for (int i = 0; i < img.getHeight() - 1; i++) {
            for (int j = 0; j < img.getWidth() - 1; j++) {
                int pixel_rgb = img.getRGB(j, i);
                ColorBright myColor = new ColorBright(pixel_rgb);
                pixels[i][j] = myColor.getBrightness();
            }
        }
        return pixels;
    }

    void scaleImage(double fWidth, double fHeight) {
        int dWidth = Double.valueOf(img.getWidth() * fWidth).intValue();
        int dHeight = Double.valueOf(img.getHeight() * fHeight).intValue();
        BufferedImage scaledImage = null;
        if (img != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, img.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(img, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        img = scaledImage;
    }
}
