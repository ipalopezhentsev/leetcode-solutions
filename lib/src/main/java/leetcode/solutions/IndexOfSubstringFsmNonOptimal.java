package leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 * Naive use of FSM - due to very stupid backtracking (see testcase for which it's needed) it has the same
 * complexity as IndexOfSubstringSimple
 * (see backtracking in matchesFsm())
 */
public class IndexOfSubstringFsmNonOptimal {
    public int strStr(String haystack, String needle) {
        var fsm = prepareFsm(needle);
        //System.out.println(fsm);
        return matchesFsm(haystack, fsm, needle.length());
    }

    private FsmState prepareFsm(String needle) {
        if (needle.isEmpty()) {
            var fsm = new FsmState();
            fsm.isFinal = true;
            return fsm;
        }
        var firstState = new FsmState();
        var curState = firstState;
        for (int i = 0; i<needle.length(); i++) {
            var c = needle.charAt(i);
            var s = new FsmState();
            curState.transitions.put(c, s);
            curState = s;
        }
        curState.isFinal = true;
        return firstState;
    }

    private int matchesFsm(String haystack, FsmState fsmFirstState, int needleLength) {
        FsmState curState = fsmFirstState;
        int i = 0;
        int prevResetIdx = i;
        int cnt=0;
        while (!curState.isFinal && i < haystack.length()) {
            var sym = haystack.charAt(i);
            var newState = curState.transitions.get(sym);
            curState = newState != null ? newState : fsmFirstState;
            if (newState == null) {
                i = prevResetIdx;
                prevResetIdx++;
                //System.out.println("Sym " + sym + " resetted idx to " + i);

            }
            //System.out.println("After sym " + sym + ": "+curState);

            i++;
            cnt++;
        }
        System.out.println(cnt);
        return curState.isFinal ? i-needleLength : -1;
    }

    private static class FsmState {
        boolean isFinal;
        Map<Character, FsmState> transitions = new HashMap<>();
        public String toString() {
            return "FsmState: isFinal=" + isFinal + ", transitions=" + transitions;
        }
    }
}
