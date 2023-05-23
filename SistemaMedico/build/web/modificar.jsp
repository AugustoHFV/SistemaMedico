<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Caompatible" content="ie=edge">
    <title>Modificar Citas</title>
   
    <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Raleway:500,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="Styles/estilo4.css">
    <link rel="shortcout icon" href="IMG/signomedical.png">
</head>

<header>
    <br><pre><a><img src="IMG/logo2.jpg" align="left" width="50" height="50"></a>                     <a class="titulo">Modificar citas</a>                                                                                                      <a  href="main.html"><button class="volver"align="right">Volver</button></a></pre></header> 
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
                        <div class="carousel-item">
                            <div class="container2">
                                <h4>Cita 1</h4>
                                <h5>Fecha</h5>
                                <input class="dato" type="date" style="width: 90%;" disabled>
                                <h5>Hora</h5>
                                <input class="dato" type="time" style="width: 90%;" disabled>
                                <h5>Situación</h5>
                                <input class="dato" type="text" style="width: 90%;" disabled>
                                <h5>Doctor</h5>
                                <input class="dato" type="text" style="width: 90%;" disabled>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container2">
                                <h4>Cita 2</h4>
                                <h5>Fecha</h5>
                                <input class="dato" type="date" style="width: 90%;">
                                <h5>Hora</h5>
                                <input class="dato" type="time" style="width: 90%;">
                                <h5>Situación</h5>
                                <input class="dato" type="text" style="width: 90%;">
                                <button class="confirmar">Confirmar</button>
                            </div>
                        </div><div class="carousel-item">
                            <div class="container2">
                                <h4>Cita 3</h4>
                                <h5>Fecha</h5>
                                <input class="dato" type="date" style="width: 90%;">
                                <h5>Hora</h5>
                                <input class="dato" type="time" style="width: 90%;">
                                <h5>Situación</h5>
                                <input class="dato" type="text" style="width: 90%;">
                                <button class="confirmar">Confirmar</button>
                            </div>
                        </div><div class="carousel-item">
                            <div class="container2">
                                <h4>Cita 4</h4>
                                <h5>Fecha</h5>
                                <input class="dato" type="date" style="width: 90%;">
                                <h5>Hora</h5>
                                <input class="dato" type="time" style="width: 90%;">
                                <h5>Situación</h5>
                                <input class="dato" type="text" style="width: 90%;">
                                <button class="confirmar">Confirmar</button>
                            </div>
                        </div><div class="carousel-item">
                            <div class="container2">
                                <h4>Cita 5</h4>
                                <h5>Fecha</h5>
                                <input class="dato" type="date" style="width: 90%;">
                                <h5>Hora</h5>
                                <input class="dato" type="time" style="width: 90%;">
                                <h5>Situación</h5>
                                <input class="dato" type="text" style="width: 90%;">
                                <button class="confirmar">Confirmar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script src="js/Carrusel.js"></script>
</body>
</html>