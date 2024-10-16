CREATE TABLE TUTORIAL (
    ID NUMBER AUTO_INCREMENT NOT NULL,
    TITLE VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    PUBLISHED BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (ID)
);

-- Oracle AUTO_INCREMENT impl - h2 does not support ORACLE behaviours

-- CREATE SEQUENCE TUTORIAL_PK_SEQ
--     START WITH 1
--     INCREMENT BY 1
--     NOCACHE
--     NOCYCLE;
--
-- CREATE OR REPLACE TRIGGER ON_INSERT_TUTORIAL
--     BEFORE INSERT ON TUTORIAL
--     FOR EACH ROW
-- BEGIN
--     SELECT TUTORIAL_PK_SEQ.nextval
--     INTO :new.ID
--     FROM dual;
-- END;