alter table order_line
    add column product_id bigint;

create table product (
                         id bigint not null auto_increment,
                         created_date datetime(6),
                         last_modified_date datetime(6),
                         name varchar(255),
                         primary key (id)
) engine=InnoDB;

alter table order_line
    add constraint order_line_product_fk
        foreign key (product_id)
            references product (id);