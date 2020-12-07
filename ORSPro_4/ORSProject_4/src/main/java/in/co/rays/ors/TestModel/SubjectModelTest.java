package in.co.rays.ors.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.bean.SubjectBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.SubjectModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SubjectModelTest.
 */
public class SubjectModelTest {


    /** The model. */
    public static SubjectModel model = new SubjectModel();

    /**
     * Main method to call test methods.
     *
     * @param args the arguments
     * @throws ParseException the parse exception
     */
    public static void main(String[] args) throws ParseException {
         testAdd();
        // testDelete();
        // testUpdate();
       //  testFindByPK();
     
     //    testSearch();
  //  testList();

    }

    /**
     * Tests add a Student.
     *
     * @throws ParseException the parse exception
     */
    public static void testAdd() throws ParseException {

        try {
            SubjectBean bean = new SubjectBean();

          //  bean.setId(1L);
            bean.setSubjectName("c");
            bean.setDescription("basic computer lang");
            bean.setCourseId(1);
            bean.setCourseName("bca");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            System.out.println("add method call");
            long pk = model.add(bean);
           System.out.println("data enter");
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests delete a Student.
     */
    public static void testDelete() {

        try {
            SubjectBean bean = new SubjectBean();
            long pk = 59L;
            bean.setId(pk);
            model.delete(bean);
            SubjectBean deletedbean = model.findByPk(pk);
            if (deletedbean != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests update a Student.
     */
    public static void testUpdate() {

        try {
            SubjectBean bean = model.findByPk(59L);
           
            bean.setSubjectName("c++");
          
            model.update(bean);

            SubjectBean updatedbean = model.findByPk(59L);
            if (!"c++".equals(updatedbean.getSubjectName())) {
                System.out.println("Test Update fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests find a Student by PK.
     */
    public static void testFindByPK() {
        try {
            SubjectBean bean = new SubjectBean();
            long pk = 1L;
            bean = model.findByPk(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getCourseId());
            System.out.println(bean.getCourseName());
            System.out.println(bean.getDescription());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getSubjectName());
            System.out.println(bean.getModifiedBy());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

      /**
       * Tests get Search.
       */
    
    public static void testSearch() {

		try {
			SubjectBean bean = new SubjectBean();
			List list = new ArrayList();
			bean.setSubjectName("Materials Engineering");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
        

    /**
     * Tests get List.
     */
    public static void testList() {

        try {
            SubjectBean bean = new SubjectBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (SubjectBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getCourseId());
                System.out.println(bean.getCourseName());
                System.out.println(bean.getDescription());
                System.out.println(bean.getSubjectName());
                            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
