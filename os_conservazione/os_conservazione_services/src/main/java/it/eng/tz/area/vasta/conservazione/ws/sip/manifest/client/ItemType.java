//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ItemType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ItemType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="UA"/&gt;
 *     &lt;enumeration value="SD"/&gt;
 *     &lt;enumeration value="UC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ItemType")
@XmlEnum
public enum ItemType {


    /**
     * Unità di aggregazione
     * 
     */
    UA,

    /**
     * Scheda documento
     * 
     */
    SD,

    /**
     * Unità di conservazione
     * 
     */
    UC;

    public String value() {
        return name();
    }

    public static ItemType fromValue(String v) {
        return valueOf(v);
    }

}
