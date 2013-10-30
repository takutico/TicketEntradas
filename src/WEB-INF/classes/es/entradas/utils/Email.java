package es.entradas.utils;

/**
 *
 * @author takuya
 */
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

    /*public static void send(final String smtpHost, final int smtpPort,
    final String from, final String to,
    final String subject, final String content, final String[] files) {*/
    public static void send(final String to, final String content, final String filePath) {

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    // Se obtienen los parametros del properties
                    String smtpHost = PropertyManager.getInstance().getProperty("email.smtpHost");
                    String smtpPort = PropertyManager.getInstance().getProperty("email.smtpPort");
                    String from = PropertyManager.getInstance().getProperty("email.from");
                    //String to = PropertyManager.getInstance().getProperty("email.to");
                    String subject = PropertyManager.getInstance().getProperty("email.subject");
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
                    texto.setText("Texto del mensaje");

                    //Luego construimos la parte del adjunto con la imagen. Suponemos que la tenemos en un fichero
                    BodyPart adjunto = new MimeBodyPart();

                    // Cargamos la imagen
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(filePath)));

                    // Opcional. De esta forma transmitimos al receptor el nombre original del fichero de imagen.
                    //adjunto.setFileName("futbol.gif");
                    //Ahora juntamos ambas en una sola parte que luego debemos añadir al mensaje
                    MimeMultipart multiParte = new MimeMultipart();
                    multiParte.addBodyPart(texto);
                    multiParte.addBodyPart(adjunto);
                    //Finalmente construimos el mensaje, le ponemos este MimeMultiPart como contenido y rellenamos el resto de campos from, to y subject.
                    MimeMessage message = new MimeMessage(session);

                    // Se rellena el From, destinatarios, subject y el contenido (texto y adjunto).
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject(subject);
                    message.setContent(multiParte);

                    //Envio del mensaje, previamente se inserta el user y el pass de la cuenta de correo
                    Transport t = session.getTransport("smtp");
                    t.connect(user, pass);
                    t.sendMessage(message,message.getAllRecipients());
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
        };
        thread.start();
    }
}
