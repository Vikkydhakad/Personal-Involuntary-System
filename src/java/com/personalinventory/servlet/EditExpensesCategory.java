/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.servlet;

import com.personalinventory.bean.ExpensesCategoryBean;
import com.personalinventory.bean.UsersBean;
import com.personalinventory.dao.ExpensesCategoryDAO;
import com.personalinventory.dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditExpensesCategory extends HttpServlet {

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
            ExpensesCategoryDAO id = new ExpensesCategoryDAO();
            int x = Integer.parseInt(request.getParameter("id"));
            ExpensesCategoryBean a = id.findById(x);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("<style>");
            out.println("#loginusername{position:absolute; top:20%; left:1%; color:white;}");
            out.println(".userprofile{width: 70%; margin-left:26.5%;position:absolute; background-color:blue;}");
            out.println(".userprofile table{color:white; font-size:1.2rem;} .userprofile a{background-color:white; text-decoration:none;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='loginusername'><h1>Welcome " + un + "</h1></div>");
            out.println("<div class='userprofile'>");
            out.println("<h1 style='margin-left:5%;'><font color='white'>Edit Income Category</font><h1>");
            out.println("<form action='UpdateExpensesCategory'>");
            out.println("<center><table><tr><td>Category Name : </td><td><input type='text' name='name' value="+a.getExp_catname()+" ></td></tr>");
            out.println("<tr><td>Category Details : </td><td><input type='test' name='detail' value="+a.getExp_catdetails()+"></td></tr><tr><td><input type='hidden' value="+x+" name='id'></td><td><input type='submit' value='ADD' ></td></tr></table></center>");
            out.println("</form>");
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
