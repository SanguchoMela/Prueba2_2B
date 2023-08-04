# Prueba2_2B

## Script MySQL
### Base de datos
create database registropersonas;

use registropersonas;

### Tabla
create table personas(

codigo_pers varchar(10) not null,

cedula_pers varchar(13) not null,

nombre_pers varchar(50),

fechaNac_pers varchar(15),

signoZod_pers varchar(15));

### Ingresar valores en tabla
insert into personas

values

('202121385','1753650389','Melany Sangucho','02-03-2001','Aries'),

('202358453','1732518652','Dayana Sangucho','30-03-1999','Piscis'),

('202157684','1753084219','Juan Perez','05-06-1988','Tauro'),

('215484351','0984651358','Maria Zapata','03-08-1969','Acuario');

### Ver los registros
select * from personas;
