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


-- funding ????€? ?€?κ°? ??Έ
SELECT funding.NEXTVAL
FROM dual;

-- funding ????€? ?€?κ°? λ³?κ²?
-- ??¬κ°?-2 = ?€? ????€κ°? ?Όλ‘? λ§μΆ?€.
ALTER SEQUENCE funding
INCREMENT BY -2;