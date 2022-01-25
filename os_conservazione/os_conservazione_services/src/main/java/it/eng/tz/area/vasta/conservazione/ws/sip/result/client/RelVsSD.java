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
 * <p>Classe Java per RelVsSD.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="RelVsSD"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="P"/&gt;
 *     &lt;enumeration value="ALL"/&gt;
 *     &lt;enumeration value="EML"/&gt;
 *     &lt;enumeration value="RIC_PEC"/&gt;
 *     &lt;enumeration value="SPI"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RelVsSD")
@XmlEnum
public enum RelVsSD {


    /**
     * Documento principale o unico descritto dalla scheda documento (per scheda documento ce ne può essere solo 1)
     * 
     */
    P,

    /**
     * Allegato
     * 
     */
    ALL,

    /**
     * E-mail con cui il/i documenti descritti dalla scheda documento sono stato acquisiti o inviati
     * 
     */
    EML,

    /**
     * E-mail di ricevuta PEC relativa alla e-mail PEC con cui il/i documenti della scheda sono stati inviati ad uno o più destinatari
     * 
     */
    RIC_PEC,

    /**
     * Segnatura.xml di una e-mail di Protocollo Interoperabile
     * 
     */
    SPI;

    public String value() {
        return name();
    }

    public static RelVsSD fromValue(String v) {
        return valueOf(v);
    }

}
