/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes.web.servlets.obsoleto;

import br.edu.ifsp.eventos.classes.Individuo;
import br.edu.ifsp.eventos.classes.Inscrito;
import br.edu.ifsp.eventos.classes.Instituicao;
import br.edu.ifsp.eventos.classes.dao.InstituicaoDAO;
import br.edu.ifsp.eventos.classes.util.Endereco;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Tiago
 */
class Erro {
    //<editor-fold defaultstate="collapsed" desc="Classe para armazenamento de erros">

    protected boolean nome = false;
    protected boolean cpf = false;
    protected boolean cep = false;
    protected boolean cnpj = false;
    protected boolean email = false;
    protected boolean senha = false;
    protected boolean endereco = false;
    protected boolean cidade = false;
    protected boolean bairro = false;
    protected boolean numero = false;
    protected boolean confirma = false;

    public boolean isCnpj() {
        return cnpj;
    }

    public void setCnpj(boolean cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isCep() {
        return cep;
    }

    public void setCep(boolean cep) {
        this.cep = cep;
        this.confirma = true;
    }

    public boolean isBairro() {
        return bairro;
    }

    public void setBairro(boolean bairro) {
        this.bairro = bairro;
        this.confirma = true;
    }

    public boolean isCidade() {
        return cidade;
    }

    public void setCidade(boolean cidade) {
        this.cidade = cidade;
        this.confirma = true;
    }

    public boolean isConfirma() {
        return confirma;
    }

    public void setConfirma(boolean confirma) {
        this.confirma = confirma;
        this.confirma = true;
    }

    public boolean isCpf() {
        return cpf;
    }

    public void setCpf(boolean cpf) {
        this.cpf = cpf;
        this.confirma = true;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
        this.confirma = true;
    }

    public boolean isEndereco() {
        return endereco;
    }

    public void setEndereco(boolean endereco) {
        this.endereco = endereco;
        this.confirma = true;
    }

    public boolean isNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
        this.confirma = true;
    }

    public boolean isNumero() {
        return numero;
    }

    public void setNumero(boolean numero) {
        this.numero = numero;
        this.confirma = true;
    }

    public boolean isSenha() {
        return senha;
    }

    public void setSenha(boolean senha) {
        this.senha = senha;
        this.confirma = true;
    }

    public String getJSON() {
        JSONObject j = new JSONObject();

        try {
            if (this.bairro) {
                j.put("bairro", 1);
            }
            if (this.cep) {
                j.put("cep", 1);
            }
            if (this.cidade) {
                j.put("cidade", 1);
            }
            if (this.cpf) {
                j.put("cpf", 1);
            }
            if (this.cnpj) {
                j.put("cnpj", 1);
            }
            if (this.email) {
                j.put("email", 1);
            }
            if (this.endereco) {
                j.put("endereco", 1);
            }
            if (this.nome) {
                j.put("nome", 1);
            }
            if (this.numero) {
                j.put("numero", 1);
            }
            if (this.senha) {
                j.put("senha", 1);
            }
            if (this.confirma) {
                j.put("confirma", 1);
            } else {
                j.put("confirma", 0);
            }
        } catch (Exception e) {
        }

        return j.toString();
    }

    public String getURL() {
        String url = "";
        if (this.bairro) {
            url += "&bairro=1";
        }
        if (this.cep) {
            url += "&cep=1";
        }
        if (this.cidade) {
            url += "&cidade=1";
        }
        if (this.cpf) {
            url += "&cpf=1";
        }
        if (this.cnpj) {
            url += "&cnpj=1";
        }
        if (this.email) {
            url += "&email=1";
        }
        if (this.endereco) {
            url += "&endereco=1";
        }
        if (this.nome) {
            url += "&nome=1";
        }
        if (this.numero) {
            url += "&numero=1";
        }
        if (this.senha) {
            url += "&senha=1";
        }

        return url;
    }
    //</editor-fold>
}

@WebServlet(name = "Cadastro", urlPatterns = {"/CadastroInscrito"})
public class CadastroInscrito extends HttpServlet {

