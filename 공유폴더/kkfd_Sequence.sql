create sequence project
increment by 1
start with 1
minvalue 1
nocycle
CACHE 20;

CREATE SEQUENCE funding
increment by 1
start with 1
minvalue 1
nocycle
cache 20;

CREATE SEQUENCE bookmark
increment by 1
start with 1
minvalue 1
nocycle
cache 20;


alter sequence project increment by 400;
select project.nextval from dual;
alter sequence project increment by 1;


-- funding ?��???��?�� ?��?���? ?��?��
SELECT funding.NEXTVAL
FROM dual;

-- funding ?��???��?�� ?��?���? �?�?
-- ?��?���?-2 = ?��?�� ?��???���? ?���? 맞춘?��.
ALTER SEQUENCE funding
INCREMENT BY -2;