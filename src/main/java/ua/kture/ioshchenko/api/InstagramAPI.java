package ua.kture.ioshchenko.api;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstagramAPI {
    private static final String CLENT_ID = "883cf7e7219848a59b1b0fe295ddf549";
    private static final String CLIENT_SECRET = "9d0585ac55e146e1877e5c1b694ffb64";
    private static final String REDIRECT_URL = "http://fake-ioschenko.rhcloud.com/instagram/accesss";
    private static final String SUBSCRIPTIONS_URL = "https://api.instagram.com/v1/subscriptions/";
    private static final String ACCESS_TOKEN_URL = "https://api.instagram.com/oauth/access_token";

    private String authUrl;

    private Logger log = Logger.getLogger(InstagramAPI.class);

    public InstagramAPI() {
        StringBuilder url = new StringBuilder(
                "https://api.instagram.com/oauth/authorize/?client_id=");
        url.append(CLENT_ID).append("&redirect_uri=").append(REDIRECT_URL)
                .append("&response_type=code");
        authUrl = url.toString();
    }

    public JSONObject getAuccess(String code) throws IOException {
        HttpPost httpPost = null;
        StringBuilder builder = new StringBuilder();

        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpPost = new HttpPost(ACCESS_TOKEN_URL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("client_id", CLENT_ID));
            nvps.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
            nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
            nvps.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));
            nvps.add(new BasicNameValuePair("code", code));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpclient.execute(httpPost);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));

            for (String line = null; (line = reader.readLine()) != null; ) {
                builder.append(line);
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception ex) {
            log.error("Instagram api", ex);
        } finally {
            httpPost.releaseConnection();

        }
        return new JSONObject(builder.toString());
    }

    public void createSubscriptions(String verifyToken) {
        HttpPost httpPost = null;

        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpPost = new HttpPost(SUBSCRIPTIONS_URL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("client_id", CLENT_ID));
            nvps.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
            nvps.add(new BasicNameValuePair("object", "user"));
            nvps.add(new BasicNameValuePair("aspect", "media"));
            nvps.add(new BasicNameValuePair("verify_token", verifyToken));
            nvps.add(new BasicNameValuePair("callback_url", REDIRECT_URL));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpclient.execute(httpPost);

        } catch (Exception ex) {
            log.error("Instagram api create subscriptions", ex);
        } finally {
            httpPost.releaseConnection();
        }

    }


    public String getAuthUrl() {
        return authUrl;
    }
}
