package yandex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class MeetingTest {
    @Test
    void testDoesNotOverlap() {
        var m1 = new Meeting(LocalTime.of(1,0), LocalTime.of(4,0));
        var m2 = new Meeting(LocalTime.of(4,1), LocalTime.of(5,0));
        assertFalse(m1.overlaps(m2));
    }

    @Test
    void testOverlaps1() {
        /*
           1234     
        1. ---
            -
        */
        var m1 = new Meeting(LocalTime.of(1,0), LocalTime.of(4,0));
        var m2 = new Meeting(LocalTime.of(2,0), LocalTime.of(3,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testOverlaps2() {
        /*
           1234     
        2. ---
            ---
        */
        var m1 = new Meeting(LocalTime.of(1,0), LocalTime.of(4,0));
        var m2 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testOverlaps3() {
        /*
           1234     
        3.  ---
           --
        */
        var m1 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        var m2 = new Meeting(LocalTime.of(1,0), LocalTime.of(3,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testOverlaps4() {
        /*
           123456
        4.  ---
           -----
        */
        var m1 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        var m2 = new Meeting(LocalTime.of(1,0), LocalTime.of(6,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testCommonStartIsOverlapping() {
        var m1 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        var m2 = new Meeting(LocalTime.of(2,0), LocalTime.of(3,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testCommonEndIsOverlapping() {
        var m1 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        var m2 = new Meeting(LocalTime.of(1,0), LocalTime.of(5,0));
        assertTrue(m1.overlaps(m2));
    }

    @Test
    void testAdjacentIsNotOverllapping() {
        var m1 = new Meeting(LocalTime.of(2,0), LocalTime.of(5,0));
        var m2 = new Meeting(LocalTime.of(1,0), LocalTime.of(2,0));
        assertFalse(m1.overlaps(m2));
    }
}
