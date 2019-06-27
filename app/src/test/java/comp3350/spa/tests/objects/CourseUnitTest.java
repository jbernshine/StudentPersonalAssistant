package comp3350.spa.tests.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.spa.objects.Course;

public class CourseUnitTest {

	private Course course;

	@Test
	public void testNewCourse() {
		course = new Course("Distributed Computing");
		assertEquals("Distributed Computing", course.getCourseName());
	}

	@Test
	public void testGetCourseName() {
		course = new Course("Distributed Computing");
		assertEquals("Distributed Computing", course.getCourseName());
	}

	@Test
	public void testSetCourseName() {
		course = new Course("Distributed Computing");
		course.setCourseName("Programming Practices");
		assertEquals("Programming Practices", course.getCourseName());
	}

	@Test
	public void testToString() {
		course = new Course("Distributed Computing");
		assertNotNull(course);
		assertEquals("Distributed Computing", course.toString());
	}

	@Test
	public void testEquals() {
		Course course2 = new Course("Distributed Computing");
		course = new Course("Distributed Computing");

		assertTrue(course.equals(course2));
	}
}