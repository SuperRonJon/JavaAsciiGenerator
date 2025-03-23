import com.superronjon.ascii.AsciiGenerator;
import com.superronjon.inputparse.GenericInputParser;
import com.superronjon.inputparse.Option;

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
		parser.addOption(new Option('w', true, "-1.0"));
		parser.addOption(new Option('h', true, "-1.0"));
		parser.parseInput(args);

		boolean invert = Boolean.parseBoolean(parser.getOptionValue('i'));
		boolean removeBorder = Boolean.parseBoolean(parser.getOptionValue('b'));
		boolean toFile = Boolean.parseBoolean(parser.getOptionValue('f'));
		double scalingFactor = Double.parseDouble(parser.getOptionValue('s'));
		double heightScaling = Double.parseDouble(parser.getOptionValue('h'));
		double widthScaling = Double.parseDouble(parser.getOptionValue('w'));

		boolean widthGiven = heightScaling > 0;
		boolean heightGiven = widthScaling > 0;

		File inputFile = new File(parser.getUnflaggedArgument(0));

        BufferedImage image;
        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Unable to read input file");
            return;
        }

		AsciiGenerator generator = null;
		if(!widthGiven && !heightGiven) {
			generator = new AsciiGenerator(image, scalingFactor);
		}
		else if(widthScaling > 0 && heightScaling > 0) {
			generator = new AsciiGenerator(image, widthScaling, heightScaling);
		}
		else {
			System.out.println("Invalid scaling parameters. \nIf not using equivalent scaling for the height and width (s)," +
				"\nboth height (h) and width (w) parameters must be supplied and be greater than 0.");
			return;
		}

        if(toFile) {
            String outputPath = inputFile.getAbsolutePath() + ".txt";
            generator.writeToFile(outputPath, invert, removeBorder);
        }
        else {
            System.out.print(generator.toString(invert, removeBorder));
        }
    }


}
