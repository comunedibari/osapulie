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
 * <p>Classe Java per TipoRifCronologico.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoRifCronologico"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="registrazione_protocollo_generale"/&gt;
 *     &lt;enumeration value="registrazione_protocollo_emergenza"/&gt;
 *     &lt;enumeration value="registrazione_protocollo"/&gt;
 *     &lt;enumeration value="registrazione_repertorio"/&gt;
 *     &lt;enumeration value="ricezione_pec"/&gt;
 *     &lt;enumeration value="invio_pec"/&gt;
 *     &lt;enumeration value="stesura"/&gt;
 *     &lt;enumeration value="raccomandata"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipoRifCronologico")
@XmlEnum
public enum TipoRifCronologico {

    @XmlEnumValue("registrazione_protocollo_generale")
    REGISTRAZIONE_PROTOCOLLO_GENERALE("registrazione_protocollo_generale"),
    @XmlEnumValue("registrazione_protocollo_emergenza")
    REGISTRAZIONE_PROTOCOLLO_EMERGENZA("registrazione_protocollo_emergenza"),

    /**
     * Protocollo, anche interno o particolare
     * 
     */
    @XmlEnumValue("registrazione_protocollo")
    REGISTRAZIONE_PROTOCOLLO("registrazione_protocollo"),
    @XmlEnumValue("registrazione_repertorio")
    REGISTRAZIONE_REPERTORIO("registrazione_repertorio"),
    @XmlEnumValue("ricezione_pec")
    RICEZIONE_PEC("ricezione_pec"),
    @XmlEnumValue("invio_pec")
    INVIO_PEC("invio_pec"),
    @XmlEnumValue("stesura")
    STESURA("stesura"),
    @XmlEnumValue("raccomandata")
    RACCOMANDATA("raccomandata");
    private final String value;

    TipoRifCronologico(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoRifCronologico fromValue(String v) {
        for (TipoRifCronologico c: TipoRifCronologico.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
