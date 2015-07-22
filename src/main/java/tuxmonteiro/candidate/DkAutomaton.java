package tuxmonteiro.candidate;

import java.util.Arrays;
import java.util.List;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class DkAutomaton implements Candidate {

    @Override
    public boolean match(String pattern, boolean showInterval) {
        boolean result = false;

        long timestampStart = System.nanoTime();

        RegExp re = new RegExp(pattern);
        RunAutomaton ra = new RunAutomaton(re.toAutomaton());
        result = ra.run(text);

        long timestampEnd = System.nanoTime();
        if (showInterval) {
            System.out.println(this.getClass().getSimpleName()+" ("+pattern+"): "
                    +(timestampEnd-timestampStart)+" nanosec - "+ (result ? "true" : "false"));
        }
        return result;
    }

    @Override
    public List<String> patterns() {
        return Arrays.asList(".*/fox/.*", ".*dog");
    }

}