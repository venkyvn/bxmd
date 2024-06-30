drop table if exists access_token;
drop table if exists packages;
drop table if exists prices;
drop table if exists remaining_prices;
drop table if exists transportation_routes;
drop table if exists provinces;
drop table if exists user_roles;
drop table if exists roles;
drop table if exists user_token;
drop table if exists users;


CREATE TABLE access_token
(
    id            BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date  DATETIME,
    updated_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date  DATETIME,
    account_id    BIGINT,
    expired       DATETIME,
    refresh_token VARCHAR(600),
    token         VARCHAR(600),
    user_agent    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS
);

CREATE TABLE packages
(
    id             BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by     NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date   DATETIME,
    updated_by     NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date   DATETIME,
    label          NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    packaging_name NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    price          NUMERIC(19, 4),
    uom            NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    value          NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS
);

CREATE TABLE provinces
(
    id            BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date  DATETIME,
    updated_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date  DATETIME,
    label         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    licenseplates NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    value         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS
);

CREATE TABLE remaining_prices
(
    id            BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date  DATETIME,
    updated_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date  DATETIME,
    distance_code NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    distance_name NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    time          VARCHAR(255)
);

CREATE TABLE prices
(
    id                 BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date       DATETIME,
    updated_by         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date       DATETIME,
    max_kg             FLOAT,
    min_kg             FLOAT,
    price_add          BIT,
    price_code         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    price_name         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    price_number       NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    remaining_price_id BIGINT FOREIGN KEY REFERENCES remaining_prices (id)
);

CREATE TABLE roles
(
    id           BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by   NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date DATETIME,
    updated_by   NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date DATETIME,
    name         NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL UNIQUE
);

CREATE TABLE transportation_routes
(
    id                        BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by                NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date              DATETIME,
    updated_by                NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date              DATETIME,
    distance_name             NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    label                     NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    transportation_route_code NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    value                     NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    province_id               BIGINT FOREIGN KEY REFERENCES provinces (id)
);

CREATE TABLE user_token
(
    id            BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date  DATETIME,
    updated_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date  DATETIME,
    expired_time  DATETIME,
    refresh_token VARCHAR(500),
    user_id       BIGINT
);

CREATE TABLE users
(
    id            BIGINT IDENTITY (1,1) PRIMARY KEY,
    created_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    created_date  DATETIME,
    updated_by    NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    updated_date  DATETIME,
    email         VARCHAR(255) UNIQUE,
    name          NVARCHAR(255) COLLATE SQL_Latin1_General_CP1_CI_AS,
    password_hash VARCHAR(255) NOT NULL,
    status        VARCHAR(255),
    username      VARCHAR(255) UNIQUE
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL FOREIGN KEY REFERENCES users (id),
    role_id BIGINT NOT NULL FOREIGN KEY REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);