drop table if exists users;
drop table if exists roles;
drop table if exists user_roles;

CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    confirm_yn BOOLEAN,
    renew_password BOOLEAN,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    modified_at DATETIME NOT NULL,
    modified_by VARCHAR(100) NOT NULL
);

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_type VARCHAR(255)
);

CREATE TABLE user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    role_id BIGINT,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE groups (
  group_sn BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE group_user (
  group_user_sn BIGINT AUTO_INCREMENT PRIMARY KEY,
  group_sn BIGINT,
  username VARCHAR(255),
  FOREIGN KEY (group_sn) REFERENCES groups (group_sn),
  FOREIGN KEY (username) REFERENCES users (username)
);

-- CREATE TABLE user_revision (
--     rev BIGINT AUTO_INCREMENT PRIMARY KEY,
--     timestamp BIGINT,
--     modified_by VARCHAR(255)
-- );

-- CREATE TABLE users_aud (
--     username VARCHAR(255) NOT NULL,
--     rev bigint NOT NULL,
--     revtype TINYINT,
--
--     name VARCHAR(255),
--     name_mod BOOLEAN DEFAULT FALSE,
--     password VARCHAR(255),
--     password_mod BOOLEAN DEFAULT FALSE,
--     phone_number VARCHAR(255),
--     phone_number_mod BOOLEAN DEFAULT FALSE,
--     email VARCHAR(255),
--     email_mod BOOLEAN DEFAULT FALSE,
--     confirm_yn BOOLEAN,
--     confirm_yn_mod BOOLEAN DEFAULT FALSE,
--     renew_password BOOLEAN,
--     renew_password_mod BOOLEAN DEFAULT FALSE,
--
--     CONSTRAINT user_aud_pk PRIMARY KEY (username, rev),
--     FOREIGN KEY (rev) REFERENCES user_revision (rev)
-- );