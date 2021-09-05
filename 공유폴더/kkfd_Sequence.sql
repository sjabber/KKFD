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


-- funding ?‹œ???Š¤?˜ ?‹¤?Œê°? ?™•?¸
SELECT funding.NEXTVAL
FROM dual;

-- funding ?‹œ???Š¤?˜ ?‹¤?Œê°? ë³?ê²?
-- ?˜„?¬ê°?-2 = ?‹¤?Œ ?‹œ???Š¤ê°? ?œ¼ë¡? ë§ì¶˜?‹¤.
ALTER SEQUENCE funding
INCREMENT BY -2;