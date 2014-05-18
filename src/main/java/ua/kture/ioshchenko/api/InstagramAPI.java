package ua.kture.ioshchenko.api;

import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
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
                .append("&response_type=token");
        authUrl = url.toString();
    }

    public String getAccessToken(String code) throws IOException {
/*        URL url ;
        HttpsURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        String line;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL("https://api.instagram.com/oauth/access_token");
            httpURLConnection = (HttpsURLConnection) url.openConnection();
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
        return result.toString();*/
        return getToken(code);

    }

    private String getToken(String code) throws IOException {
        String httpsURL = "https://api.instagram.com/oauth/access_token";

        StringBuilder query = new StringBuilder();
        query.append("client_id=").append(CLENT_ID).append("&")
                .append("client_secret=").append(CLIENT_SECRET).append("&")
                .append("grant_type=authorization_code").append("&")
                .append("redirect_uri=").append(REDIRECT_URL).append("&")
                .append("code=").append(code);

        URL myurl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-length", String.valueOf(query.length()));
        con.setRequestProperty("Content-Type", "application/x-www- form-urlencoded");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream output = new DataOutputStream(con.getOutputStream());


        output.writeBytes(query.toString());

        output.close();

        DataInputStream input = new DataInputStream(con.getInputStream());


        for (int c = input.read(); c != -1; c = input.read())
            System.out.print((char) c);
        input.close();

        System.out.println("Resp Code:" + con.getResponseCode());
        System.out.println("Resp Message:" + con.getResponseMessage());
        return con.getResponseMessage();
    }

    public String getAuthUrl() {
        return authUrl;
    }
}
