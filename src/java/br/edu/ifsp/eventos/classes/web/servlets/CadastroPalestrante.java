/**
 * Servlet responsavel pelo recebimento dos dados do palestrante
 *
 * @author Tiago
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import br.edu.ifsp.eventos.classes.Inscrito;
import br.edu.ifsp.eventos.classes.Palestrante;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "CadastroPalestrante", urlPatterns = {"/CadastroPalestrante"})
public class CadastroPalestrante extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");


        PrintWriter out = response.getWriter();
        if (Inscrito.getInscritoPorCookies(request.getCookies()).isAdministrador()) {

            try {
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                if (!isMultipart) {
                    out.println("Arquivo não enviado");
                } else {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List items = upload.parseRequest(request);
                    Iterator itr = items.iterator();
                    Palestrante palestrante = new Palestrante();
                    while (itr.hasNext()) {
                        FileItem item = (FileItem) itr.next();
                        if (item.isFormField()) {
                            String name = item.getFieldName();
                            String value = item.getString();
                            if (name.equals("palestranteNome")) {
                                palestrante.setNome(value);
                            }
                            if (name.equals("palestranteDescricao")) {
                                palestrante.setDescricao(value);
                            }
                        } else {
                            InputStream imageStream = item.getInputStream();
                            BufferedImage imageBuffer = ImageIO.read(imageStream);
                            palestrante.setFoto(imageBuffer);
                        }
                    }
                    if (!palestrante.getNome().equals("")) {
                        Palestrante.adicionar(palestrante);
                        response.sendRedirect("?pagina=admin&secao=cadPalestrantes");
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(CadastroPalestrante.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                response.sendRedirect("?pagina=admin&secao=cadPalestrantes&erro");
            } finally {
                out.close();
            }
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
