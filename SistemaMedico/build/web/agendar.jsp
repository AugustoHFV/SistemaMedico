<%@page import="Controlador.Controlador"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="Modelo.Persona"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="ModeloDAO.PersonaDAO"%>
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
           if(session.getAttribute("citas")=="2"){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body onload = 'loaded();'></body>");
                out.println("<script>");
                out.println("function loaded() {");
                out.println("alert('Limite de cita, Reinicia tu navegador para agendar mas citas');");
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
            <%
                    PersonaDAO dao=new PersonaDAO();
                    Controlador con=new Controlador();
                    String U1=(String) session.getAttribute("usuarioSesion");
                    List<Persona>list2=dao.list(U1);
                    Iterator<Persona>iter2=list2.iterator();
                    Persona per= null;
                    while(iter2.hasNext()){
                        per=iter2.next(); 
                        String N=con.descifradoCesar(per.getNombre());
                        String A=con.descifradoCesar(per.getApellidos());
                    %>
            <label>Nombre(s):</label>
            <input name="nombre" value="<%=N%>" disabled>
            <label>Apellidos:</label>
            <input name="Ape" value="<%=A%>" disabled>
            <%}%>
            <select name="Sit">
                        <%
                        List<Persona>list=dao.consultar2();
                        Iterator<Persona>iter=list.iterator();
                        while(iter.hasNext()){
                            per=iter.next();
                        %>
                        <option><%=per.getNombre()%></option>
                        <%}%>
                    </select>
            <br><br><input type="submit" name="accion" value="Agendar Cita">
        </form>
    </div>
</div>    
</body>
</html>