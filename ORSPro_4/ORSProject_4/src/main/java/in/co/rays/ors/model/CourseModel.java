package in.co.rays.ors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ors.bean.CourseBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DatabaseException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.JDBCDataSource;

// TODO: Auto-generated Javadoc
/**
 * JDBC Implementation of CourseModel.
 *
 * @author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class CourseModel {

	/** The log. */
	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of Course.
	 *
	 * @return the integer
	 * @throws DatabaseException the database exception
	 */

	public Integer nextPk() throws DatabaseException {
		log.debug("CourseModel nextPK method started");

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Exception in Database..", e);
			throw new DatabaseException("Exception : Exception in getting Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel nextPK method END");
		return pk + 1;
	}

	/**
	 * Add a Course.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */

	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("CourseModel Add method END");
		Connection conn = null;
		int pk = 0;
		
		CourseBean duplicateCourseName = findByName(bean.getName());
		if(duplicateCourseName!=null){
			throw new DuplicateRecordException("Course Name already Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.debug("EXception in Database...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add Rollback Exception.." + ex.getMessage());
			}
			throw new ApplicationException("Exception in Course Add method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel Add method END");
		return pk;
	}
	
	/**
	 * Delete a Course.
	 *
	 * @param bean the bean
	 * @throws ApplicationException the application exception
	 */

	public void delete(CourseBean bean) throws ApplicationException {
		log.debug("CourseModel Delete Method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Exception in Rollback Method" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delete Method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel Delete Method End");
	}
	
	/**
	 * Update a Course.
	 *
	 * @param bean the bean
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */

	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		
		CourseBean beanExist = findByName(bean.getName());
		if(beanExist !=null && beanExist.getId()!=bean.getId()){
			throw new DuplicateRecordException("Course Already Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_COURSE SET NAME=?,DURATION=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Database Exception ...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Exception in Rollback.." + ex.getMessage());
			}
			throw new ApplicationException("Exception in Updating the Course Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	/**
	 * Find User by Course.
	 *
	 * @param name            : get parameter
	 * @return bean
	 * @throws ApplicationException the application exception
	 */

	public CourseBean findByName(String name) throws ApplicationException {
		log.debug("Course Model FindByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE NAME=?");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.debug("Database Exception ..", e);
			throw new ApplicationException("Exception in Course Model FindByName Method ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Course Model FindByName method End");
		return bean;
	}
	
	/**
	 * Find User by Course.
	 *
	 * @param pk            : get parameter
	 * @return bean
	 * @throws ApplicationException the application exception
	 */

	public CourseBean findByPk(Long pk) throws ApplicationException {
		log.debug("CourseModel FindbyPK method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		}catch(Exception e) {
			log.error("DatabaseException ... ", e);
			throw new ApplicationException("Exception : Exception in the findbyPk method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel FindbyPK method End");
		return bean;
	}

	/**
	 * Search Course.
	 *
	 * @param bean            : Search Parameters
	 * @return the list
	 * @throws ApplicationException the application exception
	 */

	public List search (CourseBean bean) throws ApplicationException{
	return search (bean,0,0);	
	}

	/**
	 * Search Course with pagination.
	 *
	 * @param bean            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */
	
	public List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>. search k ander");
		log.debug("CourseModel Search Method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");
		if(bean!=null){
			if(bean.getId()>0){
				sql.append(" AND id = " + bean.getId());
			}
			/*if(bean.getName()!= null && bean.getName().length()>0){
				sql.append(" AND Name like '" + bean.getName() +"%'");
			}*/
/*			if(bean.getDescription()!=null && bean.getName().length()>0){
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
			}
			if(bean.getDuration()!=null && bean.getDuration().length() >0){
				sql.append(" AND Duration like '" + bean.getDuration().length() + "%'");
			}*/
		}
		
		// if page size is greater than zero then apply pagination
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit " +pageNo+","+pageSize);	
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			System.out.println(sql);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next()){
				bean=new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
			    bean.setDuration(rs.getString(3));	
			    bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
				
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Database Exception ,,,," , e);
			throw new ApplicationException("Exception in the Search Method" + e.getMessage());
		
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel Search Method End");
		System.out.println("----------------------------------->>>>"+list.size());
		return list;
	}

	/**
	 * Get List of Course.
	 *
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */
	
	public List list () throws ApplicationException{
		return list(0,0);
	}
	
	/**
	 * Get List of Course with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("CourseModel List Method End");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE ");
	// if page size is greater than zero then apply pagination
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+ pageNo +" , "+pageSize);
		}
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt =conn.prepareStatement(sql.toString());
			ResultSet rs =pstmt.executeQuery();
			while (rs.next()) {
				CourseBean bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));	
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception in list ...",e);
			throw new ApplicationException("Exception : Exception in CourseModel List method " + e.getMessage());		
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel List Method End");
		return list;
	}
	
}
