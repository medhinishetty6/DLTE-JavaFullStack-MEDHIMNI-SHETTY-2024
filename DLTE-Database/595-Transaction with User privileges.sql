Create atleast 5 users and grant following privileges

user1 - select

user2 - delete

user3 - select

user4 - insert

user5 - update




//user 1
create user medhini IDENTIFIED by medh;
grant select on transaction to medhini;
connect medhini/medh;
select * from transaction; 

//user 2
create user mothi IDENTIFIED by Mothi;
grant select on transaction to mothi;
connect mothi/Mothi;
delete from trsansaction transaction_remarks='education'; 

//user-3
create user meghana IDENTIFIED by megha;
grant select on transaction to meghana;
connect meghana/megha;
select * from transaction; 

//user-4
create user Shali IDENTIFIED by shali;
grant select on transaction to Shali;
connect Shali/shali;
insert into transaction values(3,'20-FEB-2024','Babu',100000,'emergency');

//user-4
create user prajothi IDENTIFIED by Prathi;
grant select on transaction to prajothi;
connect prajothi/Prathi;
update transaction set transaction_to='mothi' where transaction_id=1;

