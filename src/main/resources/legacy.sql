
DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id int(6) PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    email varchar(50),
    UNIQUE (id),
    UNIQUE (email)
);

DROP TABLE IF EXISTS events;

CREATE TABLE events(
    id int(6) PRIMARY KEY AUTO_INCREMENT,
    date DATE ,
    message varchar(300),
    UNIQUE (id)
);
