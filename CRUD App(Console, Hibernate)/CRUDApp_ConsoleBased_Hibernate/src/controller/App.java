package controller;

import java.util.Scanner;

import dto.Student;
import service.IStudentService;
import servicefactory.StudentServiceFactory;

public class App {

    private static IStudentService studentService;
    static Scanner scanner;
    public static void main(String[] args) {
        studentService = StudentServiceFactory.getStudentService();

        while (true) {
            scanner = new Scanner(System.in);
            System.out.println(
                    "\n********Student's Database********\n1. Press 1 for Insert operation\n2. Press 2 for select operation\n3. Press 3 for Update operation\n4. Press 4 for Delete operation\n5. Press 5 for exit");
            int nextInt = scanner.nextInt();
            switch (nextInt) {
                case 1:
                    insertOperation();
                    break;

                case 2:
                    selectOperation();
                    break;

                case 3:
                    updateOperation();
                    break;

                case 4:
                    deleteOperation();
                    break;

                case 5:
                    System.out.println("--------ThankYou-------");
                    System.exit(0);

                default:
                    System.out.println("Invalid Operation\n");

            }

        }
    }

    public static void insertOperation() {

        System.out.println("Enter student's name:: ");
        String name = scanner.next();
        System.out.println("Enter student's age:: ");
        int age = scanner.nextInt();
        System.out.println("Enter student's address:: ");
        String address = scanner.next();
        if (studentService != null) {
        	System.out.println();
            System.out.println(studentService.addStudent(name, age, address));

        }else {
			System.out.println();
			System.out.println("Failure");
		}

    }

    public static void selectOperation() {
        
        System.out.println("Enter student's id:: ");
        int sid = scanner.nextInt();
        if (studentService != null) {
            Student std = studentService.searchStudent(sid);
            if (std != null) {
                System.out.println("ID\t" + "NAME\t" + "AGE\t" + "ADDRESS\t");
                System.out.println(
                        std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getSaddress());
            } else {
                System.out.println("Student record not found for id: "+sid);
            }

        }

    }

    public static void updateOperation() {
        System.out.println("Enter student's id:: ");
        int id = scanner.nextInt();
        if (studentService != null) {
            Student studentIn = studentService.searchStudent(id);
            if (studentIn != null) {
            	//printing old record first
                System.out.println("Old Record");
                System.out.println("ID\t" + "NAME\t" + "AGE\t" + "ADDRESS\t");
                System.out.println(
                        studentIn.getSid() + "\t" + studentIn.getSname() + "\t" + studentIn.getSage() + "\t" + studentIn.getSaddress());
                System.out.println();
                //asking user for update information
                System.out.println("Enter student's new name:: ");
                String name = scanner.next();
                System.out.println("Enter student's new age:: ");
                Integer age = scanner.nextInt();
                System.out.println("Enter student's new address:: ");
                String address = scanner.next();
                Student student = new Student();
                student.setSid(id);
                student.setSname(name);
                student.setSage(age);
                student.setSaddress(address);
                Student studentOut = studentService.updateStudent(student);
                if (studentOut != null) {
                	System.out.println();
                    System.out.println("Updated Record");
                    System.out.println();
                System.out.println("ID\t" + "NAME\t" + "AGE\t" + "ADDRESS\t");
                System.out.println(
                        studentOut.getSid() + "\t" + studentOut.getSname() + "\t" + studentOut.getSage() + "\t" + studentOut.getSaddress());
            } 
            } else {
    			System.out.println();
    			System.out.println("Record not found for id: "+ id);
    		}
        }
    }

    public static void deleteOperation() {
        System.out.println("Enter student's Id:: ");
        int id = scanner.nextInt();
        if (studentService != null) {
        	System.out.println();
            System.out.println(studentService.deleteStudent(id));
        }else {
			System.out.println();
			System.out.println("Failure");
		}
    }
}
