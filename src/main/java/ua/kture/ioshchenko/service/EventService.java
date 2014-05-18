package ua.kture.ioshchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kture.ioshchenko.dao.EventDAO;
import ua.kture.ioshchenko.model.Event;

import javax.transaction.Transactional;
import java.util.Date;

@Service("eventService")
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    @Transactional
    public Event add(Event event) {
        return eventDAO.add(event);
    }

    @Transactional
    public Event get(Date date) {
        return eventDAO.get(date);
    }

    @Transactional
    public Event update(Event event) {
        return eventDAO.update(event);
    }
}
