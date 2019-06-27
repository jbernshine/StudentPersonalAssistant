package comp3350.spa.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import comp3350.spa.business.Events;
import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.persistence.StubDatabase;

public class EventsUnitTest {

    private Events events;

    @Before
    public void setUp() {
        events = new Events(new StubDatabase());
    }

    @Test
    public void testClearEvents() {
        assertNotEquals(0, events.getNumEvents());
        events.clearAllEvents();
        assertEquals(0, events.getNumEvents());
    }

    @Test
    public void testAddEvent() {
        events.clearAllEvents();
        CalendarEvent add = getTestEvent();
        events.addEvent(add);
        assertEquals(1, events.getNumEvents());
    }

    @Test
    public void testEventsForDay() {
        events.clearAllEvents();

        Calendar calOne = new GregorianCalendar();
        CalendarEvent eventOne = new CalendarEvent("Test One", calOne, CalendarEvent.Priority.HIGH);
        events.addEvent(eventOne);

        Calendar calTwo = new GregorianCalendar();
        calTwo.add(Calendar.DAY_OF_YEAR, 5);
        CalendarEvent eventTwo = new CalendarEvent("Test Two", calTwo, CalendarEvent.Priority.MEDIUM);
        events.addEvent(eventTwo);

        Calendar calThree = new GregorianCalendar();
        calThree.add(Calendar.DAY_OF_YEAR, 5);
        CalendarEvent eventThree = new CalendarEvent("Test Three", calThree, CalendarEvent.Priority.NONE);
        events.addEvent(eventThree);

        Calendar calFour = new GregorianCalendar();
        calFour.add(Calendar.DAY_OF_YEAR, 8);
        CalendarEvent eventFour = new CalendarEvent("Test Four", calFour, CalendarEvent.Priority.LOW);
        events.addEvent(eventFour);

        Calendar checkDate = new GregorianCalendar();
        checkDate.add(Calendar.DAY_OF_YEAR, 5);

        ArrayList<CalendarEvent> dayEvents = new ArrayList<>();
        events.getEventsForDay(dayEvents, checkDate);

        assertEquals(2, dayEvents.size());

        checkDate.add(Calendar.DAY_OF_YEAR, 2);
        events.getEventsForDay(dayEvents, checkDate);
        assertEquals(0, dayEvents.size());
    }

    @Test
    public void testDuplicateEvent() {
        events.clearAllEvents();
        CalendarEvent add = getTestEvent();
        events.addEvent(add);
        assertFalse(events.addEvent(add));
    }

    @Test
    public void testRandomEvent() {
        events.clearAllEvents();
        CalendarEvent add = getTestEvent();
        events.addEvent(add);
        assertEquals(add, events.getRandomEvent());
    }

    private CalendarEvent getTestEvent() {
        Calendar cal = new GregorianCalendar();
        return new CalendarEvent("Test", cal, CalendarEvent.Priority.HIGH);
    }
}
