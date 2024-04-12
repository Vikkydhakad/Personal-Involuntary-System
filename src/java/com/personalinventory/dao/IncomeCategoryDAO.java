/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.IncomeCategoryBean;
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
public class IncomeCategoryDAO {

    static Connection conn;

    public int addIncome(IncomeCategoryBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into income_category (inc_catname,inc_catdetails,userid) values('" + ib.getInc_catname() + "','" + ib.getInc_catdetails() + "','" + ib.getUserid() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int updateIncome(IncomeCategoryBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update income_category set inc_catname='" + ib.getInc_catname() + "',inc_catdetails='" + ib.getInc_catdetails() + "' where inc_catid='" + ib.getInc_catid() + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int deleteIncome(int id) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from income_category where inc_catid = '" + id + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<IncomeCategoryBean> findAllIncome() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category";
        ArrayList<IncomeCategoryBean> al = new ArrayList<IncomeCategoryBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomeCategoryBean ib = new IncomeCategoryBean();
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public ArrayList<IncomeCategoryBean> findAllIncomeByUserid(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category where userid="+userid+"";
        ArrayList<IncomeCategoryBean> al = new ArrayList<IncomeCategoryBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomeCategoryBean ib = new IncomeCategoryBean();
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public IncomeCategoryBean findById(int userid){
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category where userid='"+userid+"'";
        IncomeCategoryBean ib = new IncomeCategoryBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public IncomeCategoryBean findByCatId(int catid){
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category where inc_catid='"+catid+"'";
        IncomeCategoryBean ib = new IncomeCategoryBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }

    public static void main(String[] args) {
        IncomeCategoryBean ib = new IncomeCategoryBean();
        IncomeCategoryDAO id = new IncomeCategoryDAO();
//      add income category        
//        ib.setInc_catname("XYZ");
//        ib.setInc_catdetails("abc");
//        ib.setUserid(1234);
//        int x = id.addIncome(ib);
//        if(x>0){
//            System.out.println("Data insert Sucess");
//        }
//        else{
//            System.out.println("Data not added");
//        }

//        update income category
//          ib.setInc_catname("Rajesh Mehra");
//          ib.setInc_catdetails("Driving");
//          ib.setUserid(1234);
//          int x = id.updateIncome(ib);
//          if(x>0){
//              System.out.println("Data Update Sucess");
//          }
//          else{
//              System.out.println("Data Not Update");
//          }
//          delete incomecategory
//            int x = id.deleteIncome(1212);
//            if(x>0){
//                System.out.println("Data delete sucess");
//            }
//            else{
//                System.out.println("Data not deleted");
//            }
//            find all method
//        ArrayList<IncomeCategoryBean> al = id.findAllIncome();
//        for (IncomeCategoryBean a : al) {
//            System.out.println(a.getInc_catid() + " : "+a.getInc_catname()+" : " + a.getInc_catdetails() + " : " + a.getUserid());
//    }

//          find by id 

            
            IncomeCategoryBean a =id.findById(1234);
            if(a.getUserid()>0){
            System.out.println(a.getInc_catid() + " : "+a.getInc_catname()+" : " + a.getInc_catdetails() + " : " + a.getUserid());
        }
}
}
