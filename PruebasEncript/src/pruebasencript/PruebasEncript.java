package pruebasencript;

import XML_Sign.DatosCertificado;
import XML_Sign.Firma_XML;
import XML_Sign.XMLSign_Resp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.PrivateKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;



/**
 *
 * @author Salvador
 */
public class PruebasEncript {
    
    public static void main(String[] args) throws IOException, GeneralSecurityException{
        System.out.println("Iniciando");
        
        String xRutaCert = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\oemj730701858.cer";
        String xRutaLLave = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\Claveprivada_FIEL_OEMJ730701858_20190729_121658.key";
        String xPass = "*******";
        Firma_XML xf = new Firma_XML();
        
        
        //*** Caragar llave privada        
        PrivateKey pkey = xf.CargarLlavePrivada(xRutaLLave, xPass);        
        
        
        //*** Cargar certificado y obtener los datos
        DatosCertificado datos = xf.CargarDatosCetificado(xRutaCert);
        
        
        //*** Generara documento xml
        String xmlR = xf.XML_Root("Campo1", "Campo2", "Campo 2");
        
        
        //*** Firmar documento xml
        Document xmlFirmado;
        String digestValue = "";
        String signatureValue = "";
        
        try {
            
            XMLSign_Resp response = xf.FirmarXML(xmlR, pkey, datos);            
            xmlFirmado =  response.XmlFirmado;
            digestValue = response.DigestValue;
            signatureValue = response.SignatureValue;
            
            
            System.out.println("DigestValue: " + digestValue);
            System.out.println("SignatureValue: " + signatureValue);
            
            
            //*** Guardar el documento en archivo
            DOMSource source = new DOMSource(xmlFirmado);
            FileWriter writer = new FileWriter(new File("c:/users/salvador/downloads/xmlFirmado.xml"));
            StreamResult result = new StreamResult(writer);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;            
            transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);
        
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PruebasEncript.class.getName()).log(Level.SEVERE, null, ex);
        }catch (TransformerException ex) {
            Logger.getLogger(PruebasEncript.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
