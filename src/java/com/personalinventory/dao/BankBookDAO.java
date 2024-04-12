/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.BankBookBean;
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
public class BankBookDAO {

    static Connection conn;

    public int add(BankBookBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into bank_book (account,transaction_date,amount,userid,operation) values('" + ib.getAccount() + "','" + ib.getTransaction_date() + "','" + ib.getAmount() + "','" + ib.getUserid() + "','" + ib.getOperation() + "')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public int update(BankBookBean ib) {
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update bank_book set account='" + ib.getAccount() + "',transaction_date='" + ib.getTransaction_date() + "',amount='" + ib.getAmount() + "',operation='" + ib.getOperation() + "' where userid='" + ib.getUserid() + "'";
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
        String sql = "delete from bank_book where userid = '" + userid + "'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<BankBookBean> findAll() {
        conn = ConnectionPool.connectDB();
        String sql = "select * from bank_book";
        ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BankBookBean ib = new BankBookBean();
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

    public BankBookBean findById(int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from bank_book where userid='" + userid + "'";
        BankBookBean ib = new BankBookBean();
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

    public ArrayList<BankBookBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from bank_book where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BankBookBean ib = new BankBookBean();
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
    public double closingBalance(String sDate,String eDate,int userid){
        double r = 0;
        conn=ConnectionPool.connectDB();
        String sql = "select (select sum(amount) as total_payment from bank_book b where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='"+userid+"' and operation='Receive') - (select sum(amount) as total_receive from bank_book b where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='"+userid+"' and operation='Pay')as closing_balance from dual;";
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
        BankBookBean bb = new BankBookBean();
        BankBookDAO bd = new BankBookDAO();

        //to add
//        bb.setAccount("123445");
//        bb.setTransaction_date("2021-07-09");
//        bb.setAmount(9422.23);
//        bb.setUserid(1234);
//        bb.setOperation("pay");
//        int x = bd.add(bb);
//        if(x>0){
//            System.out.println("Data add sucess");
//        }
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
//           int x = bd.delete(1235);
//           if(x>0){
//               System.out.println("Delete Sucess");
//           }
//           to find all
//            ArrayList<BankBookBean> al = bd.findAll();
//            for(BankBookBean a:al){
//                System.out.println(" : "+a.getAcid()+" : "+a.getAccount()+" : "+a.getTransaction_date()+" : "+a.getAmount()+" : "+a.getUserid()+" : "+a.getOperation());
//            }
//              to find by id
//               BankBookBean a = bd.findById(1234);
//               if(a.getUserid()>0){
//                   System.out.println(a.getAcid()+" : "+a.getAccount()+" : "+a.getTransaction_date()+" : "+a.getAmount()+" : "+a.getUserid()+" : "+a.getOperation());
//               }
//        ArrayList<BankBookBean> b = bd.findAllDateWise("2001-06-21", "2021-06-28", 1234);
//        for (BankBookBean a : b) {
//            System.out.println(" : " + a.getAcid() + " : " + a.getAccount() + " : " + a.getTransaction_date() + " : " + a.getAmount() + " : " + a.getUserid() + " : " + a.getOperation());
//        }
            double r = bd.closingBalance("2023-05-08","2023-06-29",1);
            System.out.println("Closing Balance : "+r);
          
    }
}
