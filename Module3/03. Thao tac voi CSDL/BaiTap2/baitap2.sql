use quanlybanhang;
show tables;
describe customer;
insert into customer(cName,cAge) 
values
('Minh Quan',10),
('Ngoc Oanh',20),
('Hong Ha',50);

select * from `order`;
describe `order`;
show create table `order`;
alter table `order` modify ototalprice double null;

ALTER TABLE orderdetail DROP FOREIGN KEY orderdetail_ibfk_1;
alter table `order` modify oid int;
alter table orderdetail modify oid int;
ALTER TABLE orderdetail
ADD CONSTRAINT orderdetail_ibfk_1
FOREIGN KEY (oID) REFERENCES `order`(oid);

insert into `order`(oid,cid,odate,ototalprice) values
('1','1','2006-3-21',null),
('2','2','2006-3-23',null),
('3','1','2006-3-16',null);

describe product;

ALTER TABLE orderdetail DROP FOREIGN KEY orderdetail_ibfk_2;
ALTER TABLE product MODIFY pprice INT;
ALTER TABLE orderdetail MODIFY pID INT;
ALTER TABLE orderdetail
ADD CONSTRAINT orderdetail_ibfk_2
FOREIGN KEY (pID) REFERENCES product(pid);



insert into product(pname,pprice) values
('May Giat',3),('Tu Lanh',5),('Dieu Hoa',7),('Quat',1),('Bep Dien',2);
select* from product;

describe orderdetail;
select * from orderdetail;

insert into orderdetail(oID,pID,odQTY) values
(1,1,3),(1,3,7),(1,4,2),(2,1,1),(3,1,8),(2,5,4),(2,3,3);

select oID,oDate,oTotalPrice from `order`;
select c.cname,p.pname,od.odQTY from customer c
join `order` o on c.cid = o.cid
join orderdetail od on o.oid=od.oid
join product p on p.pid=od.pid;

select c.* from customer c 
left join `order` o on c.cid=o.cid
where o.oid is null;

select o.oid,o.odate,(od.odQTY*p.pPrice) as ototalprice
from `order` o
join orderdetail od on o.oid=od.oid
join product p on od.pid=p.pid;





 





