package ua.kture.ioshchenko.api;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class InstagramAPI {
    private static final String CLENT_ID = "883cf7e7219848a59b1b0fe295ddf549";
    private static final String CLIENT_SECRET = "9d0585ac55e146e1877e5c1b694ffb64";
    private static final String REDIRECT_URL = "http://fake-ioschenko.rhcloud.com/instagram/accesss";

    private String authUrl;

    public InstagramAPI() {
        StringBuilder url = new StringBuilder("https://api.instagram.com/oauth/authorize/?client_id=");
        url.append(CLENT_ID)
                .append("&redirect_uri=").append(REDIRECT_URL)
                .append("&response_type=code");
        authUrl = url.toString();
    }

    public String getAccessToken(String code) {
        URL url ;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        String line;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL("https://api.instagram.com/oauth/access_token");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("client_id", CLENT_ID);
            httpURLConnection.setRequestProperty("client_secret", CLIENT_SECRET);
            httpURLConnection.setRequestProperty("grant_type", "authorization_code");
            httpURLConnection.setRequestProperty("redirect_uri", REDIRECT_URL);
            httpURLConnection.setRequestProperty("code", code);
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String getAuthUrl() {
        return authUrl;
    }
}
