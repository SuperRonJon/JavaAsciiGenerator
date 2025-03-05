import com.superronjon.ascii.AsciiGenerator;
import com.superronjon.ascii.InputParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        InputParser parser = new InputParser(args);

        File inputFile = new File(parser.getImageFilePath());
        String outputPath = inputFile.getAbsolutePath() + ".txt";

        BufferedImage image;
        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Unable to read input file");
            return;
        }

        AsciiGenerator generator = new AsciiGenerator(image, parser.getScalingFactor());
        generator.writeToFile(outputPath, parser.getInvert());
    }
}
