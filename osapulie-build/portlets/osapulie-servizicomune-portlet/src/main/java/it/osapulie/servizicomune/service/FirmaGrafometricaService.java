package it.osapulie.servizicomune.service;

import it.osapulie.servizicomune.model.DelegaReportModel;
import it.osapulie.util.FirmaGrafometricaResponse;

public interface FirmaGrafometricaService {


	FirmaGrafometricaResponse invokeFirmaService(DelegaReportModel reportModel, byte[] file) throws Exception;

	byte[] waitAndSigned(DelegaReportModel delegaReport, String token)throws Exception;


}
