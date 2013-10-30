/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.category;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.Utilidades;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author User
 */
public class CategoryDAO {

    /** Se obtienen las categorías de la base de datos
     *
     * @return
     */
    protected Map getCategoryMap() {
        Map resultMap = new HashMap();
        Vector results = new Vector();

        try {
            String sql = "SELECT id, nombre FROM " + DataBase.getSchemeName() + ".category";
            results = DataBase.executeQuery(sql);
            if (results != null && results.size() > 0) {
                for (int i = 0; i < results.size(); i++) {
                    resultMap.put(((String[]) results.elementAt(i))[1], ((String[]) results.elementAt(i))[0]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
            Utilidades.mostrarError("", "Ha surgido un error al intentar obtener las categorías.");
        }
        return resultMap;
    }

    public void insertCliente(CategoryData data) {
        String sql = "INSERT INTO  " + DataBase.getSchemeName() + ".category(nombre)"
                + " VALUES ('" + data.getNombre().toUpperCase() + "'";
        if(DataBase.executeUpdate(sql) == DataBase.OPERACION_OK){
            Utilidades.mostrarInformacion("", "La categoría se ha insertado correctamente.");
        } else{
            Utilidades.mostrarError("", "No se ha podido insertar la categoría.");
            Log.getInstance().log(sql);
        }
    }
}
