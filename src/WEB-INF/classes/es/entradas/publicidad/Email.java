/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.publicidad;

/**
 *
 * @author takuya
 */
import es.entradas.cliente.Cliente;
import es.entradas.cliente.ClienteData;
import es.entradas.utils.Log;
import es.entradas.utils.PropertyManager;
import es.entradas.utils.Utilidades;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.faces.event.ValueChangeEvent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import javax.faces.context.FacesContext;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class Email {

    private EmailData emailData = new EmailData();
    private EmailBinding emailBinding = new EmailBinding();

    public void goSeleccionDestinatarios(ReturnEvent event) {
        emailData.setTo(event.getReturnValue().toString());
    }

    public void procesarUpload(ActionListener evento) {
        UploadedFile file = emailData.getAdjunto();

        if (file != null) {

            FacesContext context = FacesContext.getCurrentInstance();
            /*FacesMessage message = new FacesMessage(
            "Subida del fichero " + file.getFilename() +
            " satisfactoria (" + file.getLength() +
            " bytes)");*/
            //context.addMessage(evento.getComponent().getClientId(context),message);

            // Aqui procesariamos el fichero
            // para guardarlo donde queramos

        }

    }

    public String upload() {
        try {
            InputStream stream = emailData.getAdjunto().getInputStream();
            long size = emailData.getAdjunto().getSize();
            byte[] buffer = new byte[(int) size];
            stream.read(buffer, 0, (int) size);
            stream.close();
            //rendSuccess = true;
            //rendFailure = false;
            System.out.println("File Upload Successful.");
            return "ok";
        } catch (Exception ioe) {
            System.out.println("File Upload Unsuccessful.");
            //rendSuccess = false;
            //rendFailure = true;
            return "no";
        }
    }

    public String obtnerEmailClientes() {
        //Se obtienen los datos seleccionados de la tabla
        ArrayList tempAL = new ArrayList();
        RowKeySet rowKeys = Cliente.getClienteInstance().getClienteBinding().getTblClientes().getSelectedRowKeys();
        Iterator iterador = rowKeys.iterator();
        while (iterador.hasNext()){
            Object valor = iterador.next();
            Cliente.getClienteInstance().getClienteBinding().getTblClientes().setRowKey(valor);
            ClienteData row = (ClienteData) Cliente.getClienteInstance().getClienteBinding().getTblClientes().getRowData();
            tempAL.add(row);
        }
        String to = "";
        for(int i = 0; i < tempAL.size(); i++){
            to += ";" + ((ClienteData)tempAL.get(i)).getEmail();
        }
        if(to.length() > 0){
            to = to.substring(1, to.length());
        }
        emailData.setTo(to);
        //emailData.setTo(emailBinding.getTblClientes().get);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.returnFromDialog(emailData.getTo(), null);
        //return null;
        return "success";
    }

    public String sendEmailClienteExterno() {
        String contenido = "Se ha dado de alta para recibir información de interés.";
        contenido += "\r\rSi no desea volver a recibir correos electrónicos con información de interés, contacte con un empleado o envíe un correo electrónico a pfc.gestionentradas@gmail.com.";

        send(emailData.getSubject(), emailData.getTo(), contenido, emailData.getAdjunto());
        return "success";
    }

    public String sendEmailCliente() {
        String contenido = emailData.getContent();
        contenido += "\r\rSi no desea volver a recibir correos electrónicos con información de interés, contacte con un empleado o envíe un correo electrónico a pfc.gestionentradas@gmail.com.";

        send(emailData.getSubject(), emailData.getTo(), contenido, emailData.getAdjunto());
        return "success";
    }

    public String sendEmail() {
        send(emailData.getSubject(), emailData.getTo(), emailData.getContent(), emailData.getAdjunto());
        return "success";
    }

    public void fileUploaded(ValueChangeEvent event) {
        UploadedFile file = (UploadedFile) event.getNewValue();
        if (file != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            /*FacesMessage message = new FacesMessage(
            "Successfully uploaded file " + file.getFilename() +
            " (" + file.getLength() + " bytes)");*/
            /*context.addMessage(event.getComponent().getClientId(context), message);*/
            // Here's where we could call file.getInputStream()
        }
    }

    public static void send(final String subject, final String to, final String content, final UploadedFile file) {

        try {
            // Se obtienen los parametros del properties
            String smtpHost = PropertyManager.getInstance().getProperty("email.smtpHost");
            String smtpPort = PropertyManager.getInstance().getProperty("email.smtpPort");
            String from = PropertyManager.getInstance().getProperty("email.from");
            String starttlsEnable = PropertyManager.getInstance().getProperty("email.starttlsEnable");
            String user = PropertyManager.getInstance().getProperty("email.user");
            String smtpAuth = PropertyManager.getInstance().getProperty("email.smtpAuth");
            String pass = PropertyManager.getInstance().getProperty("email.pass");

            Properties props = new Properties();
            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", smtpHost);
            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", starttlsEnable);
            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", smtpPort);
            // Nombre del usuario
            props.setProperty("mail.smtp.user", user);
            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", smtpAuth);
            Session session = Session.getDefaultInstance(props);
            // Para obtener un log de salida más extenso
            session.setDebug(true);
            // Se construye el texto
            BodyPart texto = new MimeBodyPart();
            // Texto del mensaje
            texto.setText(content);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setText(content);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            /***************Se copia el archivo en una carpeta temporal********************/
            String urlFile = "";
            if (file != null) {
                InputStream stream = file.getInputStream();
                long size = file.getSize();
                byte[] buffer = new byte[(int) size];
                stream.read(buffer, 0, (int) size);
                stream.close();
                String uploadPath = "c:" + File.separator + "temp";
                String nombre = file.getName();//.substring(file.getName().lastIndexOf(File.separator));
                urlFile = uploadPath + File.separator + nombre;
                FileOutputStream out = new FileOutputStream(urlFile);
                out.write(buffer, 0, (int) size);
                out.close();
                //Luego construimos la parte del adjunto con la imagen. Suponemos que la tenemos en un fichero
                BodyPart adjunto = new MimeBodyPart();
                // Cargamos la imagen
                adjunto.setDataHandler(new DataHandler(new FileDataSource(urlFile)));
                // Opcional. De esta forma transmitimos al receptor el nombre original del fichero de imagen.
                adjunto.setFileName(nombre);
                //Ahora juntamos ambas en una sola parte que luego debemos añadir al mensaje
                multiParte.addBodyPart(texto);
                multipart.addBodyPart(adjunto);
            }
            //Finalmente construimos el mensaje, le ponemos este MimeMultiPart como contenido y rellenamos el resto de campos from, to y subject.
            MimeMessage message = new MimeMessage(session);
            // Se rellena el From, destinatarios, subject y el contenido (texto y adjunto).
            message.setFrom(new InternetAddress(from));
            StringTokenizer tokens = new StringTokenizer(to, ";");
            String temp;
            while (tokens.hasMoreTokens()) {
                temp = tokens.nextToken();
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(temp));
            }
            message.setSubject(subject);
            message.setContent(multipart);
            //Envio del mensaje, previamente se inserta el user y el pass de la cuenta de correo
            Transport t = session.getTransport("smtp");
            t.connect(user, pass);
            t.sendMessage(message, message.getAllRecipients());
            Utilidades.mostrarInformacion("", "Se ha enviado el email.");
            t.close();

        } catch (AddressException ex) {
            ex.printStackTrace();
            Log.getInstance().log("Email.send AddressException: " + ex.getMessage());
        } catch (MessagingException ex) {
            Log.getInstance().log("Email.send MessagingException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Log.getInstance().log("Email.send Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public EmailData getEmailData() {
        return emailData;
    }

    public void setEmailData(EmailData emailData) {
        this.emailData = emailData;
    }

    public EmailBinding getEmailBinding() {
        return emailBinding;
    }

    public void setEmailBinding(EmailBinding emailBinding) {
        this.emailBinding = emailBinding;
    }
}
