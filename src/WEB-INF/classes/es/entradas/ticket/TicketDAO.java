/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.ticket;

import es.entradas.bdatos.DataBase;
import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import es.entradas.utils.Utilidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Takuya
 */
public class TicketDAO {

    //DataBase dataBase = new DataBase();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    private static String ipservidor = PropertyManager.getInstance().getProperty("ipservidor");
    private static String password = PropertyManager.getInstance().getProperty("password");

    public int insertTicket(TicketData ticketData) {
        int resultado = -1;
        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".ticket(date, hour, people, idtype)"
                + " VALUES ("
                + "'" + ticketData.getDia() + "', "
                + "'" + ticketData.getHora() + "', "
                + ticketData.getNumeroEntradas() + ", "
                + ticketData.getIdType() + ")";
        resultado = DataBase.executeInsertAndGetId(sql);
        if (resultado != -1) {
            Utilidades.mostrarInformacion("", "El ticket se ha insertado correctamente. id = " + resultado);
        } else {
            Utilidades.mostrarError("", "No se ha podido insertar el ticket.");
            Log.getInstance().log(sql);
        }
        return resultado;
    }

    /** Se inserta el id del tipo de ticket
     *
     * @param ticketData
     */
    public void insertTickets(TicketData ticketData) {
        String sql = "INSERT INTO " + DataBase.getSchemeName() + ".ticket(date, hour, people, idtype)"
                + " VALUES ("
                + "'" + ticketData.getDia() + "', "
                + "'" + ticketData.getHora() + "', "
                + ticketData.getNumeroEntradas() + ", "
                + "(select id from " + DataBase.getSchemeName() + ".tickettype where UPPER(name) = '" + ticketData.getTicketTypeName().toUpperCase() + "'" + "))";
        if (DataBase.executeUpdate(sql) == DataBase.OPERACION_OK) {
            Utilidades.mostrarInformacion("", "El ticket se ha insertado correctamente. " + ticketData.getIdType() + " - " + ticketData.getNumeroEntradas());
        } else {
            Utilidades.mostrarError("", "No se ha podido insertar el ticket.");
            Log.getInstance().log(sql);
        }
    }

    /*public int insertTicketAndGetId(TicketData ticketData) {
    //postgres es el nombre de la base de datos
    String url = "jdbc:postgresql://" + ipservidor + "/postgres";
    int result = 0;
    Connection con = null;
    Statement stmt = null;
    try {

    //Acceso al Driver
    Class.forName("org.postgresql.Driver");

    //La conexión con los parámetros necesarios
    con = DriverManager.getConnection(url, "postgres", password);

    //Abrimos la conexión y la iniciamos
    stmt = con.createStatement();

    ResultSet rs;

    //Una variable String para almacenar la sentencia SQL
    String query = "INSERT INTO xxx.ticket(date, hour, people, idtype)"
    + " VALUES ("
    + "'" + ticketData.getDia() + "', "
    + "'" + ticketData.getHora() + "', "
    + ticketData.getNumeroEntradas() + ", "
    + ticketData.getIdType() + ")"
    + " returning idticket";

    //En el ResultSet guardamos el resultado de ejecutar la consulta
    rs = stmt.executeQuery(query);

    rs.next();
    result = Integer.parseInt(rs.getString("idticket"));
    //En un ciclo while recorremos cada fila del resultado de nuestro Select
    //while (rs.next()) {

    //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
    // result.add(new TicketTypeData(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category")));
    //}
    //Cerramos la conexión

    } catch (Exception e) {

    //Por si ocurre un error
    System.out.println(e.getMessage());
    e.printStackTrace();
    Log.getInstance().log(e.getMessage());
    } finally {
    try {
    stmt.execute("END");
    } catch (SQLException ex) {
    Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    stmt.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    con.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    return result;
    }*/
    public void modificartTicket(TicketData ticketData) {
        String sql = "UPDATE " + DataBase.getSchemeName() + ".ticket"
                + " SET people = " + ticketData.getNumeroEntradas()
                + ", hour = '" + ticketData.getHora() + "'"
                + ", date = '" + sdf.format(ticketData.getFechaModificada()) + "'"
                //+ ", date = '" + ticketData.getDia() + "'"
                + ", idtype = '" + ticketData.getIdType() + "'"
                + " WHERE idticket = " + ticketData.getIdTicket();
        if (DataBase.executeUpdate(sql) == DataBase.OPERACION_OK) {
            Utilidades.mostrarInformacion("", "El ticket se ha modificado correctamente.");
        } else {
            Utilidades.mostrarError("", "No se ha podido modificar el ticket.");
            Log.getInstance().log(sql);
        }
    }

