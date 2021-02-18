package XML_Sign;

import org.w3c.dom.Document;

/**
 *
 * @author Salvador
 */
public class XMLSign_Resp {
    
    public Document XmlFirmado;
    public String DigestValue;
    public String SignatureValue;    
    
    public XMLSign_Resp(Document doc, String digestVal, String signValue){
        this.XmlFirmado = doc;
        this.DigestValue = digestVal;
        this.SignatureValue = signValue;
    }
    
}
