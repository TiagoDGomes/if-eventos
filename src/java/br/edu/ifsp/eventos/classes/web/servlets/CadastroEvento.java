/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import br.edu.ifsp.eventos.classes.Evento;
import br.edu.ifsp.eventos.classes.Inscrito;
import br.edu.ifsp.eventos.classes.Palestrante;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "CadastroEvento", urlPatterns = {"/CadastroEvento"})
public class CadastroEvento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        if (Inscrito.getInscritoPorCookies(request.getCookies()).isAdministrador()) {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                out.println("Arquivo não enviado");
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                }
                Iterator itr = items.iterator();
                Evento e = new Evento();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if (name.equals("eventoNome")) {
                            e.setNome(value);
                        }
                        if (name.equals("eventoDetalhes")) {
                            e.setDescricao(value);
                        }
                        if (name.equals("eventoPalestrante")) {
                            e.setPalestrante(Palestrante.getPalestrantePorID(Long.parseLong(value)));
                        }
                        if (name.equals("eventoVagas")) {
                            e.setVagas(Integer.parseInt(value));
                        }
                        if (name.equals("eventoData")) {
                            java.util.Date d = null;
                            try {
                                d = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                            } catch (ParseException ex) {
                                Logger.getLogger(CadastroEvento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            e.setData(new java.sql.Date(d.getTime()));
                        }
                        if (name.equals("eventoHorario")) {
                            java.util.Date d = null;
                            try {
                                d = new SimpleDateFormat("hh:mm").parse(value);
                            } catch (ParseException ex) {
                                Logger.getLogger(CadastroEvento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            e.setHorario(new java.sql.Time(d.getTime()));
                        }


                    } else {
                        //try {
                            InputStream imageStream = item.getInputStream();
                            BufferedImage imageBuffer = ImageIO.read(imageStream);
                            e.setImagem(imageBuffer);

                        //} catch (Exception xe) {
                        //}
                    }
                }
                if (!e.getNome().equals("")) {
                    Evento.adicionar(e);
                    response.sendRedirect("?pagina=admin&secao=cadEventos");

                } else {
                    out.println("Erro.");
                }


            }
        }
        out.close();

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
