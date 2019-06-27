package comp3350.spa.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests {
    private static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(EventSchedulingAcceptanceTest.class);
        suite.addTestSuite(CourseGradeItemsAcceptanceTest.class);
        suite.addTestSuite(CourseListAcceptanceTest.class);
        return suite;
    }
}