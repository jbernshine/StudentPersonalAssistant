package comp3350.spa.acceptance;

import com.robotium.solo.Solo;

import comp3350.spa.R;
import comp3350.spa.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class CourseGradeItemsAcceptanceTest extends ActivityInstrumentationTestCase2<HomeActivity>{

    private Solo solo;

    public CourseGradeItemsAcceptanceTest() {
        super(HomeActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testGradeNavigation() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");
    }

    public void testAddInvalidGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.clickOnView(solo.getView(R.id.btnAddItem));
        solo.searchText("Grade item name required");
    }

    public void testAddDuplicateGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.enterText(0, "Test 1");
        solo.enterText(1, "30");
        solo.enterText(2, "15");
        solo.clickOnView(solo.getView(R.id.btnAddItem));
        solo.clickOnView(solo.getView(R.id.btnAddItem));
        solo.searchText("Grade item already exists.");
    }

    public void testInvalidDeleteGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.clickOnView(solo.getView(R.id.btnDeleteItem));
        solo.searchText("No such course exists.");
    }

    public void testDeleteGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.enterText(0, "Test 1");
        solo.enterText(1, "30");
        solo.enterText(2, "15");
        solo.clickOnView(solo.getView(R.id.btnAddItem));
        solo.clickOnView(solo.getView(R.id.btnDeleteItem));
        solo.clickOnView(solo.getView(R.id.btnDeleteItem));
        solo.searchText("No such course exists.");
    }

    public void testInvalidUpdateGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.enterText(0, "Test 1");
        solo.enterText(1, "30");
        solo.enterText(2, "15");
        solo.clickOnView(solo.getView(R.id.btnAddItem));

        solo.clearEditText(1);
        solo.clickOnView(solo.getView(R.id.btnUpdateItem));
        solo.searchText("Grade item weight required");
    }

    public void testUpdateGrade() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.buttonCourses));
        solo.assertCurrentActivity("Expected activity CourseListActivity", "CourseListActivity");

        solo.waitForActivity("CourseListActivity");
        solo.clickOnView(solo.getView(R.id.btnGrade));
        solo.assertCurrentActivity("Expected activity GradeActivity", "GradeActivity");

        solo.waitForActivity("GradeActivity");
        solo.enterText(0, "Test 1");
        solo.enterText(1, "30");
        solo.enterText(2, "15");
        solo.clickOnView(solo.getView(R.id.btnAddItem));

        solo.clearEditText(0);
        solo.enterText(0, "Test 2");
        solo.clickOnView(solo.getView(R.id.btnUpdateItem));
        solo.clickOnView(solo.getView(R.id.btnAddItem));
        solo.searchText("Grade item already exists.");
    }
}
