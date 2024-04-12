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
public class DayBook extends HttpServlet {

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
            out.println("th,td {padding-right:10px;padding-left:10px;}");
            out.println("td{color:white;}");
            out.println("#loginusername{position:absolute; top:20%; left:1%; color:white;}");
            out.println(".userprofile{width: 70%; margin-left:26.5%;position:absolute; background-color:blue;}");
            out.println(".userprofile table{color:white; font-size:1.2rem;} .userprofile a{background-color:white; text-decoration:none;}");
            out.println("#expenses{width:95%; margin:auto; background-color:rgb(5, 5, 155);}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='loginusername'><h1>Welcome " + un + "</h1></div>");
            out.println("<div class='userprofile'>");
            out.println("<h1 style='margin-left:5%;'><font color='white'>DayBook</font><h1>");
            out.println("<div id='expenses'>");
            out.println("<center><form action='DayBook'>");
            out.println("<table><tr><td> <br>DayBook</td><td>DateFrom<br><input type='date' name='start'></td><td>To<br><input type='date' name='end'></td><td><br><input type='submit' value='Show'></td></table>");
            out.println("</form></center>");
            out.println("<center><table><tr  style='background-color:black;'><th>S.No.</th><th>Account Name</th><th>Date</th><th>Amount</th><th>Pay/Receive</th><th>Remark</th></tr>");
            out.println("<tr><td>Expenses</td><td></td><td></td><td></td><td></td><td></td></tr>");
            int i=0;
            for(ExpensesBean a:al){
                i++;
            out.println("<tr><td>"+i+"</td><td>"+a.getExp_ac()+"</td><td>"+a.getTransaction_date()+"</td><td>"+a.getAmount()+"</td><td>"+a.getPayby()+"</td><td>"+a.getRemark()+"</td></tr>");
            }
            out.println("<tr><td>Incomes</td><td></td><td></td><td></td><td></td><td></td></tr>");
            int j=0;
            for(IncomeBean c:q){
                j++;
            out.println("<tr><td>"+j+"</td><td>"+c.getInc_ac()+"</td><td>"+c.getTransaction_date()+"</td><td>"+c.getAmount()+"</td><td>"+c.getReceiveby()+"</td><td>"+c.getRemark()+"</td></tr>");
            }
            out.println("</table>");
            out.println("</center>");
            
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
