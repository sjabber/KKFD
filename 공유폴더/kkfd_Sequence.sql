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

-- funding 시퀀스의 다음값 확인
SELECT funding.NEXTVAL
FROM dual;

-- funding 시퀀스의 다음값 변경
-- 현재값-2 = 다음 시퀀스값 으로 맞춘다.
ALTER SEQUENCE funding
INCREMENT BY -2;