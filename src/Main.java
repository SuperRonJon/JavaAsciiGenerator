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

        BufferedImage image;
        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Unable to read input file");
            return;
        }

        AsciiGenerator generator = new AsciiGenerator(image, parser.getScalingFactor());
        if(parser.getToFile()) {
            String outputPath = inputFile.getAbsolutePath() + ".txt";
            generator.writeToFile(outputPath, parser.getInvert(), parser.getRemoveBorder());
        }
        else {
            System.out.print(generator.toString(parser.getInvert(), parser.getRemoveBorder()));
        }
    }
}
