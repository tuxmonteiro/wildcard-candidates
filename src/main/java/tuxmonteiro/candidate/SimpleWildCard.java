package tuxmonteiro.candidate;

import java.util.Arrays;
import java.util.List;

public class SimpleWildCard implements Candidate {

    @Override
    public boolean match(String pattern, boolean showInterval) {
        boolean result = false;
        int textLen = text.length();

        long timestampStart = System.nanoTime();

        String tempText = text;
        int idx = -1;
        for (String subPattern : Arrays.asList(pattern.split("\\*"))) {
            idx = tempText.indexOf(subPattern);
            if (idx == -1) {
                break;
            }
            tempText = tempText.substring(idx, textLen - subPattern.length());
        };

        result = idx != -1;
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