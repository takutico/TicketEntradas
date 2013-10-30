/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.grafica;

/**
 *
 * @author User
 */
/*public class TicketChartModel {

}*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChartModel;

public class TicketChartModel extends ChartModel {

    List<String> groupLabels = new ArrayList<String>();
    List<String> seriesLabels = new ArrayList<String>();
    List<List<Double>> chartValues = new ArrayList<List<Double>>();
    private ChartTicketDAO dao = new ChartTicketDAO();

    public TicketChartModel(Date fechaInicio, Date fechaFin) {
        ArrayList<ChartTicketData> tempAL = dao.getTicketsByType(fechaInicio, fechaFin);
            List<Double> tempALD = new ArrayList<Double>();
            List<Double> numbers = new ArrayList<Double>();
            for (int i = 0; i < tempAL.size(); i++) {
                numbers.add(tempAL.get(i).getNumEntradas());
                /*seriesLabels.add("");
                groupLabels.add(tempAL.get(i).getNombreTipoTicket());*/
                seriesLabels.add(tempAL.get(i).getNombreTipoTicket());
                
                tempALD.add(tempAL.get(i).getNumEntradas());
            }
            groupLabels.add("Tickets");
            chartValues.add(numbers);
      /*
       numbers = new ArrayList<Double>();
                //new ArrayList<Double>().add(tempAL.get(i).getNumEntradas());
                numbers.add(tempAL.get(i).getNumEntradas());
                seriesLabels.add("");
                groupLabels.add(tempAL.get(i).getNombreTipoTicket());
                //tempALD.add(tempAL.get(i).getNumEntradas());
                //chartValues.add(tempAL.get(i).getListEntradas());
                chartValues.add(numbers);
       */
    }

    // How many charts are you going to have
    @Override
    public List<String> getGroupLabels() {
        //List<String> groupLabels = new ArrayList<String>();
        //groupLabels.add("Java");
        //groupLabels.add("Linux");
        //groupLabels.add(".NET");
        return groupLabels;
    }

    // How many parts (data areas) per chart
    @Override
    public List<String> getSeriesLabels() {
        //List<String> seriesLabels = new ArrayList<String>();
        //seriesLabels.add("Love it");
        //seriesLabels.add("Hate it");
        return seriesLabels;
    }

    @Override
    public List<List<Double>> getYValues() {
        //List<List<Double>> chartValues = new ArrayList<List<Double>>();
        // Fill the groups
        /*for (int i = 0; i < getGroupLabels().size(); i++) {
            List<Double> numbers = new ArrayList<Double>();

            // fill the series per group
            for (int j = 0; j < getSeriesLabels().size(); j++) {
                numbers.add(100 * Math.random());
            }
            chartValues.add(numbers);
        }*/
        return chartValues;

    }
}
