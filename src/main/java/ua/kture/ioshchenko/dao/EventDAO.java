package ua.kture.ioshchenko.dao;


import ua.kture.ioshchenko.model.Event;

import java.util.Date;

public interface EventDAO {
    public Event add(Event event);

    public Event get(Date date);

    public Event update(Event event);
}
