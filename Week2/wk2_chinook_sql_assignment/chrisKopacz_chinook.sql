/*2.1 SELECT*/
/*Task - Select all records from the Employee table*/
--SELECT *
--FROM employee;

/*Task - Select all records from the employee table where last name is King*/
--SELECT *
--FROM employee
--WHERE lastname = 'King';

/*Task - Select all records from the employee table where first name is Andrew and REPORTSTO is null*/
--SELECT *
--FROM employee
--WHERE firstname = 'Andrew'
--AND reportsto IS NULL;
--2.1-----------------------------------------------------------------------------------------



/*2.2 ORDER BY*/
/*Task - Select all albums in Album table and sort result set in descending order by title*/
--SELECT *
--FROM album
--ORDER BY title DESC;

/*Select first name from customer and sort result in ascending order by city*/
--SELECT firstname
--FROM customer
--ORDER BY city ASC;
--2.2-------------------------------------------------------------------------------------------



/*2.3 INSERT INTO*/
/*Task - Insert two new records into Genre table*/
--INSERT INTO genre (genreid, name)
--VALUES (26,'Other');
--
--INSERT INTO genre (genreid, name)
--VALUES (27,'Classic Rock');
--
--SELECT *
--FROM genre; --this last section is just here to check that it worked
------------------

/*Task - Insert two new records into Employee table*/
--INSERT INTO employee (employeeid, lastname, firstname, title, reportsto, birthdate, hiredate, address, city, state, country, postalcode, phone, fax, email)
--VALUES (9,'Gates','Bill','OS Manager',1,DATE '1955-10-28',DATE '2000-01-01','1 Microsoft Way','Redmond','WA','USA',98052,'+1 425-882-8080',NULL,'windows@chinookcorp.com');

--INSERT INTO employee (employeeid, lastname, firstname, title, reportsto, birthdate, hiredate, address, city, state, country, postalcode, phone, fax, email)
--VALUES (10,'Torvalds','Linus','OS Developer',9,DATE '1969-12-28',DATE '2000-02-01','1 Microsoft Way','Redmond','WA','USA',98052,'+1 425-882-8080',NULL,'linux@chinookcorp.com');

--SELECT *
--FROM employee; --this last section is just here to check that it worked
-----------------


/*Task - Insert two new records into the Customer table*/
--INSERT INTO customer (customerid, firstname, lastname, company,address,city,state, country, postalcode, phone,fax, email, supportrepid)
--VALUES(60,'Robert','Plant','Led Zeppelin','123 Rock St','London',NULL,'England','WC2N 5DU','+44 20-7499-9000', NULL, 'getTheLedOut@zeppelin.uk',5);

--INSERT INTO customer (customerid, firstname, lastname, company, address, city, state, country, postalcode, phone, fax, email, supportrepid)
--VALUES(61,'Jimmy','Page','Led Zeppelin','123 Rock St','London',NULL,'England','WC2N 5DU','+44 20-7499-9000',NULL,'stairwayToHeave@zeppelin.uk',5);

--SELECT *
--FROM customer;    --this last section is just here to check that it worked
--2.3-------------------------------------------------------------------------------------------


/*2.4 UPDATE*/
/*Task - Update Aron Mitchel in Customer table to Robert Walter*/
--UPDATE customer
--SET firstname='Robert',lastname='Walter'
--WHERE firstname='Aaron' AND lastname='Mitchell';
----show output -> should now be two results with firstnam='Robert'
--SELECT *
--FROM customer
--WHERE firstname='Robert';


/*Task - Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR"*/
--UPDATE artist
--SET name='CCR'
--WHERE name='Creedence Clearwater Revival';
----show output
--SELECT *
--FROM ARTIST
--WHERE name='CCR';
--2.4-------------------------------------------------------------------------------------------


/*2.5 LIKE*/
/*Task - Select all invoices with a billing address like "T%"*/
--SELECT *
--FROM invoice
--WHERE billingaddress LIKE 'T%';
--2.5-------------------------------------------------------------------------------------------


/*2.6 BETWEEN*/
/*Task - Select all invoices that have a total between 15 and 50*/
--SELECT *
--FROM invoice
--WHERE total BETWEEN 15 AND 50;


/*Task - Select all employees hired between 1st of June 2003 and 1st of March 2004*/
--SELECT *
--FROM employee
--WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';
--2.6-------------------------------------------------------------------------------------------


