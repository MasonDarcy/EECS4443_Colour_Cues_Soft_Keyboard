package mdar;

public class ColorPair {

    public ColorPair(String hex, Float probL, Float probH) {
        this.hexCode = hex;
        this.probLowerBound = probL;
        this.probUpperBound = probH;
    }

    String hexCode;
    Float probLowerBound;
    Float probUpperBound;

}
