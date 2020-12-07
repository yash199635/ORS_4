
package in.co.rays.ors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import in.co.rays.ors.bean.CollegeBean;
import in.co.rays.ors.bean.StudentBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DatabaseException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.JDBCDataSource;

// TODO: Auto-generated Javadoc
/**
 * JDBC Implementation of Student Model.
 *
 * @author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class StudentModel {

    /** The log. */
    private static Logger log = Logger.getLogger(StudentModel.class);

    /**
     * Find next PK of Student.
     *
     * @return the integer
     * @throws DatabaseException the database exception
     */
    public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_STUDENT");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model nextPK End");
        return pk + 1;
    }

    /**
     * Add a Student.
     *
     * @param bean the bean
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        System.out.println("add started");
        
        Connection conn = null;

        // get College Name
        CollegeModel cModel = new CollegeModel();
        System.out.println(bean.getCollegeId());
        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
        
        bean.setCollegeName(collegeBean.getName());

        StudentBean duplicateName = findByEmailId(bean.getEmail());
        int pk = 0;
        if (duplicateName != null) {
            throw new DuplicateRecordException("Email already exists");
        }
        
        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setLong(2, bean.getCollegeId());
            pstmt.setString(3, bean.getCollegeName());
            pstmt.setString(4, bean.getFirstName());
            pstmt.setString(5, bean.getLastName());
            pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(7, bean.getMobileNo());
            pstmt.setString(8, bean.getEmail());
            pstmt.setString(9, bean.getCreatedBy());
            pstmt.setString(10, bean.getModifiedBy());
            pstmt.setTimestamp(11, bean.getCreatedDatetime());
            pstmt.setTimestamp(12, bean.getModifiedDatetime());
            pstmt.executeUpdate();

            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
                
            } catch (Exception ex) {
            	ex.printStackTrace();
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a Student.
     *
     * @param bean the bean
     * @throws ApplicationException the application exception
     */
    public void delete(StudentBean bean) throws ApplicationException {
    	System.out.println("15");
    	log.debug("Model delete Started");
        Connection conn = null;
        try {
        	System.out.println("16");
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_STUDENT WHERE ID=?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
            System.out.println("17");
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Delete rollback exception "+ ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete Student");
        } finally {
        	System.out.println("18");
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by Student.
     *
     * @param Email            : get parameter
     * @return bean
     * @throws ApplicationException the application exception
     */

    public StudentBean findByEmailId(String Email) throws ApplicationException {
        log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_STUDENT WHERE EMAIL=?");
        StudentBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();
    //        System.out.println("tttttttttttttttttttttttttttt");
            while (rs.next()) {
    //        	System.out.println("whilllllllllllllllllllllllllll");
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy Email End");
        return bean;
    }

    /**
     * Find Student by PK.
     *
     * @param pk            : get parameter
     * @return bean
     * @throws ApplicationException the application exception
     */

    public StudentBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
        StudentBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return bean;
    }

    /**
     * Update a Student.
     *
     * @param bean the bean
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */

    public void update(StudentBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;

        StudentBean beanExist = findByEmailId(bean.getEmail());

        // Check if updated Roll no already exist
        if (beanExist != null && beanExist.getId() != bean.getId()) {
            throw new DuplicateRecordException("Email Id is already exist");
        }

        // get College Name
        CollegeModel cModel = new CollegeModel();
        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
        bean.setCollegeName(collegeBean.getName());

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setLong(1, bean.getCollegeId());
            pstmt.setString(2, bean.getCollegeName());
            pstmt.setString(3, bean.getFirstName());
            pstmt.setString(4, bean.getLastName());
            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(6, bean.getMobileNo());
            pstmt.setString(7, bean.getEmail());
            pstmt.setString(8, bean.getCreatedBy());
            pstmt.setString(9, bean.getModifiedBy());
            pstmt.setTimestamp(10, bean.getCreatedDatetime());
            pstmt.setTimestamp(11, bean.getModifiedDatetime());
            pstmt.setLong(12, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Student ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search Student.
     *
     * @param bean            : Search Parameters
     * @return the list
     * @throws ApplicationException the application exception
     */

    public List search(StudentBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search Student with pagination.
     *
     * @param bean            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Students
     * @throws ApplicationException the application exception
     */

    public List search(StudentBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            
            if (bean.getCollegeId() > 0) {
                sql.append(" AND College_Id = " + bean.getCollegeId());
            }
            if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
                sql.append(" AND FIRST_NAME like '" + bean.getFirstName()
                        + "%'");
            }
            if (bean.getLastName() != null && bean.getLastName().length() > 0) {
                sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
            }
            if (bean.getDob() != null && bean.getDob().getDate() > 0) {
                sql.append(" AND DOB = " + bean.getDob());
            }
            if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
                sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
            }
            if (bean.getEmail() != null && bean.getEmail().length() > 0) {
                sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
            }
            if (bean.getCollegeName() != null
                    && bean.getCollegeName().length() > 0) {
                sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            //	System.out.println("while...............>>>>");
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Get List of Student.
     *
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */

    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Student with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */

    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentBean bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
}
