<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="Controlador.Fecha"%>
<%@page import="Controlador.Controlador"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Caompatible" content="ie=edge">
    <title>Citas Agendadas</title>
   
    <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Raleway:500,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="Styles/estilo4.css">
    <link rel="shortcout icon" href="IMG/signomedical.png">
</head>

<header>
    <br><pre><a><img src="IMG/logo2.jpg" align="left" width="50" height="50"></a>                     <a class="titulo">Citas Agendadas</a>                                                                                                      <a  href="index.jsp"><button class="volver"align="right">Volver</button></a></pre></header> 
</header>

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
    <div class="marco">
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <div class="carousel center-align">
                        <%PersonaDAO dao=new PersonaDAO();
                                Controlador con=new Controlador();
                                Fecha F=new Fecha();
                                String U1=(String) session.getAttribute("usuarioSesion");
                                String CC=F.Fecha();
                                int HoA=F.HoraA();
                                List<Persona>list=dao.consultar6(U1,CC,HoA);
                                Iterator<Persona>iter=list.iterator();
                                Persona per2= null;
                                while(iter.hasNext()){
                                    per2=iter.next();
                                    String ED=per2.getNombre();
                                    String S=per2.getApellidos();
                                    String Fe=per2.getVer();
                                    String Co=per2.getCons();
                                    int Ho=Integer.parseInt(per2.getContra());
                                    int aux=F.Comp2(Fe);
                                    if(aux==1)
                                    {
                                    int HoT=0;
                                    while(Ho>=60){
                                        HoT=HoT+1;
                                        Ho=Ho-60;
                                    }
                                    String HoF;
                                    HoF=Integer.toString(Ho);
                                    if (Ho<10){
                                        HoF="0"+HoF;
                                    }
                                    List<Persona>list2=dao.consultar(ED);
                                    Iterator<Persona>iter2=list2.iterator();
                                    Persona per= null;
                                    while(iter2.hasNext()){
                                        per=iter2.next();
                                        }
                                        String ND=per.getNombre();
                                %>
                        <div class="carousel-item">
                            <div class="container2">
                                <h5>Doctor que lo atenderá:</h5>
                                <input class="dato" type="text" value="<%=con.descifradoCesar(ND)%>" style="width: 90%;" disabled>
                                <h5>Situación de la cita:</h5>
                                <input class="dato" type="text" value="<%=S%>" style="width: 90%;" disabled>
                                <h5>Fecha:<input class="dato" type="text" value="   <%=Fe%>" style="width: 25%;" disabled> Hora:<input class="dato" type="text" value="   <%=HoT%>:<%=HoF%>" style="width: 13%;" disabled> </h5>
                                <h5>Consultorio:</h5>
                                <input class="dato" type="text" value="<%=Co%>" style="width: 90%;" disabled>
                            </div>
                        </div>
                        <%}else if(aux==2){
                                if(F.HoraA()<Ho){
                                    int HoT=0;
                                    while(Ho>=60){
                                        HoT=HoT+1;
                                        Ho=Ho-60;
                                    }
                                    String HoF;
                                    HoF=Integer.toString(Ho);
                                    if (Ho<10){
                                        HoF="0"+HoF;
                                    }
                                    List<Persona>list2=dao.consultar(ED);
                                    Iterator<Persona>iter2=list2.iterator();
                                    Persona per= null;
                                    while(iter2.hasNext()){
                                        per=iter2.next();
                                        }
                                        String ND=per.getNombre();
                                %>
                        <div class="carousel-item">
                            <div class="container2">
                                <h5>Doctor que lo atenderá:</h5>
                                <input class="dato" type="text" value="<%=con.descifradoCesar(ND)%>" style="width: 90%;" disabled>
                                <h5>Situación de la cita:</h5>
                                <input class="dato" type="text" value="<%=S%>" style="width: 90%;" disabled>
                                <h5>Fecha:<input class="dato" type="text" value="   <%=Fe%>" style="width: 25%;" disabled> Hora:<input class="dato" type="text" value="   <%=HoT%>:<%=HoF%>" style="width: 13%;" disabled> </h5>
                                <h5>Consultorio:</h5>
                                <input class="dato" type="text" value="<%=Co%>" style="width: 90%;" disabled>
                            </div>
                        </div>
                            <%}%>
                        <%}%>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script src="JS/Carrusel.js"></script>
</body>
</html>