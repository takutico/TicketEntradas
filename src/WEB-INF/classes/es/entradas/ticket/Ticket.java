/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.ticket;

import es.entradas.utils.Log;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author Takuya
 */
public class Ticket {

    private TicketDAO ticketDAO = new TicketDAO();
    private TicketData ticketData = new TicketData();
    private ArrayList ticketAL = new ArrayList();
    private boolean renderTicketAL = false;

    public Ticket() {
        ticketData = new TicketData();
    }

    public String limpiarDatos(){
        ticketData = new TicketData();
        return "success";
    }

    /*public void getTickets(String fechaInicio, String fechaFin, String tipoTicket){
    ticketDAO.getTickets(fechaInicio, fechaFin, tipoTicket);
    }*/
    public Barcode generarCodigoBarras(String codigo) {
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(codigo, false);
        } catch (BarcodeException e) {
            Log.getInstance().log(e.getMessage());
        }
        System.out.println("CodigoBarraSwing");
        return barcode;
    }

    public static Ticket getTicketFacesContext() {
        javax.faces.context.FacesContext fc = org.apache.myfaces.context.FacesContextWrapper.getCurrentInstance();
        HttpSession sesion = (HttpSession) fc.getExternalContext().getSession(false);
        Ticket t = (Ticket) sesion.getAttribute("ticket");
        if(t == null){
            t = new Ticket();
        }
        return t;
    }

    public static TicketData getTicketDataFacesContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        TicketData data = (TicketData) fc.getExternalContext().getSessionMap().get("ticketData");
        if (data == null) {
            data = new TicketData();
        }
        return data;
    }

    public static void setTicketDataFacesContext(TicketData ticketData) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("ticketData", ticketData);
    }
    public static void setTicketFacesContext(Ticket ticket) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("ticket", ticket);
    }

    public String modificarTicket() {
        ticketDAO.modificartTicket(ticketData);
        return "success";
    }

    public String buscarTicket() {
        ticketAL = ticketDAO.getTickets(ticketData.getFechaInicio(), ticketData.getFechaFin(), ticketData.getIdType());
        return "success";
    }

    public String buscarTicketPorTipo(){
        ticketAL = ticketDAO.getTicketsGroupByType(ticketData.getFechaInicio(), ticketData.getFechaFin(), ticketData.getIdType());
        return "success";
    }

    public String insertTicket() {
        int id = ticketDAO.insertTicket(ticketData);
        ticketData = ticketDAO.getTicketByTicketId(id);
        setTicketDataFacesContext(ticketData);
        //ticketDAO.insertTicket(ticketData);
        //ticketData = new TicketData();
        return "printTicket";
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public TicketData getTicketData() {
        return ticketData;
    }

    public void setTicketData(TicketData ticketData) {
        this.ticketData = ticketData;
    }

    public ArrayList getTicketAL() {
        return ticketAL;
    }

    public void setTicketAL(ArrayList ticketAL) {
        this.ticketAL = ticketAL;
    }

    public boolean isRenderTicketAL() {
        if (ticketAL.isEmpty()) {
            renderTicketAL = false;
        } else {
            renderTicketAL = true;
        }
        return renderTicketAL;
    }

    public void setRenderTicketAL(boolean renderTicketAL) {
        this.renderTicketAL = renderTicketAL;
    }
}
