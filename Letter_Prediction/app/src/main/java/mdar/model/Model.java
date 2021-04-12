package mdar.model;

public class Model {

    public final String [] phrases =
            {"The quick brown fox jumped over the lazy dog",
                    "You can't judge a book by its cover",
                    "This sentence is incredibly difficult to type",
                    "Acrophobia is a natural fear of heights",
                    "A cephalopod is any member of the molluscan class Cephalopoda"};


    private long currentStart = 0;
    private long currentEnd = 0;
    public boolean hasStartedTyping = false;

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
