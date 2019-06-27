package comp3350.spa.tests.integration;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import comp3350.spa.application.CommonAccess;
import comp3350.spa.application.Main;
import comp3350.spa.business.CourseList;
import comp3350.spa.business.Events;
import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.objects.Course;

public class BusinessPersistenceSeam extends TestCase {

    public BusinessPersistenceSeam(String arg){
        super(arg);
    }

    @Test
    public void testCourseListPersistenceSeam(){

        System.out.println("Testing courseList persistence seam");

        CourseList courseList;
        Course course1;
        Course course2;
        String result;

        Main.setDBPathName("app/src/main/assets/db/SC");

        CommonAccess.closeDatabase();
        CommonAccess.createDatabase(Main.getDBName());

        course1 = new Course("Comp 3430");
        course2 = new Course("Comp 3190");
        assertNotNull(course1);
        assertNotNull(course2);


        courseList = new CourseList();
        courseList.addCourse(course1);
        assertFalse("Error: Course already exists.",courseList.addCourse(course1));

        courseList.renameCourse(course1, "Comp 3190");
        assertFalse("Error: Course already exists.",courseList.addCourse(course2));
        assertTrue(courseList.deleteCourse(course2));

        assertTrue(courseList.addCourse(course2));

        CommonAccess.closeDatabase();

        System.out.println("Finished testing courseList persistence seam");
    }

    @Test
    public void testEventsPersistenceSeam(){

        System.out.println("Testing events persistence seam");

        Events events;
        CalendarEvent event;
        ArrayList<CalendarEvent> dayEvents = new ArrayList<>();

        Main.setDBPathName("app/src/main/assets/db/SC");

        CommonAccess.closeDatabase();
        CommonAccess.createDatabase(Main.getDBName());



        events = new Events();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.YEAR, -20);
        CalendarEvent testEvent = new CalendarEvent("Test Event", cal, CalendarEvent.Priority.HIGH);

        assertFalse(events.containsEvent(testEvent));
        events.addEvent(testEvent);
        assertTrue(events.containsEvent(testEvent));

        events.getEventsForDay(dayEvents, cal);
        assertEquals(dayEvents.size(), 1);

        CalendarEvent getEvent = dayEvents.get(0);
        events.removeEvent(getEvent);

        assertFalse(events.containsEvent(testEvent));

        System.out.println("Finished testing events persistence seam");
    }

}
