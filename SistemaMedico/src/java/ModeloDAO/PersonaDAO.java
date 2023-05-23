/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Controlador.Controlador;
import Interfaces.Crud;
import Modelo.Persona;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO implements Crud{
    
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    Persona p=new Persona();
    Conexion cn= new Conexion();
   
    public List consultar2() {
   
        ArrayList<Persona> list= new ArrayList<>();
        String sql="select * from Situaciones";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Persona per= new Persona();
                per.setNombre(rs.getString("nombre"));
                per.setApellidos(rs.getString("especialidad"));
                list.add(per);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }
    
        public List consultar3(String Es) {
   
        ArrayList<Persona> list= new ArrayList<>();
        String sql="select * from Doctores where especialidad='"+Es+"' order by FeProxC Desc, HoProxC Desc ";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Persona per= new Persona();
                per.setNombre(rs.getString("email"));
                per.setEmail(rs.getString("FeProxC"));
                per.setContra(rs.getString("HoProxC"));
                list.add(per);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }
   
    @Override
    public List consultar4() {
   
        ArrayList<Persona> list= new ArrayList<>();
        String sql="select * from Especialidad";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Persona per= new Persona();
                per.setNombre(rs.getString("nombre"));
                list.add(per);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }
    
    public List consultar5(String Sit) {
   
        ArrayList<Persona> list= new ArrayList<>();
        String sql="select * from Situaciones where nombre='"+Sit+"'";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Persona per= new Persona();
                per.setApellidos(rs.getString("especialidad"));
                per.setVer(rs.getString("duracion"));
                list.add(per);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }
    
    @Override
    public List list(String Usu) {
        ArrayList<Persona> list= new ArrayList<>();
          String sql="select * from Usuarios where email='"+Usu+"'";
         try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Persona per= new Persona();
                per.setNombre(rs.getString("nombre"));
                per.setApellidos(rs.getString("Apellidos"));
                per.setContra(rs.getString("Contra"));
                per.setEmail(rs.getString("Email"));
                list.add(per);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }

    @Override
    public boolean registrar(Persona per) {
        String sql="insert into Usuarios(nombre,apellidos,email,contra,nivel) values('"+per.getNombre()+"','"+per.getApellidos()+"','"+per.getEmail()+"','"+per.getContra()+"','"+per.getNivel()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    
    }

    public boolean registrar2(Persona per) {
        String sql="insert into Situaciones(nombre,duracion,especialidad) values('"+per.getNombre()+"','"+per.getNivel()+"','"+per.getApellidos()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    
    }
    
    public boolean registrar3(Persona per) {
        String sql="insert into Doctores(email,especialidad) values('"+per.getNombre()+"','"+per.getEspecialidad()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }
    
    public boolean registrar4(Persona per) {
        String sql="insert into Especialidad(nombre) values('"+per.getNombre()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }
    
        public boolean registrar5(Persona per) {
        String sql="insert into Citas(Paciente,Doctor,Situacion,hora,fecha) values('"+per.getNombre()+"','"+per.getApellidos()+"','"+per.getEmail()+"','"+per.getContra()+"','"+per.getVer()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }

    public boolean editar(Persona per) {
        String sql="update doctores set FeproxC='"+per.getVer()+"', HoproxC='"+per.getHora()+"' where email='"+per.getApellidos()+"'";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
