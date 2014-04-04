<%@tag description="Menu" pageEncoding="utf-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="nome" type="java.lang.String"%>
<%@attribute name="link" type="java.lang.String"%>
<%@tag import="br.edu.ifsp.eventos.classes.web.sistema.Pagina"%>

<%
    Pagina pA = (Pagina) session.getAttribute("paginaAtual");
    String sel = "";
    if (pA.getNomeSimples().equals(jspContext.getAttribute("nome").toString())) {
        jspContext.setAttribute("sel", " selecionado");
    }
    Pagina p = Pagina.getPaginaPorNomeSimples(jspContext.getAttribute("nome").toString());
    jspContext.setAttribute("link", p.getLink());
%>
<li class="${nome}${sel}">${link}</li>  
