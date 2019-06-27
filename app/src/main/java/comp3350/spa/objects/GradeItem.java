package comp3350.spa.objects;

public class GradeItem {

    private String courseID;
    private String name;
    private double weight;
    private double grade;

    public GradeItem(String courseName, String itemName, double itemWeight) {
        courseID = courseName;
        name = itemName;
        weight = itemWeight;
        grade = -1;
    }

    public GradeItem(String courseName ,String itemName, double itemWeight, double itemGrade) {
        courseID = courseName;
        name = itemName;
        weight = itemWeight;
        grade = itemGrade;
    }

    public String getItemCourseID() {
        return courseID;
    }

    public String getItemName() {
        return name;
    }

    public double getItemWeight() {
        return weight;
    }

    public double getItemGrade() {
        return grade;
    }

    public void setItemGrade(double newGrade) {
        grade = newGrade;
    }

    public void setItemName(String newItemName) {
        name = newItemName;
    }

    public void setItemWeight(double newWeight) {
        weight = newWeight;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        GradeItem grade;

        if (object instanceof GradeItem) {
            grade = (GradeItem) object;
            if (grade.name == null && name == null || grade.name.equals(name) && grade.courseID.equals(courseID))
                result = true;
        }

        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
