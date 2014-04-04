<%@taglib prefix="ev" tagdir="/WEB-INF/tags" %><%@include file="WEB-INF/jsp/init.jspf" %>
<%@page buffer="120kb" contentType="text/html" pageEncoding="utf-8"%>

<%
    Pagina paginaAtual = (Pagina) session.getAttribute("paginaAtual");
    Inscrito usuarioLogado = (Inscrito) session.getAttribute("usuarioLogado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="robots" content="index, follow" />
        <meta name="keywords" content="ifsp, sjbv, eventos" />
        <title><%= paginaAtual.getNome()%> - Página de Eventos - IFSP Campus São João da Boa Vista</title>
        <link href="/eventos/templates/screen-all.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="<%= "/eventos/templates/" + Sistema.getEstilo() + "/css/style1.css"  %>" rel="stylesheet" type="text/css" media="screen" />
        <link rel="icon" href="favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <script type="text/javascript" src="/eventos/js/jquery.js"></script>
        <script type="text/javascript" src="/eventos/js/jquery.xmldom.js"></script>
        <script type="text/javascript" src="/eventos/js/jquery.mask.js"></script>
        <script type="text/javascript" src="/eventos/js/functions.js"></script>        
        <script>
            $(document).ready(function(){  
                $("#mensagemErroTop").fadeIn();
            });            
        </script>
    </head>
    <body>
        <div id="mainEventos">
            <% if (request.getParameter("erro") != null) { %>
            <% if (request.getParameter("erro").equals("login") ){ %>
            
            <ev:mensagemErro visible="true" id="mensagemErroTop" titulo="Erro no login"
                             mensagem="Nome de usuário e/ou senha incorretos." classCss="mensagemErro"  />
            <% } else if (request.getParameter("erro").equals("cadastro") ){
            %>
            
            <ev:mensagemErro visible="true" id="mensagemErroTop" titulo="Erro no cadastro"
                             mensagem="Você não preencheu todos os campos do cadastro." classCss="mensagemErro"  />
            <%
                 }
            }%>
            
            
            <div id="mainLogo">
                <h1>
                    <span class="nomeInstituicao">Instituto Federal de Educação, Ciência e Tecnologia</span> <br />
                    <span class="estadoInstituicao">São Paulo</span><br />
                    <span class="campus">Campus São João da Boa Vista</span><br />
                    <span class="tituloSite">Página de Eventos</span>
                </h1>
                <div class="login">
                    <ev:login />
                </div>
            </div>
            <%--  Menu principal  --%>
            <div id="mainMenu" class="menu">
                <ul class="principal">
                    <ev:menuItem nome="inicio" />
                    <ev:menuItem nome="eventos" />
                    <% if (usuarioLogado == null) {%>
                    <ev:menuItem nome="cadastro" />
                    <% } else if (usuarioLogado.isAdministrador()) {%>
                    <ev:menuItem nome="admin" />                    
                    <% } else if (usuarioLogado instanceof Individuo) {%>
                    <ev:menuItem nome="meucadastro" />
                    <% } else {%>
                    <ev:menuItem nome="admin_i" />
                    
                    <% } %>
                    
                </ul>
                <%
                    if (usuarioLogado != null)
                        if (!usuarioLogado.isAdministrador()) {%>

                <ul class="menuInscrito">
                   <!-- <ev:menuItem nome="meuseventos" /> -->
                    <!--<ev:menuItem nome="meuscertificados" /> -->
                </ul>

                <%  }
                %>
            </div> 
            <!--<div id="busca">Busca:
                <input type="text" name="q" />
                <input class="button" type="submit" value="buscar" />
            </div>-->          
            
            <%--  Conteúdo principal  --%>
            <div id="conteudo">
                <% if ((Boolean) session.getAttribute("db.Connection")) { %>
                
                <jsp:include page="<%= paginaAtual.getJspfEx()%>">
                    <jsp:param name="conteudo" value="/" />
                </jsp:include>
                
                <%  } else { %>
                <%@include file="WEB-INF/jsp/conteudo/erro/banco.jspf" %>                
                 <%  }  %>
            </div>
        </div>
        <footer>
            <address>
                Instituto Federal de Educação, Ciência e Tecnologia - [<a href="http://www.ifsp.edu.br/saojoaodaboavista">Página principal</a>] [<a href="http://189.108.236.229/internet/index.php?option=com_contact&view=contact&id=38&Itemid=88">Contato</a>] <br/>
                Acesso Dr. João Batista Merlin, s/nº - Jardim Itália - 13872-551 - São João da Boa Vista - SP - (19) 3634-1100 - (<a href="?pagina=localizacao">Ver pelo Google Maps</a> - <a href="?pagina=localizacao&use=bing">Ver pelo Bing Maps</a>) <br />
                Temos <%= Inscrito.getListaInscrito().size()%> inscritos.
            </address>
        </footer>
    </body>
</html>

