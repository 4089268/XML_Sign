/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Sign;

import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;
/**
 *
 * @author Salvador
 */
public class DatosCertificado {
    private X509Certificate cert;
    
    private int numeroCertificado = 0;
    private double version  = 0;
    private Date emitido;
    private Date vigencia;    
    private String Subject = "";
    private String Issuer = "";
    private String RFC = "";
    private String curp ="";
    private String nombre ="";
    
    public DatosCertificado(X509Certificate certificado){
        this.cert = certificado;
        this.version = this.cert.getVersion();
        this.Subject = ((X500Principal) this.cert.getSubjectX500Principal()).getName("RFC1779");
        this.Issuer = ((X500Principal) this.cert.getIssuerX500Principal()).getName("RFC1779");
        this.emitido = this.cert.getNotBefore();
        this.vigencia = this.cert.getNotAfter();
        
        String[] splitS = this.Subject.split(",");
        for(int i = 0; i < splitS.length; i++){
            String[] tmpVal = splitS[i].split("=");            
            if(tmpVal[0].replace(" ","").equals("OID.2.5.4.5")){
                curp = tmpVal[1];
            }            
            if(tmpVal[0].replace(" ","").equals("OID.2.5.4.45")){
                RFC = tmpVal[1];
            }            
            if(tmpVal[0].replace(" ","").equals("O")){
                nombre = tmpVal[1];
            }            
        }
    }
    
    public X509Certificate getCertificate(){
        return this.cert;
    }
    public int getNumeroCertificado() {
        return numeroCertificado;
    }
    public double getVersion() {
        return version;
    }
    public Date getEmitido() {
        return emitido;
    }
    public Date getVigencia() {
        return vigencia;
    }
    public String getRFC() {
        return RFC;
    }
    public String getCurp() {
        return curp;
    }
    public String getNombre() {
        return nombre;
    }
    public String getSubject() {
        return Subject;
    }
    public String getIssuer() {
        return Issuer;
    }
    
}