/*2.7 DELETE*/
/*Task - Delete a record in Cutomer table where the name is Robert Walter
(There may be constraints that rely on this, find out how to resolve them*/
--show constraints
--SELECT *
--FROM user_constraints
--ORDER BY r_constraint_name ASC;
--
--ALTER TABLE invoice
--DROP CONSTRAINT fk_invoicecustomerid;
--
--DELETE FROM customer
--WHERE firstname='Robert' AND lastname='Walter';
--2.7-------------------------------------------------------------------------------------------


/*3.1 System Defined Functions*/
/*Task - Create a function that returns the current time*/
--CREATE OR REPLACE FUNCTION get_current_time
--RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;
--BEGIN
--    SELECT systimestamp --predefined variable that is already in oracle database
--    INTO l_systimestamp
--    FROM dual;
--    RETURN l_systimestamp;
--END;
--/   --this tells the db manager software to ignore everything after the slash
--    --kind of like the ultimate semi-colon
--
----^^highlight the above block (including the slash) and run that section first - this compiles the function
--
----then run this statement by itself to execute the function
----execute
--SELECT get_current_time() FROM dual;


/*Task - Create a function that returns the length of a mediatype from the mediatype table*/
--CREATE OR REPLACE FUNCTION get_mediatype_length
--RETURN SYS_REFCURSOR IS my_cursor SYS_REFCURSOR;
--BEGIN
--    OPEN my_cursor FOR 
--    SELECT LENGTH(name) 
--    FROM mediatype;
--    RETURN my_cursor;
--END;
--/
------execute
--SELECT get_mediatype_length() FROM dual;
--this doesnt work as expected?

--3.1---------------------------------------------------------------------------------------



/*3.2 System Defined Aggregate Functions*/
/*Task - Create a function that returns the average total of all invoices*/
--CREATE OR REPLACE FUNCTION get_average_invoice_total
--RETURN NUMBER IS avg_total NUMBER(10,2);
--BEGIN
--    SELECT AVG(total)
--    INTO avg_total
--    FROM invoice;
--    RETURN avg_total;
--END;
--/
--execute
--SELECT get_average_invoice_total() FROM dual;


                                            

/*Task - Create a function that returns the most expensive track*/
--CREATE OR REPLACE FUNCTION get_max_price_track
--RETURN NUMBER IS max_price Number(4,2);
--BEGIN
--    SELECT MAX(unitprice)
--    INTO max_price
--    FROM track;
--    RETURN max_price;
--END;
--/
----execute
--SELECT get_max_price_track() FROM dual;
--3.2----------------------------------------------------------------------------------------------------


/*3.3 User Defined Scalar Functions*/
/*Task - Create a function that returns the average price of invoiceline items in the invoiceline table*/
--CREATE OR REPLACE FUNCTION avg_invoiceline
--RETURN NUMBER IS avg_line NUMBER(4,2);
--BEGIN
--    SELECT AVG(unitprice)
--    INTO avg_line
--    FROM invoiceline;
--    RETURN avg_line;
--END;
--/
--
----execute
--SELECT avg_invoiceline FROM dual;

--3.3.----------------------------------------------------------------------------------------------------


/*3.4 User Defined Table Value Functions*/
/*Task - Create a function that returns all employees who are born after 1968*/
--CREATE OR REPLACE FUNCTION employees_sixty_eight
--RETURN SYS_REFCURSOR IS my_cursor SYS_REFCURSOR;
--BEGIN
--    OPEN my_cursor FOR
--    SELECT * FROM employee
--    WHERE birthdate > DATE '1968-01-01';
--    RETURN my_cursor;
--END;
--/

--execute
--SELECT employees_sixty_eight() FROM employee;
--the output for this function is super weird
--3.4-----------------------------------------------------------------------------------------------------


/*4.1 Basic Stored Procedure*/
/*Task - Create a stored procedure that selectes the first and last names of all the employees*/
--CREATE OR REPLACE PROCEDURE get_emp_names(cursorParam OUT SYS_REFCURSOR)
--IS
--BEGIN
--    OPEN cursorParam FOR
--    SELECT firstname,lastname FROM employee;
--END;
--/
--execute
--VARIABLE curVar REFCURSOR;
--EXECUTE get_emp_names(:curVar);
--PRINT curVar;
--4.1-----------------------------------------------------------------------------------------------------


