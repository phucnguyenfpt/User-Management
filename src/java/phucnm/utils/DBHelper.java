/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Minh Phuc
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws /*ClassNotFoundException*/ NamingException, SQLException {
        //1 get current context
        Context context = new InitialContext();
        //2. get container context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        //3. get data source
        DataSource da = (DataSource) tomcatContext.lookup("DSConnect");
        //4. get connection
        Connection con = da.getConnection();
        
        return con;
//        //1.Load driver(Library->add jdbc4.jar)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Testing";
//        //3.Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "12345");
//
//        return con;
    }
}
