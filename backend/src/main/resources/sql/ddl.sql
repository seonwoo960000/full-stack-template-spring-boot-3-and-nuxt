create table if not exists member (
    id                 binary(16)            not null,
    authorization_type varchar(255)          not null,
    login              varchar(50)           not null,
    password_hash      varchar(255)          null,
    first_name         varchar(50)           not null,
    last_name          varchar(50)           not null,
    email              varchar(255)          not null,
    activated          boolean default false not null,
    lang_key           varchar(10)           null,
    image_url          varchar(255)          null invisible,
    activation_key     varchar(20)           null,
    reset_key          varchar(20)           null,
    reset_date         timestamp             null,
    created_by         varchar(50)           not null,
    created_date       timestamp             not null,
    last_modified_by   varchar(50)           not null,
    last_modified_date timestamp             not null,
    constraint member_pk
        primary key (id),
    constraint member_login_authorization_type_uk
        unique (login, authorization_type)
);

create table if not exists member_authority (
    member_id      binary(16)  not null,
    authority_name varchar(20) not null,
    constraint member_authority_pk
        primary key (member_id, authority_name)
);

create table if not exists authorityType (
    name varchar(20) not null,
    constraint authority_pk primary key (name)
);

create table if not exists post (
    id                 binary(16)   not null,
    membeR_id          binary(16)   not null,
    title              varchar(255) not null,
    content            text         not null,
    created_by         varchar(50)  not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(50)  not null,
    last_modified_date timestamp    not null,
    constraint post_pk
        primary key (id),
    constraint post_member_id_fk foreign key (membeR_id) references member(id)
)
