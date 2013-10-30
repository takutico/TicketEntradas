/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.cliente;

import org.apache.myfaces.trinidad.component.UIXTable;
import org.apache.myfaces.trinidad.component.core.nav.CoreCommandLink;
import org.apache.myfaces.trinidad.component.core.output.CoreOutputText;

/**
 *
 * @author User
 */
public class ClienteBinding {

    private UIXTable tblClientes;
    private CoreCommandLink linkPrintFormCliente;
    private CoreOutputText txtPrintForm;

    public ClienteBinding() {
        linkPrintFormCliente = new CoreCommandLink();
        linkPrintFormCliente.setRendered(true);
        txtPrintForm = new CoreOutputText();
        txtPrintForm.setRendered(true);
    }

    /*public ClienteBinding() {
        //this.tblClientes = new UIXTable();
        //tblClientes.setRendered(true);
        //tblClientes.set
    }*/

    public UIXTable getTblClientes() {
        return tblClientes;
    }

    public void setTblClientes(UIXTable tblClientes) {
        this.tblClientes = tblClientes;
    }

    public CoreCommandLink getLinkPrintFormCliente() {
        return linkPrintFormCliente;
    }

    public void setLinkPrintFormCliente(CoreCommandLink linkPrintFormCliente) {
        this.linkPrintFormCliente = linkPrintFormCliente;
    }

    public CoreOutputText getTxtPrintForm() {
        return txtPrintForm;
    }

    public void setTxtPrintForm(CoreOutputText txtPrintForm) {
        this.txtPrintForm = txtPrintForm;
    }
}
