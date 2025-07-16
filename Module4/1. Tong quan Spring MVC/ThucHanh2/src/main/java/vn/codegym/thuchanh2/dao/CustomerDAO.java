package vn.codegym.thuchanh2.dao;

import vn.codegym.thuchanh2.model.Customer;
import vn.codegym.thuchanh2.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from CustomerList");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    customerList.add(new Customer(id, name, email, address));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;

    }

    public static int getNextAvaibleId() {
        String sql = "select id from customerlist order by id asc";
        int expectedId = 1;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int currentId = rs.getInt("id");
                if (currentId != expectedId) {
                    return expectedId;
                }
                expectedId++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return expectedId;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from CustomerList where id=?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
