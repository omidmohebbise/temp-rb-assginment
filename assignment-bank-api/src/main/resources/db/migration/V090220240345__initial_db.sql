create schema bank_schema;

create table bank_schema.bank_account (
    id serial primary key,
    account_number varchar(255) not null,
    balance decimal(10, 2) not null
);

create table bank_schema.bank_transaction (
    id serial primary key,
    account_id integer not null,
    amount decimal(10, 2) not null,
    transaction_type varchar(255) not null,
    transaction_date timestamp not null
);
