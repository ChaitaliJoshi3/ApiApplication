package com.APIController.ApiAppliaction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RestApiClientTest {

    @Mock
    private CloseableHttpClient httpClient;

    @Mock
    private CloseableHttpResponse httpResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendData() throws IOException {
        String apiUrl = "http://example.com/api";
        String json = "{\"customerRef\":\"123\",\"customerName\":\"John Doe\",\"addressLine1\":\"123 Main St\",\"addressLine2\":\"\",\"town\":\"Anytown\",\"county\":\"Anycounty\",\"country\":\"Anycountry\",\"postcode\":\"12345\"}";

        // Mock HTTP client and response
        when(httpResponse.getEntity()).thenReturn(new ByteArrayInputStream("".getBytes()));
        when(httpResponse.getStatusLine()).thenReturn(mock(StatusLine.class));
        when(httpResponse.getStatusLine().getStatusCode()).thenReturn(200);
        when(httpClient.execute(any())).thenReturn(httpResponse);

        RestApiClient restApiClient = new RestApiClient(apiUrl, httpClient);
        restApiClient.sendData(new Customer("123", "John Doe", "123 Main St", "", "Anytown", "Anycounty", "Anycountry", "12345"));

        // Verify that the HTTP client executes with the correct request
        verify(httpClient).execute(argThat(argument -> {
            try {
                HttpEntity entity = argument.getEntity();
                assertEquals(json, entityToString(entity)); // Implement entityToString method to convert HttpEntity to string
                assertEquals(apiUrl, argument.getURI().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }));
    }

    // Helper method to convert HttpEntity to string
    private String entityToString(HttpEntity entity) throws IOException {
        // Implementation
    }
}


