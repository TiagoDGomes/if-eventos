
<%-- 
    Document   : mensagemErro
    Created on : 30/05/2012, 17:01:26
    Author     : Tiago
--%>

<%@tag description="Mensagem de erro" pageEncoding="UTF-8"%>
<%@attribute name="titulo"%>
<%@attribute name="mensagem"%>
<%@attribute name="id" required="true"%>
<%@attribute name="classCss" required="true"%>
<%@attribute name="visible" type="Boolean" required="true" %>
<% 
    boolean v =  jspContext.getAttribute("visible") != null ? (Boolean) jspContext.getAttribute("visible"): false;
    if  (!v) jspContext.setAttribute("style", "display: none");
%>
<div style="${style}" id="${id}" class="${classCss}">     
     <div class="fechar"><a href="javascript:closePop('#${id}');">Fechar</a></div>
     <div class="titulo">${titulo}</div>
     <div class="mensagem">${mensagem}</div>     
</div>