--�ݵ� Ʈ���� --�ݵ� ���̺� �� �߰��� �� ������Ʈ ���̺��� �ֹ�����, �޼��� �÷� ������Ʈ!
CREATE OR REPLACE TRIGGER funding_trg
AFTER
INSERT ON kk_funding
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_quantity=proj_quantity+:NEW.fun_quantity, proj_goals=TRUNC(((proj_quantity+:NEW.fun_quantity)/proj_targetcnt)*100) WHERE proj_no=:NEW.fun_proj;
END;

--�ϸ�ũ Ʈ����1 --�ϸ�ũ ���̺� �� �߰��� �� ������Ʈ ���̺��� �ϸ�ũ�� �÷� ������Ʈ!
CREATE OR REPLACE TRIGGER bookmark_trg1
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt = proj_bmcnt + 1 WHERE proj_no=:NEW.book_proj;
END;

--�ϸ�ũ Ʈ����2 --�ϸ�ũ ���̺� �� ������ �� ������Ʈ ���̺��� �ϸ�ũ�� �÷� ������Ʈ!
CREATE OR REPLACE TRIGGER bookmark_trg2
AFTER
DELETE ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt=proj_bmcnt-1 WHERE proj_no=:OLD.book_proj;
END;





--�Լ� --�ݵ� ��û ���� ��� Ȯ��
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

--�׽�Ʈ 1(200)
SELECT f_kk_funding_insert('id1', 2, 7, 350000, '����ȣ', '��� �Ƿɱ����ǵ� 30����', '���ּ� 30', '����', '1111-1111-1140') FROM dual;

--�׽�Ʈ 0(300)
SELECT f_kk_funding_insert('id2', 3, 7, 350000, '�輺��', '��� �Ƿɱ� ���ǵ� 30����', '���ּ� 30', '����', '1111-1111-1140') FROM dual;

--�׽�Ʈ -1(500)
SELECT f_kk_funding_insert('id3', 1, 7, 350000, '������', '��� �Ƿɱ� �����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������ǵ� 30����', '���ּ� 30', '����', '1111-1111-1140') FROM dual;

DELETE FROM kk_funding WHERE fun_no > 55;

SELECT * FROM kk_funding ORDER BY fun_no DESC;

SELECT * FROM kk_project WHERE proj_no = 6;


SELECT f_kk_funding_insert('id1', 1, 1, 39000, '�輭��', '���� ���ϱ� ������ 1����', '���ּ� 1', '��������', '1111-1111-1111') FROM dual;