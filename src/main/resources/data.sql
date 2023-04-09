
insert into address(id, address_number, city, complement, state) values(1,'99','Nova Hartz','Rua do Restaurante becker','RS');

insert into role(id, name) values (3,'ADMIN'), (4,'USER');

insert into user_table(id, name, password, username, address_id) values (2, 'Tim Morgenstern Beck', '$2a$10$BxJ9e/Hm7GFhTv5MgIuHNeyenrljcKB38T2RhmZCEfHyThIImNdXK', 'timbeck97', 1);

insert into user_table_roles(user_id, roles_id) values (2,3), (2,4);

insert into product(id,description, category, enabled) values(1,'TV','ELETRODOMESTIC', true),
(2,'FOGAO','ELETRODOMESTIC', true),
(3,'GELADEIRA','ELETRODOMESTIC', true),
(4,'TORRADEIRA','ELETRODOMESTIC', true),
(5,'SOM','ELETRODOMESTIC', true),
(6,'HOME THEATER','ELETRODOMESTIC', true),
(7,'BATEDEIRA','ELETRODOMESTIC', true),
(8,'LIQUIDIFICADOR','ELETRODOMESTIC', true),
(9,'FORNO','ELETRODOMESTIC', true),
(10,'SOFA','FURNITURE', true),
(11,'CAMA','FURNITURE', true),
(12,'ARMARIO','FURNITURE', true),
(13,'GUARDA ROUPA','FURNITURE', true);



