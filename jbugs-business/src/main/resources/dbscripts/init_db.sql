use msg_training;

create table users
(
    id            bigint auto_increment
        primary key,
    counter       int                  null,
    email         varchar(255)         not null,
    first_name    varchar(255)         not null,
    last_name     varchar(255)         not null,
    mobile_number varchar(255)         not null,
    password      varchar(255)         not null,
    status        tinyint(1) default 0 not null,
    username      varchar(255)         not null
);

create table roles
(
    id   bigint auto_increment
        primary key,
    type varchar(255) not null
);

create table permissions
(
    ID          bigint auto_increment
        primary key,
    description varchar(255) not null,
    type        varchar(255) not null
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK_users_roles_role_id
        foreign key (role_id) references roles (id),
    constraint FK_users_roles_user_id
        foreign key (user_id) references users (id)
);

create table permissions_roles
(
    role_id       bigint not null,
    permission_id bigint not null,
    primary key (role_id, permission_id),
    constraint FK_permissions_roles_permission_id
        foreign key (permission_id) references permissions (ID),
    constraint FK_permissions_roles_role_id
        foreign key (role_id) references roles (id)
);

create table bugs
(
    id            bigint auto_increment
        primary key,
    assigned_to   varchar(255) not null,
    created_by    varchar(255) not null,
    description   varchar(255) not null,
    fixed_version varchar(255) not null,
    severity      varchar(255) not null,
    status        varchar(255) not null,
    targetDate    date         not null,
    title         varchar(255) not null,
    version       varchar(255) not null
);

create table notifications
(
    id        bigint auto_increment
        primary key,
    date      date         not null,
    message   varchar(255) not null,
    type      varchar(255) not null,
    user_name varchar(255) not null
);

create table attachments
(
    id     bigint auto_increment
        primary key,
    text   varchar(255) not null,
    bug_id bigint       not null,
    constraint FK_attachments_bug_id
        foreign key (bug_id) references bugs (id)
);

create table comments
(
    id     bigint auto_increment
        primary key,
    date   date         not null,
    text   varchar(255) not null,
    username   varchar(255) not null,
    bug_id bigint       not null,
    constraint FK_comments_bug_id
        foreign key (bug_id) references bugs (id)
);
create table notifications
(
    id        bigint auto_increment
        primary key,
    date      date         not null,
    message   varchar(255) not null,
    type      varchar(255) not null,
    user_name varchar(255) not null
);
