package com.APIController.ApiAppliaction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseManagerTest {

    @Before
    public void setUp() {
        // Set up any necessary mocks or configurations
    }

    @Test
    public void testSaveToDatabase() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        // Mock database manager and customer data
        DatabaseManager databaseManager = new DatabaseManager(connection);
        List<Customer> customers = Arrays.asList(
                new Customer("123", "John Doe", "123 Main St", "", "Anytown", "Anycounty", "Anycountry", "12345"),
                new Customer("456", "Jane Smith", "456 Oak St", "", "Othertown", "Othercounty", "Othercountry", "67890")
        );

        // Mock JDBC PreparedStatement batch execution
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).addBatch();
        doNothing().when(preparedStatement).executeBatch();

        // Test saving customers to database
        databaseManager.saveToDatabase(customers);

        // Verify that PreparedStatement methods are called correctly
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement, times(2)).addBatch();
        verify(preparedStatement).executeBatch();
    }
}



