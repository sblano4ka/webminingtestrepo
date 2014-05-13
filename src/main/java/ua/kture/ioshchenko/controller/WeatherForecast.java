package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.DropBoxAPI;
import ua.kture.ioshchenko.service.weatherapi.WeatherHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class WeatherForecast {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private WeatherHistory weatherHistory = new WeatherHistory();

    @Autowired
    private DropBoxAPI dropBoxAPI;

    @RequestMapping(value = "/forecast", method = RequestMethod.POST)
    public String getWeatherForecast(@RequestParam String date, @RequestParam String city, Model model) {
        double temp = 0;
        try {
            temp = weatherHistory.getInformation(simpleDateFormat.parse(date), city);
            try {
                String s = dropBoxAPI.getAccessToken("!#@");
            } catch (DbxException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {

        }
        model.addAttribute("result", temp);
        return "weather";
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String viewPageIndex(Model model) {
        return "weather";
    }
}
