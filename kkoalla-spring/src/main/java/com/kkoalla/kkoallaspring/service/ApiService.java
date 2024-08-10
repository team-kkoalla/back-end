package com.kkoalla.kkoallaspring.service;


import com.kkoalla.kkoallaspring.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {
    private final ApiConfig apiConfig;

    public JSONArray fetchData(int page, int perPage) {
        JSONArray dataArray = new JSONArray();

        try {
            String urlStr = apiConfig.getFullUrl(page, perPage);
            URL url = new URL(urlStr);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String result = sb.toString();

            // JSON 파싱
            JSONObject jsonObject = new JSONObject(result);
            dataArray = jsonObject.getJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while fetching data", e);
        }

        return dataArray;
    }
}
