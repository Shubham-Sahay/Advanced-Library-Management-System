/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package additional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import library_database.data_connect;

/**
 *
 * @author Shubham Sahay
 */
public class Fine
{

    Connection con;
    ResultSet rs1,rs2;
    PreparedStatement ps1,ps2;
    data_connect dc;
    int fine;
    
   public Fine()
    {
        con=dc.database_connect();
    }
    
 private int feb_day(int y)
 {
    int d;
    if(y%4==0)
    {
        d=29;
    }
    else 
    {
        d=28;
    }
    return d; 
 }
    
 private int month(int m,int y)
 {
     int d=0;
     switch(m)
     {
         case 1: d=31;
         case 2: d=feb_day(y);
         case 3: d=31;
         case 4: d=30;
         case 5: d=31;
         case 6: d=30;
         case 7: d=31;
         case 8: d=31;
         case 9: d=30;
         case 10: d=31;
         case 11: d=30;
         case 12: d=31;
     }
         
     return d;
 }
 
 private int year(int y)
 {
     int d;
     if(y%4==0)
         d=366;
     else
         d=365;
     return d;
 }
    
 private int date_diff(int id)
 {
    int di = 0,dr = 0,mi = 0,mr = 0,yi = 0,yr = 0,diff;
    
    String sql1="select * from issue where Book_ID = "+id+"";
    String sql2="select * from returnbook where Book_ID = "+id+"";
    try
    {
        
        ps1=con.prepareStatement(sql1);
        ps2=con.prepareStatement(sql2);
        rs1=ps1.executeQuery();
        rs2=ps2.executeQuery();
        if(rs1.next())
        {
            di=rs1.getInt(15);
            mi=rs1.getInt(16);
            yi=rs1.getInt(17);
        }
        if(rs2.next())
        {
            dr=rs1.getInt(16);
            mr=rs1.getInt(17);
            yr=rs1.getInt(18);   
        }       
        ps1.close();
        ps2.close();
        rs1.close();
        rs2.close();
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
    
    mi=mi*month(mi,yi);
    mr=mr*month(mr,yr);
    yi=yi*year(yi);
    yr=yr*year(yr);
    
    int totalr=dr+mr+yr,totali=di+mi+yi;
    diff=totalr-totali;
    
     return diff;
 }
 
 public void fine_calc(int id)
 {
     int days;
     days = date_diff(id);
     System.out.println(days);
     if(days>20)
     {
         days=days-20;
         fine=days*5;
     }
     else
     {
         fine=0;
     }
     String sql="Insert into library_fine (Book_ID,Fine) values("+id+","+fine+")";
     try{
          ps1=con.prepareStatement(sql);
          ps1.execute();
          ps1.close();
     }
     catch(Exception e)
     {
         JOptionPane.showMessageDialog(null, e);
     }  
         
 }
 
 public int lost(int bprice)
 {
     fine=fine+bprice;
     return fine;
 }
 
}
