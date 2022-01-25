//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CodiceEsitoElabSIP.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CodiceEsitoElabSIP"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="KO"/&gt;
 *     &lt;enumeration value="ERR"/&gt;
 *     &lt;enumeration value="WARN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CodiceEsitoElabSIP")
@XmlEnum
public enum CodiceEsitoElabSIP {


    /**
     * Accettato senza errori nè avvertimenti sugli item presenti nel SIP
     * 
     */
    OK,

    /**
     * Rifiutato perchè non conforme alle regole stabilite (SIP non formato correttamente, SIPManifest non conforme al tracciato ecc)
     * 
     */
    KO,

    /**
     * Accettato ma ci sono errori su alcuni o tutti gli item presenti nel SIP
     * 
     */
    ERR,

    /**
     * Accettato ma ci sono avvertimenti (e nessun errore) su alcuni o tutti gli item presenti nel SIP
     * 
     */
    WARN;

    public String value() {
        return name();
    }

    public static CodiceEsitoElabSIP fromValue(String v) {
        return valueOf(v);
    }

}
