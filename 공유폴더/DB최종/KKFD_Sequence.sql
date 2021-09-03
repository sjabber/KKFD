--프로젝트 시퀀스
create sequence project
increment by 1
start with 1
minvalue 1
nocycle
CACHE 20;

--펀딩 시퀀스
CREATE SEQUENCE funding
increment by 1
start with 1
minvalue 1
nocycle
cache 20;

--북마크 시퀀스
CREATE SEQUENCE bookmark
increment by 1
start with 1
minvalue 1
nocycle
cache 20;