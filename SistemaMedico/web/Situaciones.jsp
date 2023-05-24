<%-- 
    Document   : Situaciones
    Created on : 18 may 2023, 18:21:15
    Author     : alumno
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="Modelo.Persona"%>
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
        <form action="Controlador">
            <table style="text-align: left; margin-left: auto; margin-right: auto;">
                <tbody>
                    <tr>
                        <td>
                            <label>Nombre de la situación:</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="NSit" rows="5" cols="50" required="required" maxlength="40">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Duración(minutos):</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="number" name="Dur" rows="5" cols="50" required="required" max="540">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Especialidad que lo cubre:</label>
                        </td>
                    </tr>
                    </tr>
                    <tr>
                        <td>
                    <select name="EspC">
                        <%
                        PersonaDAO dao=new PersonaDAO();
                        List<Persona>list=dao.consultar4();
                        Iterator<Persona>iter=list.iterator();
                        Persona per= null;
                        while(iter.hasNext()){
                            per=iter.next();
                        %>
                        <option><%=per.getNombre()%></option>
                        <%}%>
                    </select>
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2" rowspan="1">
                            <input value="Agregar Situacion" name="accion"   type="submit" onclick="algoritmo();"/>
                        </td>
                    </tr>
                    <tr>
                    </tr>
                </tbody>
            </table>
            <a href="index.jsp">Volver</a>
        </form>
    </body>
</html>
