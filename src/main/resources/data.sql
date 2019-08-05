delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Orders;
delete from Ingredient;


insert into Ingredient (id, name, kind)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, kind)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, kind)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, kind)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, kind)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, kind)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, kind)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, kind)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, kind)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, kind)
values ('SRCR', 'Sour Cream', 'SAUCE');