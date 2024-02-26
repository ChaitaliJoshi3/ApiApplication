package com.APIController.ApiAppliaction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.opencsv.exceptions.CsvException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvReaderTest {

    @Mock
    private CsvReader csvReader;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadCsvFile() throws IOException, CsvException {
        // Mock CSV data
        when(csvReader.readNext())
                .thenReturn(new String[]{"Customer Ref", "Customer Name", "Address Line 1", "Address Line 2",
                                         "Town", "County", "Country", "Postcode"})
                .thenReturn(new String[]{"123", "John Doe", "123 Main St", "", "Anytown", "Anycounty", "Anycountry", "12345"})
                .thenReturn(null);

        CsvReader csvReader = new CsvReader(csvReader);
        List<Customer> customers = csvReader.readCsvFile("test.csv");

        // Verify that one customer is read
        assertEquals(1, customers.size());

        // Verify the customer details
        Customer customer = customers.get(0);
        assertEquals("123", customer.getCustomerRef());
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("123 Main St", customer.getAddressLine1());
        assertEquals("", customer.getAddressLine2());
        assertEquals("Anytown", customer.getTown());
        assertEquals("Anycounty", customer.getCounty());
        assertEquals("Anycountry", customer.getCountry());
        assertEquals("12345", customer.getPostcode());
    }
}



