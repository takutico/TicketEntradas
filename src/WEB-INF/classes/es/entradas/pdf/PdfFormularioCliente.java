/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.pdf;

import es.entradas.ticket.Ticket;
import es.entradas.ticket.TicketData;
import es.entradas.user.User;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author takuya
 */
public class PdfFormularioCliente extends HttpServlet implements Serializable {

    private Map parametros = new HashMap();
    //private User user = new User();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getUser();

        /*se encarga de establecer las cabeceras adecuadas en la respuesta regresada al cliente para que este sepa cómo tratar los datos*/
        response.setContentType("application/pdf");
        /* "Content-Disposition" para que el usuario decida si quiere guardar este reporte en su máquina o
         * abrirlo con alguna otra aplicación en vez de usar directamente el navegador
         * "attachment; filename=" y el nombre que queremos darle a nuestro reporte. Para establecer esta cabcera usamos el método "setHeader"
         */
        response.setHeader("Content-Disposition", "attachment; filename=\"formulario.pdf\";");
        /*Para evitar que nuestro navegador guarde el archivo en cache */
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        /*obtener el ServletOutputStream correspondiente para enviar la respuesta al cliente:*/
        ServletOutputStream out = response.getOutputStream();

        try {
            //Ticket ticketTmp = Ticket.getTicketFacesContext();
            //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //Segun los datos obtenidos se ejecuta mensajesLeidos.jasper o mensajesNoLeidos.jasper
            JasperReport reporte;

            parametros.put("LOGO", getServletContext().getRealPath("/media/logo_4.png"));

            reporte = (JasperReport) JRLoader.loadObject(
                    getServletContext().getRealPath("/WEB-INF/classes/es/entradas/jasper/FormularioCliente.jasper"));
            /*WEB-INF\classes\es\entradas\jasper*/
            //Establece una conexion con la base de datos y una sentencia SQL
            //Class.forName("org.postgresql.Driver");
            //conn = (Connection) DriverManager.getConnection(url, login, password);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conn);
            //Ejemplo con DataSourse
            //JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, mensajes.getMensajesData().getMensajeData());

            //Ejemplo con un CollectionDataSourse
            /*ArrayList tmpAL = new ArrayList();
            ArrayList resultAL = new ArrayList();
            tmpAL = ticketTmp.getTicketAL();

            TicketData data = null;
            int totalNumeroEntradas = 0;
            //int numeroEntradas = 0;
            double totalTotal = 0;
            for (int i = 0; i < tmpAL.size(); i++) {
            data = (TicketData) tmpAL.get(i);
            totalNumeroEntradas += data.getNumeroEntradas();
            data.setTotalNumeroEntradas(totalNumeroEntradas);
            totalTotal += Double.parseDouble(data.getTotal());
            data.setTotalTotal(totalTotal + "");
            resultAL.add(data);
            }*/
            ArrayList resultAL = new ArrayList();
            resultAL.add("Esto no va");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
                    new JRBeanCollectionDataSource(resultAL));
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            //Esto es para exportar el reporte en exel
            /*JRExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);*/
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
