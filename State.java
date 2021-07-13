public class State {

    private String name;

    public State(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
