package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

public class Valore {

	private String chiave;
	private String valore;

	/**
	 *
	 */
	public Valore() {
	}

	/**
	 *
	 */
	public Valore(String chiave, String valore) {
		this.chiave = chiave;
		this.valore = valore;
	}

	/**
	 * @return the chiave
	 */
	public String getChiave() {
		return chiave;
	}

	/**
	 * @param chiave the chiave to set
	 */
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}

}
