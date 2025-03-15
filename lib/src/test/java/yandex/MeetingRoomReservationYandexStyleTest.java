package yandex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MeetingRoomReservationYandexStyleTest {
    @Test
    public void testYandexFailure() {
        var subj = new MeetingRoomReservationYandexStyle();
        var meetings = new ArrayList<MeetingInt>();
        /**
         * 1 2 3 4 5 6
         * --
         *   --
         *       --
         *       --
         *         --
         */
        meetings.add(new MeetingInt(1, 2));
        meetings.add(new MeetingInt(2, 3));
        meetings.add(new MeetingInt(4, 5));
        meetings.add(new MeetingInt(4, 5));
        meetings.add(new MeetingInt(5, 6));
        var outMeetings = subj.maximizeRoomReservations(meetings);
        //because yandex assumes that intervals (1,2) and (2,3) overlap...
        assertEquals(2, outMeetings.size());
    }
}
