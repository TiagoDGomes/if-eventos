package br.edu.ifsp.eventos.classes.util;

import br.edu.ifsp.eventos.classes.dao.connection.ConnectionDatabase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String bairro;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public static Endereco buscaEnderecoPorCepDB(String cep){
        
        
        return null;
    
    }
    public static Endereco buscaEnderecoPorCep(String cep){
        String nmpagina = "http://republicavirtual.com.br/web_cep.php?cep=" + cep;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder docBuilder;
        try {
            docBuilder = dbf.newDocumentBuilder();
            Document doc;
            doc = docBuilder.parse(nmpagina);
            Element xmlTag = doc.getDocumentElement();
            Endereco endereco = new Endereco();            
            String resultado = xmlTag.getElementsByTagName("resultado").item(0).getTextContent();
            
            if (resultado.equals("1") || resultado.equals("2")) {
                endereco.setCidade(
                        xmlTag.getElementsByTagName("cidade").item(0).getTextContent());
            }
            
             if (resultado.equals("1")) {
               endereco.setBairro(
                        xmlTag.getElementsByTagName("bairro").item(0).getTextContent());
                endereco.setLogradouro(
                        xmlTag.getElementsByTagName("tipo_logradouro").item(0).getTextContent() + " "
                        + xmlTag.getElementsByTagName("logradouro").item(0).getTextContent());
                endereco.setEstado(
                        xmlTag.getElementsByTagName("uf").item(0).getTextContent());
            }
            if (resultado.equals("0")){
                 return null;
            }

            return endereco;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
