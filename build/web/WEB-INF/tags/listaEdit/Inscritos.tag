<%-- 
    Document   : Inscritos
    Created on : 02/06/2012, 15:56:13
    Author     : Tiago
--%>

<%@tag import="javax.swing.JOptionPane"%>
<%@tag import="java.util.ArrayList"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="instituicao" type="br.edu.ifsp.eventos.classes.Instituicao"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@tag import="br.edu.ifsp.eventos.classes.Individuo"%>
<%@tag import="br.edu.ifsp.eventos.classes.Instituicao"%>

<table class="lista">
    <tr>
        <th width="8%">ID</th>
        <th width="40%">Nome</th>
        <th width="32%">Instituição</th>
        <th width="20%">Ação</th>
    </tr>
    <%
        ArrayList<Individuo> lista;
        if (jspContext.getAttribute("instituicao")==null){
            lista = Individuo.getListaIndividuo();
        } else {

            Instituicao ins = (Instituicao) jspContext.getAttribute("instituicao");
            lista = ins.getMembros();
        }
        for (Individuo ind : lista) {
    %> 
    <tr>
        <td class="numero"><%= ind.getId()%></td>
        <td class="texto"><%= ind.getNome()%></td>
        <td class="numero">
            <% if (ind.getInstituicao() != null){ %>            
            <%= ind.getInstituicao().getNome()%>
            <% }%>
        </td>
        <td class="acao">
            <!--<button class="button">Editar</button>-->
            <button onclick="excluir('id',<%= ind.getId()%>)" class="button">Excluir</button>
        </td>


    </tr>
    <%
        }
    %>
</table>