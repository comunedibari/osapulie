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
 * <p>Classe Java per CategoriaRelDoc.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoriaRelDoc"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="appartenenza"/&gt;
 *     &lt;enumeration value="causale"/&gt;
 *     &lt;enumeration value="argomento"/&gt;
 *     &lt;enumeration value="altro"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CategoriaRelDoc")
@XmlEnum
public enum CategoriaRelDoc {

    @XmlEnumValue("appartenenza")
    APPARTENENZA("appartenenza"),

    /**
     * Indica che la relazione è di causa-effetto
     * 
     */
    @XmlEnumValue("causale")
    CAUSALE("causale"),

    /**
     * Indica che la relazione è per affinità di materia/argomento trattata
     * 
     */
    @XmlEnumValue("argomento")
    ARGOMENTO("argomento"),
    @XmlEnumValue("altro")
    ALTRO("altro");
    private final String value;

    CategoriaRelDoc(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoriaRelDoc fromValue(String v) {
        for (CategoriaRelDoc c: CategoriaRelDoc.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
