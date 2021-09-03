--펀딩 트리거(펀딩 테이블에 행 추가 시 주문수량, 달성률 업데이트)
CREATE OR REPLACE TRIGGER funding_trg
AFTER
INSERT ON kk_funding
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_quantity=proj_quantity+:NEW.fun_quantity, proj_goals=TRUNC(((proj_quantity+:NEW.fun_quantity)/proj_targetcnt)*100) WHERE proj_no=:NEW.fun_proj;
END;

--북마크 트리거1(북마크 테이블에 행 추가 시 북마크 수 +1)
CREATE OR REPLACE TRIGGER bookmark_trg1
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt = proj_bmcnt + 1 WHERE proj_no=:NEW.book_proj;
END;

--북마크 트리거2(북마크 테이블에 행 삭제 시 북마크 수 -1)
CREATE OR REPLACE TRIGGER bookmark_trg2
AFTER
DELETE ON kk_bookmark
FOR EACH ROW
BEGIN
UPDATE kk_project
SET proj_bmcnt=proj_bmcnt-1 WHERE proj_no=:OLD.book_proj;
END;