/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import XML_Sign.DatosCertificado;
import XML_Sign.Firma_XML;
import XML_Sign.XMLSign_Resp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.util.Base64;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import pruebasencript.PruebasEncript;

/**
 *
 * @author Salvador
 */
public class Main extends javax.swing.JFrame {
    
    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_acuse = new javax.swing.JTextArea();
        tb_certi = new javax.swing.JTextField();
        tb_llave = new javax.swing.JTextField();
        btn_cert = new javax.swing.JButton();
        btn_llave = new javax.swing.JButton();
        tb_doc1 = new javax.swing.JTextField();
        tb_doc2 = new javax.swing.JTextField();
        btn_doc1 = new javax.swing.JButton();
        btn_doc2 = new javax.swing.JButton();
        btn_firmar = new javax.swing.JButton();
        tb_msg = new javax.swing.JTextField();
        tb_digest = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_sign = new javax.swing.JTextArea();
        tb_pass = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Certificado");

        jLabel2.setText("Llave Privada");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Acuse");

        jLabel5.setText("Documento 1");

        jLabel6.setText("Documento 3");

        tb_acuse.setColumns(20);
        tb_acuse.setRows(5);
        jScrollPane1.setViewportView(tb_acuse);

        tb_certi.setName("tb_certi"); // NOI18N

        btn_cert.setText("B");
        btn_cert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_certActionPerformed(evt);
            }
        });

        btn_llave.setText("B");
        btn_llave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_llaveActionPerformed(evt);
            }
        });

        btn_doc1.setText("B");
        btn_doc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doc1ActionPerformed(evt);
            }
        });

        btn_doc2.setText("B");
        btn_doc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doc2ActionPerformed(evt);
            }
        });

        btn_firmar.setText("FIRMAR DOCUMENTO");
        btn_firmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firmarActionPerformed(evt);
            }
        });

        tb_msg.setEditable(false);

        tb_digest.setEditable(false);

        jLabel7.setText("Mensaje");

        jLabel8.setText("Digest Value");

        jLabel9.setText("Signature Value");

        tb_sign.setEditable(false);
        tb_sign.setColumns(20);
        tb_sign.setLineWrap(true);
        tb_sign.setRows(5);
        jScrollPane2.setViewportView(tb_sign);

        jMenu1.setText("Cargar Certificado");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_digest)
                            .addComponent(tb_msg)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_certi)
                                    .addComponent(tb_llave))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_cert, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(btn_llave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_doc1)
                                    .addComponent(tb_doc2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_doc1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(btn_doc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tb_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_firmar)
                .addGap(273, 273, 273))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tb_certi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cert))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tb_llave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_llave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tb_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_doc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btn_doc1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_doc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btn_doc2))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_msg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_digest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btn_firmar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_certActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_certActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Certificado", "cer");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            this.tb_certi.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_certActionPerformed

    private void btn_llaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_llaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Llave privada", "key");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            this.tb_llave.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_llaveActionPerformed

    private void btn_doc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doc1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            this.tb_doc1.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_doc1ActionPerformed

    private void btn_doc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doc2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            this.tb_doc2.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_doc2ActionPerformed

    private void btn_firmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firmarActionPerformed
        this.GenerarXMLFirmado();
    }//GEN-LAST:event_btn_firmarActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        JOptionPane.showMessageDialog(this, "Cargar datos del certificado, no implementado");
    }//GEN-LAST:event_jMenu1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cert;
    private javax.swing.JButton btn_doc1;
    private javax.swing.JButton btn_doc2;
    private javax.swing.JButton btn_firmar;
    private javax.swing.JButton btn_llave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tb_acuse;
    private javax.swing.JTextField tb_certi;
    private javax.swing.JTextField tb_digest;
    private javax.swing.JTextField tb_doc1;
    private javax.swing.JTextField tb_doc2;
    private javax.swing.JTextField tb_llave;
    private javax.swing.JTextField tb_msg;
    private javax.swing.JPasswordField tb_pass;
    private javax.swing.JTextArea tb_sign;
    // End of variables declaration//GEN-END:variables

    
    private void GenerarXMLFirmado(){
        this.tb_msg.setText("");
        
        Firma_XML xf = new Firma_XML();
        
        try {
            
            //*** Cargar Certificado
            DatosCertificado datos = xf.CargarDatosCetificado(this.tb_certi.getText());
                       
            
            //*** Cargar Llave Privada
            PrivateKey pkey = xf.CargarLlavePrivada(this.tb_llave.getText(), this.tb_pass.getText());

            
            //*** Cargar Acuse
            String acuse = this.tb_acuse.getText();
            String file1_Base64 = "";
            String file2_Base64 = "";            
            try{
                File xf1 = new File(this.tb_doc1.getText());
                file1_Base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(xf1.toPath()));
            }catch(Exception err){}
            
            try{
                File xf2 = new File(this.tb_doc2.getText());
                file2_Base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(xf2.toPath()));
            }catch(Exception err){}
            

            //*** Generar XML
            String xmlR = xf.XML_Root(acuse, file1_Base64, file2_Base64);
            

            //*** Firmar XML
            Document xmlFirmado = null;
            String digestValue = "";
            String signatureValue = "";
            
            XMLSign_Resp response = xf.FirmarXML(xmlR, pkey, datos);
            if(response != null){
                xmlFirmado =  response.XmlFirmado;
                digestValue = response.DigestValue;
                signatureValue = response.SignatureValue;
            }
            
            this.tb_digest.setText(digestValue);
            this.tb_sign.setText(signatureValue);
                
            
            
            //*** Guardar el documento en archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardando XML firmado");   
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Xml", "xml");
            fileChooser.setFileFilter(filter);
        
            int userSelection = fileChooser.showSaveDialog(this); 
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                
                DOMSource source = new DOMSource(xmlFirmado);
                FileWriter writer = new FileWriter(fileToSave);
                StreamResult result = new StreamResult(writer);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer;            
                transformer = transformerFactory.newTransformer();
                transformer.transform(source, result);
            }
            
            
            this.tb_msg.setText("Documento firmado");
            
            
        } catch (FileNotFoundException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        } catch (CertificateException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        }catch (GeneralSecurityException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        } catch (IOException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        } catch (TransformerConfigurationException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        }catch (TransformerException ex) {
            this.tb_msg.setText("error:" + ex.getMessage());
        }
        
    }

}
