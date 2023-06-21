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
   - each row is a tuple and must be unique
3. Basic Design goals:
   - Don't store lists /arrays
   - Build compound information by referencing other tables
   - Enable powerful reasoning about data and relationships, cleaner design
   - Enable DBMS to optimize

# 2 Relational Database

## Terms

1. **Schema**: a schema is a set of attributes (name + type), which specifies the structure and rules of a table
   - schema doesn't specify any values!
2. **Attribute**: a name and a type (column heading)
3. **Instance**: the values in a table, which are a set of tuples
4. **Tuple**: one row
5. **Relation (a.k.a. table)**: a schema + instance

## Keys

Keys uniquely identify each tuple.

1. **Superkey**: a set of fields is a superkey if no two rows have the same values in those fields
2. **Key**: a key is a **minimal** set of fields to uniquely identify rows. A set of fields is a key if:
   - it's a superkey
   - None of its **proper subset** is a superkey
     - Proper subset: A proper subset of a set A is a subset of A that is not equal to A.
3. superkey vs. key:
   - a key must be a superkey
   - a superkey may not be a key
4. **Primary Key & Candidate Key:** If there are more than one keys in a schema, DBA should specify one key as a primary key, then the other keys are candidate keys.
   - specify primary key in SQL:`PRIMARY KEY (id)`
   - specify candidate keys in SQL: `UNIQUE (name)`
5. **Referential Integrity**: Reference between values should always be valid. (When you delete a record in a table, then the referential to this record are all invalid ) 
6. **Foreign Key**: a Primary Key in another table
   - `FOREIGN KEY (sid) REFERENCES students(id) ON UPDATE CASCADE ON DELETE CASCADE`
   - If the referenced key is deleted, there are three options to do in the referencing table
     - delete corresponding records
     - Nullify the foreign key (bad design!)
     - disallow the change to the referenced table

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

Relational Algebra: algebra that operate on relations 

1. π: projection. No duplicates. Examples: 

   - $π_{Title, Autor} (Titles)$
   - $π_{Title, Serial} (Titles × Inventory)$

2. σ: selection

   - $σ_{CardNum > 3}(Patrons)$
   - $π_{Phone}(σ_{CardNum > 3}(Patrons × Phones))$

3. ×: Cartesian product (A x B = the set of all ordered pairs (a,b) where a in A and b in B)

   - R1 × R2 == R2 × R1

4. ∪: set union

   - relations must be **union compatible** (same column types in same order)
   - duplicates will be removed
   - R1 ∪ R2 == R2 ∪ R1

5. : set difference

6. ∩: set intersection

   - R1 ∩ R2 = R1  (R1  R2)

7. ρ: rename operation (unary operator - single input)

   - syntax: $ρ_x(E)$

8. /: devision

   > Division operator A**÷**B or A/B can be applied if and only if:
   >
   > - Attributes of B is proper subset of Attributes of A.
   > - The relation returned by division operator will have attributes = (All attributes of A – All Attributes of B)
   > - The relation returned by division operator will return those tuples from relation A which are associated to every B’s tuple.

# 9 Advanced Queries II: EXISTS

## JOIN

1. Self-join

   ```sql
   -- find all students share the same phonenumbers --
   SELECT *
   FROM Phones a
   INNER JOIN Phones b
   	ON a.Phone = b.Phone AND a.CardNum != b.CardNum;
   ```

2. Join

   - `INNER JOIN`: We can use either `JOIN ... ON...` or `JOIN ... WHERE ...`

     - `JOIN`, `INNER JOIN`, `,` are all inner join (but for `,` we can only use `JOIN ... WHERE ...`)

       ```sql
       SELECT * FROM 
       Phones p1, Phones p2
       WHERE p1.CardNum != p2.CardNum AND p1.Phone = p2.Phone;
       ```

   - OUTER JOIN: `LEFT/RIGHT [OUTER] JOIN` -- must use `ON` clause!!

     - Shortcut: if the column names are the same, you can use `NATURAL LEFT/RIGHT JOIN`, so you don't need to use `ON` clause

   ![img](./assets/FYwNJ.png)

