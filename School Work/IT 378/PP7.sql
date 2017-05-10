CREATE TABLE EMPLOYEES
( Emp# NUMBER(5),
 Lastname VARCHAR2(10),
 Firstname VARCHAR2(10),
 Job_class VARCHAR2(4) );
 
ALTER TABLE EMPLOYEES
	ADD (EmpDate DATE DEFAULT SYSDATE);

ALTER TABLE EMPLOYEES
	ADD (EndDate DATE);

ALTER TABLE EMPLOYEES
	MODIFY (Job_class VARCHAR2(2));
	
TRUNCATE TABLE EMPLOYEES;

DROP TABLE EMPLOYEES;

FLASHBACK TABLE EMPLOYEES
	TO BEFORE DROP;
	
DROP TABLE EMPLOYEES PURGE;
	
	
ALTER TABLE ACCTMANAGER
	ADD (Comm_id NUMBER(2,0) DEFAULT 10); 
	
ALTER TABLE ACCTMANAGER
	ADD (Ben_id NUMBER(2,0));
	
	
CREATE TABLE store_reps
(rep_ID NUMBER(5),
	last VARCHAR2(15),
	first VARCHAR2(10),
	comm CHAR(1));
	

	
	
	