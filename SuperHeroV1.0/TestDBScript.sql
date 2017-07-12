drop database if exists SuperHeroTestDB;

create database SuperHeroTestDB;

use SuperHeroTestDB;

create table MetaHuman(
MetaHumanID int not null auto_increment,
MetaHumanName varchar(30),
MetaHumanSecretName varchar(30),
primary key(MetaHumanID)
);

create table Powers(
PowerID int not null auto_increment,
PowerDescription varchar(50),
primary key(PowerID)
);

create table MetaHuman_Powers(
MetaHuman_PowersID int not null auto_increment,
MetaHumanID int,
PowerID int,
primary key(MetaHuman_PowersID),
constraint fk_MetaHumanPowers_MetaHuman foreign key(MetaHumanID) references MetaHuman(MetaHumanID),
constraint fk_MetaHumanPowers_Powers foreign key(PowerID) references Powers(PowerID)
);


create table Location(
LocationID int not null auto_increment,
Longitude decimal,
Latitude decimal,
LocationName varchar(30),
LocationDescription varchar(30),
Address varchar(50),
primary key(LocationID)
);

create table Sighting(
SightingID int not null auto_increment,
SightingDate date,
SightingName varchar(30),
LocationID int,
primary key(SightingID)
);

alter table Sighting add constraint foreign key(LocationID) references Location(LocationID) on delete no action;

create table Organization(
OrganizationID int not null auto_increment,
OrganizationName varchar(30),
OrganizationDescription varchar(50),
LocationID int,
primary key(OrganizationID)
);

alter table Organization add constraint foreign key(LocationID) references Location(LocationID) on delete no action;



create table MetaHuman_Sighting(
MetaHuman_SightingID int not null auto_increment,
MetahumanID int not null,
SightingID int not null,
primary key(MetaHuman_SightingID),
constraint fk_MetaHumanSighting_MetaHuman foreign key(MetaHumanID) references MetaHuman(MetaHumanID),
constraint fk_MetaHumanSighting_Sighting foreign key(SightingID) references Sighting(SightingID)
);

create table MetaHuman_Organization(
MetaHuman_OrganizationID int not null auto_increment,
MetaHumanID int not null,
OrganizationID int not null,
primary key(MetaHuman_OrganizationID),
constraint fk_MetaHumanOrganization_MetaHuman foreign key(MetaHumanID) references MetaHuman(MetaHumanID),
constraint fk_MetaHumanOrganization_Organization foreign key(OrganizationID) references Organization(OrganizationID)
);


/*
insert into MetaHuman(MetaHumanName, MetaHumanSecretName) values
('Wonder Woman','Gal'),
('Spider Man','Peter Parket'),
('Professor X','Charles Xaviar'),
('Cyclops','Scott Summers'),
('Gambit','Remy Etienne LeBeau');


