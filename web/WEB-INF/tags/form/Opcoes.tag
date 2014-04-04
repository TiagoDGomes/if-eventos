<%-- 
    Document   : Opcoes
    Created on : 03/06/2012, 20:57:46
    Author     : Tiago
--%>

<%@tag import="br.edu.ifsp.eventos.classes.web.sistema.Sistema"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<div class="formulario">
    <form class="formulario" action="SalvarConfig" method="post">
        <div class="info">
            <span><label for="css">Estilo:</label></span>
            <span>
                <select name="css" id="css" size="1">
                    <option <% if (Sistema.getEstilo().equals("greenbook")) out.print("selected=\"selected\""); %>value="greenbook">GreenBookIF</option>
                    <option <% if (Sistema.getEstilo().equals("tiago")) out.print("selected=\"selected\""); %> value="tiago">visual antigo</option> 
                    <option <% if (Sistema.getEstilo().equals("null")) out.print("selected=\"selected\""); %> value="null">Sem estilos (não recomendado)</option>
                </select>
            </span>
            <div class="info">
                <span>
                    <label>&nbsp;</label>
                </span>
                <span>
                    <input class="button" type="submit" value="Salvar" />
                    <input class="button" type="button" value="Cancelar" onclick="ss('#admInicio')" />
                </span>
            </div>
    </form>
</div>