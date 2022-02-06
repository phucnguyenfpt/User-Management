/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phucnm.utils.DBHelper;

/**
 *
 * @author Minh Phuc
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connection BD
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password =?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    return true;
                }
            }// end connection is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdim = rs.getBoolean("isAdmin");

                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastname, isAdim);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end if account List is not existed
                    //Account List is existed
                    this.accountList.add(dto);
                }//end while rs has pointed to EOF

            }//end connection is opened

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Registration"
                        + " Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4.Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }

    public boolean createNewAccount(String username, String password,
            String fullname, boolean role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                //4.Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }

    public String getLastName(String searchValue) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String lastName = null;

        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Select username, lastname "
                        + "From Registration "
                        + "Where username Like ?";
                //3.  Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Excute Query
                rs = stm.executeQuery();

                //5. Process the Result Set
                while (rs.next()) {
                    String username = rs.getString("username");
                    lastName = rs.getString("lastname");
                }
            }// end connection

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lastName;
    }
}
