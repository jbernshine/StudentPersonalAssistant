package comp3350.spa.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.spa.tests.integration.BusinessPersistenceSeam;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        BusinessPersistenceSeam.class
})

public class IntegrationTests {

}
