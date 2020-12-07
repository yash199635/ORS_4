package in.co.rays.ors.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.bean.CollegeBean;
import in.co.rays.ors.bean.FacultyBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModel;
import in.co.rays.ors.model.FacultyModel;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes.
 *
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class FacultyModelTest {

	/** Model object to test. */
	public static FacultyModel model = new FacultyModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args the arguments
	 * @throws DuplicateRecordException the duplicate record exception
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws DuplicateRecordException, ParseException {
		testAdd();
		// testDelete();
		// testUpdate();
	 //testFindByEmailId();
		 //testFindByPK();
		// testSearch();
		//testList();

	}

	/**
	 * Tests add a College.
	 *
	 * @throws DuplicateRecordException the duplicate record exception
	 * @throws ParseException the parse exception
	 */
	public static void testAdd() throws DuplicateRecordException, ParseException {

		try {
			FacultyBean bean = new FacultyBean();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
			bean.setCollegeId(6L);
			bean.setCourseId(7L);
			bean.setSubjectId(5L);
			bean.setFirstName("Rocky");
			bean.setLastName("Vijay");
			bean.setGender("female");
			bean.setDob(sdf.parse("6/6/1993"));
			bean.setEmailId("rocky_900876@gmail.com");
			bean.setMobileNo("9168454567");
			
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
				long pk = model.add(bean);
			System.out.println("Test add succ");
	
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a College.
	 */

	public static void testDelete() {

		try {
			FacultyBean bean = new FacultyBean();
			long pk = 19L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete successfully");

			FacultyBean deletedBean = model.findByPk(pk);
			if (deletedBean != null) {

				System.out.println("Test Delete fail");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a College.
	 */
	public static void testUpdate() {

		try {
			FacultyBean bean = model.findByPk(18L);
			bean.setCollegeId(2L);
			bean.setFirstName("Sheekha");
			bean.setLastName("Dave");
			model.update(bean);

			FacultyBean updatedbean = model.findByPk(18L);
			if (!"Sheekha".equals(updatedbean.getFirstName())) {
				System.out.println("Test Update fail");
			}else{
				System.out.println("Test Update Successfully");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}


	

	/**
	 * Tests find a College by PK.
	 */
	public static void testFindByPK() {
		try {
			FacultyBean bean = new FacultyBean();
			long pk = 11;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmailId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCourseId());
			
			System.out.println(bean.getSubjectId());
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests search a College by Name.
	 */

	public static void testSearch() {
		try {
			FacultyBean bean = new FacultyBean();
			List list = new ArrayList();
	
			// bean.setCollegeName("LNCT");
			  bean.setCourseName("MPhil");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getEmailId());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get List a College.
	 */
	public static void testList() {

		try {
			FacultyBean bean = new FacultyBean();
			List list = new ArrayList();
			list = model.list(1, 18);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getDob());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getEmailId());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
      
	/**
	 * Test find by email id.
	 */
	public static void testFindByEmailId() {
		try {
			FacultyBean bean = new FacultyBean();
			bean = model.findByEmail("jay345@gmail.com");
			if (bean != null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmailId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getSubjectId());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
