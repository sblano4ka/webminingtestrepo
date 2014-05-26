package ua.kture.ioshchenko.api.instagram;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class InstagramEndpoints {
    private Logger log = Logger.getLogger(InstagramEndpoints.class);

    public String getImageUrl(String mediaId, String accessToken) {
        StringBuilder requestUrl = new StringBuilder("https://api.instagram.com/v1/media/");
        requestUrl.append(mediaId).append("?")
                .append("access_token=").append(accessToken);


        HttpGet httpGet = null;
        StringBuilder builder = new StringBuilder();

        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpGet = new HttpGet(requestUrl.toString());
            HttpResponse response = httpclient.execute(httpGet);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));

            for (String line = null; (line = reader.readLine()) != null; ) {
                builder.append(line);
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception ex) {
            log.error("Instagram api endpoints.", ex);
        } finally {
            httpGet.releaseConnection();

        }
        JSONObject jsonObject = new JSONObject(builder.toString());
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject images = data.getJSONObject("images");
        JSONObject standardResolution = images.getJSONObject("standard_resolution");

        return standardResolution.get("url").toString();
    }
}
