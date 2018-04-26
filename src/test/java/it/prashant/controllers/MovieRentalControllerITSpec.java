package it.prashant.controllers;

import com.Application;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieRentalControllerITSpec {
    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testRetrieveAllMoviesFromFile() throws JSONException {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("prashant", "password"));

        ResponseEntity<List> response = restTemplate.exchange(
                createURLWithPort("/allMoviesFromFile"),
                HttpMethod.GET, null, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(9125, response.getBody().size());
    }
}