## NULL

1. `NULL` doesn't have **reflexive property** (a number always equals to itself), which means `NULL != NULL`

2. Different from programming languages, SQL supports 3 logic values: `TRUE`, `FALSE`, `NULL`. And `NULL` means unknown.  

   - Boolean Operators on `NULL` always return NULL!!!

     ```sql
     5 = NULL // NULL
     NULL = NULL // NULL
     ```

3. Use `IS [NOT]` for `NULL`:

     - `WHERE CardNum != NULL` // wrong

       - `SELECT * FROM Patrons NATURAL LEFT JOIN CheckedOut WHERE Serial != NULL;` will return an empty set
       
     - `WHERE CardNum IS NOT NULL` // right

       - ```sql
         mysql > SELECT * FROM Patrons NATURAL LEFT JOIN CheckedOut WHERE Serial IS NOT NULL;
         +---------+------+--------+
         | CardNum | Name | Serial |
         +---------+------+--------+
         |       1 | Joe  |   1001 |
         |       1 | Joe  |   1004 |
         |       4 | Dan  |   1005 |
         |       4 | Dan  |   1006 |
         +---------+------+--------+
         4 rows in set (0.10 sec)
         ```


## Nested Query as Condition

1.  `ANY`/`ALL`: compare between a single column value with a range of other values

   - `ANY`: returns true if the operation is true for **any** of the values in the range. For exampe, find any students who have ever get an "A"

     ```sql
     SELECT Name
     FROM Students
     WHERE sID = ANY (
     	SELECT sID
       FROM Enroll
       WHERE grade = 'A'
     );
     ```
     
   - `ALL`: returns true if the operation is true for **all** of the values in the range. For example, find students who are younger than all the students enrolled in "Databases"
   
     ```sql
     SELECT s.Name, s.DOB
     FROM Students s
     WHERE s.DOB > ALL (
     	SELECT DOB
       FROM Enroll e
       NATURAL JOIN Courses c
       NATURAL JOIN Students
       WHERE c.Name = 'Databases'
     );
     ```


2. 
     EXISTS: 
     - syntax: 
       
       - `EXISTS`: returns TRUE if the subquery returns one or more records
       
       - `NOT EXISTS`: select TRUE if the subquery returns no record
       
         ```sql
         select x from y where
         [NOT] EXISTS
         (select ...);
         ```
       
       - Note, the `EXISTS` clause in SQL operates on a **row-by-row** basis. It evaluates the subquery for each row in the outer query individually.
       
     - Examples:
       
       - select students who are enrolled in Databases courses:
       
         ```sql
         SELECT sid, name, grade
         FROM enroll e
         NATURAL JOIN students s
         WHERE EXISTS (
         	SELECT sid
           FROM courses c
           WHERE c.name = 'Databases' AND c.cid = e.cid
         )
         ```
       
         result:
       
         ```sql
         +-----+----------+-------+
         | sid | name     | grade |
         +-----+----------+-------+
         |   1 | Hermione | A     |
         |   2 | Harry    | B     |
         +-----+----------+-------+
         ```
       
         Note, you must use `c.cid = e.cid` to **correlate** the inner query to the outer query. (we call this **correlated subquery**)
       
       - In comparison, the following query will return every sid because the inner query returns TRUE to every row in outer query.
       
         ```sql
         SELECT sid, name
         FROM students
         WHERE EXISTS (
         	SELECT sid
           FROM enroll
           NATURAL JOIN courses c
           WHERE c.name = 'Databases'
         );
         ```
       
       - SELECT students id, name, course name, and grade who are not enrolled in "Databases"
       
         ```sql
         SELECT e.sid, s.name, cc.name, e.grade
         FROM enroll e
         NATURAL JOIN courses cc
         JOIN students s
         	ON e.sid = s.sid
         WHERE NOT EXISTS (
         	SELECT s.sid
           FROM courses c
           WHERE c.name = 'Databases' AND c.cid = e.cid
         )
         ```

## Practice

