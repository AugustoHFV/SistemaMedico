<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Comentar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script>
 
function algoritmo()
{
    let com;
    let text;
    text=document.Controlador2.C.value;
    com =document.Controlador2.U.value;
    com1 =document.Controlador2.U1.value;
    if (com=="null")
    {
        alert("No has iniciado sesi�n")
    }
    else if(text==""){
    }
    else if(com1.toLowerCase() == com.toLowerCase()){
        alert("No puedes comentarte a ti mismo")
    }
    else 
    {
        alert("Comentario Guardado")
    }
}
 
        </script>
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
                out.println("alert('Ocurri� un error');");
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
                            <label>Contrase�a de la cuenta:</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="password" rows="5" cols="50" required="required" maxlength="30">
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
            <a href="index.jsp">Volver</a>
        </form>
    </body>
</html>
