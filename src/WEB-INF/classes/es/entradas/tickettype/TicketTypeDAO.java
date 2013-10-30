/*0
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.tickettype;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import es.entradas.utils.Utilidades;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Takuya
 */
public class TicketTypeDAO {

    DataBase dataBase = new DataBase();
    //private PropertyManager p = new PropertyManager("xxx.properties");
    //private static String ipservidor = PropertyManager.getInstance().getProperty("ipservidor");
    //private static String password = PropertyManager.getInstance().getProperty("password");

    public void modificarTicket(TicketTypeData ticketTypeData) {
        String sql = "UPDATE " + DataBase.getSchemeName() + ".tickettype"
                + " SET name = '" + ticketTypeData.getName() + "',"
                + " price = '" + ticketTypeData.getPrice() + "',"
                + " activo = " + ticketTypeData.isActivo()
                + " WHERE id = '" + ticketTypeData.getId() + "'";
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El tipo de ticket se ha actualizado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido actualizar el tipo de ticket.");
            Log.getInstance().log(sql);
        }
    }

    public void insertTicket(TicketTypeData ticketTypeData) {
        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".tickettype(name, price, activo)"
                + "VALUES ("
                + "'" + StringUtils.capitalize(ticketTypeData.getName()) + "', "
                + "'" + ticketTypeData.getPrice() + "', "
                + ticketTypeData.isActivo() + ")";
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El tipo de ticket se ha insertado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido insertar el nuevo tipo de ticket.");
            Log.getInstance().log(sql);
        }
    }
/*public void insertTicket(TicketTypeData ticketTypeData) {
        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".tickettype(name, price, category, activo)"
                + "VALUES ("
                + "'" + StringUtils.capitalize(ticketTypeData.getName()) + "', "
                + "'" + ticketTypeData.getPrice() + "', "
                + ticketTypeData.getCategory() + ","
                + ticketTypeData.isActivo() + ")";
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El tipo de ticket se ha actualizado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido actualizar el tipo de ticket.");
            Log.getInstance().log(sql);
        }
    }*/
    public ArrayList getTicketTypeOrderByCategory() {
        ArrayList resultAL = new ArrayList();
        String query = "select * from " + DataBase.getSchemeName() + ".tickettype order by category, name";
        Vector resultados = null;
        try {
            resultados = DataBase.executeQuery(query);
            TicketTypeData data = new TicketTypeData();
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    data = new TicketTypeData();
                    data.setName(((String[]) resultados.elementAt(i))[0]);
                    data.setPrice(((String[]) resultados.elementAt(i))[1]);
                    data.setCategory(((String[]) resultados.elementAt(i))[2]);
                    data.setId(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    if ((((String[]) resultados.elementAt(i))[4]).equals("t")) {
                        data.setActivo(true);
                    } else {
                        data.setActivo(false);
                    }
                    resultAL.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(query);
            Log.getInstance().log(e.getMessage());
        }
        return resultAL;
    }


    public ArrayList getTicketType(TicketTypeData ttData, String opcionActivo) {

        ArrayList result = new ArrayList();
        String query = "SELECT name, price, category, id, activo"
                + " FROM " + DataBase.getSchemeName() + ".tickettype"
                + " WHERE 1=1";
        if (opcionActivo.equals(TicketType.getACTIVO())) {
            query += " AND activo = " + true + "";
        } else if (opcionActivo.equals(TicketType.getNO_ACTIVO())) {
            query += " AND activo = " + false + "";
        }
        query += " ORDER BY name";

        Vector resultados = null;
        try {
            resultados = DataBase.executeQuery(query);
            TicketTypeData data = new TicketTypeData();
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    data = new TicketTypeData();
                    data.setName(((String[]) resultados.elementAt(i))[0]);
                    data.setPrice(((String[]) resultados.elementAt(i))[1]);
                    data.setCategory(((String[]) resultados.elementAt(i))[2]);
                    data.setId(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    if ((((String[]) resultados.elementAt(i))[4]).equals("t")) {
                        data.setActivo(true);
                    } else {
                        data.setActivo(false);
                    }
                    //data.setNombreCategoria(((String[]) resultados.elementAt(i))[5]);
                    result.add(data);
                }
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
        }
        return result;
    }
  

    public void updateTicket(TicketTypeData ticketTypeData) {
        String sql = "UPDATE " + DataBase.getSchemeName() + ".tickettype"
                + " SET name = '" + ticketTypeData.getName() + "',"
                + " price = '" + ticketTypeData.getPrice() + "',"
                + " category = " + ticketTypeData.getCategory()
                + " WHERE id = " + ticketTypeData.getId();
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El tipo de ticket se ha actualizado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido actualizar el tipo de ticket.");
            Log.getInstance().log(sql);
        }
    }
}
