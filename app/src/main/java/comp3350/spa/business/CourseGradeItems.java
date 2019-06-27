package comp3350.spa.business;

import java.util.ArrayList;

import comp3350.spa.objects.GradeItem;
import comp3350.spa.persistence.Database;
import comp3350.spa.persistence.StubDatabase;

public class CourseGradeItems {

    private Database db;

    public CourseGradeItems() {
        db = new StubDatabase();
    }

    public void addGradeItem(GradeItem newItem) {
        db.addGradeItem(newItem);
    }

    public boolean gradeItemExists(GradeItem item) {
        return db.gradeItemExists(item);
    }

    public void removeGradeItem(GradeItem oldItem) {
        db.removeGradeItem(oldItem);
    }

    public void setGradeItemGrade(GradeItem item, double grade) {
        db.setGradeItemGrade(item, grade);
    }

    public void setGradeItemWeight(GradeItem item, double weight) {
        db.setGradeItemWeight(item, weight);
    }

    public void renameGradeItem(GradeItem item, String newName) {
        db.renameGradeItem(item, newName);
    }

    public double getTotalWeight(String courseID) {
        return db.getTotalWeight(courseID);
    }

    public double getTotalGrade(String courseID) {
        return db.getTotalGrade(courseID);
    }

    public GradeItem getGradeItem(String courseID, String itemName) {
        return db.getGradeItem(courseID, itemName);
    }

    public ArrayList<GradeItem> getAllGradeItems(String courseID) {
        return db.getGradeItems(courseID);
    }

    public GradeItem copy(GradeItem oldItem) {
        GradeItem newItem;
        newItem = new GradeItem(oldItem.getItemCourseID(), oldItem.getItemName(), oldItem.getItemWeight());
        return newItem;
    }

    public ArrayList<GradeItem> futureGrades(double goalGrad, String courseID) {
        GradeItem item;
        GradeItem nonGradedItem;
        double itemWeight;
        double multi;
        double notGraded;
        double notGradedWeight = -1;
        double totalGrade = getTotalGrade(courseID);
        ArrayList<GradeItem> neededGrades = new ArrayList<>();
        ArrayList<GradeItem> allGradeItems = getAllGradeItems(courseID);

        notGraded = goalGrad - totalGrade;
        for (int i = 0; i < allGradeItems.size(); i++) {
            item = allGradeItems.get(i);
            if (item.getItemGrade() == -1) {
                nonGradedItem = copy(item);
                neededGrades.add(nonGradedItem);
                notGradedWeight += nonGradedItem.getItemWeight();
            }
        }

        multi = notGraded/notGradedWeight;
        for (int i = 0; i < neededGrades.size(); i++) {
            itemWeight = neededGrades.get(i).getItemWeight();
            neededGrades.get(i).setItemGrade(Math.floor(multi * itemWeight));
        }

        return neededGrades;
    }
}
