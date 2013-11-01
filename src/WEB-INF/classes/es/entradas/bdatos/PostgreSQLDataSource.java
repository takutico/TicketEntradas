/*
 * PostgreSQLDataSource.java
 *
 * Created on 25 October 2007, 11:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.entradas.bdatos;

import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.ds.PGPoolingDataSource;

public class PostgreSQLDataSource {
    private PGPoolingDataSource ds = null;
    private static PostgreSQLDataSource thisInstance = null;
    
    /* The name used to identify the cache uniquely */
    private static final String CACHE_NAME = "Data";
    
    /** Creates a new instance of PostgreSQLDataSource */
    private PostgreSQLDataSource(String propertiesfile) {
        initializeDataSource(propertiesfile);
    }
    
    public static synchronized PostgreSQLDataSource getInstance(String propertiesfile) {
        if (thisInstance == null) {
            thisInstance = new PostgreSQLDataSource(propertiesfile);
        }
        return thisInstance;
    }
    
    private void initializeDataSource(String propertiesfile) {
        if (ds == null)
            ds = new PGPoolingDataSource();
        
        /* Set the cache name */
        ds.setDataSourceName(CACHE_NAME);
       
        try{
            ds.setServerName(PropertyManager.getInstance().getProperty("servername",propertiesfile));
            ds.setDatabaseName(PropertyManager.getInstance().getProperty("servicename",propertiesfile));
            ds.setUser(PropertyManager.getInstance().getProperty("user",propertiesfile));
            ds.setPassword(PropertyManager.getInstance().getProperty("passwd",propertiesfile));
        } catch (Exception e){
            Log.getInstance().log("Error con el properties? " + e.toString());
        }

        try{
            ds.setPortNumber(Integer.parseInt(PropertyManager.getInstance().getProperty("port",propertiesfile)));
        } catch (Exception e){
            Log.getInstance().log("Error con el puerto " + e.toString());
        }
        
       /* Set Max Limit for the Cache.
        * This sets the maximum number of PooledConnections the cache
        * can hold. There is no default MaxLimit assumed meaning connections
        * in the cache could reach as many as the database allows.
        */
        try{
            String value = PropertyManager.getInstance().getProperty("MaxLimit",propertiesfile);
            ds.setMaxConnections(Integer.parseInt(value));
        }catch (Exception e){
            ds.setMaxConnections(30);
        }
        
       /* Set the Initial Limit.
        * This sets the size of the connection cache when the cache is
        * initially created or reinitialized. When this property is set to
        * a value greater than 0, that many connections are pre-created and
        * are ready for use.
        */
        try{
            String value = PropertyManager.getInstance().getProperty("InitialLimit",propertiesfile);
            ds.setInitialConnections(Integer.parseInt(value));
        }catch (Exception e){
            ds.setInitialConnections(20);
        }        
    }
    
    public Connection getConnection() throws SQLException {
        if (ds == null)
            return null;
        return ds.getConnection();
    }
}
