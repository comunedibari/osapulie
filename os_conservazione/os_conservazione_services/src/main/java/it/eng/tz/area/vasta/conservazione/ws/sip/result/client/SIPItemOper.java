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
 * <p>Classe Java per SIPItemOper.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="SIPItemOper"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="I"/&gt;
 *     &lt;enumeration value="U"/&gt;
 *     &lt;enumeration value="D"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SIPItemOper")
@XmlEnum
public enum SIPItemOper {


    /**
     * Invio in conservazione di unità di descrizione o documento digitale non inviato in precedenza
     * 
     */
    I,

    /**
     * Aggiornamento di dati e/o file di un'unità di descrizione o documento digitale (per questo con possibilità di "versionamento") già inviato in conservazione
     * 
     */
    U,

    /**
     * Eliminazione di un'unità di descrizione o singolo documento digitale già inviato in conservazione
     * 
     */
    D;

    public String value() {
        return name();
    }

    public static SIPItemOper fromValue(String v) {
        return valueOf(v);
    }

}
