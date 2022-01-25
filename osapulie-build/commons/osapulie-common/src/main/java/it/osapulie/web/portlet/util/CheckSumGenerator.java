/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe per la generazione del MD5 del contenuto di un file
 * 
 * @author Maria Michela Birtolo
 * 
 */
public class CheckSumGenerator {

	
	public static String generateCheckSum(byte[] buffer) {
		
		//File f = new File("c:\\myfile.txt");
		//InputStream is = new FileInputStream(f);				
		//byte[] buffer = new byte[8192];
		//int read = 0;
		try {
			// Get an MD5 implementation of MessageDigest
			MessageDigest digest = MessageDigest.getInstance("MD5");
			/*while( (read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}*/
			// pass data read from file to digest for processing
			digest.update( buffer );
			// Get the MD5 sum
			byte[] md5sum = digest.digest();
			// (Optionally) convert the MD5 byte array to a hex string
			BigInteger bigInt = new BigInteger(1, md5sum);
			String output = bigInt.toString(16);
			System.out.println("MD5: " + output);
			return output;
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("Algoritmo MD5 non trovato", e);
		}		
	}
}
