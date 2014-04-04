/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import br.edu.ifsp.eventos.classes.Evento;
import br.edu.ifsp.eventos.classes.Palestrante;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "img", urlPatterns = {"/img"})
public class Imagem extends HttpServlet {

    private BufferedImage scaleImage(BufferedImage img, int width, int height) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth * height < imgHeight * width) {
            width = imgWidth * height / imgHeight;
        } else {
            height = imgHeight * width / imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();

        response.setHeader("Cache-Control", "public");
        //response.setHeader("Expires","Sat, 01 Dec 2012 16:00:00 GMT");
        try {
            BufferedImage imagemEnviada;
            String cObj = request.getParameter("c");
            String id = request.getParameter("id");

            BufferedImage imagem ;
            if (cObj.equals("palestrante")) {
                imagem = Palestrante.getPalestrantePorID(Long.parseLong(id)).getFoto();
            }else
            if (cObj.equals("evento")) {
                imagem = Evento.getEventoPorID(Integer.parseInt(id)).getImagem();
            }else {
                imagem = new BufferedImage(0, 0, 0);
            }
            Integer sx = imagem.getWidth();
            Integer sy = imagem.getHeight();
            try {
                sx = Integer.parseInt(request.getParameter("sx"));
                sy = Integer.parseInt(request.getParameter("sy"));
            } catch (Exception ex) {
            }
            imagemEnviada = scaleImage(imagem, sx, sy);

            response.setContentType("image/png");
            ImageIO.write(imagemEnviada, "png", out);
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
