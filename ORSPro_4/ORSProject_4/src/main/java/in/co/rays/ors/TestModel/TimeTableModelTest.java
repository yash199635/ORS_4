package in.co.rays.ors.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.bean.FacultyBean;
import in.co.rays.ors.bean.TimeTableBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.TimeTableModel;



// TODO: Auto-generated Javadoc
/**
 * The Class TimeTableModelTest.
 */
public class TimeTableModelTest {
    
	/** The model. */
	public static TimeTableModel model = new TimeTableModel();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws ParseException {
	//testAdd();
	      //  testDelete();	 
		// testFindByPK();
		// testFindByEmailId();
	 testSearch();
//		 testList();
  	//testUpdate();
	
}
	
	/**
	 * Test add.
	 *
	 * @throws ParseException the parse exception
	 */
	public static void testAdd() throws ParseException {
		try {
			TimeTableBean bean = new TimeTableBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	
			bean.setCourseId(6L);
			bean.setSubjectId(7L);
			bean.setCourseName("B TECH");
			bean.setExamDate(sdf.parse("1/5/2018"));
			bean.setExamTime("07:00 AM to 10:00 AM");
			bean.setSemester("5th");
			bean.setSubjectName("RDBMS");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			TimeTableBean addedbean = model.findByPk(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Test find by PK.
	 */
	public static void testFindByPK() {
		try {
		    TimeTableBean bean = new TimeTableBean();
			long pk = 11;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getSemester());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getExamDate());
			
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test search.
	 */
	public static void testSearch() {

		try {
			TimeTableBean bean = new TimeTableBean();
			List list = new ArrayList();
			bean.setSubjectName("Human Resource Management");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (TimeTableBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSemester());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getExamDate());
				}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Test list.
	 */
	public static void testList() {

		try {
			TimeTableBean bean = new TimeTableBean();
			List list = new ArrayList();
			list = model.list(1, 12);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (TimeTableBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSemester());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getExamDate());
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
	 * Test update.
	 */
	public static void testUpdate() {

		try {
			TimeTableBean bean = model.findByPk(20L);
			bean.setSubjectId(3L);
			//bean.setSubjectName("Material Technology");
			model.update(bean);

			TimeTableBean updatedbean = model.findByPk(20L);
			/*if (!"Material Technology".equals(updatedbean.getSubjectName())) {
				System.out.println("Test Update fail");
			}else{
				System.out.println("Test Update Successfully");
			}*/
			System.out.println("-------------------------------");
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
          
	/**
	 * Test delete.
	 */
	public static void testDelete() {

		try {
			TimeTableBean bean = new TimeTableBean();
			long pk = 20L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete successfully");

			TimeTableBean deletedBean = model.findByPk(pk);
			if (deletedBean != null) {

				System.out.println("Test Delete fail");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}



}
