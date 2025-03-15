package leetcode.solutions;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * https://education.yandex.ru/handbook/algorithms/article/zhadnye-algoritmy
 * 
 * Greedy solution giving the best results is to choose the meeting with the
 * earliest end time on each step - remember our goal is to maximize number of
 * meetings, not total usage of the room.
 */
public class MeetingRoomReservation {

    public List<Meeting> maximizeRoomReservations(List<Meeting> inputMeetings) {
        if (inputMeetings.size() < 2) {
            return inputMeetings;
        }
        var outMeetings = new ArrayList<Meeting>();
        var modifiableInputMtgs = new ArrayList<Meeting>(inputMeetings);
        while (!modifiableInputMtgs.isEmpty()) {
            final Meeting earliestEndingMeeting = findEarliestEndingMeeting(modifiableInputMtgs);
            removeMeetingAndItsIntersections(modifiableInputMtgs, earliestEndingMeeting);
            outMeetings.add(earliestEndingMeeting);
        }
        return outMeetings;
    }

    private Meeting findEarliestEndingMeeting(List<Meeting> inputMeetings) {
        var iter = inputMeetings.iterator();
        Meeting earliestEndingMeeting = null;
        while (iter.hasNext()) {
            var candidate = iter.next();
            if (earliestEndingMeeting == null || candidate.end().isBefore(earliestEndingMeeting.end())) {
                earliestEndingMeeting = candidate;
            }
        }
        return earliestEndingMeeting;
    }

    private void removeMeetingAndItsIntersections(List<Meeting> inputMeetings, Meeting earliestEndingMeeting) {
        var iter = inputMeetings.listIterator();
        while (iter.hasNext()) {
            var curMtg = iter.next();
            if (curMtg == earliestEndingMeeting || curMtg.overlaps(earliestEndingMeeting)) {
                iter.remove();
            }
        }
    }
}

record Meeting(LocalTime start, LocalTime end) {
    public boolean overlaps(Meeting other) {
        /*
         * cases:
         * 1. ---
         * -
         * 2. ---
         * ---
         * 3. ---
         * --
         * 4. ---
         * -----
         */
        return start.isBefore(other.end) && end.isAfter(other.start);
    }
}
