package es.entradas.xls;

import es.entradas.user.User;
import es.entradas.user.UserData;
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
public class XlsUser extends HttpServlet {

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
        hoja = libro.createSheet("Usuarios");

        configurarEstilos();

        User data = new User();
        javax.faces.context.FacesContext fc = org.apache.myfaces.context.FacesContextWrapper.getCurrentInstance();
        data = (User) fc.getExternalContext().getSessionMap().get("user");

        try {

            ArrayList dataAL = new ArrayList();
            dataAL = data.getUserAL();
            int tableRows = dataAL.size();

            fila = hoja.createRow((short) 0);
            fila.createCell(0).setCellValue(new HSSFRichTextString("Usuarios - " + sdf.format(new Date())));
            hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            fila.getCell(0).setCellStyle(estiloCelda);

            fila = hoja.createRow((short) 2);
            fila.createCell(0).setCellValue(new HSSFRichTextString("Apellidos"));
            fila.getCell(0).setCellStyle(estiloCelda);
            fila.createCell(1).setCellValue(new HSSFRichTextString("Nombre"));
            fila.getCell(1).setCellStyle(estiloCelda);
            fila.createCell(2).setCellValue(new HSSFRichTextString("NIF"));
            fila.getCell(2).setCellStyle(estiloCelda);
            fila.createCell(3).setCellValue(new HSSFRichTextString("Dirección"));
            fila.getCell(3).setCellStyle(estiloCelda);
            fila.createCell(4).setCellValue(new HSSFRichTextString("Localidad"));
            fila.getCell(4).setCellStyle(estiloCelda);
            fila.createCell(5).setCellValue(new HSSFRichTextString("Provincia"));
            fila.getCell(5).setCellStyle(estiloCelda);
            fila.createCell(6).setCellValue(new HSSFRichTextString("CP"));
            fila.getCell(6).setCellStyle(estiloCelda);
            fila.createCell(7).setCellValue(new HSSFRichTextString("Email"));
            fila.getCell(7).setCellStyle(estiloCelda);
            fila.createCell(8).setCellValue(new HSSFRichTextString("Telefono"));
            fila.getCell(8).setCellStyle(estiloCelda);
            fila.createCell(9).setCellValue(new HSSFRichTextString("Móvil"));
            fila.getCell(9).setCellStyle(estiloCelda);
            fila.createCell(10).setCellValue(new HSSFRichTextString("Activo"));
            fila.getCell(10).setCellStyle(estiloCelda);
            fila.createCell(11).setCellValue(new HSSFRichTextString("Administrador"));
            fila.getCell(11).setCellStyle(estiloCelda);

            for (int i = 0; i < tableRows; i++) {

                filaDatos = hoja.createRow((short) i + 3);
                String apellidos = ((UserData) dataAL.get(i)).getApellidos();
                String nombre = ((UserData) dataAL.get(i)).getNombre();
                String nif = ((UserData) dataAL.get(i)).getNif();
                String direccion = ((UserData) dataAL.get(i)).getDireccion();
                String localidad = ((UserData) dataAL.get(i)).getLocalidad();
                String provincia = ((UserData) dataAL.get(i)).getProvincia();
                String cp = ((UserData) dataAL.get(i)).getCp();
                String email = ((UserData) dataAL.get(i)).getEmail();
                String telefono = ((UserData) dataAL.get(i)).getTelefono();
                String movil = ((UserData) dataAL.get(i)).getMovil();

                String activo ="";
                if(((UserData) dataAL.get(i)).isActivo()){
                    activo = "Si";
                } else{
                    activo = "No";
                }
                String admin ="";
                if(((UserData) dataAL.get(i)).isAdmin()){
                    admin = "Si";
                } else{
                    admin = "No";
                }

                filaDatos.createCell(0).setCellValue(new HSSFRichTextString(apellidos));
                filaDatos.createCell(1).setCellValue(new HSSFRichTextString(nombre));
                filaDatos.createCell(2).setCellValue(new HSSFRichTextString(nif));
                filaDatos.createCell(3).setCellValue(new HSSFRichTextString(direccion));
                filaDatos.createCell(4).setCellValue(new HSSFRichTextString(localidad));
                filaDatos.createCell(5).setCellValue(new HSSFRichTextString(provincia));
                filaDatos.createCell(6).setCellValue(new HSSFRichTextString(cp));
                filaDatos.createCell(7).setCellValue(new HSSFRichTextString(email));
                filaDatos.createCell(8).setCellValue(new HSSFRichTextString(telefono));
                filaDatos.createCell(9).setCellValue(new HSSFRichTextString(movil));
                filaDatos.createCell(10).setCellValue(new HSSFRichTextString(activo));
                filaDatos.createCell(11).setCellValue(new HSSFRichTextString(admin));
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
