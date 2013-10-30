package es.entradas.xls;

import es.entradas.ticket.Ticket;
import es.entradas.ticket.TicketData;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import es.entradas.utils.Log;
import java.util.Date;

/**
 *
 * @author takuya
 * @version
 */
public class XlsTicket extends HttpServlet {

    private HSSFFont fuente;
    private HSSFCellStyle estiloCelda;
    private HSSFCellStyle estiloCeldaDatos;
    private HSSFWorkbook libro;
    private HSSFRow fila;
    private HSSFSheet hoja;
    private HSSFRow filaDatos;

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        response.setContentType("text/xls");
        response.setHeader("Cache-Control", "no-cache");

        ServletOutputStream document = response.getOutputStream();

        libro = new HSSFWorkbook();
        hoja = libro.createSheet("Ticket");

        configurarEstilos();

        Ticket t = new Ticket();
        javax.faces.context.FacesContext fc = org.apache.myfaces.context.FacesContextWrapper.getCurrentInstance();
        t = (Ticket) fc.getExternalContext().getSessionMap().get("ticket");

        try {

            ArrayList tAL = new ArrayList();
            tAL = t.getTicketAL();
            int tableRows = tAL.size();

            String rangoFechas = "";
            if(t.getTicketData().getFechaInicio() != null){
                rangoFechas += "( de" + sdf.format(t.getTicketData().getFechaInicio());
            } else{
                rangoFechas += "( de" + sdf.format(new Date());
            }
            if(t.getTicketData().getFechaFin() != null){
                rangoFechas += " a " + sdf.format(t.getTicketData().getFechaFin()) + ")";
            } else{
                rangoFechas += " a " + sdf.format(new Date()) + ")";
            }

            fila = hoja.createRow((short) 0);
            fila.createCell(0).setCellValue(new HSSFRichTextString("Tickets - " + rangoFechas));
            hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            fila.getCell(0).setCellStyle(estiloCelda);

            fila = hoja.createRow((short) 2);
            fila.createCell(0).setCellValue(new HSSFRichTextString("Dia"));
            fila.getCell(0).setCellStyle(estiloCelda);
            fila.createCell(1).setCellValue(new HSSFRichTextString("Hora"));
            fila.getCell(1).setCellStyle(estiloCelda);
            fila.createCell(2).setCellValue(new HSSFRichTextString("Tipo ticket"));
            fila.getCell(2).setCellStyle(estiloCelda);
            fila.createCell(3).setCellValue(new HSSFRichTextString("Nº entradas"));
            fila.getCell(3).setCellStyle(estiloCelda);
            fila.createCell(4).setCellValue(new HSSFRichTextString("Costo (€)"));
            fila.getCell(4).setCellStyle(estiloCelda);

            for (int i = 0; i < tableRows; i++) {

                filaDatos = hoja.createRow((short) i + 3);
                String dia = ((TicketData) tAL.get(i)).getDia();
                String hora = ((TicketData) tAL.get(i)).getHora();
                String nombreTipoTicket = ((TicketData) tAL.get(i)).getNombreTipoTicket();
                String numEntradas = ((TicketData) tAL.get(i)).getNumeroEntradas() + "";
                String total = ((TicketData) tAL.get(i)).getTotal();

                filaDatos.createCell(0).setCellValue(new HSSFRichTextString(dia));
                filaDatos.createCell(1).setCellValue(new HSSFRichTextString(hora));
                filaDatos.createCell(2).setCellValue(new HSSFRichTextString(nombreTipoTicket));
                filaDatos.createCell(3).setCellValue(new HSSFRichTextString(numEntradas.toString()));
                filaDatos.createCell(4).setCellValue(new HSSFRichTextString(total));
            }

            hoja.setDefaultColumnWidth(20);

        } catch (java.lang.Exception ex) {
            Log.getInstance().log(ex.toString());
            ex.printStackTrace();
        }

        libro.write(document);
        document.close();

    }

    private void configurarEstilos() {
        // Se establece el tipo de fuente
        fuente = libro.createFont();
        fuente.setFontHeightInPoints((short) 8);
        fuente.setFontName(HSSFFont.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // Se aplica el estilo a la celda
        estiloCelda = libro.createCellStyle();
        estiloCelda.setWrapText(true);
        estiloCelda.setFont(fuente);
        estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        estiloCelda.setFillForegroundColor((short) 22);
        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        estiloCeldaDatos = libro.createCellStyle();
        estiloCeldaDatos.setWrapText(true);
        estiloCeldaDatos.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        estiloCeldaDatos.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
// </editor-fold>
}
