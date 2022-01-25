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
 * <p>Classe Java per CategoriaUA.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoriaUA"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FO"/&gt;
 *     &lt;enumeration value="AI"/&gt;
 *     &lt;enumeration value="UAR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CategoriaUA")
@XmlEnum
public enum CategoriaUA {


    /**
     * Fondo
     * 
     */
    FO,

    /**
     * Aggregato intermedio che sta al di sotto del fondo ma non è un'unità archivistica: ad esempio una serie, una sotto-serie, una partizione, un sub-fondo ecc
     * 
     */
    AI,

    /**
     * Unità archivistica, ovvero un aggregato "foglia" della struttura gerarchica dell'archivio (può essere un fascicolo o un sotto-fascicolo o un registro ecc)
     * 
     */
    UAR;

    public String value() {
        return name();
    }

    public static CategoriaUA fromValue(String v) {
        return valueOf(v);
    }

}
