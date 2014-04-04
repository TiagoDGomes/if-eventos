/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import br.edu.ifsp.eventos.classes.Evento;
import br.edu.ifsp.eventos.classes.Inscrito;
import br.edu.ifsp.eventos.classes.Instituicao;
import br.edu.ifsp.eventos.classes.dao.EventoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "ParticiparEvento", urlPatterns = {"/ParticiparEvento"})
public class ParticiparEvento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {
            Cookie c = null;
            for (Cookie ct : request.getCookies()) {
                if ("userId".equals(ct.getName())) {
                    c = ct;
                    break;
                }
            }
            if (c != null) {
                Inscrito ic = Inscrito.getInscritoPorCookie(c);
                if (ic != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Evento ev = new EventoDAO().obterPorId(id);
                    JSONObject jSend = new JSONObject();
                    if (request.getParameter("participar") != null) {
                        if (request.getParameter("participar").equals("sim")) {
                            ev.adicionarInscrito(ic);
                            try {jSend.put("voce", "1");} catch (JSONException ex) {}
                        } else {
                            ev.removerInscrito(ic);
                            try {jSend.put("voce", "0");} catch (JSONException ex) {}
                        }
                    }

                    ArrayList<String> inst = new ArrayList<String>();
                    ArrayList<String> ind = new ArrayList<String>();
                    for (Inscrito icc : ev.getListaInscritos()) {
                        if (icc instanceof Instituicao) {
                            inst.add(icc.getNome());
                        } else {
                            ind.add(icc.getNome());
                        }
                    }
                    try {
                        jSend.put("instituicao", inst);
                        jSend.put("pessoa", ind);
                        jSend.put("instituicao_total", inst.size());
                        jSend.put("pessoa_total", ind.size());
                        response.setContentType("application/json");
                        out.println(jSend.toString());
                    } catch (JSONException ex) {
                        Logger.getLogger(ParticiparEvento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ParticiparEvento.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
