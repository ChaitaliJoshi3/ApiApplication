package com.APIController.ApiAppliaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public void saveToDatabase(List<Customer> customers) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO customers (customer_ref, customer_name, address_line1, address_line2, town, county, country, postcode) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (Customer customer : customers) {
                    statement.setString(1, customer.getCustomerRef());
                    statement.setString(2, customer.getCustomerName());
                    statement.setString(3, customer.getAddressLine1());
                    statement.setString(4, customer.getAddressLine2());
                    statement.setString(5, customer.getTown());
                    statement.setString(6, customer.getCounty());
                    statement.setString(7, customer.getCountry());
                    statement.setString(8, customer.getPostcode());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        }
    }
}



