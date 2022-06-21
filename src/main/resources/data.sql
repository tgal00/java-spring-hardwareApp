delete from hardware;
insert into hardware(code,name,price,stock,type) values('1','RTX 3070',1999.99,10,'GPU');
insert into hardware(code,name,price,stock,type) values('2','AMD Ryzen 5600x',889.99,33,'CPU');
insert into hardware(code,name,price,stock,type) values('3','Samsung Evo 980',399.99,430,'STORAGE');



delete from review;
insert into review(title, text, rating, hardware_id) values('Review 1','Text of review 1',4,1);
insert into review(title, text, rating, hardware_id) values('Review 2','Text of review 2',2,2);
insert into review(title, text, rating, hardware_id) values('Review 3','Text of review 3',4,3);
insert into review(title, text, rating, hardware_id) values('Review 4','Text of review 4',5,1);



insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin
insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');
insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1);