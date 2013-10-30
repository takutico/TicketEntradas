/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.cliente;

/**
 *
 * @author User
 */
public class ClienteData {

    private int id = -1;
    private String nombre = "";
    private String apellidos = "";
    private String nif = "";
    private String direccion = "";
    private String localidad = "";
    private String provincia = "";
    private String pais = "";
    private String cp = "";
    private String nacionalidad = "";
    private String email = "";
    private String email_2 = "";
    private String telefono = "";
    private String movil = "";
    private boolean ofertaEmail = false;
    private boolean ofertaSms = false;
    private boolean ofertaPostal = false;
    private boolean activo = false;

    private String opcionActivo = "TODOS";

    protected void inicializaDatos() {
        id = -1;
        nombre = "";
        apellidos = "";
        nif = "";
        direccion = "";
        localidad = "";
        provincia = "";
        pais = "";
        cp = "";
        nacionalidad = "";
        email = "";
        email_2 = "";
        telefono = "";
        movil = "";
        ofertaEmail = true;
        ofertaSms = false;
        ofertaPostal = false;
        activo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_2() {
        return email_2;
    }

    public void setEmail_2(String email_2) {
        this.email_2 = email_2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isOfertaEmail() {
        return ofertaEmail;
    }

    public void setOfertaEmail(boolean ofertaEmail) {
        this.ofertaEmail = ofertaEmail;
    }

    public boolean isOfertaSms() {
        return ofertaSms;
    }

    public void setOfertaSms(boolean ofertaSms) {
        this.ofertaSms = ofertaSms;
    }

    public boolean isOfertaPostal() {
        return ofertaPostal;
    }

    public void setOfertaPostal(boolean ofertaPostal) {
        this.ofertaPostal = ofertaPostal;
    }

    public String getOpcionActivo() {
        return opcionActivo;
    }

    public void setOpcionActivo(String opcionActivo) {
        this.opcionActivo = opcionActivo;
    }
}
