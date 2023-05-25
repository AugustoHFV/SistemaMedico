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
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("consultar")) {
            acceso = consultar;
        } else if (action.equalsIgnoreCase("registrar")) {
            acceso = registrar;
        } else if (action.equalsIgnoreCase("Agendar Cita")) {
            HttpSession sesion = request.getSession();
            Fecha F=new Fecha();
            String S=request.getParameter("Sit");
            PersonaDAO dao=new PersonaDAO();
            List<Persona>list4=dao.consultar9(S);
            Iterator<Persona>iter4=list4.iterator();
            Persona per4= null;
            while(iter4.hasNext()){
                per4=iter4.next();}
            if(per4==null)
            {
                sesion.setAttribute("mensaje","Ocurrió un error");
                sesion.setAttribute("link","index.jsp");
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
            String td=per3.getVer();
            int dur=Integer.parseInt(td);
            List<Persona>list=dao.consultar3(Es);
            Iterator<Persona>iter2=list.iterator();
            Persona per2= null;
            while(iter2.hasNext()){
                per2=iter2.next();}
            String ND=per2.getNombre();
            String Fe=per2.getEmail();
            p.setNombre(N);
            p.setEmail(S);
            p.setApellidos(ND);
            int aux=360;
            int aux2=1260;
            if (Fe==null){
                int hora=F.Hora();
                String Fe2=F.Fecha();
                int hora2=hora+dur;
                if(hora<aux){
                    hora=aux;
                    hora2=hora+dur;
                }
                if(hora2>aux2){
                    Fe2=F.FechaM();
                    hora=aux;
                    hora2=hora+dur;
                }
                p.setVer(Fe2);
                p.setContra(Integer.toString(hora));
                p.setHora(hora2);
                pd.registrar5(p);
                pd.editar(p);
            }else{
                int Ho=Integer.parseInt(per2.getContra());
                int Cont= F.Comp(Fe);
                if(Cont==1){
                    String Fe2=F.Fecha();
                    if(Ho>F.Hora()){
                        int hora2=Ho+dur;
                        if(hora2>aux2){
                            Fe2=F.FechaM();
                            Ho=aux;
                            hora2=Ho+dur;
                        }
                        p.setVer(Fe2);
                        p.setContra(Integer.toString(Ho));
                        p.setHora(hora2);
                        pd.registrar5(p);
                        pd.editar(p);
                    }
                    else{
                        int hora=F.Hora();
                        int hora2=hora+dur;
                        if(hora<aux){
                            hora=aux;
                            hora2=hora+dur;
                        }
                        if(hora2>aux2){
                            Fe2=F.FechaM();
                            hora=aux;
                            hora2=hora+dur;
                        }
                        p.setVer(Fe2);
                        p.setContra(Integer.toString(hora));
                        p.setHora(hora2);
                        pd.registrar5(p);
                        pd.editar(p);
                    }
                }else if(Cont==2){
                    String[] items = Fe.split("-");
                    String FeF=Fe;
                    int hora2=Ho+dur;
                    if(Ho<aux){
                            Ho=aux;
                            hora2=Ho+dur;
                    }
                    if(hora2>aux2){
                            int año=Integer.parseInt(items[0]);
                            int mes=Integer.parseInt(items[1]);
                            int dia=Integer.parseInt(items[2]);
                            FeF=F.FechaS(año, mes, dia);
                            FeF=F.FechaM();
                            Ho=aux;
                            hora2=Ho+dur;
                    }
                    p.setVer(FeF);
                    p.setContra(Integer.toString(Ho));
                    p.setHora(hora2);
                    pd.registrar5(p);
                    pd.editar(p);
                }
            }
            acceso="modificar.jsp";
            }
            
        }else if (action.equalsIgnoreCase("Crear")) {
            try {
                HttpSession sesion = request.getSession();
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = cifradoCesar(request.getParameter("email"));
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
                System.out.println(p.getVer());
                if(p.getVer()==null)
                    {
                        int Ni=1;
                        p.setNombre(N);
                        p.setApellidos(Ap);
                        p.setEmail(A);
                        p.setContra(CE);
                        p.setNivel(Ni);
                        pd.registrar(p);
                        sesion.setAttribute("mensaje","Cuenta creada");
                        sesion.setAttribute("link","main.jsp");
                        acceso="mensaje.jsp";
                    }
                p.setVer(null);
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        } else if (action.equalsIgnoreCase("Agregar Doctor")) {
            try {
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = cifradoCesar(request.getParameter("email"));
                String CE = cifradoCesar(request.getParameter("password"));
                String Es= request.getParameter("Esp");
                int Ni=2;
                p.setNombre(N);
                p.setApellidos(Ap);
                p.setEmail(A);
                p.setContra(CE);
                p.setNivel(Ni);
                p.setEspecialidad(Es);
                pd.registrar(p);
                pd.registrar3(p);
                acceso="IndAdmin.jsp";
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        } else if (action.equalsIgnoreCase("Agregar Situacion")) {
            try {
                String NSit = request.getParameter("NSit");
                int Dur = Integer.parseInt(request.getParameter("Dur"));
                String E = request.getParameter("EspC");
                p.setNombre(NSit);
                p.setApellidos(E);
                p.setNivel(Dur);
                pd.registrar2(p);
                acceso="Situaciones.jsp";
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        }  else if (action.equalsIgnoreCase("Agregar Especialidad")) {
            try {
                String NSit = request.getParameter("NEs");
                p.setNombre(NSit);
                pd.registrar4(p);
                acceso="IndAdmin.jsp";
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        } else if (action.equalsIgnoreCase("Editar")) {
            try {
                processRequest(request, response);
                PrintWriter out = response.getWriter();
                HttpSession sesion = request.getSession();
                String N = request.getParameter("N");
                String A = request.getParameter("A");
                String CE = request.getParameter("CE");
                String U = request.getParameter("U");
                String C = request.getParameter("C");
                String U1 = (String) sesion.getAttribute("usuarioSesion");
                sesion.setAttribute("usuarioSesion", U);
                sesion.setAttribute("contra", C);
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        } else if (action.equalsIgnoreCase("Cerrar Sesión")) {
            try {
                processRequest(request, response);
                PrintWriter out = response.getWriter();
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuarioSesion", null);
                sesion.setAttribute("nivel", null);
                acceso="main.jsp";
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        }else if (action.equalsIgnoreCase("Eliminar")) {
            try {
                String Em = request.getParameter("Usu");
                pd.Eliminar(Em);
                pd.Eliminar2(Em);
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
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
