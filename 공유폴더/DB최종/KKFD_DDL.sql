--kk_member [ȸ��]
CREATE TABLE kk_member
(
    mem_id      VARCHAR2(45) NOT NULL
        CONSTRAINT kk_member_id_pk
            PRIMARY KEY,
    mem_pwd     VARCHAR2(45) NOT NULL,
    mem_name    VARCHAR2(20) NOT NULL,
    mem_phone   VARCHAR2(30) NOT NULL,
    mem_email   VARCHAR2(40) NOT NULL
);

--kk_creator [ũ��������]
CREATE TABLE kk_creator
(
    cr_id VARCHAR2(45) NOT NULL
            CONSTRAINT kk_creator_id_pk
                PRIMARY KEY,
            CONSTRAINT kk_creator_id_fk FOREIGN KEY(cr_id)
                REFERENCES kk_member(mem_id),
    cr_nn VARCHAR2(45),
    cr_intro VARCHAR2(200),
    cr_acholder VARCHAR2(20),
    cr_bank VARCHAR(20),
    cr_acno VARCHAR(20)
);

--kk_project [������Ʈ]
CREATE TABLE kk_project
(
    proj_no NUMBER NOT NULL
        CONSTRAINT kk_project_no_pk
            PRIMARY KEY,
    proj_id VARCHAR2(45) NOT NULL,
        CONSTRAINT kk_project_id_fk FOREIGN KEY(proj_id)
            REFERENCES kk_creator(cr_id),
    proj_category NUMBER NOT NULL,
    proj_title VARCHAR2(100) NOT NULL,
    proj_summary VARCHAR2(200) NOT NULL,
    proj_intro VARCHAR2(4000) NOT NULL,
    proj_fm NUMBER NOT NULL,
    proj_targetcnt NUMBER NOT NULL,
    proj_limitcnt NUMBER NOT NULL,
    proj_quantity NUMBER DEFAULT 0 NOT NULL,
    proj_goals NUMBER DEFAULT 0 NOT NULL,
    proj_start DATE NOT NULL,
    proj_end DATE NOT NULL,
    proj_delivery DATE NOT NULL,
    proj_bmcnt NUMBER DEFAULT 0 NOT NULL,
    proj_status NUMBER(1) DEFAULT 1 NOT NULL
);

--kk_bookmark [�ϸ�ũ]
CREATE TABLE kk_bookmark
(
    book_no NUMBER NOT NULL
        CONSTRAINT kk_bookmark_no_pk
            PRIMARY KEY,
    book_id VARCHAR2(45) NOT NULL,
        CONSTRAINT kk_bookmark_id_fk FOREIGN KEY(book_id)
            REFERENCES kk_member(mem_id),
    book_proj NUMBER NOT NULL,
        CONSTRAINT kk_bookmark_proj_fk FOREIGN KEY(book_proj)
            REFERENCES kk_project(proj_no)
);

--kk_funding [�ݵ�]
CREATE TABLE kk_funding
(
    fun_no NUMBER NOT NULL
        CONSTRAINT kk_funding_no_pk
            PRIMARY KEY,
    fun_id VARCHAR2(45) NOT NULL,
        CONSTRAINT kk_funding_id_fk FOREIGN KEY(fun_id)
            REFERENCES kk_member(mem_id),
    fun_proj NUMBER NOT NULL,
        CONSTRAINT kk_funding_proj_fk FOREIGN KEY(fun_proj)
            REFERENCES kk_project(proj_no),
    fun_quantity NUMBER NOT NULL,
    fun_fm NUMBER NOT NULL,
    fun_name VARCHAR2(20) NOT NULL,
    fun_address VARCHAR2(255) NOT NULL,
    fun_detail VARCHAR2(255) NOT NULL,
    fun_bank VARCHAR2(20) NOT NULL,
    fun_acno VARCHAR2(20) NOT NULL,
    fun_track NUMBER(1) DEFAULT 0 NOT NULL,
    fun_date DATE DEFAULT SYSDATE NOT NULL
);