1. CREATE tables and insert data:

   ```sql
   CREATE TABLE students (
     sid INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
     name VARCHAR(100) NOT NULL,
     DOB SMALLINT NOT NULL
   );
   
   CREATE TABLE courses (
     cid INT UNSIGNED NOT NULL PRIMARY KEY,
     name VARCHAR(100) NOT NULL
   );
   
   CREATE TABLE enroll (
     sid INT NOT NULL,
     cid INT UNSIGNED NOT NULL,
     grade ENUM('A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D', 'D-', 'E', 'X', 'WF', 'EW', 'EU', 'F'),
     PRIMARY KEY (sid, cid),
     CONSTRAINT FK_student_id FOREIGN KEY (sid) REFERENCES students(sid),
     CONSTRAINT FK_class_id FOREIGN KEY (cid) REFERENCES courses(cid)
   );
   
   INSERT INTO students (Name, DOB) VALUES ('Hermione', 1980), ('Harry', 1979), ('Ron', 1980), ('Malfoy', 1982); 
   
   INSERT INTO courses (cID, name) VALUES 
   (3500, 'SW Practice'),
   (3810, 'Architecture'),
   (5530, 'Databases');
   
   INSERT INTO enroll (sID, cID, grade) VALUES 
   (1, 3500, 'A'),
   (1, 3810, 'A-'),
   (1, 5530, 'A'),
   (2, 3810, 'A'),
   (2, 5530, 'B'),
   (3, 3500, 'C'),
   (3, 3810, 'B'),
   (4, 3500, 'C');
   ```

2. Find Names of Patrons who have not checked out a book. Use outer join. [Exclusive left join!]

   ```sql
   SELECT Name
   FROM Patrons
   NATURAL LEFT JOIN CheckedOut
   WHERE Serial IS NULL;
   ```


3. Find students who are not enrolled in databases

   ```sql
   SELECT sID, Name
   FROM Students
   WHERE sID NOT IN (
   	SELECT sID
     FROM Enrolled
     NATURAL INNER JOIN Courses
     WHERE Name="Databases"
   );
   ```

3. Find all students younger than *everyone* taking Databases

   ```sql
   SELECT name, DOB
   FROM students
   WHERE DOB > ALL (
   	SELECT DOB
     FROM students
     NATURAL INNER JOIN enroll e
     INNER JOIN courses c
     	ON e.cid = c.cid
     WHERE c.name = 'Databases'
   );
   ```

3. Find students who take every courses:

   ```sql
   SELECT s.name
   FROM students s
   WHERE NOT EXISTS (
   	SELECT c.cid
   	FROM courses c
   	WHERE NOT EXISTS (
   		SELECT e.cID
   		FROM enroll e
   		WHERE e.cID = c.cID AND e.sID = s.sID
   	)
   );
   
   ```

3. Find students who doesn't take every course:

   ```sql
   SELECT s.name
   FROM students s
   WHERE EXISTS (
   	SELECT c.cid
   	FROM courses c
   	WHERE NOT EXISTS (
   		SELECT e.cID
   		FROM enroll e
   		WHERE e.cID = c.cID AND e.sID = s.sID
   	)
   );
   
   ```

3. Find students who take all the courses that the student with a sid 2 takes

   ```sql
   SELECT s.sid, s.name
   FROM students s 
   WHERE s.sid != 2 AND NOT EXISTS (
   	SELECT e1.cid
     FROM enroll e1
     WHERE e1.sid = 2 AND NOT EXISTS (
     	SELECT e2.cid
       FROM enroll e2
       WHERE e2.sid = s.sid AND e2.cid = e1.cid
     )
   );
   ```

8. Find course numbers of each student. This course number should be bigger than the the average course number of that student are enrolled.

   ```sql
   SELECT e1.sid, e1.cid
   FROM enroll e1
   WHERE e1.cid > (
   	SELECT AVG(e2.cid)
     FROM enroll e2
     WHERE e1.sid = e2.sid
   );
   ```

# 10 Advanced Queries III

