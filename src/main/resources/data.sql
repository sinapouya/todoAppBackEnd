insert into todo(id,description,is_done,target_date,user_name) 
values(10001,'learn react',false,sysdate(),'sina');
insert into todo(id,description,is_done,target_date,user_name)
	values(10002,'learn JPA',false,sysdate(),'sina');
insert into todo(id,description,is_done,target_date,user_name)
	values(10003,'learn Spring boot',false,sysdate(),'sina');
insert into todo(id,description,is_done,target_date,user_name)
	values(10004,'learn react',false,sysdate(),'user');
insert into USERS(id,email,name,password,username)
	values(2001,'sina@gmail.com','sina','$2a$10$5TPQplg97v740OEDx/9ZJ.E5i.pfrydvpkK9N1k/sFltGKC.Hxao6','sina');
insert into USERS(id,email,name,password,username)
	values(2002,'user@gmail.com','user','$2a$10$l.WAkZU6utRTiJOP43IZ9Ogv3bYtVeSZoE70kg/wZbrc.y1.u9NY6','user');
insert into ROLES(id,rolename)
	values(3001,'ROLE_USER');
insert into ROLES(id,rolename)
	values(3002,'ROLE_ADMIN');	
insert into USER_ROLES(user_id,role_id)
	values(2001,3002);
insert into USER_ROLES(user_id,role_id)
	values(2002,3001);
		