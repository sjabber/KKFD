--�ݵ� Ʈ����(�ݵ� ���̺� �� �߰� �� �ֹ�����, �޼��� ������Ʈ)
CREATE OR REPLACE TRIGGER funding_trg
AFTER
INSERT ON kk_funding
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_quantity=proj_quantity+:NEW.fun_quantity, proj_goals=TRUNC(((proj_quantity+:NEW.fun_quantity)/proj_targetcnt)*100) WHERE proj_no=:NEW.fun_proj;
END;

--�ϸ�ũ Ʈ����1(�ϸ�ũ ���̺� �� �߰� �� �ϸ�ũ �� +1)
CREATE OR REPLACE TRIGGER bookmark_trg1
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt = proj_bmcnt + 1 WHERE proj_no=:NEW.book_proj;
END;

--�ϸ�ũ Ʈ����2(�ϸ�ũ ���̺� �� ���� �� �ϸ�ũ �� -1)
CREATE OR REPLACE TRIGGER bookmark_trg2
AFTER
DELETE ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt=proj_bmcnt-1 WHERE proj_no=:OLD.book_proj;
END;