1. regrex

   - `%`: 0 or more arbitrary chars
     - `LIKE '%star'`: ends with star
     - `LIKE  star%`: starts with star
     - `LIKE %star%`: contains star
   - `_`: any 1 char

2. aggregate functions + GROUP BY

   - COUNT() -- no spaces between the function name and the parenthese!

     ```sql
     -- COUNT the number of courses each student enroll
     SELECT name, COUNT(cid) AS num_courses
     FROM students
     NATURAL LEFT JOIN enroll
     GROUP BY name;
     ```

     Note, don't use `COUNT(*)`, of if a student doesn't enroll any course, their num_courses will be 1!

   - MAX

   - MIN

   - AVG

   - SUM

3. WHERE vs. HAVING

   - `WHERE`: used before `GROUP BY` to filter rows

   - `HAVING`: used after `GROUP BY` to filter groups

   - e.g., Find students with 2 or more courses earning an A

     ```sql
     SELECT sid, COUNT(*) as num_courses
     FROM enroll
     WHERE grade = 'A'
     GROUP BY sid
     HAVING num_courses >= 2;
     ```

4. CASE

   ```sql
   SELECT
     CASE
       WHEN grade = 'A' THEN 'Superior'
       WHEN grade = 'B' THEN 'Good'
       WHEN grade = 'C' THEN 'Adequate'
       ELSE 'Poor'
     END AS remarks
   FROM enroll;
   ```

5. dynamic delete

   - use IN and WHERE:

     ```sql
     -- delte all students who once got an A
     DELETE FROM students
     WHERE sid IN (
     	SELECT sid
       FROM enroll WHERE grade = 'A'
     )
     ```

   - use JOIN

     ```sql
     -- delete all enroll records of "Databases"
     DELETE t1
     FROM enroll t1
     JOIN courses t2
     	ON t1.cid = t2.cid
     WHERE t2.name = "Databases";
     ```

6. dynamic insert:

   - `INSERT IGNORE`: turn errors into warning

## Practice

1. Find number of students in each course

   ```sql
   SELECT cid, name, COUNT(cid) AS num_students
   FROM enroll
   NATURAL LEFT JOIN courses
   GROUP BY cid;
   ```

2. Find number of courses for each student

   ```sql
   SELECT sid, name, COUNT(sid) AS num_courses
   FROM enroll
   NATURAL LEFT JOIN students
   GROUP BY sid;
   ```

3. Find the Name of the cheapest item from each Dept

   ```sql
   SELECT a.name, price, a.dept
   FROM items a
   INNER JOIN (
   	SELECT dept, MIN(price) AS min_price
     FROM items
     GROUP BY dept
   ) b
   ON a.dept = b.dept AND a.price = b.min_price;
   ```

4. **Find the name of the oldest student in each course**

   ```sql
   SELECT e.cid, e.sid, name, s.DOB
   FROM enroll e
   NATURAL JOIN students s
   INNER JOIN (
     SELECT cid, MAX(DOB) AS max_dob
     FROM enroll
     NATURAL JOIN students
     GROUP BY cid
   ) eldest
   ON e.cid = eldest.cid AND s.DOB = max_dob;
   ```

5. Find the average number of students per major

   ```sql
   SELECT AVG(num_students)
   FROM (
     SELECT major, COUNT(*) AS num_students
     FROM students
     GROUP By major
   ) a
   ```

   or:

   ```sql
   SELECT COUNT(*) / COUNT(DISTINCT major)
   FROM students;
   ```

6. Find age in years of all students

   ```sql
   SELECT name, TIMESTAMDIFFERENCE(YEAR, MAKEDATE(DOB, 1), CURDATE()) AS age
   FROM students;
   ```

7. Write a query to checkout “The Lorax” for “Joe”

   ```sql
   INSERT INTO CheckedOut (CardNum, Serial) 
   VALUES (
     SELECT CardNum FROM Patrons WHERE Name = "Joe",
     SELECT Serial FROM Inventory 
     NATURAL JOIN Titles
     WHERE Title = "The Lorax" AND Serial NOT IN (
     	SELECT Serial FROm CheckedOut
     )
     LIMIT 1
   )
   ```

   
