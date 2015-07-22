package tuxmonteiro.candidate;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex implements Candidate {

    @Override
    public boolean match(String pattern, boolean showInterval) {
        boolean result = false;

        long timestampStart = System.nanoTime();

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(text);
        result = matcher.matches();

        long timestampEnd = System.nanoTime();
        if (showInterval) {
            System.out.println(this.getClass().getSimpleName()+" ("+pattern+"): "
                    +(timestampEnd-timestampStart)+" nanosec - "+ (result ? "true" : "false"));
        }
        return result;
    }

    @Override
    public List<String> patterns() {
        return Arrays.asList(".*/fox/.*", ".*dog$");
    }

}