/*4.2 Stored Procedure Input Parameters*/
/*Task - Create a stored procedure that updates the personal information of an employee*/
--CREATE OR REPLACE PROCEDURE update_employee(
--emp_id IN NUMBER,
--new_lastname IN VARCHAR2,
--new_firstname IN VARCHAR2,
--new_title IN VARCHAR2,
--new_bday IN DATE,
--new_address IN VARCHAR2,
--new_city IN VARCHAR2,
--new_state IN VARCHAR2,
--new_country IN VARCHAR2,
--new_postalcode IN VARCHAR2,
--new_phone IN VARCHAR2,
--new_fax IN VARCHAR2,
--new_email IN VARCHAR2)
--IS
--BEGIN
--    UPDATE employee 
--    SET lastname=new_lastname,firstname=new_firstname,title=new_title,birthdate=new_bday,address=new_address,city=new_city,state=new_state,country=new_country,postalcode=new_postalcode,phone=new_phone,fax=new_fax,email=new_email
--    WHERE employeeid=emp_id;
--END;
--/                     
--execute



/*Task - Create a stored procedure that returns the managers of an employee*/
--CREATE OR REPLACE PROCEDURE get_managers(empid IN NUMBER,managers OUT NUMBER)
--IS
--BEGIN
--    SELECT reportsto
--    INTO managers
--    FROM employee
--    WHERE employeeid = empid;
--END;
--/
--execute

------------^^^^is this supposed to be the names of the managers or just the numbers?
--4.2-----------------------------------------------------------------------------------------------------


/*4.3 Stored Procedure Output Parameters*/
/*Task - Create a stored procedure that returns the name and company of a customer*/
--CREATE OR REPLACE PROCEDURE get_customer_name_and_company(
--cust_id IN NUMBER, cust_f_name OUT VARCHAR2, cust_l_name OUT VARCHAR2, cust_company OUT VARCHAR2)
--IS
--BEGIN
--    SELECT firstname, lastname, company
--    INTO cust_f_name, cust_l_name, cust_company
--    FROM customer
--    WHERE customerid = cust_id;
--END;
--/

--declare and execute
--SET SERVEROUTPUT ON;
--DECLARE
--c_id NUMBER;
--f_name VARCHAR(100);
--l_name VARCHAR(100);
--c_comp VARCHAR2(100);
--BEGIN
--c_id := 1;
--get_customer_name_and_company(
--c_id, f_name, l_name, c_comp);
--DBMS_OUTPUT.PUT_LINE(''||f_name||' '||l_name||' '||c_comp||'');
--END;
--/
--4.3-----------------------------------------------------------------------------------------------------


/*5.0 Transactions*/
/*Task - Create a transaction that given an invoiceId will delete that invoice
(There may be constraints that rely on this, find out how to resolve them)*/
--CREATE OR REPLACE PROCEDURE invoice_delete_transaction(in_id IN invoice.invoiceid%TYPE)
--IS
--BEGIN
--    DELETE FROM invoice WHERE invoiceid=in_id;
--END;
--/
-----------------------
--
------show constraints
--SELECT *
--FROM user_constraints
--ORDER BY r_constraint_name ASC;
--
------drop offending constraint
--ALTER TABLE invoiceline
--DROP CONSTRAINT fk_invoicelineinvoiceid;
--
------add cascade on delete
--ALTER TABLE invoiceline
--ADD CONSTRAINT fk_invoicelineinvoiceid
--  FOREIGN KEY (invoiceid)
--  REFERENCES invoice(invoiceid)
--  ON DELETE CASCADE;
--
--
----execute section
----check for last invoiceid
--SELECT * FROM invoice WHERE invoiceid > 400;
--------------------
----execture
--DECLARE
--test_id NUMBER;
--BEGIN
--test_id := 412;         --change this as needed
--invoice_delete_transaction(test_id);
--END;
--/
-------------
----check
--SELECT * FROM invoice WHERE invoiceid > 400;



/*Task Create a transaction nested within a stored procedure that inserts a new record
in the Customer table*/
----sequence for use in auto-generating next customerid
--CREATE SEQUENCE customer_seq
--MINVALUE 1
--MAXVALUE 9999999999
--INCREMENT BY 1
--START WITH 276;
--/
----------------
----trigger to auto-generate next customerid
--CREATE OR REPLACE TRIGGER customer_trigger
--BEFORE INSERT ON customer
--FOR EACH ROW            --definitely want to do it for each row when we are incrementing
--BEGIN
--    SELECT customer_seq.NEXTVAL
--    INTO :new.customerid
--    FROM dual;
--END;
--/
------------------
----insert customer procedure/transaction
--CREATE OR REPLACE PROCEDURE customer_insert_transaction(
--c_Fname IN customer.firstname%TYPE,
--c_Lname IN customer.lastname%TYPE,
--c_company IN customer.company%TYPE,
--c_address IN customer.address%TYPE,
--c_city IN customer.city%TYPE,
--c_state IN customer.state%TYPE,
--c_country IN customer.country%TYPE,
--c_postalcode IN customer.postalcode%TYPE,
--c_phone IN customer.phone%TYPE,
--c_fax IN customer.fax%TYPE,
--c_email IN customer.email%TYPE,
--c_repID IN customer.supportrepid%TYPE)
--IS
--BEGIN
--    INSERT INTO customer(customerid,firstname,lastname,company,address,city,state,country,postalcode,phone,fax,email,supportrepid)
--    VALUES(customer_seq.NEXTVAL,c_Fname,c_Lname,c_company,c_address,c_city,c_state,c_country,c_postalcode,c_phone,c_fax,c_email,c_repID);
--    COMMIT;
--END;
--/



