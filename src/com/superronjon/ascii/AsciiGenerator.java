package com.superronjon.ascii;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class AsciiGenerator {
    private final String[] asciiCharacters = { "@", "%", "#", "*", "+", "=", "-", ":", ".", " " };
    private final String[] asciiCharactersInverse = asciiCharacters.clone();

    public AsciiGenerator() {
        Collections.reverse(Arrays.asList(asciiCharactersInverse));
    }

    public void writeImageToFile(BufferedImage img, String outFileName, double scaling, boolean invert, boolean removeBorder) {
        writeImageToFile(img, outFileName, scaling, scaling, invert, removeBorder);
    }

    public void writeImageToFile(BufferedImage img, String outFileName, double scalingWidth, double scalingHeight, boolean invert, boolean removeBorder) {

		File outFile = new File(outFileName);
		if(outFile.getParentFile() != null) {
			outFile.getParentFile().mkdirs();
		}
        PrintWriter writer;
        try {
			writer = new PrintWriter(outFileName, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.write(imageToString(img, scalingWidth, scalingHeight, invert, removeBorder));
        writer.close();
    }

    public String imageToString(BufferedImage img, double scaling, boolean invert, boolean removeBorder) {
        return imageToString(img, scaling, scaling, invert, removeBorder);
    }

    public String imageToString(BufferedImage img, double scalingWidth, double scalingHeight, boolean invert, boolean removeBorder) {
        BufferedImage scaledImage = scaleImage(img, scalingWidth, scalingHeight);
        double[][] brightnessValues = getBrightnessValues(scaledImage);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < brightnessValues.length; i++) {
            for(int j = 0; j < brightnessValues[i].length; j++) {
                int charIndex = (int) Math.floor(brightnessValues[i][j] / (255.1 / asciiCharacters.length));

                String brightnessChar = invert ? asciiCharactersInverse[charIndex] : asciiCharacters[charIndex];
                if(removeBorder && (i == brightnessValues.length - 1 || j == brightnessValues[i].length - 1)) {
                    if(!brightnessChar.equals("@")) {
                        builder.append(brightnessChar);
                    }
                }
                else {
                    builder.append(brightnessChar);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private double[][] getBrightnessValues(BufferedImage image) {
        double[][] brightnessValues = new double[image.getHeight()][image.getWidth()];
        for(int i = 0; i < image.getHeight() - 1; i++) {
            for(int j = 0; j < image.getWidth() - 1; j++) {
                brightnessValues[i][j] = getBrightnessValue(new Color(image.getRGB(j, i)));
            }
        }
        return brightnessValues;
    }

    private double getBrightnessValue(Color c) {
        final double pr = .299;
        final double pg = .587;
        final double pb = .114;

        return Math.sqrt((pr * Math.pow(c.getRed(), 2)) + (pg * Math.pow(c.getGreen(), 2)) + (pb * Math.pow(c.getBlue(), 2)));
    }

    private BufferedImage scaleImage(BufferedImage image, double fWidth, double fHeight) {
        int dWidth = Double.valueOf(image.getWidth() * fWidth).intValue();
        int dHeight = Double.valueOf(image.getHeight() * fHeight).intValue();

        BufferedImage scaledImage = new BufferedImage(dWidth, dHeight, image.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, dWidth, dHeight, null);
        graphics2D.dispose();

        return scaledImage;
    }
}
