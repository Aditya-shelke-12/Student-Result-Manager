public class Student {
    private int rollNo;
    private String name;
    private int[] marks = new int[5];
    private int total = 0;
    private float percentage;
    private char grade;

    public Student(String name, int rollNo, int[] marks){
        this.name = name;
        this.rollNo = rollNo + 1;
        this.marks = marks.clone();

        calculateAll();
    }


//==============================================
//Getters and Setters 
//==============================================


public String getName(){
    return name;
}

public int getRoll(){
    return rollNo;
}

public int[] getMarks(){
    return marks;
}

public int getTotal(){
    return total;
}

public float getPercentage(){
    return percentage;
}

public char getGrade(){
    return grade;
}

public void setName(String name){
    this.name = name;
}

public void setMarks(int[] marks){
    this.marks = marks.clone();
    calculateAll();
}

//==============================================
//Calculators 
//==============================================


    public void calculateAll(){

        calculateTotal();
        caluclatePercentage();
        calculateGrade();
    }

    private void calculateTotal(){
        this.total = 0;
        
        for (int i = 0; i < 5; i++){
            this.total += marks[i];
        }
    }

    private void caluclatePercentage(){

        this.percentage = (float) total * 100 / 500;
    }

    private void calculateGrade(){

        if(this.percentage >= 90){
            this.grade = 'A';
        }else if(this.percentage >= 80){
            this.grade = 'B';
        }else if(this.percentage >= 70){
            this.grade = 'C';
        }else if(this.percentage >= 60){
            this.grade = 'D';
        }else if(this.percentage >= 50){
            this.grade = 'E';
        }else {
            this.grade = 'F';
        }
    }


    //==============================================
    //Utilities 
    //==============================================


    public boolean isPassed(){
        if (percentage >= 50)
            return true;
        else
            return false;
    }

    public void displayInfo() {

        System.out.printf(
            "| %-6d | %-20s | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %-8.2f | %-5c |%n",
            rollNo,
            name,
            marks[0],
            marks[1],
            marks[2],
            marks[3],
            marks[4],
            total,
            percentage,
            grade
        );
    }
    public void displayReport() {

    System.out.println("\n========================================");
    System.out.println("           STUDENT REPORT");
    System.out.println("========================================");

    System.out.println("Roll Number : " + rollNo);
    System.out.println("Name        : " + name);

    System.out.println("\n----------- Subject Marks -----------");

    for (int i = 0; i < marks.length; i++) {
        System.out.printf("Subject %-2d : %3d%n", (i + 1), marks[i]);
    }

    System.out.println("-------------------------------------");
    System.out.println("Total        : " + total);
    System.out.printf("Percentage   : %.2f%%%n", percentage);
    System.out.println("Grade        : " + grade);
    System.out.println("Result       : " + (isPassed() ? "PASS" : "FAIL"));

    System.out.println("========================================");
}
}
