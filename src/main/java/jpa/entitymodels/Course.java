package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int cId;

	@Column(name = "name", length = 50, nullable = false)
	private String cName;

	@Column(name = "instructor", length = 50, nullable = false)
	private String cInstructorName;

	@ManyToMany(mappedBy = "courses", cascade = { CascadeType.ALL })
	private List<Student> students = new ArrayList<Student>();

	public Course() {
		super();
		this.cId = 0;
		this.cName = "";
		this.cInstructorName = "";
	}

	public Course(int cId, String cName, String cInstructorName) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {

		String values = String.format("%-3s %-20.20s %-20.9s ", Integer.toString(cId), cName, cInstructorName);
		return values;
	}

}
