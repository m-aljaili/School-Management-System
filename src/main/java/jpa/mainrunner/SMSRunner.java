/*
 * Filename: SMSRunner.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.mainrunner;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

/**
 * 1
 * 
 * @author Harry
 *
 */
// Thora - The main application entry point that interacts with user
public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {

		// Login or quit
		try {
			switch (menu1()) {
			case 1:
				if (studentLogin()) {
					registerMenu();
				}
				break;
			case 2:
				System.out.println("Goodbye!");
				break;

			default:
				System.out.println("Invalid option. Goodbye!");

			}
		} catch (Exception e) {
			System.out.println("Invalid option. Goodbye!");
		}
	}

	private int menu1() {
		System.out.println("Are you a student?");
		sb.append("\n1.Student Login\n2. Quit Application\n\nPlease Enter Selection: ");
		System.out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		Student student = studentService.getStudentByEmail(email);

		if (student != null) {
			currentStudent = student;

		} else {
			System.out.println("Wrong Credentials");
			return retValue;
		}

		if (studentService.validateStudent(email, password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			System.out.println("\nMy Classes:\n");
			System.out.printf("%-3s %-20s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
			System.out.println("-------------------------------------------------------");
			if (courses != null) {

				for (Course course : courses) {
					System.out.println(course);
				}
			}
			retValue = true;
		} else {
			System.out.println("Wrong Credentials");
			return retValue;
		}

		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1. Register a class\n2. Logout\n Enter Selection: ");
		System.out.print(sb.toString());
		sb.delete(0, sb.length());

		try {
			switch (sin.nextInt()) {
			case 1:
				List<Course> allCourses = courseService.getAllCourses();
				List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
				allCourses.removeAll(studentCourses);
				System.out.println("\nAll Courses:");
				System.out.printf("%-3s %-20s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
				System.out.println("-------------------------------------------------------");
				for (Course course : allCourses) {
					System.out.println(course);
				}
				System.out.println();
				System.out.print("Enter Course Number: ");
				int number = sin.nextInt();
				Course newCourse = courseService.getCourseById(number);

				if (newCourse != null) {
					int listSize = currentStudent.getCourses().size();

					studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getcId());

					Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());

					StudentService scService = new StudentService();
					List<Course> sCourses = scService.getStudentCourses(temp.getsEmail());

					if (listSize == sCourses.size()) {
						System.out.println("\nYou are already registered in that course!");
					} else {

						System.out.println("\nYou have registered for a new course\n");
						System.out.println("My Classes:");
						System.out.printf("%-3s %-20s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
						System.out.println("-------------------------------------------------------");
						for (Course course : sCourses) {
							System.out.println(course);
						}
					}

				} else {

					System.out.println("You have entered an invalid selection. Goodbye!");
				}
				break;
			case 2:
				System.out.println("You have been logged out. Goodbye!");
				break;
			default:
				System.out.println("Invalid selection. Goodbye!");
			} // end of switch
		} catch (Exception e) {
			System.out.println("You have entered an invalid selection. Goodbye!");
		}
	}
}
