import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter image file name: ");
        String imageFileName = scanner.nextLine();
        System.out.println("Enter desired output file name: ");
        String outFileName = scanner.nextLine();
        System.out.println("Enter scaling percentage: ");
        double scaling = scanner.nextDouble();
        System.out.println("Invert colors? ('yes' for invert)");
        String input = scanner.next();
        boolean invert = input.toLowerCase().equals("yes");

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
