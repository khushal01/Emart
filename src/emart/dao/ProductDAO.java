/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dao;

import emart.dbutil.Dbconnection;
import emart.pojo.ProductsPojo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author my family
 */
public class ProductDAO {
    public static String getNextProductId() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement s= conn.createStatement();
        ResultSet rs=s.executeQuery("select max(p_Id) from products");
        rs.next();
        String productId=rs.getString(1);
        if(productId==null)
            return "P101";
        int productNo=Integer.parseInt(productId.substring(1));
        productNo++;
        return "E"+productNo;
    }
    public static boolean addProduct(ProductsPojo p) throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,'Y')");
        ps.setString(1,p.getProductId());
        ps.setString(2,p.getProductName());
        ps.setString(3,p.getProductCompany());
        ps.setDouble(4,p.getProductPrice());
        ps.setDouble(5,p.getOurPrice());
        ps.setInt(6,p.getTax());
        ps.setInt(7,p.getQuantity());
        return ps.executeUpdate()==1;
    }
    public static List<ProductsPojo> getAllProductsDetails() throws SQLException{
        Connection conn=Dbconnection.getConnection();
        Statement s= conn.createStatement();
        ResultSet rs=s.executeQuery("select * from products where status ='Y' order by p_id");
        ArrayList<ProductsPojo> list=new ArrayList<>();
        while(rs.next()){
            ProductsPojo p=new ProductsPojo();
            
            list.add(p);
        }
        return list;
    }
    public static boolean deleteProduct(String productId) throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set status='N' where p_id=?");
        ps.setString(1,productId);
        return ps.ExecuteUpdate()==1;
    }
    public static boolean updateProduct(ProductsPojo p) throws SQLException{
        Connection conn=Dbconnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set p_name=?,p_companyName=?,p_price=?,p_ourPrice=?,p_tax=?,quantity=?");
        ps.setString(1,p.getProductName());
        ps.setString(2,p.getProductCompany());
        ps.setDouble(3,p.getProductPrice());
        ps.setDouble(4,p.getOurPrice());
        ps.setInt(5,p.getTax());
        ps.setInt(6,p.getQuantity());
    return ps.executeUpdate()==1;
}
}
