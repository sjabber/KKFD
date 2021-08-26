--[메인] 인기프로젝트
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
--[메인] 신규프로젝트
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
--[메인] 공개예정 프로젝트
SELECT 3 AS c, r.*
FROM (SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_goals,
            NVL2(b.book_no,1,0) AS bm--,p.proj_start
        FROM kk_project p
           LEFT OUTER JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        WHERE p.proj_status=1 AND SYSDATE < p.proj_start --DB시간 아니면 java시간?
        ORDER BY p.proj_no DESC
    ) r
WHERE ROWNUM BETWEEN 1 AND 8

UNION
--[메인] 성공임박
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




--[제품 상세]
SELECT p.proj_no,p.proj_category, p.proj_title, p.proj_fm, p.proj_quantity, p.proj_goals, p.proj_end, p.proj_intro,
        p.proj_targetcnt,p.proj_limitcnt,NVL2(b.book_no,1,0) AS bm, c.cr_nn, c.cr_intro
    FROM kk_project p
        LEFT OUTER JOIN kk_bookmark b ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        JOIN kk_creator c ON( p.proj_id=c.cr_id)
    WHERE p.proj_no=1;
    
--[제품 상세]창작자 이전 프로젝트
SELECT *
    FROM(SELECT proj_no, proj_title, proj_goals, proj_end, proj_summary
        FROM kk_project
        WHERE proj_id =(SELECT proj_id FROM kk_project WHERE proj_no=1)--proj_no=#{no}
        AND(proj_no != 1) --proj_no!=#{no}
        ORDER BY proj_no DESC
        )
    WHERE ROWNUM<3;--2개까지
           
--[마이페이지] 북마크
SELECT *
    FROM(SELECT ROWNUM AS rn, p.proj_no, p.proj_category, p.proj_title, p.proj_goals, p.proj_summary, p.proj_start, p.proj_end
        FROM kk_project p
           JOIN kk_bookmark b 
           ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
           ORDER BY b.book_no DESC
        )
WHERE rn BETWEEN START_ROW(2,18) AND END_ROW(2,18);

--[마이페이지] 마이 프로젝트
SELECT *
FROM (SELECT ROWNUM AS rn,p.*
      FROM(SELECT  proj_no, proj_title, proj_start, proj_end, proj_goals, proj_fm, proj_quantity, proj_status
            FROM kk_project
            WHERE proj_id='t'--id=#{id}
            ORDER BY proj_no DESC
            ) p
) WHERE rn BETWEEN START_ROW(2,10) AND END_ROW(2,10);
--WHERE rn BETWEEN START_ROW(#{currentPage},#{cntPerPage}) AND END_ROW(#{currentPage},#{cntPerPage})

--[마이페이지] 후원자보기                                 --proj_no=#{no}
SELECT (SELECT proj_delivery FROM kk_project WHERE proj_no=1),
        f.fun_no, f.fun_quantity, f.fun_fm, f.fun_track,
        m.mem_name,m.mem_phone
FROM kk_funding f
    JOIN kk_member m ON(f.fun_id=m.mem_id)
WHERE f.fun_proj=2  --f.fun_proj=#{no}
ORDER BY f.fun_no;

--[마이페이지] 펀딩한 프로젝트
SELECT f.fun_no, f.fun_fm, f.fun_track,
        p.proj_no, p.proj_title, p.proj_goals, p.proj_start, p.proj_end, p.proj_delivery, p.proj_status
FROM kk_funding f
    JOIN kk_project p ON(f.fun_proj = p.proj_no)
WHERE f.fun_id ='id1'--p.proj_id=#{id};
ORDER BY f.fun_no DESC;
        
--[조회]
SELECT *
 FROM (SELECT ROWNUM AS rn, p.*, NVL2(b.book_no,1,0) AS bm
        FROM (SELECT proj_no, proj_category, proj_title, proj_summary, proj_start, proj_end, 
                proj_goals, proj_targetcnt, proj_fm, proj_quantity
                FROM kk_project
                WHERE proj_no >0             
                    --[동적쿼리]
                    --(검색)
                    --AND (proj_title LIKE '%word%')
                    --(proj_title='%${word}%’ OR proj_summary='%${word}%’)
 	        --<when test="category!=0>
                    AND proj_category =1
                    --AND proj_category =2
                    --(달성률)
                    AND (proj_goals <= 75 AND proj_goals>=0)
                    --AND (proj_goals>75 AND proj_goals<100)
                   --AND proj_goals >=100
                   --(진행상태) 
                    --AND SYSDATE  < proj_start                             --진행예정
                    --AND (proj_start < SYSDATE AND SYSDATE<proj_end)   --진행중
                    --AND (proj_end < SYSDATE)                          --마감
            ORDER BY proj_bmcnt DESC --prod_no DESC  prod_end ASC
		--<if test="standard=0">
            ) p
        LEFT OUTER JOIN kk_bookmark b 
            ON (p.proj_no=b.book_proj AND b.book_id='id1')--id=#{id}
        )
WHERE rn BETWEEN START_ROW(1,18) AND END_ROW(1,15);
--WHERE rn BETWEEN START_ROW(#{currentPage},#{cntPerPage}) AND END_ROW(#{currentPage},#{cntPerPage})

--[조회] 페이징용 totalcnt
SELECT COUNT(proj_no) AS cnt
FROM kk_project
WHERE proj_no >0 
    AND proj_status=1
    --[동적쿼리]
    --(검색)
    --AND (proj_title LIKE '%word%' OR proj_summary LIKE '%word%')
    --(proj_title='%${word}%’ OR proj_summary='%${word}%’)
    AND proj_category =1
    --AND proj_category =2
    --(달성률)
    AND (proj_goals <= 75 AND proj_goals>=0)
    --AND (proj_goals>75 AND proj_goals<100)
    --AND proj_goals >=100
    --(진행상태)
    AND SYSDATE < proj_start;                            --진행예정
    --AND (proj_start < SYSDATE AND SYSDATE<proj_end)   --진행중
    --AND (proj_end < SYSDATE AND proj_goals<100)       --성공
    --AND (proj_end < SYSDATE AND proj_goals>100)       --실패


    
        