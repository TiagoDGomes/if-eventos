/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.dao;

import br.edu.ifsp.eventos.classes.Individuo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author bv1110705
 */
public class IndividuoDAO extends DAO<Individuo> {

    public IndividuoDAO() throws SQLException {
        super();
    }

    @Override
    public void salvar(Individuo obj) throws SQLException {
        if (obj.getId() != -1) {
            System.out.println("dao.salvar: atualizando individuo com id " + obj.getId());            
            atualizar(obj);
        } else {
            System.out.println("dao.salvar: inserindo individuo com id " + obj.getId());            
            String sql = "INSERT INTO individuo( cpf, nome, email, endereco, numero, bairro, cep, cidade, estado,senha, cookie,administrador ) "
                    + "VALUES( ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ? );";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getEmail());
            System.out.println("salvar: endereco: "+obj.getEndereco().getLogradouro());
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
                atualizar(obj);
            }
            stmt.close();
            
            sql = "select max(id) from individuo";
            stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            obj.setId(rs.getLong(1));
            stmt.close();
            if (obj.getInstituicao() != null) {
                sql = "INSERT INTO individuoinstituicao( idIndividuo, idInstituicao ) "
                        + "VALUES( ?, ? );";
                stmt = getConnection().prepareStatement(sql);
                stmt.setLong(1, obj.getId());
                stmt.setLong(2, obj.getInstituicao().getId());
                stmt.executeUpdate();

                stmt.close();

            }
        }
    }

    @Override
    public void atualizar(Individuo obj) throws SQLException {
        String sql = "UPDATE individuo "
                + "SET "
                + "    cpf = ?, "
                + "    nome = ?, "
                + "    email = ?, "
                + "    endereco = ?, "
                + "    numero = ?, "
                + "    bairro = ?, "
                + "    cep = ?, "
                + "    cidade = ?, "
                + "    estado = ?, "
                + "    senha = ?, "
                + "    cookie = ? "
                + "WHERE"
                + "    id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getCpf());
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
        System.out.println("atualizar: stmt: "+stmt);
        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir(Individuo obj) throws SQLException {
        String sql = "DELETE FROM individuo WHERE id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public ArrayList<Individuo> listarTodos() throws SQLException {
        ArrayList<Individuo> lista = new ArrayList<Individuo>();
        String sql = "SELECT * FROM Individuo left join individuoinstituicao ON id = idindividuo;";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Individuo p = new Individuo();
            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setSenha(rs.getString("senha"));
            p.setEmail(rs.getString("email"));
            p.setCpf(rs.getString("cpf"));
            p.getEndereco().setLogradouro(rs.getString("endereco"));
            p.getEndereco().setNumero(rs.getString("numero"));
            p.getEndereco().setBairro(rs.getString("bairro"));
            p.getEndereco().setCep(rs.getString("cep"));
            p.getEndereco().setCidade(rs.getString("cidade"));
            p.getEndereco().setEstado(rs.getString("estado"));
            if (rs.getString("idinstituicao") != null) {
                p.setInstituicao(new InstituicaoDAO().obterPorId(rs.getInt("idinstituicao")));
            }
            p.setAdministrador(rs.getBoolean("administrador"));
            Cookie c = new Cookie("userId", rs.getString("cookie"));
            p.setCookie(c);

            lista.add(p);

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Individuo obterPorId(int id) throws SQLException {

        String sql = "SELECT * FROM Individuo where id = ?;";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        Individuo p = null;
        if (rs.next()) {
            p = new Individuo();
            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setSenha(rs.getString("senha"));
            p.setEmail(rs.getString("email"));
            p.setCpf(rs.getString("cpf"));
            p.getEndereco().setLogradouro(rs.getString("endereco"));
             p.getEndereco().setNumero(rs.getString("numero"));
            p.getEndereco().setBairro(rs.getString("bairro"));
            p.getEndereco().setCep(rs.getString("cep"));
            p.getEndereco().setCidade(rs.getString("cidade"));
            p.getEndereco().setEstado(rs.getString("estado"));
            p.setAdministrador(rs.getBoolean("administrador"));
            System.out.println("obter por id: numeroendereco: "+p.getEndereco().getEstado());
            Cookie c = new Cookie("userId", rs.getString("cookie"));
            p.setCookie(c);

        }

        rs.close();
        stmt.close();
        return p;
    }
}
