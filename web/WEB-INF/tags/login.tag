<%-- 
    Document   : login
    Created on : 13/05/2012, 00:31:40
    Author     : Tiago
--%>
<%@tag import="br.edu.ifsp.eventos.classes.Inscrito"%>
<%@taglib prefix="ev" tagdir="/WEB-INF/tags" %>
<%@tag description="Login" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>


<%
    Inscrito u = (Inscrito) session.getAttribute("usuarioLogado");
    if (u == null) {
%>
<form class="login" action="login" method="post">

    <div class="item">E-mail:<br /><input type="text" name="login" />
    </div>
    <div class="item">Senha:<br /><input type="password" name="senha" />
    </div>
    <div class="item">
        <input class="button" type="submit" value="entrar" />
        <ev:facebookLogin />

    </div>
</form>
<% } else {%>
Você está logado como <%=u.getNome() %>. [<a href="logout">Sair</a>]                    
<% }%> 