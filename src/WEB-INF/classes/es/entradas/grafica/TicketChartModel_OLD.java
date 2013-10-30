/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.grafica;

import es.entradas.ticket.Ticket;
import es.entradas.tickettype.TicketType;
import es.entradas.tickettype.TicketTypeDAO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.myfaces.trinidad.model.ChartModel;

/**
 *
 * @author User
 */
public class TicketChartModel_OLD implements Serializable {

    
    private boolean _largerDataSet = false;
    private ChartModel _chartModel = new MyChartModel();

    public ChartModel getValue() {
        return _chartModel;
    }

    private class MyChartModel extends ChartModel implements Serializable {

        private ChartTicketDAO dao = new ChartTicketDAO();
        private List<String> _groupLabels = new ArrayList<String>();
        private List<String> _seriesLabels = Arrays.asList(new String[]{"Previous"});
        private List<String> _largeSeriesLabels = Arrays.asList(new String[]{"Opening"});
        private ArrayList<List<Double>> _chartYValues = new ArrayList<List<Double>>();
        private ArrayList<List<Double>> _chartXValues;
        private final ArrayList<List<Double>> _dialchartYValues;

        {
            _dialchartYValues = new ArrayList<List<Double>>();
            _dialchartYValues.add(Arrays.asList(new Double[]{135.}));
            _dialchartYValues.add(Arrays.asList(new Double[]{106.}));

            _chartXValues = new ArrayList<List<Double>>();
            _chartXValues.add(Arrays.asList(new Double[]{6.1}));
            _chartXValues.add(Arrays.asList(new Double[]{6.8}));
            _chartXValues.add(Arrays.asList(new Double[]{7.6}));
            _chartXValues.add(Arrays.asList(new Double[]{8.25}));
            _chartXValues.add(Arrays.asList(new Double[]{9.23}));
        }

        public MyChartModel() {

            ArrayList<ChartTicketData> tempAL = dao.getTicketsByType(null, null);
            List<Double> tempALD = new ArrayList<Double>();
            for (int i = 0; i < tempAL.size(); i++) {
                _groupLabels.add(tempAL.get(i).getNombreTipoTicket());
                tempALD.add(tempAL.get(i).getNumEntradas());
            }
            _chartYValues = new ArrayList<List<Double>>();
            _chartYValues.add(tempALD);
        }

        @Override
        public List<String> getSeriesLabels() {
            if (_largerDataSet) {
                return _largeSeriesLabels;
            } else {
                return _seriesLabels;
            }
        }

        @Override
        public List<String> getGroupLabels() {
            _groupLabels = new ArrayList<String>();
            ArrayList<ChartTicketData> tempAL = dao.getTicketsByType(null, null);
            List<Double> tempALD = new ArrayList<Double>();
            for (int i = 0; i < tempAL.size(); i++) {
                _chartModel.getMinYValue();
                _groupLabels.add(tempAL.get(i).getNombreTipoTicket());
                tempALD.add(tempAL.get(i).getNumEntradas());
            }
            _chartYValues = new ArrayList<List<Double>>();
            _chartYValues.add(tempALD);
            return _groupLabels;
        }

        @Override
        public List<List<Double>> getXValues() {

            return null;
        }

        @Override
        public List<List<Double>> getYValues() {
            /*_chartYValues = new ArrayList<List<Double>>();
            ArrayList<ChartTicketData> tempAL = dao.getTicketsByType(null, null);
            List<Double> tempALD = new ArrayList<Double>();
            for (int i = 0; i < tempAL.size(); i++) {
                _chartModel.getMinYValue();
                _groupLabels.add(tempAL.get(i).getNombreTipoTicket());
                tempALD.add(tempAL.get(i).getNumEntradas());
            }
            _chartYValues = new ArrayList<List<Double>>();
            _chartYValues.add(tempALD);*/
            return _chartYValues;
        }

        @Override
        public Double getMaxYValue() {

            return 300.0;
        }

        @Override
        public Double getMinYValue() {

            return 0.0;
        }

        @Override
        public Double getMaxXValue() {
            if (_largerDataSet) {
                return 54.0;
            } else {
                return 10.0;
            }
        }

        @Override
        public Double getMinXValue() {
            if (_largerDataSet) {
                return 0.0;
            } else {
                return 6.0;
            }
        }

        @Override
        public String getTitle() {

            return "Ticket's de entradas por tipos de ticket";
        }

        @Override
        public String getSubTitle() {

            return "";
        }

        @Override
        public String getFootNote() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            return sdf.format(new Date());
        }

        public void setGroupLabels(List<String> groupLabels) {
            this._groupLabels = groupLabels;
        }

        public void setChartYValues(ArrayList<List<Double>> chartYValues) {
            this._chartYValues = chartYValues;
        }
    }
}
