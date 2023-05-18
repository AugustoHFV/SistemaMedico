/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            String S=request.getParameter("Sit");
            System.out.println(S);
        }else if (action.equalsIgnoreCase("Crear")) {
            try {
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = cifradoCesar(request.getParameter("email"));
                String CE = cifradoCesar(request.getParameter("password"));
                int Ni=1;
                p.setNombre(N);
                p.setApellidos(Ap);
                p.setEmail(A);
                p.setContra(CE);
                p.setNivel(Ni);
                pd.registrar(p);
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        } else if (action.equalsIgnoreCase("Agregar Doctor")) {
            try {
                String N = cifradoCesar(request.getParameter("name"));
                String Ap = cifradoCesar(request.getParameter("ape"));
                String A = cifradoCesar(request.getParameter("email"));
                String CE = cifradoCesar(request.getParameter("password"));
                int Ni=2;
                p.setNombre(N);
                p.setApellidos(Ap);
                p.setEmail(A);
                p.setContra(CE);
                p.setNivel(Ni);
                pd.registrar(p);
                acceso="IndAdmin.jsp";
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }
        }else if (action.equalsIgnoreCase("Editar")) {
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
                sesion.setAttribute("contra", null);
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
