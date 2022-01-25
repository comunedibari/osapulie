package it.eng.tz.area.vasta.protocollo.spring.configuration.util;

import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;

public class ConvertUtil {

	public static BORegistroProtocollo convert(RegistroProtocolloModel m) {
		BORegistroProtocollo rpt= new BORegistroProtocollo();	
		rpt.setIdRegistroProtocollo(m.getIdRegistroProtocollo());
		rpt.setRepositoryPointer(m.getRepositoryPointer());
		rpt.setDataCreazione(m.getDataCreazione());
		rpt.setFlagGenerato(m.getFlagGenerato());
		rpt.setFileName(m.getFileName());
		rpt.setPathFileSystem(m.getPathFileSystem());
		rpt.setDenominazioneDestinatario(m.getDenominazioneDestinatario());
		rpt.setPivaDestinatario(m.getPivaDestinatario());
		rpt.setChecksum(m.getChecksum());
		rpt.setCodiceAmm(m.getCodiceAmm());
		rpt.setCodiceAoo(m.getCodiceAoo());
		rpt.setDenominazioneSoggettoProduttore(m.getDenominazioneSoggettoProduttore());
		rpt.setNomeSoggettoProduttore2(m.getNomeSoggettoProduttore2());
		rpt.setCognomeSoggettoProduttore2(m.getCognomeSoggettoProduttore2());
		rpt.setCfSoggettoProduttore2(m.getCfSoggettoProduttore2());
		rpt.setNomeResponsabile(m.getNomeResponsabile());
		rpt.setCognomeResponsabile(m.getCognomeResponsabile());
		rpt.setCfResponsabile(m.getCfResponsabile());
		rpt.setOggetto(m.getOggetto());
		rpt.setCodiceRegistro(m.getCodiceRegistro());
		rpt.setProgressivo(m.getProgressivo());
		rpt.setAnno(m.getAnno());
		rpt.setNumeroPrimaReg(m.getNumeroPrimaReg());
		rpt.setNumeroUltimaReg(m.getNumeroUltimaReg());
		rpt.setDataPrimaReg(m.getDataPrimaReg());
		rpt.setDataUltimaReg(m.getDataUltimaReg());
		rpt.setClasseDocumentale(m.getClasseDocumentale());
		rpt.setStatoVersamento(m.getStatoVersamento());
		rpt.setVersamento(m.getVersamento());
		rpt.setPdvId(m.getPdvId());
		rpt.setDocId(m.getDocId());
		rpt.setDocFilename(m.getDocFilename());
		rpt.setDataVersamento(m.getDataVersamento());
		rpt.setDenominazioneEnte(m.getDenominazioneEnte());
		return rpt;
	}
	
}
