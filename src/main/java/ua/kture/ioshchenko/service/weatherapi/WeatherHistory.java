package ua.kture.ioshchenko.service.weatherapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeatherHistory {
    private static final String KEY = "fb2d96034d2a3399";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private Map<String, String> cityRequest;

    public WeatherHistory() {
        cityRequest = new HashMap<String, String>();
        cityRequest.put("San Francisco", "/q/CA/San_Francisco");
        cityRequest.put("Sacramento", "/q/CA/Sacramento");
    }


    public double getInformation(Date date, String city) throws ParseException {
        double result = 0;
        int requestCount = 10;
        date.setYear(2013);

        for (int i = 0; i < requestCount; i++) {
            JSONObject jsonObject = sendRequest(date, city);
            result += getAvarageTemp(jsonObject);
            date.setYear(date.getYear() - 1);
        }
        return result / requestCount;

    }


    private String generateUrl(String city, Date date) {
        StringBuilder request = new StringBuilder("http://api.wunderground.com/api/");
        request.append(KEY).append("/history_")
                .append(simpleDateFormat.format(date))
                .append(getCityRequest(city))
                .append(".json");
        return request.toString();
    }

    private JSONObject sendRequest(Date date, String city) throws ParseException {
        String request = generateUrl(city, date);

        URL url;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        String line;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(request);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
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

        return new JSONObject(result.toString());
    }

    private String getCityRequest(String city) {
        return cityRequest.get(city);
    }

    private double getAvarageTemp(JSONObject jsonObject) {
        JSONObject history = (JSONObject) jsonObject.get("history");
        JSONArray jsonArray = history.getJSONArray("dailysummary");
        JSONObject result = jsonArray.getJSONObject(0);

        double min = Double.parseDouble(result.get("mintempm").toString());
        double max = Double.parseDouble(result.get("maxtempm").toString());
        return (min + max) / 2;
    }
}
