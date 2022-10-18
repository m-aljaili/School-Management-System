package jpa.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO {

	/**
	 * This method gets and returns all the courses in the course table
	 */

	public List<Course> getAllCourses() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Course AS c";
		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Course> listCourse = query.getResultList();

		return listCourse;
	}

	/**
	 * This method gets and returns a course by id
	 */

	public Course getCourseById(int id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Course c = session.get(Course.class, id);

		return c;
	}
}
