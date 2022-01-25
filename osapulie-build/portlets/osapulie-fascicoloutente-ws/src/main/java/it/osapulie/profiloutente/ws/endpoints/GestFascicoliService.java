package it.osapulie.profiloutente.ws.endpoints;


import it.osapulie.profiloutente.ws.dto.FascicoloInsertDto;
import it.osapulie.profiloutente.ws.dto.FascicoloUtenteResponse;
import it.osapulie.profiloutente.ws.dto.RichiestaFascicoloDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface GestFascicoliService extends java.rmi.Remote {
	@WebMethod(action="return", operationName="ottieniFascicoloUtente")
	public FascicoloUtenteResponse ottieniFascicoloUtente(@WebParam(name = "richiestaFascicolo") RichiestaFascicoloDto richiestaFascicolo);
	
	@WebMethod(action="return", operationName="inserisciFascicolo")
	public FascicoloUtenteResponse inserisciFascicolo(@WebParam(name = "inserisciFascicolo") FascicoloInsertDto fascicoloDto);
}