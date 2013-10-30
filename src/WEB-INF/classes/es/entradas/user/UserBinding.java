/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.user;

import org.apache.myfaces.trinidad.component.core.nav.CoreCommandButton;

/**
 *
 * @author User
 */
public class UserBinding {

    private CoreCommandButton btnNuevoPass = new CoreCommandButton();

    public CoreCommandButton getBtnNuevoPass() {
        return btnNuevoPass;
    }

    public void setBtnNuevoPass(CoreCommandButton btnNuevoPass) {
        this.btnNuevoPass = btnNuevoPass;
    }

}
