package it.osapulie.pratiche.web.portlet.varie;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.pratiche.web.ws.types.DatiCatastaliImmobileDto;
import it.osapulie.pratiche.web.ws.types.PraticaDto;
import it.osapulie.pratiche.web.ws.types.PraticaWebDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Form validator per {@link PraticaDto}.
 * 
 * @author Maria Michela Birtolo
 */
@Component( "praticaWebValidator" )
public class PraticaWebValidator implements Validator {
	
	private static Logger log = LoggerFactory.getLogger( PraticaWebValidator.class );

//	@Inject
//	private ComuneISARepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports( Class<?> clazz ) {
		return PraticaWebDto.class.isAssignableFrom( clazz );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate( Object target, Errors errors ) {

		PraticaWebDto pratica = (PraticaWebDto) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oggettoRichiesta", "NotEmpty.field.required");
		if(pratica.getTitolare() != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolare", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tecnico", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "impresa", "NotEmpty.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRichiesta", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipologia", "NotEmpty.field.required");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richNominativo", "NotEmpty.field.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richNome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richCognome", "NotEmpty.field.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richIndirizzo", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richDataNascita", "NotEmpty.field.required");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richCell", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richFax", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richEmail", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richComune", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richComuneNascita", "NotEmpty.field.required");
//		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richCf", "NotEmpty.field.required");
//		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "richPiva", "NotEmpty.field.required");
		if(pratica.getRichCf().equals("") && pratica.getRichPiva().equals("")){
			errors.rejectValue( "richCf", "NotEmpty.field.required");
		}
		
		if(pratica.getRichComune() == null || pratica.getRichComune().getDenominazione()==null || (pratica.getRichComune().getDenominazione() != null && pratica.getRichComune().getDenominazione().equals( "" ))){
			errors.rejectValue( "richComune", "NotEmpty.field.required");
		}
		if(pratica.getRichComuneNascita() == null || pratica.getRichComuneNascita().getDenominazione()==null || (pratica.getRichComuneNascita().getDenominazione() != null && pratica.getRichComuneNascita().getDenominazione().equals( "" ))){
			errors.rejectValue( "richComuneNascita", "NotEmpty.field.required");
		}
		
//		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_den", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobileInd", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobilePiano", "NotEmpty.field.required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobileInterno", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobileCap", "NotEmpty.field.required");
//		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobileTipo", "NotEmpty.field.required");
//		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_areaSin", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobileComune", "NotEmpty.field.required");
		
		if(pratica.getImmobileComune() == null || (pratica.getImmobileComune().getDenominazione() != null && pratica.getImmobileComune().getDenominazione().equals( "" ))){
			errors.rejectValue( "immobileComune", "NotEmpty.field.required");
		}
		
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_foglio", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_part", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_sub", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobile_cat", "NotEmpty.field.required");
		*/
//		//verifica che almeno il primo sia inserito
		if(pratica.getDatiCatastali() != null && !pratica.getDatiCatastali().isEmpty()){
			DatiCatastaliImmobileDto dati = pratica.getDatiCatastali().get(0);
			if(dati.getNum().equals("") && dati.getImmobileFoglioNCEU().equals("") && dati.getImmobileCatNCEU().equals("") && dati.getImmobilePartNCEU().equals("") && dati.getImmobileSubNCEU().equals("") && 
					dati.getImmobileFoglioNCT().equals("") && dati.getImmobilePartNCT().equals("") && dati.getImmobileSubNCT().equals("") ){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali[0].immobileFoglioNCEU", "NotEmpty.field.required");
			}
	//		//verifica che se inserito un dato che ci siano tutti
			for(int j=0; j < pratica.getDatiCatastali().size(); j++){
				dati = pratica.getDatiCatastali().get(j);
				if(!dati.getNum().equals("") ){
					if(!dati.getImmobileFoglioNCEU().equals("") || !dati.getImmobileCatNCEU().equals("") || !dati.getImmobilePartNCEU().equals("") || !dati.getImmobileSubNCEU().equals("") ) {
						
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobileFoglioNCEU", "NotEmpty.field.required");
						//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobileSubNCEU", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobilePartNCEU", "NotEmpty.field.required");
						//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobileCatNCEU", "NotEmpty.field.required");
						if(!com.liferay.portal.kernel.util.Validator.isNumber( dati.getImmobileFoglioNCEU() ))
							errors.rejectValue( "datiCatastali["+j+"].immobileFoglioNCEU", "NotIsNumber.field.required");
						if(!com.liferay.portal.kernel.util.Validator.isNumber( dati.getImmobilePartNCEU() ))
							errors.rejectValue( "datiCatastali["+j+"].immobilePartNCEU", "NotIsNumber.field.required");
						
					}
					if(!dati.getImmobileFoglioNCT().equals("") || !dati.getImmobilePartNCT().equals("") || !dati.getImmobileSubNCT().equals("")){
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobileFoglioNCT", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobileSubNCT", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].immobilePartNCT", "NotEmpty.field.required");
						if(!com.liferay.portal.kernel.util.Validator.isNumber( dati.getImmobileFoglioNCT() ))
							errors.rejectValue( "datiCatastali["+j+"].immobileFoglioNCT", "NotIsNumber.field.required");
						if(!com.liferay.portal.kernel.util.Validator.isNumber( dati.getImmobilePartNCT() ))
							errors.rejectValue( "datiCatastali["+j+"].immobilePartNCT", "NotIsNumber.field.required");
					}
				}
				else if(!dati.getImmobileFoglioNCEU().equals("") || !dati.getImmobileCatNCEU().equals("") || !dati.getImmobilePartNCEU().equals("") || !dati.getImmobileSubNCEU().equals("")
						|| !dati.getImmobileFoglioNCT().equals("") || !dati.getImmobilePartNCT().equals("") || !dati.getImmobileSubNCT().equals("")
						) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiCatastali["+j+"].num", "NotEmpty.field.required");
				}
			}
		}
		if(pratica.getTipologia() == null || !(pratica.getTipologia().getId() > 0))
			errors.rejectValue( "tipologia", "NotEmpty.field.required");
		
		//controllo obbligatorieta' degli allegati.
		//commento questo controllo per implementarlo al momento dell'invio del modello di richiesta 
