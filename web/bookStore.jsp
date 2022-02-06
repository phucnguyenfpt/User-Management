<%-- 
    Document   : bookStore
    Created on : Jun 27, 2021, 3:54:47 PM
    Author     : Minh Phuc
--%>

<%@page import="phucnm.store.StoreDTO"%>
<%@page import="java.util.List"%>
<%@page import="phucnm.store.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1><br/>
        <c:set var="listBook" value="${StoreDAO().getStore()}"/>
        <c:if test="${not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Book name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Add Item</th>
                    </tr>
                </thead>
                <tbody>
                    <%--
                        int count = 0;
                        for (StoreDTO book : listBook) {
                    --%>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.id}
                                <input type="hidden" name="txtID" value="${dto.id}" />
                            </td>
                            <td>
                                ${dto.name}
                                <input type="hidden" name="txtName" value="${dto.name}" />
                            </td>
                            <td>
                                ${dto.quantity}
                                <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                            </td>
                            <td>
                                ${dto.money}
                                <input type="hidden" name="txtPrice" value="${dto.money}" />
                            </td>
                            <td>
                                <input type="submit" value="Add" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>

                <%--
                    }
                --%>
            </tbody>
        </table>
    </c:if>

    <%--
        //DAO
        StoreDAO dao = new StoreDAO();
        dao.loadStrore();
        List<StoreDTO> listBook = dao.getListBook();
        if (listBook != null) {
    --%>  

    <form action="DispatchServlet">
        <input type="submit" value="ShowCart" name="btAction" />
    </form>
    <c:if test="${empty listBook}">
        <h1>
            Sorry. Please try again.
        </h1>
       
    </c:if>
    <%--
    } else {
    --%> 
    <%--
        }
    --%>
</body>
</html>
