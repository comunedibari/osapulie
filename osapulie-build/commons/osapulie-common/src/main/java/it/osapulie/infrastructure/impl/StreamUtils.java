/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.infrastructure.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Mario Scalas
 *
 */
public class StreamUtils {
	/**
	 * @param contentBytes
	 * @param fileName
	 * @throws Throwable 
	 */
	public static void dumpToFile( byte[] contentBytes, String fileName ) throws Throwable {
		if (contentBytes == null || contentBytes.length == 0) {
			return;
		}
		
		ByteArrayInputStream in = new ByteArrayInputStream( contentBytes );
		FileOutputStream out = new FileOutputStream( fileName );
		try {
			byte[] buf = new byte[1 * 1024];
			int len = 0;
			while ((len = in.read( buf )) >= 0) {
				out.write( buf, 0, len );
			}
		} finally {
			closeStream( in );
			closeStream( out );
		}
	}

	public static void closeStream( Closeable is ) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) { /* Close */ }
		}
	}
	
	/**
     * Legge il contenuto di un file
     * @param pathFile
     * @return	string
     */
	public static String leggiDaFile( String pathFile ) {
		ByteArrayOutputStream outStreamArray = new ByteArrayOutputStream();
		FileInputStream inStream = null;
		try {
			for (inStream = new FileInputStream( pathFile ); inStream.available() > 0; outStreamArray.write( inStream.read() ))
				;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeStream( inStream );
		}
		return outStreamArray.toString();
	}
}
