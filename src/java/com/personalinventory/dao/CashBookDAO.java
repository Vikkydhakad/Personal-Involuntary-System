/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.CashBookBean;
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
public class CashBookDAO {

    static Connection conn;

    public int add(CashBookBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into cash_book (account,transaction_date,amount,userid,operation) values('" + ib.getAccount() + "','" + ib.getTransaction_date() + "','" + ib.getAmount() + "','" + ib.getUserid() + "','" + ib.getOperation() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int update(CashBookBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update cash_book set account='" + ib.getAccount() + "',transaction_date='" + ib.getTransaction_date() + "',amount='" + ib.getAmount() + "',operation='" + ib.getOperation() + "' where userid='" + ib.getUserid() + "'";
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
        String sql = "delete from cash_book where userid = '" + userid + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<CashBookBean> findAll() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from cash_book";
        ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CashBookBean ib = new CashBookBean();
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public CashBookBean findById(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from cash_book where userid='" + userid + "'";
        CashBookBean ib = new CashBookBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }

    public ArrayList<CashBookBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from cash_book where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CashBookBean ib = new CashBookBean();
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    public double closingBalance(String sdate,String edate,int userid){
        double r = 0;
        conn=ConnectionPool.connectDB();
        String sql = "select (select sum(amount) as total_payment from cash_book b where transaction_date between '" + sdate + "' and '" + edate + "' and userid='"+userid+"' and operation='Receive') - (select sum(amount) as total_receive from cash_book b where transaction_date between '" + sdate + "' and '" + edate + "' and userid='"+userid+"' and operation='Pay')as closing_balance from dual";
        try {
            Statement stmt = conn.createStatement();
            ResultSet q=stmt.executeQuery(sql);
            if(q.next()){
                r=q.getDouble("closing_balance");
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
           
    }

    public static void main(String[] args) {
        //to add
        CashBookBean bb = new CashBookBean();
        CashBookDAO bd = new CashBookDAO();
        bb.setAccount("123445");
        bb.setTransaction_date("2021/6/23");
        bb.setAmount(23422.23);
        bb.setUserid(1234);
        bb.setOperation("operation");
        int x = bd.add(bb);
        if (x > 0) {
            System.out.println("Data add sucess");
        }
        //         to update
//        bb.setAccount("123445");
//        bb.setTransaction_date("23/6/2021");
//        bb.setAmount(23422.23);
//        bb.setUserid(1235);
//        bb.setOperation("coperation");
//        int x = bd.update(bb);
//        if(x>0){
//            System.out.println("Update sucess");
//        }
//         to delete
//        int x = bd.delete(1235);
//        if (x > 0) {
//            System.out.println("Delete Sucess");
//        }
//           to find all
//            ArrayList<CashBookBean> al = bd.findAll();
//            for(CashBookBean a:al){
//                System.out.println(" : "+a.getAcid()+" : "+a.getAccount()+" : "+a.getTransaction_date()+" : "+a.getAmount()+" : "+a.getUserid()+" : "+a.getOperation());
//            }
//              to find by id

//               CashBookBean a = bd.findById(1234);
//               if(a.getUserid()>0){
//                   System.out.println(a.getAcid()+" : "+a.getAccount()+" : "+a.getTransaction_date()+" : "+a.getAmount()+" : "+a.getUserid()+" : "+a.getOperation());
//               }
//        ArrayList<CashBookBean> b = bd.findAllDateWise("2001-06-21", "2021-06-28", 1234);
//        for (CashBookBean a : b) {
//            System.out.println(" : " + a.getAcid() + " : " + a.getAccount() + " : " + a.getTransaction_date() + " : " + a.getAmount() + " : " + a.getUserid() + " : " + a.getOperation());
//        }

            // double r = bd.closingBalance("2023-05-25","2023-06-06",1);
           // System.out.println("Closing Balance : "+r);
          

    }
}
