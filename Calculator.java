import java.util.ArrayList;

public class Calculator {
    public static double calculateAvg(ArrayList<Class> listClass){
        double average = 0.0;
        double sumGrades = 0.00;
        for (Class c : listClass){
            if (c.isAdvancedPlacement()){
                sumGrades += c.getGrade() * 1.1;
            } else if (c.isHonors()){
                sumGrades += c.getGrade() * 1.05;
            } else {
                sumGrades += c.getGrade();
            }
        }
        average = sumGrades / listClass.size();
        average = Math.round(average * 100.00)/100.00;
        return average;
    }
}