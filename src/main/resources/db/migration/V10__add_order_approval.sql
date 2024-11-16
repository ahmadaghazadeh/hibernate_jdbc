create table order_approval (
                                id bigint not null auto_increment,
                                created_date datetime(6),
                                last_modified_date datetime(6),
                                approve_by varchar(255),
                                primary key (id)
) engine=InnoDB;

alter table order_header
    add column order_approval_id bigint;

alter table order_header
    add constraint order_header_order_approval_fk
        foreign key (order_approval_id)
            references order_approval (id);