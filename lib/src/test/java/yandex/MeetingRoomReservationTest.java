package yandex;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeetingRoomReservationTest {

    @Test
    public void testMaximizeRoomReservations_noMeetings() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        List<Meeting> inputMeetings = List.of();
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(inputMeetings, result);
    }

    @Test
    public void testMaximizeRoomReservations_singleMeeting() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting = new Meeting(LocalTime.of(9, 0), LocalTime.of(10, 0));
        List<Meeting> inputMeetings = List.of(meeting);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(inputMeetings, result);
    }

    @Test
    public void testMaximizeRoomReservations_multipleMeetings() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting1 = new Meeting(LocalTime.of(9, 0), LocalTime.of(10, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(10, 0), LocalTime.of(11, 0));
        List<Meeting> inputMeetings = List.of(meeting1, meeting2);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(inputMeetings, result);
    }

    @Test
    public void testMaximizeRoomReservations_overlappingMeetings() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting1 = new Meeting(LocalTime.of(9, 0), LocalTime.of(10, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(9, 30), LocalTime.of(10, 30));
        List<Meeting> inputMeetings = List.of(meeting1, meeting2);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(List.of(meeting1), result);
    }

    @Test
    public void testYandex1() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting1 = new Meeting(LocalTime.of(1, 0), LocalTime.of(5, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(1, 45), LocalTime.of(3, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(1, 0), LocalTime.of(2, 0));
        Meeting meeting4 = new Meeting(LocalTime.of(5, 45), LocalTime.of(6, 15));
        Meeting meeting5 = new Meeting(LocalTime.of(1, 45), LocalTime.of(2, 15));
        Meeting meeting6 = new Meeting(LocalTime.of(4, 0), LocalTime.of(4, 30));
        Meeting meeting7 = new Meeting(LocalTime.of(3, 0), LocalTime.of(4, 0));
        Meeting meeting8 = new Meeting(LocalTime.of(3, 0), LocalTime.of(5, 45));
        Meeting meeting9 = new Meeting(LocalTime.of(1, 30), LocalTime.of(3, 15));
        Meeting meeting10 = new Meeting(LocalTime.of(2, 30), LocalTime.of(3, 30));
        Meeting meeting11 = new Meeting(LocalTime.of(4, 45), LocalTime.of(5, 30));
        List<Meeting> inputMeetings = List.of(meeting1, meeting2, meeting3, meeting4,
                meeting5, meeting6, meeting7, meeting8, meeting9, meeting10, meeting11);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(5, result.size());
        assertTrue(result.contains(meeting3));
        assertTrue(result.contains(meeting4));
        assertTrue(result.contains(meeting6));
        assertTrue(result.contains(meeting10));
        assertTrue(result.contains(meeting11));
    }

    @Test
    public void testYandex2() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting1 = new Meeting(LocalTime.of(1, 0), LocalTime.of(3, 30));
        Meeting meeting2 = new Meeting(LocalTime.of(2, 45), LocalTime.of(4, 15));
        Meeting meeting3 = new Meeting(LocalTime.of(4, 0), LocalTime.of(6, 0));
        List<Meeting> inputMeetings = List.of(meeting1, meeting2, meeting3);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(2, result.size());
        assertTrue(result.contains(meeting1));
        assertTrue(result.contains(meeting3));
    }

    @Test
    public void testYandex3() {
        MeetingRoomReservation reservation = new MeetingRoomReservation();
        Meeting meeting1 = new Meeting(LocalTime.of(1, 0), LocalTime.of(5, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(1, 45), LocalTime.of(2, 15));
        Meeting meeting3 = new Meeting(LocalTime.of(4, 0), LocalTime.of(5, 45));
        List<Meeting> inputMeetings = List.of(meeting1, meeting2, meeting3);
        List<Meeting> result = reservation.maximizeRoomReservations(inputMeetings);
        assertEquals(2, result.size());
        assertTrue(result.contains(meeting2));
        assertTrue(result.contains(meeting3));
    }
}