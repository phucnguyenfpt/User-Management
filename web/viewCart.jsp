<%-- 
    Document   : ViewCart
    Created on : Jun 17, 2021, 7:30:24 AM
    Author     : Minh Phuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="phucnm.store.StoreDTO"%>
<%@page import="java.util.Map"%>
<%@page import="phucnm.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Oniline Market</title>
    </head>
    <body>
        <h1>Your Cart include:</h1>
        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}"/>
                <c:if test="${not empty items}">
                    <form action="DispatchServlet">

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Remove</th>
                                    <th>Checkout</th>

                                </tr>
                            </thead>
                            <tbody>

                                <%--                    
                                    int count = 0;
                                    for (String name : items.keySet()) {
                                        StoreDTO dto = items.get(name);
                                --%> 

                                <c:forEach var="entry" items="${items}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${entry.key}
                                        </td>
                                        <td>
                                            ${entry.value.name}
                                        </td>
                                        <td>
                                            ${entry.value.quantity}
                                        </td>
                                        <td>
                                            ${entry.value.money} 
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" 
                                                   value="${entry.key}" />
                                        </td>

                                    </tr>
                                </c:forEach>

                                <%--
                                    }//end traversal
                                --%>
                                <tr>
                                    <td colspan="5">
                                        <a href="bookStore.jsp">Add More Item to Your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                    </td>
                                    <td>
                                        <input type="submit" value="CheckOut" name="btAction" />
                                    </td>



                                </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                <c:if test="${empty items}">
                    You haven't bought any book<br/>
                    <a href="bookStore.jsp">Continue buy book</a>
                </c:if>
            </c:if>
            <c:if test="${empty cart}">
                You haven't bought any book<br/>
                <a href="bookStore.jsp">Continue buy book</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope}">
            You haven't bought any book<br/>
            <a href="bookStore.jsp">Continue buy book</a>
        </c:if>
        <%--
            //1. Customer goes to his/her Cart place
            if (session != null) {
                //2.Custumer takes his/her Cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer takes items from Cart
                    Map<String, StoreDTO> items = cart.getItems();
                    if (cart.getItems() != null) {
                        //4. Display items

        --%>

        <%--
        }
        else {

        %>

        <%                }
        } else {
        %>

        <%
                }
            }
        --%>      

    </body>
</html>
