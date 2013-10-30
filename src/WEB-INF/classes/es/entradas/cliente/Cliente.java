/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.cliente;

import es.entradas.utils.Log;
import es.entradas.utils.Utilidades;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.apache.myfaces.trinidad.context.RequestContext;

/**
 *
 * @author User
 */
public class Cliente {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ClienteData clienteData = new ClienteData();
    private ClienteBinding clienteBinding = new ClienteBinding();
    private ArrayList clientesAL = new ArrayList();
    private boolean renderClientesAL = false;

    public String limpiarFormBusqueda() {
        clienteData = new ClienteData();
        return "success";
    }

    public String limpiarDatos() {
        clienteData = new ClienteData();
        clientesAL = new ArrayList();
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.returnFromDialog(null, null);
        return null;
    }

    public String limpiarDatosFormCliente() {
        //clienteData = new ClienteData();
        setClientFacesContext(new Cliente());
        return "success";
    }

    public String limpiarDatosClienteExterno() {
        clienteData.inicializaDatos();
        return "datosClienteExterno";
    }

    public static Cliente getClienteInstance() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object cliente = fc.getExternalContext().getSessionMap().get("cliente");
        if (cliente == null) {
            cliente = new Cliente();
        }
        return (Cliente) cliente;
    }

    public static void setClientFacesContext(Cliente cliente) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("cliente", cliente);
    }

    public String getEmailClientes() {
        String emailLst = "";
        if (clientesAL.isEmpty()) {
            Utilidades.mostrarError("", "No hay datos");
        } else {
            //clientesAL = (ArrayList<ClienteData>)clienteBinding.getTblClientes().getSelectedRowData();
            String email = "";
            for (int i = 0; i < clientesAL.size(); i++) {
                email = ((ClienteData) clientesAL.get(i)).getEmail();
                if (email != null && !email.equals("")) {
                    emailLst += ((ClienteData) clientesAL.get(i)).getEmail() + ";";
                }
            }
            if (emailLst.length() > 0) {
                emailLst = emailLst.substring(0, emailLst.length() - 1);
            }
            /*String email = "";
            for (int i = 0; i < clientesAL.size(); i++) {
            email = ((ClienteData) clientesAL.get(i)).getEmail();
            if (email != null && !email.equals("")) {
            emailLst += ((ClienteData) clientesAL.get(i)).getEmail() + ";";
            }
            }
            if (emailLst.length() > 0) {
            emailLst = emailLst.substring(0, emailLst.length() - 1);
            }*/
        }

        return emailLst;
    }

    public String buscarClientes() {
        try {
            //clienteBinding = new ClienteBinding();
            clientesAL = clienteDAO.getClientes(clienteData);
        } catch (Exception ex) {
            Log.getInstance().log(ex.toString());
        }
        return "success";
    }

    public String buscarClientesActivosConEnvioEmail() {
        try {
            //clienteBinding = new ClienteBinding();
            clienteData.setActivo(true);
            clienteData.setOfertaEmail(true);
            clientesAL = clienteDAO.getClientesDialog(clienteData);
        } catch (Exception ex) {
            Log.getInstance().log(ex.toString());
        }
        return "success";
    }

    public String insertarCliente() {
        try {
            if (clienteData.getId() <= 0) {
                clienteDAO.insertCliente(clienteData);
            } else {
                clienteDAO.modificarCliente(clienteData);
            }
        } catch (Exception ex) {
            Log.getInstance().log(ex.toString());
        }
        //clienteData = new ClienteData();
        return "success";
    }

    public String insertarClienteExterno() {
        clienteData.setActivo(true);
        clienteData.setOfertaEmail(true);
        try {
            clienteDAO.insertCliente(clienteData);
        } catch (Exception ex) {
            Log.getInstance().log(ex.toString());
        }
        return "success";
    }

    public String modificarDatosCliente(){
        clienteBinding.getLinkPrintFormCliente().setRendered(false);
        clienteBinding.getTxtPrintForm().setRendered(false);
        return "goDatosCliente";
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ClienteData getClienteData() {
        return clienteData;
    }

    public void setClienteData(ClienteData clienteData) {
        this.clienteData = clienteData;
    }

    public ArrayList getClientesAL() {
        return clientesAL;
    }

    public void setClientesAL(ArrayList clientesAL) {
        this.clientesAL = clientesAL;
    }

    public boolean isRenderClientesAL() {
        if (clientesAL.isEmpty()) {
            renderClientesAL = false;
        } else {
            renderClientesAL = true;
        }
        return renderClientesAL;
    }

    public void setRenderClientesAL(boolean renderClientesAL) {
        this.renderClientesAL = renderClientesAL;
    }

    public ClienteBinding getClienteBinding() {
        return clienteBinding;
    }

    public void setClienteBinding(ClienteBinding clienteBinding) {
        this.clienteBinding = clienteBinding;
    }

    /*public ClienteBinding getClienteBinding() {
    return clienteBinding;
    }

    public void setClienteBinding(ClienteBinding clienteBinding) {
    this.clienteBinding = clienteBinding;
    }*/
}
