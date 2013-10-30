/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.grafica;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author User
 */
public class ChartTicketDAO {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ArrayList<ChartTicketData> getTicketsByType(Date fechaInicio, Date fechaFin) {
        ArrayList<ChartTicketData> resultAL = new ArrayList<ChartTicketData>();
        String query = "SELECT sum(people), tt.name"
                + " FROM " + DataBase.getSchemeName() + ".ticket t, "
                + DataBase.getSchemeName() + ".ticketType tt"
                + " WHERE t.idType = tt.id";
        String fechaInicioStr = "";
        String fechaFinStr = "";
        if (fechaInicio == null) {
            fechaInicioStr = sdf.format(new Date());
        } else {
            fechaInicioStr = sdf.format(fechaInicio);
        }
        query += " and date >= '" + fechaInicioStr + "'";
        if (fechaFin == null) {
            fechaFinStr = sdf.format(new Date());
        } else {
            fechaFinStr = sdf.format(fechaFin);
        }
        query += " and date <= '" + fechaFinStr + "'";
        query += " group by tt.name order by tt.name";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                ChartTicketData temp;
                for (int i = 0; i < resultados.size(); i++) {
                    temp = new ChartTicketData();
                    temp.setNumEntradas(Double.valueOf(((String[]) resultados.elementAt(i))[0]));
                    temp.setNombreTipoTicket(((String[]) resultados.elementAt(i))[1]);
                    resultAL.add(temp);
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

}
