public class Transition {

    private String name;
    private State source;
    private State destination;
    private String input;
    private String pop;
    private String push;

    public Transition(String name, State source, State destination, String input, String pop, String push) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.input = input;
        this.pop = pop;
        this.push = push;
    }

    // Getters and setters
    public State getSource() {
        return source;
    }

    public State getDestination() {
        return destination;
    }

    public String getInput() {
        return input;
    }

    public String getPop() {
        return pop;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public void setDestination(State destination) {
        this.destination = destination;
    }
}
