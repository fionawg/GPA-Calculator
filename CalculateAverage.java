import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculateAverage {
    public static void main(String[] args) {
        try {
            File f = new File("src/info.data");
            Scanner s = new Scanner(f);
            int line = 1;
            String className = "";
            double grade;
            String str = s.nextLine();
            Person p = new Person(str.substring(0, str.indexOf("|")));
            p.setOverallGrade(Double.parseDouble(str.substring(str.indexOf("|") + 1)));
            line++;
            while (s.hasNextLine()){
                if (line % 2 == 0){
                    className = s.nextLine();
                } else {
                    grade = Double.parseDouble(s.nextLine());
                    p.addClass(new Class(className, grade));
                }
                line++;
            }
            s.close();
            Scanner scan = new Scanner(System.in);
            System.out.println("Hello " + p.getName() + ". Welcome back!");
            System.out.print("Would you like to change your name? [yes or no] ");
            if (scan.nextLine().equals("yes")){
                System.out.print("What would you like to change your name to? ");
                p.setName(scan.nextLine());
                p.save();
            } else {
                System.out.println("Okay! Let's continue then " + p.getName() + ".");
            }
            System.out.println("\nHere are your classes so far, " + p.getName() + ". Your weighted GPA is " + p.getOverallGrade() + ".");
            p.listClassInfo();
            System.out.print("\nTo add classes type \"a\", to remove type \"r\", to check your GPA type \"c\" and to exit type any key. ");
            String choice = scan.nextLine();
            while (choice.equals("A") || choice.equals("R") || choice.equals("a") || choice.equals("r") || choice.equals("C") || choice.equals("c") || choice.equals("L") || choice.equals("l")){
                if (choice.equals("A") || choice.equals("a")){
                    choiceA(p);
                }
                if (choice.equals("R") || choice.equals("r")){
                    choiceR(p);
                }
                if (choice.equals("C") || choice.equals("c")){
                    choiceC(p);
                }
                if (choice.equals("L") || choice.equals("l")){
                    choiceL(p);
                }
                System.out.println();
                System.out.print("Add [a], Remove [r], Check GPA [c], List Classes [l], or Exit [any key]: ");
                choice = scan.nextLine();
            }
            System.out.println();
            System.out.println("You have a GPA of " + p.getOverallGrade() + ".");
            p.listClassInfo();
            scan.close();
        } catch (FileNotFoundException e){
            String className;
            double grade;
            Scanner scan = new Scanner(System.in);
            System.out.print("Welcome to the GPA calculator! What's your name? ");
            Person p = new Person(scan.nextLine());
            System.out.println("If the class is an AP class or honors, type AP or Honors in the beginning of the class name.");
            System.out.println("AP classes have a 10% boost and honors have a 5% boost.\n");
            System.out.print("To add a class type \"a\". ");
            String choice = scan.nextLine();
            while (choice.equals("A") || choice.equals("R") || choice.equals("a") || choice.equals("r") || choice.equals("C") || choice.equals("c") || choice.equals("L") || choice.equals("l")){
                if (choice.equals("A") || choice.equals("a")){
                    choiceA(p);
                }
                if (choice.equals("R") || choice.equals("r")){
                    choiceR(p);
                }
                if (choice.equals("C") || choice.equals("c")){
                    choiceC(p);
                }
                if (choice.equals("L") || choice.equals("l")){
                    choiceL(p);
                }
                System.out.println();
                System.out.print("Add [a], Remove [r], Check GPA [c], List Classes [l], or Exit [any key]: ");
                choice = scan.nextLine();
            }
            System.out.println("\nYou have a GPA of " + p.getOverallGrade() + ".");
            p.listClassInfo();
            scan.close();
        }
    }
    public static void choiceA(Person p){
        String className = "";
        String grade = "";
        double gradee = 0.0;
        Scanner scan = new Scanner(System.in);
        System.out.print("What's the name of the class? ");
        className = scan.nextLine();
        if (p.getClassNames().contains(className)){
            System.out.println(className + " already exists.");
        } else {
            System.out.print("What's your grade in " + className + "? ");
            grade = scan.nextLine();
            grade = grade.replaceAll("[^0-9]", "");
            while (grade.equals("")){
                System.out.print("That's invalid. Enter another grade for " + className + ": ");
                grade = scan.nextLine();
                grade = grade.replaceAll("[^0-9]", "");
            }
            gradee = Double.parseDouble(grade);
            while (gradee < 0 || gradee > 110){
                System.out.print("That's invalid. Enter another grade for " + className + ": ");
                grade = scan.nextLine();
                grade = grade.replaceAll("[^0-9]", "");
                while (grade.equals("")){
                    System.out.print("That's invalid. Enter another grade for " + className + ": ");
                    grade = scan.nextLine();
                    grade = grade.replaceAll("[^0-9]", "");
                }
                gradee = Double.parseDouble(grade);
            }
            p.addClass(new Class(className, Double.parseDouble(grade)));
            System.out.println("You successfully added " + className + "!");
        }
        p.save();
    }
    public static void choiceR(Person p){
        Scanner scan = new Scanner(System.in);
        String removeClass = "";
        if (p.getClassList().size() != 0){
            p.listClassInfo();
            System.out.print("What class do you want to remove? (enter num) ");
            removeClass = scan.nextLine();
            removeClass = removeClass.replaceAll("[^0-9]", "");
        }
        if (p.getClassList().size() == 0){
            System.out.println("You have no classes. Add one to get started.");
        } else if (removeClass.equals("")){
            System.out.println("That's not a class, try again.");
        } else {
            p.removeClass(Integer.parseInt(removeClass));
        }
        p.save();
    }
    public static void choiceC(Person p){
        System.out.println("You have a GPA of " + p.getOverallGrade() + ".");
    }
    public static void choiceL(Person p){
        p.listClassInfo();
    }
}