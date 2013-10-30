/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.publicidad;

import org.apache.myfaces.trinidad.component.UIXTable;

/**
 *
 * @author User
 */
public class EmailBinding {

    private UIXTable tblClientes;

    public EmailBinding() {
        this.tblClientes = new UIXTable();
        tblClientes.setRendered(true);
    }

    public UIXTable getTblClientes() {
        return tblClientes;
    }

    public void setTblClientes(UIXTable tblClientes) {
        this.tblClientes = tblClientes;
    }

}
