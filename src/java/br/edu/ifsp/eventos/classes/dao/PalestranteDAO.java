/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.dao;

import br.edu.ifsp.eventos.classes.Evento;
import br.edu.ifsp.eventos.classes.Palestrante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bv1110705
 */
public class PalestranteDAO extends DAO<Palestrante> {

    public PalestranteDAO() throws SQLException {
        super();
    }


    @Override
    public void salvar(Palestrante obj) throws SQLException {
        if (obj.getId()!= -1){
            atualizar(obj);
        } else {
            String sql;
            PreparedStatement stmt;

            sql = "INSERT INTO Palestrante(  nome, descricao, foto ) "
                    + "VALUES(  ? , ? , LOAD_FILE(?) );";
            
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());

            try {
                stmt.setString(3, getFileNameParaImagemBD(obj.getFoto(),"png"));
                System.out.println(stmt.toString());
                stmt.executeUpdate();

            } catch (Exception ex) {
                Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            stmt.close();
        }
            
    }

    
    
    
    
    @Override
    public void atualizar(Palestrante obj) throws SQLException {
        String sql = "UPDATE Palestrante "
                + "SET "
                + " nome = ? "
                + " foto = LOAD_FILE(?) "
                + " descricao = ? "
                + "WHERE id = ? ";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        try {
            stmt.setString(2, getFileNameParaImagemBD(obj.getFoto(), "png"));
            stmt.setString(3, obj.getDescricao());
            stmt.setLong(4, obj.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        stmt.close();
    }

    
    
    
    
    
    
    @Override
    public void excluir(Palestrante obj) throws SQLException {
        String sql = "DELETE FROM Palestrante WHERE id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }
    
    
    public ArrayList<Evento> listarEventos(Palestrante obj) throws SQLException{
        ArrayList<Evento> lista = new ArrayList<Evento>();
        Evento e;
        String sql = "SELECT id, nome, descricao, vagas, data, horario,idPalestrante FROM Evento where idPalestrante = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, obj.getId());
        
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                e = new Evento();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setVagas(rs.getInt("vagas"));
                e.setData(rs.getDate("data"));
                e.setHorario(rs.getTime("horario"));
                e.setPalestrante(obj);
                e.setImagem(getImagemBD("evento", e.getId(), "imagem"));
                lista.add(e);
                
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        stmt.close();


        return lista;
        
    }
    
    

    @Override
    public ArrayList<Palestrante> listarTodos() throws SQLException {
        ArrayList<Palestrante> lista = new ArrayList<Palestrante>();
        Palestrante p;
        String sql = "SELECT id, nome, descricao FROM Palestrante";
        PreparedStatement stmt = getConnection().prepareStatement(sql);

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new Palestrante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setFoto(getImagemBD("Palestrante", p.getId(), "foto"));
                lista.add(p);
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmt.close();


        return lista;
    }


    
    
    
    
    

    @Override
    public Palestrante obterPorId(int id) throws SQLException {

        Palestrante p = null;
        String sql = "SELECT id, nome, descricao FROM palestrante WHERE id = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);

        try {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Palestrante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setFoto(getImagemBD("Palestrante", p.getId(), "foto"));

            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmt.close();

        return p;
    }
}
