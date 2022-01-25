package it.eng.tz.area.vasta.documentale.cmis.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConstraintException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.eng.tz.area.vasta.documentale.cmis.aspect.*;
import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;

@Component
public class CMISRegistroProtocollo implements Serializable {

	public static final String REGISTRO_PROTOCOLLO_TYPE = "cmis:document";
	public static final String REGISTRO_PROTOCOLLO = "Registro Protocollo";
	public static final String REGISTRO_PROTOCOLLO_OGGETTO_PROCEDIMENTO = "Conservazione registro di protocollo ai fini del DPCM 3 dicembre 2013";
	public static final String REGISTRO_PROTOCOLLO_TYPE_COMPLETE = REGISTRO_PROTOCOLLO_TYPE+", P:cons:conservazione, P:regProt:registroProtocollo";
	 
	private static final Logger logger = LoggerFactory.getLogger(CMISAbstractConnect.class.getName());

	
	protected Document createDocumentRegistroProtocollo(Folder folder, BORegistroProtocollo regProtocollo,
			Session session) {
		Document document=null;
		boolean activeVersioning = true;
		VersioningState vState;

		if (activeVersioning == true) {
			vState = VersioningState.MINOR;
		} else {
			vState = VersioningState.NONE;
		}

		try {
			File file = new File(regProtocollo.getPathFileSystem());
			if (file != null) {
				String fileName = regProtocollo.getFileName();
				Map<String, Object> properties = new HashMap<String, Object>();
			 
				properties.put(PropertyIds.OBJECT_TYPE_ID, REGISTRO_PROTOCOLLO_TYPE_COMPLETE);
				properties.put(PropertyIds.NAME, fileName);

				associaPropertyAspect(regProtocollo, properties);

				String mimetype = "application/pdf";
				InputStream fis = new FileInputStream(file);
				byte[] bytes = IOUtils.toByteArray(fis);
				InputStream input = new ByteArrayInputStream(bytes);

				logger.debug("Creating the Content Stream...");

				ContentStream contentStream = session.getObjectFactory().createContentStream(fileName, bytes.length,
						mimetype, input);

				// Creo il documento
				logger.debug("Creating the document with content...");
				 document = folder.createDocument(properties, contentStream, vState);
				 
			}

		} catch (FileNotFoundException e) {

				logger.error("METODO createDocumentRegistroProtocollo. ERRORE", e);


		} catch (IOException e) {

		
				logger.error("METODO createDocumentRegistroProtocollo. ERRORE", e);
	

		} catch (CmisConstraintException e) {

				logger.error("METODO createDocumentRegistroProtocollo. ERRORE", e);
			 
			throw e;
		} catch (Exception e) {

				logger.error("METODO createDocumentRegistroProtocollo. ERRORE", e);
		 

		}

		return document;
	}

