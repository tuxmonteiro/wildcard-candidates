package tuxmonteiro.candidate;

import java.util.List;

public interface Candidate {

    String text = "http://The.quick:brown/fox/jumps/over.the?lazy=dog";

    boolean match(String pattern, boolean showInterval);

    List<String> patterns();
}