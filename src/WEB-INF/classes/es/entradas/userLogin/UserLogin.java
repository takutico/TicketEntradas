/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.userLogin;

import es.entradas.utils.Utilidades;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class UserLogin {

    private UserLoginDAO userLoginDAO = new UserLoginDAO();
    private UserLoginData userLoginData = new UserLoginData();

    public static UserLogin getUserLoginInstance() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return (UserLogin) fc.getExternalContext().getSessionMap().get("userLogin");
    }

    public static void setUserLoginFacesContext(UserLogin userLogin) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("userLogin", userLogin);
    }

    public String cambiarPass() {
        if (userLoginData.getNuevoPass().equals(userLoginData.getRepetirNuevoPass())) {
            userLoginDAO.cambiarPass(userLoginData.getNif(), userLoginData.getNuevoPass());
        } else {
            Utilidades.mostrarError("", "Vuelva a insertar las contraseñas.");
        }
        return "success";
    }

    public String loginUser() {
        if (userLoginDAO.getAdmin(userLoginData) == UserLoginDAO.ADMIN_OK) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext exctx = ctx.getExternalContext();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
            if (session != null) {
                exctx.getSessionMap().put("idsesion", session.getId().toString());
            }

            if (getUserLoginInstance().getUserLoginData().isAdmin()) {
                return "goWelcomeAdmin";
            } else {
                return "goWelcomeEmpleado";
            }
        } else {
            Utilidades.mostrarError("", "Compruebe el login y el pass.");
            return "none";
        }
    }

    public String logoutUser() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext exctx = ctx.getExternalContext();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
            if (session != null) {
                try {
                    session.invalidate();
                    Utilidades.mostrarAviso("", "Session cerrada correctamente");
                } catch (java.lang.IllegalStateException ex) {
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return "logout";
    }

    public UserLoginDAO getUserLoginDAO() {
        return userLoginDAO;
    }

    public void setUserLoginDAO(UserLoginDAO userLoginDAO) {
        this.userLoginDAO = userLoginDAO;
    }

    public UserLoginData getUserLoginData() {
        return userLoginData;
    }

    public void setUserLoginData(UserLoginData userLoginData) {
        this.userLoginData = userLoginData;
    }
}
