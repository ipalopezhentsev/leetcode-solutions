package yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Same in spirit as MeetingRoomReservation but follows
 * 'raw' Yandex approach and follows its assumption that
 * intervals like (1,2) and (2,3) overlap because they have common point 2.
 */
public class MeetingRoomReservationYandexStyle {
    public static void main(String[] args) throws IOException {
        MeetingRoomReservationYandexStyle prog = new MeetingRoomReservationYandexStyle();
        var inputMeetings = prog.getMeetingsWithScanner();
        var outputMeetings = prog.maximizeRoomReservations(inputMeetings);
        System.out.println(outputMeetings.size());
    }

    public List<MeetingInt> maximizeRoomReservations(List<MeetingInt> inputMeetings) {
        if (inputMeetings.size() < 2) {
            return inputMeetings;
        }
        var outMeetings = new ArrayList<MeetingInt>();
        while (!inputMeetings.isEmpty()) {
            var earliestEndingMeeting = findEarliestEndingMeeting(inputMeetings);
            removeMeetingAndIntersections(inputMeetings, earliestEndingMeeting);
            outMeetings.add(earliestEndingMeeting);
        }
        return outMeetings;
    }

    private MeetingInt findEarliestEndingMeeting(List<MeetingInt> inputMeetings) {
        MeetingInt earliestMtg = null;
        for (var mtg : inputMeetings) {
            if (earliestMtg == null || mtg.end < earliestMtg.end) {
                earliestMtg = mtg;
            }
        }
        return earliestMtg;
    }

    private void removeMeetingAndIntersections(List<MeetingInt> inputMeetings, MeetingInt earliestEndingMeeting) {
        var iter = inputMeetings.listIterator();
        while (iter.hasNext()) {
            var cur = iter.next();
            if (cur == earliestEndingMeeting || earliestEndingMeeting.overlaps(cur)) {
                iter.remove();
            }
        }
    }

    private List<MeetingInt> getMeetings() throws IOException {
        var meetings = new ArrayList<MeetingInt>();
        try (var inputStrRd = new InputStreamReader(System.in);
                var reader = new BufferedReader(inputStrRd)) {
            var strLen = reader.readLine();
            int len = Integer.parseInt(strLen);
            for (int i = 0; i < len; i++) {
                var line = reader.readLine();
                var meeting = parseMeeting(line);
                meetings.add(meeting);
            }
        }
        return meetings;
    }

    private MeetingInt parseMeeting(String line) {
        var pair = line.split(" ");
        int start = Integer.parseInt(pair[0]);
        int end = Integer.parseInt(pair[1]);
        return new MeetingInt(start, end);
    }

    private List<MeetingInt> getMeetingsWithScanner() {
        var meetings = new ArrayList<MeetingInt>();
        try (var scanner = new Scanner(System.in)) {
            var len = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < len; i++) {
                var line = scanner.nextLine();
                var meeting = parseMeeting(line);
                meetings.add(meeting);
            }
            return meetings;
        }
    }
}

class MeetingInt {
    public final int start;
    public final int end;

    public MeetingInt(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(MeetingInt other) {
        return this.start <= other.end && this.end >= other.start;
    }
}