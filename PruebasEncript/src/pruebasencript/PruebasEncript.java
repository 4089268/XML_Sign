package pruebasencript;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;  
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import org.apache.commons.ssl.PKCS8Key;



/**
 *
 * @author Salvador
 */
public class PruebasEncript {
    
    public static void main(String[] args) throws IOException, GeneralSecurityException{
        System.out.println("Iniciando");
        
        PruebasEncript.CargarLLaves();            
    }
    
    public static void CargarLLaves() throws IOException, GeneralSecurityException{        
        System.out.println("Cargando Llaves\n");
        
        String ruta = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\Claveprivada_FIEL_OEMJ730701858_20190729_121658.key";
        File file = new File(ruta);
        byte[] bytesDatos = Files.readAllBytes(file.toPath());        
        char[] password = "jcom1973".toCharArray();
        
        FileInputStream xfile = new FileInputStream(ruta);
        PKCS8Key pkcs8 = new PKCS8Key( xfile, password);
        PrivateKey pk = pkcs8.getPrivateKey();
        System.out.println(pk.getFormat());
        System.out.println(pk.getAlgorithm());
        
    }
    
    // Synopsis: java GenEnveloped [document] [output]
    //
    //    where "document" is the name of a file containing the XML document
    //    to be signed, and "output" is the name of the file to store the
    //    signed document. The 2nd argument is optional - if not specified,
    //    standard output will be used.
    //
    public static void FirmarXML(){
        // Create a DOM XMLSignatureFactory that will be used to generate the
        // enveloped signature
        // XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        
    }
    
}
