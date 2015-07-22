package tuxmonteiro;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;

import tuxmonteiro.candidate.Candidate;
import tuxmonteiro.candidate.DkAutomaton;
import tuxmonteiro.candidate.JoddWildCard;
import tuxmonteiro.candidate.Regex;
import tuxmonteiro.candidate.SimpleWildCard;
import tuxmonteiro.candidate.WildCardWithStream;

public class Starter {

    enum CandidateEnum {
        WILDCARD_WITH_STREAM(new WildCardWithStream()),
        WILDCARD(new SimpleWildCard()),
        JWILDCARD(new JoddWildCard()),
        REGEX(new Regex()),
        DK_AUTOMATON(new DkAutomaton());

        private final Candidate candidate;
        private CandidateEnum(Candidate candidate) {
            this.candidate = candidate;
        }
        public Candidate get() {
            return candidate;
        }
    }

    public static void main(String[] args) {
        long warmMsec = 30000L;
        AtomicBoolean isCold = new AtomicBoolean(true);
        long startTime = System.currentTimeMillis();
        while (isCold.get()) {
            if (System.currentTimeMillis()-startTime>warmMsec) {
                isCold.set(false);
            }
            EnumSet.allOf(CandidateEnum.class).stream().forEach(candidate -> {
                candidate.get().patterns().stream().forEach(pattern -> {
                    candidate.get().match(pattern, !isCold.get());
                });
            });
        };
    }

}
