<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendar citas</title>
    <link rel="stylesheet" href="Styles/estilo3.css">
    <link rel="shortcout icon" href="IMG/signomedical.png">
</head>
<body>
        <%
           HttpSession sesion=request.getSession();
           if(sesion.getAttribute("usuarioSesion")==null || session.getAttribute("nivel")!="1"){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body onload = 'loaded();'></body>");
                out.println("<script>");
                out.println("function loaded() {");
                out.println("alert('Inicia sesión para acceder');");
                out.println("location.href ='index.jsp';");
                out.println("}");
                out.println("</script>");
                out.println("</html>");
           }
           
        %>
    <header>
        <br><pre><a><img src="IMG/logo2.jpg" align="left" width="50" height="50"></a>                     <a class="titulo">Registrar citas</a>                                                                                                              <a  href="index.jsp"><button class="volver"align="right">Volver</button></a></pre></header> 
    </header>
<div class="marco">
    <div class="form-container sign-in-container">
        <form action="Controlador">
            <select name="Sit">

            <option>Coche</option>

            <option>Avión</option>

            <option>Tren</option>

            </select>
            <br><br><input type="submit" name="accion" value="Agendar Cita">
    </div>
</div>    
</body>
</html>