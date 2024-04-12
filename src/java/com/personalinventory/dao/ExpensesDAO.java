/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.ExpensesBean;
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
public class ExpensesDAO {

    static Connection conn;

    public int addIncome(ExpensesBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into expenses (exp_ac,userid,exp_catid,amount,transaction_date,payby,remark) values ('" + ib.getExp_ac() + "','" + ib.getUserid() + "','" + ib.getExp_catid() + "','" + ib.getAmount() + "','" + ib.getTransaction_date() + "','" + ib.getPayby() + "','" + ib.getRemark() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int updateIncome(ExpensesBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update expenses set exp_ac='" + ib.getExp_ac() + "',exp_catid='" + ib.getExp_catid() + "',amount='" + ib.getAmount() + "',transaction_date='" + ib.getTransaction_date() + "',payby='" + ib.getPayby() + "',remark='" + ib.getRemark() + "' where userid='" + ib.getUserid() + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int deleteIncome(int userid) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from expenses where userid = '" + userid + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<ExpensesBean> findAllIncome() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses";
        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesBean ib = new ExpensesBean();
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
                ib.setRemark(rs.getString("remark"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public ExpensesBean findById(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses where userid='" + userid + "'";
        ExpensesBean ib = new ExpensesBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
                ib.setRemark(rs.getString("remark"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }

    public ArrayList<ExpensesBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesBean ib = new ExpensesBean();
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
                ib.setRemark(rs.getString("remark"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    public double findAmountById(String sdate, String edate,int userid) {
        conn = ConnectionPool.connectDB();
        double x = 0.0;
        String sql = "select sum(amount) as money from expenses where transaction_date between '" + sdate + "' and '" + edate + "' and userid='" + userid + "'";
        ExpensesBean ib = new ExpensesBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                x=rs.getDouble("money");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    public static void main(String[] args) {
        ExpensesBean eb = new ExpensesBean();
        ExpensesDAO ed = new ExpensesDAO();

       // double x = ed.findAmountById("2023-05-25", "2023-05-30", 1);
       //System.out.println("amount : "+x);
        
        //to add 
//        eb.setExp_ac("12345");
//        eb.setUserid(1232);
//        eb.setExp_catid("1232");
//        eb.setAmount(20000.23);
//        eb.setTransaction_date("2002/06/26");
//        eb.setPayby("Ranu Sharma");
//        eb.setRemark("This is for Rent");
//        int x = ed.addIncome(eb);
//        if(x>0){
//            System.out.println("Data add sucess");
//         }
//          to update
//        eb.setExp_ac("12345");
//        eb.setUserid(1232);
//        eb.setExp_catid("1323");
//        eb.setAmount(27000.23);
//        eb.setTransaction_date("26/06/2002");
//        eb.setPayby("Ranu Sharma");
//        eb.setRemark("This is for Rent");
//        int x = ed.updateIncome(eb);
//        if(x>0){
//            System.out.println("Update Sucesss");
//        }
//          to delete
//            int x = ed.deleteIncome(1232);
//            if(x>0){
//                System.out.println("Data delete sucess");
//            }
//          to find all
//            ArrayList<ExpensesBean> al = ed.findAllIncome();
//            for(ExpensesBean a:al){
//                System.out.println(" : "+a.getExp_id()+" : "+a.getExp_ac()+" : "+a.getUserid()+" : "+a.getExp_catid()+" : "+a.getAmount()+" : "+a.getTransaction_date()+ " : "+a.getPayby()+" : "+a.getRemark());
//            }
//          find by id
//            ExpensesBean a = ed.findById(1234);
//            if(a.getUserid()>0){
//                System.out.println(" : "+a.getExp_id()+" : "+a.getExp_ac()+" : "+a.getUserid()+" : "+a.getExp_catid()+" : "+a.getAmount()+" : "+a.getTransaction_date()+ " : "+a.getPayby()+" : "+a.getRemark());
//            }
//          find all date wise
            ArrayList<ExpensesBean> bl = ed.findAllDateWise("2021-06-01", "2021-06-30", 1234);
            for(ExpensesBean a:bl){
                System.out.println(" : "+a.getExp_id()+" : "+a.getExp_ac()+" : "+a.getUserid()+" : "+a.getExp_catid()+" : "+a.getAmount()+" : "+a.getTransaction_date()+ " : "+a.getPayby()+" : "+a.getRemark());
            }
    }
}
