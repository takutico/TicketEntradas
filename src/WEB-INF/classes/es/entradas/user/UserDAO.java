/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.user;

import es.entradas.bdatos.DataBase;
//import es.entradas.utils.Email;
import es.entradas.publicidad.Email;
import es.entradas.utils.Log;
import es.entradas.utils.Utilidades;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author User
 */
public class UserDAO {

    private static final int OPERACION_OK = 1;
    private static final int OPERACION_ERROR = 0;

    public ArrayList getUsersAL(UserData userData) {
        ArrayList resultAL = new ArrayList();
        Vector resultados = new Vector();
        String sql = "SELECT id, nombre, apellidos, nif, direccion, localidad, provincia, pais, cp,"
                + " email, telefono, movil, login, pass, admin, activo"
                + " FROM " + DataBase.getSchemeName() + ".usuario"
                + " WHERE 1 = 1";
        if (!userData.getNombre().equals("")) {
            sql += " AND upper(nombre) LIKE '%" + userData.getNombre().toUpperCase() + "%'";
        }
        if (!userData.getApellidos().equals("")) {
            sql += " AND upper(apellidos) LIKE '%" + userData.getApellidos().toUpperCase() + "%'";
        }
        if (!userData.getNif().equals("")) {
            sql += " AND upper(nif) LIKE '%" + userData.getNif().toUpperCase() + "%'";
        }
        if (!userData.getEmail().equals("")) {
            sql += " AND upper(email) LIKE '%" + userData.getEmail().toUpperCase() + "%'";
        }
        if (!userData.getMovil().equals("")) {
            sql += " AND upper(movil) LIKE '%" + userData.getMovil().toUpperCase() + "%'";
        }
        if (!userData.getTelefono().equals("")) {
            sql += " AND upper(telefono) LIKE '%" + userData.getTelefono().toUpperCase() + "%'";
        }

        try {
            resultados = DataBase.executeQuery(sql);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    UserData data = new UserData();
                    data.setId(Integer.parseInt(((String[]) resultados.elementAt(i))[0]));
                    data.setNombre(((String[]) resultados.elementAt(i))[1]);
                    data.setApellidos(((String[]) resultados.elementAt(i))[2]);
                    data.setNif(((String[]) resultados.elementAt(i))[3]);
                    data.setDireccion(((String[]) resultados.elementAt(i))[4]);
                    data.setLocalidad(((String[]) resultados.elementAt(i))[5]);
                    data.setProvincia(((String[]) resultados.elementAt(i))[6]);
                    data.setPais(((String[]) resultados.elementAt(i))[7]);
                    data.setCp(((String[]) resultados.elementAt(i))[8]);
                    data.setEmail(((String[]) resultados.elementAt(i))[9]);
                    data.setTelefono(((String[]) resultados.elementAt(i))[10]);
                    data.setMovil(((String[]) resultados.elementAt(i))[11]);
                    data.setLogin(((String[]) resultados.elementAt(i))[12]);
                    data.setPass(((String[]) resultados.elementAt(i))[13]);
                    String adminTmp = ((String[]) resultados.elementAt(i))[14];
                    if(adminTmp.equals("t") || adminTmp.equals("T")){
                        data.setAdmin(true);
                    } else {
                        data.setAdmin(false);
                    }
                    String activoTmp = ((String[]) resultados.elementAt(i))[15];
                    if(activoTmp.equals("t") || activoTmp.equals("T")){
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

    public void insertUser(UserData userData) {

        String nombre = "NULL";
        String apellidos = "NULL";
        String nif = "NULL";
        String direccion = "NULL";
        String localidad = "NULL";
        String provincia = "NULL";
        String pais = "NULL";
        String cp = "NULL";
        String email = "NULL";
        String telefono = "NULL";
        String movil = "NULL";
        String login = "NULL";
        String pass = "NULL";
        
        if(!userData.getNombre().equals("")){
            nombre = "'" + StringUtils.capitalize(userData.getNombre()) + "'";
        }
        if(!userData.getApellidos().equals("")){
            apellidos = "'" + StringUtils.capitalize(userData.getApellidos()) + "'";
        }
        if(!userData.getNif().equals("")){
            nif = "'" + userData.getNif().toUpperCase() + "'";
        }
        if(!userData.getDireccion().equals("")){
            direccion = "'" + StringUtils.capitalize(userData.getDireccion()) + "'";
        }
        if(!userData.getLocalidad().equals("")){
            localidad = "'" + StringUtils.capitalize(userData.getLocalidad()) + "'";
        }
        if(!userData.getProvincia().equals("")){
            provincia = "'" + StringUtils.capitalize(userData.getProvincia()) + "'";
        }
        if(!userData.getPais().equals("")){
            pais = "'" + StringUtils.capitalize(userData.getPais()) + "'";
        }
        if(!userData.getCp().equals("")){
            cp = "'" + StringUtils.capitalize(userData.getCp()) + "'";
        }
        if(!userData.getEmail().equals("")){
            email = "'" + userData.getEmail() + "'";
        }
        if(!userData.getTelefono().equals("")){
            telefono = "'" + userData.getTelefono() + "'";
        }
        if(!userData.getMovil().equals("")){
            movil = "'" + userData.getMovil() + "'";
        }
        if(!userData.getLogin().equals("")){
            login = "'" + userData.getLogin() + "'";
        }
        if(!userData.getPass().equals("")){
            pass = "'" + userData.getPass() + "'";
        }

        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".usuario(nombre, apellidos, nif,"
                + " direccion, localidad, provincia, pais, cp, email, telefono, movil,"
                + "login, pass, admin, activo) VALUES ("
                + nombre + ", "
                + apellidos + ", "
                + nif + ", "
                + direccion + ", "
                + localidad + ", "
                + provincia + ", "
                + pais + ", "
                + cp + ", "
                + email + ", "
                + telefono + ", "
                + movil + ", "
                + login + ", "
                + pass + ", "
                + "'" + userData.isAdmin() + "', "
                + "'" + userData.isActivo() + "')";
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El usuario se ha insertado correctamente.");
            Email.send("Gestión de entradas", userData.getEmail(), "Su contraseña para la aplicación es: " + pass, null);
        } else{
           Utilidades.mostrarError("", "No se ha podido insertar el usuario.");
           Log.getInstance().log(sql);
        }
    }

    public void modificarUser(UserData userData) {

        String nombre = "NULL";
        String apellidos = "NULL";
        String nif = "NULL";
        String direccion = "NULL";
        String localidad = "NULL";
        String provincia = "NULL";
        String pais = "NULL";
        String cp = "NULL";
        String email = "NULL";
        String telefono = "NULL";
        String movil = "NULL";
        String login = "NULL";
        String pass = "NULL";

        if(!userData.getNombre().equals("")){
            nombre = "'" + StringUtils.capitalize(userData.getNombre()) + "'";
        }
        if(!userData.getApellidos().equals("")){
            apellidos = "'" + StringUtils.capitalize(userData.getApellidos()) + "'";
        }
        if(!userData.getNif().equals("")){
            nif = "'" + userData.getNif().toUpperCase() + "'";
        }
        if(userData.getDireccion() != null && !userData.getDireccion().equals("")){
            direccion = "'" + StringUtils.capitalize(userData.getDireccion()) + "'";
        }
        if(userData.getLocalidad() != null && !userData.getLocalidad().equals("")){
            localidad = "'" + StringUtils.capitalize(userData.getLocalidad()) + "'";
        }
        if(userData.getProvincia() != null && !userData.getProvincia().equals("")){
            provincia = "'" + StringUtils.capitalize(userData.getProvincia()) + "'";
        }
        if(userData.getPais() != null && !userData.getPais().equals("")){
            pais = "'" + StringUtils.capitalize(userData.getPais()) + "'";
        }
        if(userData.getCp() != null && !userData.getCp().equals("")){
            cp = "'" + userData.getCp() + "'";
        }
        if(!userData.getEmail().equals("")){
            email = "'" + userData.getEmail() + "'";
        }
        if(userData.getTelefono() != null && !userData.getTelefono().equals("")){
            telefono = "'" + userData.getTelefono() + "'";
        }
        if(userData.getMovil() != null && !userData.getMovil().equals("")){
            movil = "'" + userData.getMovil() + "'";
        }
        if(!userData.getLogin().equals("")){
            login = "'" + userData.getLogin() + "'";
        }
        if(!userData.getPass().equals("")){
            pass = "'" + userData.getPass() + "'";
        }

        String sql = "UPDATE " + DataBase.getSchemeName() + ".usuario"
                + " SET nombre=" + nombre + ","
                + " apellidos=" + apellidos +","
                + " nif=" + nif + ","
                + " direccion=" + direccion + ","
                + " localidad=" + localidad + ", "
                + " provincia=" + provincia + ","
                + " pais=" + pais + ","
                + " cp=" + cp + ","
                + " email=" + email + ","
                + " telefono=" + telefono + ","
                + " movil=" + movil + ","
                + " login=" + login + ","
                + " pass=" + pass + ","
                + " admin='" + userData.isAdmin() + "',"
                + " activo='" + userData.isActivo() + "'"
                + " WHERE id = " + userData.getId();
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "El usuario se ha modificado correctamente.");
        } else{
           Utilidades.mostrarError("", "No se ha podido modificar el usuario.");
           Log.getInstance().log(sql);
        }
    }
}
