package eng.tz.pa.api.osa.exception;

public class OsaApiDbException extends Exception {

	private static final long serialVersionUID = -8445028957099703191L;

	public OsaApiDbException() {
		super();
	}

	public OsaApiDbException(String message, Throwable cause) {
		super(message, cause);
	}

	public OsaApiDbException(String message) {
		super(message);
	}

	public OsaApiDbException(Throwable cause) {
		super(cause);
	}
}
