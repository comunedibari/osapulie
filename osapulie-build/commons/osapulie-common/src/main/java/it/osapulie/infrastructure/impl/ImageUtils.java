package it.osapulie.infrastructure.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static it.osapulie.infrastructure.impl.StreamUtils.*;

/**
 * Utility methods for manipulating {@link Image} and {@link BufferedImage} types.
 * 
 * @author Mario Scalas
 */
public class ImageUtils {
	
	private static Logger log = LoggerFactory.getLogger( ImageUtils.class );

	/**
	 * Restituite i bytes dell'immagine in input come {@link InputStream} (es., quando letta dal classpath).
	 * 
	 * @param imageStream immagine in input
	 * @return i bytes dell'immagine o <code>null</code> se lo stream è null o c'è stato un errore durante la lettura
	 */
	public static byte[] getImageBytes( InputStream imageStream ) {
		BufferedImage image = null;
		byte[] bytes = null;
		if (imageStream != null) {
			try {
				image = ImageIO.read( imageStream );
				bytes = ImageUtils.getImageBytes( image );
			} catch (IOException e) {
				// It's ok to return null in the contract of this method
			} finally {
				closeStream( imageStream );
			}
		}
		return bytes;
	}
	
	public static byte[] getImageBytes( BufferedImage image ) {
		if (image == null) {
			return null;
		}
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image, "jpg", baos );
			return baos.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}

	public static BufferedImage loadImage( String fileName ) throws IOException {
		File inputFile = new File( fileName );
		if (!inputFile.exists()) {
			log.warn( String.format( "Input file %s does not exist!", fileName ) );
			return null;
		}
		BufferedImage image = ImageIO.read( inputFile );
		return image;
	}

	/**
	 * Crea una nuova {@link BufferedImage} a partire dai bytes indicati.
	 * @param bytes
	 * @return una nuova {@link BufferedImage} o <code>null</code> se l'input è null
	 */
	public static Image loadImage( byte[] bytes ) {
		if (bytes == null) {
			return null;
		}
		ByteArrayInputStream bis = new ByteArrayInputStream( bytes );
		BufferedImage image = null;
		try {
			image = ImageIO.read( bis );
		} catch (IOException e) {
			log.error( e.getMessage(), e );
		}
		return image;
	}

	public static byte[] readImageResource( InputStream is ) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}

			buffer.flush();
			return buffer.toByteArray();
		} catch (Throwable e) {
			log.error( e.getMessage(), e );
		} finally {
			closeStream( buffer );
			closeStream( is );
		}
		return null;
	}	
}
