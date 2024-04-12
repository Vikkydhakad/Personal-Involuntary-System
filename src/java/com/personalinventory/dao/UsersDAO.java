/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.UsersBean;
import com.personalinventorysystem.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class UsersDAO {

    static Connection conn;

    public int add(UsersBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into users (username,password,name,address,mobile,email) values ('" + ib.getUsername() + "','" + ib.getPassword() + "','" + ib.getName() + "','" + ib.getAddress() + "','" + ib.getMobile() + "','" + ib.getEmail() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int update(UsersBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update users set username='"+ib.getUsername()+"',password='"+ib.getPassword()+"',name='"+ib.getName()+"',address='"+ib.getAddress()+"',mobile='"+ib.getMobile()+"',email='"+ib.getEmail()+"' where  userid='"+ib.getUserid()+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int delete(int userid) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from users where userid = '" + userid + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<UsersBean> findAll() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from users";
        ArrayList<UsersBean> al = new ArrayList<UsersBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersBean ib = new UsersBean();
                ib.setUserid(rs.getInt("userid"));
                ib.setUsername(rs.getString("username"));
                ib.setPassword(rs.getString("password"));
                ib.setName(rs.getString("name"));
                ib.setAddress(rs.getString("address"));
                ib.setMobile(rs.getString("mobile"));
                ib.setEmail(rs.getString("email"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public UsersBean findById(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from users where userid='" + userid + "'";
        UsersBean ib = new UsersBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
               ib.setUserid(rs.getInt("userid"));
                ib.setUsername(rs.getString("username"));
                ib.setPassword(rs.getString("password"));
                ib.setName(rs.getString("name"));
                ib.setAddress(rs.getString("address"));
                ib.setMobile(rs.getString("mobile"));
                ib.setEmail(rs.getString("email")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public UsersBean findByName(String username) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from users where username='" + username + "'";
        UsersBean ib = new UsersBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
               ib.setUserid(rs.getInt("userid"));
                ib.setUsername(rs.getString("username"));
                ib.setPassword(rs.getString("password"));
                ib.setName(rs.getString("name"));
                ib.setAddress(rs.getString("address"));
                ib.setMobile(rs.getString("mobile"));
                ib.setEmail(rs.getString("email")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    
    public int loginCheck(String username,String password){
        conn=ConnectionPool.connectDB();
        int r = 0;
        String sql = "select userid from users where username='"+username+"' and password='"+password+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                r = rs.getInt("userid");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public static void main(String[] args) {
        UsersBean ub = new UsersBean();
        UsersDAO ud = new UsersDAO();
        
        //login check
//        int x = ud.loginCheck("Anand123", "12345");
//        if(x>0){
//            System.out.println("Login Sucess");
//        }
//        else{
//            System.out.println("Login Failed");
//        }

        //to add
//        ub.setUsername("Rajesh123");
//        ub.setPassword("123456");
//        ub.setName("Rajesh singh");
//        ub.setAddress("Bhopal");
//        ub.setMobile("9988776655");
//        ub.setEmail("Email@gamil.com");
//        int x = ud.add(ub);
//        if (x > 0) {
//            System.out.println("Add Sucess");
//        }

//         to update

        
        ub.setUsername("Rajesh456");
        ub.setPassword("123456");
        ub.setName("Rajesh singh");
        ub.setAddress("Bhopal");
        ub.setMobile("9988776655");
        ub.setEmail("Rajesh@gamil.com");
        ub.setUserid(2);
        int x = ud.update(ub);
        if(x>0){
            System.out.println("Data upadte sucess");
        }


//         to delete
//            int x = ud.delete(1235);
//            if(x>0){
//                System.out.println("Data delete Sucess");
//            }


//          to find all

//           ArrayList<UsersBean> al = ud.findAll();
//           for(UsersBean a:al){
//               System.out.println(a.getUserid()+" : "+a.getUsername()+" : "+a.getPassword()+" : "+a.getName()+" : "+a.getAddress()+" : "+a.getMobile()+" : "+a.getEmail());
//           }

//          to find by id

//            UsersBean a = ud.findById(1234);
//            if(a.getUserid()>0){
//                System.out.println(a.getUserid()+" : "+a.getUsername()+" : "+a.getPassword()+" : "+a.getName()+" : "+a.getAddress()+" : "+a.getMobile()+" : "+a.getEmail());
//            }
    }
}
