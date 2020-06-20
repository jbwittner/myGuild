DROP DATABASE IF EXISTS myGuild_db;
DROP USER IF EXISTS 'myGuild_user'@'localhost';

CREATE DATABASE myGuild_db;
CREATE USER 'myGuild_user'@'localhost' IDENTIFIED BY 'MyGuildPass2020';
GRANT ALL PRIVILEGES ON myGuild_db.* TO 'myGuild_user'@'localhost';