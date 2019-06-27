package comp3350.spa.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

import comp3350.spa.objects.GradeItem;
import comp3350.spa.business.CourseGradeItems;

public class CourseGradeItemsTest {
    private CourseGradeItems gradeItems;
    private static final double EPSILON = 1e-15;
    private GradeItem item = new GradeItem("Comp 3430", "Midterm", 5);
    private GradeItem item1 = new GradeItem("Comp 3430", "Final", 10);
    private GradeItem item2 = new GradeItem("Comp 1230", "Final", 15.25);
    private GradeItem item3 = new GradeItem("Comp 1230", "Assignment", 15.25);

    @Before
    public void setUp() {
        gradeItems = new CourseGradeItems();
    }

    @Test
    public void testAddGradeItem() {
        gradeItems.addGradeItem(item);
        assertEquals(true, gradeItems.gradeItemExists(item));

        gradeItems.addGradeItem(item1);
        assertEquals(true, gradeItems.gradeItemExists(item1));

        gradeItems.addGradeItem(item2);
        assertEquals(true, gradeItems.gradeItemExists(item2));

        gradeItems.addGradeItem(item3);
        assertEquals(true, gradeItems.gradeItemExists(item3));

        assertEquals(true, gradeItems.gradeItemExists(item));
        assertEquals(true, gradeItems.gradeItemExists(item1));
        assertEquals(true, gradeItems.gradeItemExists(item2));
        assertEquals(true, gradeItems.gradeItemExists(item3));
    }

    @Test
    public void testRemoveGradeItems() {
        gradeItems.removeGradeItem(item);
        gradeItems.addGradeItem(item);
        assertEquals(true, gradeItems.gradeItemExists(item));
        gradeItems.removeGradeItem(item);
        assertEquals(false, gradeItems.gradeItemExists(item));
        gradeItems.addGradeItem(item);
        gradeItems.addGradeItem(item1);
        assertEquals(true, gradeItems.gradeItemExists(item));
        assertEquals(true, gradeItems.gradeItemExists(item1));
        gradeItems.removeGradeItem(item);
        assertEquals(false, gradeItems.gradeItemExists(item));
        gradeItems.removeGradeItem(item1);
        assertEquals(false, gradeItems.gradeItemExists(item1));
    }

    @Test
    public void testSetGradeItemGrade() {
        ArrayList<GradeItem> testList;
        GradeItem testItem;

        gradeItems.addGradeItem(item);
        gradeItems.setGradeItemGrade(item, 12);
        testList = gradeItems.getAllGradeItems(item.getItemCourseID());
        testItem = testList.get(0);
        assertEquals(12, testItem.getItemGrade(), EPSILON);

        gradeItems.addGradeItem(item1);
        gradeItems.setGradeItemGrade(item1, 4);
        testList = gradeItems.getAllGradeItems(item.getItemCourseID());
        testItem = testList.get(1);
        assertEquals(4, testItem.getItemGrade(), EPSILON);

        testItem = testList.get(0);
        assertEquals(12, testItem.getItemGrade(), EPSILON);
    }

    @Test
    public void testSetGradeItemWeight() {
        ArrayList<GradeItem> testList;
        GradeItem testItem;

        gradeItems.addGradeItem(item);
        gradeItems.setGradeItemWeight(item, 12);
        testList = gradeItems.getAllGradeItems(item.getItemCourseID());
        testItem = testList.get(0);
        assertEquals(12, testItem.getItemWeight(), EPSILON);

        gradeItems.addGradeItem(item1);
        gradeItems.setGradeItemWeight(item1, 4);
        testList = gradeItems.getAllGradeItems(item.getItemCourseID());
        testItem = testList.get(1);
        assertEquals(4, testItem.getItemWeight(), EPSILON);

        testItem = testList.get(0);
        assertEquals(12, testItem.getItemWeight(), EPSILON);
    }

