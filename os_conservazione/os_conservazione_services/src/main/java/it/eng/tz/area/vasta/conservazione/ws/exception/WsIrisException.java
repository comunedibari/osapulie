package it.eng.tz.area.vasta.conservazione.ws.exception;

public class WsIrisException extends Exception {

	private static final long serialVersionUID = 4225516850673872376L;

	public WsIrisException() {
		super();
	}

	public WsIrisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WsIrisException(String message, Throwable cause) {
		super(message, cause);
	}

	public WsIrisException(String message) {
		super(message);
	}

	public WsIrisException(Throwable cause) {
		super(cause);
	}

}
