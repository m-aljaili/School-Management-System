package com.hiibernateJPANamedQueries.studentCourseSBA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jpa.entitymodels.Student;
import jpa.service.StudentService;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private static StudentService studentService;

	@BeforeAll
	public static void beforeTests() {
		studentService = new StudentService();
	}

	/**
	 * A test for the getStudentByEmail method from the Student DAO
	 */
	@Test
	void testgetStudentByEmail() {
		Student expected = new Student();
		expected.setsEmail("tattwool4@biglobe.ne.jp");
		expected.setsPassword("Hjt0SoVmuBz");
		expected.setsName("Thornie Attwool");

		Student actual = studentService.getStudentByEmail("tattwool4@biglobe.ne.jp");
		Assertions.assertEquals(expected.getsEmail(), actual.getsEmail());

	}

}
