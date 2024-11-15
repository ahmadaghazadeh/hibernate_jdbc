alter table order_header
    add column order_status tinyint check (order_status between 0 and 2);


insert
into
    order_header
(bill_to_address_address, bill_to_address_city, bill_to_address_state, bill_to_address_zip_code, customer, order_status, shipping_address, shipping_city, shipping_state, shipping_zip_code)
values
    ('a', 'b', 'c', 'd', '1asd', 0, '1', '2', '2', '4')