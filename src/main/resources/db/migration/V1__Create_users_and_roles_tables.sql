CREATE TABLE access_token (
                              id BIGINT IDENTITY(1,1) PRIMARY KEY,
                              created_by VARCHAR(255) NOT NULL,
                              created_date DATETIME,
                              updated_by VARCHAR(255) NOT NULL,
                              updated_date DATETIME,
                              account_id BIGINT,
                              expired DATETIME,
                              refresh_token VARCHAR(600),
                              token VARCHAR(600),
                              user_agent VARCHAR(255)
);

CREATE TABLE packages (
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,
                          created_by VARCHAR(255) NOT NULL,
                          created_date DATETIME,
                          updated_by VARCHAR(255) NOT NULL,
                          updated_date DATETIME,
                          label VARCHAR(255),
                          packaging_name VARCHAR(255),
                          price NUMERIC(19, 4),
                          uom VARCHAR(255),
                          value VARCHAR(255)
);

CREATE TABLE provinces (
                           id BIGINT IDENTITY(1,1) PRIMARY KEY,
                           created_by VARCHAR(255) NOT NULL,
                           created_date DATETIME,
                           updated_by VARCHAR(255) NOT NULL,
                           updated_date DATETIME,
                           label VARCHAR(255),
                           licenseplates VARCHAR(255),
                           value VARCHAR(255)
);

CREATE TABLE remaining_prices (
                                  id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                  created_by VARCHAR(255) NOT NULL,
                                  created_date DATETIME,
                                  updated_by VARCHAR(255) NOT NULL,
                                  updated_date DATETIME,
                                  distance_code VARCHAR(255),
                                  distance_name VARCHAR(255),
                                  time VARCHAR(255)
);

CREATE TABLE prices (
                        id BIGINT IDENTITY(1,1) PRIMARY KEY,
                        created_by VARCHAR(255) NOT NULL,
                        created_date DATETIME,
                        updated_by VARCHAR(255) NOT NULL,
                        updated_date DATETIME,
                        max_kg FLOAT,
                        min_kg FLOAT,
                        price_add BIT,
                        price_code VARCHAR(255),
                        price_name VARCHAR(255),
                        price_number VARCHAR(255),
                        remaining_price_id BIGINT FOREIGN KEY REFERENCES remaining_prices(id)
);

CREATE TABLE roles (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       created_by VARCHAR(255) NOT NULL,
                       created_date DATETIME,
                       updated_by VARCHAR(255) NOT NULL,
                       updated_date DATETIME,
                       name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE transportation_routes (
                                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                       created_by VARCHAR(255) NOT NULL,
                                       created_date DATETIME,
                                       updated_by VARCHAR(255) NOT NULL,
                                       updated_date DATETIME,
                                       distance_name VARCHAR(255),
                                       label VARCHAR(255),
                                       transportation_route_code VARCHAR(255),
                                       value VARCHAR(255),
                                       province_id BIGINT FOREIGN KEY REFERENCES provinces(id)
);

CREATE TABLE user_token (
                            id BIGINT IDENTITY(1,1) PRIMARY KEY,
                            created_by VARCHAR(255) NOT NULL,
                            created_date DATETIME,
                            updated_by VARCHAR(255) NOT NULL,
                            updated_date DATETIME,
                            expired_time DATETIME,
                            refresh_token VARCHAR(500),
                            user_id BIGINT
);

CREATE TABLE users (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       created_by VARCHAR(255) NOT NULL,
                       created_date DATETIME,
                       updated_by VARCHAR(255) NOT NULL,
                       updated_date DATETIME,
                       email VARCHAR(255) UNIQUE,
                       name VARCHAR(255),
                       password_hash VARCHAR(255) NOT NULL,
                       status VARCHAR(255),
                       username VARCHAR(255) UNIQUE
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL FOREIGN KEY REFERENCES users(id),
                            role_id BIGINT NOT NULL FOREIGN KEY REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);