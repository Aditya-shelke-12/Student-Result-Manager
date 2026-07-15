import java.util.Scanner;

public class Project1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("     STUDENT RESULT MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        System.out.print("Enter Maximum Number Of Students : ");
        int capacity = sc.nextInt();
        sc.nextLine();

        StudentManagement sms = new StudentManagement(capacity, sc);

        sms.start();

        sc.close();
    }

}