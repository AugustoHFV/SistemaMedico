<%-- 
    Document   : IndAdmin
    Created on : 15-abr-2019, 9:41:52
    Author     : KIKA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           HttpSession sesion=request.getSession();
           String usuario;
           String nivel;
           if(sesion.getAttribute("usuarioSesion")==null || session.getAttribute("nivel")!="3"){
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
           
        %>        
        <h1>Gestionar Médicos</h1>
        <a href="registrar2.jsp"><p align="center">Registar médico</p></a>
        <a href="Situaciones.jsp"><p align="left">Registar situación de cita</p></a>
        <a href="Especialidad.jsp"><p align="right">Registar especialidad</p></a>
    </body>
</html>
