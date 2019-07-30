create table if not exists Taco
(
  id         identity,
  name       varchar(50) not null,
  created_At timestamp   not null
);

create table if not exists Ingredient
(
  kind varchar not null,
  id   varchar not null,
  name varchar not null
);


create table if not exists Taco_Ingredients
(
  taco       bigint     not null,
  ingredient varchar(4) not null
);

alter table Taco_Ingredients
  add foreign key (taco) references Taco (id);
alter table Taco_Ingredients
  add foreign key (ingredient) references Ingredient (id);

create table if not exists Taco_Orders
(
  id              identity,
  delivery_Name   varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City   varchar(50) not null,
  delivery_State  varchar(2)  not null,
  delivery_Zip    varchar(10) not null,
  cc_Number       varchar(16) not null,
  cc_Expiration   varchar(5)  not null,
  cc_CVV          varchar(3)  not null,
  placed_At       timestamp   not null
);

create table if not exists Taco_Order_Tacos
(
  taco_Order bigint not null,
  taco       bigint not null
);

alter table Taco_Order_Tacos
  add foreign key (taco_Order) references Taco_Orders (id);
alter table Taco_Order_Tacos
  add foreign key (taco) references Taco (id);