
DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id int(6) PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    email varchar(50),
    drop_box_access_token  varchar(50)
);



