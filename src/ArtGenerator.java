import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

class ArtGenerator {
    private double[][] pixelDarkness;
    private PrintWriter writer;
    private String[] asciiCharactersInverse = {" ", ".", ":", "-", "=", "+", "*", "#", "%", "@"};
    private String[] asciiCharacters = {"@", "%", "#", "*", "+", "=", "-", ":", ".", " "};

    ArtGenerator(double[][] darknessArray, String outputFileName){
        pixelDarkness = darknessArray;
        try{
            writer = new PrintWriter(outputFileName, "UTF-8");
        }catch(FileNotFoundException|UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    void create(boolean invert){
        for(int i = 0; i < pixelDarkness.length; i++){
            for(int j = 0; j < pixelDarkness[i].length; j++){
                int charIndex = (int)Math.floor(pixelDarkness[i][j] / (255.1 / asciiCharacters.length));

                String darknessChar = "";
                if(invert){
                    darknessChar = asciiCharactersInverse[charIndex];
                }else {
                    darknessChar = asciiCharacters[charIndex];
                }

                writer.write(darknessChar);
            }
            writer.write("\r\n");
        }
        writer.close();
    }
}
