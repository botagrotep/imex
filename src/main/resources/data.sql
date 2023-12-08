CREATE TABLE Person (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name char(255),
    login char(255),
    password char(255)
);
CREATE TABLE Transportation (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    transportation_date date,
    loading_city char(255),
    loading_country char(255),
    unloading_city char(255),
    unloading_country char(255),
    type_of_track char(255),
    client_company char(255),
    manager_id int,
    cargo char(255),
    temperature_min int,
    temperature_max int,
    coordinator_comment char(255),
    transportation_comment char(255),
    comment char(255)

);

INSERT INTO Person (id, name, login, password) VALUES (1, 'Світлецька Світлана', 'svitlana', 'svitlana');
INSERT INTO Person (id, name, login, password) VALUES (2, 'Антон Уліцький', 'anton', 'anton');

--INSERT INTO Transportation (id, transportation_date, login, password)
--VALUES (1, 'Антон Уліцький', 'anton', 'anton');