<%-- 
    Document   : formItemTextBox
    Created on : 13/05/2012, 00:18:25
    Author     : Tiago
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="onkeyup" type="java.lang.String"%>
<%@attribute name="onblur"%>
<%@attribute name="size"%>
<%@attribute name="maxlength"%>
<%@attribute name="id"%>
<%@attribute name="name"%>


<%-- any content can be specified here e.g.: --%>
<input type="text" size="${size}" onblur="${onblur}" onkeyup="${onkeyup}" name="${name}" id="${id}" maxlength="${maxlength}"