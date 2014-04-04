<%-- 
    Document   : Evento
    Created on : 02/06/2012, 15:26:35
    Author     : Tiago
--%>
<%@tag import="javax.swing.JOptionPane"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags/" %>
<%@tag import="br.edu.ifsp.eventos.classes.Evento"%>
<%@tag import="br.edu.ifsp.eventos.classes.Palestrante"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>


<%@attribute name="hash"%>
<%@attribute name="evento" type="Evento"%>

<script type="text/javascript">
    jQuery(function($){
        $("#eventoData").mask("99/99/9999");
        $("#eventoHorario").mask("99:99");  
    });
</script>


<%
    
if (Palestrante.getListaPalestrantes().size()==0) {%>
<tag:mensagemErro visible="true" id="erroEventoPalestrante" mensagem="Você não pode criar um evento sem ter palestrantes cadastrados." classCss="mensagemErro" titulo="Não há palestrantes" />
<% } else {%>

<form class="formulario" action="CadastroEvento" method="post" enctype="multipart/form-data">
    <div class="info">
        <span><label for="eventoNome">Nome: </label></span>
        <span><input size="30" type="text" id="eventoNome" name="eventoNome" /></span>
    </div>
    <div class="info">
        <span><label for="eventoFoto">Foto: </label></span>
        <span><input type="file" id="eventoFoto" name="eventoFoto" /></span>
    </div>

    <div class="info">
        <span><label for="eventoDetalhes">Detalhes: </label></span>
        <span><textarea cols="35" rows="7" id="eventoDetalhes" name="eventoDetalhes" ></textarea></span>
    </div>
    <div class="info">
        <span><label for="eventoPalestrante">Palestrante:</label></span>
        <span>
            <select name="eventoPalestrante" id="eventoPalestrante" size="1">
                <%
                    for (Palestrante p : Palestrante.getListaPalestrantes()) {
                %> 
                <option value="<%=p.getId()%>"><%=p.getNome()%></option>
                <% }%>
            </select></span>
    </div> 
    <div class="info">
        <span><label for="eventoData">Data: </label></span>
        <span><input size="10" type="text" id="eventoData" name="eventoData" /></span>
    </div>
    <div class="info">
        <span><label for="eventoHorario">Horário: </label></span>
        <span><input size="10" type="text" id="eventoHorario" name="eventoHorario" /></span>
    </div>
    <div class="info">
        <span><label for="eventoVagas">Total de vagas: </label></span>
        <span><input size="6" type="text" id="eventoVagas" name="eventoVagas" /></span>
    </div>
    <div class="info">
        <span>
            <label>&nbsp;</label>
            <input type="hidden" name="h" value="${hash}" />
        </span>
        <span>
            <input class="button" type="submit" value="Salvar" />
            <input class="button" type="button" value="Cancelar" onclick="ss('#admInicio')" />
        </span>
    </div>
</form>
<% }%>