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
import org.json.simple.parser.JSONParser;
import org.apache.http.entity.StringEntity;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstagramAPI {
	private static final String CLENT_ID = "883cf7e7219848a59b1b0fe295ddf549";
	private static final String CLIENT_SECRET = "9d0585ac55e146e1877e5c1b694ffb64";
	private static final String REDIRECT_URL = "http://fake-ioschenko.rhcloud.com/instagram/accesss";

	private String authUrl;

	private Logger log = Logger.getLogger(InstagramAPI.class);

	public InstagramAPI() {
		StringBuilder url = new StringBuilder(
				"https://api.instagram.com/oauth/authorize/?client_id=");
		url.append(CLENT_ID).append("&redirect_uri=").append(REDIRECT_URL)
				.append("&response_type=code");
		authUrl = url.toString();
	}

	public String getAccessToken(String code) throws IOException {
		log.info("**************  Start API  **************");
		HttpPost httpPost = null;
		StringBuilder builder = new StringBuilder();

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			httpPost = new HttpPost(
					"https://api.instagram.com/oauth/access_token");
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

			for (String line = null; (line = reader.readLine()) != null;) {
				builder.append(line);
			}

			// System.out.println(builder.toString());

			// json = (JSONObject) new JSONParser().parse(builder.toString());

			EntityUtils.consume(response.getEntity());

		} catch (Exception ex) {
			log.error("Instagram api", ex);
		} finally {
			httpPost.releaseConnection();

		}

		return builder.toString();

	}

	public String getAuthUrl() {
		return authUrl;
	}
}
