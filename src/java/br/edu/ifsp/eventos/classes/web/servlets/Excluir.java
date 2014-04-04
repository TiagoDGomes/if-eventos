/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import br.edu.ifsp.eventos.classes.*;
import br.edu.ifsp.eventos.classes.dao.EventoDAO;
import br.edu.ifsp.eventos.classes.dao.IndividuoDAO;
import br.edu.ifsp.eventos.classes.dao.InstituicaoDAO;
import br.edu.ifsp.eventos.classes.dao.PalestranteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "ExcluirInstituicao", urlPatterns = {"/xi"})
public class Excluir extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (Inscrito.getInscritoPorCookies(request.getCookies()).isAdministrador()) {
                if (request.getParameter("tipo").equals("it")) {
                    Instituicao it = new InstituicaoDAO().obterPorId(Integer.parseInt(request.getParameter("id")));
                    Inscrito.remover(it);
                    response.sendRedirect("?pagina=admin&secao=cadInstituicao");
                } else if (request.getParameter("tipo").equals("id")) {
                    Individuo id = new IndividuoDAO().obterPorId(Integer.parseInt(request.getParameter("id")));
                    Inscrito.remover(id);
                    response.sendRedirect("?pagina=admin&secao=cadInscritos");
                } else if (request.getParameter("tipo").equals("pl")) {
                    Palestrante p = new PalestranteDAO().obterPorId(Integer.parseInt(request.getParameter("id")));
                    Palestrante.remover(p);
                    response.sendRedirect("?pagina=admin&secao=cadPalestrantes");
                } else if (request.getParameter("tipo").equals("ev")) {
                    Evento e = new EventoDAO().obterPorId(Integer.parseInt(request.getParameter("id")));
                    Evento.remover(e);
                    response.sendRedirect("?pagina=admin&secao=cadEventos");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
