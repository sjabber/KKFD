CREATE OR REPLACE PROCEDURE p_kk_funding_insert
(
v_fun_id        in VARCHAR2,
v_fun_proj      in NUMBER,
v_fun_quantity  in NUMBER,
v_fun_fm        in NUMBER,
v_fun_name      in VARCHAR2,
v_fun_address   in VARCHAR2,
v_fun_detail    in VARCHAR2,
v_fun_bank      in VARCHAR2,
v_fun_acno      in VARCHAR2,
v_errorcode     out NUMBER
)
IS
inventory NUMBER;
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
 v_errorcode :=200;
 ElSE 
 v_errorcode :=300;
END IF;
EXCEPTION
 WHEN OTHERS THEN
 ROLLBACK;
  v_errorcode :=500;
END;
/

--[테스트1 500]
variable rslt NUMBER;    
EXECUTE p_kk_funding_insert('FmsYKh27', 1, 7, 350000, '김태일', '경북 의령군 ㅁㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㅁㄴㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㄻㄴㅇㄹㄴㅇㅋ경의동 30번지', '상세주소 30', '농협', '1111-1111-1140',:rslt);
print rslt;

----[테스트2 300]
variable rslt NUMBER;    
EXECUTE p_kk_funding_insert('FmsYKh27', 3, 7, 350000, '김성열', '경북 의령군 경의동 30번지', '상세주소 30', '농협', '1111-1111-1140',:rslt);
print rslt;

----[테스트3 200]
variable rslt NUMBER;    
EXECUTE p_kk_funding_insert('FmsYKh27', 2, 7, 350000, '김태호', '경북 의령군경의동 30번지', '상세주소 30', '농협', '1111-1111-1140',:rslt);
print rslt;
