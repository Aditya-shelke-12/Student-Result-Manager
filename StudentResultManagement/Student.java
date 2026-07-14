public class Student {
    private int rollNo;
    private String name;
    private int[] marks = new int[5];
    private int total = 0;
    private float percentage;
    private char grade;

    public Student(String name, int rollNo, int[] marks){
        this.name = name;
        this.rollNo = rollNo;
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
        for (int i = 0; i < 5; i++){
            this.total += marks[i];
        }
    }

    private void caluclatePercentage(){

        this.percentage = total / 5;
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
    //Calculators 
    //==============================================


    public boolean ispassed(){
        if (percentage >= 50)
            return true;
        else
            return false;
    }
}
