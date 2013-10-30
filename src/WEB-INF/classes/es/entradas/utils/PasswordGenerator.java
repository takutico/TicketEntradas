/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.utils;

/**
 *
 * @author User
 */
public class PasswordGenerator {

	private static String NUMEROS = "0123456789";

	private static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}

	public static String getPassword() {
		return getPassword(8);
	}

	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	public static String getPassword(String key, int length) {
		String pswd = "";

		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}

		return pswd;
	}

}
