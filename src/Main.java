import com.superronjon.ascii.AsciiGenerator;
import com.superronjon.ascii.GenericInputParser;

import com.superronjon.ascii.Option;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
		GenericInputParser parser = new GenericInputParser();
		parser.addOption(new Option('i'));
		parser.addOption(new Option('b'));
		parser.addOption(new Option('f'));
		parser.addOption(new Option('s', true, "1.0"));
		parser.parseInput(args);

		boolean invert = Boolean.parseBoolean(parser.getOptionValue('i'));
		boolean removeBorder = Boolean.parseBoolean(parser.getOptionValue('b'));
		boolean toFile = Boolean.parseBoolean(parser.getOptionValue('f'));
		double scalingFactor = Double.parseDouble(parser.getOptionValue('s'));

		File inputFile = new File(parser.getOtherArguments().get(0));

        BufferedImage image;
        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Unable to read input file");
            return;
        }

        AsciiGenerator generator = new AsciiGenerator(image, scalingFactor);
        if(toFile) {
            String outputPath = inputFile.getAbsolutePath() + ".txt";
            generator.writeToFile(outputPath, invert, removeBorder);
        }
        else {
            System.out.print(generator.toString(invert, removeBorder));
        }
    }
}
