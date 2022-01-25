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
 * <p>Classe Java per FormaGiuridica.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FormaGiuridica"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PA"/&gt;
 *     &lt;enumeration value="AOO"/&gt;
 *     &lt;enumeration value="altro"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FormaGiuridica")
@XmlEnum
public enum FormaGiuridica {


    /**
     * Ente pubblico
     * 
     */
    PA("PA"),

    /**
     * Area Organizzativa Omogenea di un Ente pubblico
     * 
     */
    AOO("AOO"),

    /**
     * altro tipo di suggetto giuridico
     * 
     */
    @XmlEnumValue("altro")
    ALTRO("altro");
    private final String value;

    FormaGiuridica(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FormaGiuridica fromValue(String v) {
        for (FormaGiuridica c: FormaGiuridica.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
