package in.co.rays.ors.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.co.rays.ors.exception.ApplicationException;

// TODO: Auto-generated Javadoc
/**
 * JDBC DataSource is a Data Connection Pool.
 *
 *@author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */

public final class JDBCDataSource {

    /**
     * Instantiates a new JDBC data source.
     */
    private JDBCDataSource() {
    }

    /** JDBC Database connection pool ( DCP ). */
    private static JDBCDataSource datasource;


    /** The cpds. */
    private ComboPooledDataSource cpds = null;

    /**
     * Create instance of Connection Pool.
     *
     * @return single instance of JDBCDataSource
     */
    public static JDBCDataSource getInstance() {
        if (datasource == null) {

            ResourceBundle rb = ResourceBundle
                    .getBundle("in.co.rays.ors.bundle.system");

            datasource = new JDBCDataSource();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
                datasource.cpds.setJdbcUrl(rb.getString("url"));
                datasource.cpds.setUser(rb.getString("username"));
                datasource.cpds.setPassword(rb.getString("password"));
                datasource.cpds.setInitialPoolSize(DataUtility.getInt (rb.getString("initialPoolSize")));
                datasource.cpds.setAcquireIncrement(DataUtility.getInt(rb.getString("acquireIncrement")));
                datasource.cpds.setMaxPoolSize(DataUtility.getInt(rb.getString("maxPoolSize")));
                datasource.cpds.setMinPoolSize(DataUtility.getInt(rb.getString("minPoolSize")));
                datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
       
        }
        return datasource;
    }

    /**
     * Gets the connection from ComboPooledDataSource.
     *
     * @return connection
     * @throws Exception the exception
     */
    public static Connection getConnection() throws Exception {
        return getInstance().cpds.getConnection();
    }

    /**
     * Closes a connection.
     *
     * @param connection the connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Trn rollback.
     *
     * @param connection the connection
     * @throws ApplicationException the application exception
     */
    public static void trnRollback(Connection connection)
            throws ApplicationException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new ApplicationException(ex.toString());
            }
        }
    }

}
