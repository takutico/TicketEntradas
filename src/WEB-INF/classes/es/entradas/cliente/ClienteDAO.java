/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.cliente;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.Utilidades;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author User
 */
public class ClienteDAO {

    public ArrayList getClientes(ClienteData clienteData) {
        ArrayList resultAL = new ArrayList();
        String tmp = "";
        String query = "SELECT id, nombre, apellidos, nif, direccion, localidad, provincia,"
                + " pais, cp, nacionalidad, email, email_2, telefono, movil,"
                + " oferta_email, oferta_sms, oferta_postal, activo"
                + " FROM " + DataBase.getSchemeName() + ".client"
                + " WHERE 1 = 1";
        if (!clienteData.getNombre().equals("")) {
            query += " AND UPPER(nombre) like '%" + clienteData.getNombre().toUpperCase() + "%'";
        }
        if (!clienteData.getApellidos().equals("")) {
            query += " AND UPPER(apellidos) like '%" + clienteData.getApellidos().toUpperCase() + "%'";
        }
        if (!clienteData.getNif().equals("")) {
            query += " AND UPPER(nif) like '%" + clienteData.getNif().toUpperCase() + "%'";
        }
        if (!clienteData.getEmail().equals("")) {
            query += " AND UPPER(email) like '%" + clienteData.getEmail().toUpperCase() + "%'";
        }
        if (!clienteData.getTelefono().equals("")) {
            query += " AND UPPER(telefono) like '%" + clienteData.getTelefono().toUpperCase() + "%'";
        }
        if (!clienteData.getMovil().equals("")) {
            query += " AND UPPER(movil) like '%" + clienteData.getMovil().toUpperCase() + "%'";
        }
        if (clienteData.getOpcionActivo().equals("ACTIVO")) {
            query +=  " AND activo = " + true + "";
        } else if(clienteData.getOpcionActivo().equals("NO_ACTIVO")){
            query +=  " AND activo = " + false + "";
        }
        query += " ORDER BY apellidos, nombre, nif";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    ClienteData data = new ClienteData();
                    data.setId(Integer.parseInt(((String[]) resultados.elementAt(i))[0]));
                    data.setNombre(((String[]) resultados.elementAt(i))[1]);
                    data.setApellidos(((String[]) resultados.elementAt(i))[2]);
                    data.setNif(((String[]) resultados.elementAt(i))[3]);
                    data.setDireccion(((String[]) resultados.elementAt(i))[4]);
                    data.setLocalidad(((String[]) resultados.elementAt(i))[5]);
                    data.setProvincia(((String[]) resultados.elementAt(i))[6]);
                    data.setPais(((String[]) resultados.elementAt(i))[7]);
                    data.setCp(((String[]) resultados.elementAt(i))[8]);
                    data.setNacionalidad(((String[]) resultados.elementAt(i))[9]);
                    data.setEmail(((String[]) resultados.elementAt(i))[10]);
                    data.setEmail_2(((String[]) resultados.elementAt(i))[11]);
                    data.setTelefono(((String[]) resultados.elementAt(i))[12]);
                    data.setMovil(((String[]) resultados.elementAt(i))[13]);
                    tmp = ((String[]) resultados.elementAt(i))[14];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaEmail(true);
                    } else {
                        data.setOfertaEmail(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[15];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaSms(true);
                    } else {
                        data.setOfertaSms(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[16];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaPostal(true);
                    } else {
                        data.setOfertaPostal(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[17];
                    if(tmp.toUpperCase().startsWith("T")){
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
            Log.getInstance().log(e.getMessage());
        }
        return resultAL;
    }

    public ArrayList getClientesDialog(ClienteData clienteData) {
        ArrayList resultAL = new ArrayList();
        String tmp = "";
        String query = "SELECT id, nombre, apellidos, nif, direccion, localidad, provincia,"
                + " pais, cp, nacionalidad, email, email_2, telefono, movil,"
                + " oferta_email, oferta_sms, oferta_postal, activo"
                + " FROM " + DataBase.getSchemeName() + ".client"
                + " WHERE 1 = 1";
        if (!clienteData.getNombre().equals("")) {
            query += " AND UPPER(nombre) like '%" + clienteData.getNombre().toUpperCase() + "%'";
        }
        if (!clienteData.getApellidos().equals("")) {
            query += " AND UPPER(apellidos) like '%" + clienteData.getApellidos().toUpperCase() + "%'";
        }
        if (!clienteData.getNif().equals("")) {
            query += " AND UPPER(nif) like '%" + clienteData.getNif().toUpperCase() + "%'";
        }
        if (!clienteData.getEmail().equals("")) {
            query += " AND UPPER(email) like '%" + clienteData.getEmail().toUpperCase() + "%'";
        }
        if (!clienteData.getTelefono().equals("")) {
            query += " AND UPPER(telefono) like '%" + clienteData.getTelefono().toUpperCase() + "%'";
        }
        if (!clienteData.getMovil().equals("")) {
            query += " AND UPPER(movil) like '%" + clienteData.getMovil().toUpperCase() + "%'";
        }
        if (clienteData.isActivo()) {
            query +=  " AND activo = " + true + "";
        }
        if (clienteData.isOfertaEmail()) {
            query +=  " AND oferta_email = " + true + "";
        }
        query += " ORDER BY apellidos, nombre, nif";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    ClienteData data = new ClienteData();
                    data.setId(Integer.parseInt(((String[]) resultados.elementAt(i))[0]));
                    data.setNombre(((String[]) resultados.elementAt(i))[1]);
                    data.setApellidos(((String[]) resultados.elementAt(i))[2]);
                    data.setNif(((String[]) resultados.elementAt(i))[3]);
                    data.setDireccion(((String[]) resultados.elementAt(i))[4]);
                    data.setLocalidad(((String[]) resultados.elementAt(i))[5]);
                    data.setProvincia(((String[]) resultados.elementAt(i))[6]);
                    data.setPais(((String[]) resultados.elementAt(i))[7]);
                    data.setCp(((String[]) resultados.elementAt(i))[8]);
                    data.setNacionalidad(((String[]) resultados.elementAt(i))[9]);
                    data.setEmail(((String[]) resultados.elementAt(i))[10]);
                    data.setEmail_2(((String[]) resultados.elementAt(i))[11]);
                    data.setTelefono(((String[]) resultados.elementAt(i))[12]);
                    data.setMovil(((String[]) resultados.elementAt(i))[13]);
                    tmp = ((String[]) resultados.elementAt(i))[14];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaEmail(true);
                    } else {
                        data.setOfertaEmail(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[15];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaSms(true);
                    } else {
                        data.setOfertaSms(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[16];
                    if(tmp.toUpperCase().startsWith("T")){
                        data.setOfertaPostal(true);
                    } else {
                        data.setOfertaPostal(false);
                    }
                    tmp = ((String[]) resultados.elementAt(i))[17];
                    if(tmp.toUpperCase().startsWith("T")){
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
            Log.getInstance().log(e.getMessage());
        }
        return resultAL;
    }

    public void insertCliente(ClienteData data) {
        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".client("
                + " nombre, apellidos, nif, direccion, localidad, provincia,"
                + " pais, cp, nacionalidad, email, email_2, telefono, movil, oferta_email,"
                + " oferta_sms, oferta_postal)"
                + " VALUES ('" + StringUtils.capitalize(data.getNombre()) + "',"
                + " '" + StringUtils.capitalize(data.getApellidos()) + "',"
                + " '" + StringUtils.capitalize(data.getNif()) + "',"
                + " '" + StringUtils.capitalize(data.getDireccion()) + "',"
                + " '" + StringUtils.capitalize(data.getLocalidad()) + "',"
                + " '" + StringUtils.capitalize(data.getProvincia()) + "',"
                + " '" + StringUtils.capitalize(data.getPais()) + "',"
                + " '" + StringUtils.capitalize(data.getCp()) + "',"
                + " '" + StringUtils.capitalize(data.getNacionalidad()) + "',"
                + " '" + data.getEmail() + "',"
                + " '" + data.getEmail_2() + "',"
                + " '" + StringUtils.capitalize(data.getTelefono()) + "',"
                + " '" + StringUtils.capitalize(data.getMovil()) + "',"
                + " '" + data.isOfertaEmail() + "',"
                + " '" + data.isOfertaSms() + "',"
                + " '" + data.isOfertaPostal() + "')";

        if (DataBase.executeUpdate(sql) == DataBase.OPERACION_OK) {
            Utilidades.mostrarInformacion("", "El cliente se ha insertado correctamente.");
        } else {
            Utilidades.mostrarError("", "No se ha podido insertar el cliente.");
            Log.getInstance().log(sql);
        }
    }

    public void modificarCliente(ClienteData data) {
        String query = "UPDATE " + DataBase.getSchemeName() + ".client"
                + " SET nombre = '" + data.getNombre() + "',"
                + " apellidos = '" + data.getApellidos() + "',"
                + " nif = '" + data.getNif() + "',"
                + " direccion = '" + data.getDireccion() + "',"
                + " localidad = '" + data.getLocalidad() + "',"
                + " provincia = '" + data.getProvincia() + "',"
                + " pais = '" + data.getPais() + "',"
                + " cp = '" + data.getCp() + "',"
                + " nacionalidad = '" + data.getNacionalidad() + "',"
                + " email = '" + data.getEmail() + "',"
                + " email_2 = '" + data.getEmail_2() + "',"
                + " telefono = '" + data.getTelefono() + "',"
                + " movil = '" + data.getMovil() + "',"
                + " oferta_email = '" + data.isOfertaEmail() + "',"
                + " oferta_sms = '" + data.isOfertaSms() + "',"
                + " oferta_postal = '" + data.isOfertaPostal() + "',"
                + " activo = '" + data.isActivo() + "'"
                + " WHERE id = '" + data.getId() + "'";

        if (DataBase.executeUpdate(query) == DataBase.OPERACION_OK) {
            Utilidades.mostrarInformacion("", "Los datos del cliente se han actualizado.");
        } else {
            Utilidades.mostrarError("", "No se han podido actualizar los datos del cliente.");
            Log.getInstance().log(query);
        }
    }

    public void eliminarCliente(int idCliente) {
        String query = "DELETE FROM " + DataBase.getSchemeName() + ".client"
                + " WHERE id = " + idCliente;
        if(DataBase.executeUpdate(query) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El cliente se ha borrado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido borrar el cliente.");
            Log.getInstance().log(query);
        }
    }
}
