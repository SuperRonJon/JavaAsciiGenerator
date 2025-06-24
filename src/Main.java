import com.superronjon.ascii.AsciiGenerator;
import com.superronjon.inputparse.GenericInputParser;
import com.superronjon.inputparse.UnrecognizedOptionException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        final String CURRENT_VERSION = "v2.9.2";

        GenericInputParser parser = createInputParser();

        try {
            parser.parseInput(args);
        }
        catch (UnrecognizedOptionException e) {
            System.out.println(e.getMessage());
            return;
        }

        if(parser.getOptionValue("version").equals("True")) {
            System.out.println("ascii-generator " + CURRENT_VERSION);
            return;
        }

        if(parser.getOptionValue("help").equals("True")) {
            parser.printHelp();
            return;
        }

        boolean invert;
        boolean removeBorder;
        double scalingFactor;
        double heightScaling;
        double widthScaling;

        try{
            invert = Boolean.parseBoolean(parser.getOptionValue('i'));
            removeBorder = Boolean.parseBoolean(parser.getOptionValue('b'));
            scalingFactor = Double.parseDouble(parser.getOptionValue('s'));
            heightScaling = Double.parseDouble(parser.getOptionValue('h'));
            widthScaling = Double.parseDouble(parser.getOptionValue('w'));
        } catch (Exception e) {
            System.out.println("Error parsing input arguments...");
            System.out.println(e.getMessage());
            System.out.println("Ensure argument is expected type.");
            return;
        }


        String outputFileName = parser.getOptionValue("to-file");
        boolean toFile = !outputFileName.trim().isEmpty();

        boolean widthGiven = heightScaling > 0;
        boolean heightGiven = widthScaling > 0;

        String inputFileName = parser.getUnflaggedArgument(0);
        if(inputFileName == null) {
            System.out.println("No input file given");
            return;
        }
        File inputFile = new File(inputFileName);

        if(!inputFile.exists()) {
            System.out.println("Input file \"" + inputFileName + "\" not found... Ensure the path is correct and the file exists.");
            return;
        }

        BufferedImage image;
        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            image = null;
        }

        if(image == null) {
            System.out.println("Error parsing input file to an image... Ensure the file is a valid image file format.");
            return;
        }

        AsciiGenerator generator = new AsciiGenerator();
        boolean evenScaling = (!widthGiven && !heightGiven);
        if(!evenScaling && (widthScaling <= 0 || heightScaling <= 0)) {
            System.out.println("Invalid scaling parameters. \nIf not using equivalent scaling for the height and width (s)," +
                    "\nboth height (h) and width (w) parameters must be supplied and be greater than 0.");
            return;
        }

        if(toFile) {
            File outputFile = new File(outputFileName);
            System.out.println("Writing to " + outputFile.getAbsolutePath());
            if(evenScaling) {
                generator.writeImageToFile(image, outputFileName, scalingFactor, invert, removeBorder);
            }
            else {
                generator.writeImageToFile(image, outputFileName, widthScaling, heightScaling, invert, removeBorder);
            }
        }
        else {
            if(evenScaling) {
                System.out.print(generator.imageToString(image, scalingFactor, invert, removeBorder));
            }
            else {
                System.out.print(generator.imageToString(image, widthScaling, heightScaling, invert, removeBorder));
            }
        }
    }

    private static GenericInputParser createInputParser()
    {
        GenericInputParser parser = new GenericInputParser("Ascii Art Generator", "ascii-generator [OPTIONS...] image/file/path.jpg");
        parser.addOption('i', "invert", "Invert color so that the brightest pixels use the denses characters");
        parser.addOption('b', "remove-border", "Removes border that sometimes appears on non-inverted images");
        parser.addOption('f', "to-file", true, "", "Output to file, takes output filepath as VAL");
        parser.addOption('s', "scaling", true, "1.0", "Used to scale the images height and width evenly by VAL");
        parser.addOption('w', "width", true, "-1.0", "Used to scale the height separately from width. Both must be given");
        parser.addOption('h', "height", true, "-1.0", "Used to scale the height separately from width. Both must be given");
        parser.addOption('v', "version", "Print version number");
        parser.addOption('H', "help", "Print help menu");
        return parser;
    }


}
