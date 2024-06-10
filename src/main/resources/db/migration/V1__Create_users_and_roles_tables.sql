drop table user_roles;
drop table roles;
drop table users;


-- Create roles table
CREATE TABLE roles
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    created_by   VARCHAR(255),
    created_date TIMESTAMP,
    updated_by   VARCHAR(255),
    updated_date TIMESTAMP
);

-- Create users table
CREATE TABLE users
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    username      VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    name          VARCHAR(255),
    status        VARCHAR(50),
    created_by    VARCHAR(255),
    created_date  TIMESTAMP,
    updated_by    VARCHAR(255),
    updated_date  TIMESTAMP
);

-- Create user_roles junction table
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);
