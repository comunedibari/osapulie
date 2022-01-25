package it.eng.tz.area.vasta.documentale.cmis.exception;

public class DocumentaleCmisException extends Exception {

	private static final long serialVersionUID = -8445028957099703191L;

	public DocumentaleCmisException() {
		super();
	}

	public DocumentaleCmisException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentaleCmisException(String message) {
		super(message);
	}

	public DocumentaleCmisException(Throwable cause) {
		super(cause);
	}
}
