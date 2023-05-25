<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="Modelo.Persona"%>
<%@page import="Modelo.Persona"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Comentar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            PersonaDAO dao=new PersonaDAO();
            List<Persona>list=dao.consultar4();
            Iterator<Persona>iter=list.iterator();
            Persona per= null;
            int c=0;
            while(iter.hasNext()){
                c=c+1;
                per=iter.next(); 
            }
           if(c<=0){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body onload = 'loaded();'></body>");
                out.println("<script>");
                out.println("function loaded() {");
                out.println("alert('Debes de agregar una especialidad antes de agregar a una situación');");
                out.println("location.href ='index.jsp';");
                out.println("}");
                out.println("</script>");
                out.println("</html>");
           }
        %>
        %>
        <form action="Controlador">
            <table style="text-align: left; margin-left: auto; margin-right: auto;">
                <tbody>
                    <tr>
                        <td>
                            <label>Nombre(s) del Doctor</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="name" rows="5" cols="50" required="required" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Apellidos del Doctor</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="ape" rows="5" cols="50" required="required" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Correo del Doctor</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="email" required="required" type="email" maxlength="300">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Contraseña de la cuenta:</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="password" rows="5" cols="50" required="required" maxlength="30">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Especialidad:</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <select name="Esp">
                        <%
                        List<Persona>list2=dao.consultar4();
                        Iterator<Persona>iter2=list2.iterator();
                        Persona per2= null;
                        while(iter2.hasNext()){
                            per2=iter2.next();
                        %>
                        <option><%=per2.getNombre()%></option>
                        <%}%>
                    </select>
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2" rowspan="1">
                            <input value="Agregar Doctor" name="accion"   type="submit" onclick="algoritmo();"/>
                        </td>
                    </tr>
                    <tr>
                    </tr>
                </tbody>
            </table>
            <a href="IndAdmin.jsp">Volver</a>
        </form>
    </body>
</html>
