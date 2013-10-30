/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author takuya
 */
public class Utilidades {

    public static void mostrarError(String asunto, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                asunto, mensaje));
    }

    public static void mostrarInformacion(String asunto, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                asunto, mensaje));
    }

    public static void mostrarAviso(String asunto, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                asunto, mensaje));
    }

    public static void enviarEmail(){
        new Email().send(null, null, null);
    }
}
