package jpa.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	/**
	 * This method gets and returns all the students from the student table
	 */
	public List<Student> getAllStudents() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Transaction tx = session.beginTransaction();

		String hql = "FROM Student AS s";
		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Student> listCourse = query.getResultList();

		// tx.commit();

		return listCourse;
	}

	/**
	 * This method gets and returns a student found by his/her email
	 */
	public Student getStudentByEmail(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Transaction t = session.beginTransaction();

		Student st = session.get(Student.class, sEmail);
		return st;

	}

	/**
	 * This method validates the student's credentials and returns a boolean value
	 */

	public boolean validateStudent(String sEmail, String sPassword) {
		boolean validate = false;
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Transaction tx = session.beginTransaction();

		Student u = session.get(Student.class, sEmail);

		if (u != null) {
			if (u.getsPassword().equals(sPassword))
				validate = true;
		} else {
			validate = false;
		}
		// tx.commit();

		return validate;
	}

	/**
	 * This method registers a student to a course. It updates the student_course
	 * joined table
	 * 
	 */
	public void registerStudentToCourse(String e, int cId) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Student st = session.get(Student.class, e);

		List<Course> listCourse = st.getCourses();

		Course u = session.get(Course.class, cId);

		if (listCourse.contains(u)) {
			System.out.println("You are registered for this course already");
		} else {
			listCourse.add(u);
			t.commit();
		}

	}

	/**
	 * This method gets and returns the student's courses
	 */

	public List<Course> getStudentCourses(String e) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Transaction t = session.beginTransaction();

		Student st = session.get(Student.class, e);

		List<Course> listCourses = st.getCourses();

		return listCourses;

	}

}
