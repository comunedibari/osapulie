package it.osapulie.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoUserUtil {

	private String codiceFiscale;
	private String sesso;
	private String dataNascita_dd_MM_yy;
	private Date dataNascita;
	private int eta;
	private String giorno;
	private String mese;
	private String anno;
	private Map<String, String> mesi;
	protected static Logger log = LoggerFactory.getLogger(InfoUserUtil.class);

	public InfoUserUtil(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
		inits();
	}

	// (A = Gennaio, B, C, D, E, H, L, M, P, R, S, T = Dicembre)
	private void inits() {
		mesi = new HashMap<String, String>();
		mesi.put("A", "01");
		mesi.put("B", "02");
		mesi.put("C", "03");
		mesi.put("D", "04");
		mesi.put("E", "05");
		mesi.put("H", "06");
		mesi.put("L", "07");
		mesi.put("M", "08");
		mesi.put("P", "09");
		mesi.put("R", "10");
		mesi.put("S", "11");
		mesi.put("T", "12");

		if (codiceFiscale != null && codiceFiscale.length() > 15) {

			String code = codiceFiscale.substring(6, 11);
			giorno = code.substring(3, 5);
			anno = code.substring(0, 2);
			mese = code.substring(2, 3);
			
			log.info("CF GIORNO: [{}]",giorno);
			log.info("CF MESE: [{}]",mese);
			log.info("CF ANNO: [{}]",anno);
			
			int giorno__=0;
			try {
			giorno__ = Integer.parseInt(giorno);	
			}catch (Exception e) {
				log.error("CF GIORNO NON VALIDO : Exception[{}]",e.getMessage());
			}

			if (giorno__ > 39) {
				sesso = "F";
				giorno__ = (giorno__ - 40);
			} else {
				sesso = "M";
			}

		   if(mesi.get(mese)!=null) {
			mese = mesi.get(mese);
		   }else {
			   log.error("CF MESE NON VALIDO : [{}]",mese);
		   }
			dataNascita_dd_MM_yy = giorno__ < 10 ? ("0" + giorno__) : giorno__ + "-" + mese + "-" + anno;
			log.info("CF DATANASCITA ELABORATA DAL CODFISC. INSERITO: [{}]",dataNascita_dd_MM_yy);
			try {
				dataNascita = new SimpleDateFormat("dd-MM-yy").parse(dataNascita_dd_MM_yy);
				eta = getEta(dataNascita);
			} catch (ParseException e) {
				log.error("CF CREAZIONE DATA FALLITA : ParseException[{}]",e.getMessage());
			}catch (Exception e) {
				log.error("CF CREAZIONE DATA FALLITA : Exception[{}]",e.getMessage()); 
			}

		}else {
			log.info("CODICE FISCALE NON VALIDO");
		}

	}

	private int getEta(Date dataNascita) {
		Calendar a = Calendar.getInstance(Locale.ITALY);
		a.setTime(dataNascita);
		Calendar b = Calendar.getInstance(Locale.ITALY);
		int age = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			age--;
		}
		return age;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getSesso() {
		return sesso;
	}

	public String getDataNascita_dd_MM_yy() {
		return dataNascita_dd_MM_yy;
	}

	public Date getDataNascita() {
		return dataNascita;

	}

	public int getEta() {
		return eta;
	}

}
