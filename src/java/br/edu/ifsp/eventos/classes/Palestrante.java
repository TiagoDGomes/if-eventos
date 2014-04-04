//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Palestrante.java
//  @ Date : 30/04/2012
//  @ Author : 
//
//
package br.edu.ifsp.eventos.classes;

import br.edu.ifsp.eventos.classes.dao.PalestranteDAO;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Palestrante {

    private static ArrayList<Palestrante> listaPalestrante = new ArrayList();

    public static void remover(Palestrante p) throws SQLException {
        new PalestranteDAO().excluir(p);
    }
    private long id = -1;
    private static long idCount;
    private String nome = "";
    private BufferedImage foto = null;
    private String descricao = "";

    public static Palestrante getPalestrantePorID(long id) {
        /*
         * for (Palestrante p : Palestrante.getListaPalestrantes()) { if
         * (p.getId() == id) { return p; } } return null;
         */
        try {

            PalestranteDAO po = new PalestranteDAO();
            return po.obterPorId((int)id);
        } catch (SQLException ex) {
            Logger.getLogger(Palestrante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BufferedImage getFoto() {
        return foto;
    }

    public void setFoto(BufferedImage foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void adicionar(Palestrante p) throws SQLException {

        PalestranteDAO po = new PalestranteDAO();
        po.salvar(p);
    }

    public static Iterator<Palestrante> getPalestrantes() throws SQLException {
        return getListaPalestrantes().iterator();
    }

    public static ArrayList<Palestrante> getListaPalestrantes() throws SQLException  {
        
            return new PalestranteDAO().listarTodos();
        
    }
    public ArrayList<Evento> getEventos(){
        try {
            return new PalestranteDAO().listarEventos(this);
        } catch (SQLException ex) {
            
        }
        return new ArrayList<Evento>();
        
    }
    public int getTotalEventos() {
        return getEventos().size();
    }
}