	private void associaPropertyAspect(BORegistroProtocollo regProtocollo, Map<String, Object> properties) {

		regProtocollo.setClasseDocumentale("Registro Giornaliero di Protocollo");
		regProtocollo.setStatoVersamento("DA_VERSARE");
		regProtocollo.setVersamento(false);

		// Conservazione
		properties.put(PropertyConservazioneAspectIds.CLASSE_DOCUMENTALE, regProtocollo.getClasseDocumentale());
		properties.put(PropertyConservazioneAspectIds.STATO_VERSAMENTO, regProtocollo.getStatoVersamento());
		properties.put(PropertyConservazioneAspectIds.VERSATO, regProtocollo.getVersamento());
		// properties.put(RepositoryRegistroProtocolloDAO.REGISTRO_PROTOCOLLO_PDV_ID,
		// regProtocollo.getPdvId());
		// properties.put(RepositoryRegistroProtocolloDAO.REGISTRO_PROTOCOLLO_DOC_ID,
		// regProtocollo.getDocId());
		// properties.put(RepositoryRegistroProtocolloDAO.REGISTRO_PROTOCOLLO_DOC_FILE_NAME,
		// regProtocollo.getDocFilename());
		// properties.put(RepositoryRegistroProtocolloDAO.REGISTRO_PROTOCOLLO_DATA_VERSAMENTO,
		// regProtocollo.getDataVersamento());

		// Documento Amministrativo
		// Aoo aoo = aooManager.getCurrentAoo();
		// UnitaOrganizzativa uo = unitaOrganizzativaManager.getUoVirtualeProtocollo();
		// Ente ente = enteManager.getCurrentEnte();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// ID DOCUMENTO???
		// TODO ????
		// Impronta
		properties.put(PropertyRegistroDiProtocolloAspectIds.HASH, regProtocollo.getChecksum());
		// DATA CHIUSURA
		properties.put(PropertyRegistroDiProtocolloAspectIds.CLOSING_DATE,
				format.format(regProtocollo.getDataCreazione()));
		// OGGETTO
		properties.put(PropertyRegistroDiProtocolloAspectIds.OGGETTO_DOCUMENTO, regProtocollo.getOggetto());
		// DENOMINAZIONE E PIVA DEST
		properties.put(PropertyRegistroDiProtocolloAspectIds.DESTINATARIO_DENOMINAZIONE,
				regProtocollo.getDenominazioneDestinatario());
		properties.put(PropertyRegistroDiProtocolloAspectIds.DESTINATARIO_PARTITAIVA,
				regProtocollo.getPivaDestinatario());
		// DENOMINAZIONE SOGG PRODUTTORE
		properties.put(PropertyRegistroDiProtocolloAspectIds.SOGGETTO_PRODUTTORE_DENOMINAZIONE,
				regProtocollo.getDenominazioneSoggettoProduttore());
		// SOGGETTO PRODUTTORE 2
		properties.put(PropertyRegistroDiProtocolloAspectIds.COGNOME_SOGGETTO_PRODUTTORE2,
				regProtocollo.getCognomeSoggettoProduttore2());
		properties.put(PropertyRegistroDiProtocolloAspectIds.NOME_SOGGETTO_PRODUTTORE2,
				regProtocollo.getNomeSoggettoProduttore2());
		properties.put(PropertyRegistroDiProtocolloAspectIds.CODICE_FISCALE_SOGGETTO_PRODUTTORE2,
				regProtocollo.getCfSoggettoProduttore2());
		// RESPONSABILE DOCUMENTALE
		properties.put(PropertyRegistroDiProtocolloAspectIds.COGNOME_RESPONSABILE_GESTIONE_DOCUMENTALE,
				regProtocollo.getCognomeResponsabile());
		properties.put(PropertyRegistroDiProtocolloAspectIds.NOME_RESPONSABILE_GESTIONE_DOCUMENTALE,
				regProtocollo.getNomeResponsabile());
		properties.put(PropertyRegistroDiProtocolloAspectIds.CF_RESPONSABILE_GESTIONE_DOCUMENTALE,
				regProtocollo.getCfResponsabile());
		// IDENTIFICATIVO REGISTRO
		properties.put(PropertyRegistroDiProtocolloAspectIds.CODICE_IDENTIFICATIVO_DEL_REGISTRO,
				regProtocollo.getCodiceRegistro());
		// PROGRESSIVO REGISTRO
		properties.put(PropertyRegistroDiProtocolloAspectIds.NUMERO_PROGRESSIVO_DEL_REGISTRO,
				regProtocollo.getProgressivo());
		// ANNO
		properties.put(PropertyRegistroDiProtocolloAspectIds.ANNO, regProtocollo.getAnno());
		// DATA PRIMA ED ULTIMA REGISTRAZIONE
		properties.put(PropertyRegistroDiProtocolloAspectIds.DATA_PRIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
				format.format(regProtocollo.getDataPrimaReg()));
		properties.put(PropertyRegistroDiProtocolloAspectIds.DATA_ULTIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
				format.format(regProtocollo.getDataUltimaReg()));
		// NUMERO PRIMA ED ULTIMA REGISTRAZIONE
		if (regProtocollo.getNumeroPrimaReg() == 0 && regProtocollo.getNumeroUltimaReg() == 0) {
			properties.put(PropertyRegistroDiProtocolloAspectIds.NUMERO_PRIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
					"-");
			properties.put(PropertyRegistroDiProtocolloAspectIds.NUMERO_ULTIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
					"-");
		} else {
			properties.put(PropertyRegistroDiProtocolloAspectIds.NUMERO_PRIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
					regProtocollo.getNumeroPrimaReg());
			properties.put(PropertyRegistroDiProtocolloAspectIds.NUMERO_ULTIMA_REGISTRAZIONE_EFFETTUATA_SUL_REGISTRO,
					regProtocollo.getNumeroUltimaReg());
		}

		// OGGETTO DEL PROCEDIMENTO
		properties.put(PropertyRegistroDiProtocolloAspectIds.OGGETTO_PROCEDIMENTO, regProtocollo.getOggetto());
		// AOO RIFERIMENTO
		properties.put(PropertyRegistroDiProtocolloAspectIds.AOO_DI_RIFERIMENTO, regProtocollo.getCodiceAoo());
		// CODICE IPA
		properties.put(PropertyRegistroDiProtocolloAspectIds.CODICE_IDENTIFICATIVO_AMMINISTRAZIONE_IPA,
				regProtocollo.getCodiceAmm());
		// DENOMINAZIONE amm
		properties.put(PropertyRegistroDiProtocolloAspectIds.DENOMINAZIONE_DELL_AMMINISTRAZIONE,
				regProtocollo.getDenominazioneEnte());

	}

}
