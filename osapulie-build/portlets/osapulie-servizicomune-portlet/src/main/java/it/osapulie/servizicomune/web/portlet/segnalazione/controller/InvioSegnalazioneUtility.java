/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.segnalazione.controller;

import it.osapulie.shared.service.SegnalazioneFoglia;


public class InvioSegnalazioneUtility {
	

	public InvioSegnalazioneUtility() {
	}
	
	public String stampaNodo(SegnalazioneFoglia nodo)
	{
		String mail="";
		String label = "font-size: 12 px; padding: 0px 8px 0px 0px;";
		String stile_old = "text-decoration: line-through; color: red; padding: 0px 7px; font-size: 12px; font-weight: bold;";
		String stile_new ="color: green; padding: 0px 5px; font-size: 12px;";
		if(nodo.isFoglia() && nodo.isCheck()){
			mail +="<li>" +
					"<table>" +
						"<tr>" +
							"<td style=\""+label+"\">"+ nodo.getChiave()+"</td>" +
							"<td style=\""+stile_old+"\">"+ nodo.getValore_old()+"</td>" +
							"<td style=\""+stile_new+"\">"+ nodo.getValore_new()+"</td>" +
						"</tr>" +
					"</table>" +
				   "</li>";
		}else if(nodo.isCheck()) {
			if(nodo.getChiave()!=null && !nodo.getChiave().equals("")){
				mail += "<li>" + nodo.getChiave();
			}
			mail +="<ul>";
			for(SegnalazioneFoglia foglia :nodo.getListaSegnalazioni()){
				if(foglia.isCheck()){
					mail += stampaNodo(foglia);
				}else if(!foglia.isCheck() && foglia.isFoglia()){
					mail += "<li>" +
						"<table>" +
							"<tr>" +
								"<td style=\""+label+"\">"+ foglia.getChiave()+"</td>" +
								"<td style=\""+label+"\">"+ foglia.getValore_old()+"</td>" +
								"<td style=\""+stile_new+"\"></td>" +
							"</tr>" +
						"</table>" +
					   "</li>";
				}
			}
			mail += "</ul>";
			if(nodo.getChiave()!=null && !nodo.getChiave().equals("")){
				mail += "</li>";
			}
		}
		return mail;
	}
	
}