--5.0-----------------------------------------------------------------------------------------------------


/*6.1 Triggers After/For*/
/*Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table*/
--CREATE OR REPLACE TRIGGER after_insert_on_employee_table
--AFTER
--INSERT ON employee
--BEGIN
--    DBMS_OUTPUT.PUT_LINE('New employee added to table!');           --might need to enable with SET SERVEROUTPUT ON?
--END;
--/

/*Task - Create an after update trigger on the album table that fires after a row is inserted in the table*/
--CREATE OR REPLACE TRIGGER after_update_on_album_table                              --^^^^^^^^is this supposed to be update?
--AFTER
--UPDATE OF albumid OR UPDATE OF title OR UPDATE OF artistid
--ON album
--FOR EACH ROW
--BEGIN
--    DBMS_OUTPUT.PUT_LINE('albumid:'||:old.albumid||'-->'||:new.albumid);              --might need to enable with SET SERVEROUTPUT ON?
--    DBMS_OUTPUT.PUT_LINE('title:'||:old.title||'-->'||:new.title);
--    DBMS_OUTPUT.PUT_LINE('artisid:'||:old.artistid||'-->'||:new.artistid);
--    DBMS_OUTPUT.PUT_LINE('==============');
--END;
--/

/*Task - Create an after delete trigger on the customer table that fires after a row is deleted from the table*/
--CREATE OR REPLACE TRIGGER after_delete_on_customer
--AFTER
--DELETE ON customer
--BEGIN
--    DBMS_OUTPUT.PUT_LINE('Row deleted from customer table');                    --might need to enable with SET SERVEROUTPUT ON?
--END;
--/

--6.1------------------------------------------------------------------------------------------------------



/*7.1 JOINs - Inner*/
/*Task - Create an inner join that joins customers and orders and specifies the name of the customer
and the invoiceId*/
--SELECT c.firstname,c.lastname,i.invoiceid
--FROM customer c
--INNER JOIN invoice i
--ON c.customerid = i.customerid;

--7.1-------------------------------------------------------------------------------------------------------


/*7.2 JOINs - Outer*/
/*Task - Create an outer join that joins the customer and invoice table, specifying the CustomerId, 
firstname, lastname, invoiceId, and total*/
--SELECT c.customerid,c.firstname,c.lastname,i.invoiceid,i.total
--FROM customer c
--LEFT OUTER JOIN invoice i
--ON c.address = i.billingaddress;

--7.2--------------------------------------------------------------------------------------------------------


/*7.3 JOINs - Right*/
/*Task - Create a right join that joins album and artist specifying artist name and title*/
--SELECT artist.name,album.title
--FROM album
--RIGHT OUTER JOIN artist
--ON artist.artistid = album.ARTISTID;
--7.3---------------------------------------------------------------------------------------------------------


/*7.4 JOINs - Cross*/
/*Task - Create a cross join that joins album and artist and sorts by artist name in ascending order*/
--SELECT *
--FROM album
--CROSS JOIN artist a
--ORDER BY a.name ASC;
--7.4----------------------------------------------------------------------------------------------------------


/*7.5 JOINs - Self*/
/*Task - Perform a self-join on the employee table, joining on the reportsto column*/
--SELECT *
--FROM employee e
--JOIN employee em
--ON e.reportsto = em.reportsto;

--7.5------------------------------------------------------------------------------------------------------------

--not sure which problem this goes to
--CREATE OR REPLACE PROCEDURE get_all_artists(cursorParam OUT SYS_REFCURSOR)
--IS                                          --var name ,IN or OUT, var type
--BEGIN
--    OPEN cursorParam FOR
--    SELECT * FROM artist;
--END;
--/
--
--VARIABLE curVar REFCURSOR;
--EXECUTE GET_ALL_ARTISTS(:curVar);
--PRINT curVar;



