package ua.kture.ioshchenko.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.model.User;

@Repository
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public User add(User user) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    public User get(String email, String password) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password));
        User user = (User) criteria.uniqueResult();

        return user;
    }
}
