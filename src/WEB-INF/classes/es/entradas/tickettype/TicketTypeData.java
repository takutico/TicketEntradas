/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.tickettype;

/**
 *
 * @author Takuya
 */
public class TicketTypeData {
/*
   id integer NOT NULL,
  "name" character varying(50) NOT NULL,
  price character varying(10) NOT NULL,
  category integer NOT NULL,
 */
    public static final String CATEGORIA_1 = "No gratuito";
    public static final String CATEGORIA_0 = "Gratuito";

    private int id;
    private String name;
    private String price;
    private String category;
    private boolean activo = true;

    // Variables para los pdf e informes
    private String nombreCategoria = "";

    public TicketTypeData() {
        this.id = 0;
        this.name = "";
        this.price = "0";
        this.category = "";
    }

    public TicketTypeData(int id, String name, String price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


}
