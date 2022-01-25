/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test;

import it.osapulie.domain.ComuneISA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test helper methods.
 * 
 * @author Mario Scalas
 */
public class TestHelpers {
	/**
	 * @return
	 * @throws Exception 
	 */
	public static List<ComuneISA> createMockComuni( int nrComuni ) throws Exception {
		List<ComuneISA> objects = new ArrayList<ComuneISA>();

		for (int i = 0; i < nrComuni; i++) {
			ComuneISA object = createMockComune( 1 );
			objects.add( object );
		}

		return objects;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static ComuneISA createMockComune( int n ) throws IOException {
		ComuneISA object = new ComuneISA();
//		BufferedImage image = ImageIO.read( TestHelpers.class.getResourceAsStream( "/images/test_image.png" ) );
		object.setNome( "Città #" + n );
		object.setDescrizione( "ComuneISA di " + object.getNome() );
		object.setCap( String.format( "%06d", n ) );
//		object.setLogo( ImageUtils.getImageBytes( image ) );
		return object;
	}
}
