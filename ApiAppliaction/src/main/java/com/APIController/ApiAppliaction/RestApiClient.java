package com.APIController.ApiAppliaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestApiClient {
    private ObjectMapper objectMapper;
    private String apiUrl;

    public RestApiClient(String apiUrl) {
        this.objectMapper = new ObjectMapper();
        this.apiUrl = apiUrl;
    }

    public void sendData(Customer customer) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(apiUrl);
            String json = objectMapper.writeValueAsString(customer);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle response if needed
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Data sent successfully.");
                } else {
                    System.err.println("Failed to send data. Status code: " + statusCode);
                }
            }
        } finally {
            httpClient.close();
        }
    }
}


