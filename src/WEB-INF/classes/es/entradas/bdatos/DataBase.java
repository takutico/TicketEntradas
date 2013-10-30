package es.entradas.bdatos;

import java.sql.*;
import java.util.Vector;

import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import es.entradas.utils.Utilidades;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private static String propertiesfile = "Database.properties";
    private static String ipservidor = PropertyManager.getInstance().getProperty("ipservidor");
    private static String password = PropertyManager.getInstance().getProperty("password");
    private static String databaseName = "";
    private static String schemeName = "";
    private static DataBase database = null;
    public static final int OPERACION_ERROR = -1;
    public static final int OPERACION_OK = 1;

    public static String getSchemeName() {
        if (schemeName.equals("")) {
            schemeName = PropertyManager.getInstance().getProperty("scheme", propertiesfile);
        }
        return schemeName;
    }

    public static void setSchemeName(String aSchemeName) {
        schemeName = aSchemeName;
    }

    public DataBase() {
        try {
            new DataBase(null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new instance of DataBase
     */
    private DataBase(String propertiesfile) throws ClassNotFoundException {
        if (propertiesfile != null) {
            DataBase.propertiesfile = propertiesfile;
        } else {
            DataBase.propertiesfile = "Database.properties";
        }

        try {
            databaseName = PropertyManager.getInstance().getProperty("database", propertiesfile);
        } catch (Exception e) {
            databaseName = "xxx";
        }
    }

    /**
     * Se obtiene o se crea una nueva instacia de DataBase
     */
    synchronized public static DataBase getInstance(String propertiesfile) throws ClassNotFoundException {
        if (database == null) {
            database = new DataBase(propertiesfile);
        }
        return database;

    }

    public static DataBase getInstance() throws ClassNotFoundException {
        return getInstance(null);
    }

    /** Obtiene la conexion con la base de datos
     *
     * @param autocomited
     * @return
     * @throws Exception
     */
    public Connection dbConnection(boolean autocomited) throws Exception {
        return dbConnectionPostgresql(autocomited);
    }

    public Connection dbConnectionPostgresql(boolean autocomited) throws SQLException, Exception {
        PostgreSQLDataSource ds = PostgreSQLDataSource.getInstance(propertiesfile);

        Connection con = ds.getConnection();

        if (con.isClosed()) {
            throw new Exception("Error al abrir la conexion a la bbdd: connexion cerrada");
        }

        con.setAutoCommit(autocomited);

        return con;
    }

    public static int executeUpdate(String sql) {
        String url = "jdbc:postgresql://" + ipservidor + "/postgres";
        Connection con = null;
        Statement stmt = null;
        int resultOperacion = OPERACION_OK;
        try {
            //Acceso al Driver
            Class.forName("org.postgresql.Driver");
            //La conexión con los parámetros necesarios
            con = DriverManager.getConnection(url, "postgres", password);
            //Abrimos la conexión y la iniciamos
            stmt = con.createStatement();
            //Ejecutamos la consulta SQL
            Log.getInstance().log(sql);
            stmt.executeUpdate(sql);
            //Cerramos la conexión
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
            resultOperacion = OPERACION_ERROR;
        } finally {
            try {
                stmt.execute("END");
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
        }
        return resultOperacion;
    }

    public static int executeInsertAndGetId(String sql) {
        String url = "jdbc:postgresql://" + ipservidor + "/postgres";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int resultadoInt = -1;
        try {
            //Acceso al Driver
            Class.forName("org.postgresql.Driver");
            //La conexión con los parámetros necesarios
            con = DriverManager.getConnection(url, "postgres", password);
            //Abrimos la conexión y la iniciamos
            stmt = con.createStatement();

            //Ejecutamos la consulta SQL
            Log.getInstance().log(sql);
            stmt.executeUpdate(sql);

            sql = "SELECT last_value FROM xxx.ticket_idticket_seq1";
            rs = stmt.executeQuery(sql);
            if (rs != null && rs.next()) {
                resultadoInt = rs.getInt(1);
            }
            //Cerramos la conexión
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
        } finally {
            try {
                stmt.execute("END");
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Log.getInstance().log(ex.getMessage());
            }
        }
        return resultadoInt;
    }

    public static Vector executeQuery(String query) {
        String url = "jdbc:postgresql://" + ipservidor + "/postgres";
        Connection con = null;
        Statement stmt = null;
        Vector result = new Vector();
        try {
            //Acceso al Driver
            Class.forName("org.postgresql.Driver");
            //La conexión con los parámetros necesarios
            con = DriverManager.getConnection(url, "postgres", password);
            //Abrimos la conexión y la iniciamos
            stmt = con.createStatement();
            /*Un ResultSet es como en .NET un DataSet, un arreglo temporal donde se almacenará el resultado de la consulta SQL*/
            ResultSet rs;
            //En el ResultSet guardamos el resultado de ejecutar la consulta
            Log.getInstance().log(query);
            rs = stmt.executeQuery(query);
            java.sql.ResultSetMetaData md = rs.getMetaData();
            //Se obtiene el numero de columnas
            int ColumnCount = md.getColumnCount();
            //En un ciclo while recorremos cada fila del resultado de nuestro Select
            while ((rs != null) && rs.next()) {
                String ColumnValues[] = new String[ColumnCount];
                for (int i = 0; i < ColumnCount; i++) {
                    ColumnValues[i] = rs.getString(i + 1);
                }
                result.add(ColumnValues);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            Log.getInstance().log(e.getMessage());
            Utilidades.mostrarError("", "Ha ocurrido un error con la base de datos. Contacte con su administrador de sistemas.");
            //Se cierran las conexiones con la base de datos
        } finally {
            try {
                stmt.execute("END");
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }
}
