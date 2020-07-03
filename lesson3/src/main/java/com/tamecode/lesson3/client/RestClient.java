package com.tamecode.lesson3.client;

import com.tamecode.lesson3.entity.User;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: LiQiongchao
 * @Date: 2020/7/3 22:46
 */
public class RestClient {

    public static void main(String[] args) {
//        restTemplate();
        restTemplateAndHttpClient();
    }

    /**
     * 使用 RestTemplate 发 Http 请求
     */
    private static void restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        String content = restTemplate.getForObject("http://localhost:8080/json/user", String.class);

        // 调用 JSON 接口
//        User content = restTemplate.getForObject("http://localhost:8080/json/user", User.class);

        // 调用 XML 接口
        User content = restTemplate.getForObject("http://localhost:8080/xml/user", User.class);
        System.out.println(content);
        // User(name=lee, age=20)
    }

    /**
     * 使用 RestTemplate 包装 HttpClient 发送 Http 请求
     */
    private static void restTemplateAndHttpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = httpClientBuilder.build();
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory);
        // 调用 JSON 接口
        User content = restTemplate.getForObject("http://localhost:8080/json/user", User.class);
        System.out.println(content);
        // User(name=Z322, age=26)
    }

}
