package managers.dataModels;

public class Result {
    private final Dot dot;
    private final boolean status;
    private final String currentTime;
    private final double timeOfCalculating;

    private Result(Dot dot, boolean status, double timeOfCalculating, String currentTime) {
        this.dot = dot;
        this.status = status;
        this.currentTime = currentTime;
        this.timeOfCalculating = timeOfCalculating;
    }

    public static Result createResult(Dot dot, boolean status, double timeOfCalculating, String currentTime) {
        return new Result(dot, status, timeOfCalculating, currentTime);
    }

    public Dot getDot() {
        return dot;
    }

    public boolean getStatus() {
        return status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public double getTimeOfCalculating() {
        return timeOfCalculating;
    }
}
