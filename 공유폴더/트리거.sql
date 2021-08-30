  --[트리거] 펀딩신청->주문수량+갯수, 달성률 계산
CREATE OR REPLACE TRIGGER kk_proejct
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN 
UPDATE kk_project
    SET proj_bmcnt = proj_bmcnt+1;
END;

UPDATE kk_project
    SET proj_goals = ROUND((proj_quantity+fun_quantity)/proj_targetcnt)*100);
    SET proj_quantity = (proj_quantity+fun_quantity);


--[트리거] 북마크 on -> bmcnt-1
CREATE OR REPLACE TRIGGER kk_bookmark_trg1
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN 
UPDATE kk_project
    SET proj_bmcnt = proj_bmcnt+1;
END;

--[트리거] 북마크 off -> bmcnt-1
CREATE OR REPLACE TRIGGER kk_bookmark_trg2
AFTER
INSERT ON kk_bookmark
FOR EACH ROW
BEGIN 
UPDATE kk_project
    SET proj_bmcnt = proj_bmcnt-1;
END;