<%-- 
    Document   : Palestrantes
    Created on : 02/06/2012, 15:53:21
    Author     : Tiago
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag import="br.edu.ifsp.eventos.classes.Palestrante"%>

<table class="lista">
    <tr>
        <th width="4%">ID</th>
        <th width="12%">Foto</th>
        <th width="54%">Nome</th>
        <th width="10%">Nº eventos</th>
        <th width="20%">Ação</th>
    </tr>
    <%
        for (Palestrante p : Palestrante.getListaPalestrantes()) {
    %> 
    <tr>
        <td class="numero"><%= p.getId()%></td>
        <td class="numero"><img src="/eventos/img?c=palestrante&id=<%= p.getId()%>&sx=80&sy=80" alt="<%= p.getNome()%>"/></td>
        <td class="texto">
            <strong><%= p.getNome()%></strong><br />
            <%= p.getDescricao()%>
        </td>
        <td class="numero"><%= p.getTotalEventos()%></td>
        <td class="acao">
            <!--<button class="button">Editar</button>-->
            <button onclick="excluir('pl',<%= p.getId()%>)" class="button">Excluir</button>
        </td>
    </tr>
    <%
        }
    %>
</table>