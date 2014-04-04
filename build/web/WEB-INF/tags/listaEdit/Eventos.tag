<%-- 
    Document   : Eventos
    Created on : 02/06/2012, 15:55:04
    Author     : Tiago
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag import="br.edu.ifsp.eventos.classes.Evento"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table class="lista">
    <tr>
        <th width="8%">ID</th>
        <th width="40%">Evento</th>
        <th width="15%">Data</th>
        <th width="15%">Horario</th>
        <th width="22%">Ação</th>
    </tr>
    <%
        for (Evento e : Evento.getListaEventos()) {
    %> 
    <tr>
        <td class="numero"><%= e.getId()%></td>
        <td class="texto"><%= e.getNome()%></td>
        <td class="numero"><%= e.getDataSdf()%></td>
        <td class="numero"><%= e.getHorarioSdf()%></td>
        <td class="acao">
            <!--<button class="button">Editar</button>-->
            <button onclick="excluir('ev',<%= e.getId()%>)"  class="button">Excluir</button>
        </td>
    </tr>
    <%
        }
    %> 
</table>