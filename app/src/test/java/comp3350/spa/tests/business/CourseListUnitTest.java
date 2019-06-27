package comp3350.spa.tests.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import comp3350.spa.business.CourseList;
import comp3350.spa.objects.Course;
import comp3350.spa.persistence.StubDatabase;

public class CourseListUnitTest {
    private CourseList courses;

    @Before
    public void setUp() {
        courses = new CourseList(new StubDatabase());
    }

    @Test
    public void testAddCourse() {
        assertTrue(courses.addCourse(new Course("Test Course")));
    }

    @Test
    public void testAddingNullCourse() {
        assertFalse(courses.addCourse(new Course()));
    }

    @Test
    public void testAddingEmptyStringCourse() { assertFalse(courses.addCourse((new Course("")))); }

    @Test
    public void testAddDuplicateCourse() {
        String name = "Testing Duplicate";
        courses.addCourse(new Course(name));
        assertFalse(courses.addCourse(new Course(name)));
    }

    @Test
    public void testDeleteCourse() {
        Course testCourse = new Course("Test Course");
        courses.addCourse(testCourse);
        assertTrue(courses.deleteCourse(testCourse));
        assertFalse(courses.deleteCourse(testCourse));
    }

    @Test
    public void testDeleteNonExistentCourse() {
        assertFalse(courses.deleteCourse(new Course("This course does not exist")));
    }

    @Test
    public void testRenamingCourse() {
        Course oldCourse = new Course("Old Course");
        courses.addCourse(oldCourse);
        assertTrue(courses.renameCourse(oldCourse, "New Course"));

        ArrayList<Course> courseList = new ArrayList<>();
        courses.getCourses(courseList);
        assertTrue(courseList.contains(new Course("New Course")));
        assertFalse(courseList.contains(new Course("Old Course")));
    }

    @Test
    public void testRenamingNonExistentCourse() {
        Course notAdded = new Course("This course does not exist");
        assertFalse(courses.renameCourse(notAdded, "Should be false"));
    }

    @Test
    public void testRenamingNullCourse() {
        Course nullCourse = new Course();
        assertFalse(courses.renameCourse(nullCourse, "Should be false"));
    }

    @Test
    public void testRenamingEmptyCourse() {
        Course emptyCourse = new Course("");
        assertFalse(courses.renameCourse(emptyCourse, "Should be false"));
    }

    @Test
    public void testOperations() {
        Course newCourse = new Course("New Course");
        assertTrue(courses.addCourse(newCourse));
        assertTrue(courses.renameCourse(newCourse, "Renamed Course"));
        assertFalse(courses.renameCourse(new Course("New Course"), "False"));
        assertTrue(courses.addCourse(new Course("New Course")));
        assertTrue(courses.deleteCourse(new Course("New Course")));
        assertFalse(courses.renameCourse(new Course("New Course"), "False"));
    }

    @Test
    public void testRenaming() {
        ArrayList<Course> courseList = new ArrayList<>();
        courses.getCourses(courseList);
        Course oldCourse = courseList.get(1);

        courses.renameCourse(oldCourse, "Comp 3350");
        courses.getCourses(courseList);
        assertEquals(oldCourse, courseList.get(1));
        assertEquals("Comp 3350", courseList.get(1).toString());
    }
}
