CREATE TABLE Items_audit(
    id BIGINT not null auto_increment,
    string VARCHAR(50) not null,
    old_string VARCHAR(50) not null,
    item_date TIMESTAMP not null,
    old_item_date TIMESTAMP not null,
    number DECIMAL(10,2) not null,
    old_number DECIMAL(10,2) not null,
    operation CHAR(1),
    create_at TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8

