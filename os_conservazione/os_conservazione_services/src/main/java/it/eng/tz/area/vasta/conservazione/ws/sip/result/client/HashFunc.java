//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per HashFunc.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="HashFunc"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SHA-1"/&gt;
 *     &lt;enumeration value="SHA-256"/&gt;
 *     &lt;enumeration value="RIPEMD-160"/&gt;
 *     &lt;enumeration value="MD5"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "HashFunc")
@XmlEnum
public enum HashFunc {

    @XmlEnumValue("SHA-1")
    SHA_1("SHA-1"),
    @XmlEnumValue("SHA-256")
    SHA_256("SHA-256"),
    @XmlEnumValue("RIPEMD-160")
    RIPEMD_160("RIPEMD-160"),
    @XmlEnumValue("MD5")
    MD_5("MD5");
    private final String value;

    HashFunc(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HashFunc fromValue(String v) {
        for (HashFunc c: HashFunc.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
