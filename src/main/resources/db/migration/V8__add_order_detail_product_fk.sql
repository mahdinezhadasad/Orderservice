alter table order_line add column product_id bigint;
alter table order_line add constraint order_line_product_fk
    FOREIGN KEY (product_id) references product(id);