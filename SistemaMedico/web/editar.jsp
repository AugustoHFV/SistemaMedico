<%-- 
    Document   : editar
    Created on : 31 mar 2023, 19:14:00
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cuenta</title>
    </head>
    <body>
        <%
           HttpSession sesion=request.getSession();
           String usuario;
           String nivel;
            if(sesion.getAttribute("usuarioSesion")==null || session.getAttribute("nivel")!="1"){
                    if(session.getAttribute("nivel")!="2"){
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body onload = 'loaded();'></body>");
                    out.println("<script>");
                    out.println("function loaded() {");
                    out.println("alert('Ocurrió un error');");
                    out.println("location.href ='index.jsp';");
                    out.println("}");
                    out.println("</script>");
                    out.println("</html>");
                }
            }
           
        %>    
        <h1>Modificar Cuenta</h1>
        
    </body>
</html>
