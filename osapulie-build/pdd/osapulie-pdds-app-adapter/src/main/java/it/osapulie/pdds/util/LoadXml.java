/*
 * Created on 3-mag-2005
 */
package it.osapulie.pdds.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * La classe effettua la lettura dell'xml settando i parametri utili per le query
 *
 * @author Michele
 *
 */
public class LoadXml {

	private String codiceFiscale;
	private String codiceServizio;
	private String tributo;
	private String dal;
	private String al;
	private String datapartenza = "1980";
	private String datafine = "2100";
	private String partitaiva;
	private String erroreParser2;
	private String root = " ";
	private ArrayList tipotrib = new ArrayList();
	private String annoIn = "1980";
	private String annoFi = "2100";

	protected static Logger log = LoggerFactory.getLogger(LoadXml.class.getName());

	public void load(InputSource in) throws Exception {

		Document document = null;

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			DocumentBuilder parser = dbf.newDocumentBuilder();

			document = parser.parse(in);
			setRoot(document.getFirstChild().getNodeName().split("#")[0]);

			leggi(document, 0);

		}
		catch (Exception e) {
			erroreParser2 = e.toString();
			throw new Exception(e);
		}
	}

	private void leggi(Node node, int numLevelDeep) {

		int type = node.getNodeType();

		if (type == Node.ELEMENT_NODE) {

			if ("visuraDichiarazioneIciRichiesta".equals(node.getNodeName()) || "pagamentiTarsuRichiesta".equals(node.getNodeName())) {
				try {
					setDal(node.getAttributes().getNamedItem("annoInizio").getNodeValue().toString());
					setAl(node.getAttributes().getNamedItem("annoFine").getNodeValue().toString());
				}
				catch (Exception e) {

					// TODO: handle exception
				}

			}
			;
			if ("codiceFiscale".equals(node.getNodeName())) {
				setCodiceFiscale(node.getFirstChild().getNodeValue().trim());
			}

			if ("tipoTributo".equals(node.getNodeName())) {
				setTributo(node.getFirstChild().getNodeValue());
			}

			if ("codiceServizio".equals(node.getNodeName())) {
				setCodiceServizio(node.getFirstChild().getNodeValue());
			}

			if ("dataPartenza".equals(node.getNodeName())) {
				setDatapartenza(node.getFirstChild().getNodeValue());
			}

			if ("dataFine".equals(node.getNodeName())) {
				setDatafine(node.getFirstChild().getNodeValue());
			}

			if ("partitaIva".equals(node.getNodeName())) {
				setPartitaiva(node.getFirstChild().getNodeValue().trim());
			}

			if ("ALL".equals(node.getNodeName())) {
				tipotrib.add("all");
			}

			if ("OSAP".equals(node.getNodeName())) {
				tipotrib.add("osap");
			}

			if ("ICI".equals(node.getNodeName())) {
				tipotrib.add("ici");
			}

			if ("TARSU".equals(node.getNodeName())) {
				tipotrib.add("tarsu");
			}

			if ("ICP".equals(node.getNodeName())) {
				tipotrib.add("icp");
			}

			if ("AFFISSIONI".equals(node.getNodeName())) {
				tipotrib.add("affissione");
			}

			if ("anno".equals(node.getNodeName())) {
				setAnnoIn(node.getFirstChild().getNodeValue());
			}

			if ("annoInizio".equals(node.getNodeName())) {
				setAnnoIn(node.getFirstChild().getNodeValue());
			}

			if ("annoFine".equals(node.getNodeName())) {
				setAnnoFi(node.getFirstChild().getNodeValue());
			}
		}
		NodeList children = node.getChildNodes();
		if (children != null) {
			for (int i = 0; i < children.getLength(); i++) {
				leggi(children.item(i), numLevelDeep + 1);
			}
		}
	}

	/**
	 * Metodo GET restituisce parametro al.
	 */
	public String getAl() {
		if (al == null) {
			al = "";
			return al;
		}
		else {
			return al;
		}
	}

	public static String getXmlContent(String name) {

		String thisLine;
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(LoadXml.class.getClassLoader().getResourceAsStream("xmlpratiche/" + name + ".xml")));
		try {
			while ((thisLine = bufferedReader.readLine()) != null) { // while loop begins here
				buffer.append(thisLine);
			}
		}
		catch (IOException e) {
			log.error("getXmlContent :: " + e.getMessage(), e);
		}

		return buffer.toString();
	}

	/**
	 * Metodo SET per settare parametro al.
	 */
	public void setAl(String al) {
		this.al = al;
	}

	/**
	 * Metodo GET restituisce parametro codiceFiscale.
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * Metodo SET per settare parametro codiceFiscale.
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * Metodo GET restituisce parametro codiceServizio.
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * Metodo SET per settare parametro codiceServizio.
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	/**
	 * Metodo GET restituisce parametro dal.
	 */
	public String getDal() {
		if (dal == null) {
			dal = "";
			return dal;
		}
		else {
			return dal;
		}
	}

	/**
	 * Metodo SET per settare parametro dal.
	 */
	public void setDal(String dal) {
		this.dal = dal;
	}

	/**
	 * Metodo GET restituisce parametro erroreParser2.
	 */
	public String getErroreParser2() {
		return erroreParser2;
	}

	/**
	 * Metodo SET per settare parametro erroreParser2.
	 */
	public void setErroreParser2(String erroreParser2) {
		this.erroreParser2 = erroreParser2;
	}

	/**
	 * Metodo GET restituisce parametro root.
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * Metodo SET per settare parametro root.
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @return Returns the datafine.
	 */
	public String getDatafine() {
		return datafine;
	}

	/**
	 * @param datafine The datafine to set.
	 */
	public void setDatafine(String datafine) {

		this.datafine = datafine;
	}

	/**
	 * @return Returns the datapartenza.
	 */
	public String getDatapartenza() {
		return datapartenza;
	}

	/**
	 * @param datapartenza The datapartenza to set.
	 */
	public void setDatapartenza(String datapartenza) {
		this.datapartenza = datapartenza;
	}

	/**
	 * @return Returns the partitaiva.
	 */
	public String getPartitaiva() {
		return partitaiva;
	}

	/**
	 * @param partitaiva The partitaiva to set.
	 */
	public void setPartitaiva(String partitaiva) {
		this.partitaiva = partitaiva;
	}

	public String getTributo() {
		return tributo;
	}

	public void setTributo(String tributo) {
		this.tributo = tributo;
	}

	public String getAnnoFi() {
		return annoFi;
	}

	public void setAnnoFi(String annoFi) {
		this.annoFi = annoFi;
	}

	public String getAnnoIn() {
		return annoIn;
	}

	public void setAnnoIn(String annoIn) {
		this.annoIn = annoIn;
	}

	public ArrayList getTipotrib() {
		return tipotrib;
	}

	public void setTipotrib(ArrayList tipotrib) {
		this.tipotrib = tipotrib;
	}
}
