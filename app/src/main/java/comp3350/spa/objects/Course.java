package comp3350.spa.objects;

public class Course {

    private String courseName;

    public Course() {
        courseName = null;
    }

    public Course (String newCourseName) {
        courseName = newCourseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String newName) {
        courseName = newName;
    }

    public String toString() {
        return courseName;
    }

    public boolean equals(Object object) {
        boolean result = false;
        Course course;

        if (object instanceof Course) {
            course = (Course) object;
            if (course.courseName == null && courseName == null || course.courseName.equals(courseName))
                result = true;
        }

        return result;
    }
}
