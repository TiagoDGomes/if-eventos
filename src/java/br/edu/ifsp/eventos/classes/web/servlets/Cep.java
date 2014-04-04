
package br.edu.ifsp.eventos.classes.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "Cep", urlPatterns = {"/cep"})
public class Cep extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String nmpagina = "http://republicavirtual.com.br/web_cep.php?cep=" + request.getParameter("cep");
            HttpURLConnection conexao = null;
            URL url = new URL(nmpagina);

            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Request-Method", "GET");
            conexao.setDoInput(true);
            conexao.setDoOutput(false);
            conexao.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuffer newData = new StringBuffer(10000);
            String s = "";
            while (null != ((s = br.readLine()))) {
                newData.append(s);
            }
            br.close();
            out.println(new String(newData));
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
