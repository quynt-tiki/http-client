package vn.tiki.test.httpclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String login() {

        return "Hello";
    }

    @RequestMapping("/test")
    public String testHello() {
        var cacheKey = "http://localhost:6969/test" + (new Random()).nextInt(1000);
        String fromCache = loadFromCache(cacheKey);
        var res = query();
        if (fromCache == null) {
            putToCache(cacheKey, res);
        }

        return res;
    }

    public String loadFromCache(String cacheKey) {
      return HazelcastCache.getInstance().get(cacheKey);

    }

    public void putToCache(String key, String value) {
        HazelcastCache.getInstance().put(key, value);
    }

    public String query() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:6789/remote_server_test", HttpMethod.GET, entity,
                String.class).getBody();

    }
}