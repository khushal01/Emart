/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dao;

import emart.dbutil.Dbconnection;
import emart.pojo.ReceptionistPojo;
import emart.pojo.UserPojo;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author my family
 */
public class ReceptionistDAO {
    public static Map<String,String> getNonRegisteredReceptionist() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empId,empName from employees where job='receptionist' and empId not in(select empId from users where usertype='Receptionist')");
        HashMap<String,String> receptionList= new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionList.put(id, name);
        }
        return receptionList;
    }
    
    public static boolean addReceptionist(UserPojo uj) throws SQLException{
       Connection conn=Dbconnection.getConnection();
            PreparedStatement ps= conn.prepareStatement("insert into users values(?,?,?,?,?)");
            ps.setString(1, uj.getUserId());
            ps.setString(2, uj.getEmpId());
            ps.setString(3, uj.getPassword());
            ps.setString(4, uj.getUserType());
            ps.setString(5, uj.getUserName());
            
            int result=ps.executeUpdate();
            return result==1;
            
        }
    public static List<ReceptionistPojo> getAllReceptionistDetails() throws SQLException{
            Connection conn=Dbconnection.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select users.empId,userName,userId,job,salary from users,employees where userType='Receptionist' and users.empId=employees.empid order by empid");
            ArrayList<ReceptionistPojo> recepList=new ArrayList<>();
            
            while(rs.next()){
                ReceptionistPojo rp = new ReceptionistPojo();
                rp.setEmpId(rs.getString(1));
                rp.setEmpName(rs.getString(2));
                rp.setUserId(rs.getString(3));
                rp.setJob(rs.getString(4));
                rp.setSalary(rs.getString(5));
                
                recepList.add(rp);
            }
            return recepList;
    }
    public static Map<String,String> getAllReceptionistId() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select userId,UserName  where usertype='Receptionist'order by userid");
        HashMap<String,String> receptionList= new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionList.put(id, name);
        }
        return receptionList;
    }
    public static boolean updatePassword(String userId,String pwd) throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update users set password=? where userId=?");
        ps.setString(1,pwd);
        ps.setString(2,userId);
        return ps.executeUpdate()==1;
    }
    public static List<String> getAllReceptionstId() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select userid from where userType='Receptionist' order by userId");
        List <String>recepIdList = new ArrayList<>();
        while(rs.next()){
            recepIdList.add(rs.getString(1));
        }
        return recepIdList;
    }
    public static boolean deleteReceptionist(String userId) throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from users where userId=?");
        ps.setString(1,userId);
        return ps.executeUpdate()==1;
    }
    }

