package XML_Sign;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.ssl.PKCS8Key;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Salvador
 */
public class Firma_XML {
    
    /**
        * Crea una instancia de la llave privada en formato PrivateKey.
        * 
        * @param rutaLlave Ruta fisica del archivo de la llave privada. 
        * @param contraseña Contraseña requerida para desencriptar la llave privada.
        * 
        * @return  Regresar la instancia de la llave privada en formato PrivateKey.        
    */
    public PrivateKey CargarLlavePrivada(String rutaLlave, String contraseña)throws IOException, GeneralSecurityException {
        
        //File file = new File(rutaLlave);
        //byte[] bytesDatos = Files.readAllBytes(file.toPath());
        
        char[] password = contraseña.toCharArray();
        
        FileInputStream xfile = new FileInputStream(rutaLlave);
        PKCS8Key pkcs8 = new PKCS8Key( xfile, password);
        PrivateKey pk = pkcs8.getPrivateKey();
        
        return pk;
    }
    
    /**
        * Carga un archivo de certificado y regresa los datos importantes.
        * 
        * @param rutaCert Ruta fisica del archivo del certificado.
        * 
        * @return  Regresar los datos importantes del certificao cargado.
    */
    public DatosCertificado CargarDatosCetificado(String rutaCert) throws FileNotFoundException, CertificateException{
        DatosCertificado datos ;
        
        InputStream inStream = new FileInputStream(rutaCert);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);        
        datos = new DatosCertificado(cert);
        
