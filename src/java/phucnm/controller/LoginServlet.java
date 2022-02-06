/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phucnm.registration.RegistrationDAO;
import phucnm.utils.DBHelper;

/**
 *
 * @author Minh Phuc
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {
//        HashMap<String, String> roadmap = (HashMap<String, String>) request.getServletContext().getAttribute("ROADMAP");
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;
        try {

            //call DAO -> new DAO Object & call method of DAO
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);

            if (result) {
                Cookie cookie = new Cookie(username, password);
                //cookie.setMaxAge(60 * 4)
                cookie.setMaxAge(60 * 4);
                response.addCookie(cookie);
                // Login xong luu lai thong tin nguoi dung
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                
                String name = dao.getLastName(username);
                session.setAttribute("NAME", name);
//                url = SEARCH_PAGE;
                url = SEARCH_PAGE;

                //get FullName -- call DAO to get FULLNAME From Lastname
//                session.setAttribute("FULLNAME", fullname);
                //session.getAttribute("FULLNAME", fullname)
                //add Cookie to Client using  resObj
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 4);
//                response.addCookie(cookie);
            }

        } catch (NamingException ex) {
//            ex.printStackTrace();
            log("LoginServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log("LoginServlet _ SQL" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
//            url =roadmap.get(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
            
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
