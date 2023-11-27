package org.example.service;

import net.minidev.json.JSONObject;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestTemplateService implements SiteService {
    private final RestTemplate restTemplate;
    private final String url;
    private final String cookie;

    public RestTemplateService(RestTemplate restTemplate, @Value("${application.server.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.cookie = getCookie();
    }

    @Override
    public List<User> getUserList() {
        HttpEntity<String> entity = new HttpEntity<>(setHeaders());
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        System.out.println("код GET" + response.getHeaders() + "\n");
        return response.getBody();
    }

    @Override
    public void addUser(User user) {
        HttpEntity<String> request =
                new HttpEntity<>(setUser(user).toString(), setHeaders());

        ResponseEntity<String> userResultJsonStr =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        request,
                        String.class
                );
        System.out.println("код добавления" + userResultJsonStr.getHeaders() + "\n");
    }

    @Override
    public void changeUser(User user) {
        HttpEntity<String> request =
                new HttpEntity<>(setUser(user).toString(), setHeaders());

        ResponseEntity<String> userResultJsonStr =
                restTemplate.exchange(
                        url,
                        HttpMethod.PUT,
                        request,
                        String.class
                );
        System.out.println("код изменения" + userResultJsonStr.getHeaders() + "\n");
    }

    @Override
    public void deleteUser(Long id) {
        HttpEntity<String> request =
                new HttpEntity<>(setHeaders());

        ResponseEntity<String> userResultJsonStr =
                restTemplate.exchange(
                        url + "/" + id,
                        HttpMethod.DELETE,
                        request,
                        String.class
                );
        System.out.println("код удаления" + userResultJsonStr.getHeaders() + "\n");
    }

    @Override
    public String getCookie() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class
        ).getHeaders().getFirst(HttpHeaders.SET_COOKIE);
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.COOKIE, cookie);
        return headers;
    }

    private JSONObject setUser(User user) {
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("id", user.getId());
        userJsonObject.put("name", user.getName());
        userJsonObject.put("lastName", user.getLastName());
        userJsonObject.put("age", user.getAge());
        return userJsonObject;
    }
}