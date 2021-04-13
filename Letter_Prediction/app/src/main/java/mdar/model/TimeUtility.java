package mdar.model;

public class TimeUtility {

    private long currentStart = 0;
    private long currentEnd = 0;
    public boolean hasStartedTyping = false;

    //this runs when the user hits a key, testing if they've started typing
    public void toggle() {
        if(!hasStartedTyping) {
            hasStartedTyping = true;
            startTime();
        }
    }

    public void startTime() {
        this.currentStart = System.currentTimeMillis();
    }

    public void endTime() {
        this.currentEnd = System.currentTimeMillis();
    }

    public float getElapsedTime() {
    long elapsedTime = currentEnd - currentStart;
    return (elapsedTime / 1000F);
    }


}
