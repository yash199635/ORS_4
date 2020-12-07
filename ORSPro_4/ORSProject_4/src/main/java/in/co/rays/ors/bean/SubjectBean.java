package in.co.rays.ors.bean;


// TODO: Auto-generated Javadoc
/**
 * Subject JavaBean encapsulates Subject attributes.
 *
 * @author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class SubjectBean extends BaseBean {


	/** Name of Subject. */
	private String subjectName;

	/** Description of Subject. */
	private String description;

    /** CourseId of Subject. */
	private long courseId;

    /** Course Name of Subject. */
	private String courseName;


    /**
     * Getter and Setter of Subject.
     *
     * @return the subject name
     */

    public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	

    /* (non-Javadoc)
     * @see in.co.rays.ors.bean.DropdownListBean#getKey()
     */
    public String getKey() {
        return id + "";
    }

   
    /* (non-Javadoc)
     * @see in.co.rays.ors.bean.DropdownListBean#getValue()
     */
    public String getValue() {
        return subjectName;
    }
}
