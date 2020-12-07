package in.co.rays.ors.TestModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.bean.CollegeBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModel;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes.
 *
 * @author Abstract Factory
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class CollegeModelTest {

	/** Model object to test. */
	public static CollegeModel model = new CollegeModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args the arguments
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public static void main(String[] args) throws DuplicateRecordException {
		// testAdd();
		// testDelete();
		 //testUpdate();
		// testFindByName();
	// testFindByPK();
	//	 testSearch();
		testList();

	}

	/**
	 * Tests add a College.
	 *
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public static void testAdd() throws DuplicateRecordException {

		try {
			CollegeBean bean = new CollegeBean();
			 bean.setId(2L);
			bean.setName("Jay");
			bean.setAddress("Bhalu");
			bean.setState("MP");
			bean.setCity("Indore");
			bean.setPhoneNo("073124244");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			System.out.println("Test add succ");
			CollegeBean addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a College.
	 */
	public static void testDelete() {

		try {
			CollegeBean bean = new CollegeBean();
			long pk = 20L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ");
			CollegeBean deletedBean = model.findByPK(pk);
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
			CollegeBean bean = model.findByPK(19L);
			bean.setName("BHU University");
			bean.setCity("Bnaras");
			bean.setAddress("Shiv Dham");
			bean.setState("Uttar Pradesh");
			model.update(bean);
			System.out.println("Test Update succ");
			CollegeBean updateBean = model.findByPK(19L);
			if (!"BHU University".equals(updateBean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a College by Name.
	 */

	public static void testFindByName() {

		try {
			CollegeBean bean = model.findByName("Vikram university");
			if (bean == null) {
				System.out.println("Test Find By Name fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a College by PK.
	 */
	public static void testFindByPK() {
		try {
			CollegeBean bean = new CollegeBean();
			long pk = 2L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests search a College by Name.
	 */

	public static void testSearch() {
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
		//	bean.setName("LNCT");
			 bean.setAddress("shiv dham");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
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
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
