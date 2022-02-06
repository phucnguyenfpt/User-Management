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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phucnm.registration.RegistrationDAO;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "StartupAccountServerlet", urlPatterns = {"/StartupAccountServerlet"})
public class StartupAccountServerlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

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
//        HashMap<String, String> roadmap = (HashMap<String, String>) request.getServletContext().getAttribute("ROADMAP");
        String url = LOGIN_PAGE;

        try {
            //1 checking cookies exists in request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2 traverse all cookies to check authentication
                for (Cookie cookie : cookies) {
                    //3. get username and password from cookies
                    String username = cookie.getName();
                    if (!username.equals("JSESSIONID")) {
                        String password = cookie.getValue();

                        //4. call DAO to check authentication
                        RegistrationDAO dao = new RegistrationDAO();

                        boolean result = dao.checkLogin(username, password);
                        if (result) {
                            HttpSession session = request.getSession();
                            session.setAttribute("USERNAME", username);

                            String lastName = dao.getLastName(username);
                            session.setAttribute("NAME", lastName);
                            //5.send to search
                            url = SEARCH_PAGE;
                            break;

                        }//authentication is success
                    }

                }//checking each cookies in array
            }//end cookies is existed
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log("StartupAccountServerlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
//            ex.printStackTrace();
            log("StartupAccountServerlet _ Naming" + ex.getMessage());
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
