public class Class {
    private String className;
    private boolean advancedPlacement;
    private boolean honors;
    private double grade;

    public Class(String className, double grade){
        this.className = className;
        this.advancedPlacement = false;
        this.honors = false;
        if (className.toLowerCase().indexOf("honors") == 0){
            this.honors = true;
        } else if (className.toLowerCase().indexOf("ap") == 0){
            this.advancedPlacement = true;
        }
        this.grade = grade;
    }

    public String getClassName(){
        return className;
    }

    public boolean isAdvancedPlacement() {
        return advancedPlacement;
    }

    public boolean isHonors(){
        return honors;
    }

    public double getGrade() {
        return grade;
    }
}