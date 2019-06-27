package comp3350.spa.tests.objects;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.spa.objects.GradeItem;

public class GradeItemUnitTest {
    private GradeItem gradeItem;
    private static final double EPSILON = 1e-15;

    @Test
    public void testGetItemCourseID() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        assertEquals("Comp 3430", gradeItem.getItemCourseID());

        gradeItem = new GradeItem(" ", "Midterm", 16);
        assertEquals(" ", gradeItem.getItemCourseID());

        gradeItem = new GradeItem("", "Midterm", 16);
        assertEquals("", gradeItem.getItemCourseID());
    }

    @Test
    public void testGetItemName() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        assertEquals("Midterm", gradeItem.getItemName());

        gradeItem = new GradeItem("Comp 3430", " ", 16);
        assertEquals(" ", gradeItem.getItemName());

        gradeItem = new GradeItem("Comp 3430", "", 16);
        assertEquals("", gradeItem.getItemName());
    }

    @Test
    public void testGetItemWeight() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        assertEquals(16, gradeItem.getItemWeight(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 16.55);
        assertEquals(16.55, gradeItem.getItemWeight(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 0);
        assertEquals(0, gradeItem.getItemWeight(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", -1);
        assertEquals(-1, gradeItem.getItemWeight(), EPSILON);
    }

    @Test
    public void testGetItemGrade() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 0, 16);
        assertEquals(16, gradeItem.getItemGrade(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 0, 16.55);
        assertEquals(16.55, gradeItem.getItemGrade(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 12, 0);
        assertEquals(0, gradeItem.getItemGrade(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 0, -1);
        assertEquals(-1, gradeItem.getItemGrade(), EPSILON);
    }

    @Test
    public void testToString() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        assertEquals("Midterm", gradeItem.toString());

        gradeItem = new GradeItem("Comp 3430", " ", 16);
        assertEquals(" ", gradeItem.toString());

        gradeItem = new GradeItem("Comp 3430", "", 16);
        assertEquals("", gradeItem.toString());
    }

    @Test
    public void testSetItemName() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        gradeItem.setItemName("Final Exam");
        assertEquals("Final Exam", gradeItem.toString());

        gradeItem.setItemName(" ");
        assertEquals(" ", gradeItem.toString());

        gradeItem.setItemName("");
        assertEquals("", gradeItem.toString());
    }

    @Test
    public void testSetItemWeight() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);

        gradeItem.setItemWeight(0.00);
        assertEquals(0, gradeItem.getItemWeight(), EPSILON);

        gradeItem.setItemWeight(-1);
        assertEquals(-1, gradeItem.getItemWeight(), EPSILON);
    }

    @Test
    public void testSetItemGrade() {
        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);

        gradeItem.setItemGrade(0.00);
        assertEquals(0.00, gradeItem.getItemGrade(), EPSILON);

        gradeItem.setItemGrade(-1);
        assertEquals(-1, gradeItem.getItemGrade(), EPSILON);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 16, 12);

        gradeItem.setItemGrade(0.00);
        assertEquals(0.00, gradeItem.getItemGrade(), EPSILON);

        gradeItem.setItemGrade(-1);
        assertEquals(-1, gradeItem.getItemGrade(), EPSILON);
    }

    @Test
    public void testItemEquals() {
        GradeItem testItem = new GradeItem("Comp 3430", "Midterm", 16);

        gradeItem = new GradeItem("Comp 3430", "Midterm", 16);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("Comp 3430", " ", 16);
        testItem = new GradeItem("Comp 3430", " ", 16);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("Comp 3430", "", 16);
        testItem = new GradeItem("Comp 3430", "", 16);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem(" ", "Midterm", 16);
        testItem = new GradeItem(" ", "Midterm", 16);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("", "Midterm", 16);
        testItem = new GradeItem("", "Midterm", 16);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("Comp 3430", "Midterm", 16, 12);
        testItem = new GradeItem("Comp 3430", "Midterm", 16, 12);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("Comp 3430", " ", 16, 12);
        testItem = new GradeItem("Comp 3430", " ", 16, 12);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("Comp 3430", "", 16, 12);
        testItem = new GradeItem("Comp 3430", "", 16, 12);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem(" ", "Midterm", 16, 12);
        testItem = new GradeItem(" ", "Midterm", 16, 12);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));

        gradeItem = new GradeItem("", "Midterm", 16, 12);
        testItem = new GradeItem("", "Midterm", 16, 12);
        assertEquals(testItem, gradeItem);
        assertTrue(gradeItem.equals(testItem));
    }
}
