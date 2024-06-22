/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import emart.dbutil.Dbconnection;
import emart.pojo.UserPojo;
import emart.pojo.UserProfile;
import java.sql.SQLException;


/**
 *
 * @author my family
 */
public class UserDAO {
    public static boolean validateUser(UserPojo user)throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userId=? and password=? and userType=?");
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            String userName=rs.getString(5);
            UserProfile.setUserName(userName);
            return true;
        }
        return false;
    }
    
    public static boolean isUserPresent(String empid) throws SQLException{
        
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select 1 from users where userId=?");
        ps.setString(1, empid);
        ResultSet rs=ps.executeQuery();
        return rs.next();
}
}

    
