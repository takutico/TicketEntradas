/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.tickettype;

import org.apache.myfaces.trinidad.component.core.input.CoreInputText;

/**
 *
 * @author User
 */
public class TicketTypeBinding {

    private CoreInputText precio = new CoreInputText();

    public TicketTypeBinding() {
        inicializaDatos();
    }

    private void inicializaDatos(){
        precio.setDisabled(false);
    }

    public CoreInputText getPrecio() {
        return precio;
    }

    public void setPrecio(CoreInputText precio) {
        this.precio = precio;
    }

}
