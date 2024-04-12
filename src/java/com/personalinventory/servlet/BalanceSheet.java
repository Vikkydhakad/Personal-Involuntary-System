/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.servlet;

import com.personalinventory.bean.ExpensesBean;
import com.personalinventory.bean.IncomeBean;
import com.personalinventory.bean.UsersBean;
import com.personalinventory.dao.ExpensesDAO;
import com.personalinventory.dao.IncomeDAO;
import com.personalinventory.dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HOME
 */
public class BalanceSheet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             RequestDispatcher rd = request.getRequestDispatcher("headerandsidebar.html");
            rd.include(request, response);
            HttpSession hs = request.getSession();
            String un = hs.getAttribute("users").toString();
            UsersDAO ud = new UsersDAO();
            UsersBean ub = ud.findByName(un);
            int x = ub.getUserid();

            String sdate = request.getParameter("start");
            String edate = request.getParameter("end");
            
            
            ExpensesDAO ed = new ExpensesDAO();
            ArrayList<ExpensesBean> al = ed.findAllDateWise(sdate, edate, x);
            
            IncomeDAO p = new IncomeDAO();
            ArrayList<IncomeBean> q = p.findAllDateWise(sdate, edate, x);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("<style>");
            out.println("th,td {padding-right:15px;padding-left:15px;}");
            out.println("th,td {margin-right:15px;margin-left:15px;}");
            out.println("td{color:white;}");
            out.println("#table1{float:left}");
            out.println("#table2{float:left}");
            out.println(".outertable{ margin-left:25%;}");
            out.println("#loginusername{position:absolute; top:20%; left:1%; color:white;}");
            out.println(".userprofile{width: 70%; margin-left:26.5%;position:absolute; background-color:blue;}");
            out.println(".userprofile table{color:white; font-size:1.2rem;} .userprofile a{background-color:white; text-decoration:none;}");
            out.println("#expenses{width:95%; margin:auto; background-color:rgb(5, 5, 155);}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='loginusername'><h1>Welcome " + un + "</h1></div>");
            out.println("<div class='userprofile'>");
            out.println("<h1 style='margin-left:5%;'><font color='white'>BalanceSheet</font><h1>");
            out.println("<div id='expenses'>");
            out.println("<center><form action='BalanceSheet'>");
            out.println("<table><tr><td>DateFrom<br><input type='date' name='start'></td><td>To<br><input type='date' name='end'></td><td><br><input type='submit' value='Show'></td></table>");
            out.println("</form></center>");
            out.println("<div class='outertable'");
            
            
            out.println("<div id='table1'><table><tr style='background-color:black;'><th>Incomes</th><th></th></tr><tr style='background-color:black;'><th>Incomes</th><th>Amount</th></tr>");
            for(IncomeBean h:q){
            out.println("<tr><td>"+h.getInc_ac()+"</td><td>"+h.getAmount()+"</td></tr>");
            }
            double sd = p.findAmountById(sdate, edate, x);
            out.println("<tr style='background-color:black;'><th>Total</th><th>"+sd+"</th></tr>");
            out.println("</table></div>");
            
            
            
            out.println("<div id='table2'><table><tr style='background-color:black;'><th>Expenses</th><th></th></tr><tr style='background-color:black;'><th>Expenses</th><th>Amount</th></tr>");
            for(ExpensesBean k:al){
            out.println("<tr><td>"+k.getExp_ac()+"</td><td>"+k.getAmount()+"</td></tr>");
            }
            double ab =  ed.findAmountById(sdate, edate, x);
            out.println("<tr style='background-color:black;'><th>Total</th><th>"+ab+"</th></tr>");
            out.println("</table></div>");
            out.println("</div>");
            out.println("<center><table style='margin-top:10%;'><tr style='background-color:black;'><th>Gross Profit : </th><th>"+Math.round(sd-ab)+"</th></tr></table></center>");
            out.println("</div></div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
