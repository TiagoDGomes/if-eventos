//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : eventos
//  @ File Name : Instituicao.java
//  @ Date : 30/04/2012
//  @ Author : Tiago
//
//
package br.edu.ifsp.eventos.classes;

import br.edu.ifsp.eventos.classes.dao.InstituicaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Instituicao extends Inscrito {

    static void removerInstituicao(Instituicao instituicao) throws SQLException {
        new InstituicaoDAO().excluir(instituicao);
    }

    private String cnpj;
    
    public static ArrayList<Instituicao> getListaInstituicao() {
        try {
            //return listaInstituicao;
            return new InstituicaoDAO().listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Instituicao>();
    }
    //Obs: package-private
    static void adicionarInstituicao(Instituicao instituicao){
        try {
            //listaInstituicao.add(instituicao);
            new InstituicaoDAO().salvar(instituicao);
        } catch (SQLException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static void excluirInstituicao(Instituicao instituicao){
        try {
            //listaInstituicao.add(instituicao);
            new InstituicaoDAO().excluir(instituicao);
        } catch (SQLException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    private void adicionarIndividuo(Individuo individuo) {
        
    }

    public void removerIndividuo(Object individuo) {
        
    }

    public int getTotalMembros(){
        try {
            return new InstituicaoDAO().getTotalMembros(this);
        } catch (SQLException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public ArrayList<Individuo> getMembros() {
        ArrayList<Individuo> lista = new ArrayList<Individuo>();
        try {
            lista = new InstituicaoDAO().listarMembros(this);
        } catch (SQLException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
