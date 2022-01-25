package it.eng.tz.area.vasta.protocollo.exception;

public class ProtocolloDbException extends Exception {

	private static final long serialVersionUID = -8445028957099703191L;

	public ProtocolloDbException() {
		super();
	}

	public ProtocolloDbException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProtocolloDbException(String message) {
		super(message);
	}

	public ProtocolloDbException(Throwable cause) {
		super(cause);
	}
}
