drop database carrental;
Create database CarRental;
use CarRental;
create table customer(
			cid int primary key auto_increment,
            phn int not null,
            pass varchar(50) not null,
            cname varchar(32) not null,
            job varchar(32),
            gender varchar(8) not null,
            address varchar(40) not null,
            date_of_birth date not null,
            license_type varchar(14)
			);
     select * from customer;                
            
create table booking(
			paid boolean not null,
            bid int primary key not null auto_increment,
            date_of_pickup date not null,
            date_of_dropoff date not null,
            need_driver int not null,
            makes_cid int not null,
            foreign key(makes_cid) references customer(cid)
            );
            
create table resulting_bill(
			book_id int not null,
            bdate date not null,
            discount real,
            foul_of_late_car real,
            total_to_pay real not null,
            primary key(book_id,bdate),
            foreign key(book_id) references booking(bid) on delete cascade
            );

create table car(
			registration_number varchar(16) not null,
			vin varchar(25) primary key not null,
			meter_reading int,
			number_of_bags int,
			number_of_occupants int,
			manufacturer_name varchar (32),
			color varchar (32),
			model varchar(32),
			Rental_price_per_day real not null,
			size varchar(18),
			year_of_manufacture int,
			fuel_efficiency varchar(32),
			transmission_system varchar(32),
			security_deposit real not null
			);

create table report(
			report_id int primary key not null auto_increment,
			rdate date not null,
			car_state varchar(70),
			need_of_service boolean not null,
			mechanic_name varchar(32)
			);

create table service(
			sname varchar(32),
			service_id int primary key not null auto_increment,
			total_cost real not null,
			car_part_name varchar(32),
            based_on_rid int not null,
            foreign key(based_on_rid) references report(report_id)
			);


create table employees(
			jid int primary key not null auto_increment,
            pass varchar(50) not null,
            still_employed boolean not null,
            ename varchar(32) not null,
            DOB date not null,
            gender varchar(8) not null,
            total_salary real not null,
            address varchar(40) not null,
            email varchar(40) not null,
            phone_num int not null
            );

create table accountant(
			jid int not null,
            primary key(jid),
            foreign key(jid) references employees(jid)
            );
            
create table manager(
			jid int not null,
            primary key(jid),
            foreign key(jid) references employees(jid)
            );
            
create table driver(
			jid int not null,
            license_end_date date not null,
            state_of_availability boolean,
            primary key(jid),
            foreign key(jid) references employees(jid)
            );
            
create table mechanic(
			jid int not null,
            primary key(jid),
            foreign key(jid) references employees(jid)
            );

create table customer2car(
			cust_id int,
            car_vin varchar(25),
            getdate date,
            primary key(cust_id,car_vin,getdate),
            foreign key(car_vin) references car(vin),
            foreign key(cust_id) references customer(cid)
            );
        
create table cust2driver(
			cid int,
            eid int,
            getdate date,
            primary key(cid,eid,getdate),
            foreign key(cid) references customer(cid),
            foreign key(eid) references driver(jid)
            );
            
create table driver2car(
			eid int,
            vin varchar(25),
            primary key(eid,vin),
            foreign key(eid) references driver(jid),
            foreign key(vin) references car(vin)
            );
            
create table car2rep2mech(
			vin varchar(25),
            rid int,
            jid int,
            primary key(vin,rid,jid),
            foreign key(vin) references car(vin),
            foreign key(rid) references report(report_id),
            foreign key(jid) references mechanic(jid)
            );
            
create table mech2service(
			sid int,
            jid int,
            primary key(sid,jid),
            foreign key(sid) references service(service_id),
            foreign key(jid) references mechanic(jid)
            );
            
insert into car(registration_number,vin,meter_reading,number_of_bags,number_of_occupants,manufacturer_name,color,model,Rental_price_per_day,size,year_of_manufacture,
			fuel_efficiency,transmission_system,security_deposit)values
('6-9600-A','SFJDA4551003100',64000,3,5,'BMW','black','520d',200,'sedan',2019,'medium','automatic',2000),
('7-5000-H','VVWDFA446100311',110000,2,5,'VW','silver','passat',150,'sedan',2018,'good','automatic',1500),
('2-6451-N','DKDFA1200211231',160000,4,5,'SKODA','daytona grey','kodiaq',170,'suv',2017,'good','automatic',1700),
('3-1245-Y','HYNDI0064513399',90000,2,5,'HYUNDAI','white','i30',110,'hatchback',2017,'excellent','manual',1100),
('4-1002-H','TYTO00129461364',150000,4,5,'TOYOTA','blue','hilux','220','pickup',2018,'bad','manual',2200);

