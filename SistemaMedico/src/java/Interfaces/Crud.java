/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;
import Modelo.Persona;
import java.util.List;

public interface Crud {
    /*
   public List consultar();
   public List list(String Usu);
*/
   public List consultar2();
   public List consultar4();
   public List list(String Usu);
   public boolean registrar4(Persona per);
   public boolean registrar3(Persona per);
   public boolean registrar2(Persona per);
   public boolean registrar(Persona per);
   public boolean editar(Persona per);
   public boolean eliminar(int id);
   
}
