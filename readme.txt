The application package provides the Main class which has the main method, and CommonAccess, which other classes get through the Database interface.


The business package contains the business logic for the three areas of the app: Courses handles logic for Course objects, CourseGradeItems for GradeItem objects, and Events for CalendarEvent objects. All interactions with the database goes through this package.


The object package contains the information for the specific instance of a Course, GradeItem, or CalendarEvent object.


The persistence package handles our Database interface, which is automatically instantiated with SQLDatabase, but can be injected with StubDatabase for testing purposes.


The presentation package handles the classes for the activities and user messages. There is a class for each activity (CalendarActivity, CalendarAddFragment, CourseListActivity, GradeActivity, HomeActivity) as well as a class to handle user messages (Messages)


When the application opens, there are two buttons for the course list and the calendar. Just click the button to go to the activity. Should be pretty straightforward.


In the course list, you can add/remove/modify a course name. Selecting an existing course fills out the field of the course name, which you can type. You can modify and delete the course from here. If you type in a different course name and click add, you create a new course. If you add a course name that already exists or delete a course that doesn’t exist, an error message will appear. If you update a course, you must select a course for it to update.


The GradeActivity can be accessed from the CourseListActivity by selecting a course and selecting “Grade”


The calendar shows a calendar that you can click with pre-entered events. Neither this or the course list are fully implemented yet. Only the events preprogrammed can be selected, and you can’t add, delete, or modify them. 


The log is in a spreadsheet format in a pdf format and csv.


Iteration 3 has better testing than ever before:


There are three different kinds of tests. The Unit Tests (formerly AllTests.java) resides in the test folder and runs CourseGradeItemsTest, CourseListUnitTests, EventsUnitTests, CourseUnitTest, EventUnitTest, and GradeItemUnitTest. They all run from the suite called UnitTests.java


The integration tests also belong in the test folder. It is run from the suite called IntegrationTests.java, where BusinessPersistenceSeam is run from.


The acceptance tests are in the androidTest folder. The RunAcceptanceTest.java file runs the test suite with the Android device or emulator. It runs the CourseGradeItemsAcceptanceTest, CourseListAcceptanceTest, and the EventSchedulingAcceptanceTest.