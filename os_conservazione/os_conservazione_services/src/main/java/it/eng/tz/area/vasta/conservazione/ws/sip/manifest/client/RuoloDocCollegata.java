//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per RuoloDocCollegata.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="RuoloDocCollegata"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="precedente"/&gt;
 *     &lt;enumeration value="successivo"/&gt;
 *     &lt;enumeration value="altro"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RuoloDocCollegata")
@XmlEnum
public enum RuoloDocCollegata {

    @XmlEnumValue("precedente")
    PRECEDENTE("precedente"),
    @XmlEnumValue("successivo")
    SUCCESSIVO("successivo"),
    @XmlEnumValue("altro")
    ALTRO("altro");
    private final String value;

    RuoloDocCollegata(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RuoloDocCollegata fromValue(String v) {
        for (RuoloDocCollegata c: RuoloDocCollegata.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
