package es.entradas.menu;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailItem;
import org.apache.myfaces.trinidad.component.core.nav.CoreCommandNavigationItem;

/**
 *
 * @author User
 */
public class MenuBinding {

    private CoreShowDetailItem pnlEmpleado = new CoreShowDetailItem();
    private CoreShowDetailItem pnlAdmin = new CoreShowDetailItem();

    private CoreCommandNavigationItem itemEmitirTicket = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemClientes = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemGestionTicket = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemIncidenciaTicket = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemInforme = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemGrafica = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemEmpleados = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemEmail = new CoreCommandNavigationItem();
    private CoreCommandNavigationItem itemCambiarPass = new CoreCommandNavigationItem();

    public CoreShowDetailItem getPnlEmpleado() {
        return pnlEmpleado;
    }

    public void setPnlEmpleado(CoreShowDetailItem pnlEmpleado) {
        this.pnlEmpleado = pnlEmpleado;
    }

    public CoreShowDetailItem getPnlAdmin() {
        return pnlAdmin;
    }

    public void setPnlAdmin(CoreShowDetailItem pnlAdmin) {
        this.pnlAdmin = pnlAdmin;
    }

    public CoreCommandNavigationItem getItemEmitirTicket() {
        return itemEmitirTicket;
    }

    public void setItemEmitirTicket(CoreCommandNavigationItem itemEmitirTicket) {
        this.itemEmitirTicket = itemEmitirTicket;
    }

    public CoreCommandNavigationItem getItemClientes() {
        return itemClientes;
    }

    public void setItemClientes(CoreCommandNavigationItem itemClientes) {
        this.itemClientes = itemClientes;
    }

    public CoreCommandNavigationItem getItemGestionTicket() {
        return itemGestionTicket;
    }

    public void setItemGestionTicket(CoreCommandNavigationItem itemGestionTicket) {
        this.itemGestionTicket = itemGestionTicket;
    }

    public CoreCommandNavigationItem getItemIncidenciaTicket() {
        return itemIncidenciaTicket;
    }

    public void setItemIncidenciaTicket(CoreCommandNavigationItem itemIncidenciaTicket) {
        this.itemIncidenciaTicket = itemIncidenciaTicket;
    }

    public CoreCommandNavigationItem getItemInforme() {
        return itemInforme;
    }

    public void setItemInforme(CoreCommandNavigationItem itemInforme) {
        this.itemInforme = itemInforme;
    }

    public CoreCommandNavigationItem getItemEmpleados() {
        return itemEmpleados;
    }

    public void setItemEmpleados(CoreCommandNavigationItem itemEmpleados) {
        this.itemEmpleados = itemEmpleados;
    }

    public CoreCommandNavigationItem getItemEmail() {
        return itemEmail;
    }

    public void setItemEmail(CoreCommandNavigationItem itemEmail) {
        this.itemEmail = itemEmail;
    }

    public CoreCommandNavigationItem getItemCambiarPass() {
        return itemCambiarPass;
    }

    public void setItemCambiarPass(CoreCommandNavigationItem itemCambiarPass) {
        this.itemCambiarPass = itemCambiarPass;
    }

    public CoreCommandNavigationItem getItemGrafica() {
        return itemGrafica;
    }

    public void setItemGrafica(CoreCommandNavigationItem itemGrafica) {
        this.itemGrafica = itemGrafica;
    }

}
