/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.admin;

/**
 *
 * @author User
 */
public class AdminData {

    private int idAdmin = 0;
    private String adminLogin = "";
    private String adminPass = "";
    private String nombre = "";
    private String apellidos = "";
    private String nif = "";

    public AdminData(String adminLogin, String adminPass) {
        this.adminLogin = adminLogin;
        this.adminPass = adminPass;
    }

    public AdminData() {
        
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }




}
