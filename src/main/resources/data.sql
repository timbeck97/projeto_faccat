
insert into address(id, address_number, city, complement, state) values(1,'99','Nova Hartz','Rua do Restaurante becker','RS');

insert into role(id, name) values (3,'ADMIN'), (4,'USER');

insert into user_table(id, name, password, username, address_id) values (2, 'Tim Morgenstern Beck', '$2a$10$BxJ9e/Hm7GFhTv5MgIuHNeyenrljcKB38T2RhmZCEfHyThIImNdXK', 'timbeck97', 1);

insert into user_table_roles(user_id, roles_id) values (2,3), (2,4);


