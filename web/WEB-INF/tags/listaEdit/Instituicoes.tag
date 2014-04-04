<%-- 
    Document   : Instituicoes
    Created on : 02/06/2012, 15:49:56
    Author     : Tiago
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag import="br.edu.ifsp.eventos.classes.Instituicao"%>
<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>


<table class="lista">
    <tr>
        <th width="8%">ID</th>
        <th width="60%">Nome</th>
        <th width="12%">Nº inscritos</th>
        <th width="20%">Ação</th>
    </tr>
    <%
        for (Instituicao instituicao : Instituicao.getListaInstituicao()) {
    %> 
    <tr>
        <td class="numero"><%= instituicao.getId()%></td>
        <td class="texto"><%= instituicao.getNome()%></td>
        <td class="numero"><%= instituicao.getMembros().size()%></td>
        <td class="acao">
            <!--<button class="button">Editar</button>-->
            <button onclick="excluir('it',<%= instituicao.getId()%>)" class="button">Excluir</button>
        </td>

    </tr>
    <%
        }
    %>
</table>