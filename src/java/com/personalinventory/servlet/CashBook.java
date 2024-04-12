/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.servlet;

import com.personalinventory.bean.CashBookBean;
import com.personalinventory.bean.UsersBean;
import com.personalinventory.dao.CashBookDAO;
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
public class CashBook extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            RequestDispatcher rd = request.getRequestDispatcher("headerandsidebar.html");
            rd.include(request, response);
            HttpSession hs = request.getSession();
            String un = hs.getAttribute("users").toString();
            UsersDAO ud = new UsersDAO();
            UsersBean ub = ud.findByName(un);
            int x = ub.getUserid();
            
            String sdate = request.getParameter("start");
            String edate = request.getParameter("end");
            CashBookDAO cdb = new CashBookDAO();
            ArrayList<CashBookBean> al = cdb.findAllDateWise(sdate, edate, x);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("<style>");
            out.println("th,td {padding-right:10px;padding-left:10px;}");
            out.println("#loginusername{position:absolute; top:20%; left:1%; color:white;}");
            out.println(".userprofile{width: 70%; margin-left:26.5%;position:absolute; background-color:blue;}");
            out.println(".userprofile table{color:white; font-size:1.2rem;} .userprofile a{background-color:white; text-decoration:none;}");
            out.println("#expenses{width:95%; margin:auto; background-color:rgb(5, 5, 155);}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='loginusername'><h1>Welcome " + un + "</h1></div>");
            out.println("<div class='userprofile'>");
            out.println("<h1 style='margin-left:5%;'><font color='white'>CashBook</font><h1>");
            out.println("<div id='expenses'>");
            out.println("<center><form action='CashBook'>");
            out.println("<table><tr><td> <br>CashBook</td><td>DateFrom<br><input type='date' name='start'></td><td>To<br><input type='date' name='end'></td><td><br><input type='submit' value='Show'></td></table>");
            out.println("</form></center>");
            out.println("<div id='hidden'><center><table style='padding:10px;'>");
            out.println("<tr style='background-color:black;}'><th>S.No.</th><th>Date</th><th>Amount</th><th>Pay/Receive</th></tr>");
            
            int i=0;
            for(CashBookBean k:al){
                i++;
            out.println("<tr>");
            out.println("<td>"+i+"</td><td>"+k.getTransaction_date()+"</td><td>"+k.getAmount()+"</td><td>"+k.getOperation()+"</td>");
            out.println("</tr>");
            }
            double cl = cdb.closingBalance(sdate,edate,x);
            out.println("<tr style='background-color:black;}'><td>Closing Balance</td><td></td><td>"+cl+"</td><td></td></tr>");
            out.println("</table></center></div>");
            out.println("</div>");
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
