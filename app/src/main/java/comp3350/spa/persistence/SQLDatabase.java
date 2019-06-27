package comp3350.spa.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.objects.Course;
import comp3350.spa.objects.GradeItem;

public class SQLDatabase implements Database {

    private Statement st1;
    private Connection c1;
    private ResultSet rs2;

    private String dbName;
    private String dbType;

    private String cmdString;

    public SQLDatabase(String name) {
        dbName = name;
    }

    public void open(String dbPath) {
        System.out.println("Attempting to open...");
        String url;
        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();

        } catch (Exception e) {
            processSQLError(e);
        }

        System.out.println("Opened " +dbType +" database " + dbPath);
    }


    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();
        return result;
    }

    public void close() {
        try {
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();

        } catch (Exception e) {
            processSQLError(e);
        }

        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public void getEventSequential(List<CalendarEvent> eventResult) {
        CalendarEvent event;
        String name;
        int priorityID;
        int eventID;
        CalendarEvent.Priority priority;
        Date date;
        GregorianCalendar cal;

        try {
            cmdString = "Select * from events";
            rs2 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            processSQLError(e);
        }

        try {
            while (rs2.next()) {
                eventID = rs2.getInt("EVENTID");
                name = rs2.getString("NAME");
                priorityID = rs2.getInt("PRIORITY");
                date = rs2.getDate("TIME");
                cal = new GregorianCalendar();
                cal.setTime(date);

                switch (priorityID) {
                    case 1:
                        priority = CalendarEvent.Priority.LOW;
                        break;
                    case 2:
                        priority = CalendarEvent.Priority.MEDIUM;
                        break;
                    case 3:
                        priority = CalendarEvent.Priority.HIGH;
                        break;
                    default:
                        priority = CalendarEvent.Priority.NONE;
                        break;
                }

                event = new CalendarEvent(name, cal, priority);
                event.setEventId(eventID);
                eventResult.add(event);
            }

            rs2.close();

        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public void clearAllEvents() {
        try {
            cmdString = "TRUNCATE TABLE events";
            rs2 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public void insertEvent(CalendarEvent event){
        try {
            int priority = 0;
            switch (event.getPriority()) {
                case HIGH:
                    priority = 3;
                    break;
                case MEDIUM:
                    priority = 2;
                    break;
                case LOW:
                    priority = 1;
                    break;
            }

            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            String values = "'" + event.getEventName() + "'," + priority + ", '" + dt1.format(event.getEventDate().getTime()) + "'";
            cmdString = "Insert into EVENTS (NAME,PRIORITY,TIME)" + " Values(" + values +")";
            st1.executeUpdate(cmdString);

        } catch(Exception e) {
            processSQLError(e);
        }


    }

    public boolean containsEvent(CalendarEvent event) {
        try {
            if (event.getEventId() >= 0)
                cmdString = "SELECT * FROM events WHERE EVENTID = " + event.getEventId();

            else
                cmdString = "SELECT * FROM EVENTS WHERE NAME = '" + event.getEventName() + "'";

            rs2 = st1.executeQuery(cmdString);
            if (rs2.next())
                return true;

        } catch(Exception e) {
            processSQLError(e);
        }

        return false;
    }

    public void updateEvent(CalendarEvent event) {
        try {
            int priority = 0;
            switch (event.getPriority()) {
                case HIGH:
                    priority = 3;
                    break;
                case MEDIUM:
                    priority = 2;
                    break;
                case LOW:
                    priority = 1;
                    break;
            }

            String values = event.getEventId() + "'" + event.getEventName() + "'," + priority + ", '" + event.getEventDate().getTime() + "'";
            cmdString = "UPDATE events " + " Values(" + values +") WHERE EVENTID = " + event.getEventId();
            st1.executeUpdate(cmdString);

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void deleteEvent(CalendarEvent event) {
        try {
            cmdString = "DELETE FROM EVENTS WHERE EVENTID = " + event.getEventId();
            st1.executeUpdate(cmdString);

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void addCourse(Course newCourse) {
        try {
            String values = "('" + newCourse.getCourseName() + "')";
            cmdString = "INSERT INTO CourseList (" + "courseName" + ") " + "Values" + values + ";";
            st1.executeUpdate(cmdString);

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void deleteCourse(Course deleteCourse) {
        try {
            if (courseExists(deleteCourse)) {
                String values = "courseName = '" + deleteCourse.getCourseName() + "'";
                cmdString = "DELETE FROM CourseList WHERE " + values + ";";
                st1.executeUpdate(cmdString);
            }

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void getCourses(List<Course> eventResult) {
        Course course;
        try {
            cmdString = "SELECT * FROM CourseList;";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next()) {
                course = new Course(rs2.getString("courseName"));
                eventResult.add(course);
            }

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void renameCourse(Course oldCourse, String newName) {
        try {
            if (courseExists(oldCourse)) {
                String values = "courseName = '" +  oldCourse.getCourseName() + "'";
                cmdString = "UPDATE CourseList SET courseName = '" + newName + "' WHERE " + values;
                st1.executeUpdate(cmdString);
            }

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public boolean courseExists(Course course) {
        boolean result = false;
        try {
            String values = "courseName = '" + course.getCourseName() + "'";
            cmdString = "SELECT courseName FROM courseList WHERE " + values;
            rs2 = st1.executeQuery(cmdString);
            if (rs2.next())
                result = true;

        } catch(Exception e) {
            processSQLError(e);
        }

        return result;
    }

    public void addGradeItem(GradeItem newItem) {
        try {
            String values = "(" + newItem.getItemCourseID() + ", " + newItem.getItemName() + ", " + newItem.getItemWeight() + ", " + newItem.getItemGrade() + ")";
            cmdString = "INSERT INTO CourseGradeItems Values " + values + ";";

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void removeGradeItem(GradeItem oldItem) {
        try {
            String values = "courseID = '" + oldItem.getItemCourseID() + "', itemName = '" + oldItem.getItemName() + "';";
            cmdString = "DELETE FROM CourseGradeItems WHERE " + values + ";";

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void renameGradeItem(GradeItem item, String newName) {
        try {
            String values = "courseID = '" + item.getItemCourseID() + "', itemName = '" + item.getItemName() + "';";
            cmdString = "UPDATE CourseGradeItems SET itemName = '" + newName + "' WHERE " + values + ";";

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public double getTotalWeight(String courseID) {
        int total = 0;
        try {
            cmdString = "SELECT itemWeight FROM CourseGradeItems WHERE courseID = '" + courseID + "';";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
                total += rs2.getDouble(0);

        } catch(Exception e) {
            processSQLError(e);
        }

        return total;
    }

    public double getTotalGrade(String courseID) {
        int total = 0;
        double number = 0;
        try {
            cmdString = "SELECT itemGrade FROM CourseGradeItems WHERE courseID = '" + courseID + "';";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
                number = rs2.getDouble(0);
                if(number > -1) {
                    total += number;
                }

        } catch(Exception e) {
            processSQLError(e);
        }

        return total;
    }

    public void setGradeItemGrade(GradeItem item, double grade) {
        try {
            String values = "courseID = '" + item.getItemCourseID() + "', itemName = '" + item.getItemName() + "';";
            cmdString = "UPDATE CourseGradeItems SET itemGrade = '" + grade + "' WHERE " + values + ";";

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public void setGradeItemWeight(GradeItem item, double weight) {
        try {
            String values = "courseID = '" + item.getItemCourseID() + "', itemName = '" + item.getItemName() + "';";
            cmdString = "UPDATE CourseGradeItems SET itemWeight = '" + weight + "' WHERE " + values + ";";

        } catch(Exception e) {
            processSQLError(e);
        }
    }

    public ArrayList<GradeItem> getGradeItems(String courseID) {
        ArrayList<GradeItem> gradeItemList = new ArrayList<>();
        GradeItem item;

        try {
            cmdString = "SELECT * FROM CourseGradeItems WHERE courseID = '" + courseID + "';";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next()) {
                item = new GradeItem(courseID, rs2.getString(1), rs2.getDouble(2), rs2.getDouble(3));
                gradeItemList.add(item);
            }

        } catch(Exception e) {
            processSQLError(e);
        }

        return gradeItemList;
    }

    public boolean gradeItemExists(GradeItem item) {
        boolean result = false;
        try {
            String values = "courseID = '" + item.getItemCourseID() + "', itemName = '" + item.getItemName() + "';";
            cmdString = "SELECT * FROM CourseGradeItems WHERE " + values +";";
            rs2 = st1.executeQuery(cmdString);
            if(rs2.next())
                result = true;

        } catch(Exception e) {
            processSQLError(e);
        }

        return result;
    }

    public GradeItem getGradeItem(String courseID, String itemName) {
        GradeItem item = null;
        try {
            String values = "courseID = '" + courseID + "', itemName = '" + itemName + "';";
            cmdString = "SELECT * FROM CourseGradeItems WHERE " + values +";";
            rs2 = st1.executeQuery(cmdString);
            if(rs2.next())
                item = new GradeItem(rs2.getString(0), rs2.getString(1), rs2.getDouble(2), rs2.getDouble(3));

        } catch(Exception e) {
            processSQLError(e);
        }

        return item;
    }
}
