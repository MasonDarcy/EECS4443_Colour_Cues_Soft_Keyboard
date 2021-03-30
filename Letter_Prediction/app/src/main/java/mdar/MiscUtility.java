package mdar;

import android.util.Log;

public final class MiscUtility {

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

    public static String unstableProbToColor(Float prob) {

        for(int i = 0; i < colorPairs.length; i++)  {
            if(prob >= colorPairs[i].probLowerBound && prob <= colorPairs[i].probUpperBound) {
                return colorPairs[i].hexCode;
            }
        }
        return "#FF000000";
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

}
