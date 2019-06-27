package comp3350.spa.business;

import java.util.List;

import comp3350.spa.application.Main;
import comp3350.spa.objects.Course;
import comp3350.spa.application.CommonAccess;
import comp3350.spa.persistence.Database;

public class CourseList {

    private Database db;

    public CourseList() {
        db = CommonAccess.getDatabase(Main.getDBName());
    }

    public CourseList(Database database) {
        db = database;
    }

    public void getCourses(List<Course> eventResult) {
        eventResult.clear();
        db.getCourses(eventResult);
    }

    public boolean addCourse(Course newCourse) {
        boolean success = false;
        if ((newCourse.getCourseName() != null && !newCourse.getCourseName().equals("") && !courseExists(newCourse))) {
            db.addCourse(newCourse);
            success = courseExists(new Course(newCourse.getCourseName()));
        }

        return success;
    }

    public boolean deleteCourse(Course oldCourse) {
        boolean success = false;
        if (courseExists(oldCourse)) {
            db.deleteCourse(oldCourse);
            success = !courseExists(new Course(oldCourse.getCourseName()));
        }

        return success;
    }

    public boolean renameCourse(Course oldCourse, String newName) {
        boolean success = false;
        if (newName != null && !newName.equals("") && courseExists(oldCourse) && !courseExists(new Course(newName))) {
            db.renameCourse(oldCourse, newName);
            success = courseExists(new Course(newName));
        }

        return success;
    }

    private boolean courseExists(Course course) {
        boolean exists = false;
        if (course.getCourseName() != null && !course.getCourseName().equals("")) {
            exists = db.courseExists(course);
        }

        return exists;
    }
}