insert into employees(ename,pass,still_employed,DOB,gender,total_salary,address,email,phone_num)values
('Sami Almasri',md5("101"),true,'1991-4-1','male',2500,'Nablus','sami_work@hotmail.com' ,0562556480),('Morad Ismael',md5("103"),true,'1995-2-23','male',2700,'Jenin', 'mor_95@yahoo.com', 0562066445),
('Sondos Radad',md5("105"),true,'1993-1-11','female',2200,'Ramallah','sondos_rad@hotmail.com',0597510612),('Rama Jaber',md5("107"),true,'1997-4-21','female',2500,'Ramallah','rama.97@yahoo.com',0598355201),
('Fares Alkhaldi',md5("109"),true,'1988-2-8','male',3000,'Nablus','fares_88@gmail.com',0597605312);

insert into customer (phn, pass, cname, job,gender,address,date_of_birth,license_type) values
(2000, md5("1001"),"mohammad nasser","Accountant","male","Om Alsharaiet",'1996-10-10',"automatic"),
(1971, md5("1003"),"batata","Engineer","female","Nelen",'1999-11-12',"manual"),
(0015, md5("1005"),"fof","Computer Engineer","male","Ramallah",'2000-3-21',"automatic");

insert into booking values
(false, 1,'2021-4-19','2021-4-27',1,1),
(true,2,'2021-3-2','2021-3-31',0,2),
(true,3,'2021-3-6','2021-3-15',0,3);

insert into resulting_bill values
(2,'2021-4-1',0,250,5800),
(3,'2021-3-15',70,0,1350);

insert into customer2car values
(2,'SFJDA4551003100','2021-3-2'),
(1,'DKDFA1200211231','2021-4-19'),
(3,'VVWDFA446100311','2021-3-6');



insert into accountant values
(3),(4);

insert into manager values
(1);

insert into driver values
(2,'2024-7-16',true);

insert into mechanic values
(5);

insert into cust2driver values
(1,2,'2021-4-19');

insert into driver2car values
(2,'DKDFA1200211231');

insert into report (report_id,rdate,need_of_service,mechanic_name) values
(152,'2021-4-15',true,'Fares Alkhaldi');

insert into car2rep2mech values
('HYNDI0064513399',152,5);

insert into service values
('engine fix',600,8500,'head gasket',152); 
            
insert into mech2service values
(600,5);
select * from customer;

select c.model from car c
where c.vin not in(
	select  c.vin from car c, customer2car c2c, customer cs, booking b
    where c.vin = c2c.car_vin and
		  c2c.cust_id = cs.cid and
          cs.cid = b.makes_cid and
          (( b.date_of_pickup >= '2021-4-17' and b.date_of_pickup <= '2021-4-25') or
          (b.date_of_dropoff >= '2021-4-17' and b.date_of_dropoff <= '2021-4-25') or
          (b.date_of_pickup <= '2021-4-17' and b.date_of_dropoff >= '2021-4-25'))
          );
          
          
          select c.vin, c.manufacturer_name, c.model, c.year_of_manufacture, c.Rental_price_per_day, c.fuel_efficiency, c.security_deposit, c.transmission_system, c.size, c.number_of_occupants, c.color 
				from car c where c.vin not in(select c.vin from car c, customer2car c2c, customer cs, booking b where c.vin = c2c.car_vin and c2c.cust_id = cs.cid and cs.cid = b.makes_cid and (( b.date_of_pickup >= 
				 "2021-4-21"   and b.date_of_pickup <=   "2021-4-27"  ) or (b.date_of_dropoff >=  "2021-4-21"
				 and b.date_of_dropoff <=   "2021-4-27"  ) or (b.date_of_pickup <=   "2021-4-21"
				  and b.date_of_dropoff >=   "2021-4-27"  ) ) );

select d.jid from driver d
where d.jid not in(
select d.jid from driver d, customer cs, booking b, cust2driver c2d
where d.jid = c2d.eid and
	  c2d.cid = cs.cid and
      cs.cid = b.makes_cid and
      (( b.date_of_pickup >= 
				 "2021-4-28"   and b.date_of_pickup <=   "2021-4-29"  ) or (b.date_of_dropoff >=  "2021-4-28"
				 and b.date_of_dropoff <=   "2021-4-29"  ) or (b.date_of_pickup <=   "2021-4-28"
				  and b.date_of_dropoff >=   "2021-4-29"  )) 
      );
      
select e.ename from employees e where e.jid = 2;
select * from employees;
select e.ename,e.gender,e.total_salary, e.still_employed, e.address, e.email, e.phone_num from employees e, manager m where e.jid = m.jid;

select b.bid from booking b ,customer c where b.makes_cid = c.cid and b.paid = 0;
select b.date_of_pickup from booking b where b.bid = 1;
select b.date_of_dropoff from booking b where b.bid = 1;
select b.need_driver from booking b where b.bid = 1;
select c.Rental_price_per_day from car c, customer cs, booking b, customer2car c2c where b.bid = 1 and b.makes_cid = cs.cid and cs.cid = c2c.cust_id and c2c.car_vin = c.vin;
update booking set booking.paid = 1 where booking.paid  = 0 and booking.makes_cid  = 1;
insert into resulting_bill(book_id, bdate, foul_of_late_car, total_to_pay)values();
