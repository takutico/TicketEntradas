/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.entradas.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author takuya
 */
public class NIFValidator implements Validator {

    /** Verifica que el NIF tenga un form�to v�lido. Para calcular la letra del DNI (c�digo de control)
     * se divide el n�mero completro del DNI entre 23 y el resto de dicha division, que corresponder�
     * a un n�mero comprendido entre 1 y 22 se le asignar� un n�mero seg�n la cadena "TRWAGMYFPDXBNJZSQVHLCKET".
     *
     * Para el c�lculo de la letra del NIF se realiza lo mismo pero previamente se sustituye ela primera letra
     * siguiendo el criterio "X" = 0, "Y" = 1 y "Z" = 2.
     *
     *
     * @param fc
     * @param uic
     * @param value
     * @throws ValidatorException
     */
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {

        if (value != null) {
            String nif = value.toString();
            int numero = 0;
            String digitoControl = "";
            char digitoControlCalculado;
            String letras = "TRWAGMYFPDXBNJZSQVHLCKET";

            // Si la longitud del NIF es distinto de 9, est� mal
            if (nif.length() != 9) {
                FacesMessage message = new FacesMessage("Longitud erronea: DNI(8 dig. + 1 letra)/NIE(1 letra + 7 dig. + 1 letra)");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            } else {

                if (nif.toUpperCase().startsWith("X")) {
                    nif.toUpperCase().replaceFirst("X", "0");
                } else if (nif.toUpperCase().startsWith("Y")) {
                    nif.toUpperCase().replaceFirst("Y", "1");
                } else if (nif.toUpperCase().startsWith("Z")) {
                    nif.toUpperCase().replaceFirst("Z", "2");
                }

                numero = Integer.parseInt(nif.substring(0, nif.length() - 1));
                numero = numero % 23;
                digitoControl = nif.substring(nif.length() - 1, nif.length()).toUpperCase();
                digitoControlCalculado = letras.charAt(numero);

                if (!String.valueOf(digitoControlCalculado).equalsIgnoreCase(digitoControl)) {
                    FacesMessage message = new FacesMessage("Letra erronea");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                } 
            }
        }
    }
}
