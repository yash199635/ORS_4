package in.co.rays.ors.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.Logger;


import in.co.rays.ors.bean.DropdownListBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DatabaseException;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.JDBCDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseModel.
 */
public abstract class BaseModel implements Serializable, DropdownListBean,
Comparable {

/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

/** The log. */
private static Logger log = Logger.getLogger(BaseModel.class);

/** Non Business primary key. */
protected long id;

/**
* User name that creates this record.
*/
protected String createdBy;

/**
* User name that modifies this record.
*/
protected String modifiedBy;

/** Created timestamp of record. */
protected Timestamp createdDatetime;

/** Modified timestamp of record. */
protected Timestamp modifiedDatetime;

/**
 * accessor methods.
 *
 * @return the id
 */
public long getId() {
return id;
}

/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(long id) {
this.id = id;
}

/**
 * Gets the created datetime.
 *
 * @return the created datetime
 */
public Timestamp getCreatedDatetime() {
return createdDatetime;
}

/**
 * Sets the created datetime.
 *
 * @param createdDatetime the new created datetime
 */
public void setCreatedDatetime(Timestamp createdDatetime) {
this.createdDatetime = createdDatetime;
}

/**
 * Gets the modified datetime.
 *
 * @return the modified datetime
 */
public Timestamp getModifiedDatetime() {
return modifiedDatetime;
}

/**
 * Sets the modified datetime.
 *
 * @param modifiedDatetime the new modified datetime
 */
public void setModifiedDatetime(Timestamp modifiedDatetime) {
this.modifiedDatetime = modifiedDatetime;
}

/**
 * Gets the created by.
 *
 * @return the created by
 */
public String getCreatedBy() {
return createdBy;
}

/**
 * Sets the created by.
 *
 * @param createdBy the new created by
 */
public void setCreatedBy(String createdBy) {
this.createdBy = createdBy;
}

/**
 * Gets the modified by.
 *
 * @return the modified by
 */
public String getModifiedBy() {
return modifiedBy;
}

/**
 * Sets the modified by.
 *
 * @param modifiedBy the new modified by
 */
public void setModifiedBy(String modifiedBy) {
this.modifiedBy = modifiedBy;
}

/**
 * Compares IDs ( Primary Key). If keys are equals then objects are equals.
 *
 * @param next the next
 * @return the int
 */
public int compareTo(BaseModel next) {
return (int) (id - next.getId());
}

/**
 * created next PK of record.
 *
 * @return the long
 * @throws DatabaseException the database exception
 */

public long nextPK() throws DatabaseException {
log.debug("Model nextPK Started");
Connection conn = null;
long pk = 0;
try {
    conn = JDBCDataSource.getConnection();
    PreparedStatement pstmt = conn
            .prepareStatement("SELECT MAX(ID) FROM " + getTableName());
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
 * Gets table name of Model.
 *
 * @return the table name
 */
public abstract String getTableName();

/**
 * Updates created by info.
 *
 * @throws ApplicationException the application exception
 */
public void updateCreatedInfo() throws ApplicationException {

log.debug("Model update Started..." + createdBy);

Connection conn = null;

String sql = "UPDATE " + getTableName()
        + " SET CREATED_BY=?, CREATED_DATETIME=? WHERE ID=?";
log.debug(sql);

try {
    conn = JDBCDataSource.getConnection();
    conn.setAutoCommit(false); // Begin transaction
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, createdBy);
    pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
    pstmt.setLong(3, id);

    pstmt.executeUpdate();
    conn.commit(); // End transaction
    pstmt.close();
} catch (SQLException e) {
    log.error("Database Exception..", e);
    JDBCDataSource.trnRollback(conn);
    throw new ApplicationException(e.toString());
} catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} finally {
    JDBCDataSource.closeConnection(conn);
}
log.debug("Model update End");
}

/**
 * Updates modified by info.
 *
 * @throws Exception the exception
 */
public void updateModifiedInfo() throws Exception {

log.debug("Model update Started");
Connection conn = null;

String sql = "UPDATE " + getTableName()
        + " SET MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?";

try {
    conn = JDBCDataSource.getConnection();
    conn.setAutoCommit(false); // Begin transaction
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, modifiedBy);
    pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
    pstmt.setLong(3, id);
    pstmt.executeUpdate();
    conn.commit(); // End transaction
    pstmt.close();
} catch (SQLException e) {
    log.error("Database Exception..", e);
    JDBCDataSource.trnRollback(conn);
} finally {
    JDBCDataSource.closeConnection(conn);
}
log.debug("Model update End");
}

/**
 * Populate Model from ResultSet.
 *
 * @param <T> the generic type
 * @param model the model
 * @param rs the rs
 * @return the t
 * @throws SQLException the SQL exception
 */
protected <T extends BaseModel> T populateModel(T model, ResultSet rs)
    throws SQLException {
model.setId(rs.getLong("ID"));
model.setCreatedBy(rs.getString("CREATED_BY"));
model.setModifiedBy(rs.getString("MODIFIED_BY"));
model.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
model.setModifiedDatetime(rs.getTimestamp("MODIFIED_DATETIME"));
return model;
}
}

