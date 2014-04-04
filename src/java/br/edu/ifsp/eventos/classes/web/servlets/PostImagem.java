/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Session;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "PostImagem", urlPatterns = {"/PostImagem"})
public class PostImagem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(
                request);
        out.println("request: " + request);
        if (!isMultipart) {
            out.println("File Not Uploaded");
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            try {
                items = upload.parseRequest(request);
                out.println("items: " + items);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    out.println("name: " + name);
                    String value = item.getString();
                    out.println("value: " + value);
                } else {
                    try {
                        String itemName = item.getName();
                        Random generator = new Random();
                        int r = Math.abs(generator.nextInt());

                        String reg = "[.*]";
                        String replacingtext = "";
                        out.println("Text before replacing is:-"
                                + itemName);
                        Pattern pattern = Pattern.compile(reg);
                        Matcher matcher = pattern.matcher(itemName);
                        StringBuffer buffer = new StringBuffer();

                        while (matcher.find()) {
                            matcher.appendReplacement(buffer, replacingtext);
                        }
                        int IndexOf = itemName.indexOf(".");
                        String domainName = itemName.substring(IndexOf);
                        out.println("domainName: " + domainName);

                        String finalimage = buffer.toString() + "_" + r + domainName;
                        out.println("Final Image===" + finalimage);

                        File savedFile = new File(request.getSession().getServletContext().getRealPath(finalimage));
                        item.write(savedFile);
                        out.println("<html>");
                        out.println("<body>");
                        out.println("<table><tr><td>");
                        out.println("<img src=images/" + finalimage + ">");
                        out.println("</td></tr></table>");

                        Connection conn = null;
                        String url = "jdbc:mysql://localhost:3306/";
                        String dbName = "test";
                        String driver = "com.mysql.jdbc.Driver";
                        String username = "root";
                        String userPassword = "root";
                        String strQuery = null;
                        String strQuery1 = null;
                        String imgLen = "";

                        try {
                            out.println("itemName::::: " + itemName);
                            Class.forName(driver).newInstance();
                            conn = (Connection) DriverManager.getConnection(url + dbName, username,
                                    userPassword);
                            Statement st = (Statement) conn.createStatement();
                            strQuery = "insert into testimage set image='"
                                    + finalimage + "'";
                            int rs = st.executeUpdate(strQuery);
                            out.println("Query Executed    Successfully++++++++++++++                            ");
                            out.println("image inserted successfully");
                            out.println("</body>");
                            out.println("</html>");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
