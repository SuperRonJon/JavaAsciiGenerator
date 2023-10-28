import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        String configFilePath = args[0];

        String outDirectory = null;
        String scalingString = null;
        String invertColorsString = null;

        try {
            FileReader reader = new FileReader(new File(configFilePath));
            Properties p = new Properties();
            p.load(reader);
            outDirectory = p.getProperty("OUTPUT_DIRECTORY", "out/");
            scalingString = p.getProperty("SCALING", "1.0");
            invertColorsString = p.getProperty("INVERT_COLORS", "False");

        }catch (IOException e){
            System.out.println("Unable to read config file, adding default values");
            outDirectory = "out/";
            scalingString = "1.0";
            invertColorsString = "False";
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter image file name: ");
        String imageFileName = scanner.nextLine();

        double scaling = Double.parseDouble(scalingString);
        boolean invert = Boolean.parseBoolean(invertColorsString);
        String outFileName = new File(imageFileName).getName().replaceFirst("\\..*", ".txt");
        outFileName = outDirectory + outFileName;
        scanner.close();

        createArtFromFile(imageFileName, outFileName, scaling, invert);
    }

    private static void createArtFromFile(String filename, String outFile, double scaling, boolean invert){
        ImageProcessor processor = new ImageProcessor(filename);
        processor.scaleImage(scaling, scaling);
        double[][] darknessArray = processor.getDarknessArray();
        ArtGenerator generator = new ArtGenerator(darknessArray, outFile);
        generator.create(invert);
    }
}
