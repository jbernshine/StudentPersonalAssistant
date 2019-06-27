package comp3350.spa.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.spa.R;
import comp3350.spa.business.CourseList;
import comp3350.spa.objects.Course;

public class CourseListActivity extends Activity {

    private CourseList courseBusiness;
    private ArrayList<Course> courseList;
    private ArrayAdapter<Course> courseAdapter;
    private Course selectedCourse = null;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseBusiness = new CourseList();
        courseList = new ArrayList<>();
        courseBusiness.getCourses(courseList);
        setContentView(R.layout.activity_course_list);
        buildList();
    }

    private void buildList() {
        list = (ListView)findViewById(R.id.listCourses);
        final EditText textInput = (EditText)findViewById(R.id.editCourseName);
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList);
        list.setAdapter(courseAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (Course)list.getItemAtPosition(position);
                String itemValue = selectedCourse.getCourseName();
                textInput.setText(itemValue, TextView.BufferType.EDITABLE);
            }
        });
    }

    public void btnSaveOnClick(View v) {
        EditText editName = (EditText)findViewById(R.id.editCourseName);
        if (selectedCourse != null) {
            String oldText = selectedCourse.getCourseName();
            courseBusiness.renameCourse(selectedCourse, editName.getText().toString());
            selectedCourse.setCourseName(editName.getText().toString());
            String result = validateCourseData(selectedCourse);
            if (result != null) {
                Messages.warning(this, result);
                selectedCourse.setCourseName(oldText);

            } else
                courseAdapter.notifyDataSetChanged();

        } else
            Messages.warning(this, "You must select a course to update.");
    }

    public void btnAddOnClick(View v) {
        Course course = createCourseFromEditText();
        String result = validateCourseData(course);

        if (null == result) {
            if (courseBusiness.addCourse(course)) {
                selectedCourse = course;
                courseBusiness.getCourses(courseList);
                courseAdapter.notifyDataSetChanged();
            } else
                Messages.warning(this, "Course already exists.");

        } else
            Messages.warning(this, result);
    }

    public void btnDeleteOnClick(View v) {
        Course course = createCourseFromEditText();
        if (courseBusiness.deleteCourse(course)) {
            courseBusiness.getCourses(courseList);
            courseAdapter.notifyDataSetChanged();

        } else
            Messages.warning(this, "No such course exists.");
    }

    public void btnGradeOnClick(View v) {
        EditText editID = (EditText)findViewById(R.id.editCourseName);
        String courseName = editID.getText().toString();

        Intent csIntent = new Intent(CourseListActivity.this, GradeActivity.class);
        Bundle b = new Bundle();
        b.putString("courseName", courseName);
        csIntent.putExtras(b);
        CourseListActivity.this.startActivity(csIntent);
    }

    private Course createCourseFromEditText() {
        EditText editName = (EditText)findViewById(R.id.editCourseName);
        return new Course(editName.getText().toString());
    }

    private String validateCourseData(Course course) {
        String result = null;
        if (course.getCourseName().length() == 0)
            result = "Course name required";

        return result;
    }
}
