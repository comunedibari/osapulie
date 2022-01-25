package it.osapulie.pratiche.web.portlet.varie;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pratiche.web.ws.types.CampiAggiuntiviPraticaWebDto;


/**
 * @author Maria Michela Birtolo
 * 
 */
public class ControlliCampiAggiuntivi{

	private static Logger log = LoggerFactory.getLogger( ControlliCampiAggiuntivi.class );
	
	
	public static boolean campoObbligatorio(CampiAggiuntiviPraticaWebDto campo){
		boolean ret = true;
		
		String strAllegato = "null";
		
		if (campo.getCampiAggiuntivi().getTipologia().equals("i")){
			if (campo.getAllegatoContent() == null)
				ret = false;
			else{
				strAllegato = String.format("Name :>>%s<< | size : %d", 
						campo.getAllegatoNome(),
						campo.getAllegatoContent().length);
				if (campo.getAllegatoContent().length == 0)
					ret = false;
			}
		}
		else{
			if(campo.getValore() == null || campo.getValore().equals("") || campo.getValore().replaceAll(",", "").equals(""))
				ret = false;
		}
		log.debug(String.format("Validazione del campo : Tipologia = %s; Label = %s; Valore = %s; Allegato = %s",
				campo.getCampiAggiuntivi().getTipologia(),
				campo.getCampiAggiuntivi().getLabel(),
				campo.getValore(),
				strAllegato));
		
		return ret;
	}
	public static boolean campoObbligatorioAlmenoUno(List<CampiAggiuntiviPraticaWebDto> campi){
		boolean ret = false;
		for (CampiAggiuntiviPraticaWebDto campiAggiuntiviPratica : campi) {
			if(campiAggiuntiviPratica.getValore() != null && !campiAggiuntiviPratica.getValore().equals("") && !campiAggiuntiviPratica.getValore().replaceAll(",", "").equals(""))
				ret = true;
		} 
		return ret;
	}
	public static boolean campoObbligatorioDipendente(List<CampiAggiuntiviPraticaWebDto> campi){
		boolean ret = false;
		if(campi.get(0).getValore() != null && !campi.get(0).getValore().equals("") && !campi.get(0).getValore().replaceAll(",", "").equals("")){
			//verifico che se c'è l'elemento 0 anche l' altro campo e' valorizzato
			if(campi.get(1).getValore() != null && !campi.get(1).getValore().equals("") && !campi.get(1).getValore().replaceAll(",", "").equals(""))
				ret = true;

		}else{
			//verifico che anche l' elemento 1 non e' valorizzato
			if(campi.get(1).getValore() == null || campi.get(1).getValore().equals("") || campi.get(1).getValore().replaceAll(",", "").equals(""))
				ret = true;
		}		

		return ret;
	}
	public static boolean verificaControllo(List<CampiAggiuntiviPraticaWebDto> campi,int indice){
		String controllo = campi.get(indice).getCampiAggiuntivi().getControllo();
		String ctrl[] = controllo.split("#");
		if(ctrl[0].equals("campoObbligatorio"))
			return campoObbligatorio(campi.get(indice));
		if(ctrl[0].equals("campoObbligatorioAlmenoUno")){
			String campiSpl[] = ctrl[1].split(",");
			List<CampiAggiuntiviPraticaWebDto> campiCheck = new ArrayList<CampiAggiuntiviPraticaWebDto>();
			for (int i = 0; i < campiSpl.length; i++) {
				campiCheck.add(campi.get(Integer.parseInt(campiSpl[i])));
			}
			return campoObbligatorioAlmenoUno(campiCheck);
		}
		if(ctrl[0].equals("campoObbligatorioDipendente")){
			String campiSpl[] = ctrl[1].split(",");
			List<CampiAggiuntiviPraticaWebDto> campiCheck = new ArrayList<CampiAggiuntiviPraticaWebDto>();
			for (int i = 0; i < campiSpl.length; i++) {
				campiCheck.add(campi.get(Integer.parseInt(campiSpl[i])));
			}
			return campoObbligatorioDipendente(campiCheck);
		}
		if(ctrl[0].equals("campoObbligatorioDipendenteDaValore")){
			String campiSpl[] = ctrl[1].split("v");
			CampiAggiuntiviPraticaWebDto campoCheck = campi.get(Integer.parseInt(campiSpl[0]));

			if (contieneValore(campoCheck, Integer.parseInt(campiSpl[1])))
				return campoObbligatorio(campi.get(indice));
		}
		return true;
	}

	private static boolean contieneValore(CampiAggiuntiviPraticaWebDto campo, int valoreIdx){
		boolean ret = false;
		String listaValori[] = campo.getCampiAggiuntivi().getListaValori().split(",");

		if (listaValori !=null && listaValori.length >= valoreIdx){
			String valore = listaValori[valoreIdx];
		
			if (campo.getValore()!=null){
				if(campo.getValore().contains(valore))
					ret = true;
			}
		}


		return ret;
	}
}
