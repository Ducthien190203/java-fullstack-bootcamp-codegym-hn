use demo;
drop database demo;
create database demo;
drop table products;
CREATE TABLE Products (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    productCode VARCHAR(50) NOT NULL,
    productName VARCHAR(100) NOT NULL,
    productPrice DECIMAL(10 , 2 ) NOT NULL,
    productAmount INT NOT NULL,
    productDescription TEXT,
    productStatus VARCHAR(20) DEFAULT 'active'
);
INSERT INTO Products (productCode, productName, productPrice, productAmount, productDescription, productStatus)
VALUES 
('P001', 'Laptop Dell Inspiron', 15999000.00, 10, 'Laptop Dell Inspiron 15.6 inch, RAM 8GB, SSD 512GB', 'active'),
('P002', 'Chuột Logitech M185', 299000.00, 50, 'Chuột không dây nhỏ gọn, kết nối USB receiver', 'active'),
('P003', 'Bàn phím cơ AKKO 3068', 1299000.00, 20, 'Bàn phím cơ không dây, layout 65%, switch Gateron', 'active'),
('P004', 'Màn hình Samsung 24\"', 3490000.00, 15, 'Màn hình LED 24 inch, Full HD, tần số 75Hz', 'active'),
('P005', 'Tai nghe Sony WH-CH520', 1490000.00, 25, 'Tai nghe Bluetooth chống ồn, pin lâu', 'inactive');

alter table products add unique index idx_productCode(productCode);
alter table products add unique index idx_productPrice(productName,productPrice);
explain select * from products where productCode='P005';

create view product_views as
select productCode,productName,productPrice,productStatus
from products;
select * from product_views;

create or replace view product_views as
select productCode,productName,productPrice
from products;
select*from product_views;
drop view product_views;
delimiter //
create procedure getAllRecords()
begin 
select * from products;
end //
delimiter ;
call getAllRecords();

delimiter //
create procedure add_product(
in p_code varchar(50),
in p_name varchar(100),
in p_price decimal(10,2),
in p_amount int,
in p_description text,
in p_status varchar(20)
)
begin 
insert into products
(productCode,productName,productPrice,productAmount,productDescription,productStatus)
values
(p_code,p_name,p_price,p_amount,p_description,p_status);
end //
delimiter ;

CALL add_product(
  'P007',
  'Webcam Logitech C920',
  1790000.00,
  12,
  'Webcam Full HD 1080p hỗ trợ họp trực tuyến',
  'active'
);
SELECT * FROM products;
 -- WHERE productCode = 'P007';
 
 
 delimiter //
 create procedure updateProductInfoById
 (
 in p_id int,
 in p_code varchar(50),
 in p_name varchar(100),
 in p_price decimal(10,2),
 in p_amount int,
 in p_description text,
 in p_status varchar(20)
 )
 begin 
 update products
 set 
 productCode = p_code,
        productName = p_name,
        productPrice = p_price,
        productAmount = p_amount,
        productDescription = p_description,
        productStatus = p_status
where Id = p_id;
end //
delimiter ;

CALL updateProductInfoById(
    3,
    'P003-U',
    'Bàn phím cơ AKKO 3068 v2',
    1399000.00,
    18,
    'Bàn phím cơ không dây nâng cấp switch Gateron Pro',
    'active'
);
select * from products where Id=3;

delimiter //
create procedure remove_product_by_id(
in p_id int)
begin
delete from products
where id=p_id;
end //
delimiter ;
call remove_product_by_id(3);
select * from products;




 




