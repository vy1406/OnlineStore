drop table librarysettings;
drop table BooksInLoan;
drop table Loans;
drop table Users;
drop table Category;
drop table BookCopy;
drop table Books;
drop table Students;

create table librarysettings(
                    setting INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
                    numofloans integer not null,
                    damagefine integer not null,
                    latefine integer not null,
                    maxfine integer not null);
create table Users(
                    username varchar(20) primary key, 
                    password varchar(20) not null,
                    privilege varchar(20) not null);
create table Category(
                    categoryId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
                    categoryName varchar(20) primary key);
create table Books(
                    isbn varchar(20) primary key, 
                    bookname varchar(50) not null,
                    author varchar(20) not null,
                    Category varchar(20) not null,
                    releaseyear decimal not null,
                    picurl varchar(300));
create table BookCopy(
                    copyid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
                    bookisbn varchar(20) references Books(isbn) on delete cascade on update no action,
                    inloan boolean not null,
                    copycondition decimal not null,
                    canLoan boolean not null,
                    primary key(copyid,bookisbn));
create table Students(
                    studentid varchar(10) primary key, 
                    sname varchar(20) not null,
                    surname varchar(20) not null,
                    email varchar(20) not null,
                    phonenumber varchar(20) not null,
                    gender varchar(20) not null,
                    fine decimal not null);
create table Loans(
                    loanid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
                    studentid varchar(10) references students(studentid) on delete cascade on update no action,
                    startdate date not null,
                    enddate date not null);
create table BooksInLoan(
                    copyid INTEGER not null,
                    bookisbn varchar(20) not null,
                    actualreturndate date,
                    inLoan boolean not null,
                    loanid integer not null references loans(loanid) on delete cascade on update no action,
                    primary key(loanid,bookisbn,copyid),
                    foreign key (copyid,bookisbn) references BookCopy(copyid,bookisbn) on delete cascade on update no action);
	     

insert into librarysettings (numofloans,damagefine,latefine,maxfine) values (5,5,2,50);

insert into Users values('librarian', 'librarian','librarian');
insert into Users values('admin', 'admin','admin');
insert into Users values('111', '111','student');

insert into Category (categoryName) values('Advanture');
insert into Category (categoryName) values('Science');
insert into Category (categoryName) values('Horror');
insert into Category (categoryName) values('Romance');
insert into Category (categoryName) values('Thriller');
insert into Category (categoryName) values('Action');

insert into Books values('0439554934','Harry potter','j k rowling','Advanture',2001,'/Hw4/pics/harry_potter.jpg');
insert into Books values('4578936188','Game of thrones','George Martin','Advanture',1996,'/Hw4/pics/game_of
_thrones.jpg');
insert into Books values('1612130291','Fifty Shades of Grey','E L James','Romance',2014,'/Hw4/pics/fifty_shades_of_grey.jpg');
insert into Books values('1787423614','The Da Vinci Code','Dan Brown','Advanture',2003,'/Hw4/pics/da_vinci_code.jpg');
insert into Books values('9846534568','The Hunger Games','Suzanne Collins','Action',2008,'/Hw4/pics/The_Hunger_Games.jpg');
insert into Books values('1416914293','City of Ashes','Cassandra Clare','Horror',2008,'/Hw4/pics/city_of_ashes.jpg');
insert into Books values('0385732554','The Giver','Lois Lowry','Science',2006,'/Hw4/pics/the_giver.jpg');
insert into Books values('0743227441','The Other Boleyn Girl','Philippa Gregory','Romance',2003,'/Hw4/pics/Other_Boleyn_Girl.jpg');
insert into Books values('1416989412','Hush Hush','Becca Fitzpatrick','Thriller',2009,'/Hw4/pics/Hush_hush.jpg');
insert into Books values('1301949825','Hopeless','Colleen Hoover','Thriller',1953,'/Hw4/pics/hopeless.jpg');

insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('0439554934', true, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('0439554934', true, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('0439554934', true, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('4578936188', true, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('4578936188', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('4578936188', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1612130291', true, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1612130291', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1787423614', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('9846534568', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1416914293', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('0385732554', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('0743227441', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1416989412', false, 1, true);
insert into BOOKCOPY (BOOKISBN, INLOAN, COPYCONDITION, CANLOAN) values ('1301949825', false, 1, true);
	
insert into Students values('122','wasya','perashkov','asd@asd.com','111','male',5);
insert into Students values('133','mishanya','chupakabra','asd@asd.com','111','male',0);
insert into Students values('144','shimshon','agibor','asd@asd.com','111','male',0);
insert into Students values('123','arbuz','sasulkin','asd@asd.com','111','male',0);
insert into Students values('111','natasha','shabun','asd@asd.com','111','female',0);
insert into Students values('222','alex','trevgoda','asd@asd.com','111','male',0);
insert into Students values('333','vova','yelizavetski','asd@asd.com','111','male',0);
insert into Students values('444','stas','zagumenii','asd@asd.com','111','male',0);

INSERT INTO LOANS (STUDENTID, STARTDATE, ENDDATE) VALUES ('111', '2017-01-22', '2017-02-02');
INSERT INTO LOANS (STUDENTID, STARTDATE, ENDDATE) VALUES ('122', '2017-02-22', '2017-02-28');
INSERT INTO LOANS (STUDENTID, STARTDATE, ENDDATE) VALUES ('133', '2017-02-22', '2017-02-28');
INSERT INTO LOANS (STUDENTID, STARTDATE, ENDDATE) VALUES ('111', '2017-02-24', '2017-03-03');

INSERT INTO ROOT.BOOKSINLOAN (COPYID, BOOKISBN, ACTUALRETURNDATE, INLOAN, LOANID) VALUES (1, '0439554934', NULL, true, 1);
INSERT INTO ROOT.BOOKSINLOAN (COPYID, BOOKISBN, ACTUALRETURNDATE, INLOAN, LOANID) VALUES (2, '0439554934', NULL, true, 2);
INSERT INTO ROOT.BOOKSINLOAN (COPYID, BOOKISBN, ACTUALRETURNDATE, INLOAN, LOANID) VALUES (3, '0439554934', NULL, true, 3);
INSERT INTO ROOT.BOOKSINLOAN (COPYID, BOOKISBN, ACTUALRETURNDATE, INLOAN, LOANID) VALUES (7, '1612130291', NULL, true, 4);
INSERT INTO ROOT.BOOKSINLOAN (COPYID, BOOKISBN, ACTUALRETURNDATE, INLOAN, LOANID) VALUES (4, '4578936188', NULL, true, 1);