    private String r(String r) {
        if (r != null) {
            return r;
        }
        return "";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie c = new Cookie("userId", request.getParameter("h"));
        PrintWriter out = response.getWriter();
        boolean json = false;

        if (request.getParameter("json") != null) {
            json = true;
        }
        Erro err = new Erro();
        try {



            if (r(request.getParameter("nome")).equals("")) {
                err.setNome(true);
            }
            if (r(request.getParameter("tipo")).equals("Individuo")) {
                if (r(request.getParameter("cpf")).equals("")) {
                    err.setCpf(true);
                }
                if (r(request.getParameter("cep")).equals("")) {
                    err.setCep(true);
                }
                if (r(request.getParameter("endereco")).equals("")) {
                    err.setEndereco(true);
                }
                if (r(request.getParameter("cidade")).equals("")) {
                    err.setCidade(true);
                }
                if (r(request.getParameter("bairro")).equals("")) {
                    err.setBairro(true);
                }
            }
            if (r(request.getParameter("tipo")).equals("Instituicao")) {
                //if (request.getParameter("cnpj").equals("")) {
                //err.setCnpj(true); 
                //    cnpj = "";
                //}
            }
            if (r(request.getParameter("email")).equals("")) {
                err.setEmail(true);
            }
            if (r(request.getParameter("senha")).equals("")) {
                err.setSenha(true);
            }




            if (!err.isConfirma()) {

                
                System.out.println("processRequest: sem erros...");
                if (r(request.getParameter("tipo")).equals("Individuo")) {
                    System.out.println("processRequest: individuo.");
                
                    Individuo inscrito;
                    if (!r(request.getParameter("i")).equals("")) {
                        System.out.println("Pegando individuo por cookie...");
                        inscrito = (Individuo) Inscrito.getInscritoPorCookies(request.getCookies());
                    } else {
                        System.out.println("new Individuo...");
                        inscrito = new Individuo();
                        System.out.println("novo individuo com id " + inscrito.getId());
                    }

                    inscrito.setCpf(r(request.getParameter("cpf")));


                    if (r(request.getParameter("idi")).equals("")) {
                        inscrito.setInstituicao(null);
                        response.addCookie(c);
                    } else {
                        Instituicao it = new InstituicaoDAO().obterPorId(Integer.parseInt(request.getParameter("idi")));
                        inscrito.setInstituicao(it);
                    }
                    inscrito.setHashCode(r(request.getParameter("h")));
                    if (!r(request.getParameter("i")).equals("")) {
                        inscrito.setId(Long.parseLong(r(request.getParameter("i"))));
                    }

                    Endereco e = new Endereco();
                    e.setBairro(r(request.getParameter("bairro")));
                    e.setCep(r(request.getParameter("cep")));
                    e.setLogradouro(r(request.getParameter("endereco")));
                    e.setCidade(r(request.getParameter("cidade")));
                    e.setNumero(r(request.getParameter("numeroEndereco")));
                    e.setEstado(r(request.getParameter("uf")));
                    inscrito.setCookie(c);

                    inscrito.setNome(r(request.getParameter("nome")));
                    inscrito.setEmail(r(request.getParameter("email")));
                    inscrito.setEndereco(e);
                    inscrito.setSenha(r(request.getParameter("senha")));
                    System.out.println("adicionando individuo com id " + inscrito.getId());
                    Individuo.adicionar(inscrito);


                } else {
                    Instituicao inscrito = new Instituicao();
                    
                    if (!r(request.getParameter("i")).equals("")) {                        
                        inscrito.setId(Long.parseLong(r(request.getParameter("i"))));
                    }
                    Endereco e = new Endereco();
                    e.setBairro(r(request.getParameter("bairro")));
                    e.setCep(r(request.getParameter("cep")));
                    e.setLogradouro(r(request.getParameter("endereco")));
                    e.setCidade(r(request.getParameter("cidade")));
                    e.setNumero(r(request.getParameter("numeroEndereco")));
                    e.setEstado(r(request.getParameter("uf")));
                    inscrito.setEndereco(e);
                    inscrito.setCnpj(r(request.getParameter("cnpj")));
                    inscrito.setNome(r(request.getParameter("nome")));
                    inscrito.setEmail(r(request.getParameter("email")));
                    inscrito.setSenha(r(request.getParameter("senha")));
                    inscrito.setCookie(c);
                    response.addCookie(c);
                    Instituicao.adicionar(inscrito);
                }





            }else {
                response.sendRedirect("?pagina=cadastro&erro=1&confirma=1" + err.getURL());
            }

        } catch (Exception ex) {
            response.sendRedirect("?pagina=admin&secao=cadInstituicao&erro=cadastroI&" + err.getURL());
            ex.printStackTrace();
        } finally {
            System.out.println("finish...");
            if (json) {
                System.out.println("json..");
                response.setContentType("application/json");
                out.println(err.getJSON());
            } else {
                System.out.println("processRequest: redirect...");
                if (r(request.getParameter("tipo")).equals("Individuo")) {
                    System.out.println("processRequest: é individuo.");
                    if (err.getURL().equals("")) {
                        response.sendRedirect("?pagina=inicio&");
                    } else {
                        response.sendRedirect("?pagina=cadastro&erro=cadastro" + err.getURL());
                    }

                } else {
                    System.out.println("é instituicao...");
                    Inscrito ii = Inscrito.getInscritoPorCookie(c);
                    if (ii != null) {
                        System.out.println("não null...");
                        if (ii.isAdministrador()) {
                            System.out.println("admin...");
                            response.sendRedirect("?pagina=admin&secao=cadInstituicao&erro=cadastroI&" + err.getURL());
                        } else {
                            System.out.println("non admin...");
                            response.sendRedirect("?pagina=admin_i&secao=cadInstituicao&erro=cadastroI&" + err.getURL());
                        }
                    }
                }
            }
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
