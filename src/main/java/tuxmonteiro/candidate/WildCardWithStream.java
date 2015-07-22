package tuxmonteiro.candidate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class WildCardWithStream implements Candidate {

    @Override
    public boolean match(String pattern, boolean showInterval) {
        AtomicBoolean result = new AtomicBoolean(true);
        int textLen = text.length();

        long timestampStart = System.nanoTime();

        AtomicReference<String> tempText = new AtomicReference<>(text);
        Arrays.asList(pattern.split("\\*")).forEach(subPattern -> {
            int idx = -1;
            result.set(result.get() && (idx = tempText.get().indexOf(subPattern)) != -1);
            tempText.set(tempText.get().substring(idx, textLen - subPattern.length()));
        });

        long timestampEnd = System.nanoTime();

        if (showInterval) {
            System.out.println(this.getClass().getSimpleName()+" ("+pattern+"): "
                    +(timestampEnd-timestampStart)+" nanosec - "+ (result.get() ? "true" : "false"));
        }
        return result.get();
    }

    @Override
    public List<String> patterns() {
        return Arrays.asList("*/fox/*", "*dog");
    }
}