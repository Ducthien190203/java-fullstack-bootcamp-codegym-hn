use classicmodels;
select * from customers;
alter table customers  add index idx_customername(customername);
alter table customers add index idx_city(city);
EXPLAIN select * from customers where customername='Land of Toys Inc.';
explain select * from customers where city='Las Vegas';
ALTER TABLE customers ADD INDEX idx_full_name(contactFirstName, contactLastName);
EXPLAIN SELECT * FROM customers WHERE contactFirstName = 'Jean' or contactFirstName = 'King';
ALTER TABLE customers DROP INDEX idx_full_name;

delimiter //
create procedure findAllCustomers()
begin
select * from customers;
end //
delimiter ;

call findAllCustomers();

delimiter //
drop procedure if exists findAllCustomers //
create procedure findAllCustomers()
begin
select * from customers where  customernumber=175;
end //
delimiter ;
call findAllCustomers();


delimiter //
create procedure getCusById(in cusNum int)
begin
select * from customers where customerNumber =cusNum;
end //
delimiter ;

call getCusById(175);

delimiter //
create procedure getCustomersCountByCity
(in in_city varchar(50), out total int)
begin
select count(customernumber)
into total
from customers
where city = in_city;
end //
delimiter ;
call getCustomersCountByCity('Lyon',@total);
select @total;
 
 describe customers;
 select * from customers;
 delimiter //
 create procedure getCustomerByCity
 (in in_country varchar(50), out out_customerName varchar(50), out out_phone varchar(50))
 begin
 select customername,phone
 into out_customerName,out_phone
 from customers
 where country=in_country
 limit 1;
 end //
 delimiter ;
 drop procedure if exists getCustomerByCity;
 
 call getCustomerByCity('USA',@customerName,@phone);
select @customerName,@phone;
 
 delimiter //
 create procedure SetCounter
 (inout counter int,
 in inc int)	
 begin
 set counter = counter + inc;
 end //
 delimiter ;
 SET @counter = 1;

CALL SetCounter(@counter,1); -- 2

CALL SetCounter(@counter,1); -- 3

CALL SetCounter(@counter,5); -- 8

call setcounter(@counter,2);

SELECT @counter; 

create view customer_view as
select customernumber, customername,phone 
from customers;

select* from customer_view;

CREATE OR REPLACE VIEW customer_views AS

SELECT customerNumber, customerName, contactFirstName, contactLastName, phone

FROM customers

WHERE city = 'Nantes';
select* from customer_views;
drop view customer_views;

delimiter //
create trigger set_default_status
before insert on orders
for each row
begin 
if new.status is null or new.status = '' then
set new.status ='In Process';
end if;
end //
delimiter ;
INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, customerNumber)
VALUES (10500, '2025-06-23', '2025-06-30', NULL, 103);
INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, customerNumber)
VALUES (10501, '2025-06-23', '2025-06-30', NULL, 'Shipped', 103);

select orderNumber,status from orders where orderNumber in(10500,10501);



