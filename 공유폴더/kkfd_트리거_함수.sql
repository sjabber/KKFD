--펀딩 트리거 --펀딩 테이블에 행 추가될 때 프로젝트 테이블의 주문수량, 달성률 컬럼 업데이트!
CREATE OR REPLACE TRIGGER funding_trg
AFTER
INSERT ON kk_funding
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_quantity=proj_quantity+:NEW.fun_quantity, proj_goals=TRUNC(((proj_quantity+:NEW.fun_quantity)/proj_targetcnt)*100) WHERE proj_no=:NEW.fun_proj;
END;

--북마크 트리거1 --북마크 테이블에 행 추가될 때 프로젝트 테이블의 북마크수 컬럼 업데이트!
CREATE OR REPLACE TRIGGER bookmark_trg1
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt = proj_bmcnt + 1 WHERE proj_no=:NEW.book_proj;
END;

--북마크 트리거2 --북마크 테이블에 행 삭제될 때 프로젝트 테이블의 북마크수 컬럼 업데이트!
CREATE OR REPLACE TRIGGER bookmark_trg2
AFTER
DELETE ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt=proj_bmcnt-1 WHERE proj_no=:OLD.book_proj;
END;





--함수 --펀딩 신청 직전 재고를 확인
CREATE OR REPLACE FUNCTION f_kk_funding_insert
(
v_fun_id        in VARCHAR2,
v_fun_proj      in NUMBER,
v_fun_quantity  in NUMBER,
v_fun_fm        in NUMBER,
v_fun_name      in VARCHAR2,
v_fun_address   in VARCHAR2,
v_fun_detail    in VARCHAR2,
v_fun_bank      in VARCHAR2,
v_fun_acno      in VARCHAR2
)
RETURN NUMBER
IS
PRAGMA AUTONOMOUS_TRANSACTION;
inventory NUMBER;
v_errorcode NUMBER;
BEGIN
SELECT (proj_limitcnt-proj_quantity) INTO inventory
FROM kk_project
WHERE proj_no = v_fun_proj;

IF v_fun_quantity <= inventory THEN 
 INSERT INTO kk_funding 
 VALUES (funding.NEXTVAL, v_fun_id, v_fun_proj, 
 v_fun_quantity, v_fun_fm, v_fun_name, v_fun_address,
 v_fun_detail, v_fun_bank, v_fun_acno, 0, SYSDATE);
 COMMIT;
 v_errorcode:=1; --200
 RETURN v_errorcode;
 ELSE
 v_errorcode:=0; --300
 RETURN v_errorcode;
END IF;
EXCEPTION
 WHEN OTHERS THEN
 ROLLBACK;
  v_errorcode:=-1; --500
RETURN v_errorcode;
END;
/

drop function f_kk_funding_insert;

commit;

--테스트 1(200)
SELECT f_kk_funding_insert('id1', 2, 7, 350000, '김태호', '경북 의령군경의동 30번지', '상세주소 30', '농협', '1111-1111-1140') FROM dual;

--테스트 0(300)
SELECT f_kk_funding_insert('id2', 3, 7, 350000, '김성열', '경북 의령군 경의동 30번지', '상세주소 30', '농협', '1111-1111-1140') FROM dual;

--테스트 -1(500)
SELECT f_kk_funding_insert('id3', 1, 7, 350000, '김태일', '경북 의령군 ㅁㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㅁㄴㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㄻㄴㅇㄹㄴㅇㅋ경의동 30번지', '상세주소 30', '농협', '1111-1111-1140') FROM dual;

DELETE FROM kk_funding WHERE fun_no > 55;

SELECT * FROM kk_funding ORDER BY fun_no DESC;

SELECT * FROM kk_project WHERE proj_no = 6;


SELECT f_kk_funding_insert('id1', 1, 1, 39000, '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111') FROM dual;