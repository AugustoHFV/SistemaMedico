<%-- 
    Document   : IndAdmin
    Created on : 15-abr-2019, 9:41:52
    Author     : KIKA
--%>

<%@page import="Controlador.Controlador"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="ModeloDAO.PersonaDAO"%>
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
        <a href="registrar2.jsp"><p align="center">Registar médico</p></a>
        <a href="Situaciones.jsp"><p align="left">Registar situación de cita</p></a>
        <a href="Especialidad.jsp"><p align="right">Registar especialidad</p></a>
        <a href="Controlador?accion=Cerrar Sesión"><p align="center">Cerrar Sesión</p></a>
        <table border="1" align="center">
            <th>Nombre doctor</th>
            <th>Especialidad</th>
            <%
                Controlador con=new Controlador();
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list=dao.consultar8();
                Iterator<Persona>iter=list.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                List<Persona>list2=dao.consultar(per.getNombre());
                Iterator<Persona>iter2=list2.iterator();
                Persona per2= null;
                while(iter2.hasNext()){
                    per2=iter2.next();}
                String ND=per2.getNombre();
                %>
            <tbody>
                <th><%=con.descifradoCesar(ND)%></th>
                <th><%=per.getEspecialidad()%></th>
                <th><a href="Controlador2?accion=Eliminar&Usu=<%=per.getNombre()%>">Eliminar</a></th>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
