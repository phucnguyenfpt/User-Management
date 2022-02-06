/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phucnm.cart.CartObject;
import phucnm.store.StoreDTO;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

//    private final String SHOPPING_PAGE = "shopping.html";
    private final String SHOPPING_PAGE = "bookStore.jsp";

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
        PrintWriter out = response.getWriter();

        try {
            //1. Den noi lay gio
            HttpSession session = request.getSession();

            //2. Cus laay gio (Attribute)
            CartObject cart = (CartObject) session.getAttribute("CART");

            //check gio hang
            if (cart == null) {
                cart = new CartObject();
            }
            // khach hang lay gio
            String id = request.getParameter("txtID");
            String name = request.getParameter("txtName");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            int price = Integer.parseInt(request.getParameter("txtPrice"));

            StoreDTO dto = new StoreDTO(id, name, quantity, price);

            //4. check mon hang ton tai hay ch
            dto.setQuantity(cart.addItemToCart(dto));

//            dto.setMoney(cart.updatePrice(dto));
            session.setAttribute("CART", cart);
            String url = SHOPPING_PAGE;

        } finally {
//            response.sendRedirect(SHOPPING_PAGE);
            response.sendRedirect(SHOPPING_PAGE);
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
