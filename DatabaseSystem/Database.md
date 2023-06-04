# 1 Intro

https://utah.zoom.us/j/95463678310
Meeting ID: 954 6367 8310
Passcode: 657611

## Course Intro

1. Assignments
   - Midterms (15%): 
   - Assignments (35%): 
   - Project (50%): implement a Canvas-like system
2. TA hours
   - Anusri Gunji
     - Email: [u1419627@utah.edu](mailto:u1419627@utah.edu)
     - Office/help hours: 
       - Wednesday 12:00 PM - 4:00 PM
       - Thursday 12:00 PM - 2:30 PM
   - Nischal Vangipuram
     - Email: [u1417975@utah.edu](mailto:u1417975@utah.edu) 
     - Office/help hours: 
       - Tuesday    12:00 PM - 3:00 PM
       - Wednesday 12:00 PM - 2:00 PM
       - Thursday   12:00 PM - 1:00 PM

## Database Intro

1. Database sytem contains two major components:
   - Database Management System (DBMS): underlying machinery
   - Query Language: common interface
2. Relational Database: store structured data

# 2 Relational Database

## Terms

1. Schema: a schema is a set of attributes, which specifies the structure and rules of a table
2. Attribute: a name and a type (column heading)
3. Instance: the values in a table, which are a set of tuples
4. Tuple: one row
5. Relation: a schema + instance
6. Key: a key uniquely identify each tuple
   - :star: a set of fields is a key if:
     - it's a superkey
     - None of its proper subset is a superkey
7. Superkey: a set of fields is a superkey if no two rows have the same values in those fields
8. Primary Key & Candidate Key: If there are more than one keys in a schema, DBA should specify one key as a primary key, then the other keys are candidate keys.

9. **Referential Integrity**: Reference between values should always be valid. (When you delete a record in a table, then the referential to this record are all invalid ) 

   > In the context of [relational databases](https://en.wikipedia.org/wiki/Relational_database), it requires that if a value of one attribute (column) of a [relation](https://en.wikipedia.org/wiki/Relation_(database)) (table) references a value of another attribute (either in the same or a different relation), then the referenced value must exist. (from Wikipedia)

## Using CADE MySQL

1. connect Utah vpn if you are not using UConnect
2. login to CADE: `ssh u1428723@lab1-17.eng.utah.edu`
3. run mysql: `mysql -h cs-db -u u1428723 -p`; enter the password

# 3 Entity-Relationship (ER) Model

1. Entity & Relationship
   - Entity: entity is a definable thing or concept within a system, like a person, object (e.g. invoice), concept (e.g. Profile) or event (e.g. transactions).

Q: what is cardinallity in relationship in ER Model?

Cardinality refers to **the maximum number of times an instance in one entity can relate to instances of another entity**.

bold line / a double line indicates all entities in the set must participate in the relationship

# 4 SQL Tables

1. **Weak Entity**: In a relational database, a weak entity is an entity that cannot be uniquely identified by its own attributes alone. Therefore, it must use foreign keys in conjuction with its attributes to create a primary key. The foreign key is typically a primary key of the entity it is related to. 
2. Q: what is partial key?

How to set default value of a field?

alter table?

Data types

- number:

  - TINYINT: 1
  - SMALLINT: 2
  - MEDIUMINT: 3
  - INT: 4
  - BIGINT: 8
  - UNSIGNED

- string:
  - VARCHAR (N): 
    - pick N: n bytes + 1 / 2 bytes for size
      - ASCII: size = 1

      - LATIN: size = 2

- blob (???)

install mysql on MAC

## Foreign Keys

```sql
FOREIGN KEY (<column>) REFERENCES <table>(<table’s key>)
ON DELETE <action>
ON UPDATE <action>
```

`<action>` can be:

- RESTRICT(default): disallow the change
- CASCADE: also delete/update in child table
- SET NULL
- SET DEFAULT

for example:

```sql
FORREIGN KEY (ISBN) REFERECENS Titles(ISBN)
ON UPDATE CASCADE
```

In this case, if an ISBN also appears in the child table, the record won't be allowed to be deleted.

## Algorithm

TODO

# 5 Intro to SQL

## ER to Schema

1. It's not mecessary to add an ID to a weak entity. Say, for classes and courses, a class doesn't mean anything without a related course.
2. IS-A to Schema: base type & derived types
   - If the base type is abstract: only create schema for derived types
   - Otherwise, create schemas for both types

## SQL

1. DBMS vs. Query Language
   - SQL: language
     - SQK=L implementations: MySQL, PostgreSQL, SQL Server, ...
   - DBMS (Database Management System): implementation
2. SQL is a combination of DDL (Data Definition Language) and DML (Data Manipulation Language)

### DDL

1. Create Tables

   ```mysql
   create table <name> (
     <column1Name> <type> <properties>,
     <column2Name> <type> <properties>,
     ...
     <table properties>
   );
   ```

   - `SHOW CREATE TABLE <table_name>`
     - `CHARSET=utf8mb4`: ??? one character is 4 bytes

2. Drop Tables

3. Alter Tables

   ```mysql
   ALTER TABLE titles
   ADD COLUMN pubDate date
   DROP COLUMN pubDate;
   ```

4. Column constraints

   - NOT NULL | UNIQUE | PRIMARY KEY | AUTO_INCREMENT
   - CHECK (expression): ???
   - NULL: ???
   - REFERENCES regtable (ref column)
   - ON DELETE action | ON UPDATE action
     - actions:
       - RESTRICT (default)
       - CASCADE
       - SET NULL
       - SET DEFAULT: set to column's default value

5. table properties:
   - UNIQUE (col1, col2, ...)
   - PRIMARY KEY (col)
   - FOREIGN KEY :TODO 完整整个property
6. Give constraint a name: `CONSTRAINT <constraint_name> <constraint>`

### DML

#### SELECT

```mysql
SELECT [DISTINCT] target-list
FROM relation-list
[WHERE qualification]
[ORDER BY column] [DESC]
[LIMIT number]
```

##### JOIN

1. natural join: join two tables on the columns (must have the same name) they have in common
2. Inner join
3. Outer join
4. left join
5. right join

Q: use `where` or `on` on join. I think use where is not efficient. Why nabil prefer to use where?

TODO: How to improve SQL query efficiency? 

#### INSERT

1. Insert into every column in order, you don't need to specify column names:

   ```mysql
   INSERT INTO Titles 
   VALUES (“978-0441172719”, “Dune”, “Herbert”),
   			 (....);
   ```

2. Insert into specified columns: (use this only when there are columns can be `NULL`/`DEFAULT`/`AUTO_INCREMENT`)

   ```mysql
   INSERT INTO Titles (ISBN, title, author)
   VALUES (“978-0441172719”, “Dune”, “Herbert”);
   ```

#### DELETE

```mysql
DELETE FROM Titles
WHERE author = "Herbert"
```

# 6 Relational Algebra

