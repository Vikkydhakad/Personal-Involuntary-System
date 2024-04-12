/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.dao;

import com.personalinventory.bean.IncomeBean;
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
public class IncomeDAO {
    static Connection conn;
    
    public int addIncome(IncomeBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into incomes (inc_ac,userid,inc_catid,amount,transaction_date,reveiveby,remark) values ('"+ib.getInc_ac()+"','"+ib.getUserid()+"','"+ib.getInc_catid()+"','"+ib.getAmount()+"','"+ib.getTransaction_date()+"','"+ib.getReceiveby()+"','"+ib.getRemark()+"')";
        try {
            Statement stmt = conn.createStatement();
            r= stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    public int updateIncome(IncomeBean ib){
        int r = 0;
        conn= ConnectionPool.connectDB();
        String sql = "update incomes set inc_id='"+ib.getInc_id()+"',inc_ac='"+ib.getInc_ac()+"',inc_catid='"+ib.getInc_catid()+"',amount='"+ib.getAmount()+"',transaction_date='"+ib.getTransaction_date()+"',reveiveby='"+ib.getReceiveby()+"',remark='"+ib.getRemark()+"' where userid='"+ib.getUserid()+"';";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public int deleteIncome(int userid){
        int r=0;
        conn= ConnectionPool.connectDB();
        String sql = "delete from incomes where userid = '"+userid+"'";
        try {
            Statement stmt = conn.createStatement();
            r=stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public ArrayList<IncomeBean> findAllIncome(){
    conn = ConnectionPool.connectDB();
    String sql = "select * from incomes";
    ArrayList<IncomeBean> al = new ArrayList<IncomeBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                IncomeBean ib = new IncomeBean();
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("reveiveby"));
                ib.setRemark(rs.getString("remark"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return al;
}
    public IncomeBean findById(int userid){
        conn = ConnectionPool.connectDB();
        String sql = "select * from incomes where userid='"+userid+"'";
        IncomeBean ib = new IncomeBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("reveiveby"));
                ib.setRemark(rs.getString("remark"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    
    public ArrayList<IncomeBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from incomes where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<IncomeBean> al = new ArrayList<IncomeBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomeBean ib = new IncomeBean();
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("reveiveby"));
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
        String sql = "select sum(amount) as money from incomes where transaction_date between '" + sdate + "' and '" + edate + "' and userid='" + userid + "'";
        IncomeBean ib = new IncomeBean();
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
//        IncomeBean ib = new IncomeBean();
//        IncomeDAO id = new IncomeDAO();
//       // to add the income details        
//        ib.setInc_ac("1234");
//        ib.setUserid(1232);
//        ib.setInc_catid(2873);
//        ib.setAmount(20345.03);
//        ib.setTransaction_date("23/06/2021");
//        ib.setReceiveby("xyz");
//        ib.setRemark("remarks");
//        
//        int x = id.addIncome(ib);
//        if(x>0){
//            System.out.println("Data Add Sucess");
//        }
//        else{
//            System.out.println("Data not added");
//        }


//         update income
//        IncomeBean ib = new IncomeBean();
//        IncomeDAO id = new IncomeDAO();
//        ib.setInc_id(3);
//        ib.setInc_ac("1232");
//        ib.setInc_catid(2872);
//        ib.setAmount(20000.32);
//        ib.setTransaction_date("26,06,2002");
//        ib.setReceiveby("DJ Mishra");
//        ib.setRemark("This is Fees");
//        ib.setUserid(1232);
//        int x = id.updateIncome(ib);
//        if(x>0){
//            System.out.println("Income update sucess");
//        }
//        else{
//            System.out.println("Income not updated");
//        }
//        delete income
//        IncomeBean ib = new IncomeBean();
//        IncomeDAO id = new IncomeDAO();
//        int x = id.deleteIncome(1232);
//        if(x>0){
//            System.out.println("Data delete sucess");
//        }
//        else{
//            System.out.println("Data not delete");
//        }


//      FindAll income
//        IncomeBean ib = new IncomeBean();
//        IncomeDAO id = new IncomeDAO();
//        ArrayList<IncomeBean> al = id.findAllIncome();
//        for(IncomeBean a:al){
//            System.out.println(a.getInc_id()+" : "+a.getInc_ac()+" : "+a.getUserid()+" : "+a.getInc_catid()+" : "+a.getAmount()+" : "+a.getTransaction_date()+" : "+a.getReceiveby()+" : "+a.getRemark());
//        }

//      FindById income
        IncomeBean a = new IncomeBean();
        IncomeDAO id = new IncomeDAO();
        a=id.findById(1234);
        if(a.getUserid()>0){
            System.out.println(a.getInc_id()+" : "+a.getInc_ac()+" : "+a.getUserid()+" : "+a.getInc_catid()+" : "+a.getAmount()+" : "+a.getTransaction_date()+" : "+a.getReceiveby()+" : "+a.getRemark());
        }
    }
}
