CREATE TABLE Items(
    id BIGINT not null auto_increment,
    string VARCHAR(50) not null,
    item_date TIMESTAMP not null,
    number DECIMAL(10,2) not null,
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8

