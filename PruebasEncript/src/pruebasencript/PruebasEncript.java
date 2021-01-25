package pruebasencript;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.crypto.EncryptedPrivateKeyInfo;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.PBEKeySpec;  
import java.io.IOException;  
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;  
import java.security.Key;
import java.security.KeyFactory;  
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;  
import java.security.NoSuchProviderException;
import java.security.PrivateKey;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.PKCS8EncodedKeySpec;  
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.ssl.PKCS8Key;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import static org.bouncycastle.cms.RecipientId.password;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

/**
 *
 * @author Salvador
 */
public class PruebasEncript {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException{
        // TODO code application logic here
        System.out.println("");
        
        PruebasEncript.CargarLLaves_4();
        //PruebasEncript.CargarLlaves_3();
        //PruebasEncript.CargarLlaves_2();            
        //PruebasEncript.CargarLlaves();
            
            
    }
    
    public static void CargarLlaves() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("Cargar Llaves 1\n");
        
        String ruta = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\Claveprivada_FIEL_OEMJ730701858_20190729_121658.key";
        File file = new File(ruta);
        byte[] bytesDatos = Files.readAllBytes(file.toPath());        
        char[] password = "jcom1973".toCharArray();
        
        System.out.println("Iniciando 2 " + bytesDatos.length);
        
        System.out.println(">1");
        EncryptedPrivateKeyInfo pkInfo = new EncryptedPrivateKeyInfo(bytesDatos);
        System.out.println(">2");
        PBEKeySpec keySpec = new PBEKeySpec(password); // password  
        System.out.println(">3");
        SecretKeyFactory pbeKeyFactory = SecretKeyFactory.getInstance(pkInfo.getAlgName());  
        System.out.println(">4");
        PKCS8EncodedKeySpec encodedKeySpec = pkInfo.getKeySpec(pbeKeyFactory.generateSecret(keySpec));  
        System.out.println(">5");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
        System.out.println(">6");
        PrivateKey encryptedPrivateKey = keyFactory.generatePrivate(encodedKeySpec);
        System.out.println(">7");
        System.out.println(encryptedPrivateKey.getFormat());
        
    }
    
    public static void CargarLlaves_2() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException{
        System.out.println("Cargar Llaves 2\n");
        
        String ruta = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\Claveprivada_FIEL_OEMJ730701858_20190729_121658.key";
        File file = new File(ruta);
        byte[] bytesDatos = Files.readAllBytes(file.toPath());        
        char[] password = "jcom1973".toCharArray();
        
        EncryptedPrivateKeyInfo xInfo = new EncryptedPrivateKeyInfo("AES",bytesDatos);
        System.out.println(xInfo.getEncryptedData());
        
//        var xSpec = xInfo.getKeySpec(xk, "RSA");
//        System.out.println(xInfo.getAlgName());        
        
    }    
    
    public static void CargarLlaves_3() throws IOException{
        System.out.println("Cargar Llaves 3\n");
        
        String ruta = "C:\\Users\\Salvador\\Downloads\\Test_FirmaElectronica\\FIEL_OEMJ730701858_20190729121658\\Claveprivada_FIEL_OEMJ730701858_20190729_121658.key";
        File file = new File(ruta);
        byte[] bytesDatos = Files.readAllBytes(file.toPath());        
        char[] password = "jcom1973".toCharArray();
        
        System.out.println("1");
        PEMParser pemParser = new PEMParser(new FileReader(file));
        
        System.out.println("2");
        Object object = pemParser.readObject();
                
        System.out.println("3");
        PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build(password);
        
        System.out.println("4");
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("RSA");
        
        System.out.println("5");
        KeyPair kp;
        
        System.out.println("6");
        if (object instanceof PEMEncryptedKeyPair) {            
            System.out.println("7 a");
           System.out.println("Encrypted key - we will use provided password");
           kp = converter.getKeyPair(((PEMEncryptedKeyPair) object).decryptKeyPair(decProv));
       } else {
           System.out.println("7 b");
           System.out.println("Unencrypted key - no password needed");
           var ki = converter.getPrivateKey((PrivateKeyInfo) object);
        System.out.println(ki.getFormat());
        System.out.println(ki.getAlgorithm());
           kp = converter.getKeyPair((PEMKeyPair) object);
           
       }
        
    }
    
    public static void CargarLLaves_4() throws IOException, GeneralSecurityException{        
        System.out.println("Cargar Llaves 4\n");
        
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
            
}
