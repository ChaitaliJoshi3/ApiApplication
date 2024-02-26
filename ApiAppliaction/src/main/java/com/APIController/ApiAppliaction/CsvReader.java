package com.APIController.ApiAppliaction;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Customer> readCsvFile(String filePath) throws IOException, CsvException {
        List<Customer> customers = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] header = reader.readNext(); // Assuming the first row contains headers
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                Customer customer = parseCustomer(nextLine, header);
                customers.add(customer);
            }
        }

        return customers;
    }

    private Customer parseCustomer(String[] data, String[] header) {
        Customer customer = new Customer();
        for (int i = 0; i < header.length; i++) {
            switch (header[i]) {
                case "Customer Ref":
                    customer.setCustomerRef(data[i]);
                    break;
                case "Customer Name":
                    customer.setCustomerName(data[i]);
                    break;
                case "Address Line 1":
                    customer.setAddressLine1(data[i]);
                    break;
                case "Address Line 2":
                    customer.setAddressLine2(data[i]);
                    break;
                case "Town":
                    customer.setTown(data[i]);
                    break;
                case "County":
                    customer.setCounty(data[i]);
                    break;
                case "Country":
                    customer.setCountry(data[i]);
                    break;
                case "Postcode":
                    customer.setPostcode(data[i]);
                    break;
                default:
                    // Handle unexpected headers or ignore
                    break;
            }
        }
        return customer;
    }
}



