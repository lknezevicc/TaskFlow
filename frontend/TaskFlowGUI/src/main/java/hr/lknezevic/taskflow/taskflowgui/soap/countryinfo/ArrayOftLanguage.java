
package soap.countryinfo;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOftLanguage complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ArrayOftLanguage">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="tLanguage" type="{http://www.oorsprong.org/websamples.countryinfo}tLanguage" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOftLanguage", propOrder = {
    "tLanguage"
})
public class ArrayOftLanguage {

    @XmlElement(nillable = true)
    protected List<TLanguage> tLanguage;

    /**
     * Gets the value of the tLanguage property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tLanguage property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TLanguage }
     * </p>
     * 
     * 
     * @return
     *     The value of the tLanguage property.
     */
    public List<TLanguage> getTLanguage() {
        if (tLanguage == null) {
            tLanguage = new ArrayList<>();
        }
        return this.tLanguage;
    }

}
