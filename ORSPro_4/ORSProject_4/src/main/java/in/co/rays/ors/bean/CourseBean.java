package in.co.rays.ors.bean;

// TODO: Auto-generated Javadoc
/**
 * Course JavaBean encapsulates Course attributes.
 *
 * @author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class CourseBean extends BaseBean{

    /** Name of Course. */
	private String name;

    /** Description of Course. */
	private String description;

    /** Duration of Course. */
	
	private String duration;
	

    /**
     * Getter and Setter of Course.
     *
     * @return the name
     */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}


    /* (non-Javadoc)
     * @see in.co.rays.ors.bean.DropdownListBean#getKey()
     */
    public String getKey() {
        return id+"";
    }

   
    /* (non-Javadoc)
     * @see in.co.rays.ors.bean.DropdownListBean#getValue()
     */
    public String getValue() {
        return name;
    }
	
}
