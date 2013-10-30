
package es.entradas.utils;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Escuchador de Fases de JSF para poder realizar ciertas operaciones sobre todas las paginas del sistema.
 * Operaciones como:
 *      - Control de caducidad de sesion.
 * 
 * @author tyamaguchi
 */
public class PhaseListenerGestionEntradas implements PhaseListener {

    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        String viewId = context.getViewRoot().getViewId();

        //Estamos en la pagina principal de login???   
        boolean onLoginPage = viewId.startsWith("/index.jsp");

        //Estoy logueado ya que he puesto en sesion el id de mi sesion???
        boolean isLoggedIn = context.getExternalContext().getSessionMap().get("idsesion") != null;

        if (!isLoggedIn && !onLoginPage) {
            // No estoy logueado, por lo que remito al usuario a la pagina de login...
            //Utilidades.MostrarError("sessionExpired", "Session expirada");
            goTo(context, "logout_sesion_expirada");
        }
    }
    
    //Navego con la regla de navegacion apropiada
    private void goTo(FacesContext ctx, String where) {
        ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, where);
    }

    public void beforePhase(PhaseEvent phaseEvent) {
        //No hago nada
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