    @Test
    public void testRenameGradeItem() {
        ArrayList<GradeItem> testList;
        GradeItem testItem;

        gradeItems.addGradeItem(item);
        gradeItems.renameGradeItem(item, "A1");
        testList = gradeItems.getAllGradeItems("Comp 3430");
        testItem = testList.get(0);
        assertEquals("A1", testItem.getItemName());
        gradeItems.renameGradeItem(item, " ");
        testList = gradeItems.getAllGradeItems("Comp 3430");
        testItem = testList.get(0);
        assertEquals(" ", testItem.getItemName());
        gradeItems.renameGradeItem(item, "");
        testList = gradeItems.getAllGradeItems("Comp 3430");
        testItem = testList.get(0);
        assertEquals("", testItem.getItemName());

        gradeItems.addGradeItem(item2);
        gradeItems.renameGradeItem(item2, "A1");
        testList = gradeItems.getAllGradeItems("Comp 1230");
        testItem = testList.get(0);
        assertEquals("A1", testItem.getItemName());
        gradeItems.renameGradeItem(item2, " ");
        testList = gradeItems.getAllGradeItems("Comp 1230");
        testItem = testList.get(0);
        assertEquals(" ", testItem.getItemName());
        gradeItems.renameGradeItem(item2, "");
        testList = gradeItems.getAllGradeItems("Comp 1230");
        testItem = testList.get(0);
        assertEquals("", testItem.getItemName());
    }

    @Test
    public void testGradeItemExists() {
        assertEquals(false, gradeItems.gradeItemExists(item));

        gradeItems.addGradeItem(item);
        assertEquals(true, gradeItems.gradeItemExists(item));
        assertEquals(false, gradeItems.gradeItemExists(item2));
        gradeItems.removeGradeItem(item);
        assertEquals(false, gradeItems.gradeItemExists(item));

        gradeItems.addGradeItem(item);
        gradeItems.addGradeItem(item1);
        assertEquals(true, gradeItems.gradeItemExists(item));
        assertEquals(true, gradeItems.gradeItemExists(item1));
    }

