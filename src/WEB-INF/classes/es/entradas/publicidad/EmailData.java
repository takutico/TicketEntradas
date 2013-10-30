/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.publicidad;

import org.apache.myfaces.custom.fileupload.UploadedFile;



/**
 *
 * @author takuya
 */
public class EmailData {

    private String to = "";
    private String subject = "";
    private UploadedFile adjunto;
    private String content = "";

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UploadedFile getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(UploadedFile adjunto) {
        this.adjunto = adjunto;
    }

}
