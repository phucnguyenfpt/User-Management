/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phucnm.registration.RegistrationCreateError;
import phucnm.registration.RegistrationDAO;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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

//        HashMap<String, String> roadmap = (HashMap<String, String>) request.getServletContext().getAttribute("ROADMAP");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundError = false;
        String url = ERROR_PAGE;

        try {
            //1. check all error's user 
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username is requires from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is requires from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundError = true;
                errors.setFullnameLengthError("Full name is requires from 6 to 50 characters");
            }
            //2. Process
            if (foundError) {
                //send error to user
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                // call DAO to insert to DB
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createNewAccount(username, password, fullname, false);

                if (result) {
                    // tranfer to login
                    url = LOGIN_PAGE;
                }//.insert is successfull
            }// no error occur
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL" + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is Existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming" + ex.getMessage());
        } finally {
//            url = roadmap.get(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
