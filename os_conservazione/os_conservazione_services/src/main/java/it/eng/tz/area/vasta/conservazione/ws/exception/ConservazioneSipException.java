package it.eng.tz.area.vasta.conservazione.ws.exception;

public class ConservazioneSipException extends Exception {

	private static final long serialVersionUID = 5602387743756314896L;

	public ConservazioneSipException() {
		super();
	}

	public ConservazioneSipException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConservazioneSipException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConservazioneSipException(String message) {
		super(message);
	}

	public ConservazioneSipException(Throwable cause) {
		super(cause);
	}

}
