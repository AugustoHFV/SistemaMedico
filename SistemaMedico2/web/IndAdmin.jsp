
<%@page import="Controlador.Fecha"%>
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
         <link rel="stylesheet" href="Styles/estilo5.css">
                 <link rel="shortcout icon" href="IMG/signomedical.png">
        <title>Administrador</title>
        <style>

[type="checkbox"]:checked,
[type="checkbox"]:not(:checked){
  position: absolute;
  left: -9999px;
}
.menu-icon:checked + label,
.menu-icon:not(:checked) + label{
  position: fixed;
  top: 63px;
  right: 75px;
  display: block;
  width: 30px;
  height: 30px;
  padding: 0;
  margin: 0;
  cursor: pointer;
  z-index: 10;
}
.menu-icon:checked + label:before,
.menu-icon:not(:checked) + label:before{
  position: absolute;
  content: '';
  display: block;
  width: 30px;
  height: 20px;
  z-index: 20;
  top: 0;
  left: 0;
  border-top: 2px solid #ececee;
  border-bottom: 2px solid #ececee;
  transition: border-width 100ms 1500ms ease, 
              top 100ms 1600ms cubic-bezier(0.23, 1, 0.32, 1),
              height 100ms 1600ms cubic-bezier(0.23, 1, 0.32, 1), 
              background-color 200ms ease,
              transform 200ms cubic-bezier(0.23, 1, 0.32, 1);
}
.menu-icon:checked + label:after,
.menu-icon:not(:checked) + label:after{
  position: absolute;
  content: '';
  display: block;
  width: 22px;
  height: 2px;
  z-index: 20;
  top: 10px;
  right: 4px;
  background-color: #ececee;
  margin-top: -1px;
  transition: width 100ms 1750ms ease, 
              right 100ms 1750ms ease,
              margin-top 100ms ease, 
              transform 200ms cubic-bezier(0.23, 1, 0.32, 1);
}
.menu-icon:checked + label:before{
  top: 10px;
  transform: rotate(45deg);
  height: 2px;
  background-color: #ececee;
  border-width: 0;
  transition: border-width 100ms 340ms ease, 
              top 100ms 300ms cubic-bezier(0.23, 1, 0.32, 1),
              height 100ms 300ms cubic-bezier(0.23, 1, 0.32, 1), 
              background-color 200ms 500ms ease,
              transform 200ms 1700ms cubic-bezier(0.23, 1, 0.32, 1);
}
.menu-icon:checked + label:after{
  width: 30px;
  margin-top: 0;
  right: 0;
  transform: rotate(-45deg);
  transition: width 100ms ease,
              right 100ms ease,  
              margin-top 100ms 500ms ease, 
              transform 200ms 1700ms cubic-bezier(0.23, 1, 0.32, 1);
}

.nav{
  position: fixed;
  top: 33px;
  right: 50px;
  display: block;
  width: 80px;
  height: 80px;
  padding: 0;
  margin: 0;
  z-index: 9;
  overflow: hidden;
  box-shadow: 0 8px 30px 0 rgba(0,0,0,0.3);
  background-color: #006980;
  animation: border-transform 7s linear infinite;
  transition: top 350ms 1100ms cubic-bezier(0.23, 1, 0.32, 1),  
              right 350ms 1100ms cubic-bezier(0.23, 1, 0.32, 1),
              transform 250ms 1100ms ease,
              width 650ms 400ms cubic-bezier(0.23, 1, 0.32, 1),
              height 650ms 400ms cubic-bezier(0.23, 1, 0.32, 1);
}
@keyframes border-transform{
    0%,100% { border-radius: 63% 37% 54% 46% / 55% 48% 52% 45%; } 
  14% { border-radius: 40% 60% 54% 46% / 49% 60% 40% 51%; } 
  28% { border-radius: 54% 46% 38% 62% / 49% 70% 30% 51%; } 
  42% { border-radius: 61% 39% 55% 45% / 61% 38% 62% 39%; } 
  56% { border-radius: 61% 39% 67% 33% / 70% 50% 50% 30%; } 
  70% { border-radius: 50% 50% 34% 66% / 56% 68% 32% 44%; } 
  84% { border-radius: 46% 54% 50% 50% / 35% 61% 39% 65%; } 
}

.menu-icon:checked ~ .nav {
  animation-play-state: paused;
  top: 50%;
  right: 50%;
  transform: translate(50%, -50%);
  width: 200%;
  height: 200%;
  transition: top 150ms 500ms cubic-bezier(0.23, 1, 0.32, 1),  
              right 150ms 500ms cubic-bezier(0.23, 1, 0.32, 1),
              transform 50ms 500ms ease,
              width 550ms 800ms cubic-bezier(0.23, 1, 0.32, 1),
              height 550ms 800ms cubic-bezier(0.23, 1, 0.32, 1);
}

