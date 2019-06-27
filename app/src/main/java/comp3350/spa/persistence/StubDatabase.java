package comp3350.spa.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.objects.Course;
import comp3350.spa.objects.GradeItem;

public class StubDatabase implements Database {

    private ArrayList<Course> courses;
    private ArrayList<CalendarEvent> events;
    private ArrayList<GradeItem> grades;

    public void open(String dbName) {

    }

    public void close() {

    }

    public StubDatabase() {
        courses = new ArrayList<>();
        Course testCourse1 = new Course("Test Course 1");
        Course testCourse2 = new Course("Test Course 2");

        addCourse(testCourse1);
        addCourse(testCourse2);

        grades = new ArrayList<>();

        CalendarEvent event;

        events = new ArrayList<>();
        Calendar cal = new GregorianCalendar();

        event = new CalendarEvent("Assignment 1 Due", cal, CalendarEvent.Priority.HIGH);
        events.add(event);

        cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        event = new CalendarEvent("Assignment 2 Due", cal, CalendarEvent.Priority.MEDIUM);
        events.add(event);

        cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, 8);
        event = new CalendarEvent("Comp 2140 Midterm", cal, CalendarEvent.Priority.HIGH);
        events.add(event);

        cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        event = new CalendarEvent("Assignment 3 Due", cal, CalendarEvent.Priority.LOW);
        events.add(event);
        event = new CalendarEvent("Comp 1020 Midterm", cal, CalendarEvent.Priority.HIGH);
        events.add(event);

        cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, 1);
        event = new CalendarEvent("Assignment 4 Due", cal, CalendarEvent.Priority.NONE);
        events.add(event);
    }

    public void getEventSequential(List<CalendarEvent> eventResult) {
        eventResult.addAll(events);
    }

    public void clearAllEvents() {
        events.clear();
    }

    public void insertEvent(CalendarEvent event) {
        events.add(event);
    }

    public boolean containsEvent(CalendarEvent event) {
        return events.contains(event);
    }

    public void updateEvent(CalendarEvent event) {
        int index = events.indexOf(event);
        if (index >= 0){
            events.set(index, event);
        }
    }

    public void deleteEvent(CalendarEvent event) {
        events.remove(event);
    }

    public void addCourse(Course newCourse) {
        courses.add(newCourse);
    }

    public void deleteCourse(Course deleteCourse) {
        courses.remove(deleteCourse);
    }

    public void getCourses(List<Course> eventResult) {
        eventResult.clear();
        eventResult.addAll(courses);
    }

    public void renameCourse(Course oldCourse, String newName) {
        Course foundCourse;
        int index = courses.indexOf(oldCourse);

        if (index >= 0) {
            foundCourse = courses.get(index);
            foundCourse.setCourseName(newName);
        }
    }

    public boolean courseExists(Course course) {
        return courses.contains(course);
    }

    public void addGradeItem(GradeItem newItem) {
        grades.add(newItem);
    }

    public void removeGradeItem(GradeItem oldItem) {
        grades.remove(oldItem);
    }

    public void renameGradeItem(GradeItem item, String newName) {
        GradeItem foundItem;
        int index = grades.indexOf(item);

        if (index >= 0) {
            foundItem = grades.get(index);
            foundItem.setItemName(newName);
        }
    }

    public double getTotalWeight(String courseID) {
        double total = 0;
        GradeItem item;

        for (int i = 0; i < grades.size(); i++) {
            item = grades.get(i);
            if (courseID.equals(item.getItemCourseID()))
                total += item.getItemWeight();
        }

        return total;
    }

    public double getTotalGrade(String courseID) {
        double total = 0;
        GradeItem item;

        for (int i = 0; i < grades.size(); i++) {
            item = grades.get(i);
            if (courseID.equals(item.getItemCourseID()))
                if (item.getItemGrade() >= 0)
                    total += item.getItemGrade();
        }

        return total;
    }

    public void setGradeItemGrade(GradeItem item, double grade) {
        int index = grades.indexOf(item);
        if (index >= 0)
            grades.get(index).setItemGrade(grade);
    }

    public void setGradeItemWeight(GradeItem item, double weight) {
        int index = grades.indexOf(item);
        if (index >= 0)
            grades.get(index).setItemWeight(weight);
    }

    public ArrayList<GradeItem> getGradeItems(String courseID) {
        GradeItem item;
        ArrayList<GradeItem> items = new ArrayList<>();

        for (int i = 0; i < grades.size(); i++) {
            item = grades.get(i);
            if (courseID.equals(item.getItemCourseID()))
                items.add(item);
        }

        return items;
    }

    public boolean gradeItemExists(GradeItem item) {
        return grades.contains(item);
    }

    public GradeItem getGradeItem(String courseID, String itemName) {
        GradeItem result = null;
        for (int i = 0; i < grades.size(); i++) {
            GradeItem item = grades.get(i);
            if (item.getItemCourseID().equals(courseID) && item.getItemName().equals(itemName))
                result = item;
        }

        return result;
    }
}

