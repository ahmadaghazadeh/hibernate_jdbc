create table author (
                        id bigint not null auto_increment,
                        first_name varchar(255),
                        last_name varchar(255),
                        primary key (id)
) engine=InnoDB;

create table book (
                      id bigint not null auto_increment,
                      isbn varchar(255),
                      publisher varchar(255),
                      title varchar(255),
                      author_id bigint,
                      primary key (id)
) engine=InnoDB;

alter table book
    add constraint
        foreign key (author_id)
            references author (id);


INSERT INTO author (first_name, last_name)
VALUES ('John', 'Doe');

INSERT INTO author (first_name, last_name)
VALUES ('Ahmad', 'Aghazadeh');

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-3-16-148410-0', 'Penguin Books', 'Sample Book Title', 1);

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-3-16-148410-2', 'Penguin Books2', 'Sample Book Title2', 1);

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-3-16-148410-3', 'Penguin Books3', 'Sample Book Title3', 1);

