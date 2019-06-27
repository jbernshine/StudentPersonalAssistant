package comp3350.spa.acceptance;

import comp3350.spa.R;
import comp3350.spa.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

public class CourseListAcceptanceTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public CourseListAcceptanceTest() { super(HomeActivity.class); }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testCourseNavigation() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");
    }

    public void testAddCourse() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.editCourseName));
        solo.enterText(0, "COMP 4020");
        solo.clickOnView(solo.getView(R.id.btnAdd));
        solo.searchText("COMP 4020");

        solo.clickInList(3);
        solo.clickOnView(solo.getView(R.id.btnDelete));
    }

    public void testAddExistingCourse() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.editCourseName));
        solo.clearEditText(0);
        solo.enterText(0, "Comp 3190");
        solo.clickOnView(solo.getView(R.id.btnAdd));
        solo.searchText("Course already exists.");
    }

    public void testUpdateCourse() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickInList(1);
        solo.clearEditText(0);
        solo.enterText(0, "Updated Course");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.clickInList(1);
        solo.searchText("Updated Course");

        solo.clearEditText(0);
        solo.enterText(0, "Comp 3430");
        solo.clickOnView(solo.getView(R.id.btnSave));
    }

    public void testUpdateCourseWithoutClicking() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clearEditText(0);
        solo.enterText(0, "This course doesn't exist");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.searchText("You must select a course to update.");
    }

    public void testDeleteCourse() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickInList(1);
        solo.clickOnView(solo.getView(R.id.btnDelete));

        solo.clickInList(1);
        solo.searchText("Comp 3190");

        solo.clickOnView(solo.getView(R.id.btnDelete));
        solo.clearEditText(0);
        solo.enterText(0, "Comp 3430");
        solo.clickOnView(solo.getView(R.id.btnAdd));
        solo.clearEditText(0);
        solo.enterText(0, "Comp 3190");
        solo.clickOnView(solo.getView(R.id.btnAdd));

    }

    public void testDeleteCourseByTyping() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clearEditText(0);
        solo.enterText(0, "Comp 3190");
        solo.clickOnView(solo.getView(R.id.btnDelete));

        solo.clickInList(1);
        solo.searchText("Comp 3430");

        solo.clearEditText(0);
        solo.enterText(0, "Comp 3190");
        solo.clickOnView(solo.getView(R.id.btnAdd));
    }

    public void testDeleteNonExistentCourse() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clearEditText(0);
        solo.enterText(0, "Did course doesn't exist");
        solo.clickOnView(solo.getView(R.id.btnDelete));

        solo.searchText("No such course exists.");

    }
}
