<%-- 
    Created on : 02/06/2012, 15:19:28
    Author     : Tiago
--%>
<%@tag import="br.edu.ifsp.eventos.classes.Palestrante"%>
<%@tag description="Palestrante" pageEncoding="UTF-8"%>
<%@attribute name="hash"%>

<%@attribute name="palestrante" type="Palestrante"%>
        <form name="palestrante" class="formulario" action="CadastroPalestrante" method="post" enctype="multipart/form-data">         
            
            <div class="info">
                <span><label for="palestranteNome">Nome: </label></span>
                <span><input size="30" type="text" id="palestranteNome" name="palestranteNome" value="${palestrante.getNome()}" /></span>
            </div>
            <div class="info">
                <span><label for="palestranteFoto">Foto: </label></span>
                <span><input type="file" id="palestranteFoto" name="palestranteFoto" /></span>
            </div>
            <div class="info">
                <span><label for="palestranteDetalhes">Detalhes: </label></span>
                <span><textarea cols="35" rows="7" id="palestranteDetalhes" name="palestranteDetalhes"  value="${palestrante.getDetalhes()}" ></textarea></span>
            </div>
            <div class="info">
                <span>
                    <label>&nbsp;</label>
                    <input type="hidden" name="h" value="${hash}" />
                    <input type="hidden" name="id" value="${palestrante.getId()}" />                    
                </span>
                <span>
                    <input class="button" type="submit" value="Salvar" />
                    <input class="button" type="button" value="Cancelar" onclick="ss('#admInicio')" />
                </span>
            </div>
        </form>