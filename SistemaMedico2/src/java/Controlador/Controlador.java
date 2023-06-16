/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String consultar = "consultar.jsp";
    String registrar = "main.jsp";
    String editar = "editar.jsp";

    Correos Corr=new Correos();
    Persona p = new Persona();
    PersonaDAO pd = new PersonaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("consultar")) {
            acceso = consultar;
        } else if (action.equalsIgnoreCase("registrar")) {
            acceso = registrar;
        }else if (action.equalsIgnoreCase("Crear")) {
            try {
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = request.getParameter("email");
                A=A.replaceAll("'", "");
                String CE = cifradoCesar(request.getParameter("password"));
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list2=dao.list(A);
                Iterator<Persona>iter=list2.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                    p.setVer(descifradoCesar(per.getApellidos()));
                    sesion.setAttribute("mensaje","Este correo ya ha sido registrado");
                    sesion.setAttribute("link","main.jsp");
                    acceso="mensaje.jsp";
                }
                if(p.getVer()==null)
                    {
                        int Ni=1;
                        p.setNombre(N);
                        p.setApellidos(Ap);
                        p.setEmail(A);
                        p.setContra(CE);
                        p.setNivel(Ni);
                        pd.registrar(p);
                        Corr.Correo(A,"Cuenta Registrada","Has creado una nueva cuenta en ASAP con este correo");
                        sesion.setAttribute("mensaje","Cuenta creada");
                        sesion.setAttribute("link","main.jsp");
                        acceso="mensaje.jsp";
                    }
                p.setVer(null);
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","main.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Confirmar")) {
            try{
            Fecha F=new Fecha();
            String Fe=F.Fecha();
            String S=request.getParameter("Sit");
            String Dia=request.getParameter("Dia");
            int aum=0;
            if(F.Comp(Dia)==3){
                aum=1;
            }else if(F.Comp(Dia)==2){
                aum=0;
            }
            else{
                sesion.setAttribute("mensaje","No puedes seleccionar días pasados");
                sesion.setAttribute("link","agendar.jsp");
                acceso="mensaje.jsp";
            }
            if(F.Comp(Dia)!=1){
            PersonaDAO dao=new PersonaDAO();
            List<Persona>list4=dao.consultar9(S);
            Iterator<Persona>iter4=list4.iterator();
            Persona per4= null;
            while(iter4.hasNext()){
                per4=iter4.next();}
            if(per4==null)
            {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","agendar.jsp");
                acceso="mensaje.jsp";
            }
            else{
            String N=(String) sesion.getAttribute("usuarioSesion");
            List<Persona>list3=dao.consultar5(S);
            Iterator<Persona>iter3=list3.iterator();
            Persona per3= null;
            while(iter3.hasNext()){
                per3=iter3.next();}
            String Es=per3.getApellidos();
            String aum2=Integer.toString(aum);
            sesion.setAttribute("Especialidad",Es);
            sesion.setAttribute("Dia",Dia);
            sesion.setAttribute("Situa",S);
            sesion.setAttribute("aum",aum2);
            acceso="confirmar.jsp";               
            }
            }
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","agendar.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Agendar Cita"))
        {
            try{
            Fecha F=new Fecha();
            PersonaDAO dao=new PersonaDAO();
            String U1=(String) sesion.getAttribute("usuarioSesion");
            String CC=F.Fecha();
            int HoA=F.HoraA();
            List<Persona>list5=dao.consultar21(U1,CC,HoA);
            Iterator<Persona>iter5=list5.iterator();
            Persona per5= null;
            int c=0;
            while(iter5.hasNext()){
                c=c+1;
                per5=iter5.next(); 
            }
            List<Persona>list7=dao.consultar22(U1,CC,HoA);
            Iterator<Persona>iter7=list7.iterator();
            Persona per7= null;
            while(iter7.hasNext()){
                c=c+1;
                per7=iter7.next(); 
            }
           if(c>=3 && sesion.getAttribute("nivel")=="1"){
                sesion.setAttribute("mensaje","Limite de citas alcanzado, espera a que pasen algunas de tus citas para poder agendar más");
                sesion.setAttribute("link","agendar.jsp");
                acceso="mensaje.jsp";
           }
           else{
            String Es=(String) sesion.getAttribute("Especialidad");
            sesion.setAttribute("Especialidad",null);
            String Fe=(String) sesion.getAttribute("Dia");
            sesion.setAttribute("Dia",null);
            String Ho=request.getParameter("Hora");
            String[] items = Ho.split(":");
            int Horas=Integer.parseInt(items[0]);
            int Mins=Integer.parseInt(items[1]);
            Mins=Mins+(Horas*60);
            List<Persona>list=dao.consultar19(Es,Mins);
            Iterator<Persona>iter=list.iterator();
            Persona per= null;
            boolean ban1=false;
            int c2=0;
            while(iter.hasNext() && ban1==false){
                per=iter.next();
                String Doc=per.getNombre();
                List<Persona>list2=dao.consultar18(Fe,Es,Mins,Doc);
                Iterator<Persona>iter2=list2.iterator();
                Persona per2= null;
                boolean ban2=false;
                while(iter2.hasNext()){
                    per2=iter2.next();
                    ban2=true;
                }
                System.out.println("si");
                if (ban2==false){
                    String ED=per.getNombre();
                    String EP=(String) sesion.getAttribute("usuarioSesion");
                    String S=(String) sesion.getAttribute("Situa");
                    sesion.setAttribute("Situa",null);
                    String Cons=per.getCons();
                    List<Persona>list4=dao.consultar9(S);
                    Iterator<Persona>iter4=list4.iterator();
                    Persona per4= null;
                    int dur=0;
                    while(iter4.hasNext()){
                        per4=iter4.next();
                        dur=per4.getHora();
                    }
                    int Hof=Mins+dur;
                    p.setNombre(EP);
                    p.setApellidos(ED);
                    p.setEmail(S);
                    p.setHora(Mins);
                    p.setHora2(Hof);
                    p.setCons(Cons);
                    p.setVer(Fe);
                    p.setContra(Es);
                    pd.registrar5(p);
                    List<Persona>list6=dao.consultar(ED);
                    Iterator<Persona>iter6=list6.iterator();
                    Persona per6= null;
                    while(iter6.hasNext()){
                        per6=iter6.next();
                    }
                    String ND=per6.getNombre();
                    List<Persona>list8=dao.consultar(EP);
                    Iterator<Persona>iter8=list8.iterator();
                    Persona per8= null;
                    while(iter8.hasNext()){
                        per8=iter8.next();
                    }
                    String NP=per8.getNombre();
                    Corr.Correo(U1,"Cita Agendada","Agendaste un cita para el día "+Fe+" a las "+Ho+" horas, por la situación de: "+S+". El nombre del doctor que te atenderá es: "+descifradoCesar(ND));
                    Corr.Correo(ED,"Nueva Cita","Han agendado una nueva cita cita para el día "+Fe+" a las "+Ho+" horas, por la situación de: "+S+". El nombre del pacient al que atenderá es: "+descifradoCesar(NP));
                    acceso="modificar.jsp";
                    c2=c2+1;
                    ban1=true;
                }
            }           
            if(c2==0){
                    sesion.setAttribute("mensaje","Ocurrió un error, prueba intentar de nuevo");
                    sesion.setAttribute("link","index.jsp");
                    acceso="mensaje.jsp";
            }
           }
           } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","index.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Agregar Doctor")) {
            try {
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = request.getParameter("email");
                A=A.replaceAll("'", "");
                String CE = cifradoCesar(request.getParameter("password"));
                String Es= request.getParameter("Esp");
                String HoI= request.getParameter("horI");
                String HoF= request.getParameter("horF");
                String Cons= request.getParameter("Cons");
                String[] items = HoI.split(":");
                int Ho1=Integer.parseInt(items[0]);
                int Ho2=Integer.parseInt(items[1]);
                Ho2=(Ho1*60)+Ho2;
                items = HoF.split(":");
                int Ho3=Integer.parseInt(items[0]);
                int Ho4=Integer.parseInt(items[1]);
                Ho4=(Ho3*60)+Ho4;
                if(Ho2<Ho4){
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list2=dao.list(A);
                Iterator<Persona>iter=list2.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                    p.setVer(descifradoCesar(per.getApellidos()));
                    sesion.setAttribute("mensaje","Este correo ya ha sido registrado");
                    sesion.setAttribute("link","registrar2.jsp");
                    acceso="mensaje.jsp";
                }
                List<Persona>list=dao.consultar16(Cons);
                Iterator<Persona>iter2=list.iterator();
                Persona per2= null;
                int horI=0;
                while(iter2.hasNext()){
                    per2=iter2.next();
                    horI=Integer.parseInt(per2.getNombre());
                }
                int horIH=0;
                int horI2=horI;
                while(horI2>=60){
                    horIH=horIH+1;
                    horI2=horI2-60;
                }
                String hoI1=Integer.toString(horIH);
                String hoI2=Integer.toString(horI2);
                if(horI2<10)
                {
                    hoI2="0"+hoI2;
                }
                String HoIF2=hoI1+":"+hoI2;
                list=dao.consultar17(Cons);
                iter2=list.iterator();
                per2= null;
                int horF=0;
                while(iter2.hasNext()){
                    per2=iter2.next();
                    horF=Integer.parseInt(per2.getNombre());
                }
                int horFH=0;
                int horF2=horF;
                while(horF2>=60){
                    horFH=horFH+1;
                    horF2=horF2-60;
                }
                String hoF1=Integer.toString(horFH);
                String hoF2=Integer.toString(horF2);
                if(horF2<10)
                {
                    hoF2="0"+hoF2;
                }
                String HoFF2=hoF1+":"+hoF2;
                if(Ho2<=horI || Ho2>=horF){
                    if(Ho4<=horI || Ho4>=horF){
                if(per==null)
                    {
                int Ni=2;
                p.setNombre(N);
                p.setApellidos(Ap);
                p.setEmail(A);
                p.setContra(CE);
                p.setNivel(Ni);
                p.setHora(Ho2);
                p.setHora2(Ho4);
                p.setEspecialidad(Es);
                p.setCons(Cons);
                pd.registrar(p);
                pd.registrar3(p);
                Corr.Correo(A,"Correo Regitrado","Este correo ha sido registrado en ASAP como un doctor");
                acceso="IndAdmin.jsp";
                }}
                else{
                    sesion.setAttribute("mensaje","Hora no disponible, este consultorio solamente está disponible antes de las "+HoIF2+" y después de las "+HoFF2);
                    sesion.setAttribute("link","registrar2.jsp");
                    acceso="mensaje.jsp";
                    }
                }
                else{
                    sesion.setAttribute("mensaje","Hora no disponible, este consultorio solamente está disponible antes de las "+HoIF2+" y después de las "+HoFF2);
                    sesion.setAttribute("link","registrar2.jsp");
                    acceso="mensaje.jsp";
                }}else{
                    sesion.setAttribute("mensaje","La hora de termino no puede ser mayor a la hora de inicio");
                    sesion.setAttribute("link","registrar2.jsp");
                    acceso="mensaje.jsp";
                }
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","registrar2.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Agregar Situacion")) {
            try {
                String NSit = request.getParameter("NSit");
                int Dur = Integer.parseInt(request.getParameter("Dur"));
                String E = request.getParameter("EspC");
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list2=dao.consultar12(NSit);
                Iterator<Persona>iter=list2.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                }
                if(per==null){
                p.setNombre(NSit);
                p.setApellidos(E);
                p.setNivel(Dur);
                pd.registrar2(p);
                acceso="IndAdmin.jsp";
                                }
                else{
                    sesion.setAttribute("mensaje","Situación ya resgistrada");
                    sesion.setAttribute("link","Situaciones.jsp");
                                        acceso="mensaje.jsp";
                }
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","Situaciones.jsp");
                acceso="mensaje.jsp";
            }
        }  else if (action.equalsIgnoreCase("Agregar Especialidad")) {
            try {
                processRequest(request, response);
                String NSit = request.getParameter("NEs");
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list2=dao.consultar11(NSit);
                Iterator<Persona>iter=list2.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                }
                if(per==null){
                p.setNombre(NSit);
                pd.registrar4(p);
                                acceso="IndAdmin.jsp";
                                }
                else{
                    sesion.setAttribute("mensaje","Especialidad ya resgistrada");
                    sesion.setAttribute("link","Especialidad.jsp");
                                        acceso="mensaje.jsp";
                }
           } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","Especialidad.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Agregar Consultorio")) {
            try {
                processRequest(request, response);
                String NSit = request.getParameter("NEs");
                PersonaDAO dao=new PersonaDAO();
                List<Persona>list2=dao.consultar13(NSit);
                Iterator<Persona>iter=list2.iterator();
                Persona per= null;
                while(iter.hasNext()){
                    per=iter.next();
                }
                if(per==null){
                p.setNombre(NSit);
                pd.registrar6(p);
                                acceso="IndAdmin.jsp";
                                }
                else{
                    sesion.setAttribute("mensaje","Consultorio ya resgistrado");
                    sesion.setAttribute("link","Consultorios.jsp");
                                        acceso="mensaje.jsp";
                }
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","Consultorios.jsp");
                acceso="mensaje.jsp";
            }
            }else if (action.equalsIgnoreCase("Editar")) {
            try {
                processRequest(request, response);
                PrintWriter out = response.getWriter();
                String N = request.getParameter("N");
                String A = request.getParameter("A");
                String C = request.getParameter("C");
                p.setNombre(cifradoCesar(N));
                p.setApellidos(cifradoCesar(A));
                p.setContra(cifradoCesar(C));
                String E=(String) sesion.getAttribute("usuarioSesion");
                pd.editarC(p, E);
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","main.jsp");
                acceso="mensaje.jsp";
            }
        } else if (action.equalsIgnoreCase("Cerrar Sesión")) {
            try {
                processRequest(request, response);
                PrintWriter out = response.getWriter();
                sesion.setAttribute("usuarioSesion", null);
                sesion.setAttribute("nivel", null);
                sesion.setAttribute("mensaje","sesion cerrada");
                sesion.setAttribute("link","index.jsp");
                acceso="mensaje.jsp";
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","index.jsp");
                acceso="mensaje.jsp";
            }
        }else if (action.equalsIgnoreCase("Eliminar")) {
            try {
                String Em = request.getParameter("Usu");
                pd.Eliminar(Em);
                pd.Eliminar2(Em);
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","IndAdmin.jsp");
                acceso="mensaje.jsp";
            }
        }
        else if (action.equalsIgnoreCase("Mensaje")) {
            try {
                String Em = request.getParameter("Usu");
                pd.Eliminar(Em);
                pd.Eliminar2(Em);
            } catch (Exception e) {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","index.jsp");
                acceso="mensaje.jsp";
            }
        }
        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public static String cifradoCesar(String texto) {
        int codigo = 3;
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else {
                cifrado.append((char) (texto.charAt(i) + codigo));
            }
        }
        return cifrado.toString();
    }

    public static String descifradoCesar(String texto) {
        int codigo = 3;
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else {
            cifrado.append((char) (texto.charAt(i) - codigo));
            }
        }
        return cifrado.toString();
    }
}
