 create table if not exists Taco_Order (
  id identity primary key,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null,
  user_id bigint
 );
 create table if not exists Taco (
  id identity primary key,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
 );
 create table if not exists Ingredient (
  id varchar(4) not null primary key,
  name varchar(25) not null,
  type varchar(10) not null
 );
 CREATE TABLE taco_ingredient ( 
    taco_id BIGINT, 
    ingredient_id varchar(4), 
    PRIMARY KEY (taco_id, ingredient_id), 
    FOREIGN KEY (taco_id) REFERENCES taco(id), 
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
 );
 create table account_user {};

alter table Taco add foreign key (taco_order) references Taco_Order(id);
alter table Taco_Order add foreign key (user_id) references Account_User(id);
