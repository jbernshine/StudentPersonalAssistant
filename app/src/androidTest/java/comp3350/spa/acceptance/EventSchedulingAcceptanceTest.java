package comp3350.spa.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.spa.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class EventSchedulingAcceptanceTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public EventSchedulingAcceptanceTest() {
        super(HomeActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testAddEventToPopulatedEvent() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(comp3350.spa.R.id.button));
        solo.assertCurrentActivity("Expected activity CalendarActivity", "CalendarActivity");

        solo.clickOnText("15");
        Assert.assertTrue(solo.searchText("Assignment 1"));
        solo.clickOnButton(0);
        solo.clickOnMenuItem("Add");
        solo.enterText(0, "Guitar Lesson");
        solo.clickOnButton("Add");

        solo.goBack();

        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Calendar");
        solo.assertCurrentActivity("Expected activity CalendarActivity", "CalendarActivity");

        solo.clickOnText("15");
        Assert.assertTrue(solo.searchText("Assignment 1") && solo.searchText("Guitar Lesson"));
        solo.clickOnText("Guitar Lesson");
        solo.clickOnText("Yes");
        solo.clickOnButton(0);
        solo.sleep(2000);
        Assert.assertFalse(solo.searchText("Guitar Lesson"));
    }

    public void testAddEventToUnpopulatedEvent() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Calendar");
        solo.assertCurrentActivity("Expected activity CalendarActivity", "CalendarActivity");

        solo.clickOnText("15");
        solo.clickOnButton(0);
        solo.clickOnMenuItem("Add");
        solo.enterText(0, "Guitar Lesson");
        solo.clickOnText("Medium");
        solo.clickOnText("High");
        solo.clickOnButton("Add");

        solo.goBack();

        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Calendar");
        solo.assertCurrentActivity("Expected activity CalendarActivity", "CalendarActivity");

        solo.clickOnText("15");
        Assert.assertTrue(solo.searchText("Guitar Lesson"));
        solo.clickOnText("Guitar Lesson");
        solo.clickOnButton("Yes");
        solo.sleep(2000);
        Assert.assertFalse(solo.searchText("Guitar Lesson"));
    }
}
