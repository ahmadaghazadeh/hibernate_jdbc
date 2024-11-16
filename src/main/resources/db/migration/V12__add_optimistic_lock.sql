alter table order_approval
    add column order_header_id bigint;

alter table order_header
    add column version integer default 0;

alter table order_approval
    add constraint order_approval_order_header_fk
        foreign key (order_header_id)
            references order_header (id)