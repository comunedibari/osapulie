package it.osapulie.web;

/**
 * Web layer standard exception.
 * 
 * @author Mario Scalas
 */
public class WebLayerException extends RuntimeException {
	private static final long serialVersionUID = -4788727207958770063L;

	public WebLayerException( String message, Throwable e ) {
		super( message, e );
	}

	/**
	 * @param message
	 */
	public WebLayerException( String message ) {
		super( message );
	}
}