    /*public ArrayList getAcumuladoTickets(String fechaInicio, String fechaFin, String nombreTipoTicket) {
    ArrayList result = new ArrayList();
    //postgres es el nombre de la base de datos
    String url = "jdbc:postgresql://" + ipservidor + "/postgres";
    //ArrayList result = new ArrayList();
    int idType = 0;
    Connection con = null;
    Statement stmt = null;
    try {

    //Acceso al Driver
    Class.forName("org.postgresql.Driver");

    //La conexión con los parámetros necesarios
    con = DriverManager.getConnection(url, "postgres", password);

    //Abrimos la conexión y la iniciamos
    stmt = con.createStatement();

    ResultSet rs;

    //Una variable String para almacenar la sentencia SQL
    String query = "select date, hour, tt.name, SUM(people) as sum_people, tt.price "
    + " from xxx.ticket t, xxx.tickettype tt"
    + " where t.idtype = tt.id";
    // Se aplica el filtro
    if (fechaInicio == null || fechaInicio.equals("")) {
    fechaInicio = sdf.format(new Date());
    }
    query += " and date >= '" + fechaInicio + "'";
    if (fechaFin == null || fechaFin.equals("")) {
    fechaFin = sdf.format(new Date());
    }
    query += " and date <= '" + fechaFin + "'";
    if (nombreTipoTicket != null && !nombreTipoTicket.equals("")) {
    query += " and tt.name = '" + nombreTipoTicket + "'";
    }
    query += " group by date, hour, tt.name, tt.price order by date, hour, name";
    //query += " order by date, hour";


    //En el ResultSet guardamos el resultado de ejecutar la consulta
    Log.getInstance().log(query);
    rs = stmt.executeQuery(query);
    //rs.next();
    //idType = Integer.parseInt(rs.getString("id"));
    //En un ciclo while recorremos cada fila del resultado de nuestro Select
    while (rs.next()) {
    //idType = Integer.parseInt(rs.getString("id"));

    //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
    //Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category"))
    result.add(new TicketData(Integer.parseInt(rs.getString("sum_people")), rs.getString("name"), sdf2.format(sdf.parse(rs.getString("date"))), rs.getString("hour"), rs.getString("price")));
    }
    //Cerramos la conexión
    } catch (Exception e) {

    //Por si ocurre un error
    System.out.println(e.getMessage());
    e.printStackTrace();
    Log.getInstance().log(e.getMessage());
    } finally {
    try {
    stmt.execute("END");
    } catch (SQLException ex) {
    Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    stmt.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    con.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    return result;
    }*/

