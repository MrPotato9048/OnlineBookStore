create table if not exists book (
                                    id bigint generated by default as identity not null,
                                    title varchar(255),
                                    author varchar(100),
                                    price long,
                                    description varchar(max),
                                    stock int,
                                    filename varchar(255),
                                    contentType varchar(255),
                                    content blob,
                                    primary key (id)
);

create table if not exists users (
                                     username varchar(50) not null,
                                     password varchar(100) not null,
                                     primary key (username)
);

create table if not exists userRoles (
                                         userRoleId int generated always as identity,
                                         username varchar(50) not null,
                                         role varchar(50) not null,
                                         primary key (userRoleId),
                                         foreign key (username) references users(username)
);

create table if not exists comment (
                                       commentId int generated by default as identity not null,
                                       bookId bigint not null,
                                       username varchar(50) not null,
                                       subject varchar(max),
                                       primary key (commentId),
                                       foreign key (bookId) references book,
                                       foreign key (username) references users(username)
);