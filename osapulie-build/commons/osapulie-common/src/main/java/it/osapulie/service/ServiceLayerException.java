package it.osapulie.service;

public class ServiceLayerException extends RuntimeException {

	private static final long serialVersionUID = 8373847486417252636L;

	/**
	 *
	 */
	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceLayerException(String message) {
		super(message);
	}

	/**
	 * @param e
	 */
	public ServiceLayerException(Throwable e) {
		super(e);
	}

}
