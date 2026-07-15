import java.util.Scanner;

public class StudentManagement {
    private Student[] students;
    private int studentCount;
    private final Scanner sc;

    public StudentManagement(int capacity, Scanner sc){
        students = new Student[capacity];
        this.sc = sc;
        studentCount = 0;
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public void start(){
        int choice;

    do {

        showMenu();

        System.out.print("Enter Your Choice : ");
        choice = readInt();

        switch (choice) {

            case 1:
                addStudents();
                break;

            case 2:
                displayStudents();
                break;

            case 3:

                searchRoll();

                break;

            case 4:

                searchName();
                break;

            case 5:
                findTopper();
                break;

            case 6:
                findSubjectHigher();
                break;

            case 7:
                findSubjectLower();
                break;

            case 8:
                classStatistics();
                break;

            case 9:
                rankStudents();
                break;

            case 10:
                editStudent();
                break;

            case 11:
                deleteStudent();
                break;

            case 0:
                System.out.println("\nThank You For Using Student Result Management System.");
                break;

             default:
                 System.out.println("Invalid Choice.");

        }

        // if (choice != 0) {
        //     pressEnterToContinue();
        // }

    } while (choice != 0);

    }

    private void showMenu() {

        System.out.println("\n==========================================");
        System.out.println("      STUDENT RESULT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search By Roll Number");
        System.out.println("4. Search By Name");
        System.out.println("5. Find Topper");
        System.out.println("6. Highest Marks In Each Subject");
        System.out.println("7. Lowest Marks In Each Subject");
        System.out.println("8. Class Statistics");
        System.out.println("9. Rank Students");
        System.out.println("10. Edit Student");
        System.out.println("11. Delete Student");
        System.out.println("0. Exit");
        System.out.println("==========================================");

    }

    private void addStudents(){
        if (students.length == studentCount){
            System.out.println("DataBase Full");
            return;
        }

        System.out.println("Enter Name : ");
        String name = sc.nextLine();

        int [] marks = new int[5];

        for (int i = 0; i < 5; i++){
            do{
                System.out.println("Enter marks of subject " + (i+1) + " : ");
                marks[i] = readInt(); 

            }while (marks[i] < 0 || marks[i] > 100);
        }

        students[studentCount] = new Student(name, studentCount ++, marks);

        System.out.println("Student Added :)");
    }

    private void displayStudents() {

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        String border =
        "+--------+----------------------+-------+-------+-------+-------+-------+-------+----------+-------+";

        System.out.println(border);

        System.out.printf(
        "| %-6s | %-20s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-8s | %-5s |%n",
        "Roll","Name","S1","S2","S3","S4","S5","Total","Percent","Grade");

        System.out.println(border);

        for(int i = 0; i < studentCount; i++){
            students[i].displayInfo();
        }

        System.out.println(border);
    }

    private void searchRoll(){

         if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("Enter Roll no : ");
        int roll = readInt();

        for (int i = 0; i < studentCount; i++){

            if(students[i].getRoll() == roll){
                students[i].displayReport();
                return;
            }
        }

        System.out.println("No Student Found !");
    }

    private void searchName(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("Enter Name : ");
        String name = sc.nextLine();

        boolean isfound = false;

        for (int i = 0; i < studentCount; i++){

            if(students[i].getName().equalsIgnoreCase(name)){
                students[i].displayReport();
                isfound = true;
            }
        }
        if (!isfound)
            System.out.println("No Student Found !");
    }

    private void findTopper(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        int maxIndex = 0;

        for (int i = 1; i < studentCount; i++){

            if (students[i].getTotal() > students[maxIndex].getTotal()){
                maxIndex = i;
            }
        }
        System.out.println("Topper : ");
        students[maxIndex].displayReport();
    }

    private void findSubjectHigher(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("Toppers : ");
        
        for (int i = 0; i < 5; i++){

            int maxIndex = 0;
            for (int j = 0; j < studentCount; j++){
                
                if (students[j].getMarks()[i] > students[maxIndex].getMarks()[i]){
                    maxIndex = j;
                }
            }

            System.out.println("Subject" + (i+1) + " : " + students[maxIndex].getMarks()[i] + "(" + students[maxIndex].getName() + ")");

        }
    }

    private void findSubjectLower(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("Toppers : ");
        
        for (int i = 0; i < 5; i++){

            int minIndex = 0;
            for (int j = 0; j < studentCount; j++){
                
                if (students[j].getMarks()[i] < students[minIndex].getMarks()[i]){
                    minIndex = j;
                }
            }

            System.out.println("Subject" + (i+1) + " : " + students[minIndex].getMarks()[i] + "(" + students[minIndex].getName() + ")");

        }
    }

    private void classStatistics(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        int passed = 0, failed = 0, total = 0, highestIndex = 0, lowestIndex = 0;
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeE = 0, gradeF = 0;
        
        for (int i = 0; i < studentCount; i++){

            if(students[i].isPassed())
                passed++;
            else 
                failed++;

            total += students[i].getTotal();

            if (students[highestIndex].getPercentage() < students[i].getPercentage()){

                highestIndex = i;
            }
                

            if(students[lowestIndex].getPercentage() > students[i].getPercentage()){

                lowestIndex = i;
            }

            switch (students[i].getGrade()) {
                
                case 'A':
                    gradeA ++;
                    break;
                
                case 'B':
                    gradeB ++;
                    break;                
                
                case 'C':
                    gradeC ++;
                    break;

                case 'D':
                    gradeD ++;
                    break;

                case 'E':
                    gradeE ++;
                    break;

                case 'F':
                    gradeF ++;
                    break;

                default:
                    break;
            }
                
        }

        float classAverage = (float) total * 100 / (studentCount * 500) ;
        float highestPercentage = students[highestIndex].getPercentage();
        float lowestPercentage = students[lowestIndex].getPercentage();
        String topperName = students[highestIndex].getName(), lowestName = students[lowestIndex].getName();
        int topperRoll = students[highestIndex].getRoll(), lowestRoll = students[lowestIndex].getRoll();
        int highestTotal = students[highestIndex].getTotal(), lowestTotal = students[lowestIndex].getTotal();


        System.out.println("\n=================================================");
        System.out.println("              CLASS STATISTICS");
        System.out.println("=================================================");

        System.out.printf("Total Students      : %d%n", studentCount);

        System.out.printf("Students Passed     : %d%n", passed);
        System.out.printf("Students Failed     : %d%n", failed);

        System.out.printf("Class Average       : %.2f%%%n", classAverage);

        System.out.println();

        System.out.printf("Highest Percentage  : %.2f%%%n", highestPercentage);
        System.out.printf("Topper              : %s (Roll No. %d)%n",
                topperName,
                topperRoll);

        System.out.println();

        System.out.printf("Lowest Percentage   : %.2f%%%n", lowestPercentage);
        System.out.printf("Lowest Scorer       : %s (Roll No. %d)%n",
                lowestName,
                lowestRoll);

        System.out.println();

        System.out.printf("Highest Total Marks : %d / 500%n", highestTotal);
        System.out.printf("Lowest Total Marks  : %d / 500%n", lowestTotal);

        System.out.println();

        System.out.println("Grade Distribution");
        System.out.println("--------------------------------");

        System.out.printf("Grade A : %d%n", gradeA);
        System.out.printf("Grade B : %d%n", gradeB);
        System.out.printf("Grade C : %d%n", gradeC);
        System.out.printf("Grade D : %d%n", gradeD);
        System.out.printf("Grade E : %d%n", gradeE);
        System.out.printf("Grade F : %d%n", gradeF);

        System.out.println("=================================================");
    }

    private void rankStudents(){
        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        Student[] rankedStudents = new Student[studentCount];

        rankedStudents = students.clone();

        for (int i =0; i < studentCount - 1; i++){

            for (int j = 0; j < studentCount - i - 1; j++){

                if (rankedStudents[j].getTotal() < rankedStudents[j + 1].getTotal()){
                    Student temp = rankedStudents[j];
                    rankedStudents[j] = rankedStudents[j+1];
                    rankedStudents[j+1] = temp;
                }
            }

        }

        String border =
        "+--------+----------------------+-------+-------+-------+-------+-------+-------+----------+-------+";

        System.out.println(border);

        System.out.printf(
        "| %-6s | %-20s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-8s | %-5s |%n",
        "Roll","Name","S1","S2","S3","S4","S5","Total","Percent","Grade");

        System.out.println(border);

        for(int i = 0; i < studentCount; i++){
            rankedStudents[i].displayInfo();
        }

        System.out.println(border);
    }

    private void editStudent(){

        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("==========================================");
        System.out.println("         EDIT STUDENT");
        System.out.println("==========================================");

        System.out.println("Enter Roll Number : ");
        int roll = readInt();

        int index = -1;

        for (int i = 0; i < studentCount; i++){
            if (students[i].getRoll() == roll){
                index = i;
                break;
            }
        }

        if (index == -1){
            System.out.println("Student having roll no : " + roll + " don't exists!");
            return;
        }

        students[index].displayReport();

        System.out.println("\n==========================================");
        System.out.println("What would you like to edit?");
        System.out.println("==========================================");
        System.out.println("1. Student Name");
        System.out.println("2. Subject Marks");
        System.out.println("3. Both Name & Marks");
        System.out.println("0. Cancel");

        System.out.print("\nEnter Choice : ");
        int choice = readInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("\nEnter New Name : ");
                String newName = sc.nextLine();

                students[index].setName(newName);

                System.out.println("\n✓ Name Updated Successfully.");
                break;

            case 2:
                System.out.println("\nEnter New Marks");

                int[] newMarks = inputMarks();

                students[index].setMarks(newMarks);

                System.out.println("\n✓ Marks Updated Successfully.");
                break;

            case 3:
                System.out.print("\nEnter New Name : ");
                String updatedName = sc.nextLine();

                students[index].setName(updatedName);

                System.out.println("\nEnter New Marks");

                int[] updatedMarks = inputMarks();

                students[index].setMarks(updatedMarks);

                System.out.println("\n✓ Student Record Updated Successfully.");
                break;

            case 0:
                System.out.println("\nEdit Cancelled.");
                return;

            default:
                System.out.println("\nInvalid Choice.");
                return;
        }

        System.out.println("\n==========================================");
        System.out.println("UPDATED STUDENT DETAILS");
        System.out.println("==========================================");  

        students[index].displayReport();

    }
    
    private void deleteStudent(){
        if (studentCount == 0) {
            System.out.println("\nNo Students Data Found.");
            return;
        }

        System.out.println("Enter Roll no of Student to delete");
        int roll = readInt();

        sc.nextLine();

        int index = -1;

        for(int i = 0; i < studentCount; i++){
            if (students[i].getRoll() == roll){
                index = i;
                break;
            }
        }

        if (index == -1){
            System.out.println("Student having roll no : " + roll + " don't exists!");
            return;
        }

        String name = students[index].getName();

        for (int i = index; i < studentCount-1; i++){
            students[i] = students[i+1];
        }

        students[studentCount - 1] = null;

        studentCount--;

        System.out.println("Student " + name + " is been deleted !");
    }

    private int[] inputMarks() {

        int[] marks = new int[5];

        System.out.println("\nEnter Marks of 5 Subjects");

        for (int i = 0; i < 5; i++) {

            do {

                System.out.print("Subject " + (i + 1) + " : ");
                int mark = readInt();

                marks[i] = mark;

            }while (marks[i] < 0 || marks[i] > 100);

        }

        sc.nextLine(); // Clear buffer

        return marks;

    }
}
