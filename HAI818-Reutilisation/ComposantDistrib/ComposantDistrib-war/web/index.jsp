<%-- 
    Document   : index
    Created on : 10 févr. 2022, 16:56:38
    Author     : martin.sanchez@etu.umontpellier.fr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:useBean id="counter" scope="session" class="mainPackage.Counter" />
        
        <%
            String s = request.getParameter("operation");
            if ("incr".equals(s)) counter.incr();
            else if ("decr".equals(s)) counter.decr();
            else if ("raz".equals(s)) counter.raz();
        %>
        
        <h1>Valeur du compteur :
        <jsp:getProperty name="counter" property="value" />
        </h1>
            <form name="Name Input Form" action="index.jsp">
            <input type="submit" value="incr" name="operation"/>
            <input type="submit" value="decr" name="operation"/>
            <input type="submit" value="raz" name="operation"/>
        </form>    
        
        
    </body>
</html>
