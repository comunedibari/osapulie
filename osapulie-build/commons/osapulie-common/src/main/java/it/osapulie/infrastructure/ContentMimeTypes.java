/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.infrastructure;

import javax.activation.MimetypesFileTypeMap;

/**
 * Content MIME-types that we manage in this application.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public class ContentMimeTypes {

	public static final String PDF = "application/x-pdf";

	private static final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

	public static String getFileType(String fileName) {
		return fileTypeMap.getContentType(fileName);
	}
}
