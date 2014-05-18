package ua.kture.ioshchenko.dao;

import org.hibernate.Criteria;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.model.Event;

import java.util.Date;

@Repository
public class EventDAOImp extends HibernateDaoSupport implements EventDAO {

    @Autowired
    public EventDAOImp(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Event add(Event event) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(event);
        return event;
    }

    @Override
    public Event get(Date date) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Event.class)
                .add(Restrictions.eq("date", date));
        Event event = (Event) criteria.uniqueResult();

        return event;
    }

    @Override
    public Event update(Event event) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(event);
        return event;
    }
}
