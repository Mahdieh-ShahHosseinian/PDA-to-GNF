import java.io.*;
import java.util.ArrayList;

/**
 * δ(qi, a, A) = {c1, c2, . . . , cn}
 *
 * ci = (qi, λ)
 * ci = (qj , BC)
 *
 * (qj , λ) ∈ δ(qi, a, A)        (qiAqj ) → a
 *
 * ci = (qj , BC)                (qiAqk) → a(qjBql)(qlCqk)
 */

public class ConvertToGNF {

    private final ReadXMLFile readXMLFile = new ReadXMLFile("src/inputFile.xml");
    private final Automata automata = readXMLFile.getAutomata();
    private final File saveFile = new File(System.getProperty("user.home") + "\\Documents\\GNF.txt");

    public ConvertToGNF() {
        print();
    }

    private void print() {

        try {

            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(saveFile)));
            performSecondCondition();

            for (Transition transition : automata.getTransitions()) {

                if (transition.getPush().equals("la")) {

                    printWriter.println("(" + transition.getSource().toString() + transition.getPop() +
                            transition.getDestination().toString() + ")\t->\t" + (transition.getInput().equals("la") ? "λ" : transition.getInput()));
                } else {

                    for (State k : automata.getStates()) {

                        printWriter.print("(" + transition.getSource() + transition.getPop() + k + ")\t->\t");

                        for (State l : automata.getStates()) {

                            printWriter.print((transition.getInput().equals("la") ? "" : transition.getInput()) + "(" +
                                    transition.getDestination() + transition.getPush().charAt(0) +
                                    l + ")(" + l + transition.getPush().charAt(1) + k + ")" +
                                    (l == automata.getStates().get(automata.getStates().size() - 1) ? "" : " | "));
                        }
                        printWriter.println();
                    }
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void performSecondCondition() {

        ArrayList<Transition> newTransitions = new ArrayList<>();
        for (Transition transition : automata.getTransitions()) {

            if (transition.getPush().length() == 1 && !transition.getPush().equals("la")) {

                State newState = new State("q" + automata.getStates().size());

                automata.getStates().add(newState);
                newTransitions.add(new Transition("tr-1", newState, transition.getDestination()
                        , "la", "z", transition.getPush() + "z"));

                transition.setDestination(newState);
                transition.setPush("la");
            }
        }

        automata.getTransitions().addAll(newTransitions);
    }
}