    /*public ArrayList getAcumuladoPorTipoTickets(String fechaInicio, String fechaFin, String nombreTipoTicket) {
    ArrayList result = new ArrayList();

    //postgres es el nombre de la base de datos
    String url = "jdbc:postgresql://" + ipservidor + "/postgres";
    //ArrayList result = new ArrayList();
    int idType = 0;
    Connection con = null;
    Statement stmt = null;
    try {

    //Acceso al Driver
    Class.forName("org.postgresql.Driver");

    //La conexión con los parámetros necesarios
    con = DriverManager.getConnection(url, "postgres", password);

    //Abrimos la conexión y la iniciamos
    stmt = con.createStatement();

    ResultSet rs;

    //Una variable String para almacenar la sentencia SQL
    String query = "select date, tt.name, SUM(people) as sum_people, tt.price "
    + " from xxx.ticket t, xxx.tickettype tt"
    + " where t.idtype = tt.id";
    // Se aplica el filtro
    if (fechaInicio == null || fechaInicio.equals("")) {
    fechaInicio = sdf.format(new Date());
    }
    query += " and date >= '" + fechaInicio + "'";
    if (fechaFin == null || fechaFin.equals("")) {
    fechaFin = sdf.format(new Date());
    }
    query += " and date <= '" + fechaFin + "'";
    if (nombreTipoTicket != null && !nombreTipoTicket.equals("")) {
    query += " and tt.name = '" + nombreTipoTicket + "'";
    }
    query += " group by tt.name, date, tt.price"
    + " order by tt.name, date";
    //query += " order by date, hour";


    //En el ResultSet guardamos el resultado de ejecutar la consulta
    Log.getInstance().log(query);
    rs = stmt.executeQuery(query);
    //rs.next();
    //idType = Integer.parseInt(rs.getString("id"));
    //En un ciclo while recorremos cada fila del resultado de nuestro Select
    while (rs.next()) {
    //idType = Integer.parseInt(rs.getString("id"));
    //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
    //Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category"))
    result.add(new TicketData(Integer.parseInt(rs.getString("sum_people")), rs.getString("name"), sdf2.format(sdf.parse(rs.getString("date"))), "", rs.getString("price")));
    }

    //Cerramos la conexión

    } catch (Exception e) {

    //Por si ocurre un error
    System.out.println(e.getMessage());
    e.printStackTrace();
    Log.getInstance().log(e.getMessage());
    } finally {
    try {
    stmt.execute("END");
    } catch (SQLException ex) {
    Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    stmt.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    con.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    if (result.size() > 0) {

    String tipoActual = "";
    String totalTipoTicketActual = "0";
    int totalNumeroEntradas = 0;
    String totalTotal = "0";
    int totalTotalInt = 0;
    TicketData ticketData = new TicketData();

    for (int i = 0; i < result.size(); i++) {
    ticketData = (TicketData) result.get(i);
    if (ticketData.getTicketTypeName().equals(tipoActual)) {
    ticketData.setTotalTipoTicket(totalTipoTicketActual);
    ticketData.sumarTotalTipoTicket(ticketData.getTotalTipoTicket());
    ticketData.setTotalNumeroEntradas(totalNumeroEntradas);
    ticketData.sumarTotalNumeroEntradas(ticketData.getTotalNumeroEntradas());
    } else {
    totalTotalInt += totalNumeroEntradas;
    totalTotal = Float.parseFloat(totalTotal) + Float.parseFloat(totalTipoTicketActual) + "";
    ticketData.setTotalTipoTicket(ticketData.getTotal());
    ticketData.setTotalNumeroEntradas(ticketData.getNumeroEntradas());
    }
    tipoActual = ticketData.getTicketTypeName();
    totalNumeroEntradas = ticketData.getTotalNumeroEntradas();
    totalTipoTicketActual = ticketData.getTotalTipoTicket();
    ticketData.setTotalTotal(totalTotal);
    ticketData.setTotalTotalInt(totalTotalInt);
    ticketData.setLastPage(true);
    }
    totalTotalInt += totalNumeroEntradas;
    totalTotal = Float.parseFloat(totalTotal) + Float.parseFloat(totalTipoTicketActual) + "";
    ticketData.setTotalTotal(totalTotal);
    ticketData.setTotalTotalInt(totalTotalInt);
    }

    return result;
    }*/
    public ArrayList getArqueoDiarioTickets() {
        ArrayList result = new ArrayList();
        /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
        //postgres es el nombre de la base de datos
        String url = "jdbc:postgresql://" + ipservidor + "/postgres";
        //ArrayList result = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        try {

//Acceso al Driver
            Class.forName("org.postgresql.Driver");

//La conexión con los parámetros necesarios
            con = DriverManager.getConnection(url, "postgres", password);

//Abrimos la conexión y la iniciamos
            stmt = con.createStatement();

            /*Un ResultSet es como en .NET un DataSet, un arreglo temporal donde se almacenará el resultado de la consulta SQL*/
            ResultSet rs;

//Una variable String para almacenar la sentencia SQL
            String query = "select tt.name, SUM(people) as sum_people, tt.price, tt.category "
                    + " from xxx.ticket t, xxx.tickettype tt"
                    + " where t.idtype = tt.id";

            query += " and date >= '" + sdf.format(new Date()) + "'";

            query += " and date <= '" + sdf.format(new Date()) + "'";

            query += " group by tt.name, tt.price, tt.category order by tt.category, tt.name";
            //query += " order by date, hour";


//En el ResultSet guardamos el resultado de ejecutar la consulta
            Log.getInstance().log(query);
            rs = stmt.executeQuery(query);
            //rs.next();
            //idType = Integer.parseInt(rs.getString("id"));
//En un ciclo while recorremos cada fila del resultado de nuestro Select
            int totalNumeroEntradas = 0;
            String totalTotal = "0";
            TicketData ticketData;
            TicketData ticketData2;
            while (rs.next()) {
                //idType = Integer.parseInt(rs.getString("id"));
                /*Aqui practicamente podemos hacer lo que deseemos con el resultado, en mi caso solo lo mande a imprimir*/
                //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
                //Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category"))
                /* ---------------------------------------------------------
                ESTO ES LO QUE VA AQUI
                ticketData = new TicketData(Integer.parseInt(rs.getString("sum_people")), "", rs.getString("name"), rs.getString("price"), rs.getString("category"), 0, "");
                totalTotal = Float.parseFloat(totalTotal) + Float.parseFloat(ticketData.getTotal()) + "";
                totalNumeroEntradas += Integer.parseInt(rs.getString("sum_people"));
                ticketData2 = new TicketData(Integer.parseInt(rs.getString("sum_people")), ticketData.getTotal(), rs.getString("name"), rs.getString("price"), rs.getString("category"), totalNumeroEntradas, totalTotal);
                result.add(ticketData2);
                ----------------------------------------------------------*/
            }
            /*
            id integer NOT NULL,
            "name" character varying(50) NOT NULL,
            price character varying(10) NOT NULL,
            category integer NOT NULL,
             */
//Cerramos la conexión

        } catch (Exception e) {

//Por si ocurre un error
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(e.getMessage());
        } finally {
            try {
                stmt.execute("END");
            } catch (SQLException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    /*public ArrayList getTickets(String fechaInicio, String fechaFin, String nombreTipoTicket) {
    ArrayList result = new ArrayList();
    //postgres es el nombre de la base de datos
    String url = "jdbc:postgresql://" + ipservidor + "/postgres";
    //ArrayList result = new ArrayList();
    int idType = 0;
    Connection con = null;
    Statement stmt = null;
    try {

    //Acceso al Driver
    Class.forName("org.postgresql.Driver");

    //La conexión con los parámetros necesarios
    con = DriverManager.getConnection(url, "postgres", password);

    //Abrimos la conexión y la iniciamos
    stmt = con.createStatement();

    ResultSet rs;

    //Una variable String para almacenar la sentencia SQL
    String query = "select date, hour, tt.name, people "
    + " from xxx.ticket t, xxx.tickettype tt"
    + " where t.idtype = tt.id";
    // Se aplica el filtro
    if (fechaInicio == null || fechaInicio.equals("")) {
    fechaInicio = sdf.format(new Date());
    }
    query += " and date >= '" + fechaInicio + "'";
    if (fechaFin == null || fechaFin.equals("")) {
    fechaFin = sdf.format(new Date());
    }
    query += " and date <= '" + fechaFin + "'";
    if (nombreTipoTicket != null && !nombreTipoTicket.equals("")) {
    query += " and tt.name = '" + nombreTipoTicket + "'";
    }
    query += " order by date, hour";


    //En el ResultSet guardamos el resultado de ejecutar la consulta
    rs = stmt.executeQuery(query);
    //rs.next();
    //idType = Integer.parseInt(rs.getString("id"));
    //En un ciclo while recorremos cada fila del resultado de nuestro Select
    while (rs.next()) {
    //idType = Integer.parseInt(rs.getString("id"));
    //System.out.println("id = " + rs.getString("id") + " name = " + rs.getString("name") + " price = " + rs.getString("price"));
    //Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("price"), rs.getString("category"))
    result.add(new TicketData(Integer.parseInt(rs.getString("people")), rs.getString("name"), rs.getString("date"), rs.getString("hour")));
    }
    //Cerramos la conexión

    } catch (Exception e) {

    //Por si ocurre un error
    System.out.println(e.getMessage());
    e.printStackTrace();
    Log.getInstance().log(e.getMessage());
    } finally {
    try {
    stmt.execute("END");
    } catch (SQLException ex) {
    Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    stmt.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    con.close();
    } catch (SQLException ex) {
    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    return result;
    }*/
    public ArrayList getTickets(Date fechaInicio, Date fechaFin, int idTicketType) {
        ArrayList resultAL = new ArrayList();
        String query = "SELECT date, hour, people, idtype, idticket,"
                + " (SELECT name FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype),"
                + " (SELECT price FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype)"
                + "FROM " + DataBase.getSchemeName() + ".ticket"
                + " WHERE 1 = 1";
        String fechaInicioStr = "";
        String fechaFinStr = "";
        if (fechaInicio == null) {
            fechaInicioStr = sdf.format(new Date());
        } else {
            fechaInicioStr = sdf.format(fechaInicio);
        }
        query += " and date >= '" + fechaInicioStr + "'";
        if (fechaFin == null) {
            fechaFinStr = sdf.format(new Date());
        } else {
            fechaFinStr = sdf.format(fechaFin);
        }
        query += " and date <= '" + fechaFinStr + "'";
        if (idTicketType > 0) {
            query += " and idtype = '" + idTicketType + "'";
        }
        query += " order by date, hour";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            //int totalNumeroEntradas = 0;
            //int numeroEntradas = 0;
            //double totalTotal = 0;
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    TicketData data = new TicketData();
                    data.setDia(((String[]) resultados.elementAt(i))[0]);
                    data.setHora(((String[]) resultados.elementAt(i))[1]);
                    //numeroEntradas = Integer.parseInt(((String[]) resultados.elementAt(i))[2]);
                    //data.setNumeroEntradas(numeroEntradas);
                    data.setNumeroEntradas(Integer.parseInt(((String[]) resultados.elementAt(i))[2]));
                    //totalNumeroEntradas += numeroEntradas;
                    //data.setTotalNumeroEntradas(totalNumeroEntradas);
                    data.setIdType(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    data.setIdTicket(Integer.parseInt(((String[]) resultados.elementAt(i))[4]));
                    data.setNombreTipoTicket(((String[]) resultados.elementAt(i))[5]);
                    data.setPrecio(((String[]) resultados.elementAt(i))[6]);
                    //totalTotal += Double.parseDouble(data.getTotal());
                    //data.setTotalTotal(totalTotal + "");
                    resultAL.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(query);
            Log.getInstance().log(e.getMessage());

        }
        return resultAL;
    }

    public ArrayList getTicketsGroupByType(Date fechaInicio, Date fechaFin, int idTicketType) {
        ArrayList resultAL = new ArrayList();
        String query = "SELECT date, hour, people, idtype, idticket,"
                + " (SELECT name FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype) as typeName,"
                + " (SELECT price FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype)"
                + "FROM " + DataBase.getSchemeName() + ".ticket"
                + " WHERE 1 = 1";
        String fechaInicioStr = "";
        String fechaFinStr = "";
        if (fechaInicio == null) {
            fechaInicioStr = sdf.format(new Date());
        } else {
            fechaInicioStr = sdf.format(fechaInicio);
        }
        query += " and date >= '" + fechaInicioStr + "'";
        if (fechaFin == null) {
            fechaFinStr = sdf.format(new Date());
        } else {
            fechaFinStr = sdf.format(fechaFin);
        }
        query += " and date <= '" + fechaFinStr + "'";
        if (idTicketType > 0) {
            query += " and idtype = '" + idTicketType + "'";
        }
        query += " order by typeName, date, hour";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {
                    TicketData data = new TicketData();
                    data.setDia(((String[]) resultados.elementAt(i))[0]);
                    data.setHora(((String[]) resultados.elementAt(i))[1]);
                    data.setNumeroEntradas(Integer.parseInt(((String[]) resultados.elementAt(i))[2]));
                    data.setIdType(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    data.setIdTicket(Integer.parseInt(((String[]) resultados.elementAt(i))[4]));
                    data.setNombreTipoTicket(((String[]) resultados.elementAt(i))[5]);
                    data.setPrecio(((String[]) resultados.elementAt(i))[6]);
                    resultAL.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(query);
            Log.getInstance().log(e.getMessage());

        }
        return resultAL;
    }

    public TicketData getTicket(int idTicketType) {
        TicketData data = new TicketData();
        String query = "SELECT date, hour, people, idtype, idticket, "
                + "(SELECT name FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype),"
                + " (SELECT price FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype)"
                + "FROM " + DataBase.getSchemeName() + ".ticket"
                + " WHERE 1 = 1";
        if (idTicketType > 0) {
            query += " and idtype <= '" + idTicketType + "'";
        }
        query += " order by date, hour";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {

                    data.setDia(((String[]) resultados.elementAt(i))[0]);
                    data.setHora(((String[]) resultados.elementAt(i))[1]);
                    data.setNumeroEntradas(Integer.parseInt(((String[]) resultados.elementAt(i))[2]));
                    data.setIdType(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    data.setIdTicket(Integer.parseInt(((String[]) resultados.elementAt(i))[4]));
                    data.setNombreTipoTicket(((String[]) resultados.elementAt(i))[5]);
                    data.setPrecio(((String[]) resultados.elementAt(i))[6]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(query);
            Log.getInstance().log(e.getMessage());

        }
        return data;
    }

    public TicketData getTicketByTicketId(int idTicket) {
        TicketData data = new TicketData();
        String query = "SELECT date, hour, people, idtype, idticket, "
                + "(SELECT name FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype),"
                + " (SELECT price FROM " + DataBase.getSchemeName() + ".tickettype WHERE id = idtype)"
                + "FROM " + DataBase.getSchemeName() + ".ticket"
                + " WHERE 1 = 1";
        if (idTicket > 0) {
            query += " and idticket = '" + idTicket + "'";
        }
        query += " order by date, hour";

        Vector resultados;
        try {
            resultados = DataBase.executeQuery(query);
            if (resultados != null && resultados.size() > 0) {
                for (int i = 0; i < resultados.size(); i++) {

                    data.setDia(((String[]) resultados.elementAt(i))[0]);
                    data.setHora(((String[]) resultados.elementAt(i))[1]);
                    data.setNumeroEntradas(Integer.parseInt(((String[]) resultados.elementAt(i))[2]));
                    data.setIdType(Integer.parseInt(((String[]) resultados.elementAt(i))[3]));
                    data.setIdTicket(Integer.parseInt(((String[]) resultados.elementAt(i))[4]));
                    data.setNombreTipoTicket(((String[]) resultados.elementAt(i))[5]);
                    data.setPrecio(((String[]) resultados.elementAt(i))[6]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.getInstance().log(query);
            Log.getInstance().log(e.getMessage());

        }
        return data;
    }
}
