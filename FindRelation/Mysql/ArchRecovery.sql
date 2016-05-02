#create database ArchRecovery;
use ArchRecovery;
#---------------------------------------------
create table if not exists RELATIONS(
ID int(10) NOT NULL default '0',
RelationName varchar(35) NOT NULL,
Description varchar(350) NOT NULL,
PRIMARY KEY (ID));
#---------------------------------------------
create table CLASS_RELATIONS(
ID int(10) NOT NULL default '0',
Source varchar(35) NOT NULL,
Target varchar(35) NOT NULL,
Location varchar(350) NOT NULL,
FOREIGN KEY (ID) REFERENCES RELATIONS(ID));
#---------------------------------------------
show tables;
#*********************************************
#Insertamos los diferentes tipos de relaciones
#*********************************************
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (1,'Realization','relationship is a relationship between two model elements, in which one model element (the client) realizes the behavior that the other model element (the supplier) specifies. Several clients can realize the behavior of a single supplier. You can use realization relationships in class diagrams and component diagrams.');
#------
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (2,'Generalization', 'Generalization relationship is a relationship in which one model element (the child) is based on another model element (the parent). Generalization relationships are used in class, component, deployment, and use-case diagrams to indicate that the child receives all of the attributes, operations, and relationships that are defined in the parent.');
#------
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (3,'Aggregation','An aggregation relationship depicts a classifieras a part of,or as subordinate to,another classifier.');
#------
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (4,'Composition','A composition relationship specifies that the lifetime of the part classifier is dependent on the lifetime of the whole.');
#------
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (6,'Association', 'An association is a relationship between two classifiers, such as classes or use cases, that describes the reasons for the relationship and the rules that govern the relationship.');
#------
INSERT INTO RELATIONS (ID, RelationName, Description) 
VALUES (5,'Dependency', 'A dependency relationship indicates that changes to one model element (the supplier or independent model element)can cause changes in another\r\nmodel element(the client or dependent model element).');


