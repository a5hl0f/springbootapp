insert into users(id,version,created,updated,username,password,first_name,last_name,mail,active)
values(1,1,current_date,current_date,'admin','$2y$10$1l7OeYRw45A1HGqmWiW8/eK37cSIa2hAdfGC8Qji3vF0J.Ue3.uz2','Adminstrator','God','god@heaven.hv', true);

insert into country(id,version,created,updated, name, sign)
values(2,1,current_date,current_date,'Hungary','H');

insert into country(id,version,created,updated, name, sign)
values(3,1,current_date,current_date,'Ukraine','U');

insert into country(id,version,created,updated, name, sign)
values(4,1,current_date,current_date,'USA','US');

insert into country(id,version,created,updated, name, sign)
values(5,1,current_date,current_date,'United Kingdom','UK');

insert into country(id,version,created,updated, name, sign)
values(6,1,current_date,current_date,'Earth','Eth');

insert into country(id,version,created,updated, name, sign)
values(7,1,current_date,current_date,'Betelgeuse','BET');

insert into country(id,version,created,updated, name, sign)
values(8,1,current_date,current_date,'Magrathea','MAG');

insert into country(id,version,created,updated, name, sign)
values(9,1,current_date,current_date,'Vogaria','VOG');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(2,1,current_date,current_date,'Zaphod Beeblebrox', 'B9999', 'Betelgeuse City', 'Main street', '42',
       select id from country where sign='BET', '4242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(3,1,current_date,current_date, 'Ford Prefect', 'B9999', 'Betelgeuse City', 'Main street', '43',
       select id from country where sign='BET', '5242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(4,1,current_date,current_date, 'Tricia McMillan', '1234', 'London', 'Downing street', '10',
       select id from country where sign='UK', '6242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(5,1,current_date,current_date, 'Arthur Dent','1234','Cottington','Country Lane','155',
       select id from country where sign='UK', '7242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(6,1,current_date,current_date, 'Marvin the Robot', 'B1111', 'Heart of Gold spaceship', 'Main deck', '1',
       select id from country where sign='BET', '8242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(7,1,current_date,current_date, 'Slartibartfast', 'M2121', 'Magrathea city', 'Service lane', '1',
       select id from country where sign='MAG', '9242424242424');

insert into person(id,version,created,updated, name, zip, city, street, number, country, id_number)
values(8,1,current_date,current_date, 'Prostetic Vogon Jeltz', 'V111', 'Vogaria city', 'First corner', '1',
       select id from country where sign='VOG', '0242424242424');
