/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.ExpensesCategoryBean;
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
public class ExpensesCategoryDAO {

    static Connection conn;

    public int add(ExpensesCategoryBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into expenses_category (exp_catname,exp_catdetails,userid) values ('" + ib.getExp_catname() + "','" + ib.getExp_catdetails() + "','" + ib.getUserid() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int update(ExpensesCategoryBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update expenses_category set exp_catname='" + ib.getExp_catname() + "',exp_catdetails='" + ib.getExp_catdetails() + "' where userid = '" + ib.getUserid() + "'";
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
        String sql = "delete from expenses_category where exp_catid = '" + userid + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<ExpensesCategoryBean> findAll() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses_category";
        ArrayList<ExpensesCategoryBean> al = new ArrayList<ExpensesCategoryBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesCategoryBean ib = new ExpensesCategoryBean();
                ib.setExp_catid(rs.getInt("exp_catid"));
                ib.setExp_catname(rs.getString("exp_catname"));
                ib.setExp_catdetails(rs.getString("exp_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public ExpensesCategoryBean findById(int userid){
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses_category where userid='"+userid+"'";
        ExpensesCategoryBean ib = new ExpensesCategoryBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                ib.setExp_catid(rs.getInt("exp_catid"));
                ib.setExp_catname(rs.getString("exp_catname"));
                ib.setExp_catdetails(rs.getString("exp_catdetails"));
                ib.setUserid(rs.getInt("userid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public ArrayList<ExpensesCategoryBean> findAllIncomeByUserid(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses_category where userid="+userid+"";
        ArrayList<ExpensesCategoryBean> al = new ArrayList<ExpensesCategoryBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesCategoryBean ib = new ExpensesCategoryBean();
                ib.setExp_catid(rs.getInt("exp_catid"));
                ib.setExp_catname(rs.getString("exp_catname"));
                ib.setExp_catdetails(rs.getString("exp_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public static void main(String[] args) {
        ExpensesCategoryBean eb = new ExpensesCategoryBean();
        ExpensesCategoryDAO ed = new ExpensesCategoryDAO();

        //to add
//        eb.setExp_catname("Shopping");
//        eb.setExp_catdetails("xlasfja");
//        eb.setUserid(1234);
//        int x = ed.add(eb);
//        if(x>0){
//            System.out.println("Data insert sucess");
//        }
//          to udpate
//        eb.setExp_catname("Shopping");
//        eb.setExp_catdetails("Television");
//        eb.setUserid(1234);
//        int x = ed.update(eb);
//        if(x>0){
//            System.out.println("Data update sucess");
//        }

//          to delete
//            int x = ed.delete(1235);
//            if(x>0){
//                System.out.println("Data delete sucess");
//            }


//          to find all
//            ArrayList<ExpensesCategoryBean> a = ed.findAll();
//            for(ExpensesCategoryBean e:a){
//                System.out.println(" : "+e.getExp_catid()+" : "+e.getExp_catname()+" : "+e.getExp_catdetails()+" : "+e.getUserid());
//            }


//              to find by id
               ExpensesCategoryBean e = ed.findById(1234);
               if(e.getUserid()>0){
                   System.out.println(" : "+e.getExp_catid()+" : "+e.getExp_catname()+" : "+e.getExp_catdetails()+" : "+e.getUserid());
               }
    }
}
