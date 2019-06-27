package comp3350.spa.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.objects.Course;
import comp3350.spa.objects.GradeItem;

public interface Database {

    void open(String dbName);

    void close();

    void getEventSequential(List<CalendarEvent> eventResult);

    void clearAllEvents();

    void insertEvent(CalendarEvent event);

    boolean containsEvent(CalendarEvent event);

    void updateEvent(CalendarEvent event);

    void deleteEvent(CalendarEvent event);

    void addCourse(Course newCourse);

    void deleteCourse(Course deleteCourse);

    void getCourses(List<Course> eventResult);

    void renameCourse(Course oldCourse, String newName);

    boolean courseExists(Course course);

    void addGradeItem(GradeItem newItem);

    void removeGradeItem(GradeItem oldItem);

    void renameGradeItem(GradeItem item, String newName);

    double getTotalWeight(String courseID);

    double getTotalGrade(String courseID);

    void setGradeItemGrade(GradeItem item, double grade);

    void setGradeItemWeight(GradeItem item, double weight);

    ArrayList<GradeItem> getGradeItems(String courseID);

    boolean gradeItemExists(GradeItem item);

    GradeItem getGradeItem(String courseID, String itemName);
}
