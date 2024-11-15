create table order_header (
                              id bigint not null auto_increment,
                              bill_to_address_address varchar(255),
                              bill_to_address_city varchar(255),
                              bill_to_address_state varchar(255),
                              bill_to_address_zip_code varchar(255),
                              customer varchar(255),
                              shipping_address varchar(255),
                              shipping_city varchar(255),
                              shipping_state varchar(255),
                              shipping_zip_code varchar(255),
                              primary key (id)
) engine=InnoDB;

