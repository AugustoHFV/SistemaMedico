/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author werna
 */
public class Fecha {
    static Calendar Hora= Calendar.getInstance();
    static Calendar Fecha= Calendar.getInstance();
        
    public int Hora(){
    int hora, minutos,Horatotal;
    hora=Hora.get(Calendar.HOUR_OF_DAY);
    minutos= Hora.get(Calendar.MINUTE);
    Horatotal=(hora*60)+minutos+60;
    System.out.println(Horatotal);
    return Horatotal;
    }
    
    
    public String Fecha(){
        int dia, mes, año;
        dia=Fecha.get(Calendar.DATE);
        mes=Fecha.get(Calendar.MONTH);
        if(mes<12){
            mes=mes+1;
        }else{
            mes=1;
        }
        año=Fecha.get(Calendar.YEAR);
        String fecha=año+"-"+"0"+mes+"-"+dia;
        return fecha;
    }
    
    public String FechaM(){
        int dia, mes, año;
        dia=Fecha.get(Calendar.DATE);
        mes=Fecha.get(Calendar.MONTH);
        if(mes<12){
            mes=mes+1;
        }else{
            mes=1;
        }
        año=Fecha.get(Calendar.YEAR);
        if(dia>=28)
        {
            if(mes==2)
            {
                mes=mes+1;
                dia=1;
            }
            else if(mes==4 ||mes==6 ||mes==9 ||mes==11){
                dia=dia+1;
                if(dia>30){
                    mes=mes+1;
                    dia=1;
                }
            }else if(mes==12){
                dia=dia+1;
                if(dia>31){
                    mes=1;
                    dia=1;
                }
            }else{
                dia=dia+1;
                if(dia>31){
                    mes=mes+1;
                    dia=1;
                }
            }
        }else{
            dia=dia+1;
        }
        String fecha=año+"-"+mes+"-"+dia;
        return fecha;
    }
    
    public int Comp(String Fe){
        int n;
        String Fe2=Fecha();
        try{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(Fe);
        System.out.println(Fe2);
        Date Fecha1= sdf.parse(Fe2);
        Date Fecha2= sdf.parse(Fe);
        if (Fecha1.after(Fecha2)){
            n=1;
        }
        else{
            n=2;
        }
        }catch(Exception e){
            n=0;
        }
        System.out.println(n);
        return n;
    }

    public String FechaS(int año, int mes, int dia){
        if(dia>=28)
        {
            if(mes==2)
            {
                mes=mes+1;
                dia=1;
            }
            else if(mes==4 ||mes==6 ||mes==9 ||mes==11){
                dia=dia+1;
                if(dia>30){
                    mes=mes+1;
                    dia=1;
                }
            }else if(mes==12){
                dia=dia+1;
                if(dia>31){
                    mes=1;
                    dia=1;
                }
            }else{
                dia=dia+1;
                if(dia>31){
                    mes=mes+1;
                    dia=1;
                }
            }
        }else{
            dia=dia+1;
        }
        String fecha=año+"-"+mes+"-"+dia;
        return fecha;
    }
}
