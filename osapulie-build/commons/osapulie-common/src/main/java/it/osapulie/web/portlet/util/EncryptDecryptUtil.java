package it.osapulie.web.portlet.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*****
 * Classe per Criptare e Decriptare in AES 128 bit
 * 
 * @author Giovanni Barone
 *
 */
public class EncryptDecryptUtil {
	
	public static final String PASSKEY_CONST = "zo9l0$t1£hk5w3u";
	
	public static String encrypt(String textToEncrypt, String passkey) throws NoSuchAlgorithmException,	NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(passkey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return asHex(cipher.doFinal(textToEncrypt.getBytes()));
	}
	
	public static String decrypt(String textToDecrypt, String passkey) throws NoSuchAlgorithmException,	NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(passkey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(asBytes(textToDecrypt)));
	}
	
    /**
    * Turns array of bytes into string
    *
    * @param buf	Array of bytes to convert to hex string
    * @return	Generated hex string
    */
    private static String asHex (byte buf[]) {
     StringBuffer strbuf = new StringBuffer(buf.length * 2);
     for (int i = 0; i < buf.length; i++) {
      if (((int) buf[i] & 0xff) < 0x10)
	    strbuf.append("0");
      strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
     }
     return strbuf.toString();
    }
    
    /**
     * Turns String into array of bytes
     * @param s
     * @return
     */
    private static byte[] asBytes (String s) {
        String s2;
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < s.length() / 2; i++) {
            s2 = s.substring(i * 2, i * 2 + 2);
            b[i] = (byte)(Integer.parseInt(s2, 16) & 0xff);
        }
        return b;
    }


}