        return datos;
    }
    
    /**
     * Genera el documento a firmar
     */
    public String XML_Root(String campo1,String campo2, String campo3){
        String xmlRoot = "";        
            xmlRoot += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            xmlRoot += "<contenido>";   //  xmlns:xsd=""http://www.w3.org/2001/XMLSchema"" xmlns:xsi=""http://www.w3.org/2001/XMLSchema-instance""
            xmlRoot += "<acuse>";
            xmlRoot += "<text>" + campo1 + "</text>";
            xmlRoot += "<evidence1>" + campo2 + "</evidence1>";
            xmlRoot += "<evidence2>" + campo3 + "</evidence2>";
            xmlRoot += "<date>" + FormatearFecha(new Date()) + "</date>";
            xmlRoot += "<uuid>" + java.util.UUID.randomUUID() + "</uuid>";
            xmlRoot += "</acuse>";
            xmlRoot += "</contenido>";
        
        return xmlRoot;
    }
    
    /**
     * Firma documento XML
     * @param xmldoc Documento xml que se firmara
     * @param privateKey Llave privada que se utilizara para firmar el documento
     * @param datosCert Certificado del cual se obtendran los datos del emisor y la llave publica
     * @return XMLSign_Resp Un objecto el cual incluye el documento firmado, el digest value y el signature value
     */
    public XMLSign_Resp FirmarXML(String xmlDoc, PrivateKey privateKey, DatosCertificado datosCertificado){
        //*** Convertir xmlString a documento xml
        Document docXml = convertStringToXMLDocument(xmlDoc);
        String stringDigestValue = "";
        String stringSignatureValue = "";
        
        X509Certificate cert = datosCertificado.getCertificate();
            
        try { 
        
            //***Crear XML Signature Factory, que se usara para generar el enveloped signature 
            XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");

            //***  Create a DOMSignContext and specify the DSA PrivateKey and location of the resulting XMLSignature's parent element
            DOMSignContext domSignCtx = new DOMSignContext(privateKey, docXml.getDocumentElement() );  

            Reference ref = null;  
            SignedInfo signedInfo = null;  

            // Create a Reference to the enveloped document (in this case we are signing the whole document, so a URI of "" signifies that) and
            // also specify the SHA1 digest algorithm and the ENVELOPED Transform
            ref = xmlSigFactory.newReference("",
                xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
                Collections.singletonList( xmlSigFactory.newTransform( Transform.ENVELOPED, (TransformParameterSpec) null) ),
                null,
                null
            );  

            //*** Crear signarute info
            signedInfo = xmlSigFactory.newSignedInfo(
                xmlSigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,  
                (C14NMethodParameterSpec) null),
                xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
                Collections.singletonList(ref)
            );  
            

            //*** Obtener llave privada del certificado
            PublicKey publicKey = cert.getPublicKey();

            
            //*** Generando el KeyInfo
            List keyInfoItems = new ArrayList();
            KeyInfoFactory keyInFact = xmlSigFactory.getKeyInfoFactory();
            
            //*** Agregar la llave publica
            KeyValue keyVal = keyInFact.newKeyValue(publicKey);
            keyInfoItems.add(keyVal);
            
            //*** Agregar los datos del certificado
            List certDataContent = new ArrayList();                        
            X509IssuerSerial certIssuert = keyInFact.newX509IssuerSerial(cert.getIssuerX500Principal().getName("RFC2253"), cert.getSerialNumber());            
            certDataContent.add(certIssuert);            
            certDataContent.add(cert.getSubjectX500Principal().getName("RFC2253"));
            certDataContent.add(cert);
            X509Data certData = keyInFact.newX509Data(certDataContent);
            keyInfoItems.add(certData);
                        
            //*** Crear el KeyInfo
            KeyInfo kInfo = keyInFact.newKeyInfo( keyInfoItems);
            
            
            //*** Crear nodo personalizado Object "gobmxEsquemaTramitacion"           
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            Document doc = dbf.newDocumentBuilder().newDocument();
                
            Element elementSignProp = doc.createElement("dsp:SignatureProperties");
            elementSignProp.setAttribute("xmlns:dsp","http://www.w3.org/2009/xmldsig-properties");
            
            Element elSignProp1 = doc.createElement("SignatureProperty");
            elSignProp1.setAttribute("Id", "identifier");
            elSignProp1.setAttribute("Target", "#DistributorASignature");
            Element oIdentifier11 = doc.createElement("Identifier");
            oIdentifier11.setTextContent("Acuse");
            elSignProp1.appendChild(oIdentifier11);
                        
            Element elSignProp2 = doc.createElement("SignatureProperty");
            elSignProp2.setAttribute("Id", "created");
            elSignProp2.setAttribute("Target", "#DistributorASignature");
            Element oIdentifier21 = doc.createElement("Created");
            oIdentifier21.setTextContent(FormatearFecha(new Date()));
            elSignProp2.appendChild(oIdentifier21);
                        
            Element elSignProp3 = doc.createElement("SignatureProperty");
            elSignProp3.setAttribute("Id", "curpOrfcFirmante");
            elSignProp3.setAttribute("Target", "#DistributorASignature");
            Element oIdentifier31 = doc.createElement("curpOrfcFirmante");
            oIdentifier31.setTextContent(datosCertificado.getCurp());
            elSignProp3.appendChild(oIdentifier31);
                        
            Element elSignProp4 = doc.createElement("SignatureProperty");
            elSignProp4.setAttribute("Id", "nombreFirmante");
            elSignProp4.setAttribute("Target", "#DistributorASignature");
            Element oIdentifier41 = doc.createElement("nombreFirmante");
            oIdentifier41.setTextContent(datosCertificado.getNombre());
            elSignProp4.appendChild(oIdentifier41);
            
            elementSignProp.appendChild(elSignProp1);
            elementSignProp.appendChild(elSignProp2);
            elementSignProp.appendChild(elSignProp3);
            elementSignProp.appendChild(elSignProp4);
                            
            /* Object */
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            XMLObject object1 = fac.newXMLObject(Collections.singletonList(new DOMStructure(elementSignProp)),"gobmxEsquemaTramitacion" , null, null);
                        
            
            //*** Create XML Signature
            System.out.println("> XML Sign ");
            XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, kInfo, Collections.singletonList(object1), null,null);
            //XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, kInfo);  
            
            //*** Firmar documento
            xmlSignature.sign(domSignCtx);
            
            // Obtner el digestvalue y el signaturevalue
            byte[] bytesDigestValue = ref.getDigestValue();
            stringDigestValue = Base64.getEncoder().encodeToString(bytesDigestValue);
            
            byte[] bytesSignatureValue = xmlSignature.getSignatureValue().getValue();
            stringSignatureValue = Base64.getEncoder().encodeToString(bytesSignatureValue);
                        
            
        } catch (MarshalException ex) {  
            System.out.println("Error: " + ex.getMessage() );
            return null;
        } catch (XMLSignatureException ex) {  
            System.out.println("Error: " + ex.getMessage() );
            return null;
        }catch (NoSuchAlgorithmException ex) {  
            System.out.println("Error: " + ex.getMessage() );
            return null;
        } catch (InvalidAlgorithmParameterException ex) {  
            System.out.println("Error: " + ex.getMessage() );
            return null;
        } catch (KeyException ex) {
            System.out.println("Error: " + ex.getMessage() );
            return null;
        }catch(Exception err){
            System.out.println("err: " + err.getMessage() + "\n "+  err.getStackTrace());
            return null;
        } finally{
            
            return new XMLSign_Resp(docXml, stringDigestValue,stringSignatureValue);
            
        }

    }

    
    /**** Funciones Generales ******/
    private Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            doc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));
        } 
        catch (Exception e)  {
            e.printStackTrace();
        } finally{
            return doc;
        }
        
    }
    private String FormatearFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        return sdf.format(fecha);
    }
}