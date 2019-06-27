package comp3350.spa.tests.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import comp3350.spa.objects.CalendarEvent;

public class EventUnitTest {

    @Test
    public void testEvent() {
        Calendar eventDate = new GregorianCalendar();
        CalendarEvent eventOne = new CalendarEvent("Test", eventDate, CalendarEvent.Priority.MEDIUM);

        assertTrue(eventOne.getEventName().equals("Test"));
        assertTrue(eventOne.getEventDate().equals(eventDate));
        assertTrue(eventOne.getPriority().equals(CalendarEvent.Priority.MEDIUM));
    }

    @Test
    public void testEventPriorityChange() {
        Calendar eventDate = new GregorianCalendar();
        CalendarEvent eventOne = new CalendarEvent("Test", eventDate, CalendarEvent.Priority.MEDIUM);

        assertTrue(eventOne.getPriority().equals(CalendarEvent.Priority.MEDIUM));
        eventOne.setPriority(CalendarEvent.Priority.HIGH);
        assertTrue(eventOne.getPriority().equals(CalendarEvent.Priority.HIGH));
    }

    @Test
    public void testSameDayNull() {
        Calendar eventOneDate = new GregorianCalendar();
        CalendarEvent eventOne = new CalendarEvent("Test", eventOneDate, CalendarEvent.Priority.MEDIUM);

        assertFalse(eventOne.isSameDay(null));
    }

    @Test
    public void testSameDay() {
        Calendar eventOneDate = new GregorianCalendar();
        Calendar eventTwoDate = new GregorianCalendar();

        Calendar eventThreeDate = new GregorianCalendar();
        eventThreeDate.add(Calendar.DAY_OF_YEAR, 1);

        Calendar eventFourDate = new GregorianCalendar();
        eventFourDate.add(Calendar.MONTH, 1);

        Calendar eventFiveDate = new GregorianCalendar();
        eventFiveDate.add(Calendar.YEAR, 1);

        CalendarEvent eventOne = new CalendarEvent("Test", eventOneDate, CalendarEvent.Priority.MEDIUM);

        assertTrue(eventOne.isSameDay(eventTwoDate));
        assertFalse(eventOne.isSameDay(eventThreeDate));
        assertFalse(eventOne.isSameDay(eventFourDate));
        assertFalse(eventOne.isSameDay(eventFiveDate));
    }

    @Test
    public void testToString() {
        Calendar eventOneDate = new GregorianCalendar();
        CalendarEvent eventOne = new CalendarEvent("Test", eventOneDate, CalendarEvent.Priority.MEDIUM);
        assertEquals("Test", eventOne.toString());
    }
}
