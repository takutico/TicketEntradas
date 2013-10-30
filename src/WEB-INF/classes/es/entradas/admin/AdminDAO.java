/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.admin;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AdminDAO {
    
    private DataBase dataBase = new DataBase();
    private static String ipservidor = PropertyManager.getInstance().getProperty("ipservidor");
    private static String password = PropertyManager.getInstance().getProperty("password");
    private static final int OPERACION_OK = 1;
    private static final int OPERACION_ERROR = 0;

    public int restaurarPass(AdminData adminData){
        int resultadoOperacion = OPERACION_OK;
        String sql = "UPDATE xxx.administrator" +
                " SET pass='1234'" +
                " WHERE idadmin = " + adminData.getIdAdmin();
        DataBase.executeUpdate(sql);
        return resultadoOperacion;
    }

    public ArrayList getAdministradores(){
        ArrayList resultAL = new ArrayList();
        Vector resultados = new Vector();
        String sql = "SELECT idadmin, adminname, pass, nombre, apellidos, nif FROM xxx.administrator;";
        try {
            resultados = DataBase.executeQuery(sql);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    AdminData data = new AdminData();
                    data.setIdAdmin(Integer.parseInt(((String[]) resultados.elementAt(i))[0]));
                    data.setAdminLogin(((String[]) resultados.elementAt(i))[1]);
                    data.setAdminPass(((String[]) resultados.elementAt(i))[2]);
                    data.setNombre(((String[]) resultados.elementAt(i))[3]);
                    data.setApellidos(((String[]) resultados.elementAt(i))[4]);
                    data.setNif(((String[]) resultados.elementAt(i))[5]);
                    resultAL.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
        }
        return resultAL;
    }

    public int modificarAdmin(AdminData adminData){
        int resultadoOperacion = OPERACION_OK;
        String sql = "UPDATE xxx.administrator" +
                " SET adminname='" + adminData.getAdminLogin() + "'," +
                " nombre='" + adminData.getNombre() + "'," +
                " apellidos='" + adminData.getApellidos() + "'," +
                " nif='" + adminData.getNif() + "'" +
                " WHERE idadmin='" + adminData.getIdAdmin() + "'";
        DataBase.executeUpdate(sql);
        return resultadoOperacion;
    }

    public void insertAdmin(AdminData admindata) {
        String sql = "INSERT INTO xxx.administrator(adminname, pass)"
                + " VALUES ("
                + "'" + admindata.getAdminLogin() + "', "
                + "'" + admindata.getAdminPass() + "')";
        DataBase.executeUpdate(sql);
    }

    public int loginAdmin(AdminData adminData){


        /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
        //postgres es el nombre de la base de datos
        String url = "jdbc:postgresql://" + PropertyManager.getInstance().getProperty("ipservidor") + "/postgres";
        //ArrayList result = new ArrayList();
        int result = 0;
        Connection con = null;
        Statement stmt = null;
        try {

//Acceso al Driver
            Class.forName("org.postgresql.Driver");

//La conexión con los parámetros necesarios
            con = DriverManager.getConnection(url, "postgres", password);

//Abrimos la conexión y la iniciamos
             stmt = con.createStatement();

            /*Un ResultSet es como en .NET un DataSet, un arreglo temporal donde se almacenará el resultado de la consulta SQL*/
            ResultSet rs;

//Una variable String para almacenar la sentencia SQL
            String query = "select idadmin "
                    + " from xxx.administrator"
                    + " where adminname = '" + adminData.getAdminLogin() + "'";

//En el ResultSet guardamos el resultado de ejecutar la consulta
            rs = stmt.executeQuery(query);
            rs.next();
            result = Integer.parseInt(rs.getString("idadmin"));
//En un ciclo while recorremos cada fila del resultado de nuestro Select
            //while (rs.next()) {
                //idType = Integer.parseInt(rs.getString("id"));
                /*Aqui practicamente podemos hacer lo que deseemos con el resultado, en mi caso solo lo mande a imprimir*/
                //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
                //Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category"))
                //result.add(new TicketData(Integer.parseInt(rs.getString("people")), rs.getString("name"), rs.getString("date"), rs.getString("hour")));
            //}
            /*
            id integer NOT NULL,
            "name" character varying(50) NOT NULL,
            price character varying(10) NOT NULL,
            category integer NOT NULL,
             */
//Cerramos la conexión

        } catch (Exception e) {

//Por si ocurre un error
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
            return -1;
        } finally {
            try {
                stmt.execute("END");
            } catch (SQLException ex) {
                Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;

    }

}
