create table order_line (
                            id bigint not null auto_increment,
                            created_date timestamp,
                            last_modified_date timestamp,
                            quantity integer,
                            order_header_id bigint,
                            primary key (id)
) engine=InnoDB;

alter table order_line
    add constraint
        foreign key (order_header_id)
            references order_header (id);