    @Test
    public void testGetTotalWeight() {
        GradeItem testItem;
        GradeItem testItem1;
        GradeItem testItem2;

        assertEquals(0, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.addGradeItem(item);
        assertEquals(5, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.addGradeItem(item1);
        assertEquals(15, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        testItem = new GradeItem("Comp 3430", "A2", 15.25);
        gradeItems.addGradeItem(testItem);
        assertEquals(30.25, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        testItem1 = new GradeItem("Comp 3430", "A3", 30.25);
        gradeItems.addGradeItem(testItem1);
        assertEquals(60.5, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.removeGradeItem(testItem1);
        assertEquals(30.25, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.setGradeItemWeight(testItem, 100.75);
        assertEquals(115.75, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        testItem2 = new GradeItem("Comp 3430", "A3", 0);
        gradeItems.addGradeItem(testItem2);
        assertEquals(115.75, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.setGradeItemWeight(testItem2, 0);
        gradeItems.setGradeItemWeight(testItem, 0);
        gradeItems.setGradeItemWeight(item, 0);
        gradeItems.setGradeItemWeight(item1, 0);
        assertEquals(0, gradeItems.getTotalWeight("Comp 3430"), EPSILON);

        gradeItems.removeGradeItem(item);
        gradeItems.removeGradeItem(item1);
        gradeItems.removeGradeItem(testItem);
        gradeItems.removeGradeItem(testItem2);
        assertEquals(0, gradeItems.getTotalWeight("Comp 3430"), EPSILON);
    }

    @Test
    public void testGetTotalItemGrade() {
        gradeItems.addGradeItem(item);
        assertEquals(0, gradeItems.getTotalGrade("Comp 3430"), EPSILON);

        gradeItems.addGradeItem(item1);
        assertEquals(0, gradeItems.getTotalGrade("Comp 3430"), EPSILON);

        gradeItems.setGradeItemGrade(item, 100);
        assertEquals(100, gradeItems.getTotalGrade("Comp 3430"), EPSILON);

        gradeItems.setGradeItemGrade(item1, 50.12);
        assertEquals(150.12, gradeItems.getTotalGrade("Comp 3430"), EPSILON);

        gradeItems.addGradeItem(item2);
        assertEquals(150.12, gradeItems.getTotalGrade("Comp 3430"), EPSILON);
        assertEquals(0, gradeItems.getTotalGrade("Comp 1230"), EPSILON);

        gradeItems.setGradeItemGrade(item2, 75);
        assertEquals(150.12, gradeItems.getTotalGrade("Comp 3430"), EPSILON);
        assertEquals(75, gradeItems.getTotalGrade("Comp 1230"), EPSILON);

        gradeItems.removeGradeItem(item);
        gradeItems.removeGradeItem(item1);
        assertEquals(0, gradeItems.getTotalGrade("Comp 3430"), EPSILON);
        assertEquals(75, gradeItems.getTotalGrade("Comp 1230"), EPSILON);
    }

    @Test
    public void testGetAllGradeItems() {
        ArrayList<GradeItem> testList;
        testList = gradeItems.getAllGradeItems("Comp 3430");
        assertEquals(0, testList.size());

        gradeItems.addGradeItem(item);
        testList = gradeItems.getAllGradeItems("Comp 3430");
        assertEquals(item, testList.get(0));

        gradeItems.addGradeItem(item1);
        testList = gradeItems.getAllGradeItems("Comp 3430");
        assertEquals(item, testList.get(0));
        assertEquals(item1, testList.get(1));

        gradeItems.removeGradeItem(item);
        testList = gradeItems.getAllGradeItems("Comp 3430");
        assertEquals(item1, testList.get(0));
        assertEquals(1, testList.size());

        gradeItems.removeGradeItem(item1);
        testList = gradeItems.getAllGradeItems("Comp 3430");
        assertEquals(0, testList.size());
    }

    @Test
    public void testGetGradeItem() {
        assertEquals(null, gradeItems.getGradeItem("Comp 3430", "Midterm"));

        gradeItems.addGradeItem(item);
        assertEquals(item, gradeItems.getGradeItem("Comp 3430", "Midterm"));
        assertEquals(null, gradeItems.getGradeItem("Comp 3430", "Final"));

        gradeItems.addGradeItem(item1);
        assertEquals(item, gradeItems.getGradeItem("Comp 3430", "Midterm"));
        assertEquals(item1, gradeItems.getGradeItem("Comp 3430", "Final"));

        gradeItems.removeGradeItem(item);
        gradeItems.removeGradeItem(item1);
        assertEquals(null, gradeItems.getGradeItem("Comp 3430", "Midterm"));
        assertEquals(null, gradeItems.getGradeItem("Comp 3430", "Final"));

        gradeItems.addGradeItem(item);
        gradeItems.addGradeItem(item2);
        gradeItems.addGradeItem(item3);
        gradeItems.removeGradeItem(item);
        assertEquals(null, gradeItems.getGradeItem("Comp 3430", "Midterm"));
        assertEquals(item2, gradeItems.getGradeItem("Comp 1230", "Final"));
        assertEquals(item3, gradeItems.getGradeItem("Comp 1230", "Assignment"));
    }

    @Test
    public void testFutureGrades() {
        ArrayList<GradeItem> testList;
        GradeItem testItem = new GradeItem("Comp 3430", "Midterm1", 25, 20);
        GradeItem testItem1 = new GradeItem("Comp 3430", "Midterm2", 25, 15);
        GradeItem testItem2 = new GradeItem("Comp 3430", "Midterm", 40);
        GradeItem testItem3 = new GradeItem("Comp 3430", "Midterm", 10);

        gradeItems.addGradeItem(testItem);
        gradeItems.addGradeItem(testItem1);
        gradeItems.addGradeItem(testItem2);
        gradeItems.addGradeItem(testItem3);

        testList = gradeItems.futureGrades(75, "Comp 3430");
        assertEquals(2, testList.size());
        GradeItem newItem = testList.get(0);
        GradeItem newItem1 = testList.get(1);
        assertEquals(32, newItem.getItemGrade(), EPSILON);
        assertEquals(8, newItem1.getItemGrade(), EPSILON);

        GradeItem testItem4 = new GradeItem("Comp 4430", "Midterm1", 25, 20);
        GradeItem testItem5 = new GradeItem("Comp 4430", "Midterm2", 25, 15);
        GradeItem testItem6 = new GradeItem("Comp 4430", "Midterm", 35);
        GradeItem testItem7 = new GradeItem("Comp 4430", "Midterm", 15);
        gradeItems.addGradeItem(testItem4);
        gradeItems.addGradeItem(testItem5);
        gradeItems.addGradeItem(testItem6);
        gradeItems.addGradeItem(testItem7);

        testList = gradeItems.futureGrades(75, "Comp 4430");
        assertEquals(2, testList.size());
        newItem = testList.get(0);
        newItem1 = testList.get(1);
        assertEquals(28, newItem.getItemGrade(), EPSILON);
        assertEquals(12, newItem1.getItemGrade(), EPSILON);

        testList = gradeItems.futureGrades(65, "Comp 4430");
        assertEquals(2, testList.size());
        newItem = testList.get(0);
        newItem1 = testList.get(1);
        assertEquals(21, newItem.getItemGrade(), EPSILON);
        assertEquals(9, newItem1.getItemGrade(), EPSILON);
    }
}
