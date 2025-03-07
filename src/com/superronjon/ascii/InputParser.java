package com.superronjon.ascii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    private final List<String> tokens;
    private String imageFilePath;
    private double scalingFactor;
    private boolean invert;
    private boolean toFile;
    private boolean removeBorder;

    public InputParser(String[] args) {
        tokens = new ArrayList<>();
        tokens.addAll(Arrays.asList(args));

        scalingFactor = 1.0;
        invert = false;
        toFile = false;
        removeBorder = false;
        settingsFromTokens();
    }

    public double getScalingFactor() { return scalingFactor; }

    public boolean getInvert() { return invert; }

    public String getImageFilePath() { return imageFilePath; }

    public boolean getToFile() { return toFile; }

    public boolean getRemoveBorder() { return removeBorder; }

    private void settingsFromTokens() {
        if(tokens.isEmpty()) {
            imageFilePath = "";
            return;
        }
        if(tokens.size() == 1 && !tokens.get(0).startsWith("-")) {
            imageFilePath = tokens.get(0);
            return;
        }
        if(tokens.size() == 1 && tokens.get(0).startsWith("-")) {
            imageFilePath = "";
            return;
        }

        int scalingFactorTokenIndex = -1;
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).startsWith("-")) {
                String options = tokens.get(i).substring(1);
                for(int j = 0; j < options.length(); j++) {
                    switch(options.charAt(j)) {
                        case 'i':
                            invert = true;
                            break;
                        case 's':
                            scalingFactorTokenIndex = i+1;
                            break;
                        case 'f':
                            toFile = true;
                            break;
                        case 'b':
                            removeBorder = true;
                            break;
                    }
                }
            }
            else {
                if (i == scalingFactorTokenIndex && i != tokens.size() - 1) {
                    scalingFactor = Double.parseDouble(tokens.get(i));
                }
            }
        }
        imageFilePath = tokens.get(tokens.size() - 1);
    }
}
