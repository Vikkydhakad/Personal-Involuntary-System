/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.servlet;

import com.personalinventory.bean.IncomeCategoryBean;
import com.personalinventory.bean.UsersBean;
import com.personalinventory.dao.IncomeCategoryDAO;
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
public class Income extends HttpServlet {

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
            
            IncomeCategoryDAO icb = new IncomeCategoryDAO();
            ArrayList<IncomeCategoryBean> y = icb.findAllIncomeByUserid(x);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("<style>");
            out.println("#loginusername{position:absolute; top:20%; left:1%; color:white;}");
            out.println(".userprofile{width: 70%; margin-left:26.5%;position:absolute; background-color:blue;}");
            out.println(".userprofile table{color:white; font-size:1.2rem;} .userprofile a{background-color:white; text-decoration:none;}");
            out.println("#expenses{width:95%; margin:auto; background-color:rgb(5, 5, 155);}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='loginusername'><h1>Welcome " + un + "</h1></div>");
            out.println("<div class='userprofile'>");
            out.println("<h1 style='margin-left:5%;'><font color='white'>Income</font><h1>");
            out.println("<div id='expenses'>");
            out.println("<center><form action='addIncome'>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Income : </td>");
            out.println("<td><input type='text' name='expenses' placeholder='bill/ticket/stationary'></td>");
            out.println(" </tr>");
            out.println(" <tr>");
            out.println("<td>Category : </td>");
            out.println("<td><select name='category' id='category'>");
            for(IncomeCategoryBean r:y){
            out.println("<option value="+r.getInc_catdetails()+">"+r.getInc_catname()+"</option>");
            }
            out.println("</select></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println(" <td>Amount : </td>");
            out.println(" <td><input type='text' name='amount'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println(" <td>Receive By : </td>");
            out.println(" <td><select name='payby' id='payby'><option value='Cash'>Cash</option><option value='Online'>Online</option></select></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Date : </td>");
            out.println("<td><input type='date' name='date'></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td>Remark : </td>");
            out.println("<td><input type='text' name='remark'></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td></td>");
            out.println("<td><input type='hidden' name='id' value="+x+"></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td></td>");
            out.println("<td><input type='submit' value='Add Income'></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form></center>");
            

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
