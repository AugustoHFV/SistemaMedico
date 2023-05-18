/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladordatos;

import Controlador.Controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Srvldatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

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
        processRequest(request, response);
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        Consultas op = new Consultas();
        HttpSession sesion = request.getSession();
        String A = (String) sesion.getAttribute("nivel");

        if (A == "1" || A == "2") {
            response.sendRedirect("editar.jsp");
        } else if (A == "3"){
            response.sendRedirect("IndAdmin.jsp");
        }
        else {
            response.sendRedirect("main.jsp");
        }
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
        PrintWriter out = response.getWriter();
        Consultas op = new Consultas();
        HttpSession sesion = request.getSession();
        Controlador con=new Controlador();

        String em =con.cifradoCesar(request.getParameter("email"));
        String pass = con.cifradoCesar(request.getParameter("password"));
        String m;
        String l;
        try {
            if (op.Login(em, pass) == 1) {
                sesion.setAttribute("nivel", "1");
                sesion.setAttribute("usuarioSesion", em);
                m="Sesion Iniciada";
                l="index.jsp";
            } else if (op.Login(em, pass) == 2) {
                m="Sesion Iniciada como médico";
                l="Medico.jsp";
                sesion.setAttribute("nivel", "2");
                sesion.setAttribute("usuarioSesion", em);
            } else if (op.Login(em, pass) == 3) {
                m="Sesion Iniciada como administrador";
                l="IndAdmin.jsp";
                sesion.setAttribute("nivel", "3");
                sesion.setAttribute("usuarioSesion", em);
            }else{
                m="Correo o Contraseña incorrectos";
                l="index.jsp";
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body onload = 'loaded();'></body>");
            out.println("<script>");
            out.println("function loaded() {");
            out.println("alert('"+m+"');");
            out.println("location.href ='"+l+"';");
            out.println("}");
            out.println("</script>");
            out.println("</html>");
        }
    catch (SQLException ex

    
        ) {
            Logger.getLogger(Srvldatos.class.getName()).log(Level.SEVERE, null, ex);
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
