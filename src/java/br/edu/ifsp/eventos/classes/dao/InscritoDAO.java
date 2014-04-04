/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.dao;

import br.edu.ifsp.eventos.classes.Inscrito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author bv1110705
 */
public class InscritoDAO extends DAO<Inscrito> {

    public InscritoDAO() throws SQLException {
        super();
    }

    @Override
    public void salvar(Inscrito obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Inscrito obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluir(Inscrito obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Inscrito obterPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Inscrito obterPorLoginSenha(String login, String senha) throws SQLException {
        Inscrito ic = null;
        String sql = "SELECT id FROM individuo where email = ? and senha = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            ic = new IndividuoDAO().obterPorId(id);
        } else {
            sql = "SELECT id FROM instituicao where email = ? and senha = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
                int id = rs2.getInt("id");
                ic = new InstituicaoDAO().obterPorId(id);
            }
            rs2.close();
        }
        rs.close();
        stmt.close();

        return ic;
    }

    public Inscrito obterPorCookie(Cookie c) throws SQLException {
        Inscrito ic = null;
        String sql = "SELECT id FROM individuo where cookie = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, c.getValue());
        ResultSet rs = stmt.executeQuery();
        System.out.println("obterPorCookie: é individuo?");            
        if (rs.next()) {
            
            int id = rs.getInt("id");
            System.out.println("obterPorCookie: sim. é individuo: id " + id);
            ic = new IndividuoDAO().obterPorId(id);
        } else {
            System.out.println("obterPorCookie: não é individuo... instituicao?");
            
            sql = "SELECT id FROM instituicao where cookie = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, c.getValue());
            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
                int id = rs2.getInt("id");
                System.out.println("obterPorCookie: sim. é instituicao: id " + id);
            
                ic = new InstituicaoDAO().obterPorId(id);
            }
            rs2.close();
        }
        rs.close();
        stmt.close();

        return ic;
    }

    @Override
    public ArrayList<Inscrito> listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
