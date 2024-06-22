/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dao;

import java.sql.Statement;
import java.sql.Connection;
import emart.dbutil.Dbconnection;
import emart.pojo.EmployeePojo;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author my family
 */
public class EmployeeDAO {
    public static String getNextEmpId() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement s= conn.createStatement();
        ResultSet rs=s.executeQuery("select max(empId) from employees");
        rs.next();
        String empId=rs.getString(1);
        int empNo=Integer.parseInt(empId.substring(1));
        empNo++;
        return "E"+empNo;
    }
    
     public static boolean addEmployee(EmployeePojo emp) throws SQLException{
            Connection conn=Dbconnection.getConnection();
            PreparedStatement ps= conn.prepareStatement("insert into employees values(?,?,?,?)");
            ps.setString(1, emp.getEmpId());
            ps.setString(2, emp.getEmpName());
            ps.setDouble(4, emp.getSalary());
            ps.setString(3, emp.getJob());
            
            int result=ps.executeUpdate();
            return result==1;
            
        }
     
     public static List<EmployeePojo> getAllEmployees() throws SQLException{
         
         Connection conn=Dbconnection.getConnection();
         Statement s= conn.createStatement();
        ResultSet rs=s.executeQuery("select * from employees");   
            
            ArrayList<EmployeePojo> empList= new ArrayList<>();
            
            while(rs.next()){
                
                EmployeePojo emp = new EmployeePojo();
                emp.setEmpId(rs.getString(1));
            emp.setEmpName(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));
            empList.add(emp);
     }
            return empList;
     }
     
     public static List<String> getAllEmpId() throws SQLException{
         Connection conn=Dbconnection.getConnection();
         Statement s= conn.createStatement();
        ResultSet rs=s.executeQuery("select empId from employees"); 
        
        ArrayList<String> empIdList= new ArrayList<>();
        
        while(rs.next()){
            empIdList.add(rs.getString(1));
        }
        return empIdList;
     }
     
     public static EmployeePojo findByEmpId(String empId) throws SQLException{
         Connection conn=Dbconnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select * from employees where empId=?");
         ps.setString(1, empId);
         ResultSet rs=ps.executeQuery();
         rs.next();
         
         EmployeePojo emp=new EmployeePojo();
         emp.setEmpId(rs.getString(1));
         emp.setEmpName(rs.getString(2));
         emp.setJob(rs.getString(3));
         emp.setSalary(Double.parseDouble(rs.getString(4)));
         
         return emp;
     }
     
     public static boolean updateEmployee(EmployeePojo emp) throws SQLException{
         Connection conn=Dbconnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("update employees set empName=?,job=?,salary=? where empId=?");
         ps.setString(1,emp.getEmpName());
         ps.setString(2,emp.getJob());
         ps.setDouble(3,emp.getSalary());
         ps.setString(4,emp.getEmpId());
         int x=ps.executeUpdate();
         
         if(x==0)
             return false;
         else{ 
             
             boolean result= UserDAO.isUserPresent(emp.getEmpId());
             if(result==false)
                 return true; 
          ps=conn.prepareStatement("update users set userName=?,userType=? where userId=?");
          ps.setString(1, emp.getEmpName());
          ps.setString(2,emp.getJob());
          ps.setString(3,emp.getEmpId());
          int y=ps.executeUpdate();
          return y==1;
     }
     }
     
     public static boolean deleteEmployees(String empId) throws SQLException{
         Connection conn=Dbconnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("delete from employees where empId=?");
         ps.setString(1, empId);
         int x=ps.executeUpdate();
         return x==1;
     }
}
