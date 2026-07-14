package StudentResultManagement;

import java.util.Scanner;

public class StudentManagement {
    private Student[] students;
    private int studentCount;
    private Scanner sc;

    public StudentManagement(int capacity){
        students = new Student[capacity];
        sc = new Scanner(System.in);
        studentCount = 0;
    }

    public void start(){
        int choice;

    do {

        showMenu();

        System.out.print("Enter Your Choice : ");
        choice = sc.nextInt();
        sc.nextLine();

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

        //     case 9:
        //         rankStudents();
        //         break;

        //     case 10:
        //         editStudent();
        //         break;

        //     case 11:
        //         deleteStudent();
        //         break;

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
                marks[i] = sc.nextInt(); 

            }while (marks[i]<0 && marks[i]>100);
        }

        students[studentCount] = new Student(name, studentCount ++, marks);

        System.out.println("Student Added :)");
    }

    public void displayStudents() {

        if (studentCount == 0) {
            System.out.println("\nNo Students Found.");
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


    //==============================================
    // Searches
    //==============================================

    private void searchRoll(){


        System.out.println("Enter Roll no : ");
        int roll = sc.nextInt();

        for (int i = 0; i < studentCount; i++){

            if(students[i].getRoll() == roll){
                students[i].displayReport();
                return;
            }
        }

        System.out.println("No Student Found !");
    }

    private void searchName(){

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

        if(studentCount == 0){
            System.out.println("No Data Found !");
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

        if(studentCount == 0){
            System.out.println("No Data Found !");
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

        if(studentCount == 0){
            System.out.println("No Data Found !");
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

        int passed = 0, failed = 0, total = 0, highestIndex = 0, lowestIndex = 0;
        int gradeA, gradeB, gradeC, gradeD, gradeE, gradeF;
        
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

        int classAverage = total / 5 ;
        float highestPercentage = students[highestIndex].getPercentage();
        float lowestPercentage = students[lowestIndex].getPercentage();
        String topperName = students[highestIndex].getName(), lowestName = students[lowestIndex].getName();
        int topperRoll = students[highestIndex].getRoll(), lowestRoll = students[lowestIndex].getRoll();
        int highestTotal = students[highestIndex].getTotal(), lowestTotal = students[lowestIndex].getTotal()


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
}
