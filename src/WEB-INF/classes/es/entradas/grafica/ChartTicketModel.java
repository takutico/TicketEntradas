/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.grafica;

/**
 *
 * @author User
 */
public class ChartTicketModel {

    private ChartTicketData chartTicketData = new ChartTicketData();
    private TicketChartModel ticketChartModel = new TicketChartModel(null, null);

    public String actualizar(){
        ticketChartModel = new TicketChartModel(chartTicketData.getFechaInicio(), chartTicketData.getFechaFin());
        return "success";
    }

    public ChartTicketData getChartTicketData() {
        return chartTicketData;
    }

    public void setChartTicketData(ChartTicketData chartTicketData) {
        this.chartTicketData = chartTicketData;
    }

    public TicketChartModel getTicketChartModel() {
        return ticketChartModel;
    }

    public void setTicketChartModel(TicketChartModel ticketChartModel) {
        this.ticketChartModel = ticketChartModel;
    }

}
