package io.pivotal;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by bboe on 7/29/16.
 */
@RestController
public class AppRoutesController {
    @Value("${routepath}")
    private String routePath;

    @Value("${routeusername}")
    private String routeUsername;

    @Value("${routepassword}")
    private String routePassword;

    @RequestMapping("/")
    public Map getRoutes() {
        System.out.printf("AppRoutesController.getRoutes Route path: %s, Username: %s, Password: %s\n", routePath, routeUsername, routePassword);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(createHeaders(routeUsername, routePassword));

        ResponseEntity<Map> response = restTemplate.exchange(routePath, HttpMethod.GET, request, Map.class);
        return response.getBody();
    }

    private HttpHeaders createHeaders( String username, String password ){
        HttpHeaders result = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        result.set("Authorization", authHeader );
        return result;
    }
}
