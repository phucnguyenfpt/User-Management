<%-- 
    Document   : createNewAccount
    Created on : Jun 30, 2021, 8:39:50 PM
    Author     : Minh Phuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 characters)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 characters)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
                Full name <input type="text" name="txtFullname" value="${param.txtFullname}" />(2-50 characters)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="submit" value="Reset" />
        </form>
    </body>
</html>
