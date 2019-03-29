import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        ImageProcessor ip = new ImageProcessor("B:\\Pictures\\Saved Pictures\\think.png");
        double[][] darknessArray = ip.getDarknessArray();
        ArtGenerator ag = new ArtGenerator(darknessArray, "B:\\programming\\Ascii-Generator\\out.txt");
        ag.create();
    }
}