//		for(int i=0; i < pratica.getAllegati().size(); i++){
////			log.debug( "allegati.size = "+pratica.getAllegati().size() );
////			log.debug( "allegati.isObbligatorio = "+pratica.getAllegati().get(i).getAllegati().isObbligatorio() );			
////			log.debug( "allegati.getAllegato = "+pratica.getAllegati().get(i).getAllegato() );
////			log.debug( "allegati.getAllegato.length = "+pratica.getAllegati().get(i).getAllegato().length );
////			log.debug( "allegati.getuuid = "+pratica.getAllegati().get(i).getUuidFile() );
//			
//			if(pratica.getAllegati().get(i).getAllegati().isObbligatorio()){
//				if((pratica.getId() == null && (pratica.getAllegati().get(i).getAllegato().length==0 || pratica.getAllegati().get(i).getNomeFile().equals( "" ))) || (pratica.getId()!=null && (pratica.getAllegati().get(i).getAllegato().length == 0 || pratica.getAllegati().get(i).getNomeFile().equals( "" )) && pratica.getAllegati().get(i).getUuidFile().equals(""))  )
//					errors.rejectValue( "allegati["+i+"]", "NotEmpty.field.required");
//			}
//		}
		
		//controllo la dimensione dei file
		for(int i=0; i < pratica.getAllegati().size(); i++){			
			if(pratica.getAllegati().get(i).getAllegato() != null && pratica.getAllegati().get(i).getAllegato().length > 10485760 ){
					errors.rejectValue( "allegati["+i+"]", "Maximum.upload.size.exceded");
			}
//			String nomeFile = pratica.getAllegati().get(i).getNomeFile();
//			if(nomeFile.contains( ".p7m" )){			
//				String nomeFileext = nomeFile.substring( 0, nomeFile.lastIndexOf( "." ));				
//				if(nomeFileext.lastIndexOf( "." ) == -1){
//					errors.rejectValue( "allegati["+i+"]", "Maximum.upload.size.exceded");
//				}
//			}
		}
		
		if(pratica.getCampiaggiuntivi()!= null && pratica.getCampiaggiuntivi().size()>0)
		{
			for(int i=0;i<pratica.getCampiaggiuntivi().size();i++)
			{
				if (pratica.getCampiaggiuntivi().get(i) != null && pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi() != null){
					
//				log.debug(String.format("%s)  Label:%s, controllo:%s",
//						i, 
//						pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getLabel(), 
//						pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getControllo()));
//				
				if(pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getTipologia()=="d" && pratica.getCampiaggiuntivi().get(i).getValore() != null && !pratica.getCampiaggiuntivi().get(i).getValore().equals(""))
				{
					if(!com.liferay.portal.kernel.util.Validator.isNumber(pratica.getCampiaggiuntivi().get(i).getValore()))
					{
						errors.rejectValue( "campiaggiuntivi["+i+"].valore", "NotIsNumber.field.required" );
						//return;
					}
				}
				if(pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getTipologia()=="e" && pratica.getCampiaggiuntivi().get(i).getValore() != null && !pratica.getCampiaggiuntivi().get(i).getValore().equals(""))
				{
					if(!DateUtils.isData(pratica.getCampiaggiuntivi().get(i).getValore()))
					{
						errors.rejectValue( "campiaggiuntivi["+i+"].valore", "NotIsDate.field.required" );
						//return;
					}
				}
				if(pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getControllo() != null && !pratica.getCampiaggiuntivi().get(i).getCampiAggiuntivi().getControllo().equals("")){
					if(!ControlliCampiAggiuntivi.verificaControllo(pratica.getCampiaggiuntivi(), i)){
						errors.rejectValue( "campiaggiuntivi["+i+"].valore", "NotEmpty.field.required" );
						//return;
					}
				}
				}
			}
		}
		
	}	
}
