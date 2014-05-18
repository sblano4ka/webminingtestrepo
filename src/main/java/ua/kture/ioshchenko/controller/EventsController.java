package ua.kture.ioshchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kture.ioshchenko.model.Event;
import ua.kture.ioshchenko.model.EventBean;
import ua.kture.ioshchenko.service.EventService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class EventsController {

    @Autowired
    private EventService eventService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String indexPage(Model model) {
        return "events";
    }


    @RequestMapping(value = "/getevent", method = RequestMethod.POST)
    @ResponseBody
    public Event getEvent(@RequestBody EventBean eventBean, Model model) {
        Event event = null;
        try {
            event = eventService.get(simpleDateFormat.parse(eventBean.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (event == null) {
            event = new Event();
        }

        return event;
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    @ResponseBody
    public Event addEvent(@RequestBody EventBean eventBean, Model model) {
        Event event = new Event();
        event.setMessage(eventBean.getMessage());
        try {
            event.setDate(simpleDateFormat.parse(eventBean.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        eventService.add(event);

        return event;
    }

    @RequestMapping(value = "/updateevent", method = RequestMethod.POST)
    @ResponseBody
    public Event updateEvent(@RequestBody EventBean eventBean, Model model) {
        Event event = null;
        try {
            event = eventService.get(simpleDateFormat.parse(eventBean.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (event != null) {
            event.setMessage(eventBean.getMessage());
            eventService.update(event);
        }
        return event;
    }
}
