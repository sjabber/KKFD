--[����] �α�������Ʈ
SELECT 1 AS proj_area, r.*
FROM (SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_goals,
            NVL2(b.book_no,1,0) AS bm --p.proj_bmcnt,p.proj_end
        FROM kk_project p
           LEFT OUTER JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        WHERE p.proj_status=1 AND  SYSDATE < p.proj_end
        ORDER BY p.proj_bmcnt DESC
    ) r
WHERE ROWNUM BETWEEN 1 AND 8

UNION
--[����] �ű�������Ʈ
SELECT 2 AS c, r.*
FROM (SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_goals,
            NVL2(b.book_no,1,0) AS bm--,p.proj_start,p.proj_end
        FROM kk_project p
           LEFT OUTER JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        WHERE p.proj_status=1 AND p.proj_start< SYSDATE 
        ORDER BY  p.proj_start DESC
    ) r
WHERE ROWNUM BETWEEN 1 AND 8

UNION
--[����] �������� ������Ʈ
SELECT 3 AS c, r.*
FROM (SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_goals,
            NVL2(b.book_no,1,0) AS bm--,p.proj_start
        FROM kk_project p
           LEFT OUTER JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        WHERE p.proj_status=1 AND SYSDATE < p.proj_start --DB�ð� �ƴϸ� java�ð�?
        ORDER BY p.proj_no DESC
    ) r
WHERE ROWNUM BETWEEN 1 AND 8

UNION
--[����] �����ӹ�
SELECT 4 AS c, r.*
FROM (SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_goals,
            NVL2(b.book_no,1,0) AS bm--, p.proj_end
        FROM kk_project p
           LEFT OUTER JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        WHERE p.proj_status=1 
        AND ( p.proj_goals BETWEEN 75 AND 100)
        AND SYSDATE < p.proj_end
        ORDER BY p.proj_end ASC
    ) r
WHERE ROWNUM BETWEEN 1 AND 8;




--[��ǰ ��]
SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_fm, p.proj_quantity, p.proj_goals, p.proj_end, p.proj_intro,
        p.proj_targetcnt,p.proj_limitcnt,NVL2(b.book_no,1,0) AS bm, c.cr_nn, c.cr_intro
    FROM kk_project p
        LEFT OUTER JOIN kk_bookmark b ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        JOIN kk_creator c ON( p.proj_id=c.cr_id)
    WHERE p.proj_no=1;
    
--[��ǰ ��]â���� ���� ������Ʈ
SELECT *
    FROM(SELECT proj_no, proj_title, proj_goals, proj_end, proj_summary
        FROM kk_project
        WHERE proj_id =(SELECT proj_id FROM kk_project WHERE proj_no=1)--proj_no=#{no}
        AND(proj_no != 1) --proj_no!=#{no}
        ORDER BY proj_no DESC
        )
    WHERE ROWNUM<3;--2������
           
--[����������] �ϸ�ũ
SELECT *
    FROM(SELECT ROWNUM AS rn, p.proj_no, p.proj_category, p.proj_title, p.proj_goals, p.proj_summary, p.proj_start, p.proj_end
        FROM kk_project p
           JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
           ORDER BY b.book_no DESC
        )
WHERE rn BETWEEN START_ROW(2,18) AND END_ROW(2,18);

--[����������] ���� ������Ʈ
SELECT *
FROM (SELECT ROWNUM AS rn,p.*
      FROM(SELECT  proj_no, proj_title, proj_start, proj_end, proj_goals, proj_fm, proj_quantity, proj_status
            FROM kk_project
            WHERE proj_id='t'--id=#{id}
            ORDER BY proj_no DESC
            ) p
) WHERE rn BETWEEN START_ROW(2,10) AND END_ROW(2,10);
--WHERE rn BETWEEN START_ROW(#{currentPage},#{cntPerPage}) AND END_ROW(#{currentPage},#{cntPerPage})

--[����������] �Ŀ��ں���                                 --proj_no=#{no}
SELECT (SELECT proj_delivery FROM kk_project WHERE proj_no=1),
        f.fun_no, f.fun_quantity, f.fun_fm, f.fun_track,
        m.mem_name,m.mem_phone
FROM kk_funding f
    JOIN kk_member m ON(f.fun_id=m.mem_id)
WHERE f.fun_proj=2  --f.fun_proj=#{no}
ORDER BY f.fun_no;

--[����������] �ݵ��� ������Ʈ
SELECT f.fun_no, f.fun_fm, f.fun_track,
        p.proj_no, p.proj_title, p.proj_goals, p.proj_start, p.proj_end, p.proj_delivery, p.proj_status
FROM kk_funding f
    JOIN kk_project p ON(f.fun_proj = p.proj_no)
WHERE f.fun_id ='id1'--p.proj_id=#{id};
ORDER BY f.fun_no DESC;
        
--[��ȸ]
SELECT *
 FROM (SELECT ROWNUM AS rn, p.*, NVL2(b.book_no,1,0) AS bm
        FROM (SELECT proj_no, proj_category, proj_title, proj_summary, proj_start, proj_end, 
                proj_goals, proj_targetcnt, proj_fm, proj_quantity
                FROM kk_project
                WHERE proj_no >0             
                    --[��������]
                    --(�˻�)
                    --AND (proj_title LIKE '%word%')
                    --(proj_title='%${word}%�� OR proj_summary='%${word}%��)
 	        --<when test="category!=0>
                    AND proj_category =1
                    --AND proj_category =2
                    --(�޼���)
                    AND (proj_goals <= 75 AND proj_goals>=0)
                    --AND (proj_goals>75 AND proj_goals<100)
                   --AND proj_goals >=100
                   --(�������) 
                    --AND SYSDATE  < proj_start                             --���࿹��
                    --AND (proj_start < SYSDATE AND SYSDATE<proj_end)   --������
                    --AND (proj_end < SYSDATE)                          --����
            ORDER BY proj_bmcnt DESC --prod_no DESC  prod_end ASC
		--<if test="standard=0">
            ) p
        LEFT OUTER JOIN kk_bookmark b 
            ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        )
WHERE rn BETWEEN START_ROW(1,18) AND END_ROW(1,15);
--WHERE rn BETWEEN START_ROW(#{currentPage},#{cntPerPage}) AND END_ROW(#{currentPage},#{cntPerPage})

--[��ȸ] ����¡�� totalcnt
SELECT COUNT(proj_no) AS cnt
FROM kk_project
WHERE proj_no >0 
    AND proj_status=1
    --[��������]
    --(�˻�)
    --AND (proj_title LIKE '%word%' OR proj_summary LIKE '%word%')
    --(proj_title='%${word}%�� OR proj_summary='%${word}%��)
    AND proj_category =1
    --AND proj_category =2
    --(�޼���)
    AND (proj_goals <= 75 AND proj_goals>=0)
    --AND (proj_goals>75 AND proj_goals<100)
    --AND proj_goals >=100
    --(�������)
    AND SYSDATE < proj_start;                            --���࿹��
    --AND (proj_start < SYSDATE AND SYSDATE<proj_end)   --������
    --AND (proj_end < SYSDATE AND proj_goals<100)       --����
    --AND (proj_end < SYSDATE AND proj_goals>100)       --����


    
        