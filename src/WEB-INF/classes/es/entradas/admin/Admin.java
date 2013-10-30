/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.admin;

import java.util.ArrayList;


/**
 *
 * @author User
 */
public class Admin {

    private AdminDAO adminDAO = new AdminDAO();
    private AdminData adminData = new AdminData("","");

    public String restaurarPass(){
        adminDAO.restaurarPass(adminData);
        return "success";
    }

    public ArrayList getAdministradores(){
        ArrayList administradoresAL = new ArrayList();
        administradoresAL = adminDAO.getAdministradores();
        return administradoresAL;
    }

    public String guardarAdmin(){
        if(adminData.getIdAdmin() <= 0){
            adminDAO.insertAdmin(adminData);
        } else{
            adminDAO.modificarAdmin(adminData);
        }
        return "success";
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public AdminData getAdminData() {
        return adminData;
    }

    public void setAdminData(AdminData adminData) {
        this.adminData = adminData;
    }

    public String loginAdmin(){
        adminData.setIdAdmin(adminDAO.loginAdmin(adminData));
        return "goInsertTicket";
    }

}
