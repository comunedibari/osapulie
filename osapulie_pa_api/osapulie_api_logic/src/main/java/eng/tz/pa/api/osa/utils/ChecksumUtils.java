package eng.tz.pa.api.osa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class ChecksumUtils {

	public static String generateChecksum(InputStream inputStream) throws NoSuchAlgorithmException, IOException {

		return Base64.encodeBase64String(org.apache.commons.codec.digest.DigestUtils.sha256(inputStream));
	}
	
	public static String generateChecksum(byte[] inputByte) throws NoSuchAlgorithmException, IOException {
		return Base64.encodeBase64String(inputByte);
	}
}
