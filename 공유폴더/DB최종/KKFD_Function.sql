--START_ROW
create or replace FUNCTION start_row(first_no number, second_no number)
RETURN number
IS
BEGIN
    RETURN (first_no-1)*second_no+1;
END;

--END_ROW
create or replace FUNCTION end_row(first_no number, second_no number)
RETURN number
IS
BEGIN
    RETURN first_no*second_no;
END;

--펀딩 추가 함수
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