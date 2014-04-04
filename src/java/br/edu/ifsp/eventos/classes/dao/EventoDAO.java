/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.dao;

import br.edu.ifsp.eventos.classes.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bv1110705
 */
public class EventoDAO extends DAO<Evento> {

    public EventoDAO() throws SQLException {
        super();
    }

    @Override
    public void salvar(Evento obj) throws SQLException {
        if (obj.getId() != -1) {
            atualizar(obj);
        } else {
            String sql;
            PreparedStatement stmt;

            sql = "INSERT INTO evento(  nome, descricao, imagem, vagas, horario, data, idPalestrante ) "
                    + "VALUES(  ? , ? , LOAD_FILE(?), ?, ?, ?, ? );";

            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setInt(4, obj.getVagas());
            stmt.setTime(5, obj.getHorario());
            stmt.setDate(6, obj.getData());
            stmt.setLong(7, obj.getPalestrante().getId());


            try {
                stmt.setString(3, getFileNameParaImagemBD(obj.getImagem(), "png"));
                System.out.println(stmt.toString());
                stmt.executeUpdate();

            } catch (Exception ex) {
                Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            stmt.close();

        }
    }

    @Override
    public void atualizar(Evento obj) throws SQLException {
        String sql = "UPDATE evento "
                + "SET "
                + " nome = ?, "
                + " imagem = LOAD_FILE(?), "
                + " descricao = ?, "
                + " vagas = ?, "
                + " horario = ?, "
                + " data = ?, "
                + " idPalestrante = ? "
                + "WHERE id = ? ";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        try {
            stmt.setString(2, getFileNameParaImagemBD(obj.getImagem(), "png"));
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getVagas());
            stmt.setTime(5, obj.getHorario());
            stmt.setDate(6, obj.getData());
            stmt.setLong(7, obj.getPalestrante().getId());

            stmt.setLong(8, obj.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        stmt.close();
    }

    @Override
    public void excluir(Evento obj) throws SQLException {
        String sql = "DELETE FROM evento WHERE id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Evento obterPorId(int id) throws SQLException {
        Evento e = null;
        String sql = "SELECT id, "
                + "nome, "
                + "descricao,"
                + "horario, "
                + "data, "
                + "vagas, "
                + "idPalestrante "
                + "FROM evento WHERE id = ? ";
        PreparedStatement stmt = getConnection().prepareStatement(sql);

        try {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                e = new Evento();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setVagas(rs.getInt("vagas"));
                e.setData(rs.getDate("data"));
                e.setHorario(rs.getTime("horario"));
                e.setPalestrante(new PalestranteDAO().obterPorId(rs.getInt("idPalestrante")));
                e.setImagem(getImagemBD("evento", e.getId(), "imagem"));
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(PalestranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmt.close();

        return e;
    }

    @Override
    public ArrayList<Evento> listarTodos() throws SQLException {
        return listarTodos(true);
    }

    public ArrayList<Evento> listarTodos(boolean inclusiveJaRealizados) throws SQLException {
        ArrayList<Evento> lista = new ArrayList<Evento>();
        Evento e;
        String sql;
        if (inclusiveJaRealizados) {
            sql = "SELECT id, nome, descricao, vagas, data, horario, idPalestrante FROM evento ORDER BY data DESC ";
        } else {
            sql = "SELECT id, nome, descricao, vagas, data, horario, idPalestrante FROM evento WHERE data > now() ORDER BY data ASC ";
        }
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        /*
         * if (!inclusiveJaRealizados) { Calendar calendar = new
         * GregorianCalendar(); java.util.Date date = new java.util.Date();
         * calendar.setTime(date); stmt.setDate(1, new
         * java.sql.Date(calendar.getTime().getTime())); }
         */
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
                e.setPalestrante(new PalestranteDAO().obterPorId(rs.getInt("idPalestrante")));
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

    public void inserirInscrito(Evento ev, Inscrito ic) throws SQLException {

        String sql;
        if (ic instanceof Individuo) {
            sql = "INSERT INTO EventoIndividuo (idEvento, idIndividuo) VALUES ( ?, ? );";
        } else {
            sql = "INSERT INTO EventoInstituicao (idEvento, idInstituicao) VALUES ( ?, ? );";
        }
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
        stmt.setLong(2, ic.getId());
        stmt.executeUpdate();
        stmt.close();

    }

    public void removerInscrito(Evento ev, Inscrito ic) throws SQLException {

        String sql;
        if (ic instanceof Individuo) {
            sql = "DELETE FROM EventoIndividuo WHERE idEvento = ? AND idIndividuo = ?;";
        } else {
            sql = "DELETE FROM EventoInstituicao  WHERE idEvento = ? AND idInstituicao = ? ";
        }
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
        stmt.setLong(2, ic.getId());
        stmt.executeUpdate();
        stmt.close();

    }

    public boolean checarParticipacao(Evento ev, Inscrito ic) throws SQLException {

        String sql;
        if (ic instanceof Individuo) {
            sql = "SELECT * FROM EventoIndividuo WHERE idEvento = ? AND idIndividuo = ?;";
        } else {
            sql = "SELECT * FROM EventoInstituicao WHERE idEvento = ? AND idInstituicao = ? ";
        }
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
        stmt.setLong(2, ic.getId());
        ResultSet rs = stmt.executeQuery();
        boolean ret = rs.next();
        stmt.close();
        return ret;

    }

    public ArrayList<Inscrito> listarTodosParticipantes(Evento ev) throws SQLException {
        ArrayList<Inscrito> lista = new ArrayList<Inscrito>();
        String sql = "SELECT * "
                + "FROM EventoInstituicao ei "
                + "INNER JOIN Instituicao i ON i.id = ei.idInstituicao "
                + "WHERE ei.idEvento = ? "
                + "order by horaInscricao asc";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
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
        rs.close();
        stmt.close();

        sql = "SELECT * FROM EventoIndividuo ei "
                + "INNER JOIN Individuo i "
                + "ON i.id = ei.idIndividuo "
                + "LEFT JOIN IndividuoInstituicao ii "
                + "ON ii.idIndividuo = id "
                + "WHERE ei.idEvento = ? "
                + "order by ei.horaInscricao asc ;";

        stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
        rs = stmt.executeQuery();

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
        return lista;



    }

    public JSONObject listarTodosParticipantesJSON(Evento ev) throws SQLException, JSONException {
        JSONObject jr = new JSONObject();
        String sql = ""
                + "SELECT i.nome, ei.idIndividuo as idInscrito, ei.horaInscricao, 'P' "
                + "  FROM EventoIndividuo ei "
                + "  INNER JOIN Individuo i ON i.id = ei.idIndividuo "
                + "  LEFT JOIN IndividuoInstituicao ii ON ii.idIndividuo = id "
                + "  WHERE idEvento = ? "
                + "UNION "
                + "SELECT ii.nome, eii.idInstituicao as idInscrito, eii.horaInscricao, 'I'"
                + "  FROM EventoInstituicao eii "
                + "  INNER JOIN Instituicao ii ON ii.id = eii.idInstituicao"
                + "  WHERE idEvento = ? "
                + "ORDER BY horainscricao";


        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setLong(1, ev.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            JSONObject j = new JSONObject();
            j.put("nome", rs.getString("nome"));
            j.put("id", rs.getInt("idInscrito"));
            j.put("hInscricao", rs.getTime("horaInscricao"));
            j.put("tipo", rs.getString("P"));
            jr.put(rs.getString("idInscrito"), j);
        }
        return jr;
    }
}
