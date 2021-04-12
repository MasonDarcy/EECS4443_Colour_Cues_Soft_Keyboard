package mdar.model;

import java.util.HashMap;

public final class GlobalMap {

    public static HashMap probs;
    public static boolean isLoaded = false;
    public static void setMap(HashMap probs) {
        GlobalMap.probs = probs;
    }

    }
