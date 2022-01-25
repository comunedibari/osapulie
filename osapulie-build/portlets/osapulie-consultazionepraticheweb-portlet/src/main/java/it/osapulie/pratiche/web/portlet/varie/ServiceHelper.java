package it.osapulie.pratiche.web.portlet.varie;

import flexjson.JSONSerializer;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.pratiche.web.ws.types.CategoriePraticheDto;
import it.osapulie.pratiche.web.ws.types.ProfiloUtenteDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.BeanUtils;


public class ServiceHelper {

	/* MASCHERE PER LA CONFIGURAZIONE DELLA PORTLET */
	//public static final byte SHOW_TAB_MASK = 1; // 00000001

	/* MASCHERE DI BIT PER LA CONFIGURAZIONE DEI CAMPI AGGIUNTIVI DEL TIPO PRATICA */
	public static final byte BACK_END_MAIN_PAG_MASK = 1; 		// 00000001
	public static final byte FRONT_END_MAIN_PAG_MASK = 2;		// 00000010
	
	public static final byte BACK_FRONT_END_MAIN_PAG_MASK = BACK_END_MAIN_PAG_MASK | FRONT_END_MAIN_PAG_MASK;	// 00000011
	
	/* 
	1 - BackEnd + MainPag
	2 - FrontEnd + MainPag
	3 - BackFrontEnd + MainPag
	5 - BackEnd + Tab
	7 - BackFrontEnd + Tab
	*/
	
	/* MASCHERE DI BIT USATE PER LA CONFIGURAZIONE DELLA TIPOLOGIA DI PRATICA */
	public static final byte SHOW_MITT_DEST = 1; 	// 0000 0001
	public static final byte SHOW_STATO = 2;		// 0000 0010
	public static final byte SHOW_TIP_INT = 4;	// 0000 0100
	public static final byte SHOW_LOCALITA = 8;		// 0000 1000
	
	/**
	 * Verifico la configurazione caricata dal DB
	 * 
	 * @param mask
	 * @param maskToCheck
	 * @return true se la maschera &egrave; verificata
	 */
	public static boolean checkBitMask(byte mask, byte maskToCheck) {
		return ((mask & maskToCheck) == maskToCheck);
	}
	
	/**
	 * Setta la maschera di bit per la configurazione del tipo pratica / campo aggiuntivo
	 * 
	 * @param bitToSet
	 * @return la maschera di bit per la configurazione
	 */
	public static byte setBitMask(byte[] bitToSet) {
		byte result = 0;
		for (int i = 0; i < bitToSet.length; i++) {
			result |= bitToSet[i];
		}
		return result;
	}
	
	

	public static String getEncoding(File file) {
		String encoding = System.getProperty("file.encoding");  
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			char buffer[] = new char[3];
			int length = bufferedReader.read(buffer);
			if ((length >= 2) && ((buffer[0] == (char) 0xff && buffer[1] == (char) 0xfe) || 
					(buffer[0] == (char) 0xfe && buffer[1] == (char) 0xff)))
				encoding = "UTF16";
			if ((length >= 3) && (buffer[0] == (char) 0xef && buffer[1] == (char) 0xbb &&
					buffer[2] == (char) 0xbf))
				encoding = "UTF8";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
		}
		return encoding;
	}
	
	public static ProfiloUtenteDto convertToProfiloUtente(ProfiloUtenteCittadino profilo){
		
		ProfiloUtenteDto prof = new ProfiloUtenteDto();
		BeanUtils.copyProperties( profilo, prof );
		
		return prof;
		
	}
	
	public static String listToJson(List<CategoriePraticheDto> lista)
			throws IOException {
		JSONSerializer serializer = new JSONSerializer();
		StringBuilder builder = new StringBuilder();
		String result;

		serializer.exclude("*.class").exclude("*.parent").exclude("*.new")
					.include("*.tipoPratica.id")
					.include("*.tipoPratica.descrizione").exclude("*.id")
					.exclude("*.tipoPratica.*").exclude("*.ruolo").deepSerialize(lista, builder);
		
		result = builder.toString();
		result = result.replaceAll("\"children\":\\[\\],", "");
		result = result
				.replaceAll("\"descr\"", "\"isFolder\": true, \"title\"");
		result = result.replaceAll("descrizione", "title");
		result = result.replaceAll(",\"children\":\\[\\]", "");

		System.out.println(result);
		
		return result;
	}
	
	
}
