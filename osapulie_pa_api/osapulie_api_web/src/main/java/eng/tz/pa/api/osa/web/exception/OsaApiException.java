package eng.tz.pa.api.osa.web.exception;

public class OsaApiException extends Exception {

	private static final long serialVersionUID = -8445028957099703191L;

	public OsaApiException() {
		super();
	}

	public OsaApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public OsaApiException(String message) {
		super(message);
	}

	public OsaApiException(Throwable cause) {
		super(cause);
	}
}
