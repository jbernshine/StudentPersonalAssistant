package comp3350.spa.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import comp3350.spa.R;
import comp3350.spa.business.CourseGradeItems;
import comp3350.spa.objects.GradeItem;

public class GradeActivity extends Activity {

    private CourseGradeItems gradeBusiness;
    private ArrayList<GradeItem> gradeItemList;
    private ArrayAdapter<GradeItem> gradeAdapter;
    private GradeItem selectedGrade = null;
    private String courseName;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        courseName = b.getString("courseName");

        gradeBusiness = new CourseGradeItems();
        gradeItemList = new ArrayList<>();
        gradeItemList = gradeBusiness.getAllGradeItems(courseName);

        setContentView(R.layout.activity_grade);
        buildList();
    }

    private void buildList() {
        list = (ListView)findViewById(R.id.listGrades);
        final EditText textInput = (EditText)findViewById(R.id.editItemName);

        gradeAdapter = new ArrayAdapter<GradeItem>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, gradeItemList);

        list.setAdapter(gradeAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                selectedGrade = (GradeItem) list.getItemAtPosition(position);
                String itemValue = selectedGrade.getItemCourseID();
                textInput.setText(itemValue, TextView.BufferType.EDITABLE);

            }
        });
    }

    public void btnAddItemOnClick(View v) {
        String result;

        GradeItem grade = createGradeFromEditText();
        result = validateGradeData(grade);
        if(null == result) {
            if(!gradeBusiness.gradeItemExists(grade)) {
                gradeBusiness.addGradeItem(grade);
                selectedGrade = grade;
                gradeItemList = gradeBusiness.getAllGradeItems(courseName);
                gradeAdapter.notifyDataSetChanged();
            } else {
                Messages.warning(this, "Grade item already exists.");
            }
        } else {
            Messages.warning(this, result);
        }
    }

    public void btnUpdateItemOnClick(View v) {
        String result;
        double weight;
        double grade;

        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editWeight = (EditText)findViewById(R.id.editItemWeight);
        EditText editGrade = (EditText)findViewById(R.id.editItemGrade);

        if(selectedGrade != null) {
            String oldName = selectedGrade.getItemName();
            Double oldWeight = selectedGrade.getItemWeight();
            Double oldGrade = selectedGrade.getItemGrade();

            if(0 == editWeight.getText().toString().length()) {
                weight = -1;
            } else {
                weight = Double.parseDouble(editWeight.getText().toString());
            }
            if(0 == editGrade.getText().toString().length()) {
                grade = -1;
            } else {
                grade = Double.parseDouble(editGrade.getText().toString());
            }

            gradeBusiness.renameGradeItem(selectedGrade, editName.getText().toString());
            gradeBusiness.setGradeItemWeight(selectedGrade, weight);
            gradeBusiness.setGradeItemGrade(selectedGrade, grade);
            result = validateGradeData(selectedGrade);
            if(null != result) {
                Messages.warning(this, result);
                selectedGrade.setItemName(oldName);
                selectedGrade.setItemWeight(oldWeight);
                selectedGrade.setItemGrade(oldGrade);
            } else {
                gradeAdapter.notifyDataSetChanged();
            }
        } else
            Messages.warning(this, "You must select a grade item to update.");
    }

    public void btnDeleteItemOnClick(View v) {
        GradeItem removeGrade = createGradeFromEditText();
        if(gradeBusiness.gradeItemExists(removeGrade)){
            gradeBusiness.removeGradeItem(removeGrade);
            gradeItemList = gradeBusiness.getAllGradeItems(courseName);
            gradeAdapter.notifyDataSetChanged();
        } else {
            Messages.warning(this, "No such grade exists.");
        }

    }

    private GradeItem createGradeFromEditText() {
        GradeItem grade;
        double weight;
        double testGrade;

        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editWeight = (EditText)findViewById(R.id.editItemWeight);
        EditText editGrade = (EditText)findViewById(R.id.editItemGrade);

        if(0 == editWeight.getText().toString().length()) {
            weight = -1;
        } else {
            weight = Double.parseDouble(editWeight.getText().toString());
        }
        if(0 == editGrade.getText().toString().length()) {
            testGrade = -1;
        } else {
            testGrade = Double.parseDouble(editGrade.getText().toString());
        }

        grade = new GradeItem(courseName, editName.getText().toString(), weight, testGrade);

        return grade;
    }

    private String validateGradeData(GradeItem grade) {
        if (grade.getItemName().length() == 0)
            return "Grade item name required";
        if (grade.getItemWeight() == -1)
            return "Grade item weight required";

        return null;
    }
}
