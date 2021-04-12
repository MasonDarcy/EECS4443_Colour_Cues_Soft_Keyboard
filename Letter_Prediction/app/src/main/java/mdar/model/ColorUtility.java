package mdar.model;

import android.util.Log;

public final class ColorUtility {

    private final static ColorPair[] colorPairs = {
            new ColorPair("#FFff0000", 0.0F, 0.00001F),
            new ColorPair("#FFff4d4d", 0.00001F, 0.0001F),
            new ColorPair("#FFff9e9e", 0.0001F, 0.001F),
            new ColorPair("#FFffcfcf", 0.001F, 0.01F),
            new ColorPair("#FFfffcfc", 0.01F, 0.038F),
            new ColorPair("#FFd4ffd1", 0.038F, 0.1F),
            new ColorPair("#FF96ff8f", 0.1F, 0.15F),
            new ColorPair("#FF50ff45", 0.15F, 0.3F),
            new ColorPair("#FF0fff00", 0.3F, 1F)
    };

    private final static ColorPair[] greenOnlyColorPairs = {
            new ColorPair("#FFd8e6db", 0.0F, 0.00001F),
            new ColorPair("#FFd8e6db", 0.00001F, 0.0001F),
            new ColorPair("#FFd8e6db", 0.0001F, 0.001F),
            new ColorPair("#FFd8e6db", 0.001F, 0.01F),
            new ColorPair("#FFfffcfc", 0.01F, 0.038F),
            new ColorPair("#FFd4ffd1", 0.038F, 0.1F),
            new ColorPair("#FF96ff8f", 0.1F, 0.15F),
            new ColorPair("#FF50ff45", 0.15F, 0.3F),
            new ColorPair("#FF0fff00", 0.3F, 1F)
    };

    public static String greenRedProbToColor(Float prob) {

        for(int i = 0; i < colorPairs.length; i++)  {
            if(prob >= colorPairs[i].probLowerBound && prob <= colorPairs[i].probUpperBound) {
                return colorPairs[i].hexCode;
            }
        }
        return "#FF000000";
    }

    public static String greenProbToColor(Float prob) {

        for(int i = 0; i < greenOnlyColorPairs.length; i++)  {
            if(prob >= greenOnlyColorPairs[i].probLowerBound && prob <= greenOnlyColorPairs[i].probUpperBound) {
                return greenOnlyColorPairs[i].hexCode;
            }
        }
        return "#FF000000";
    }

    public static String probToColor(Float prob, String type) {
        if(type.equals("Vanilla")) {
            return "#FFd8e6db";
        } else if (type.equals("Green Red")) {
            return greenRedProbToColor(prob);
        } else {
            return greenProbToColor(prob);
        }
    }


    //This can be improved or simplified
    public static String probabilityToColor(float prob) {
        String output = "#FF";
        Log.i("DEBUG", prob + " is the probability");
        float whiteLine = 0.03846F;
        int red = prob <= whiteLine ? 100 : (int) (255 * (1  / (1.5 + prob)));
        int green = prob >= whiteLine ? 255 : (int) (255 * prob);
        int blue = (int) (prob <= whiteLine ? 255 * (prob / whiteLine) : 255 * (whiteLine / 1 + prob));
        if(blue > 0) {
            blue *= 0.35;
        }
        Log.i("DEBUG", "blue: " + blue);
        if(red < 16) {
            output += "0" + Integer.toHexString(red);
        } else {
            output += Integer.toHexString(red);
        }

        if(green < 16) {
            output += "0" + Integer.toHexString(green);
        } else {
            output += Integer.toHexString(green);
        }
        if(blue < 16) {
            output += "0" + Integer.toHexString(blue);
        } else {
            output += Integer.toHexString(blue);
        }

        if(!Float.isNaN(prob)) {
            Log.i("DEBUG", "Color: " + output);

            return output;
        } else{
            return  "#FFFF0000";
        }
    }




    public static char mapNumberToLetter(int i) {
        return (char) (65 + i);
    }

    //Given a sentence, find and return the word being worked on
    public static String findCurrentWord(String sentence) {
        String[] parts = sentence.split(" ");
        String lastWord = parts[parts.length - 1];
        return lastWord;
    }

    public static class ColorPair {

        public ColorPair(String hex, Float probL, Float probH) {
            this.hexCode = hex;
            this.probLowerBound = probL;
            this.probUpperBound = probH;
        }

        String hexCode;
        Float probLowerBound;
        Float probUpperBound;

    }
}
