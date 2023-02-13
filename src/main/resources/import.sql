-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

insert into DocumentType (idDocumentType, documentTypeName) values(nextval('hibernate_sequence'), 'DNI');
insert into DocumentType (idDocumentType, documentTypeName) values(nextval('hibernate_sequence'), 'Pasaporte');
insert into DocumentType (idDocumentType, documentTypeName) values(nextval('hibernate_sequence'), 'Carnet de Extranjería');
insert into DocumentType (idDocumentType, documentTypeName) values(nextval('hibernate_sequence'), 'RUC');