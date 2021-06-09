CREATE TABLE student
(
    student_id INTEGER     NOT NULL
        CONSTRAINT student_pk
            PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    login      VARCHAR(50)
);

ALTER TABLE student OWNER TO githubver;

CREATE UNIQUE INDEX student_login_uindex ON student (login);

