CREATE DATABASE kurs;


CREATE TABLE users (
    user_id serial PRIMARY KEY,
    user_name character varying(20) NOT NULL,
    email character varying(30) NOT NULL,
    password character varying(20) NOT NULL,
    avatar character varying(100000),
    teams character varying(10000),
    chats character varying(10000)
);

CREATE TABLE team (
    team_id serial PRIMARY KEY,
    team_name character varying(30) NOT NULL,
    team_participants character varying(10000) NOT NULL,
    admins character varying(10000) NOT NULL,
    avatar character varying(100000),
    channels character varying(10000)
);

CREATE TABLE chat (
    chat_id serial PRIMARY KEY,
    chat_name character varying(30) NOT NULL,
    chat_participants character varying(10000),
    team_id serial references team(team_id),
    avatar character varying(100000),
    chat_type character varying(20)
);

CREATE TABLE message (
    message_id serial PRIMARY KEY,
    chat_id serial references chat(chat_id) NOT NULL,
    user_id serial references users(user_id) NOT NULL,
    time character varying(255) NOT NULL,
    text character varying(5000) NOT NULL,
    status boolean NOT NULL,
    is_fixed boolean NOT NULL,
    ref integer
);

CREATE TABLE chat_ref (
    ref_id serial PRIMARY KEY,
    w2m character varying(200),
    git character varying(200),
    meeting character varying(200),
    chat_id serial references chat(chat_id) NOT NULL
);

INSERT INTO users VALUES (0, 'admin', 'admin@gmail.com', 'admin', 'ACTIVE');
INSERT INTO team VALUES (0, 'admin team', '0 ', '0');