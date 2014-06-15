package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.model.User;

/*        Root User: adminW1zQSF6
        Root Password: Qlj-rNRBvLyu
        Database Name: fake
        127.2.121.130:3306
        Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/

        Root User: adminW1zQSF6
        Root Password: Qlj-rNRBvLyu
        URL: https://fake-ioschenko.rhcloud.com/phpmyadmin/

*/


public interface UserDAO {
    void add(User user);

    User get(String email);

    void update(User user);

    User getUserByInstagramUserId(String instagramUserId);
}
