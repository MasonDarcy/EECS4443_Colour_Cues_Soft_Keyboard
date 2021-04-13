package mdar.model;

import java.util.HashMap;

public final class SubstringProbabilityMap {

    public static HashMap probs;
    public static boolean isLoaded = false;
    public static void setMap(HashMap probs) {
        SubstringProbabilityMap.probs = probs;
    }
    }
