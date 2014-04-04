/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.dao;

import br.edu.ifsp.eventos.classes.Individuo;
import br.edu.ifsp.eventos.classes.Instituicao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.swing.JOptionPane;

/**
 *
 * @author bv1110705
 */
public class InstituicaoDAO extends DAO<Instituicao> {

    public InstituicaoDAO() throws SQLException {
        super();
    }

    @Override
    public void salvar(Instituicao obj) throws SQLException {
        if (obj.getId()!=-1){
            atualizar(obj);
        } else {
            String sql = "INSERT INTO instituicao( cnpj, nome, email, endereco, numero, bairro, cep, cidade, estado, senha, cookie,administrador ) "
                    + "VALUES( ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ? );";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, obj.getCnpj());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getEndereco().getLogradouro());
            stmt.setString(5, obj.getEndereco().getNumero());
            stmt.setString(6, obj.getEndereco().getBairro());
            stmt.setString(7, obj.getEndereco().getCep());
            stmt.setString(8, obj.getEndereco().getCidade());
            stmt.setString(9, obj.getEndereco().getEstado());
            stmt.setString(10, obj.getSenha());
            stmt.setString(11, obj.getCookie().getValue());
            stmt.setInt(12, obj.isAdministrador() ? 1 : 0);        
            try {
                stmt.executeUpdate();
            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
            stmt.close();
        
        }
        
    }

    @Override
    public void atualizar(Instituicao obj) throws SQLException {
        String sql = "UPDATE instituicao "
                + "SET "
                + "    cnpj = ?, "
                + "    nome = ?, "
                + "    email = ?, "
                + "    endereco = ?, "
                + "    numero = ?, "
                + "    bairro = ?, "
                + "    cep = ?, "
                + "    cidade = ?, "
                + "    estado = ?, "
                + "    senha = ?,"
                + "    cookie = ?"
                + "WHERE"
                + "    id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getCnpj());
        stmt.setString(2, obj.getNome());
        stmt.setString(3, obj.getEmail());
        stmt.setString(4, obj.getEndereco().getLogradouro());
        stmt.setString(5, obj.getEndereco().getNumero());
        stmt.setString(6, obj.getEndereco().getBairro());
        stmt.setString(7, obj.getEndereco().getCep());
        stmt.setString(8, obj.getEndereco().getCidade());
        stmt.setString(9, obj.getEndereco().getEstado());
        stmt.setString(10, obj.getSenha());
        stmt.setString(11, obj.getCookie().getValue());
        stmt.setLong(12, obj.getId());
        try {
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.getMessage());
        }
        stmt.close();
    }

    @Override
    public void excluir(Instituicao obj) throws SQLException {
        String sql = "DELETE FROM instituicao WHERE id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Instituicao obterPorId(int id) throws SQLException {
        String sql = "SELECT * FROM instituicao where id = ?;";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        Instituicao p = null;
        if (rs.next()) {
            p = new Instituicao();
            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setSenha(rs.getString("senha"));
            p.setEmail(rs.getString("email"));
            p.setCnpj(rs.getString("cnpj"));
            p.getEndereco().setLogradouro(rs.getString("endereco"));
            p.getEndereco().setNumero(rs.getString("numero"));
            p.getEndereco().setBairro(rs.getString("bairro"));
            p.getEndereco().setCep(rs.getString("cep"));
            p.getEndereco().setCidade(rs.getString("cidade"));
            p.getEndereco().setEstado(rs.getString("estado"));
            p.setAdministrador(rs.getBoolean("administrador"));
            Cookie c = new Cookie("userId", rs.getString("cookie"));
           
            p.setCookie(c);

        }

        rs.close();
        stmt.close();
        return p;
    }

    @Override
    public ArrayList<Instituicao> listarTodos() throws SQLException {
        ArrayList<Instituicao> lista = new ArrayList<Instituicao>();
        String sql = "SELECT * FROM Instituicao;";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Instituicao p = new Instituicao();
            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setSenha(rs.getString("senha"));
            p.setEmail(rs.getString("email"));
            p.setCnpj(rs.getString("cnpj"));
            p.getEndereco().setLogradouro(rs.getString("endereco"));
            p.getEndereco().setNumero(rs.getString("numero"));
            p.getEndereco().setBairro(rs.getString("bairro"));
            p.getEndereco().setCep(rs.getString("cep"));
            p.getEndereco().setCidade(rs.getString("cidade"));
            p.getEndereco().setEstado(rs.getString("estado"));
            p.setAdministrador(rs.getBoolean("administrador"));
            Cookie c = new Cookie("userId", rs.getString("cookie"));
            p.setCookie(c);

            lista.add(p);

        }
        return lista;
    }
    public int getTotalMembros(Instituicao ins) throws SQLException{
        String sql = "SELECT COUNT(idIndividuo) as total FROM individuoinstituicao WHERE idInstituicao = ? ;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ins.getId());        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            return rs.getInt("total");
        }
        return 0;

    }
    public ArrayList<Individuo> listarMembros(Instituicao ins) throws SQLException {
        ArrayList<Individuo> lista = new ArrayList<Individuo>();
        String sql = "SELECT id,nome,senha,email,cpf,endereco,cidade,estado,cookie,numero,bairro,cep FROM individuo "
                + " INNER JOIN individuoinstituicao "
                + " WHERE id = idindividuo "
                + " AND idinstituicao =? ;";



        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ins.getId());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Individuo p = new Individuo();
            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setSenha(rs.getString("senha"));
            p.setEmail(rs.getString("email"));
            p.setCpf(rs.getString("cpf"));
            p.setInstituicao(ins);
            p.getEndereco().setLogradouro(rs.getString("endereco"));
            p.getEndereco().setNumero(rs.getString("numero"));
            p.getEndereco().setBairro(rs.getString("bairro"));
            p.getEndereco().setCep(rs.getString("cep"));
            p.getEndereco().setCidade(rs.getString("cidade"));
            p.getEndereco().setEstado(rs.getString("estado"));
            p.setAdministrador(false);
            Cookie c = new Cookie("userId", rs.getString("cookie"));
            p.setCookie(c);
            
            lista.add(p);

        }

        rs.close();
        stmt.close();

        return lista;
    }
    
    public void adicionarIndividuo(Instituicao it, Individuo ind) throws SQLException{
        String sql = "INSERT INTO individuoinstituicao( idInstituicao, idIndividuo ) "
                    + "VALUES( ?, ? );";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, it.getId());
        stmt.setLong(2, ind.getInstituicao().getId());
        stmt.executeUpdate();

        stmt.close();
    }
}
