import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Person {
    private String name;
    private double overallGrade;
    private ArrayList<Class> classList;
    private ArrayList<String> classNames;

    public Person(String name){
        this.name = name;
        overallGrade = 0.0;
        classList = new ArrayList<Class>();
        classNames = new ArrayList<String>();
    }

    public void addClass(Class c){
        classList.add(c);
        classNames.add(c.getClassName());
        overallGrade = Calculator.calculateAvg(classList);
    }

    public void removeClass(int num){
        int index = num - 1;
        if (classList.size() == 0){
            System.out.println("You have no classes. Add one to get started.");
        } else {
            if (classList.size() > index && index >= 0){
                classNames.remove(num - 1);
                System.out.println("You successfully removed " + classList.remove(num - 1).getClassName() + "!");
                overallGrade = Calculator.calculateAvg(classList);
            } else {
                System.out.println("Error: that class does not exist.");
            }
        }
    }

    public void listClassInfo(){
        if (classList.size() == 0){
            System.out.println("You have no classes added.");
        }
        int counter = 0;
        for (Class c : classList){
            counter++;
            System.out.println(counter + ": " + c.getClassName() + " - " + c.getGrade() + "%");
        }
    }

    public ArrayList<Class> getClassList(){
        return classList;
    }

    public ArrayList<String> getClassNames(){
        return classNames;
    }

    public String getName(){
        return name;
    }

    public double getOverallGrade(){
        return overallGrade;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setOverallGrade(double grade){
        overallGrade = grade;
    }

    public void save(){
        try {
            File f = new File("src/info.data");
            f.createNewFile();
            FileWriter fw = new FileWriter("src/info.data");
            fw.write(name + "|" + overallGrade + "\n");
            for (int i = 0; i < classList.size(); i++){
                fw.write(classList.get(i).getClassName() + "\n");
                fw.write(classList.get(i).getGrade() + "\n");
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}
