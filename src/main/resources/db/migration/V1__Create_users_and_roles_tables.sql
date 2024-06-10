create table access_token
(
    id            bigint generated by default as identity
        primary key,
    created_by    varchar(255) not null,
    created_date  timestamp,
    updated_by    varchar(255) not null,
    updated_date  timestamp,
    account_id    bigint,
    expired       timestamp,
    refresh_token varchar(600),
    token         varchar(600),
    user_agent    varchar(255)
);

alter table access_token
    owner to admin;

create table packages
(
    id             bigint generated by default as identity
        primary key,
    created_by     varchar(255) not null,
    created_date   timestamp,
    updated_by     varchar(255) not null,
    updated_date   timestamp,
    label          varchar(255),
    packaging_name varchar(255),
    price          numeric(19, 4),
    uom            varchar(255),
    value          varchar(255)
);

alter table packages
    owner to admin;

create table provinces
(
    id            bigint generated by default as identity
        primary key,
    created_by    varchar(255) not null,
    created_date  timestamp,
    updated_by    varchar(255) not null,
    updated_date  timestamp,
    label         varchar(255) not null,
    licenseplates varchar(255) not null,
    value         varchar(255) not null
);

alter table provinces
    owner to admin;

create table remaining_prices
(
    id            bigint generated by default as identity
        primary key,
    created_by    varchar(255) not null,
    created_date  timestamp,
    updated_by    varchar(255) not null,
    updated_date  timestamp,
    distance_code varchar(255) not null,
    distance_name varchar(255) not null,
    time          varchar(255)
);

alter table remaining_prices
    owner to admin;

create table prices
(
    id                 bigint generated by default as identity
        primary key,
    created_by         varchar(255)     not null,
    created_date       timestamp,
    updated_by         varchar(255)     not null,
    updated_date       timestamp,
    max_kg             double precision,
    min_kg             double precision not null,
    price_add          boolean,
    price_name         varchar(255)     not null,
    price_number       varchar(255)     not null,
    remaining_price_id bigint
        constraint fkl6coloem9j26htg8s9ak2fywh
            references remaining_prices
);

alter table prices
    owner to admin;

create table roles
(
    id           bigint generated by default as identity
        primary key,
    created_by   varchar(255) not null,
    created_date timestamp,
    updated_by   varchar(255) not null,
    updated_date timestamp,
    name         varchar(255) not null
        constraint uk_ofx66keruapi6vyqpv6f2or37
            unique
);

alter table roles
    owner to admin;

create table transportation_routes
(
    id                        bigint generated by default as identity
        primary key,
    created_by                varchar(255) not null,
    created_date              timestamp,
    updated_by                varchar(255) not null,
    updated_date              timestamp,
    distance_name             varchar(255) not null,
    label                     varchar(255) not null,
    transportation_route_code varchar(255) not null,
    value                     varchar(255) not null,
    province_id               bigint
        constraint fk4hq38brw1dp07gk0s08iox217
            references provinces
);

alter table transportation_routes
    owner to admin;

create table user_token
(
    id            bigint generated by default as identity
        primary key,
    created_by    varchar(255) not null,
    created_date  timestamp,
    updated_by    varchar(255) not null,
    updated_date  timestamp,
    expired_time  timestamp,
    refresh_token varchar(500),
    user_id       bigint
);

alter table user_token
    owner to admin;

create table users
(
    id            bigint generated by default as identity
        primary key,
    created_by    varchar(255) not null,
    created_date  timestamp,
    updated_by    varchar(255) not null,
    updated_date  timestamp,
    email         varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    name          varchar(255),
    password_hash varchar(255) not null,
    status        varchar(255),
    username      varchar(255)
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to admin;

create table user_roles
(
    user_id bigint not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id bigint not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    primary key (user_id, role_id)
);

alter table user_roles
    owner to admin;

