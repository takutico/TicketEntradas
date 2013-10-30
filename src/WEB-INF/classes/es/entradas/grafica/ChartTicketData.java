/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.grafica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class ChartTicketData {

    private String nombreTipoTicket = "";
    private Double numEntradas = 0d;
    private List<Double> listEntradas = new ArrayList<Double>();
    private Date fechaInicio = null;
    private Date fechaFin = null;

    public String getNombreTipoTicket() {
        return nombreTipoTicket;
    }

    public void setNombreTipoTicket(String nombreTipoTicket) {
        this.nombreTipoTicket = nombreTipoTicket;
    }

    public Double getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(Double numEntradas) {
        this.numEntradas = numEntradas;
    }

    public List<Double> getListEntradas() {
        //List<Double> numbers = new ArrayList<Double>();
        //new ArrayList<Double>().add(numEntradas);
        listEntradas.add(numEntradas);
        return listEntradas;
    }

    public void setListEntradas(List<Double> listEntradas) {
        this.listEntradas = listEntradas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
