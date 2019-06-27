package comp3350.spa.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.spa.tests.business.CourseListUnitTest;
import comp3350.spa.tests.business.EventsUnitTest;
import comp3350.spa.tests.business.CourseGradeItemsTest;
import comp3350.spa.tests.objects.CourseUnitTest;
import comp3350.spa.tests.objects.EventUnitTest;
import comp3350.spa.tests.objects.GradeItemUnitTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        EventUnitTest.class,
        CourseUnitTest.class,
        EventsUnitTest.class,
        CourseListUnitTest.class,
        GradeItemUnitTest.class,
        CourseGradeItemsTest.class
})

public class UnitTests {

}
