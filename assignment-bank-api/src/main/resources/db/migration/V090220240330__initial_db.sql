create schema security_schema ;


create table security_schema.roles
(
    id          bigint not null
        primary key,
    description varchar(255),
    title       varchar(255)
);


create table security_schema.users
(
    id        bigserial
        primary key,
    full_name varchar(255),
    password  varchar(255),
    username  varchar(255),
    enabled   boolean,
    verified  boolean
);

create table security_schema.users_roles
(
    user_id bigint not null
        constraint user_role_user_id_fk
            references security_schema.users,
    role_id bigint not null
        constraint user_role_role_id_fk
            references security_schema.roles,
    primary key (user_id, role_id)
);


create table security_schema.verifications
(
    id                     bigserial
        primary key,
    code                   varchar(255),
    verification_date_time timestamp(6),
    user_id                bigint
        constraint use_verification_user_id_fk
            references security_schema.users
);


insert into security_schema.roles (id, description, title) values (1, 'Admin', 'USER');

