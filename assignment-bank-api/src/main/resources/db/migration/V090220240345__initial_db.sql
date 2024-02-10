create schema bank_schema;

create table bank_schema.accounts
(
    id                  bigserial
        primary key,
    account_holder_name varchar(255)     not null,
    account_number      varchar(255)     not null,
    balance             double precision not null,
    user_id             bigint
        constraint account_user_id_fk
            references security_schema.users
);

create table bank_schema.transactions
(
    id                     bigserial
        primary key,
    amount                 double precision not null,
    transaction_date_time  timestamp(6)     not null,
    destination_account_id bigint           not null
        constraint transaction_destination_account_id_fk
            references bank_schema.accounts,
    source_account_id      bigint           not null
        constraint transaction_source_account_id_fk
            references bank_schema.accounts
);
