create table category (
                          id bigint not null auto_increment,
                          created_date datetime(6),
                          last_modified_date datetime(6),
                          name varchar(255),
                          primary key (id)
) engine=InnoDB;

create table product_category (
                                  product_id bigint not null,
                                  category_id bigint not null,
                                  primary key (product_id, category_id)
) engine=InnoDB;

alter table product_category
    add constraint product_category_category_fk
        foreign key (category_id)
            references category (id);

alter table product_category
    add constraint product_category_product_fk
        foreign key (product_id)
            references product (id);

