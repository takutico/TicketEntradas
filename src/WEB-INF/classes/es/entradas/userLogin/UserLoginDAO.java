/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.userLogin;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.Utilidades;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author User
 */
public class UserLoginDAO {

    public static final int ADMIN_OK = 1;
    public static final int ADMIN_ERROR = 0;

    public int cambiarPass(String nif, String newPass){
        String query = "UPDATE " + DataBase.getSchemeName() + ".usuario" +
                " SET pass = '" + newPass + "'" +
                " WHERE upper(nif) = '" + nif + "'";
        if(DataBase.executeUpdate(query) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "La contraseña se ha actualizado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido cambiar la contraseña.");
            Log.getInstance().log(query);
            return ADMIN_ERROR;
        }
        return ADMIN_OK;
    }

    public int getAdmin(UserLoginData userLoginData) {
        ArrayList resultAL = new ArrayList();
        Vector resultados = new Vector();
        String sql = "SELECT id, nombre, apellidos, nif, direccion, localidad, provincia, pais, cp,"
                + " email, telefono, movil, login, pass, admin, activo"
                + " FROM " + DataBase.getSchemeName() + ".usuario"
                + " WHERE 1 = 1"
                + " AND upper(login) = '" + userLoginData.getLogin().toUpperCase() + "'"
                + " AND upper(pass) = '" + userLoginData.getPass().toUpperCase() + "'"
                + " AND activo = true";

        try {
            resultados = DataBase.executeQuery(sql);
            String temp = "";
            if (resultados != null && resultados.size() > 0) {
                UserLoginData data = null;
                for (int i = 0; i < resultados.size(); i++) {
                    data = new UserLoginData();
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
                    temp = ((String[]) resultados.elementAt(i))[14];
                    if(temp.startsWith("t") ||temp.startsWith("T")){
                        data.setAdmin(true);
                    } else{
                        data.setAdmin(false);
                    }
                    temp = ((String[]) resultados.elementAt(i))[15];
                    if(temp.startsWith("t") ||temp.startsWith("T")){
                        data.setActivo(true);
                    } else{
                        data.setActivo(false);
                    }
                    resultAL.add(data);
                }
                UserLogin u = new UserLogin();
                u.setUserLoginData(data);
                UserLogin.setUserLoginFacesContext(u);
            } else{
                return ADMIN_ERROR;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
            return ADMIN_ERROR;
        }

        return ADMIN_OK;
    }

}
