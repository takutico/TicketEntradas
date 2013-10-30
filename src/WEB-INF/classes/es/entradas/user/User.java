/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.user;

import es.entradas.publicidad.Email;
import es.entradas.utils.PasswordGenerator;
import es.entradas.utils.Utilidades;
import java.util.ArrayList;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
public class User {

    private UserDAO userDAO = new UserDAO();
    private UserData userData = new UserData();
    private UserBinding userBinding = new UserBinding();

    private ArrayList userAL = new ArrayList();
    private boolean renderUserAL = false;

    public static void setUserFacesContext(User user) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("user", user);
    }

    public String limpiarDatos(){
        userData = new UserData();
        return "success";
    }

    public String modificarUser(){
        return "goDatosUser";
    }

    public String guardarUser(){
        if(userData.getId() <= 0){
            userData.setPass(PasswordGenerator.getPassword());
            userDAO.insertUser(userData);
        } else{
            userDAO.modificarUser(userData);
        }
        return "success";
    }
    
    public String nuevoPass(){
        userData.setPass(PasswordGenerator.getPassword());
        userDAO.modificarUser(userData);
        Email.send("Gestión de entradas", userData.getEmail(), "Su contraseña para la aplicación es: " + userData.getPass(), null);
        //Utilidades.mostrarInformacion(null, "Se ha enviado la nueva contraseña.");
        return "success";
    }

    public String buscarUser(){
        userAL = userDAO.getUsersAL(userData);
        return "success";
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public ArrayList getUserAL() {
        return userAL;
    }

    public void setUserAL(ArrayList userAL) {
        this.userAL = userAL;
    }

    public boolean isRenderUserAL() {
        if(userAL.isEmpty()){
            renderUserAL = false;
        } else{
            renderUserAL = true;
        }
        return renderUserAL;
    }

    public void setRenderUserAL(boolean renderUserAL) {
        this.renderUserAL = renderUserAL;
    }

    public UserBinding getUserBinding() {
        return userBinding;
    }

    public void setUserBinding(UserBinding userBinding) {
        this.userBinding = userBinding;
    }

}
