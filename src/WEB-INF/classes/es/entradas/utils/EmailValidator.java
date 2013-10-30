package es.entradas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author takuya
 */
public class EmailValidator implements Validator {

    /** Se verifica que el email introducido tenga el formato "xxx@yyy.zzz"
     *
     * @param fc
     * @param uic
     * @param value
     * @throws ValidatorException
     */
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        String enteredEmail = (String) value;
        //Se estable el patr�n para el email
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        //Se comprueba la cadena seg�n el patr�n
        Matcher m = p.matcher(enteredEmail);

        //Si no encaja con el patron muestra un mensaje de error de validacion
        if (!m.matches()) {
            FacesMessage message = new FacesMessage("Email no v�lido");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
