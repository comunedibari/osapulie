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
 * <p>Classe Java per CategoriaRegistrazione.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoriaRegistrazione"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="protocollo_generale"/&gt;
 *     &lt;enumeration value="protocollo_particolare"/&gt;
 *     &lt;enumeration value="protocollo_interno"/&gt;
 *     &lt;enumeration value="repertorio"/&gt;
 *     &lt;enumeration value="protocollo_emergenza"/&gt;
 *     &lt;enumeration value="altro"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CategoriaRegistrazione")
@XmlEnum
public enum CategoriaRegistrazione {

    @XmlEnumValue("protocollo_generale")
    PROTOCOLLO_GENERALE("protocollo_generale"),
    @XmlEnumValue("protocollo_particolare")
    PROTOCOLLO_PARTICOLARE("protocollo_particolare"),
    @XmlEnumValue("protocollo_interno")
    PROTOCOLLO_INTERNO("protocollo_interno"),
    @XmlEnumValue("repertorio")
    REPERTORIO("repertorio"),
    @XmlEnumValue("protocollo_emergenza")
    PROTOCOLLO_EMERGENZA("protocollo_emergenza"),
    @XmlEnumValue("altro")
    ALTRO("altro");
    private final String value;

    CategoriaRegistrazione(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoriaRegistrazione fromValue(String v) {
        for (CategoriaRegistrazione c: CategoriaRegistrazione.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
