package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "email", length = 50, nullable = false)
	private String sEmail;

	@Column(name = "name", length = 50, nullable = false)
	private String sName;

	@Column(name = "password", length = 50, nullable = false)
	private String sPassword;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<Course>();

	public Student(String email, String name, String password) {
		super();
		this.sEmail = email;
		this.sName = name;
		this.sPassword = password;
	}

	public Student() {
		super();
		this.sEmail = "";
		this.sName = "";
		this.sPassword = "";
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPassword=" + sPassword + "]";
	}

}
