/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.store;

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
public class StoreDAO implements Serializable {

    private List<StoreDTO> listBook;

    public List<StoreDTO> getListBook() {
        return listBook;
    }

    public List<StoreDTO> loadStrore()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. connect database
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create sql String
                String sql = "Select id, name, quantity, price "
                        + "From Store";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");

                    StoreDTO dto = new StoreDTO(id, name, quantity, price);

                    if (this.listBook == null) {
                        this.listBook = new ArrayList<>();
                    }
                    this.listBook.add(dto);
                }
                return this.listBook;

            }

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

        return null;
    }

    public List<StoreDTO> getStore() throws SQLException, NamingException {
        loadStrore();
        return getListBook();

    }

    public boolean checkOutBook(StoreDTO dto) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL String
            if (con != null) {

                String sql = "INSERT INTO CheckOut (id, name, quantity, price, total) "
                        + "VALUES (?, ?, ?, ?, ?) ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getName());
                stm.setInt(3, dto.getQuantity());
                stm.setInt(4, dto.getMoney());
                stm.setInt(5, dto.getMoney() * dto.getQuantity());

                //4. ExcuteQuery
                int effectiveRow = stm.executeUpdate();

                //5. Process Result
                if (effectiveRow > 0) {
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

    public boolean updateStore(String id, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect BD
            con = DBHelper.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Store "
                        + "SET quantity = quantity - ? "
                        + "Where id = ? and quantity >= ? ";

                stm = con.prepareStatement(sql);

                stm.setInt(1, quantity);
                stm.setString(2, id);
                stm.setInt(3, quantity);

                //4. ExcuteQuery
                int effectiveRow = stm.executeUpdate();

                //5. Process Result
                if (effectiveRow > 0) {
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

}
