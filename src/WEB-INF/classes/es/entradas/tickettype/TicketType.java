/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.tickettype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Takuya
 */
public class TicketType {

    

    private TicketTypeDAO ticketTypeDAO = new TicketTypeDAO();
    private TicketTypeData ticketTypeData = new TicketTypeData();
    private TicketTypeBinding ticketTypeBinding = new TicketTypeBinding();

    private ArrayList ticketTypeAL = new ArrayList();
    private ArrayList ticketTypeActivosAL = new ArrayList();
    private Map ticketTypeMap = new HashMap();
    private static String ACTIVO = "ACTIVO";
    private static String NO_ACTIVO = "NO_ACTIVO";
    private static String TODOS = "TODOS";
    private String opcionActivo = TODOS;

    public static TicketType getTicketTypeInstance() {
        FacesContext fc = FacesContext.getCurrentInstance();
        TicketType tt = (TicketType) fc.getExternalContext().getSessionMap().get("ticketType");
        if(tt == null){
            tt = new TicketType();
        }
        return tt;
    }

    public static void setTicketTypeFacesContext(TicketType ticketType) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("ticketType", ticketType);
    }

    public String goModificarTicketType(){
        ticketTypeBinding.getPrecio().setDisabled(true);
        return "goDatosTicketType";
    }

    public TicketTypeDAO getTicketTypeDAO() {
        return ticketTypeDAO;
    }

    public void setTicketTypeDAO(TicketTypeDAO ticketTypeDAO) {
        this.ticketTypeDAO = ticketTypeDAO;
    }

    public String buscarTicketType(){
        ticketTypeAL = ticketTypeDAO.getTicketType(ticketTypeData, opcionActivo);
        return "success";
    }

    public String guardarTicketType(){
        if(ticketTypeData.getId() <= 0){
            ticketTypeDAO.insertTicket(ticketTypeData);
        } else{
            ticketTypeDAO.modificarTicket(ticketTypeData);
        }
        ticketTypeAL = ticketTypeDAO.getTicketType(ticketTypeData, TicketType.TODOS);
        return "success";
    }

    public void updateTicketType(TicketTypeData ticketTypeData){
        ticketTypeDAO.updateTicket(ticketTypeData);
    }

    public TicketTypeData getTicketTypeData() {
        return ticketTypeData;
    }

    public void setTicketTypeData(TicketTypeData ticketTypeData) {
        this.ticketTypeData = ticketTypeData;
    }

    public ArrayList getTicketTypeAL() {
        if(ticketTypeAL.size() <= 0){
            ticketTypeAL = ticketTypeDAO.getTicketType(ticketTypeData, TicketType.ACTIVO);
        }
        return ticketTypeAL;
    }

    public void setTicketTypeAL(ArrayList ticketTypeAL) {
        this.ticketTypeAL = ticketTypeAL;
    }

    public Map getTicketTypeMap() {
        ArrayList tempAL = getTicketTypeActivosAL();
        for(int i = 0; i < tempAL.size(); i++){
            ticketTypeMap.put(((TicketTypeData)tempAL.get(i)).getName(),
                    ((TicketTypeData)tempAL.get(i)).getId());
        }

        return ticketTypeMap;
    }

    public void setTicketTypeMap(Map ticketTypeMap) {
        this.ticketTypeMap = ticketTypeMap;
    }

    public static String getACTIVO() {
        return ACTIVO;
    }

    public static void setACTIVO(String aACTIVO) {
        ACTIVO = aACTIVO;
    }

    public static String getNO_ACTIVO() {
        return NO_ACTIVO;
    }

    public static void setNO_ACTIVO(String aNO_ACTIVO) {
        NO_ACTIVO = aNO_ACTIVO;
    }

    public static String getTODOS() {
        return TODOS;
    }

    public static void setTODOS(String aTODOS) {
        TODOS = aTODOS;
    }

    public String getOpcionActivo() {
        return opcionActivo;
    }

    public void setOpcionActivo(String opcionActivo) {
        this.opcionActivo = opcionActivo;
    }

    public TicketTypeBinding getTicketTypeBinding() {
        return ticketTypeBinding;
    }

    public void setTicketTypeBinding(TicketTypeBinding ticketTypeBinding) {
        this.ticketTypeBinding = ticketTypeBinding;
    }

    public ArrayList getTicketTypeActivosAL() {
        if(ticketTypeActivosAL.size() <= 0){
            ticketTypeActivosAL = ticketTypeDAO.getTicketType(ticketTypeData, TicketType.ACTIVO);
        }
        return ticketTypeActivosAL;
    }

    public void setTicketTypeActivosAL(ArrayList ticketTypeActivosAL) {
        this.ticketTypeActivosAL = ticketTypeActivosAL;
    }

}
