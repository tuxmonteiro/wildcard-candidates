package tuxmonteiro.candidate;

import java.util.Arrays;
import java.util.List;

import jodd.util.Wildcard;

public class JoddWildCard implements Candidate {

    @Override
    public boolean match(String pattern, boolean showInterval) {
        long timestampStart = System.nanoTime();

        boolean result = Wildcard.match(text, pattern);

        long timestampEnd = System.nanoTime();

        if (showInterval) {
            System.out.println(this.getClass().getSimpleName()+" ("+pattern+"): "
                    +(timestampEnd-timestampStart)+" nanosec - "+ (result ? "true" : "false"));
        }
        return result;
    }

    @Override
    public List<String> patterns() {
        return Arrays.asList("*/fox/*", "*dog");
    }

}