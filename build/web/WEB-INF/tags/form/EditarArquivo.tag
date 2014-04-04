<%-- 
    Document   : EditarArquivo
    Created on : 02/06/2012, 15:34:29
    Author     : Tiago
--%>
<%@tag import="java.io.FileReader"%>
<%@tag import="java.io.FileInputStream"%>
<%@tag import="java.io.File"%>
<%@tag import="java.io.BufferedReader"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="arquivo" required="true"%>


<%-- any content can be specified here e.g.: --%>
<form class="" action="EditarPagina?arquivo=${arquivo}" method="post">
            <textarea cols="140" rows="25" style="overflow: auto;"  ><%
                //out.println(request.getSession().getServletContext().getRealPath("WEB-INF/jsp/conteudo/inicio.jspf")   );
                
                FileReader reader = new FileReader(request.getSession().getServletContext().getRealPath((String)jspContext.getAttribute("arquivo")));
                BufferedReader buffReader = new BufferedReader(reader);

                String linha;
                while ((linha = buffReader.readLine()) != null) {
                    out.println(linha);
                }
                reader.close();
                %></textarea>
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