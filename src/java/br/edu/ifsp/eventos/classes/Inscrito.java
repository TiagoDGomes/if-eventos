//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : eventos
//  @ File Name : Inscrito.java
//  @ Date : 30/04/2012
//  @ Author : Tiago
//
//
package br.edu.ifsp.eventos.classes;

import br.edu.ifsp.eventos.classes.dao.InscritoDAO;
import br.edu.ifsp.eventos.classes.util.Endereco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

public abstract class Inscrito {

    private long id = -1;
    private String nome = "";
    private String email = "";
    private String senha = "";
    private Endereco endereco = new Endereco();
    private boolean administrador = false;
    private Cookie cookie;



    public static Inscrito getInscritoPorCookies(Cookie cc[]) {
        Cookie c = new Cookie("userID", "");
        for (Cookie x : cc) {
            if (x.getName().equals("userId")) {
                c = x;
                break;
            }
        }
        return Inscrito.getInscritoPorCookie(c);

    }

    public static Inscrito getInscritoPorCookie(Cookie c) {
        try {
            return new InscritoDAO().obterPorCookie(c);
        } catch (SQLException ex) {
            Logger.getLogger(Inscrito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Inscrito getInscritoLoginSenha(String login, String senha) {
        try {
            return new InscritoDAO().obterPorLoginSenha(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(Inscrito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        /*
         * for (Inscrito x : Inscrito.getListaInscrito()) { if (x != null) { if
         * (x.getEmail().equals(login)) { if (x.getSenha().equals(senha)) {
         * return x; } } } } return null;
         */
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public static ArrayList<Inscrito> getListaInscrito() {
        ArrayList<Inscrito> lst = new ArrayList<Inscrito>();
        lst.addAll(Instituicao.getListaInstituicao());
        lst.addAll(Individuo.getListaIndividuo());
        return lst;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static void adicionar(Inscrito inscrito) {
        if (inscrito instanceof Individuo) {
            System.out.println("adicionar: adicionando individuo com id " + inscrito.getId());
            Individuo.adicionarIndividuo((Individuo) inscrito);
        } else if (inscrito instanceof Instituicao) {
            System.out.println("Adicionando instituicao...");
            Instituicao.adicionarInstituicao((Instituicao) inscrito);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void remover(Inscrito inscrito) {
        try {
            if (inscrito instanceof Individuo) {
                Individuo.removerIndividuo((Individuo) inscrito);
            } else if (inscrito instanceof Instituicao) {
                Instituicao.removerInstituicao((Instituicao) inscrito);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inscrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}