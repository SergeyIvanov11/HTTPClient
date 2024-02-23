package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

//Задание 2 "Чтение данных API NASA"
public class NASA_API {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=CzF2QkGii6MInfMwdv6eSyhOBqDTMzCS0wVYGsbD");
        CloseableHttpResponse response = httpClient.execute(request);

        NASAServiceResponse NASAResponse = mapper.readValue(
                response.getEntity().getContent(), new
                        TypeReference<NASAServiceResponse>() {
                        });

        HttpGet request2 = new HttpGet(NASAResponse.getUrl());
        CloseableHttpResponse response2 = httpClient.execute(request2);

        String fileName = NASAResponse.getUrl().split("/")[NASAResponse.getUrl().split("/").length - 1];

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
            while (fileReader.ready()) {
                fileWriter.write(fileReader.read());
            }
            fileWriter.close();
            fileReader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
