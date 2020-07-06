CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                   COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL      COMMENT 'Название',
    full_name   VARCHAR(100)NOT NULL      COMMENT 'Полное название',
    inn         VARCHAR(10) NOT NULL      COMMENT 'ИНН',
    kpp         VARCHAR(9) NOT NULL       COMMENT 'КПП',
    address     VARCHAR(200)NOT NULL      COMMENT 'Адрес',
    phone       VARCHAR(20)               COMMENT 'Телефон',
    is_active   BOOLEAN NOT NULL          COMMENT 'Активность в настоящий момент'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id          INTEGER                   COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    org_id      INTEGER NOT NULL          COMMENT 'Идентификатор организации, к которой принадлежит офис',
    name        VARCHAR(50) NOT NULL      COMMENT 'Название',
    address     VARCHAR(200) NOT NULL     COMMENT 'Адрес',
    phone       VARCHAR(20)               COMMENT 'Телефон',
    is_active   BOOLEAN NOT NULL          COMMENT 'Активность'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    office_id           INTEGER NOT NULL      COMMENT 'Идентификатор офиса, в котором работает сотрудник',
    doc_id              INTEGER NOT NULL      COMMENT 'Идентификатор документа, удостоверяющего личность',
    country_id          INTEGER NOT NULL      COMMENT 'Код страны',
    first_name          VARCHAR(50) NOT NULL  COMMENT 'Имя',
    second_name         VARCHAR(50)           COMMENT 'Фамилия',
    middle_name         VARCHAR(50)           COMMENT 'Отчество',
    position            VARCHAR(50) NOT NULL  COMMENT 'Занимаемая должность',
    phone               VARCHAR(20)           COMMENT 'Идентификатор документа, удостоверяющего личность',
    is_identified       BOOLEAN NOT NULL      COMMENT 'Является ли сотрудник идентифицированным'
);
COMMENT ON TABLE User IS 'Сотрудник';

CREATE TABLE IF NOT EXISTS Doc (
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    type_id         INTEGER NOT NULL        COMMENT 'Код типа документа',
    number          VARCHAR(50) NOT NULL    COMMENT 'Номер',
    date            DATE NOT NULL           COMMENT 'Дата выдачи'
);
COMMENT ON TABLE Doc IS 'Документ';

CREATE TABLE IF NOT EXISTS Doc_Type(
    id      INTEGER                     COMMENT 'Уникальный идентификатор - код документа' PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL        COMMENT 'Название типа документа',
    code    VARCHAR(20) NOT NULL        COMMENT 'Код документа'
);
COMMENT ON TABLE Doc_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Country (
    id      INTEGER PRIMARY KEY         COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL        COMMENT 'Название страны',
    code    VARCHAR(20) NOT NULL        COMMENT 'Код страны'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE INDEX IX_Office_Organization_Id ON Office(org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User(office_id);
ALTER table User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_Doc_Id ON User(doc_id);
ALTER table User ADD FOREIGN KEY (doc_id) REFERENCES Doc(id);

CREATE INDEX IX_User_Country_Id ON User(country_id);
ALTER table User ADD FOREIGN KEY (country_id) REFERENCES Country(id);

CREATE INDEX IX_Doc_Doc_Type_Id ON Doc(type_id);
ALTER table Doc ADD FOREIGN KEY (type_id) REFERENCES Doc_Type(id);