.nav ul{
  position: absolute;
  top: 50%;
  left: 0;
  display: block;
  width: 100%;
  padding: 0;
  margin: 0;
  z-index: 6;
  text-align: center;
  transform: translateY(-50%);
  list-style: none;
}
.nav ul li{
  position: relative;
  display: block;
  width: 100%;
  padding: 0;
  margin: 10px 0;
  text-align: center;
  list-style: none;
  pointer-events: none;
  opacity: 0;
  visibility: hidden;
  transform: translateY(30px);
  transition: all 50ms linear;
}
.nav ul li:nth-child(1){
  transition-delay: 180ms;
}
.nav ul li:nth-child(2){
  transition-delay: 130ms;
}
.nav ul li:nth-child(3){
  transition-delay: 80ms;
}
.nav ul li:nth-child(4){
  transition-delay: 30ms;
}.nav ul li:nth-child(5){
  transition-delay: 10ms;
}
.nav ul li a{
  font-family: 'Montserrat', sans-serif;
  font-size: 9vh;
  text-transform: uppercase;
  line-height: 1.3;
  font-weight: 800;
  display: inline-block;
  position: relative;
  color: #ececee;
  transition: all 50ms linear;
  text-decoration: none;
}
.nav ul li a:hover{
  text-decoration: none;
  color: #ffeba7;
}
.nav ul li a:after{
  display: block;
  position: absolute;
  top: 50%;
  content: '';
  height: 2vh;
  margin-top: -1vh;
  width: 0;
  left: 0;
  background-color: #353746;
  opacity: 0.8;
  transition: width 150ms linear;
}



.menu-icon:checked ~ .nav  ul li {
  pointer-events: auto;
  visibility: visible;
  opacity: 1;
  transform: translateY(0);
  transition: opacity 150ms ease,
              transform 50ms ease;
}
.menu-icon:checked ~ .nav ul li:nth-child(1){
  transition-delay: 1400ms;
}
.menu-icon:checked ~ .nav ul li:nth-child(2){
  transition-delay: 1480ms;
}
.menu-icon:checked ~ .nav ul li:nth-child(3){
  transition-delay: 1560ms;
}
.menu-icon:checked ~ .nav ul li:nth-child(4){
  transition-delay: 1640ms;
}
.menu-icon:checked ~ .nav ul li:nth-child(5){
  transition-delay: 1640ms;
}

        @media only screen and (max-width: 600px) {
        
            .titulo {
                float: none;
                display: block;
                text-align: center;
            }
            header img {
                float: none;
                display: block;
                margin: 0 auto;
            }
            
            .menu {
                float: none;
                text-align: center;
                margin-top: 10px;
            }
            
            .menu a {
                display: block;
                margin: 5px 0;
            }
        }
        @media screen and (max-width: 991px) {
  .menu-icon:checked + label,
  .menu-icon:not(:checked) + label{
    right: 55px;
  }
  .logo {
    right: 10px;
  }
  .nav{
    right: 30px;
  }
  h1{
    font-size: 9vw;
    -webkit-text-stroke: 2px transparent;
    text-stroke: 2px transparent;
    -webkit-text-fill-color: #ffeba7;
    text-fill-color: #ffeba7;
    color: #ffeba7;
  }
  .nav ul li a{
    font-size: 8vh;
  }
}
.titulo {
    font-size: 50px;
    display: flex;
    justify-content: left;
    margin-left: 100px;
    position: absolute;
    top: 4%;
    color: black;
    left: 8%
}
    </style>
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
         <header>
        <a><img src="IMG/logo2.jpg" align="left" width="90" height="90"></a>
    <a class="titulo">Registro General</a>  
    <input class="menu-icon" type="checkbox" id="menu-icon" name="menu-icon"/>
    <label for="menu-icon"></label>
    <nav class="nav">     
      <ul class="pt-5">
        <li><a href="registrar2.jsp">Registrar Doctor</a></li>
        <li><a href="Situaciones.jsp">Registrar Situaciones</a></li>
        <li><a href="Especialidad.jsp">Registrar Especialidad</a></li>
        <li><a href="Consultorios.jsp">Registrar Consultorio</a></li>
        <li><a href="Controlador?accion=Cerrar Sesión">Cerrar Sesión</a></li>
      </ul>
    </nav>
        </header>
        <table border="1" align="center">
            <th>Nombre doctor</th>
            <th>Especialidad</th>
            <th>Inicio de horario</th>
            <th>Fín de horario</th>
            <th>Consultorio</th>
            <th>Citas Vigentes</th>
            <th>Estado</th>
            <%
                Fecha Fe=new Fecha();
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
                int Ho=per.getHora();
                int HoT=0;
                while(Ho>=60){
                HoT=HoT+1;
                Ho=Ho-60;
                                    }
                String HoF=Integer.toString(Ho);
                if (Ho<10){
                    HoF="0"+HoF;
                }
                int Ho2=per.getHora2();
                int HoT2=0;
                while(Ho2>=60){
                HoT2=HoT2+1;
                Ho2=Ho2-60;
                                    }
                String HoF2=Integer.toString(Ho2);
                if (Ho2<10){
                    HoF2="0"+HoF2;
                }
                String Hoy=Fe.Fecha();
                int Hor=Fe.HoraA();
                List<Persona>list3=dao.consultar24(per.getNombre(),Hoy,Hor);
                Iterator<Persona>iter3=list3.iterator();
                Persona per3= null;
                int citas=0;
                while(iter3.hasNext()){
                    per3=iter3.next();
                    citas=per3.getHora();
                }
                List<Persona>list4=dao.consultar25(per.getNombre(),Hoy);
                Iterator<Persona>iter4=list4.iterator();
                Persona per4= null;
                while(iter4.hasNext()){
                    per4=iter4.next();
                    citas=citas+per4.getHora();
                }
                %>
            <tbody>
                <th><%=con.descifradoCesar(ND)%></th>
                <th><%=per.getEspecialidad()%></th>
                 <th><%=HoT%>:<%=HoF%></th>
                 <th><%=HoT2%>:<%=HoF2%></th>
                 <th><%=per.getCons()%></th>
                 <th><%=citas%></th>
                <th><a class="eliminar" href="Controlador?accion=Eliminar&Usu=<%=per.getNombre()%>"><button>Eliminar</button></a></th>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
