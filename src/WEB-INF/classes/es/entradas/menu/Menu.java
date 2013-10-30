package es.entradas.menu;

import es.entradas.cliente.Cliente;
import es.entradas.cliente.ClienteData;
import es.entradas.ticket.Ticket;
import es.entradas.tickettype.TicketType;
import es.entradas.tickettype.TicketTypeData;
import es.entradas.user.User;
import es.entradas.user.UserData;
import es.entradas.userLogin.UserLogin;
import java.util.Date;

/**
 *
 * @author User
 */
public class Menu {

    private boolean renderPnlAdministracion = false;
    private boolean renderPnlEmpleado = true;
    private boolean expandedPnlAdministracion = false;
    private boolean expandedPnlEmpleado = false;

    private MenuBinding menuBinding = new MenuBinding();

    public Menu() {
        if(UserLogin.getUserLoginInstance().getUserLoginData().isAdmin()){
            menuBinding.getPnlAdmin().setDisclosed(true);
        } else{
            menuBinding.getPnlAdmin().setDisclosed(false);
            menuBinding.getPnlEmpleado().setDisclosed(true);
        }
    }

    private void inicializarItem(){
        menuBinding.getItemEmitirTicket().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemClientes().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemGestionTicket().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemIncidenciaTicket().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemInforme().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemEmpleados().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemEmail().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemCambiarPass().setInlineStyle("background-color: #D3D2D2;");
        menuBinding.getItemGrafica().setInlineStyle("background-color: #D3D2D2;");
    }

    public String goIndexTicket(){
        Ticket.setTicketFacesContext(new Ticket());
        inicializarItem();
        menuBinding.getItemEmitirTicket().setInlineStyle("background-color: #EBF5FE;");
        return "goIndexTicket";
    }

    public String goBusquedaTicket(){
        Ticket t = new Ticket();
        t.setTicketAL(t.getTicketDAO().getTickets(new Date(), new Date(), -1));
        t.getTicketData().setFechaInicio(new Date());
        t.getTicketData().setFechaFin(new Date());
        Ticket.setTicketFacesContext(t);
        inicializarItem();
        menuBinding.getItemIncidenciaTicket().setInlineStyle("background-color: #EBF5FE;");
        return "goBusquedaTicket";
    }

    public String goDatosTicket(){
        return "goDatosTicket";
    }

    public String goNuevoTicket(){
        inicializarItem();
        menuBinding.getItemEmitirTicket().setInlineStyle("background-color: #EBF5FE; font-weight: bold; color: #EBF5FE;");
        return "goNuevoTicket";
    }

    public String goIndexUsuario(){
        User.setUserFacesContext(new User());
        inicializarItem();
        menuBinding.getItemEmpleados().setInlineStyle("background-color: #EBF5FE;");
        return "goIndexUsuario";
    }

    public String goBusquedaUsuario(){
        User user = new User();
        user.setUserAL(user.getUserDAO().getUsersAL(new UserData()));
        user.getUserBinding().getBtnNuevoPass().setRendered(true);
        User.setUserFacesContext(user);
        return "goBusquedaUsuario";
    }

    public String goDatosUsuario(){
        User user = new User();
        user.getUserBinding().getBtnNuevoPass().setRendered(false);
        User.setUserFacesContext(user);
        return "goDatosUsuario";
    }

    public String goIndexTicketType(){
        TicketType.setTicketTypeFacesContext(new TicketType());
        inicializarItem();
        menuBinding.getItemGestionTicket().setInlineStyle("background-color: #EBF5FE;");
        return "goIndexTicketType";
    }

    public String goBusquedaTicketType(){
        TicketType ttp = new TicketType();
        ttp.setTicketTypeAL(ttp.getTicketTypeDAO().getTicketType(new TicketTypeData(), "Todos"));
        TicketType.setTicketTypeFacesContext(ttp);
        return "goBusquedaTicketType";
    }

    public String goDatosTicketType(){
        TicketType ttp = new TicketType();
        ttp.setTicketTypeAL(ttp.getTicketTypeDAO().getTicketType(new TicketTypeData(), "Todos"));
        TicketType.setTicketTypeFacesContext(ttp);
        return "goDatosTicketType";
    }

    public String goBusquedaCategoria(){
        return "goBusquedaCategoria";
    }

    public String goDatosCategoria(){
        return "goDatosCategoria";
    }

    public String goCambiarPass(){
        return "goCambiarPass";
    }

    public String goIndexCliente(){
        Cliente.setClientFacesContext(new Cliente());
        inicializarItem();
        menuBinding.getItemClientes().setInlineStyle("background-color: #EBF5FE;");
        return "goIndexCliente";
    }

    public String goBusquedaCliente(){
        Cliente c = new Cliente();
        c.setClientesAL(c.getClienteDAO().getClientes(new ClienteData()));
        Cliente.setClientFacesContext(c);
        return "goBusquedaCliente";
    }

    public String goDatosCliente(){
        Cliente.setClientFacesContext(new Cliente());
        return "goDatosCliente";
    }

    public String goEmail(){
        inicializarItem();
        menuBinding.getItemEmail().setInlineStyle("background-color: #EBF5FE;");
        Cliente.setClientFacesContext(new Cliente());
        return "goEmail";
    }

    public String goChangePass(){
        inicializarItem();
        menuBinding.getItemCambiarPass().setInlineStyle("background-color: #EBF5FE;");
        return "goChangePass";
    }

    public String goInformePorGrupo(){
        inicializarItem();
        menuBinding.getItemInforme().setInlineStyle("background-color: #EBF5FE;");
        return "goInformeByGoup";
    }

    public String goGrafica(){
        inicializarItem();
        menuBinding.getItemGrafica().setInlineStyle("background-color: #EBF5FE;");
        return "goGrafica";
    }

    public boolean isRenderPnlAdministracion() {
        if(UserLogin.getUserLoginInstance().getUserLoginData().isAdmin()){
            renderPnlAdministracion = true;
        } else{
            renderPnlAdministracion = false;
        }
        return renderPnlAdministracion;
    }

    public void setRenderPnlAdministracion(boolean renderPnlAdministracion) {
        this.renderPnlAdministracion = renderPnlAdministracion;
    }

    public boolean isRenderPnlEmpleado() {
        return renderPnlEmpleado;
    }

    public void setRenderPnlEmpleado(boolean renderPnlEmpleado) {
        this.renderPnlEmpleado = renderPnlEmpleado;
    }

    public boolean isExpandedPnlAdministracion() {
        return expandedPnlAdministracion;
    }

    public void setExpandedPnlAdministracion(boolean expandedPnlAdministracion) {
        this.expandedPnlAdministracion = expandedPnlAdministracion;
    }

    public boolean isExpandedPnlEmpleado() {
        return expandedPnlEmpleado;
    }

    public void setExpandedPnlEmpleado(boolean expandedPnlEmpleado) {
        this.expandedPnlEmpleado = expandedPnlEmpleado;
    }

    public MenuBinding getMenuBinding() {
        return menuBinding;
    }

    public void setMenuBinding(MenuBinding menuBinding) {
        this.menuBinding = menuBinding;
    }

    /*public boolean isRenderTicketType() {
        if(UserLogin.getUserLoginInstance().getUserLoginData().isAdmin()){
            renderTicketType = true;
        } else{
            renderTicketType = false;
        }
        return renderTicketType;
    }*/
}
